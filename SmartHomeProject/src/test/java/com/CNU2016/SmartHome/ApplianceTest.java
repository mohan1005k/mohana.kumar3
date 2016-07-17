package com.CNU2016.SmartHome;

import com.CNU2016.SmartHome.Appliance;
import com.CNU2016.SmartHome.ApplianceDetails;
import com.CNU2016.SmartHome.AvailableAppliances;
import com.CNU2016.SmartHome.State;
//import org.eclipse.che.examples.*;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

public class ApplianceTest {


    @Test
    public void test1() {
        try {
            ApplianceDetails ob = new ApplianceDetails("FAN");
            assertFalse(true);

            //assertEquals("fan",ob.getName());
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }


    }

    @Test
    public void test11() {
        try {
            ApplianceDetails ob = new ApplianceDetails("");
            assertFalse(true);

            //assertEquals("fan",ob.getName());
        } catch (NullPointerException e) {
            assertTrue(true);
        }


    }


    @Test
    public void test2() {
        ApplianceDetails ob = new ApplianceDetails(AvailableAppliances.AC.getValue());
        assertEquals("AC", ob.getName());

    }


    @Test
    public void test3() {
        ApplianceDetails ob = new ApplianceDetails(AvailableAppliances.AC.getValue());
        assertEquals(State.ON, ob.getStatus());

    }

    @Test
    public void test4() {
        ApplianceDetails ob = new ApplianceDetails(AvailableAppliances.AC.getValue());

        ob.switchStatus();
        assertEquals(State.OFF, ob.getStatus());
        ob.switchStatus();
        assertEquals(State.ON, ob.getStatus());
    }

    @Test
    public void test5() {
        Appliance.size = 0;
        Appliance.perform(1, AvailableAppliances.AC.getValue(), 0);
        Appliance.perform(1, AvailableAppliances.WATERHEATER.getValue(), 0);
        Appliance.perform(1, AvailableAppliances.COOKINGOVEN.getValue(), 0);
        assertEquals(3, Appliance.size);
    }

    @Test
    public void test6() {

        Appliance a = new Appliance();
        Appliance.size = 0;
        Appliance.perform(1, AvailableAppliances.AC.getValue(), 0);
        long startTime = System.currentTimeMillis();

        while (true) {
            float diff = (System.currentTimeMillis() - startTime);
            diff = diff / 1000;
            float limit = 1.1f;

            if (diff >= limit) {
                break;
            }

        }
        ApplianceDetails ob = Appliance.objects.get(0);
        assertEquals(State.OFF, ob.getStatus());

    }

    @Test
    public void test7() {
        try {

            Appliance a = new Appliance();
            Appliance.perform(1, AvailableAppliances.WATERHEATER.getValue(), 0);
            Appliance.perform(2, "", Appliance.size - 1);
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }


    }

    @Test
    public void test8() {
        try {

            Appliance a = new Appliance();
            Appliance.perform(1, AvailableAppliances.COOKINGOVEN.getValue(), 0);
            Appliance.perform(3, "", Appliance.size - 1);
            Appliance.perform(3, "", Appliance.size - 1);
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }


    }

}
