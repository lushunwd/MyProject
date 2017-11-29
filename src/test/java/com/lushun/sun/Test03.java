package com.lushun.sun;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by MyTest on 2017/11/18.
 */

public class Test03 {
    @Test
    public void test01(){
        System.out.println("test01---------------Test03");
        Assert.assertTrue(true);
    }
    @Test
    public void test02(){
        System.out.println("test02---------------Test03");
        Assert.assertTrue(false);
    }
    @Test
    public void test03(){
        System.out.println("test03---------------Test03");
        Assert.assertTrue(true);
    }
}
