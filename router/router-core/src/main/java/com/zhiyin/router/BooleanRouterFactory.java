package com.zhiyin.router;

import ch.kerbtier.amarillo.Route;
import ch.kerbtier.amarillo.Router;
import ch.kerbtier.amarillo.Verb;
import com.google.common.collect.Maps;
import com.zhiyin.router.action.BooleanValueAbstractAction;
import com.zhiyin.router.action.BooleanValueAction;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.implementation.FixedValue;

import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * Created by wangqinghui on 2016/12/6.
 */
@Slf4j
public class BooleanRouterFactory {

    private Map<String,Class> RouterActionMap = Maps.newConcurrentMap();

    private Map<String,Router> RouterMap = Maps.newConcurrentMap();

//    public static Setter Setter() {
//        return new Setter();
//    }
//
//    public static class Setter {
//
//        private Boolean circuitBreakerEnabled = null;
//        private Integer circuitBreakerErrorThresholdPercentage;
//    }

    private static BooleanRouterFactory singleton;
    private BooleanRouterFactory (){}
    public static BooleanRouterFactory getSingleton() {
        if (singleton == null) {                         //Single Checked
            synchronized (BooleanRouterFactory.class) {
                if (singleton == null) {                 //Double Checked
                    singleton = new BooleanRouterFactory();
                }
            }
        }
        return singleton ;
    }

    public static void create(String type,String config){
        getSingleton().setRoute(type,config);
    }

    public void setRoute(String type,String config){
        resetAction(type,config);
    }

    public static boolean match(String type,String matcher){
        if( ! singleton.RouterMap.containsKey(type) ){
            log.error("router type {} not init.",type);
            return false;
        }

       return (boolean) singleton.RouterMap.get(type).getResult(matcher,Verb.GET);
    }

    public void resetAction(String type,String config){

//        Class<? extends Router> claz = RouterMap.get(type).getClass();

        AnnotationDescription routeDe = AnnotationDescription.Builder.ofType(Route.class).define("pattern", config).build();

        Class<?> binaryValueAction = new ByteBuddy()
                .subclass(BooleanValueAbstractAction.class)
                .defineMethod("match", Boolean.class, Modifier.PUBLIC)
                .intercept(FixedValue.value(Boolean.TRUE))
                .annotateMethod(routeDe)
                .make()
                .load(this.getClass().getClassLoader())
                .getLoaded();


        Router routing = new Router();
        routing.register(binaryValueAction);

        RouterActionMap.put(type,binaryValueAction);

        RouterMap.put(type,routing);
    }

}
