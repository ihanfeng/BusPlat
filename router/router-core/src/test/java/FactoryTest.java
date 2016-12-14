import com.zhiyin.router.BooleanRouterFactory;
import org.junit.Test;

/**
 * Created by wangqinghui on 2016/12/6.
 */
public class FactoryTest {

    @Test
    public void booleanTest() {
            BooleanRouterFactory.create("demo1","/a/b/.*");
            BooleanRouterFactory.match("demo1","/a/b/c");

    }
}
