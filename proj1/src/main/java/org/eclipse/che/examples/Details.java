package org.eclipse.che.examples;
import java.io.*;
import java.util.*;

import static java.util.concurrent.TimeUnit.*;

import java.util.TimerTask;
import java.util.Date;

 public class Details
{   
        private String name;
        public State state; 
        private int id;
        private long startTime;
        
        public Details(String str)
        {
            //not a valid machine
            if(!(str.compareTo("AC")==0 || str.compareTo("WH")==0 || str.compareTo("CO")==0))
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
      /*public void print()
      {
          System.out.println(name+" "+state+" "+id+" "+startTime);
      }*/
}
