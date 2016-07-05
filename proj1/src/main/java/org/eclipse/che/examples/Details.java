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
            Scanner in = new Scanner(System.in);
            name=str;
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
      public void print()
      {
          System.out.println(name+" "+state+" "+id+" "+startTime);
      }
}
