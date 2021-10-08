package com.servlet.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author 涂鏊飞tu_aofei@163.com
 * @description: 工厂模式工具类 设计模式：简单工厂模式
 * @create 2021-10-08 9:49
 */
public class FactoryUtils {
    public static <T> T createInstance(String className) {
        T instance = null;
        try {
            Constructor constructor = Class.forName(className).getDeclaredConstructor();
            constructor.setAccessible(true);
            instance = (T) constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
}
