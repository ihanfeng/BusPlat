import com.alibaba.fastjson.JSON;
import com.zhiyin.app.dbs.AppDbsProviderApplication;
import com.zhiyin.app.dbs.entity.AppInfo;
import com.zhiyin.app.dbs.service.IAppInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by hg on 2016/7/11.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {AppDbsProviderApplication.class})
@WebAppConfiguration
public class DialogInfoServiceImplTest {

    @Autowired
    IAppInfoService appInfoService;
    @Test
    public void t(){

        List<AppInfo> all = appInfoService.selectAll();
        System.out.println(JSON.toJSONString(all));

        Long id = 51735210967040L ;

        AppInfo info = appInfoService.selectById(id);
        System.out.println(JSON.toJSONString(info));
//        appInfoService.deleteByPrimaryKey(id );

        log.info("ss");
//        System.out.println(     appInfoService.selectById( id ) );
    }

}