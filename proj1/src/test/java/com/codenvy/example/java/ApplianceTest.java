package com.codenvy.example.java;
import org.eclipse.che.examples.*;

import org.junit.*;
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.asserEquals;


public class ApplianceTest {

@Test
public void test1()
{
    Details ob=new Details("AC");
    assertEquals(System.currentTimeMillis(),ob.getTime());
  assertEquals("AC",ob.getName());
  assertEquals(State.ON,ob.getStatus());
  ob.switchStatus();
  assertEquals(State.OFF,ob.getStatus());
  
  
    
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
 
}


}
