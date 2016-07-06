package org.eclipse.che.examples;

import java.io.*;
import java.util.*;
import static java.util.concurrent.TimeUnit.*;
import java.util.TimerTask;
import java.util.Date;

public class Appliance {
    
    static public int size=0;

    

    public static ArrayList<Details>objects=new ArrayList<Details>();
    
    public Appliance()
    {
        Timer time = new Timer(); // Instantiate Timer Object
		long a=0,b=1000;
		time.schedule(new Task(),a,b);
    }
    
    public static void perform(int num,String str,int num2)
    {
            Scanner in = new Scanner(System.in);
    //        System.out.println("1.Add device\n2.Switch ON a device\n3.Switch OFF a device\n4.Display status of machines");
      //      num=in.nextInt();
            if(num==1)
            {
                  
                objects.add(new Details(str));
             /*   t1 = new Thread(new RunnableImpl());
                t1.start();
                t1.run();*/
            }
            /*else if(num==2)
            {
        //            num=in.nextInt();
                    Details ob=(Details)objects.get(num2);
                    ob.state=State.ON;
                    ob.print();
            }
            else if(num==3)
            {
               // num=in.nextInt();
                Details ob=(Details)objects.get(num2);
                ob.state=State.OFF;
                ob.print();
            }
            else if(num==4)
            {
                for(int i=0;i<Appliance.size;i++)
                {
                    Details ob=objects.get(i);
                    ob.print();
                }
            }else
            {
                System.out.println("Not a valid choice");
            }*/
    }
   
    public static void main(String [] args)
    {
   
   // Thread t1 = new Thread(new RunnableImpl());
    
     /*   Timer time = new Timer(); // Instantiate Timer Object
		long a=0,b=5000;
		time.schedule(new Task(),a,b);
	   
        Scanner in = new Scanner(System.in);
        int num;
        Appliance.perform(1,"AC",0);
        Appliance.perform(1,"WH",0);
        Appliance.perform(1,"CO",0);
        Appliance.perform(3,"",0);
        Appliance.perform(3,"",1);
       */
       }
    
}
