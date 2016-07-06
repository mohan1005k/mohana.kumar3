package org.eclipse.che.examples;
import static java.util.concurrent.TimeUnit.*;
import java.util.TimerTask;
import java.util.Date;

public class Task extends TimerTask {


    public void run() {
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
                           limit=1;
                       }
                       else if(name.compareTo("WH")==0)
                       {
                           limit=5;
                       }
                       else if(name.compareTo("CO")==0)
                       {
                           limit=6;
                       }
                       if(diff>=limit)
                       {
                           //resetting timer and switching status
                           Appliance.objects.get(i).setTime(System.currentTimeMillis());
                           Appliance.objects.get(i).switchStatus();
                           System.out.println("Status changes for: "+Appliance.objects.get(i).getName()+" with id: "+Appliance.objects.get(i).getId());
                           //ob.print();
                       }
                   
                }

    }
}
