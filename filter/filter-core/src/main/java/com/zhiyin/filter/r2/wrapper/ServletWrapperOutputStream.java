/*
 * Created by yurtozc on 3/24/15 1:09 PM.
 */

package com.zhiyin.filter.r2.wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ServletWrapperOutputStream extends ServletOutputStream {

    private final ByteArrayOutputStream baos;

    public ServletWrapperOutputStream() {
        this.baos = new ByteArrayOutputStream();
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener listener) {

    }
    public void write(int b) throws IOException {
        baos.write(b);
    }

    public int size() {
        return baos.size();
    }

    public byte[] toByteArray() {
        return baos.toByteArray();
    }

    public void flush() throws IOException {
        baos.flush();
    }
    public void close() throws IOException {
        baos.close();
    }
}
