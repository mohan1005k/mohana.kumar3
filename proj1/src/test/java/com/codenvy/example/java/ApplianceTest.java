package com.codenvy.example.java;
import org.eclipse.che.examples.*;

import org.junit.*;
import static org.junit.Assert.assertEquals;
import static java.util.concurrent.TimeUnit.*;
import static org.junit.Assert.*;
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
public void test1()
{
    try
    {
     Details ob=new Details("fan");
     assertFalse(true);
     
     //assertEquals("fan",ob.getName());
    }
    catch(IllegalArgumentException e)
    {
        assertTrue(true);
    }
 

}
@Test 
public void test11()
{
    try
    {
     Details ob=new Details("");
     assertFalse(true);
     
     //assertEquals("fan",ob.getName());
    }
    catch(NullPointerException e)
    {
        assertTrue(true);
    }
 

}


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
  ob.switchStatus();
  assertEquals(State.ON,ob.getStatus());
}

@Test
public void test5()
{
    Appliance.size=0;
    Appliance.perform(1,"AC",0);
    Appliance.perform(1,"WH",0);
    Appliance.perform(1,"CO",0);
    assertEquals(3,Appliance.size);    
}
@Test 
public void test6()
{
    
    Appliance a=new Appliance();
    Appliance.size=0;
    Appliance.perform(1,"AC",0);
   // Appliance.perform(1,"WH",0);
    //Appliance.perform(1,"CO",0);
    long startTime=System.currentTimeMillis();
   
   while(true)
   {
         float diff=(System.currentTimeMillis()-startTime);
                       diff=diff/1000;
                       float limit=1.1f;
                       
                       if(diff>=limit)
                    {
                        break;
                    }
                       
   }
                      Details ob=Appliance.objects.get(0);
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

@Test 
public void test7()
{
   try
   {
   
   Appliance a=new Appliance();
    //Appliance.size=0;
    Appliance.perform(1,"WH",0);
    Appliance.perform(2,"",Appliance.size-1);
    assertFalse(true);
    }
    catch(IllegalArgumentException e)
    {
        assertTrue(true);
    }


}

@Test 
public void test8()
{
   try
   {
   
   Appliance a=new Appliance();
    //Appliance.size=0;
    Appliance.perform(1,"CO",0);
    Appliance.perform(3,"",Appliance.size-1);
    Appliance.perform(3,"",Appliance.size-1);
    assertFalse(true);
    }
    catch(IllegalArgumentException e)
    {
        assertTrue(true);
    }


}

}
