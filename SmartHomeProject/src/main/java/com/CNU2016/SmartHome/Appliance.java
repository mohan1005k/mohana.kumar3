package com.CNU2016.SmartHome;

import java.util.*;


public class Appliance {
    
    static public int size=0;

    

    public static List<ApplianceDetails>objects=new ArrayList<ApplianceDetails>();
    // Instantiate Timer Object
    public Appliance()
    {
        Timer time = new Timer();
		long a=0,b=1000;
		time.schedule(new Task(),a,b);
    }
    
    public static void perform(int operation,String str,int id)
    {
             if(operation==Operation.ADD.getChoice())
            {
                  
                objects.add(new ApplianceDetails(str));
            }
            else if(operation==Operation.SWITCHON.getChoice())
            {
                    ApplianceDetails ob=(ApplianceDetails)objects.get(id);
                    if(ob.state==State.ON)
                    {
                        throw new IllegalArgumentException();
                    }
                    else
                    {
                        ob.switchStatus();;
                    }

            }
            else if(operation==Operation.SWITCHOFF.getChoice())
            {
               ApplianceDetails ob=(ApplianceDetails)objects.get(id);
               if(ob.state==State.OFF)
                    {
                        throw new IllegalArgumentException();
                    }
                    else
                    {
                        ob.switchStatus();
                    }
            }
            else
            {
                System.out.println("Not a valid choice");
            }
    }
   

    
}
