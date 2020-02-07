/*     */ package com.yufei.utils;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public class SystemCommandExecutor
/*     */ {
/*     */   private List<String> commandInformation;
/*     */   private String adminPassword;
/*     */   private ThreadedStreamHandler inputStreamHandler;
/*     */   private ThreadedStreamHandler errorStreamHandler;
/*     */   
/*     */   public SystemCommandExecutor(List<String> commandInformation) {
/*  65 */     if (commandInformation == null) throw new NullPointerException("The commandInformation is required."); 
/*  66 */     this.commandInformation = commandInformation;
/*  67 */     this.adminPassword = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int executeCommand() throws IOException, InterruptedException {
/*  73 */     int exitValue = -99;
/*     */ 
/*     */ 
/*     */     
/*  77 */     try { ProcessBuilder pb = new ProcessBuilder(this.commandInformation);
/*  78 */       Process process = pb.start();
/*     */ 
/*     */ 
/*     */       
/*  82 */       OutputStream stdOutput = process.getOutputStream();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  87 */       InputStream inputStream = process.getInputStream();
/*  88 */       InputStream errorStream = process.getErrorStream();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  93 */       this.inputStreamHandler = new ThreadedStreamHandler(inputStream, stdOutput, this.adminPassword);
/*  94 */       this.errorStreamHandler = new ThreadedStreamHandler(errorStream);
/*     */ 
/*     */       
/*  97 */       this.inputStreamHandler.start();
/*  98 */       this.errorStreamHandler.start();
/*     */ 
/*     */       
/* 101 */       exitValue = process.waitFor();
/*     */ 
/*     */       
/* 104 */       this.inputStreamHandler.interrupt();
/* 105 */       this.errorStreamHandler.interrupt();
/* 106 */       this.inputStreamHandler.join();
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
/* 122 */       return exitValue; } catch (IOException e) { throw e; } catch (InterruptedException e) { throw e; } finally { Exception exception = null; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringBuilder getStandardOutputFromCommand() {
/* 131 */     return this.inputStreamHandler.getOutputBuffer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringBuilder getStandardErrorFromCommand() {
/* 139 */     return this.errorStreamHandler.getOutputBuffer();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws IOException, InterruptedException {
/* 190 */     String query = "{\n  \"_source\": false,\n  \"query\": {\n    \"function_score\": {\n      \"query\": {\n        \"bool\": {\n          \"must\": [\n            {\n              \"payload_term\": {\n                \"prekey\": \"26025\"\n              }\n            }\n            \n          ],\n        \n          \"must_not\": [],\n          \"should\": []\n        }\n      },\n      \"script_score\": {\n        \"script\": \"((_score+1)*1.0 + 2.0/((1.469097882-(doc[\\\"uploadTime\\\"].value as double)/1000000000)*17000+1.0))/_score\"\n      }\n    }\n  },\n  \"size\":50\n} ";
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
/* 216 */     List<String> command = new ArrayList<>();
/*     */ 
/*     */     
/* 219 */     long startt = System.currentTimeMillis();
/*     */     
/* 221 */     command.add("curl");
/* 222 */     command.add("-XGET");
/* 223 */     command.add("-d");
/* 224 */     command.add(query);
/* 225 */     command.add("http://119.29.172.40:9201/vcg_creative_yufei-56g_3s_0r_2/_search");
/*     */ 
/*     */     
/* 228 */     SystemCommandExecutor commandExecutor = new SystemCommandExecutor(command);
/*     */     try {
/* 230 */       int i = commandExecutor.executeCommand();
/* 231 */     } catch (IOException|InterruptedException e) {
/*     */       
/* 233 */       e.printStackTrace();
/*     */     } 
/*     */ 
/*     */     
/* 237 */     StringBuilder stdout = commandExecutor.getStandardOutputFromCommand();
/* 238 */     StringBuilder stderr = commandExecutor.getStandardErrorFromCommand();
/* 239 */     System.out.print(stdout);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 245 */     long endt = System.currentTimeMillis();
/* 246 */     System.out.print("take " + ((endt - startt) / 1000L) + "\n");
/*     */   }
/*     */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/SystemCommandExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */