package com.cn.service.sierviceImp;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import com.cn.service.RASPublicPassWord;

import sun.misc.BASE64Decoder;

public class RASPublicPassWordImp implements RASPublicPassWord {

	public String RASpassword(String key) {
		 PublicKey publicKey = null;
        try {
            
            byte[] keyBytes;  
             keyBytes = (new BASE64Decoder()).decodeBuffer(key);  

             X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);  
             KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
             publicKey = keyFactory.generatePublic(keySpec);
             
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
         return publicKey.toString();   
	}
	
	//获取公钥匙
	public static PublicKey getPublicKey(){
		
		return null;
	}
	
	
}
