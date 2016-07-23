package com.CNU2016.BugDetector;

import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.BytecodeScanningDetector;
import edu.umd.cs.findbugs.classfile.ClassDescriptor;
import edu.umd.cs.findbugs.classfile.DescriptorFactory;
import edu.umd.cs.findbugs.classfile.MethodDescriptor;
import edu.umd.cs.findbugs.detect.BooleanReturnNull;
import org.apache.bcel.classfile.*;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.Type;

import java.util.List;

public  class CustomDetector extends BytecodeScanningDetector {
// ------------------------------ FIELDS ------------------------------

    protected BugReporter bugReporter;
    private static final ClassDescriptor threadDescriptor= DescriptorFactory.createClassDescriptor(Thread.class);
    private static final ClassDescriptor runnableDescriptor= DescriptorFactory.createClassDescriptor(Runnable.class);

    protected boolean entity;

// --------------------------- CONSTRUCTORS ---------------------------

    public CustomDetector(BugReporter bugReporter)
    {
        this.bugReporter = bugReporter;
    }

// -------------------------- OTHER METHODS --------------------------

public Boolean analyzeSuperClasses(ClassDescriptor invokedObject)
{
    try {

        if(threadDescriptor.equals(invokedObject))
        {
            return Boolean.TRUE;
        }
        return analyzeSuperClasses(invokedObject.getXClass().getSuperclassDescriptor());

    }
    catch(Exception e)
    {
        return Boolean.FALSE;
    }
}

    /**
     *
     */
@Override
public void sawMethod()
{
        Boolean runnableFlag=Boolean.FALSE,threadFlag=Boolean.FALSE;
    try {

        MethodDescriptor invokedMethod = getMethodDescriptorOperand();
        ClassDescriptor invokedObject = getClassDescriptorOperand();

        String className = invokedObject.getClassName();
        String methodName = invokedMethod.getName();

        for (ClassDescriptor descriptor : invokedObject.getXClass().getInterfaceDescriptorList())
        {
            if(runnableDescriptor.equals(descriptor) && methodName.equalsIgnoreCase("run"))
            {
                runnableFlag=Boolean.TRUE;
            }
        }
        threadFlag=methodName.equalsIgnoreCase("run") && analyzeSuperClasses(invokedObject);
        if(threadFlag==Boolean.TRUE)
        {
            System.out.println(invokedObject);
            System.out.println("thread flag true : " + methodName);
            bugReporter.reportBug(new BugInstance(this, "ThreadError", HIGH_PRIORITY).addClassAndMethod(this).addSourceLine(this));

        }
        else if(runnableFlag==Boolean.TRUE)
        {
            System.out.println(invokedObject);
            System.out.println("runnable flag true : "+methodName);
            bugReporter.reportBug(new BugInstance(this, "RunnableError", HIGH_PRIORITY).addClassAndMethod(this).addSourceLine(this));

        }

    }
    catch (Exception e)
    {
    }

}
    @Override
    public void visit(JavaClass obj)
    {
        for (AnnotationEntry entry : obj.getAnnotationEntries()) {
            entity |= "Ljavax/persistence/Entity;".equals(entry.getAnnotationType());
        }
        super.visit(obj);
    }

    @Override
    public void visit(Field obj)
    {
        if (!entity) {
            return;
        }
        if (isInvalid(obj)) {
            bugReporter.reportBug(new BugInstance(this, getBugType(), HIGH_PRIORITY).addClass(this).addField(this));
        }
    }

    @Override
    public void visit(Method obj)
    {
        if (!entity) {
            return;
        }
        if (isInvalid(obj)) {
            bugReporter.reportBug(new BugInstance(this, getBugType(), HIGH_PRIORITY).addClass(this).addMethod(this));
        }
    }

    protected String getBugType()
    {
        return "ThreadError";
    }

   protected boolean isInvalid(FieldOrMethod obj)
   {
       boolean columnAnnotationPresent = false;
       boolean notEmptyAnnotationPresent = false;
       boolean notNullColumn = false;
       for (AnnotationEntry entry : obj.getAnnotationEntries()) {
           if ("Lorg/hibernate/validator/constraints/NotEmpty;".equals(entry.getAnnotationType())) {
               notEmptyAnnotationPresent = true;
           } else if ("Ljavax/persistence/Column;".equals(entry.getAnnotationType())) {
               columnAnnotationPresent = true;
               for (ElementValuePair pair : entry.getElementValuePairs()) {
                   notNullColumn |= "nullable".equals(pair.getNameString()) && "false".equalsIgnoreCase(pair.getValue().stringifyValue());
               }
           }
       }
       Type type;
       if (obj instanceof Field) {
           type = ((Field) obj).getType();
       } else {
           type = ((Method) obj).getReturnType();
       }
       boolean isString = "java.lang.String".equals(type.toString());
       return columnAnnotationPresent && (notNullColumn && isString && !notEmptyAnnotationPresent || !notNullColumn && notEmptyAnnotationPresent);

   }

}
