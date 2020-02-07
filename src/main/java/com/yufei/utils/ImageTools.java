/*     */ package com.yufei.utils;
/*     */ 
/*     */ import org.im4java.core.ConvertCmd;
/*     */ import org.im4java.core.IMOperation;
/*     */ import org.im4java.core.Operation;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageTools
/*     */ {
/*  11 */   public static String imageMagickPath = CommonUtil.loadProperties("im4java").get("imageMagickPath");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void cutImage(String srcPath, String newPath, int x, int y, int x1, int y1) throws Exception {
/*  27 */     int width = x1 - x;
/*  28 */     int height = y1 - y;
/*  29 */     IMOperation op = new IMOperation();
/*  30 */     op.addImage(new String[] { srcPath });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  37 */     op.crop(Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(x), Integer.valueOf(y));
/*  38 */     op.addImage(new String[] { newPath });
/*  39 */     ConvertCmd convert = new ConvertCmd();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  44 */     convert.run((Operation)op, new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void cutImage(int width, int height, String srcPath, String newPath) throws Exception {
/*  56 */     IMOperation op = new IMOperation();
/*  57 */     op.addImage(new String[] { srcPath });
/*  58 */     op.resize(Integer.valueOf(width), Integer.valueOf(height));
/*  59 */     op.addImage(new String[] { newPath });
/*  60 */     ConvertCmd convert = new ConvertCmd();
/*     */ 
/*     */     
/*  63 */     convert.run((Operation)op, new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void cutImage(int width, String srcPath, String newPath) throws Exception {
/*  75 */     IMOperation op = new IMOperation();
/*  76 */     op.addImage(new String[] { srcPath });
/*  77 */     op.resize(Integer.valueOf(width), null);
/*  78 */     op.addImage(new String[] { newPath });
/*  79 */     ConvertCmd convert = new ConvertCmd();
/*     */     
/*  81 */     if (!System.getProperty("file.separator").equalsIgnoreCase("/")) {
/*  82 */       convert.setSearchPath(imageMagickPath);
/*     */     }
/*     */     
/*  85 */     convert.run((Operation)op, new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addImgText(String srcPath, String text) throws Exception {
/*  93 */     IMOperation op = new IMOperation();
/*  94 */     op.font("宋体").gravity("southeast").pointsize(Integer.valueOf(18)).fill("#BCBFC8")
/*  95 */       .draw("text 0,0 " + text + "");
/*  96 */     op.addImage();
/*  97 */     op.addImage();
/*  98 */     ConvertCmd convert = new ConvertCmd();
/*     */ 
/*     */     
/* 101 */     convert.run((Operation)op, new Object[] { srcPath, srcPath });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws Exception {
/* 110 */     addImgText("/data/images/shoe.jpg", "zhezhe.cn");
/*     */   }
/*     */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/ImageTools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */