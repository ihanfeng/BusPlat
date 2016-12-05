//import ch.kerbtier.amarillo.Call;
//import ch.kerbtier.amarillo.Route;
//import ch.kerbtier.amarillo.Router;
//import ch.kerbtier.amarillo.Verb;
//import ch.kerbtier.amarillo.tests.actions.RuleAc;
//import com.zhiyin.router.BinaryValAction;
//import net.bytebuddy.ByteBuddy;
//import net.bytebuddy.description.annotation.AnnotationDescription;
//import net.bytebuddy.dynamic.DynamicType;
//import net.bytebuddy.implementation.FixedValue;
//import net.bytebuddy.implementation.SuperMethodCall;
//import net.bytebuddy.matcher.ElementMatchers;
//import org.testng.annotations.Test;
//
///**
// * Created by wangqinghui on 2016/12/5.
// */
//public class GenCode {
//
//    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
//        Class<?> dynamicType = new ByteBuddy()
//                .subclass(Object.class)
//                .method(ElementMatchers.named("toString"))
//                .intercept(FixedValue.value("Hello World!"))
//                .make()
//                .load(GenCode.class.getClassLoader())
//                .getLoaded();
//
//        System.out.println( dynamicType.newInstance().toString() );
//
//        AnnotationDescription  routeDe = AnnotationDescription.Builder.ofType(Route.class).build();
//
//        DynamicType.Unloaded<BinaryValAction> dynamicType2 = new ByteBuddy()
//                .subclass(BinaryValAction.class)
//                .method(ElementMatchers.named("match"))
//                .intercept(SuperMethodCall.INSTANCE)
//                .annotateMethod(routeDe)
//                .make();
//
//
////        assertThat(dynamicType.newInstance().toString(), is("Hello World!"));
//
//        Router routing = new Router();
//
//        routing.register(RuleAc.class);
//
//
//        Call call = routing.find("a/b/3", Verb.GET);
//        System.out.println(call.getMethod());
////    assertEquals(call.getMethod(), ClassPattern.class.getMethod("rootGet"));
//        Integer l1 = (Integer)call.execute();
//        System.out.println(l1);
//    }
//}
