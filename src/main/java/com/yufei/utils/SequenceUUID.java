/*    */ package com.yufei.utils;
/*    */ 
/*    */ import java.net.InetAddress;
/*    */ import java.security.SecureRandom;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SequenceUUID
/*    */ {
/* 13 */   private static SequenceUUID oInstance = null;
/*    */   
/* 15 */   private static String midValue = null;
/*    */   
/* 17 */   private static Logger log = Logger.getLogger(SequenceUUID.class);
/*    */ 
/*    */   
/*    */   public static synchronized SequenceUUID getInstance() {
/* 21 */     if (oInstance == null) {
/* 22 */       oInstance = new SequenceUUID();
/*    */     }
/* 24 */     return oInstance;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUUID() {
/* 29 */     long timeNow = System.currentTimeMillis();
/* 30 */     int timeLow = (int)timeNow & 0xFFFFFFFF;
/*    */     
/* 32 */     if (midValue == null) {
/*    */       try {
/* 34 */         InetAddress inet = InetAddress.getLocalHost();
/* 35 */         byte[] bytes = inet.getAddress();
/* 36 */         String hexInetAddress = hexFormat(getInt(bytes), 8);
/* 37 */         String thisHashCode = hexFormat(System.identityHashCode(this), 8);
/*    */         
/* 39 */         midValue = hexInetAddress + thisHashCode;
/*    */       }
/* 41 */       catch (Exception e) {
/* 42 */         log.error(e.getMessage(), e);
/*    */       } 
/*    */     }
/*    */     
/* 46 */     SecureRandom oRandom = new SecureRandom();
/* 47 */     int node = oRandom.nextInt();
/*    */     
/* 49 */     return hexFormat(timeLow, 8) + midValue + hexFormat(node, 8);
/*    */   }
/*    */ 
/*    */   
/*    */   private static int getInt(byte[] bytes) {
/* 54 */     int i = 0;
/* 55 */     int j = 24;
/*    */     
/* 57 */     for (int k = 0; j >= 0; k++) {
/* 58 */       int l = bytes[k] & 0xFF;
/* 59 */       i += l << j;
/* 60 */       j -= 8;
/*    */     } 
/*    */     
/* 63 */     return i;
/*    */   }
/*    */ 
/*    */   
/*    */   private static String hexFormat(int i, int j) {
/* 68 */     String s = Integer.toHexString(i);
/*    */     
/* 70 */     return padHex(s, j) + s;
/*    */   }
/*    */ 
/*    */   
/*    */   private static String padHex(String s, int i) {
/* 75 */     StringBuffer tmpBuffer = new StringBuffer();
/*    */     
/* 77 */     if (s.length() < i) {
/* 78 */       for (int j = 0; j < i - s.length(); j++) {
/* 79 */         tmpBuffer.append('0');
/*    */       }
/*    */     }
/*    */     
/* 83 */     return tmpBuffer.toString();
/*    */   }
/*    */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/SequenceUUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */