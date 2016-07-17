package com.CNU2016.SmartHome;

public class ApplianceDetails
{   
        private String name;
        public State state; 
        private int id;
        private long startTime;
        
        public ApplianceDetails(String str)
        {
            //not a valid machine
            if(!(str.compareTo(AvailableAppliances.AC.getValue())==0 || str.compareTo(AvailableAppliances.WATERHEATER.getValue())==0 || str.compareTo(AvailableAppliances.COOKINGOVEN.getValue())==0))
            {
                if(str.compareTo("")==0)
                {
                    throw null;
                }
                else
                {
                    throw new IllegalArgumentException();
                }

            }
            name=str;
            state=State.ON;
            id=Appliance.size;
            startTime=System.currentTimeMillis();
            Appliance.size++;
        }
      public int getId()
        {
            return id;
        }
      public long getTime()
      {
          return startTime;
      }
      public void setTime(long time)
      {
          startTime=time;
      }
      public State getStatus()
      {
          return state;
      }
      public static int  getSize()
      {
          return Appliance.size;
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

}
