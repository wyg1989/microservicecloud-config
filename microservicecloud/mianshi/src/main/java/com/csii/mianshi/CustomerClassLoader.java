package com.csii.springboot06;

import java.io.FileNotFoundException;

/**
 * 用户自定义类加载器
 */
public class CustomerClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] result = getClassFromCustomerPath(name);
        if(result==null){
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            return defineClass(name,result,0,result.length);
        }
        return super.findClass(name);
    }

    private byte[] getClassFromCustomerPath(String name) {
        //
        return new byte[0];
    }

    public static void main(String[] args) {
        CustomerClassLoader CustomerClassLoader = new CustomerClassLoader();
       // CustomerClassLoader.findClass("")
        try {
            Class<?> one = Class.forName("one", true, CustomerClassLoader);
            Object obj =   one.newInstance();
        }catch (Exception e){
          e.printStackTrace();
        }


    }
}
