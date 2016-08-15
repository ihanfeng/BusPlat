import com.alibaba.fastjson.JSON;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.hg.msg.entity.MsgUserNotify;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by hg on 2016/4/19.
 */
@Slf4j
public class Se {
    public static void main(String[] args) throws Exception {

        Kryo kryo = new Kryo();
// ...
        Output output = new Output(new FileOutputStream("file.bin"));
        MsgUserNotify someObject = new MsgUserNotify();
        someObject.setId(122L);
        kryo.writeObject(output, someObject);
        output.close();
// ...
        Input input = new Input(new FileInputStream("file.bin"));
        MsgUserNotify desObj = kryo.readObject(input, MsgUserNotify.class);
        input.close();

        log.info(JSON.toJSONString(desObj));
    }
}
