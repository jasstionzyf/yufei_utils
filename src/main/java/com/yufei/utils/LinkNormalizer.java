/*     */ package com.yufei.utils;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
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
/*     */ public class LinkNormalizer
/*     */ {
/*  29 */   private static Log log = LogFactory.getLog(LinkNormalizer.class);
/*  30 */   static final Map<Pattern, String> ESCAPE_PATTERNS = new HashMap<>();
/*     */   
/*     */   static {
/*  33 */     ESCAPE_PATTERNS.put(Pattern.compile("\\s"), "%20");
/*  34 */     ESCAPE_PATTERNS.put(Pattern.compile("\\^"), "%5E");
/*  35 */     ESCAPE_PATTERNS.put(Pattern.compile("\""), "%22");
/*  36 */     ESCAPE_PATTERNS.put(Pattern.compile("'"), "%27");
/*  37 */     ESCAPE_PATTERNS.put(Pattern.compile("`"), "%60");
/*  38 */     ESCAPE_PATTERNS.put(Pattern.compile("\\["), "%5B");
/*  39 */     ESCAPE_PATTERNS.put(Pattern.compile("\\]"), "%5D");
/*  40 */     ESCAPE_PATTERNS.put(Pattern.compile("\\{"), "%7B");
/*  41 */     ESCAPE_PATTERNS.put(Pattern.compile("\\}"), "%7D");
/*     */   }
/*     */   
/*  44 */   static final Map<Pattern, String> UNESCAPE_PATTERNS = new HashMap<>();
/*     */   
/*     */   static {
/*  47 */     UNESCAPE_PATTERNS.put(Pattern.compile("%20"), " ");
/*  48 */     UNESCAPE_PATTERNS.put(Pattern.compile("%5E"), "^");
/*  49 */     UNESCAPE_PATTERNS.put(Pattern.compile("%22"), "\"");
/*  50 */     UNESCAPE_PATTERNS.put(Pattern.compile("%27"), "'");
/*  51 */     UNESCAPE_PATTERNS.put(Pattern.compile("%60"), "`");
/*  52 */     UNESCAPE_PATTERNS.put(Pattern.compile("%5B"), "[");
/*  53 */     UNESCAPE_PATTERNS.put(Pattern.compile("%5D"), "]");
/*  54 */     UNESCAPE_PATTERNS.put(Pattern.compile("%7B"), "{");
/*  55 */     UNESCAPE_PATTERNS.put(Pattern.compile("%7D"), "}");
/*     */   }
/*     */ 
/*     */   
/*  59 */   protected static final Map<Pattern, String> URL_REPLACEMENTS = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  65 */     URL_REPLACEMENTS.put(Pattern.compile("[\t\n\r]"), "");
/*     */   }
/*     */ 
/*     */   
/*  69 */   protected static final Map<Pattern, String> PATH_REPLACEMENTS = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  75 */     PATH_REPLACEMENTS.put(Pattern.compile("\\\\"), "/");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  81 */     PATH_REPLACEMENTS.put(Pattern.compile("/[^/]*/\\.\\./"), "/");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     PATH_REPLACEMENTS.put(Pattern.compile("^/([\\.]{1,2}/)*"), "/");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     PATH_REPLACEMENTS.put(Pattern.compile("/([\\.]?/)*"), "/");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String normalize(String url) {
/*  99 */     if (url == null) throw new IllegalArgumentException("url is null"); 
/* 100 */     url = url.trim();
/* 101 */     for (Pattern pattern : URL_REPLACEMENTS.keySet()) {
/* 102 */       url = pattern.matcher(url).replaceAll(URL_REPLACEMENTS.get(pattern));
/*     */     }
/*     */     
/* 105 */     int queryStringIndex = url.indexOf('?');
/* 106 */     int pageAnchorIndex = url.indexOf('#');
/* 107 */     String hostTest = (queryStringIndex != -1) ? url.substring(0, queryStringIndex) : url;
/* 108 */     int hostIndex = (hostTest.indexOf("://") != -1) ? (hostTest.indexOf("://") + 3) : -1;
/* 109 */     int pathIndex = (hostIndex != -1) ? url.indexOf('/', hostIndex) : 0;
/*     */     
/* 111 */     if (pathIndex > queryStringIndex && queryStringIndex > 0) {
/* 112 */       pathIndex = -1;
/*     */     }
/* 114 */     int pathEndIndex = StrictMath.min((pageAnchorIndex != -1) ? pageAnchorIndex : url.length(), (queryStringIndex != -1) ? queryStringIndex : url.length());
/*     */     
/* 116 */     String schemaHostPort = (pathIndex != -1) ? url.substring(0, pathIndex) : url.substring(0, url.length());
/* 117 */     String path = (pathIndex != -1) ? url.substring(pathIndex, pathEndIndex) : "";
/* 118 */     String suffix = (pathEndIndex != url.length()) ? url.substring(pathEndIndex, url.length()) : "";
/*     */ 
/*     */     
/* 121 */     schemaHostPort = schemaHostPort.toLowerCase();
/* 122 */     if (schemaHostPort.startsWith("http:") && schemaHostPort.endsWith(":80"))
/* 123 */       schemaHostPort = schemaHostPort.substring(0, schemaHostPort.length() - ":80".length()); 
/* 124 */     if (schemaHostPort.startsWith("https:") && schemaHostPort.endsWith(":443")) {
/* 125 */       schemaHostPort = schemaHostPort.substring(0, schemaHostPort.length() - ":443".length());
/*     */     }
/*     */     
/* 128 */     if (path != null && !"".equals(path)) {
/* 129 */       for (Pattern pattern : PATH_REPLACEMENTS.keySet()) {
/* 130 */         path = pattern.matcher(path).replaceAll(PATH_REPLACEMENTS.get(pattern));
/*     */       }
/*     */     } else {
/* 133 */       path = "/";
/*     */     } 
/*     */     
/* 136 */     String normalized = schemaHostPort + path + suffix;
/* 137 */     if (log.isTraceEnabled())
/* 138 */       log.trace("normalize() - normalized: " + normalized + ", schemaHostPort: " + schemaHostPort + ", path: " + path + ", suffix: " + suffix + ", original url: " + url); 
/* 139 */     return normalized;
/*     */   }
/*     */ 
/*     */   
/*     */   public String escape(String url) {
/* 144 */     for (Pattern pattern : ESCAPE_PATTERNS.keySet()) {
/* 145 */       url = pattern.matcher(url).replaceAll(ESCAPE_PATTERNS.get(pattern));
/*     */     }
/* 147 */     return url;
/*     */   }
/*     */ 
/*     */   
/*     */   public String unescape(String url) {
/* 152 */     for (Pattern pattern : UNESCAPE_PATTERNS.keySet()) {
/* 153 */       url = pattern.matcher(url).replaceAll(UNESCAPE_PATTERNS.get(pattern));
/*     */     }
/* 155 */     return url;
/*     */   }
/*     */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/LinkNormalizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */