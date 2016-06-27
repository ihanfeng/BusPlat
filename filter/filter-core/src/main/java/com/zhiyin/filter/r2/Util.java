/*
 * Created by yurtozc on 3/24/15 1:12 PM.
 */

package com.zhiyin.filter.r2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Util {

    public static final String ENC_UTF8 = "utf-8";

    /**
     * Casts the object "o" to the given class "clazz". Returns null if the object can not be casted.
     *
     * @param clazz
     * @param object
     * @return T object OR null
     */
    public static <T> T convertWithCastCheck(final Class<T> clazz, final Object object) {
        try {
            return clazz.cast(object);
        } catch (ClassCastException e) {
            return null;
        }
    }

    /**
     * Reads the data of the input stream, and converts its data to an UTF-8 String.
     *
     * @param in The input stream which will be read.
     * @return The String that created with input stream.
     * @throws IOException
     *
     */
    public static String readInputStream(final InputStream in) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        final int bufferSize = 128;

        BufferedReader bufferedReader = null;

        try {
            if (in != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(in, Charset.forName(ENC_UTF8)));
                char[] charBuffer = new char[bufferSize];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        return stringBuilder.toString();
    }
}
