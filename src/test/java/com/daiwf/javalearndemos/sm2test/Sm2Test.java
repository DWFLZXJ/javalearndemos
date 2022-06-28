package com.daiwf.javalearndemos.sm2test;


import org.junit.Test;

import java.util.Random;

public class Sm2Test {
	

	/*public static void main(String[] args) {
		//生成密钥
		Sm2Key key2 = SmCryptoUtil.createSm2Key();
		//公钥
        System.out.println(key2.getPublicKey());
        //私钥
        System.out.println(key2.getPrivateKey());
		 String data="加解密数据";
		 //公钥
		 String publicKeysm2 = "04cdf58c65e0536b10edd8f26556540eacbd2f0143b6159e46ee70108a1d5543360cc89718f0e01f670edd23bbb704bddd56b5b5cd35324011ea170d2cf0c1908f";
		 //私钥
		 String privateKeysm2 = "5897a539498c8abce7b5d389ef15b8235b587b8897a8f1b8a707d78131e5efe5";
         //数据加密
		 String sm2Encode = SmCryptoUtil.sm2Encode(data, publicKeysm2);
         System.out.println(sm2Encode);
         //数据解密
         String sm2Decode = SmCryptoUtil.sm2Decode(sm2Encode, privateKeysm2);
         System.out.println(sm2Decode);
        
        
	}*/

	@Test
	public void sm2test(){

	 Random rand = new Random();
		int randtest =rand.nextInt(10);
		System.out.println(randtest);

		long test =7060487741508412814L;

		String str ="7060487741508412814";
		long newln =Long.valueOf(str).longValue();
		System.out.println(newln);


	}

}
