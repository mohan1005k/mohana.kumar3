package com.CNU2016.javarules;

import java.lang.Object.*;
/**
 * Created by mohanakumar on 20/07/16.
 */
public class Rule {

    private int number1;
    private int number2;
    private String rule;

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }


    public String getRule() {
        return rule;
    }

    public void setRules(String rule) {
        this.rule = rule;
    }

    public boolean compareTwoNumbers()
    {
        if(number1!=number2 || number2!=number1)
            return false;
        return true;
    }

    public int divideTwoNumbers()
    {
        if(number2==0)
            new IllegalArgumentException();
        else
            return number1/number2;
        return 0;

    }

    public static void main(String args[]) throws Exception
    {

        /**
         * squid : S1700
         * It's confusing to have a class member with the same name (case differences aside) as its enclosing class.
         */
        Rule rule=new Rule();
        rule.setRules("RuleViolation1");
        System.out.println(rule.getRule());


        /**
         *  squid:S1764
         *  Using the same value on either side of a binary operator is almost always a mistake.
         */
        Rule rule2=new Rule();
        rule2.setNumber1(1);
        rule2.setNumber2(2);
        System.out.println("Equality of number1 and number2 :"+rule2.compareTwoNumbers());

        /**
         * squid:S1848
         * There is no good reason to create a new object to not do anything with it.
         */

        try {
            Rule rule3 = new Rule();
            rule3.setNumber1(2);
            rule3.setNumber2(0);
            System.out.println("Result of division : " + rule3.divideTwoNumbers());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }
}
