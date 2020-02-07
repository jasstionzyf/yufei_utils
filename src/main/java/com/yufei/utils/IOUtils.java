/*    */ package com.yufei.utils;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.InputStream;
/*    */ import java.nio.charset.Charset;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IOUtils
/*    */ {
/*    */   protected static final int BUFFER_SIZE = 1024;
/*    */   protected static final String default_encoding = "utf-8";
/*    */   
/*    */   public static byte[] translantStreamToByte(InputStream in) {
/* 17 */     byte[] buffer = new byte[1024];
/* 18 */     byte[] conts = null;
/* 19 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/*    */     
/*    */     try { int bufferFilled;
/* 22 */       while ((bufferFilled = in.read(buffer, 0, buffer.length)) != -1) {
/* 23 */         out.write(buffer, 0, bufferFilled);
/*    */       }
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 41 */       return conts; } catch (Exception e) { return conts; }
/*    */     finally
/*    */     { Exception exception = null;
/*    */       try {
/*    */         if (in != null)
/*    */           in.close(); 
/*    */         if (out != null)
/*    */           out.close(); 
/*    */       } catch (Exception exception1) {} }
/* 50 */      } public static String translantByteStreamToString(byte[] bytes, String encoding) { if (encoding == null) {
/* 51 */       encoding = "utf-8";
/*    */     }
/* 53 */     String pData = null;
/*    */     try {
/* 55 */       pData = new String(bytes, Charset.forName(encoding));
/* 56 */     } catch (Exception e) {
/* 57 */       pData = new String(bytes, Charset.forName("utf-8"));
/*    */     } 
/*    */     
/* 60 */     return pData; }
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
/*    */ 
/*    */   
/*    */   public static String deflateByte2String(String charset, byte[] input) throws Exception {
/* 74 */     byte[] temp = new byte[input.length * 10];
/* 75 */     int length = input.length;
/* 76 */     byte[] results = new byte[length];
/* 77 */     System.arraycopy(temp, 0, results, 0, input.length);
/*    */     
/* 79 */     return new String(results, charset);
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {}
/*    */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/IOUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */