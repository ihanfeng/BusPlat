package com.zhiyin.app.api.filter.response;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

public class MyResponseWrapper extends HttpServletResponseWrapper {
	private MyWriter myWriter;
	private MyOutputStream myOutputStream;
	
	public MyResponseWrapper(HttpServletResponse response) {
		super(response);
	}
	
	@Override
	public PrintWriter getWriter() throws IOException {
		myWriter = new MyWriter(super.getWriter());
		return myWriter;
	}
	
	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		myOutputStream = new MyOutputStream(super.getOutputStream());
		return myOutputStream;
	}

	public MyWriter getMyWriter() {
		return myWriter;
	}

	public MyOutputStream getMyOutputStream() {
		return myOutputStream;
	}
	
}
