/*    */ package com.yufei.utils;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.io.StringWriter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExceptionUtil
/*    */ {
/*    */   public static String getExceptionDetailsMessage(Exception e) {
/* 16 */     StringWriter sw = new StringWriter();
/* 17 */     e.printStackTrace(new PrintWriter(sw));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 26 */     return sw.toString();
/*    */   }
/*    */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/ExceptionUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */