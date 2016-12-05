package com.zhiyin.router.demo;

import ch.kerbtier.amarillo.Call;
import ch.kerbtier.amarillo.Route;
import ch.kerbtier.amarillo.Router;
import ch.kerbtier.amarillo.Verb;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.implementation.FixedValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by wangqinghui on 2016/12/5.
 */
public class Tt {

    public static void main(String[] args) {


        AnnotationDescription annotationDescription = AnnotationDescription.Builder.ofType(MapToProperty.class)
                .define("property", "<value>")
                .build();

        AnnotationDescription  routeDe = AnnotationDescription.Builder.ofType(Route.class).define("pattern","a/b").build();



        Class<?> example = new ByteBuddy()
                .subclass(Object.class)
                .defineField("foo", String.class,Modifier.PUBLIC)
                .annotateField(annotationDescription)
                .defineField("name2", String.class, Modifier.PUBLIC)
                .defineField("age", String.class, Modifier.PUBLIC)

                .defineMethod("run", String.class, Modifier.PUBLIC)

                .intercept(FixedValue.value("Hello World!"))
                .annotateMethod(routeDe)
                .make()
                .load(Tt.class.getClassLoader())
                .getLoaded();



        Field[] fields = example.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName() + " " + field.getAnnotations().length);

        }

        for (Method method : example.getDeclaredMethods()) {
            if(method.getAnnotations().length> 0){
                System.out.println(method.getName() + " " + method.getAnnotations()[0].toString());
            }
        }
//        example.ge

        System.out.println( example);


        Router routing = new Router();
        routing.register(example);


        Call call = routing.find("a/b", Verb.GET);
        System.out.println(call.getMethod());
        String l1 = (String)call.execute();
        System.out.println(l1);

    }
}
