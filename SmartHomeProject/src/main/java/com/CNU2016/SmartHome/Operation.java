package com.CNU2016.SmartHome;

/**
 * Created by mohanakumar on 17/07/16.
 */
public enum Operation {


    ADD(1), SWITCHON(2),SWITCHOFF(3);

    int choice;
    Operation(int p) {
        choice = p;
    }
    int getChoice() {
        return choice;
    }
}
