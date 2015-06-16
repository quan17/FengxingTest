package com.miaozhen.real;

public class IPConvertor {

	    public static String numToIP(long ip){  
	        StringBuilder sb = new StringBuilder();  
	        for (int i = 3; i >=0; i--) {  
	            sb.append((ip>>>(i*8))&0x000000ff);  
	            if (i!=0) {  
	                sb.append('.');  
	            }  
	        }  
	        System.out.println(sb);  
	        return sb.toString();  
	    }  
	      
	    public static long ipToNum(String ip){  
	        long num = 0;
	        String[] tempdelete=new String[5];
	        tempdelete=ip.split("\\%20");
	        ip=tempdelete[tempdelete.length-1];
	        String[] sections = ip.split("\\.");  
	        int i=3;  
	        for (String str : sections) {  
	            num+=(Long.parseLong(str)<<(i*8));  
	            i--;  
	        }  
	       // System.out.println(num);  
	        return num;  
	    }  
	}

