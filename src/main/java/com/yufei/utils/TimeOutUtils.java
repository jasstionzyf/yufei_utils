/*    */ package com.yufei.utils;
/*    */ 
/*    */ import java.util.concurrent.Callable;
/*    */ import java.util.concurrent.ExecutionException;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.Executors;
/*    */ import java.util.concurrent.Future;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import java.util.concurrent.TimeoutException;
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
/*    */ public class TimeOutUtils
/*    */ {
/*    */   public static void runWithTimeout(final Runnable runnable, long timeout, TimeUnit timeUnit) throws Exception {
/* 23 */     runWithTimeout(new Callable()
/*    */         {
/*    */           public Object call() throws Exception {
/* 26 */             runnable.run();
/* 27 */             return null;
/*    */           }
/*    */         },  timeout, timeUnit);
/*    */   }
/*    */   
/*    */   public static <T> T runWithTimeout(Callable<T> callable, long timeout, TimeUnit timeUnit) throws Exception {
/* 33 */     ExecutorService executor = Executors.newSingleThreadExecutor();
/* 34 */     Future<T> future = executor.submit(callable);
/*    */     
/* 36 */     executor.shutdown();
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 42 */       return future.get(timeout, timeUnit);
/* 43 */     } catch (TimeoutException e) {
/*    */ 
/*    */       
/* 46 */       future.cancel(true);
/* 47 */       throw e;
/* 48 */     } catch (ExecutionException e) {
/*    */       
/* 50 */       Throwable t = e.getCause();
/* 51 */       if (t instanceof Error)
/* 52 */         throw (Error)t; 
/* 53 */       if (t instanceof Exception) {
/* 54 */         throw e;
/*    */       }
/* 56 */       throw new IllegalStateException(t);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void main(String[] args) throws Exception {
/* 62 */     runWithTimeout(new Callable<String>()
/*    */         {
/*    */           public String call() throws Exception
/*    */           {
/* 66 */             Thread.currentThread(); Thread.sleep(500L);
/*    */             
/* 68 */             return null;
/*    */           }
/*    */         },  1000L, TimeUnit.MILLISECONDS);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static void log(long startTime, String msg) {
/* 76 */     long elapsedSeconds = System.currentTimeMillis() - startTime;
/* 77 */     System.out.format("%1$5sms [%2$16s] %3$s\n", new Object[] { Long.valueOf(elapsedSeconds), Thread.currentThread().getName(), msg });
/*    */   }
/*    */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/TimeOutUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */