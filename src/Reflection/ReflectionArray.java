package Reflection;

import java.lang.reflect.Array;

public class ReflectionArray {
    public static void main(String[] args) {
        int[] intArray = new int[0];

        Class<? extends int[]> clazz = intArray.getClass();
        System.out.println(clazz.isArray());
        System.out.println(clazz.getTypeName());

        Class<?> componentType = clazz.getComponentType();
        System.out.println(componentType);
        System.out.println(componentType.getName()+"\r");

        float [] fl = (float[])Array.newInstance(float.class, 5);
        System.out.println(fl.length+"   ");
        System.out.print(fl.getClass().getTypeName());

    }
}