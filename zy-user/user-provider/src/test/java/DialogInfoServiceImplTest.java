import com.alibaba.fastjson.JSON;
import com.zhiyin.user.dbs.UserDbsProviderApplication;
import com.zhiyin.user.dbs.entity.DeviceFixInfo;
import com.zhiyin.user.dbs.service.IDeviceInfoService;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * Created by hg on 2016/7/11.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {UserDbsProviderApplication.class})
@WebAppConfiguration
public class DialogInfoServiceImplTest {

    @Resource
    IDeviceInfoService deviceInfoService;


    @Test
    public void testInsertDialog() throws Exception {

        DeviceFixInfo fixInfo = new DeviceFixInfo();
        fixInfo.setSerialno("1");
        fixInfo.setImei("1");
        fixInfo.setIdfa("1");
        fixInfo.setModel("Iphone");
        fixInfo.setUuid("1");

        log.info(JSON.toJSONString(fixInfo));

        deviceInfoService.insertFix(fixInfo);

        DeviceFixInfo sel = deviceInfoService.selectDevice(fixInfo);

        log.info(JSON.toJSONString(sel));
    }


}