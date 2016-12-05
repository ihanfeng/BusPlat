package ch.kerbtier.amarillo;

import com.zhiyin.router.BaseAction;
import com.zhiyin.router.BinaryValueAction;
import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClassWrapper {

  private String pattern;
  private Verb httpMethod = Verb.GET;
  private List<MethodWrapper> callMatchers = new ArrayList<>();

  public Object defaultVal = null;
  
  public ClassWrapper(Class<?> subject) {

//      Class c[] = subject.getInterfaces();
//      if(Arrays.asList(c).contains(BinaryValueAction.class))
//      {
//          System.out.println("sssss");
//      }
//
//    if(subject.isAssignableFrom( BinaryValueAction.class )){
//      try {
//        defaultVal =  ((BaseAction)subject.newInstance()).defaultAction();
//      } catch (InstantiationException e) {
//        e.printStackTrace();
//      } catch (IllegalAccessException e) {
//        e.printStackTrace();
//      }
//    }

      List<Class<?>> ints = ClassUtils.getAllInterfaces(subject);
      System.out.println(ints);
      for (Class<?> anInt : ints) {
          if(anInt.getName().equals(BaseAction.class.getName())){
                    try {
        defaultVal =  ((BaseAction)subject.newInstance()).defaultAction();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
          }
      }


    Route annotation = subject.getAnnotation(Route.class);
    if(annotation != null) {
      if(!annotation.pattern().equals(Route.NULL)) {
        pattern = annotation.pattern();
        if(annotation.verb() != Verb.UNDEFINED) {
          httpMethod = annotation.verb();
        }
      }
    }

    for(Method method: subject.getMethods()) {
      Route action = method.getAnnotation(Route.class);
      if(action != null) {
        MethodWrapper cm = new MethodWrapper(pattern, httpMethod, subject, method, action);
        callMatchers.add(cm);
      }
    }
    
  }
  
  public List<Call> getCall(String path, Verb method) {
    List<Call> calls = null;
    for(MethodWrapper cm: callMatchers) {
      Call call = cm.getCall(path, method);
      if(call != null) {
        if(calls == null) {
          calls = new ArrayList<>();
        }
        calls.add(call);
      }
    }

    if(calls != null) {
      return calls;
    }
    return Collections.emptyList();
  }


//  public Object callDefault(){
//
//  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for(MethodWrapper cm: callMatchers) {
      sb.append(cm.getPattern() + ":" + cm.getVerb() + "\n");
    }
    return sb.toString();
  }
}
