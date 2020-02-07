/*     */ package com.yufei.utils;
/*     */ 
/*     */ import com.mchange.v2.log.MLog;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ArrayBlockingQueue;
/*     */ import java.util.concurrent.CountDownLatch;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParallelExecutor<PType>
/*     */ {
/*  21 */   private int threadNum = 1;
/*     */   private Collection<PType> collection;
/*  23 */   private Worker<PType> worker = null;
/*  24 */   private CountDownLatch countDownLatch = null;
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
/*     */   @Deprecated
/*     */   public ParallelExecutor(int threadNum, Collection<PType> collection, Worker<PType> worker) {
/*  37 */     this.threadNum = threadNum;
/*  38 */     this.collection = collection;
/*  39 */     this.worker = worker;
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
/*     */   
/*     */   public ParallelExecutor(CountDownLatch countDownLatch, int threadNum, Collection<PType> collection, Worker<PType> worker) {
/*  52 */     this.countDownLatch = countDownLatch;
/*  53 */     this.threadNum = threadNum;
/*  54 */     this.collection = collection;
/*  55 */     this.worker = worker;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute() {
/*  63 */     List<Runnable> runnables = new ArrayList<>();
/*     */     
/*  65 */     List<List<PType>> lists = CommonUtil.splitCollection(this.collection, this.threadNum);
/*  66 */     if (CommonUtil.isEmptyOrNull(lists)) {
/*     */       return;
/*     */     }
/*     */     
/*  70 */     int finalGroupCount = lists.size();
/*  71 */     final ThreadLocal<Worker> workThreadLocal = new ThreadLocal<>();
/*  72 */     workThreadLocal.set(this.worker);
/*     */     
/*  74 */     for (int i = 0; i < finalGroupCount; i++) {
/*  75 */       Runnable runnable = null;
/*     */       
/*  77 */       final List<PType> subCollection = lists.get(i);
/*  78 */       runnable = new Runnable()
/*     */         {
/*     */           
/*     */           public void run()
/*     */           {
/*     */             try {
/*  84 */               workThreadLocal.set(ParallelExecutor.this.worker);
/*     */               
/*  86 */               Worker worker = workThreadLocal.get();
/*  87 */               worker.run(subCollection);
/*  88 */             } catch (Exception e) {
/*  89 */               e.printStackTrace();
/*  90 */               MLog.info(ExceptionUtil.getExceptionDetailsMessage(e));
/*     */             } finally {
/*  92 */               if (ParallelExecutor.this.countDownLatch != null) {
/*  93 */                 ParallelExecutor.this.countDownLatch.countDown();
/*     */               }
/*     */             } 
/*     */           }
/*     */         };
/*     */ 
/*     */ 
/*     */       
/* 101 */       runnables.add(runnable);
/*     */     } 
/*     */ 
/*     */     
/* 105 */     ExecutorService executorService = new ThreadPoolExecutor(this.threadNum, this.threadNum, 3L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(this.threadNum));
/* 106 */     for (Runnable runnable : runnables) {
/* 107 */       executorService.execute(runnable);
/*     */     }
/*     */     
/* 110 */     executorService.shutdown();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 119 */     Collection<String> collection = new ArrayList<>();
/* 120 */     for (int i = 0; i < 10000; i++) {
/* 121 */       collection.add(String.valueOf(i));
/*     */     }
/* 123 */     ParallelExecutor<String> parallel = new ParallelExecutor<>(3, collection, new Worker<String>()
/*     */         {
/*     */           
/*     */           public void run(Collection<String> collection)
/*     */           {
/* 128 */             for (String str : collection) {
/* 129 */               System.out.print(str + "\n");
/*     */             }
/*     */           }
/*     */         });
/*     */     
/* 134 */     parallel.execute();
/*     */   }
/*     */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/ParallelExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */