import java.lang.instrument.ClassFileTransformer;
import java.security.*;
import java.lang.instrument.*;
import java.io.IOException;

import javassist.*;

/**
 * Created by mohanakumar on 20/07/16.
 */
public class SimpleTransformer implements ClassFileTransformer {
    public SimpleTransformer() {
        super();
    }

    public byte[] transform(ClassLoader loader, String className, Class redefiningClass, ProtectionDomain domain, byte[] bytes) throws IllegalClassFormatException
    {
        if (className.contains("java") || className.contains("sun"))
        {
            return null;
        }
        try
        {
            return transformClass(redefiningClass, bytes);
        }
        catch (Exception e)
        {
        }
        return null;
    }

    private byte[] transformClass(Class classToTransform, byte[] b) {
        ClassPool pool = ClassPool.getDefault();
        CtClass cl = null;
        try {
            cl = pool.makeClass(new java.io.ByteArrayInputStream(b));
                CtBehavior[] methods =cl.getDeclaredBehaviors();
                for (int i = 0; i < methods.length; i++) {
                    if (methods[i].isEmpty() == false)
                    {
                        System.out.println(methods[i].getLongName());
                        String methodName = methods[i].getLongName();
                        methods[i].insertBefore("System.out.println(\"ARGUMENTS : \"); for (int iter=0;iter<$args.length;iter++) {System.out.println($args[iter]);}");
                        methods[i].insertBefore("System.out.println(\"ENTERING FUNCTION :" + methodName + "\");");
                        methods[i].insertAfter("{System.out.println(\"Returning \" + $_);}");
                        methods[i].insertAfter("System.out.println(\"EXITING FUNCTION :" + methodName + "\");");
                        methods[i].insertAfter("System.out.println(\"\");");

                    }
                }
            b = cl.toBytecode();
        } catch (Exception e) {
        } finally {
            if (cl != null) {
                cl.detach();
            }
        }
        return b;
    }
}
