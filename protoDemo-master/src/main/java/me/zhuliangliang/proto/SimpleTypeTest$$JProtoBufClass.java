package me.zhuliangliang.proto;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.CodedConstant;
import com.baidu.bjf.remoting.protobuf.utils.FieldUtils;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.UninitializedMessageException;

import java.io.IOException;

public class SimpleTypeTest$$JProtoBufClass implements com.baidu.bjf.remoting.protobuf.Codec<me.zhuliangliang.proto.SimpleTypeTest> {
    private com.google.protobuf.Descriptors.Descriptor descriptor;

    public byte[] encode(me.zhuliangliang.proto.SimpleTypeTest t) throws IOException {
        int size = 0;
        com.google.protobuf.ByteString f_1 = null;
        if (!CodedConstant.isNull((java.lang.String) FieldUtils.getField(t, "name"))) {
            f_1 = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) FieldUtils.getField(t, "name"));
        }
        if (!CodedConstant.isNull((java.lang.String) FieldUtils.getField(t, "name"))) {
            size += com.google.protobuf.CodedOutputStream.computeBytesSize(1, f_1);

        }
        if (f_1 == null) {
            throw new UninitializedMessageException(CodedConstant.asList("name"));
        }
        Integer f_2 = null;
        if (!CodedConstant.isNull(t.getValue())) {
            f_2 = t.getValue();
        }
        if (!CodedConstant.isNull(t.getValue())) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(2, f_2.intValue());

        }
        final byte[] result = new byte[size];
        final CodedOutputStream output = CodedOutputStream.newInstance(result);
        writeTo(t, output);
        return result;
    }

    public me.zhuliangliang.proto.SimpleTypeTest decode(byte[] bb) throws IOException {
        CodedInputStream input = CodedInputStream.newInstance(bb, 0, bb.length);
        me.zhuliangliang.proto.SimpleTypeTest ret = new me.zhuliangliang.proto.SimpleTypeTest();
        try {
            boolean done = false;
            Codec codec = null;
            while (!done) {
                int tag = input.readTag();
                if (tag == 0) {
                    break;
                }
                if (tag == 10) {
                    FieldUtils.setField(ret, "name", input.readString())
                    ;
                    continue;
                }
                if (tag == 16) {
                    ret.setValue(input.readInt32())
                    ;
                    continue;
                }
                input.skipField(tag);
            }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw e;
        } catch (java.io.IOException e) {
            throw e;
        }
        if (CodedConstant.isNull((java.lang.String) FieldUtils.getField(ret, "name"))) {
            throw new UninitializedMessageException(CodedConstant.asList("name"));
        }
        return ret;
    }

    public int size(me.zhuliangliang.proto.SimpleTypeTest t) throws IOException {
        int size = 0;
        com.google.protobuf.ByteString f_1 = null;
        if (!CodedConstant.isNull((java.lang.String) FieldUtils.getField(t, "name"))) {
            f_1 = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) FieldUtils.getField(t, "name"));
        }
        if (!CodedConstant.isNull((java.lang.String) FieldUtils.getField(t, "name"))) {
            size += com.google.protobuf.CodedOutputStream.computeBytesSize(1, f_1);
        }
        if (f_1 == null) {
            throw new UninitializedMessageException(CodedConstant.asList("name"));
        }
        Integer f_2 = null;
        if (!CodedConstant.isNull(t.getValue())) {
            f_2 = t.getValue();
        }
        if (!CodedConstant.isNull(t.getValue())) {
            size += com.google.protobuf.CodedOutputStream.computeInt32Size(2, f_2.intValue());
        }
        return size;
    }

    public void writeTo(me.zhuliangliang.proto.SimpleTypeTest t, CodedOutputStream output) throws IOException {
        com.google.protobuf.ByteString f_1 = null;
        if (!CodedConstant.isNull((java.lang.String) FieldUtils.getField(t, "name"))) {
            f_1 = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) FieldUtils.getField(t, "name"));
        }
        if (f_1 == null) {
            throw new UninitializedMessageException(CodedConstant.asList("name"));
        }
        Integer f_2 = null;
        if (!CodedConstant.isNull(t.getValue())) {
            f_2 = t.getValue();
        }
        if (f_1 != null) {
            output.writeBytes(1, f_1);
        }
        if (f_2 != null) {
            output.writeInt32(2, f_2.intValue());
        }
    }

    public me.zhuliangliang.proto.SimpleTypeTest readFrom(CodedInputStream input) throws IOException {
        me.zhuliangliang.proto.SimpleTypeTest ret = new me.zhuliangliang.proto.SimpleTypeTest();
        try {
            boolean done = false;
            Codec codec = null;
            while (!done) {
                int tag = input.readTag();
                if (tag == 0) {
                    break;
                }
                if (tag == 10) {
                    FieldUtils.setField(ret, "name", input.readString())
                    ;
                    continue;
                }
                if (tag == 16) {
                    ret.setValue(input.readInt32())
                    ;
                    continue;
                }
                input.skipField(tag);
            }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw e;
        } catch (java.io.IOException e) {
            throw e;
        }
        if (CodedConstant.isNull((java.lang.String) FieldUtils.getField(ret, "name"))) {
            throw new UninitializedMessageException(CodedConstant.asList("name"));
        }
        return ret;
    }

    public com.google.protobuf.Descriptors.Descriptor getDescriptor() throws IOException {
        if (this.descriptor != null) {
            return this.descriptor;
        }
        com.google.protobuf.Descriptors.Descriptor descriptor = CodedConstant.getDescriptor(me.zhuliangliang.proto.SimpleTypeTest.class);
        return (this.descriptor = descriptor);
    }
}