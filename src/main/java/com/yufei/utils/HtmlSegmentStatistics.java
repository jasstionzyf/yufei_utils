/*    */ package com.yufei.utils;
/*    */ 
/*    */ public class HtmlSegmentStatistics
/*    */ {
/*    */   private int totalCharNum;
/*    */   private int htmlTagCharNum;
/*    */   private int contentCharNum;
/*    */   private double percentageOfContent;
/*    */   
/*    */   public String toString() {
/* 11 */     return "HtmlSegmentStatistics [totalCharNum=" + this.totalCharNum + ", htmlTagCharNum=" + this.htmlTagCharNum + ", contentCharNum=" + this.contentCharNum + ", percentageOfContent=" + this.percentageOfContent + "]";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public HtmlSegmentStatistics(int totalCharNum, int htmlTagCharNum, int contentCharNum) {
/* 22 */     this.totalCharNum = totalCharNum;
/* 23 */     this.htmlTagCharNum = htmlTagCharNum;
/* 24 */     this.contentCharNum = contentCharNum;
/*    */   }
/*    */   
/*    */   public int getTotalCharNum() {
/* 28 */     return this.totalCharNum;
/*    */   }
/*    */   public void setTotalCharNum(int totalCharNum) {
/* 31 */     this.totalCharNum = totalCharNum;
/*    */   }
/*    */   public int getHtmlTagCharNum() {
/* 34 */     return this.htmlTagCharNum;
/*    */   }
/*    */   public void setHtmlTagCharNum(int htmlTagCharNum) {
/* 37 */     this.htmlTagCharNum = htmlTagCharNum;
/*    */   }
/*    */   public int getContentCharNum() {
/* 40 */     return this.contentCharNum;
/*    */   }
/*    */   public void setContentCharNum(int contentCharNum) {
/* 43 */     this.contentCharNum = contentCharNum;
/*    */   }
/*    */   public double getPercentageOfContent() {
/* 46 */     return getContentCharNum() / getTotalCharNum();
/*    */   }
/*    */   public void setPercentageOfContent(double percentageOfContent) {
/* 49 */     this.percentageOfContent = percentageOfContent;
/*    */   }
/*    */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/HtmlSegmentStatistics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */