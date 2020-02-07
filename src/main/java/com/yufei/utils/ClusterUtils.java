/*    */ package com.yufei.utils;
/*    */ 
/*    */ import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

import java.util.ArrayList;
import java.util.List;

/*    */
/*    */
/*    */
/*    */
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClusterUtils
/*    */ {
/*    */   public static void main(String[] args) {
/* 43 */     List<ValueWrapper> points = new ArrayList<>();
/*    */     
/* 45 */     double[] d1 = { 0.0D, 1.0D };
/* 46 */     points.add(new ValueWrapper(d1));
/* 47 */     double[] d2 = { 0.0D, 2.0D };
/*    */     
/* 49 */     points.add(new ValueWrapper(d2));
/* 50 */     double[] d3 = { 0.0D, 3.0D };
/* 51 */     double[] d4 = { 0.0D, 5.0D };
/* 52 */     double[] d5 = { 0.0D, 6.0D };
/* 53 */     double[] d51 = { 0.0D, 7.0D };
/*    */     
/* 55 */     double[] d6 = { 0.0D, 10.0D };
/* 56 */     double[] d7 = { 0.0D, 11.0D };
/*    */ 
/*    */     
/* 59 */     points.add(new ValueWrapper(d3));
/* 60 */     points.add(new ValueWrapper(d51));
/*    */ 
/*    */     
/* 63 */     points.add(new ValueWrapper(d4));
/*    */     
/* 65 */     points.add(new ValueWrapper(d5));
/* 66 */     points.add(new ValueWrapper(d6));
/*    */     
/* 68 */     points.add(new ValueWrapper(d7));
/*    */     
/* 70 */     KMeansPlusPlusClusterer<ValueWrapper> clusterer = new KMeansPlusPlusClusterer(5, 100);
/* 71 */     List<CentroidCluster<ValueWrapper>> clusterResults = clusterer.cluster(points);
/*    */ 
/*    */     
///* 74 */     for (int i = 0; i < clusterResults.size(); i++) {
///* 75 */       System.out.println("Cluster " + i);
///* 76 */       for (ValueWrapper locationWrapper : ((CentroidCluster)clusterResults.get(i)).getPoints())
///* 77 */         System.out.print(locationWrapper.getPoint()[0] + ":" + locationWrapper.getPoint()[1]);
///* 78 */       System.out.println("\n");
///*    */     }
             }
/*    */ 
/*    */ 
/*    */   
/*    */   public static class ValueWrapper
/*    */     implements Clusterable
/*    */   {
/*    */     double[] points;
/*    */ 
/*    */     
/*    */     public ValueWrapper(double[] points) {
/* 91 */       this.points = new double[] { 0.0D, 0.0D };
/*    */       this.points = points;
/*    */     }
/*    */     public double[] getPoint() {
/* 95 */       return this.points;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/ClusterUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */