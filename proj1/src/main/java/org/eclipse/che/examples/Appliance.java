package org.eclipse.che.examples;

import java.io.*;
import java.util.*;
import static java.util.concurrent.TimeUnit.*;
import java.util.TimerTask;
import java.util.Date;



/*class RunnableImpl implements Runnable {
   
   private Thread t;
   
   private String threadName;
   
   RunnableImpl(){
 //      objects=obs;
      // System.out.println("Creating " +  threadName );
   }
   public void run() {
   //   System.out.println("Running " +  threadName );
      try {
    
                       
                for(int i=0;i<Appliance.size;i++)
                {
                       Details ob=Appliance.objects.get(i);
                       long startTime=ob.getTime();
                        String name=ob.getName();
                        float diff=(System.currentTimeMillis()-startTime);
                       diff=diff/1000;
                       float limit=0;
                       if(name.compareTo("AC")==0)
                       {
                           limit=2;
                       }
                       else if(name.compareTo("WH")==0)
                       {
                           limit=8;
                       }
                       else if(name.compareTo("CO")==0)
                       {
                           limit=5;
                       }
                       if(diff>=limit)
                       {
                           Appliance.objects.get(i).setTime(System.currentTimeMillis());
                           Appliance.objects.get(i).switchStatus();
                           System.out.println("status changes for:\n");
                           ob.print();
                       }
                   
                }
          
     } catch (Exception e) {
         System.out.println("Thread " +  threadName + " interrupted.");
     }
     //System.out.println("Thread " +  threadName + " exiting.");
   }
   
   public void start ()
   {
      System.out.println("Starting " +  threadName );
      if (t == null)
      {
         t = new Thread (this, threadName);
         t.start ();
      }
   }

}*/
 class Details
{   
    enum State
    {
        ON,OFF;
    }
        private String name;
        public State state; 
        private int id;
        private long startTime;
        
        public Details()
        {
            Scanner in = new Scanner(System.in);
            name=in.nextLine();
            state=State.ON;
            id=Appliance.size;
            startTime=System.currentTimeMillis();
            Appliance.size++;
      }
      public long getTime()
      {
          return startTime;
      }
      public void setTime(long time)
      {
          startTime=time;
      }
      public void switchStatus()
      {
          if(state==State.ON)
          {
              state=State.OFF;
          }
          else
          {
              state=State.ON;
          }
      }
      public String getName()
      {
          return name;
      }
      public void print()
      {
          System.out.println(name+" "+state+" "+id+" "+startTime);
      }
}
/*public class ScheduledTask extends TimerTask {

	Date now; // to display current time

	// Add your task here
	public void run() {
		now = new Date(); // initialize date
		System.out.println("Time is :" + now); // Display current time
	}
}
*/
class Task extends TimerTask {


    public void run() {
          //              System.out.println("its here\n");
                for(int i=0;i<Appliance.size;i++)
                {
                       Details ob=Appliance.objects.get(i);
                       long startTime=ob.getTime();
                        String name=ob.getName();
                        float diff=(System.currentTimeMillis()-startTime);
                       diff=diff/1000;
                       float limit=0;
                       if(name.compareTo("AC")==0)
                       {
                           limit=2;
                       }
                       else if(name.compareTo("WH")==0)
                       {
                           limit=8;
                       }
                       else if(name.compareTo("CO")==0)
                       {
                           limit=5;
                       }
                       if(diff>=limit)
                       {
                           Appliance.objects.get(i).setTime(System.currentTimeMillis());
                           Appliance.objects.get(i).switchStatus();
                           System.out.println("Status changes for:\n");
                           ob.print();
                       }
                   
                }

    }
}
public class Appliance {
    
    static public int size=0;

    public static ArrayList<Details>objects=new ArrayList<Details>();
    

    public static void main(String [] args)
    {
   
   // Thread t1 = new Thread(new RunnableImpl());
    
        Timer time = new Timer(); // Instantiate Timer Object
		long a=0,b=5000;
		time.schedule(new Task(),a,b);
	   
        Scanner in = new Scanner(System.in);
        int num;
        while(true)
        {
            System.out.println("1.Add device\n2.Switch ON a device\n3.Switch OFF a device\n4.Display status of machines");
            num=in.nextInt();
            if(num==1)
            {
                
                objects.add(new Details());
             /*   t1 = new Thread(new RunnableImpl());
                t1.start();
                t1.run();*/
            }
            else if(num==2)
            {
                    num=in.nextInt();
                    Details ob=(Details)objects.get(num);
                    ob.state=Details.State.ON;
                    
            }
            else if(num==3)
            {
                num=in.nextInt();
                Details ob=(Details)objects.get(num);
                ob.state=Details.State.OFF;
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
            }
        }
    }
}
