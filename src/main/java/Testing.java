public class Testing {

    @Test
    public static void m1(){throw new RuntimeException("M1 failing");}

    public static void m2(){}

    public static void m3(){}

    @Test
    public static void m4(){System.out.println("M4 passing");}

    @Test
    public static void m5(){throw new RuntimeException("M5 failing");}
}
