import com.wustrive.aesrsa.util.AES;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by wangqinghui on 2016/8/19.
 */
@Slf4j
public class EncryptTest {

    public static void main(String[] args) {
        String en = AES.encryptToBase64("ssssss2222222222222222222sssssss", "1234567812345678");
        log.info(en);
    }
}
