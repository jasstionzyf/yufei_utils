/*    */ package com.yufei.utils;
/*    */ 
/*    */ import com.yufei.annotation.ExcelFieldOrder;
/*    */ import com.yufei.annotation.ExcelHeaders;
/*    */ import com.yufei.annotation.QueryEnable;
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnnotationUtil
/*    */ {
/*    */   public static List<String> getQueryEnableFieldNames(Class entityClass) {
/* 21 */     List<String> fieldNames = new ArrayList<>();
/* 22 */     List<Field> fields = CommonUtil.getAllDeclareFields(entityClass);
/* 23 */     for (Field field : fields) {
/* 24 */       if (fieldIsQueryEnable(field).booleanValue()) {
/* 25 */         fieldNames.add(field.getName());
/*    */       }
/*    */     } 
/* 28 */     return fieldNames;
/*    */   }
/*    */   
/*    */   public static Boolean fieldIsQueryEnable(Field field) {
/* 32 */     QueryEnable queryEnable = field.<QueryEnable>getAnnotation(QueryEnable.class);
/* 33 */     if (queryEnable == null) {
/* 34 */       return Boolean.valueOf(false);
/*    */     }
/* 36 */     return Boolean.valueOf(queryEnable.enable());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static List<String> getFieldOrders(Class entityClass) {
/* 44 */     List<String> fieldOrders = new ArrayList<>();
/* 45 */     ExcelFieldOrder excelFAnnotation = (ExcelFieldOrder)entityClass.getAnnotation(ExcelFieldOrder.class);
/* 46 */     if (excelFAnnotation != null) {
/* 47 */       for (String fieldOrder : excelFAnnotation.fieldOrders().split(",")) {
/* 48 */         fieldOrders.add(fieldOrder);
/*    */       }
/*    */     }
/*    */     
/* 52 */     return fieldOrders;
/*    */   }
/*    */   public static List<String> getExcelHeaders(Class entityClass) {
/* 55 */     List<String> fieldOrders = new ArrayList<>();
/* 56 */     ExcelHeaders excelFAnnotation = (ExcelHeaders)entityClass.getAnnotation(ExcelHeaders.class);
/* 57 */     if (excelFAnnotation != null) {
/* 58 */       for (String fieldOrder : excelFAnnotation.headers().split(",")) {
/* 59 */         fieldOrders.add(fieldOrder);
/*    */       }
/*    */     }
/*    */     
/* 63 */     return fieldOrders;
/*    */   }
/*    */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/AnnotationUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */