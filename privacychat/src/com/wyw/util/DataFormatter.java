package com.wyw.util;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class DataFormatter {
	
	//
	//方法：ByteToObject 和 ObjectToByte
	//     是直接转换内存，附带太多冗余信息，不推荐使用
	//     可以在需要转换的对象的类里面自行编写转换代码
	//
	
	public static Object ByteToObject(byte[] bytes){ 
        Object obj = null; 

        try {  
	        ByteArrayInputStream bi = new ByteArrayInputStream(bytes); 
	        ObjectInputStream oi = new ObjectInputStream(bi); 
	        obj = oi.readObject();    
	
	        bi.close(); 
	        oi.close(); 
        }  
        catch(Exception e) {  
            System.out.println("translation"+e.getMessage()); 
            e.printStackTrace(); 
        }  
        
        return obj; 
    } 
	
    public static byte[] ObjectToByte(java.lang.Object obj) 
    {
        byte[] bytes = null; 

        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream(); 
            ObjectOutputStream oo = new ObjectOutputStream(bo); 
            oo.writeObject(obj); 
   
            bytes = bo.toByteArray(); 
   
            bo.close(); 
            oo.close();     
        }  
        catch(Exception e) {  
            System.out.println("translation"+e.getMessage()); 
            e.printStackTrace(); 
        }  
        
        return bytes; 
    } 

}
