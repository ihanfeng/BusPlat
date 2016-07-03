/*
 * Created by yurtozc on 3/24/15 1:09 PM.
 */

package com.zhiyin.app.api.filter.response.r2.wrapper;

import javax.servlet.ServletOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ServletWrapperOutputStream extends ServletOutputStream {

    private final ByteArrayOutputStream baos;

    /**
     * Creates a wrapper for the servlet responses, creating an empty byte array output stream when initialized.
     * The data which will be logged is written to this wrapper and to its byte array.
     * @see javax.servlet.ServletOutputStream
     */
    public ServletWrapperOutputStream() {
        this.baos = new ByteArrayOutputStream();
    }

    /**
     * Writes the specified byte to this servlet wrapper output stream.
     */
    public void write(int b) throws IOException {
        baos.write(b);
    }

    /**
     * Returns the current byte size of the stream.
     * @return int current size of the byte array output stream
     */
    public int size() {
        return baos.size();
    }

    /**
     * Creates a newly allocated byte array. Contents of this steream have been copied into it.
     * @return byte[] array of the output stream data.
     */
    public byte[] toByteArray() {
        return baos.toByteArray();
    }

    /**
     * Flushes the stream, the buffered data will be written.
     */
    public void flush() throws IOException {
        baos.flush();
    }

    /**
     * Closes the byte array stream.
     */
    public void close() throws IOException {
        baos.close();
    }
}
