import ch.kerbtier.amarillo.Call;
import ch.kerbtier.amarillo.Route;
import ch.kerbtier.amarillo.Router;
import ch.kerbtier.amarillo.Verb;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zhiyin.router.BinaryValueAbstractAction;
import com.zhiyin.router.BinaryValueAction;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.implementation.FixedValue;

import java.lang.reflect.Modifier;

/**
 * Created by wangqinghui on 2016/12/5.
 */
public class GenRouterClassTest {

    public static void main(String[] args) {

        AnnotationDescription routeDe = AnnotationDescription.Builder.ofType(Route.class).define("pattern", "a/b").build();

        Class<?> binaryValueAction = new ByteBuddy()
                .subclass(BinaryValueAbstractAction.class)
                .defineMethod("match", Boolean.class, Modifier.PUBLIC)
                .intercept(FixedValue.value(Boolean.TRUE))
                .annotateMethod(routeDe)
                .make()
                .load(GenRouterClassTest.class.getClassLoader())
                .getLoaded();

        Router routing = new Router();
        routing.register(binaryValueAction);

        Object res = routing.getResult("a/bb", Verb.GET);
        System.out.println( res );
    }
}
