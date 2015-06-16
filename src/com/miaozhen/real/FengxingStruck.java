package com.miaozhen.real;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
//import org.apache.hadoop.io.WritableComparable;

public class FengxingStruck implements Writable {

	public FengxingStruck(){}
	
	private String k;
	private String TestIP;
	private String MiaozhenIP;
	
	

	
	



	public FengxingStruck(String k, String testIP, String miaozhenIP) {
		super();
		this.k = k;
		this.TestIP = testIP;
		this.MiaozhenIP = miaozhenIP;
	}
	


	public String getK() {
		return k;
	}



	public void setK(String k) {
		this.k = k;
	}



	public String getTestIP() {
		return TestIP;
	}



	public void setTestIP(String testIP) {
		this.TestIP = testIP;
	}



	public String getMiaozhenIP() {
		return MiaozhenIP;
	}



	public void setMiaozhenIP(String miaozhenIP) {
		this.MiaozhenIP = miaozhenIP;
	}



	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	
		
	}
	
