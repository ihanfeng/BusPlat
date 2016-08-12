import com.alibaba.fastjson.JSON;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by hg on 2016/8/12.
 */
@Slf4j
public class EmojiTest {

    @Test
    public void test(){

        Emoji emoji = EmojiManager.getForAlias("smile");
        System.out.println("HEY: " + emoji.getUnicode());
        log.info(JSON.toJSONString(emoji));
    }
}
