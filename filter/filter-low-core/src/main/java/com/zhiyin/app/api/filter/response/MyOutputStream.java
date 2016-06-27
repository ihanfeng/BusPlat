package com.zhiyin.app.api.filter.response;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * 这只是示例，可以参考MyWriter
 * @author Administrator
 *
 */
public class MyOutputStream extends ServletOutputStream {
	private ServletOutputStream outputStream;
	
	public MyOutputStream(ServletOutputStream outputStream) {
		this.outputStream = outputStream;
	}

	@Override
	public void write(int b) throws IOException {
		outputStream.write(b);
		System.out.println("output1");
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		super.write(b, off, len);
		System.out.println("output2");
	}

	@Override
	public void write(byte[] b) throws IOException {
		super.write(b);
		System.out.println("output3");
	}

}
