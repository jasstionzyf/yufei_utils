/*    */ package com.yufei.utils;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import java.sql.Statement;
/*    */ 
/*    */ public class DButils
/*    */ {
/*    */   public static void closeConnection(Connection conn) {
/*    */     try {
/* 12 */       if (conn != null) {
/* 13 */         conn.close();
/*    */       }
/* 15 */     } catch (SQLException e) {
/*    */       
/* 17 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void closeStatement(Statement sm) {
/*    */     try {
/* 23 */       if (sm != null) {
/* 24 */         sm.close();
/*    */       }
/* 26 */     } catch (SQLException e) {
/*    */       
/* 28 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void closeResultSet(ResultSet rs) {
/*    */     try {
/* 34 */       if (rs != null) {
/* 35 */         rs.close();
/*    */       }
/* 37 */     } catch (SQLException e) {
/*    */       
/* 39 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/DButils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */