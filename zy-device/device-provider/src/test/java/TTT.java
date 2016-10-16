import com.alibaba.fastjson.JSON;
import com.zhiyin.device.dbs.entity.DeviceFixInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by wangqinghui on 2016/10/10.
 */
@Slf4j
public class TTT {
    public static void main(String[] args) {
        DeviceFixInfo fixInfo = new DeviceFixInfo();
        fixInfo.setSerialno("1");
        fixInfo.setImei("1");
        fixInfo.setIdfa("1");
        fixInfo.setModel("Iphone");
        fixInfo.setUuid("1");

        log.info(JSON.toJSONString(fixInfo));
    }
}
