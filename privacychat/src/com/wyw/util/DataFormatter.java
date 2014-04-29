package com.wyw.util;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class DataFormatter {
	
	//
	//������ByteToObject �� ObjectToByte
	//     ��ֱ��ת���ڴ棬����̫��������Ϣ�����Ƽ�ʹ��
	//     ��������Ҫת���Ķ�������������б�дת������
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
