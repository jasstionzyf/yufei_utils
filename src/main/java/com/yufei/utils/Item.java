/*    */ package com.yufei.utils;
/*    */ 
/*    */ public class Item
/*    */ {
/*  5 */   private String description = null;
/*    */   
/*  7 */   private String name = null;
/*    */   public String getName() {
/*  9 */     return this.name;
/*    */   }
/*    */   public void setName(String name) {
/* 12 */     this.name = name;
/*    */   }
/*    */   
/*    */   public Item(String name, String description) {
/* 16 */     this.name = name;
/* 17 */     this.description = description;
/*    */   }
/*    */ 
/*    */   
/*    */   public Item() {}
/*    */   
/*    */   public String getDescription() {
/* 24 */     return this.description;
/*    */   }
/*    */   public void setDescription(String description) {
/* 27 */     this.description = description;
/*    */   }
/*    */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/Item.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */