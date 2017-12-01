package com.lushun.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.lushun.*;
import com.lushun.reflection.ReflectionUtils;
import org.junit.Test;

/**
 * 测试类,用JUnit4 进行测试
 *
 * @author syh
 */

public class ReflectionUtilsTest {

    /**
     * 测试获父类的各个方法对象
     */

    @Test
    public void testGetDeclaredMethod() {

        Object obj = new Son();

        Method publicMethod = ReflectionUtils.getDeclaredMethod(obj, "publicMethod");
        System.out.println(publicMethod.getName());

        Method defaultMethod = ReflectionUtils.getDeclaredMethod(obj, "defaultMethod");
        System.out.println(defaultMethod.getName());

        Method protectedMethod = ReflectionUtils.getDeclaredMethod(obj, "protectedMethod");
        System.out.println(protectedMethod.getName());

        Method privateMethod = ReflectionUtils.getDeclaredMethod(obj, "privateMethod");
        System.out.println(privateMethod.getName());
    }

    /**
     * 测试调用     * @throws Exception
     */

    @Test
    public void testInvokeMethod() throws Exception {
        Object obj = new Son();


        ReflectionUtils.invokeMethod(obj, "publicMethod", null, null);

        ReflectionUtils.invokeMethod(obj, "defaultMethod", null, null);
        ReflectionUtils.invokeMethod(obj, "protectedMethod", null, null);

        ReflectionUtils.invokeMethod(obj, "privateMethod", null, null);
    }

    /**
     * 测试获父类的各个属性名
     */

    @Test
    public void testGetDeclaredField() {

        Object obj = new Son();

        Field publicField = ReflectionUtils.getDeclaredField(obj, "publicField");
        System.out.println(publicField.getName());

        Field defaultField = ReflectionUtils.getDeclaredField(obj, "defaultField");
        System.out.println(defaultField.getName());

        Field protectedField = ReflectionUtils.getDeclaredField(obj, "protectedField");
        System.out.println(protectedField.getName());

        Field privateField = ReflectionUtils.getDeclaredField(obj, "privateField");
        System.out.println(privateField.getName());

    }

    @Test
    public void testSetFieldValue() {

        Object obj = new Son();

        System.out.println("原来的各个属性的值: ");
        System.out.println("publicField = " + ReflectionUtils.getFieldValue(obj, "publicField"));
        System.out.println("defaultField = " + ReflectionUtils.getFieldValue(obj, "defaultField"));
        System.out.println("protectedField = " + ReflectionUtils.getFieldValue(obj, "protectedField"));
        System.out.println("privateField = " + ReflectionUtils.getFieldValue(obj, "privateField"));

        ReflectionUtils.setFieldValue(obj, "publicField", "a");
        ReflectionUtils.setFieldValue(obj, "defaultField", "b");
        ReflectionUtils.setFieldValue(obj, "protectedField", "c");
        ReflectionUtils.setFieldValue(obj, "privateField", "d");

        System.out.println("***********************************************************");

        System.out.println("将属性值改变后的各个属性值: ");
        System.out.println("publicField = " + ReflectionUtils.getFieldValue(obj, "publicField"));
        System.out.println("defaultField = " + ReflectionUtils.getFieldValue(obj, "defaultField"));
        System.out.println("protectedField = " + ReflectionUtils.getFieldValue(obj, "protectedField"));
        System.out.println("privateField = " + ReflectionUtils.getFieldValue(obj, "privateField"));

    }

    @Test
    public void testGetFieldValue() {

        Object obj = new Son();

        System.out.println("publicField = " + ReflectionUtils.getFieldValue(obj, "publicField"));
        System.out.println("defaultField = " + ReflectionUtils.getFieldValue(obj, "defaultField"));
        System.out.println("protectedField = " + ReflectionUtils.getFieldValue(obj, "protectedField"));
        System.out.println("privateField = " + ReflectionUtils.getFieldValue(obj, "privateField"));

    }

}  