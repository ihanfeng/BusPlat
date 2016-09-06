import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Protocol;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by hg on 2016/8/29.
 */
@Slf4j
public class FactoryTest {

    public static void main(String[] args) {
       Protocol protocol  =
                ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();


        Protocol protocolExten = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("registry");


//        Protocol protocolExten = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("dubbo");

        log.info( protocolExten.getDefaultPort() +"");
        log.info("name: {}.",protocol.getClass().getName());
        log.info(protocol.getDefaultPort() + "");
    }
}
