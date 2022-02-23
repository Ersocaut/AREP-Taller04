package edu.escuelaing.arep.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class ReflectionExample {
    public static void main(String[] args) {
        printMembers("Hola".getClass().getDeclaredMethods(), "Methods");
    }

    private static void printMembers(Member[] mbrs, String s) {
        System.out.format("%s:%n", s);
        for (Member mbr : mbrs) {
            if (mbr instanceof Field)
                System.out.format(" %s%n", ((Field) mbr).toGenericString());
            else if (mbr instanceof Constructor)
                System.out.format(" %s%n", ((Constructor) mbr).toGenericString());
            else if (mbr instanceof Method)
                System.out.format(" %s%n", ((Method) mbr).toGenericString());
        }
        if (mbrs.length == 0)
            System.out.format(" -- No %s --%n", s);
        System.out.format("%n");
    }
}