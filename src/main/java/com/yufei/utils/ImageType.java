/*    */ package com.yufei.utils;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ImageType
/*    */ {
/* 11 */   JPG,
/* 12 */   GIF,
/* 13 */   PNG,
/* 14 */   UNKNOWN;
/*    */
/* 16 */
private static final Map<String, ImageType> extensionMap;
static {
    extensionMap = new HashMap<>();
    extensionMap.put("jpg", JPG);
    extensionMap.put("jpeg", JPG);
    extensionMap.put("gif", GIF);
    extensionMap.put("png", PNG);
  }
/*    */
/*    */   public static ImageType getType(String ext) {
/* 26 */     ext = ext.toLowerCase();
/* 27 */     if (extensionMap.containsKey(ext)) {
/* 28 */       return extensionMap.get(ext);
/*    */     }
/* 30 */     return UNKNOWN;
/*    */   }
/*    */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/ImageType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */