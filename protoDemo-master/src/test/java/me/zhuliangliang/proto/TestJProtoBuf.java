package me.zhuliangliang.proto;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by hg on 2016/8/9.
 */
public class TestJProtoBuf {

    @Test
    public void testProto() {
        PersonJProtoBufProtoClass c = new PersonJProtoBufProtoClass();
//        PersonJProtoBufProtoClass

        Codec<SimpleTypeTest> simpleTypeCodec = ProtobufProxy
                .create(SimpleTypeTest.class);

        SimpleTypeTest stt = new SimpleTypeTest();
//        stt.name = "abc";


        stt.setValue(100);
        try {
            // 序列化
            byte[] bb = simpleTypeCodec.encode(stt);
            // 反序列化
            SimpleTypeTest newStt = simpleTypeCodec.decode(bb);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
