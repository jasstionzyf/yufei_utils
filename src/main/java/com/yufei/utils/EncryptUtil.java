/*    */ package com.yufei.utils;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ 
/*    */ 
/*    */ public class EncryptUtil
/*    */ {
/*    */   public static void main(String[] args) {
/* 11 */     String md5_1 = md5("123");
/* 12 */     String md5_2 = md5("abc");
/* 13 */     System.out.println(md5_1 + "\n" + md5_2);
/* 14 */     System.out.println("md5 length: " + md5_1.length());
/*    */     
/* 16 */     String sha_1 = sha("123");
/* 17 */     String sha_2 = sha("abc");
/* 18 */     System.out.println(sha_1 + "\n" + sha_2);
/* 19 */     System.out.println("sha length: " + sha_1.length());
/*    */   }
/*    */ 
/*    */   
/*    */   public static String md5(String inputText) {
/* 24 */     return encrypt(inputText, "md5");
/*    */   }
/*    */ 
/*    */   
/*    */   public static String sha(String inputText) {
/* 29 */     return encrypt(inputText, "sha-1");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static String encrypt(String inputText, String algorithmName) {
/* 42 */     if (inputText == null || "".equals(inputText.trim())) {
/* 43 */       throw new IllegalArgumentException("请输入要加密的内容");
/*    */     }
/* 45 */     if (algorithmName == null || "".equals(algorithmName.trim())) {
/* 46 */       algorithmName = "md5";
/*    */     }
/* 48 */     String encryptText = null;
/*    */     try {
/* 50 */       MessageDigest m = MessageDigest.getInstance(algorithmName);
/* 51 */       m.update(inputText.getBytes("UTF8"));
/* 52 */       byte[] s = m.digest();
/*    */       
/* 54 */       return hex(s);
/* 55 */     } catch (NoSuchAlgorithmException e) {
/* 56 */       e.printStackTrace();
/* 57 */     } catch (UnsupportedEncodingException e) {
/* 58 */       e.printStackTrace();
/*    */     } 
/* 60 */     return encryptText;
/*    */   }
/*    */ 
/*    */   
/*    */   private static String hex(byte[] arr) {
/* 65 */     StringBuffer sb = new StringBuffer();
/* 66 */     for (int i = 0; i < arr.length; i++) {
/* 67 */       sb.append(Integer.toHexString(arr[i] & 0xFF | 0x100).substring(1, 3));
/*    */     }
/*    */     
/* 70 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/EncryptUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */