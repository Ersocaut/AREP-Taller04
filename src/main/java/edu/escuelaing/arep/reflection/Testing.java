package edu.escuelaing.arep.reflection;

import edu.escuelaing.arep.annotation.*;

public class Testing {

    @MyTest
    public static void m1(){throw new RuntimeException("M1 failing");}

    public static void m2(){}

    public static void m3(){}

    @MyTest
    public static void m4(){System.out.println("M4 passing");}

    @MyTest
    public static void m5(){throw new RuntimeException("M5 failing");}
}
