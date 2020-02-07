/*    */ package com.yufei.component.repetition;
/*    */ 
/*    */ import java.util.BitSet;
/*    */ import org.infinispan.commons.hash.MurmurHash3;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IsRepetiveBatSet
/*    */   implements IsRepetive
/*    */ {
/* 14 */   public static IsRepetive isRepetive = null; private int max; private BitSet bitSetN; private BitSet bitSetP; private MurmurHash3 murmurHash3; private long existedNumber;
/*    */   public static synchronized IsRepetive getInstance() {
/* 16 */     if (isRepetive == null) {
/* 17 */       isRepetive = new IsRepetiveBatSet();
/*    */     }
/* 19 */     return isRepetive;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private IsRepetiveBatSet() {
/* 30 */     this.max = (int)(Math.pow(2.0D, 10.0D) - 1.0D);
/* 31 */     this.bitSetN = new BitSet(this.max);
/* 32 */     this.bitSetP = new BitSet(this.max);
/* 33 */     this.murmurHash3 = new MurmurHash3();
/*    */     
/* 35 */     this.existedNumber = 0L; } public IsRepetiveBatSet(int capacity) { this.max = (int)(Math.pow(2.0D, 10.0D) - 1.0D); this.bitSetN = new BitSet(this.max); this.bitSetP = new BitSet(this.max); this.murmurHash3 = new MurmurHash3(); this.existedNumber = 0L;
/*    */     this.max = capacity; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public synchronized boolean isRepetive(String identify) {
/* 42 */     if (identify == null) {
/* 43 */       return false;
/*    */     }
/* 45 */     int hashCode = this.murmurHash3.hash(identify);
/* 46 */     if (hashCode < 0) {
/* 47 */       if (this.bitSetN.get(Math.abs(hashCode))) {
/* 48 */         return true;
/*    */       }
/*    */       
/* 51 */       this.bitSetN.set(Math.abs(hashCode));
/* 52 */       this.existedNumber++;
/* 53 */       return false;
/*    */     } 
/*    */     
/* 56 */     if (this.bitSetP.get(hashCode)) {
/* 57 */       return true;
/*    */     }
/*    */     
/* 60 */     this.bitSetP.set(hashCode);
/* 61 */     this.existedNumber++;
/* 62 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 68 */     String str = "http://www.lefeng.com/?utm_source=bdppc1&utm_medium=cpc&utm_campaign=218&aid=6581&cid2=A%E5%93%81%E7%89%8C%E8%AF%8D_%E5%95%86%E5%93%81%E5%93%81%E7%89%8C-%E4%B9%90%E8%9C%82%E7%BD%91_%E9%94%99%E8%AF%8D&cid3=%E4%B9%90%E5%B3%B0&referer=http://www.baidu.com/baidu?word=%E4%B9%90%E5%B3%B0&amp;se=ichuner_1_";
/* 69 */     String str1 = "http://product.lefeng.com/product/53304.html";
/* 70 */     String str2 = "http://product.lefeng.com/product/123748.html";
/* 71 */     String str3 = "http://product.lefeng.com/product/155962.html";
/* 72 */     String str4 = "http://www.lefeng.com/?utm_source=bdppc1&utm_medium=cpc&utm_campaign=218&aid=6581&cid2=A%E5%93%81%E7%89%8C%E8%AF%8D_%E5%95%86%E5%93%81%E5%93%81%E7%89%8C-%E4%B9%90%E8%9C%82%E7%BD%91_%E9%94%99%E8%AF%8D&cid3=%E4%B9%90%E5%B3%B0&referer=http://www.baidu.com/baidu?word=%E4%B9%90%E5%B3%B0&amp;se=ichuner_1_";
/* 73 */     IsRepetive isRepetive = new IsRepetiveBatSet();
/* 74 */     System.out.print(isRepetive.isRepetive(str));
/* 75 */     System.out.print(isRepetive.isRepetive(str1));
/* 76 */     System.out.print(isRepetive.isRepetive(str2));
/* 77 */     System.out.print(isRepetive.isRepetive(str3));
/* 78 */     System.out.print(isRepetive.isRepetive(str4));
/*    */     while (true);
/*    */   }
/*    */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/component/repetition/IsRepetiveBatSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */