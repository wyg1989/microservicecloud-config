package com.csii.springboot06;

public class ClassLoaderTest2 {
    public static void main(String[] args) {
        try {
            //1、获取当前类的类加载器
            ClassLoader classLoader1 = Class.forName("java.lang.String").getClassLoader();
            System.out.println(classLoader1);
            //2、获取当前线程上线文的类加载器
            ClassLoader classLoader2 = Thread.currentThread().getContextClassLoader();
            System.out.println(classLoader2);
            //3、获取系统类加载器
            ClassLoader classLoader3 = ClassLoader.getSystemClassLoader();
            System.out.println(classLoader3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
