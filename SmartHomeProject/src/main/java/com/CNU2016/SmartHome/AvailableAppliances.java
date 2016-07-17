package com.CNU2016.SmartHome;

/**
 * Created by mohanakumar on 17/07/16.
 */
public enum AvailableAppliances {
    AC("AC"), WATERHEATER("WH"),COOKINGOVEN("CO");

    String value;
    AvailableAppliances(String p) {
        value = p;
    }
    public String getValue() {
        return value;
    }
}
