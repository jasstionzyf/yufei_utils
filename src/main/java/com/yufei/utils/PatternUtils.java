/*     */ package com.yufei.utils;
/*     */ 
/*     */ import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ 
/*     */ 
/*     */ public class PatternUtils
/*     */ {
/*  18 */   public static Log mLog = LogFactory.getLog(PatternUtils.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getStrByRegex(String content, String regexs, boolean isCleanHtml) {
/*  31 */     String str = null;
/*  32 */     List<Pattern> patters = null;
/*     */     
/*  34 */     if (regexs == null) {
/*  35 */       return null;
/*     */     }
/*     */     
/*  38 */     Pattern pattern = null;
/*  39 */     String[] temps = null;
/*  40 */     List<Pattern> patterns = null;
/*  41 */     patterns = new ArrayList<>();
/*  42 */     temps = regexs.split("!");
/*  43 */     for (String reg : temps) {
/*  44 */       pattern = Pattern.compile(reg, 2);
/*  45 */       patterns.add(pattern);
/*     */     } 
/*  47 */     str = getStrByPattern(patterns, content);
/*  48 */     if (isCleanHtml) {
/*  49 */       str = CommonUtil.cleanHtml(str);
/*     */     }
/*  51 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getStrByRegex(String content, String regexs, int position) {
/*  57 */     String str = null;
/*  58 */     List<Pattern> patters = null;
/*     */     
/*  60 */     if (regexs == null) {
/*  61 */       return null;
/*     */     }
/*     */     
/*  64 */     Pattern pattern = null;
/*  65 */     String[] temps = null;
/*  66 */     List<Pattern> patterns = null;
/*  67 */     patterns = new ArrayList<>();
/*  68 */     temps = regexs.split("!");
/*  69 */     for (String reg : temps) {
/*  70 */       pattern = Pattern.compile(reg, 2);
/*  71 */       patterns.add(pattern);
/*     */     } 
/*  73 */     str = getStrByPattern(patterns, content, position);
/*  74 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getListStrByRegex(String content, String regexs) {
/*  79 */     List<String> strs = null;
/*  80 */     List<Pattern> patters = null;
/*     */     
/*  82 */     if (regexs == null) {
/*  83 */       return null;
/*     */     }
/*     */     
/*  86 */     Pattern pattern = null;
/*  87 */     String[] temps = null;
/*  88 */     List<Pattern> patterns = null;
/*  89 */     patterns = new ArrayList<>();
/*  90 */     temps = regexs.split("!");
/*  91 */     for (String reg : temps) {
/*  92 */       pattern = Pattern.compile(reg, 2);
/*  93 */       patterns.add(pattern);
/*     */     } 
/*  95 */     strs = getListStrByPattern(patterns, content);
/*  96 */     return strs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getStrByRegex(Map<String, String> regexMap, String content, String property) {
/* 102 */     String str = null;
/* 103 */     List<Pattern> patters = null;
/* 104 */     String regexs = regexMap.get(property);
/* 105 */     if (regexs == null) {
/* 106 */       return null;
/*     */     }
/*     */     
/* 109 */     if (regexs.contains(";")) {
/* 110 */       String[] rules = regexs.split(";");
/* 111 */       content = getStrByRegex(content, rules[0], false);
/* 112 */       str = getStrByRegex(content, rules[1], 
/* 113 */           Integer.valueOf(rules[2]).intValue());
/*     */       
/* 115 */       return str;
/*     */     } 
/* 117 */     str = getStrByRegex(content, regexs, true);
/* 118 */     return str;
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
/*     */   public static String getStrByRegex(String content, String regexs) {
/* 134 */     String str = null;
/* 135 */     List<Pattern> patters = null;
/* 136 */     if (regexs == null) {
/* 137 */       return null;
/*     */     }
/*     */     
/* 140 */     if (regexs.contains("~")) {
/* 141 */       String[] rules = regexs.split("~");
/* 142 */       if (rules.length == 3) {
/* 143 */         content = getStrByRegex(content, rules[0], false);
/* 144 */         str = getStrByRegex(content, rules[1], 
/* 145 */             Integer.valueOf(rules[2]).intValue());
/*     */       } 
/* 147 */       if (rules.length == 2) {
/* 148 */         str = getStrByRegex(content, rules[0], Integer.valueOf(rules[1]).intValue());
/*     */       }
/*     */       
/* 151 */       return str;
/*     */     } 
/* 153 */     str = getStrByRegex(content, regexs, false);
/* 154 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getStrByPattern(List<Pattern> patterns, String content) {
/* 165 */     String str = null;
/* 166 */     if (patterns == null || patterns.size() == 0) {
/* 167 */       mLog.debug("正则表达式没有配置,将匹配结果设置为为空字符串！");
/*     */       
/* 169 */       return "";
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 174 */     int conti = 0;
/* 175 */     for (Pattern pattern : patterns) {
/* 176 */       if (conti == 1) {
/*     */         break;
/*     */       }
/* 179 */       Matcher matcher = pattern.matcher(content);
/*     */       
/* 181 */       int count = 0;
/*     */       
/* 183 */       if (matcher.find()) {
/* 184 */         count = matcher.groupCount();
/* 185 */         if (count == 0) {
/* 186 */           str = matcher.group(0);
/* 187 */           conti = 1;
/*     */         }
/*     */         else {
/*     */           
/* 191 */           str = matcher.group(1);
/* 192 */           conti = 1;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 198 */       if (str == null || str.equals("")) {
/* 199 */         str = "";
/* 200 */         mLog.debug("正则表达式匹配失败，正则表达式类型为：" + pattern.pattern() + "将匹配结果设置为为空字符串！");
/*     */ 
/*     */ 
/*     */         
/* 204 */         mLog.debug("*********************************************************************************");
/*     */       } 
/*     */     } 
/*     */     
/* 208 */     return str;
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
/*     */   public static String getStrByPattern(List<Pattern> patterns, String content, int position) {
/* 220 */     String str = null;
/* 221 */     if (patterns == null || patterns.size() == 0) {
/* 222 */       mLog.debug("正则表达式没有配置,将匹配结果设置为为空字符串！");
/*     */       
/* 224 */       return "";
/*     */     } 
/*     */     
/* 227 */     int conti = 0;
/* 228 */     for (Pattern pattern : patterns) {
/* 229 */       if (conti == 1) {
/*     */         break;
/*     */       }
/* 232 */       Matcher matcher = pattern.matcher(content);
/*     */       
/* 234 */       int count = 0;
/* 235 */       int po = 1;
/*     */       
/* 237 */       while (matcher.find()) {
/* 238 */         count = matcher.groupCount();
/* 239 */         if (count == 0) {
/* 240 */           str = matcher.group(0);
/*     */         }
/* 242 */         else if (po == position) {
/* 243 */           str = matcher.group(1);
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 248 */         po++;
/* 249 */         conti = 1;
/*     */       } 
/* 251 */       if (str == null || str.equals("")) {
/* 252 */         str = "";
/* 253 */         mLog.debug("正则表达式匹配失败，正则表达式类型为：" + pattern.pattern() + "将匹配结果设置为为空字符串！");
/*     */ 
/*     */ 
/*     */         
/* 257 */         mLog.debug("*********************************************************************************");
/*     */       } 
/*     */     } 
/*     */     
/* 261 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getListStrByPattern(List<Pattern> patterns, String content) {
/* 266 */     List<String> strs = new ArrayList<>();
/*     */ 
/*     */     
/* 269 */     if (patterns == null || patterns.size() == 0) {
/* 270 */       mLog.debug("正则表达式没有配置,将匹配结果设置为为空字符串！");
/*     */       
/* 272 */       return strs;
/*     */     } 
/* 274 */     int conti = 0;
/* 275 */     for (Pattern pattern : patterns) {
/* 276 */       if (conti == 1) {
/*     */         break;
/*     */       }
/* 279 */       Matcher matcher = pattern.matcher(content);
/*     */       
/* 281 */       int count = 0;
/* 282 */       String matchContent = null;
/* 283 */       while (matcher.find()) {
/* 284 */         count = matcher.groupCount();
/*     */         
/* 286 */         for (int i = 0; i < count; i++) {
/* 287 */           matchContent = matcher.group(i + 1);
/* 288 */           addUniqueElement(strs, matchContent);
/*     */         } 
/*     */ 
/*     */         
/* 292 */         if (count == 0) {
/* 293 */           matchContent = matcher.group(0);
/*     */           
/* 295 */           addUniqueElement(strs, matchContent);
/*     */         } 
/*     */         
/* 298 */         conti = 1;
/*     */       } 
/*     */     } 
/*     */     
/* 302 */     if (strs.size() == 0) {
/* 303 */       for (Pattern pattern : patterns) {
/* 304 */         mLog.debug("正则表达式匹配失败，正则表达式类型为：" + pattern.pattern());
/*     */       }
/*     */       
/* 307 */       mLog.debug("*********************************************************************************");
/*     */     } 
/*     */     
/* 310 */     return strs;
/*     */   }
/*     */   
/*     */   public static boolean match(String content, String regexes) {
/* 314 */     if (CommonUtil.isEmptyOrNull(regexes) || CommonUtil.isEmptyOrNull(content)) {
/* 315 */       throw new IllegalArgumentException();
/*     */     }
/*     */ 
/*     */     
/* 319 */     Pattern pattern = Pattern.compile(regexes);
/*     */ 
/*     */     
/* 322 */     Matcher matcher = pattern.matcher(content);
/* 323 */     return matcher.find();
/*     */   }
/*     */   
/*     */   public static <T> void addUniqueElement(List<T> list, T obj) {
/* 327 */     if (!list.contains(obj)) {
/* 328 */       list.add(obj);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String parseHtmlByAttribute(String key, String value, String source) {
/* 335 */     String matchStr = null;
/*     */     
/* 337 */     Document doc = Jsoup.parse(source);
/* 338 */     Elements elements = doc.getElementsByAttributeValue(key, value);
/* 339 */     if (elements.size() == 0) {
/* 340 */       return null;
/*     */     }
/*     */     
/* 343 */     Element element = elements.get(0);
/* 344 */     if (element == null) {
/* 345 */       return null;
/*     */     }
/* 347 */     matchStr = element.text();
/* 348 */     return matchStr;
/*     */   }
/*     */   
/*     */   public static String parseHtmlByAttributeFromElement(String key, String value, Element element) {
/* 352 */     String matchStr = null;
/* 353 */     Elements elements = element.getElementsByAttributeValue(key, value);
/* 354 */     if (elements.size() == 0) {
/* 355 */       return null;
/*     */     }
/*     */     
/* 358 */     Element element_ = elements.get(0);
/* 359 */     if (element == null) {
/* 360 */       return null;
/*     */     }
/* 362 */     matchStr = element_.text();
/* 363 */     return matchStr;
/*     */   }
/*     */   
/*     */   public static Element parseElementByAttributeFromElement(String key, String value, Element element) {
/* 367 */     String matchStr = null;
/* 368 */     Elements elements = element.getElementsByAttributeValue(key, value);
/* 369 */     if (elements.size() == 0 || elements == null) {
/* 370 */       return null;
/*     */     }
/*     */     
/* 373 */     Element element_ = elements.get(0);
/* 374 */     if (element_ == null) {
/* 375 */       return null;
/*     */     }
/*     */     
/* 378 */     return element_;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Elements getElementsByAttribute(String key, String value, String source) {
/* 383 */     Document doc = Jsoup.parse(source);
/* 384 */     Elements elements = doc.getElementsByAttributeValue(key, value);
/* 385 */     return elements;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 390 */     String url = "http://www.nba98.com/nbalx/65265.html";
/* 391 */     System.out.print(match(url, ".*nba1298.*!.*nbas.*"));
/* 392 */     String doi = "sfsdf453453sdfsdf";
/* 393 */     System.out.print(match(doi, "[\\d]{1,}"));
/*     */   }
/*     */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/PatternUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */