package com.codenvy.example.java;
import org.eclipse.che.examples.*;

import org.junit.*;
import static org.junit.Assert.assertEquals;
import static java.util.concurrent.TimeUnit.*;
import java.util.TimerTask;
import java.util.Date;

public class ApplianceTest {

/*@Test
public void test1()
{
    Details ob=new Details("AC");
    assertEquals(System.currentTimeMillis(),ob.getTime());
  assertEquals("AC",ob.getName());
  assertEquals(State.ON,ob.getStatus());
  ob.switchStatus();
  assertEquals(State.OFF,ob.getStatus());
  
  
  
  
    
}*/

@Test
public void test2()
{
        Details ob=new Details("AC");
          assertEquals("AC",ob.getName());
    
}


@Test
public void test3()
{
        Details ob=new Details("AC");
         assertEquals(State.ON,ob.getStatus());
    
}

@Test 
public void test4()
{
        Details ob=new Details("AC");
 
  ob.switchStatus();
  assertEquals(State.OFF,ob.getStatus());
 
}

@Test
public void test6()
{
   // Appliance a=new Appliance();
    Appliance.size=0;
    Appliance.perform(1,"AC",0);
    Appliance.perform(1,"WH",0);
    Appliance.perform(1,"CO",0);
    assertEquals(3,Appliance.size);    
}
@Test 
public void test5()
{
    
    Appliance a=new Appliance();
    Appliance.size=0;
    Appliance.perform(1,"AC",0);
    Appliance.perform(1,"WH",0);
    Appliance.perform(1,"CO",0);
    long startTime=System.currentTimeMillis();
   
   while(true)
   {
         float diff=(System.currentTimeMillis()-startTime);
                       diff=diff/1000;
                       float limit=2;
                       
                       if(diff>=limit)
                    {
                        break;
                    }
                       
   }
                      Details ob=Appliance.objects.get(1);
                       assertEquals(State.OFF,ob.getStatus());
  /*
         for(int i=0;i<Appliance.size;i++)
                {
                      Details ob=Appliance.objects.get(i);
                       assertEquals(State.ON,ob.getStatus());
                       break;
                }
*/
}




}
