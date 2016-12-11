package com.zhiyin.jagent;

import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;

/**
 * Created by wangqinghui on 2016/12/8.
 */
public class ClazzUtil {

//    public static boolean methodMatch


    public static boolean methodForbidModify(CtBehavior ctMethod) {
        return !methodCouldModify(ctMethod);
    }

    public static boolean methodCouldModify(CtBehavior ctMethod) {
        if ( ctMethod.isEmpty() == true) {
            return false;
        }
        if( AgentConfig.NotModifyMethodNames.contains(ctMethod.getName())){
            return false;
        }
        return true;
    }


    public static boolean methodCouldModify(CtMethod ctMethod) {

        if (Modifier.isNative(ctMethod.getModifiers())) {
            return false;
        }

        if (AgentConfig.NotModifyMethodNames.contains(ctMethod.getName())) {
            return false;
        }

        return true;
    }




    public static boolean classCouldModify(CtClass ctClass) {
        if (ctClass.isInterface()) {
            return false;
        }

        return classCouldModify(ctClass.getName());
    }

    public static boolean classCouldModify(String className) {

        if (className.startsWith("com/zhiyin") || className.startsWith("com.zhiyin")) {
            return true;
        }

        return false;
    }

    public static boolean classForbidModify(String className) {
        return !classCouldModify(className);
    }
    public static boolean classForbidModify(CtClass ctClass) {
        return  !classCouldModify(ctClass);
    }

    public static void main(String[] args) {
        String clazz= "java.lang.Long";
        System.out.println(classCouldModify( clazz ));
        System.out.println(classForbidModify( clazz ));

    }
}