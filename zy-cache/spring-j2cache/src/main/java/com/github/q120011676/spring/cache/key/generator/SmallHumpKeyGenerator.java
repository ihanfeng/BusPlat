package com.github.q120011676.spring.cache.key.generator;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by say on 3/21/16.
 */
@Component(value = "smallHump")
public class SmallHumpKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        String name = method.getName();
        for (int i = 0; i < name.length(); i++) {
            if (Character.isUpperCase(name.charAt(i))) {
                name = name.substring(i);
                break;
            }
        }
        return name;
    }
}