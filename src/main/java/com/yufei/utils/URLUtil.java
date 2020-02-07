/*     */ package com.yufei.utils;
/*     */ 
/*     */ import java.net.InetAddress;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.BitSet;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.log4j.Logger;
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
/*     */ public class URLUtil
/*     */ {
/*  23 */   private static final Logger log = Logger.getLogger(URLUtil.class);
/*     */   
/*  25 */   private static final BitSet WWW_FORM_URL = new BitSet(256);
/*     */   
/*     */   static {
/*     */     int i;
/*  29 */     for (i = 97; i <= 122; i++) {
/*  30 */       WWW_FORM_URL.set(i);
/*     */     }
/*  32 */     for (i = 65; i <= 90; i++) {
/*  33 */       WWW_FORM_URL.set(i);
/*     */     }
/*     */     
/*  36 */     for (i = 48; i <= 57; i++) {
/*  37 */       WWW_FORM_URL.set(i);
/*     */     }
/*     */ 
/*     */     
/*  41 */     WWW_FORM_URL.set(45);
/*  42 */     WWW_FORM_URL.set(95);
/*  43 */     WWW_FORM_URL.set(44);
/*  44 */     WWW_FORM_URL.set(46);
/*  45 */     WWW_FORM_URL.set(58);
/*  46 */     WWW_FORM_URL.set(42);
/*  47 */     WWW_FORM_URL.set(47);
/*  48 */     WWW_FORM_URL.set(38);
/*  49 */     WWW_FORM_URL.set(63);
/*  50 */     WWW_FORM_URL.set(37);
/*  51 */     WWW_FORM_URL.set(61);
/*  52 */     WWW_FORM_URL.set(35);
/*  53 */     WWW_FORM_URL.set(64);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  58 */   private static URLUtil instance = new URLUtil();
/*     */ 
/*     */ 
/*     */   
/*     */   public static URLUtil getInstance() {
/*  63 */     return instance;
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
/*     */   public boolean isHttp(String url) {
/* 110 */     if (StringUtils.isNotEmpty(url)) {
/* 111 */       String lowerUrl = url.toLowerCase();
/* 112 */       return lowerUrl.startsWith("http://");
/*     */     } 
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getProtocol(String url) {
/* 120 */     if (url.indexOf("://") < 0) {
/* 121 */       url = "http://" + url;
/*     */     }
/* 123 */     return url.substring(0, url.indexOf("://"));
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
/*     */   public static String getBaseUrlFromUrl(String url) {
/* 200 */     if (!url.contains("?")) {
/* 201 */       return url;
/*     */     }
/* 203 */     url = url.substring(0, url.lastIndexOf("?"));
/* 204 */     return url;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDomainFromUrl(String url) {
/* 212 */     if (url.lastIndexOf("/") == 6) {
/* 213 */       url = url + "/";
/*     */     }
/* 215 */     String domain = null;
/* 216 */     String domainRegex = "http://(.*?)/";
/* 217 */     Pattern pattern = Pattern.compile(domainRegex, 2);
/* 218 */     Matcher matcher = pattern.matcher(url);
/* 219 */     String match = null;
/*     */     
/* 221 */     while (matcher.find()) {
/* 222 */       domain = matcher.group(1);
/*     */     }
/*     */     
/* 225 */     return domain;
/*     */   }
/*     */   
/*     */   public static String formatUrlWithBaseUrl(String url, String baseUrl) {
/* 229 */     url = baseUrl + url;
/*     */     
/* 231 */     url = filterUrl(url);
/* 232 */     return url;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String formatUrl(String url, String domain) {
/* 237 */     if (url.startsWith("http://") || url.startsWith("rtsp://")) {
/* 238 */       return url;
/*     */     }
/* 240 */     if (url.indexOf("/", 0) != 0) {
/* 241 */       url = "/" + url;
/*     */     }
/* 243 */     url = "http://" + domain + url;
/*     */     
/* 245 */     url = filterUrl(url);
/* 246 */     return url;
/*     */   }
/*     */   
/*     */   private static String filterUrl(String url) {
/* 250 */     String[] filterdStrs = { "amp;", " " };
/* 251 */     for (String str : filterdStrs) {
/* 252 */       url = url.replace(str, "");
/*     */     }
/* 254 */     return url;
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
/*     */   public static void main(String[] args) {
/* 274 */     String url = "http://www.iteye.com/sg/dg/dg/dg//dg/dg";
/* 275 */     System.out.println(getDomainFromUrl(url));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getIpFromDomain(String domainName) {
/* 281 */     String ip = null;
/*     */     try {
/* 283 */       InetAddress ad = InetAddress.getByName(domainName);
/* 284 */       ip = ad.getHostAddress();
/* 285 */     } catch (UnknownHostException e) {
/*     */       
/* 287 */       if (log.isDebugEnabled()) {
/* 288 */         log.debug("请检查域名设置是否正确！");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 293 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String cleanHtml(String about) {
/* 300 */     return CommonUtil.cleanHtml(about);
/*     */   }
/*     */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/URLUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */