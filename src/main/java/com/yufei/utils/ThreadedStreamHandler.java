/*     */ package com.yufei.utils;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintWriter;
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
/*     */ 
/*     */ class ThreadedStreamHandler
/*     */   extends Thread
/*     */ {
/*     */   InputStream inputStream;
/*     */   String adminPassword;
/*     */   OutputStream outputStream;
/*     */   PrintWriter printWriter;
/*  45 */   StringBuilder outputBuffer = new StringBuilder();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean sudoIsRequested = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ThreadedStreamHandler(InputStream inputStream) {
/*  58 */     this.inputStream = inputStream;
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
/*     */ 
/*     */ 
/*     */   
/*     */   ThreadedStreamHandler(InputStream inputStream, OutputStream outputStream, String adminPassword) {
/*  74 */     this.inputStream = inputStream;
/*  75 */     this.outputStream = outputStream;
/*  76 */     this.printWriter = new PrintWriter(outputStream);
/*  77 */     this.adminPassword = adminPassword;
/*  78 */     this.sudoIsRequested = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*  86 */     if (this.sudoIsRequested) {
/*     */ 
/*     */       
/*  89 */       this.printWriter.println(this.adminPassword);
/*  90 */       this.printWriter.flush();
/*     */     } 
/*     */     
/*  93 */     BufferedReader bufferedReader = null;
/*     */     
/*     */     try {
/*  96 */       bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
/*  97 */       String line = null;
/*  98 */       while ((line = bufferedReader.readLine()) != null)
/*     */       {
/* 100 */         this.outputBuffer.append(line + "\n");
/*     */       }
/*     */     }
/* 103 */     catch (IOException ioe) {
/*     */ 
/*     */       
/* 106 */       ioe.printStackTrace();
/*     */     }
/* 108 */     catch (Throwable t) {
/*     */ 
/*     */       
/* 111 */       t.printStackTrace();
/*     */     } finally {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 117 */         bufferedReader.close();
/*     */       }
/* 119 */       catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void doSleep(long millis) {
/*     */     try {
/* 130 */       Thread.sleep(millis);
/*     */     }
/* 132 */     catch (InterruptedException interruptedException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringBuilder getOutputBuffer() {
/* 140 */     return this.outputBuffer;
/*     */   }
/*     */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/ThreadedStreamHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */