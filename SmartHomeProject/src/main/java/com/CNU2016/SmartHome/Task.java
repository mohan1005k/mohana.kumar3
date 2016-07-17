package com.CNU2016.SmartHome;
import java.util.TimerTask;

enum Limit{


        AC(1), WATERHEATER(100000),COOKINGOVEN(10000);

        int limit;
        Limit(int p) {
            limit = p;
        }
        int getLimit() {
            return limit;
        }


}
public class Task extends TimerTask {


    public void run() {
                for(int i=0;i<Appliance.size;i++)
                {
                       ApplianceDetails ob=Appliance.objects.get(i);
                       long startTime=ob.getTime();
                        String name=ob.getName();
                        float diff=(System.currentTimeMillis()-startTime);
                       diff=diff/1000;
                       float limit=0;
                       if(name.compareTo(AvailableAppliances.AC.getValue())==0)
                       {
                           limit=Limit.AC.getLimit();
                       }
                       else if(name.compareTo(AvailableAppliances.WATERHEATER.getValue())==0)
                       {
                           limit=Limit.WATERHEATER.getLimit();
                       }
                       else if(name.compareTo(AvailableAppliances.COOKINGOVEN.getValue())==0)
                       {
                           limit=Limit.COOKINGOVEN.getLimit();
                       }
                       if(diff>=limit)
                       {
                           //resetting timer and switching status
                           Appliance.objects.get(i).setTime(System.currentTimeMillis());
                           Appliance.objects.get(i).switchStatus();
                           System.out.println("Status changes for: "+Appliance.objects.get(i).getName()+" with id: "+Appliance.objects.get(i).getId());

                       }
                   
                }

    }
}
