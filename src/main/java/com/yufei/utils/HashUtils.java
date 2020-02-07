/*    */ package com.yufei.utils;
/*    */ 
/*    */ import java.util.BitSet;
/*    */ import org.infinispan.commons.hash.MurmurHash3;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HashUtils
/*    */ {
/*    */   public static void main(String[] args) {
/* 14 */     String str = "http://www.amazon.cn/Lenovo-%E8%81%94%E6%83%B3-S890-%E5%8F%8C%E5%8D%A1%E5%8F%8C%E5%BE%85%E6%99%BA%E8%83%BD%E6%89%8B%E6%9C%BA-%E9%9D%9E%E5%AE%9A%E5%88%B6/dp/B00A64P47E/ref=br_lf_m_224388_1_8_img?ie=UTF8&m=A1AJ19PSB66TGU&s=wireless&pf_rd_p=70646632&pf_rd_s=center-5&pf_rd_t=1401&pf_rd_i=224388&pf_rd_m=A1AJ19PSB66TGU&pf_rd_r=044J0XY0V6EHV7F2QY20";
/* 15 */     String str1 = "http://www.amazon.cn/CONOR-%E9%85%B7%E8%AF%BA-WG200-3G%E6%99%BA%E8%83%BD%E6%89%8B%E6%9C%BA-%E5%8F%8C%E5%8D%A1%E5%8F%8C%E6%A8%A14-0%E5%AF%B8%E5%B1%8F%E5%B9%95-500%E4%B8%87%E5%83%8F%E7%B4%A0-%E7%99%BD%E8%89%B2/dp/B009DCR39C/ref=br_lf_m_224388_1_13_img?ie=UTF8&m=A1AJ19PSB66TGU&s=wireless&pf_rd_p=70646632&pf_rd_s=center-5&pf_rd_t=1401&pf_rd_i=224388&pf_rd_m=A1AJ19PSB66TGU&pf_rd_r=044J0XY0V6EHV7F2QY20";
/*    */ 
/*    */     
/* 18 */     MurmurHash3 murmurHash3 = new MurmurHash3();
/*    */ 
/*    */ 
/*    */     
/* 22 */     System.out.print(murmurHash3.hash(str) + "\n");
/* 23 */     System.out.print(murmurHash3.hash(str1) + "\n");
/* 24 */     int max = (int)(Math.pow(2.0D, 28.0D) - 1.0D);
/* 25 */     System.out.print(max + "\n");
/* 26 */     System.out.print(((new Long(Math.abs(murmurHash3.hash(str1)))).longValue() + (new Long(max)).longValue()) + "\n");
/* 27 */     Long maxLong = Long.valueOf((long)(Math.pow(2.0D, 31.0D) - 1.0D));
/* 28 */     System.out.print(maxLong + "\n");
/* 29 */     System.out.print((max / 1024 / 1024) + "MB");
/*    */     
/* 31 */     BitSet bitSetN = new BitSet(max);
/* 32 */     BitSet bitSetP = new BitSet(max);
/* 33 */     int hashCode1 = murmurHash3.hash(str);
/* 34 */     int hashCode2 = murmurHash3.hash(str1);
/* 35 */     if (hashCode1 >= 0) {
/* 36 */       bitSetP.set(hashCode1);
/*    */     } else {
/*    */       
/* 39 */       bitSetN.set(Math.abs(hashCode1));
/*    */     } 
/*    */     
/* 42 */     if (hashCode2 >= 0) {
/* 43 */       bitSetP.set(hashCode2);
/*    */     } else {
/*    */       
/* 46 */       bitSetN.set(Math.abs(hashCode2));
/*    */     } 
/*    */ 
/*    */     
/* 50 */     System.out.print(bitSetN.get(0) + ";" + bitSetN.get(Math.abs(hashCode2)));
/*    */     
/* 52 */     System.out.print(bitSetP.get(0) + ";" + bitSetP.get(hashCode1));
/*    */   }
/*    */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/HashUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */