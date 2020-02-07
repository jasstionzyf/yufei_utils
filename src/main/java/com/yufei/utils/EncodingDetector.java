/*     */ package com.yufei.utils;
/*     */ 
/*     */ import com.google.common.base.CharMatcher;
/*     */ import com.ibm.icu.text.CharsetDetector;
/*     */ import com.ibm.icu.text.CharsetMatch;
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ 
/*     */ public class EncodingDetector
/*     */ {
/*  29 */   public static final Pattern PATTERN_ENCODING = Pattern.compile("charset=(.*?)($)", 2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   public static final Pattern PATTERN_HEAD_ENCODING = Pattern.compile("&lt;head&gt;.*?&lt;/head&gt;", 2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public static final Pattern PATTERN_HEADER = Pattern.compile("&lt;meta.*charset=(.*?)(\"|').*", 32);
/*     */   
/*     */   private class EncodingClue
/*     */   {
/*     */     private String value;
/*     */     private String source;
/*     */     private int confidence;
/*     */     
/*     */     public EncodingClue(String value, String source) {
/*  48 */       this(value, source, -1);
/*     */     }
/*     */     
/*     */     public EncodingClue(String value, String source, int confidence) {
/*  52 */       this.value = value.toLowerCase();
/*  53 */       this.source = source;
/*  54 */       this.confidence = confidence;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  58 */       return this.value + " (" + this.source + ((this.confidence >= 0) ? (", " + this.confidence + "% confidence") : "") + ")";
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isEmpty() {
/*  63 */       return (this.value == null || "".equals(this.value));
/*     */     }
/*     */     
/*     */     public boolean meetsThreshold() {
/*  67 */       return (this.confidence < 0 || (EncodingDetector.this
/*  68 */         .minConfidence >= 0 && this.confidence >= EncodingDetector.this.minConfidence));
/*     */     }
/*     */   }
/*     */   
/*  72 */   private static final Logger log = Logger.getLogger(EncodingDetector.class);
/*     */   
/*     */   public static final int NO_THRESHOLD = -1;
/*     */   
/*     */   public static final String MIN_CONFIDENCE_KEY = "block.charset.min.confidence";
/*     */   
/*  78 */   private static final HashMap<String, String> ALIASES = new HashMap<>();
/*     */   
/*  80 */   private static final HashSet<String> DETECTABLES = new HashSet<>();
/*     */   
/*     */   private static final int MIN_LENGTH = 4;
/*     */ 
/*     */   
/*     */   static {
/*  86 */     DETECTABLES.add("text/html");
/*  87 */     DETECTABLES.add("text/plain");
/*  88 */     DETECTABLES.add("text/richtext");
/*  89 */     DETECTABLES.add("text/rtf");
/*  90 */     DETECTABLES.add("text/sgml");
/*  91 */     DETECTABLES.add("text/tab-separated-values");
/*  92 */     DETECTABLES.add("text/xml");
/*  93 */     DETECTABLES.add("application/rss+xml");
/*  94 */     DETECTABLES.add("application/xhtml+xml");
/*     */ 
/*     */     
/*  97 */     ALIASES.put("EUC-KR", "x-windows-949");
/*  98 */     ALIASES.put("x-EUC-CN", "GB18030");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 103 */   private int minConfidence = 50;
/*     */   
/*     */   private CharsetDetector detector;
/*     */   
/*     */   private List<EncodingClue> clues;
/*     */ 
/*     */   
/*     */   public EncodingDetector() {
/* 111 */     this.detector = new CharsetDetector();
/* 112 */     this.clues = new ArrayList<>();
/*     */   }
/*     */ 
/*     */   
/*     */   public void autoDetectClues(byte[] data, String contentType, boolean filter) {
/* 117 */     if (this.minConfidence >= 0 && DETECTABLES.contains(contentType) && data.length > 4) {
/*     */       
/* 119 */       CharsetMatch[] matches = null;
/*     */ 
/*     */       
/*     */       try {
/* 123 */         this.detector.enableInputFilter(filter);
/* 124 */         if (data.length > 4) {
/* 125 */           this.detector.setText(data);
/* 126 */           matches = this.detector.detectAll();
/*     */         } 
/* 128 */       } catch (Exception e) {
/* 129 */         log.debug("Exception from ICU4J (ignoring): ", e);
/*     */       } 
/*     */       
/* 132 */       if (matches != null) {
/* 133 */         for (CharsetMatch match : matches)
/*     */         {
/* 135 */           addClue(match.getName(), "detect", match.getConfidence());
/*     */         }
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 141 */     addClue(parseCharacterEncoding(data, contentType), "header");
/*     */   }
/*     */   
/*     */   public void addClue(String value, String source, int confidence) {
/* 145 */     if (value == null || "".equals(value)) {
/*     */       return;
/*     */     }
/* 148 */     value = resolveEncodingAlias(value);
/* 149 */     if (value != null) {
/* 150 */       this.clues.add(new EncodingClue(value, source, confidence));
/*     */     }
/*     */   }
/*     */   
/*     */   public void addClue(String value, String source) {
/* 155 */     addClue(value, source, -1);
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
/*     */   public String guessEncoding(String baseUrl, String defaultValue) {
/* 168 */     if (log.isTraceEnabled()) {
/* 169 */       findDisagreements(baseUrl, this.clues);
/*     */     }
/*     */     
/* 172 */     EncodingClue defaultClue = new EncodingClue(defaultValue, "default");
/* 173 */     EncodingClue bestClue = defaultClue;
/*     */     
/* 175 */     for (EncodingClue clue : this.clues) {
/* 176 */       if (log.isTraceEnabled()) {
/* 177 */         log.trace(baseUrl + ": charset " + clue);
/*     */       }
/* 179 */       String charset = clue.value;
/* 180 */       if (this.minConfidence >= 0 && clue.confidence >= this.minConfidence) {
/*     */         
/* 182 */         if (log.isTraceEnabled()) {
/* 183 */           log.trace(baseUrl + ": Choosing encoding: " + charset + " with confidence " + clue
/* 184 */               .confidence);
/*     */         }
/* 186 */         return resolveEncodingAlias(charset).toLowerCase();
/* 187 */       }  if (clue.confidence == -1 && bestClue == defaultClue) {
/* 188 */         bestClue = clue;
/*     */       }
/*     */     } 
/*     */     
/* 192 */     if (log.isTraceEnabled()) {
/* 193 */       log.trace(baseUrl + ": Choosing encoding: " + bestClue);
/*     */     }
/* 195 */     return bestClue.value.toLowerCase();
/*     */   }
/*     */   
/*     */   private void findDisagreements(String url, List<EncodingClue> newClues) {
/* 199 */     HashSet<String> valsSeen = new HashSet<>();
/* 200 */     HashSet<String> sourcesSeen = new HashSet<>();
/* 201 */     boolean disagreement = false;
/* 202 */     for (EncodingClue clue : newClues) {
/* 203 */       if (!clue.isEmpty() && !sourcesSeen.contains(clue.source)) {
/* 204 */         if (valsSeen.size() > 0 && !valsSeen.contains(clue.value) && clue
/* 205 */           .meetsThreshold()) {
/* 206 */           disagreement = true;
/*     */         }
/* 208 */         if (clue.meetsThreshold()) {
/* 209 */           valsSeen.add(clue.value);
/*     */         }
/* 211 */         sourcesSeen.add(clue.source);
/*     */       } 
/*     */     } 
/* 214 */     if (disagreement) {
/* 215 */       StringBuffer sb = new StringBuffer();
/* 216 */       sb.append("Disagreement: ").append(url).append("; ");
/* 217 */       for (int i = 0; i < newClues.size(); i++) {
/* 218 */         if (i > 0) {
/* 219 */           sb.append(", ");
/*     */         }
/* 221 */         sb.append(newClues.get(i));
/*     */       } 
/* 223 */       log.trace(sb.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String resolveEncodingAlias(String encoding) {
/* 228 */     if (encoding == null || !Charset.isSupported(encoding))
/* 229 */       return null; 
/* 230 */     String canonicalName = Charset.forName(encoding).name();
/* 231 */     return ALIASES.containsKey(canonicalName) ? ALIASES.get(canonicalName) : canonicalName;
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
/*     */   public static String parseCharacterEncoding(byte[] content, String contentType) {
/* 244 */     String encoding = null;
/* 245 */     if (contentType != null && contentType.length() > 0 && !"".equals(contentType)) {
/* 246 */       Matcher match = PATTERN_ENCODING.matcher(contentType);
/* 247 */       if (match.find()) {
/* 248 */         encoding = match.group(1);
/*     */       }
/*     */     } 
/* 251 */     if (encoding == null || encoding.length() == 0 || encoding.equals("")) {
/* 252 */       String htmlContent = new String(content);
/* 253 */       Matcher match = PATTERN_HEADER.matcher(htmlContent);
/* 254 */       if (match.find()) {
/* 255 */         htmlContent = match.group();
/* 256 */         match = PATTERN_HEAD_ENCODING.matcher(htmlContent);
/* 257 */         if (match.find()) {
/* 258 */           encoding = match.group(1);
/*     */         }
/*     */       } else {
/* 261 */         match = PATTERN_HEAD_ENCODING.matcher(htmlContent);
/* 262 */         if (match.find()) {
/* 263 */           encoding = match.group(1);
/*     */         }
/*     */       } 
/*     */     } 
/* 267 */     if (encoding == null) return null; 
/* 268 */     encoding = encoding.trim();
/* 269 */     if (encoding.length() > 2 && encoding.startsWith("\"") && encoding
/* 270 */       .endsWith("\"")) {
/* 271 */       encoding = encoding.substring(1, encoding.length() - 1);
/*     */     }
/* 273 */     if (encoding.indexOf("/") > 0) {
/* 274 */       encoding = encoding.substring(0, encoding.indexOf("/"));
/*     */     }
/* 276 */     if (encoding.indexOf(">") > 0) {
/* 277 */       encoding = encoding.substring(0, encoding.indexOf(">"));
/*     */     }
/*     */     
/* 280 */     return encoding.trim();
/*     */   }
/*     */ 
/*     */   
/*     */   private static int calculateTryToRead(int totalRead) {
/* 285 */     int tryToRead = 8192;
/* 286 */     if (500000 - totalRead < 8192) {
/* 287 */       tryToRead = 500000 - totalRead;
/*     */     }
/* 289 */     return tryToRead;
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
/*     */   public static void main(String[] args) throws IOException {
/* 324 */     String str = "ntropy at lowest temperatures. (3) We predict a Cole-Cole type relation. between the real and imaginary part of the response functions for rotational. and planar shear that is occurring due to the dynamics of defects. Similar. results apply for other response functions. (4) Using the framework of glassy. dynamics, we predict low-frequency yet to be measured electro-elastic features. in defect rich 4He crystals. These predictions allow one to directly test the. ideas and very presence of glassy contributions in 4He.. .";
/* 325 */     for (String str1 : str.split(" ")) {
/* 326 */       boolean isAscii = CharMatcher.ASCII.matchesAllOf(str1);
/* 327 */       if (!isAscii) {
/* 328 */         System.out.print(str1 + "\n");
/*     */       }
/*     */     } 
/* 331 */     Character ch = new Character('¡');
/* 332 */     System.out.print(ch.charValue());
/*     */   }
/*     */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/EncodingDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */