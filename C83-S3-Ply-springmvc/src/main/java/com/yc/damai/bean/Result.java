package com.yc.damai.bean;

public class Result {

	private int code;// 0失败, 1成功
	
	private String mag;// 返回的信息
	
	private Object data;// 返回的数据

	
	public Result(int code, String mag) {
		super();
		this.code = code;
		this.mag = mag;
	}
	
	public Result(int code, String mag, Object data) {
		super();
		this.code = code;
		this.mag = mag;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMag() {
		return mag;
	}

	public void setMag(String mag) {
		this.mag = mag;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", mag=" + mag + ", data=" + data + "]";
	}
	
	
	
}
