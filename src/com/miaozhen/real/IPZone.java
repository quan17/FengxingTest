package com.miaozhen.real;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IPZone {
	public static boolean IPisZone(String IP, String Zone){
		String ZoneCode = null;
		boolean flag = false;
		Long LongIP = null;
		try{LongIP=IPConvertor.ipToNum(IP);}
		catch (Exception e5) {
        }
		
		switch(Zone){
		case "wenzhou": 	ZoneCode="000081";break;
		case "fuzhou": 		ZoneCode="000091";break;
		case "hangzhou": 	ZoneCode="000079";break;
		case "chengdu": 	ZoneCode="000145";break;
		default: System.out.println("new Zone"+Zone);break;
		}
		File file = new File("newlib");
        BufferedReader reader = null;
        try {
//            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null && ZoneCode != null) { 
            	String[] LineSplice =new String[3];
            	LineSplice=tempString.split(",");
            	long leftnum=  Long.parseLong(LineSplice[0]);
            	long rightnum= Long.parseLong(LineSplice[1]);
            	if(LongIP>= leftnum && LongIP<=rightnum)
            		if(ZoneCode.equals(LineSplice[2].substring(LineSplice[2].length()-6)))
            			{flag=true;break;}
            		else 
        				{flag=false;break;}
            	else 
				{flag=false;}
            
            
            
            }
            
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e1) {
            }
        }
    }
		return flag;
	}
	


//public static void main(String[] args) {  
//	System.out.println(IPZone.IPisZone("223.92.134.19", "wenzhou"));
//	 
//}
}