package com.investigatingsoftware.app;

import java.util.*; // import the ArrayList class

public class App 
{
    public static void main( String[] args )
    {

        List<Object> list = new ArrayList<Object>();
        list.add(1000);     //works fine
        list.add("lokesh"); //compile time error; 
        System.out.println(list);
    }
}
