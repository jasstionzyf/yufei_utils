/*    */ package com.yufei.utils;
/*    */ 
/*    */ import net.sf.json.JSON;
/*    */ import net.sf.json.JSONSerializer;
/*    */ import net.sf.json.xml.XMLSerializer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JSONUtil
/*    */ {
/*    */   public static String convertXmlToJson(String inputXml) {
/* 12 */     String jsonStr = null;
/* 13 */     XMLSerializer xmlSerializer = new XMLSerializer();
/* 14 */     JSON json = xmlSerializer.read(inputXml);
/* 15 */     jsonStr = json.toString();
/* 16 */     return jsonStr;
/*    */   }
/*    */   public static String convertJsonToXml(String jsonData) {
/* 19 */     XMLSerializer serializer = new XMLSerializer();
/* 20 */     JSON json = JSONSerializer.toJSON(jsonData);
/* 21 */     String xml = serializer.write(json);
/* 22 */     return xml;
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {}
/*    */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/JSONUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */