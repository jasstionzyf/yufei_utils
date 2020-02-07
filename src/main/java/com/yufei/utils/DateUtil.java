/*     */ package com.yufei.utils;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.collections.map.HashedMap;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DateUtil
/*     */ {
/*  17 */   static final Map<String, String> monthMap = (Map<String, String>)new HashedMap();
/*     */   static {
/*  19 */     monthMap.put("January", "01");
/*  20 */     monthMap.put("February", "02");
/*     */     
/*  22 */     monthMap.put("March", "03");
/*     */     
/*  24 */     monthMap.put("April", "04");
/*     */     
/*  26 */     monthMap.put("May", "05");
/*     */     
/*  28 */     monthMap.put("June", "06");
/*     */     
/*  30 */     monthMap.put("July", "07");
/*     */     
/*  32 */     monthMap.put("August", "08");
/*     */     
/*  34 */     monthMap.put("September", "09");
/*     */     
/*  36 */     monthMap.put("October", "10");
/*  37 */     monthMap.put("November", "11");
/*     */     
/*  39 */     monthMap.put("December", "12");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String DATE_TIME_Z = "yyyy-MM-ddTHH:mm:ss.000Z";
/*     */ 
/*     */   
/*     */   public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
/*     */   
/*     */   public static final String DATE_TIME_INFO = "yyyyMMddHHmmss";
/*     */   
/*     */   public static final long dayTime = 86400000L;
/*     */   
/*     */   public static final String TIME = "HH:mm:ss";
/*     */   
/*     */   public static final String DAY = "yyyy-MM-dd";
/*     */   
/*     */   public static final String MONTH = "yyyyMM";
/*     */   
/*     */   public static final String MONTH_CH = "yyyy年MM月";
/*     */   
/*     */   public static final String DAY_CH = "yyyy年MM月dd日";
/*     */   
/*     */   public static final String DAY_SLASH = "yyyy/MM/dd";
/*     */   
/*     */   public static final long NUMBER1970_1_1 = -28800000L;
/*     */   
/*  67 */   private static final Log log = LogFactory.getLog(DateUtil.class);
/*     */ 
/*     */   
/*     */   private static SimpleDateFormat getFmt(String fmt) {
/*  71 */     return new SimpleDateFormat(fmt);
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
/*     */   public static String getDateString(Date date, String format) {
/*  83 */     if (date != null) {
/*     */       
/*  85 */       SimpleDateFormat fmt = getFmt(format);
/*  86 */       return fmt.format(date);
/*     */     } 
/*     */     
/*  89 */     return "";
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
/*     */   public static String getDateString(Date date) {
/* 101 */     SimpleDateFormat fmt = getFmt("yyyy-MM-dd HH:mm:ss");
/* 102 */     return fmt.format(date);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDateInfoString(Date date) {
/* 113 */     SimpleDateFormat fmt = getFmt("yyyyMMddHHmmss");
/* 114 */     return fmt.format(date);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getCurrentDateString() {
/* 125 */     SimpleDateFormat fmt = getFmt("yyyy-MM-dd HH:mm:ss");
/* 126 */     return fmt.format(new Date());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getCurrentDateString(String format) {
/* 137 */     SimpleDateFormat fmt = getFmt(format);
/* 138 */     return fmt.format(new Date());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Date getDate(String datet) {
/* 148 */     SimpleDateFormat fmt = getFmt("yyyy-MM-dd HH:mm:ss");
/* 149 */     Date date = null;
/*     */     
/*     */     try {
/* 152 */       date = fmt.parse(datet);
/* 153 */     } catch (ParseException e) {
/*     */ 
/*     */       
/* 156 */       log.debug("字符串转换成日期错误！ " + e.getMessage());
/*     */     } 
/* 158 */     return date;
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
/*     */   public static Date getDate(String datet, String format) {
/* 171 */     SimpleDateFormat fmt = getFmt(format);
/* 172 */     Date date = null;
/*     */     
/*     */     try {
/* 175 */       date = fmt.parse(datet);
/* 176 */     } catch (ParseException e) {
/*     */ 
/*     */       
/* 179 */       log.debug("字符串转换成日期错误！ " + e.getMessage());
/*     */     } 
/* 181 */     return date;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Date getDate(Date datet, String format) {
/* 197 */     SimpleDateFormat fmt = getFmt(format);
/* 198 */     Date result = null;
/*     */     
/*     */     try {
/* 201 */       result = fmt.parse(fmt.format(datet));
/*     */     }
/* 203 */     catch (ParseException e) {
/*     */ 
/*     */       
/* 206 */       e.printStackTrace();
/*     */     } 
/* 208 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Date getStartTimeOfDate(Date value) {
/* 215 */     Calendar cal1 = Calendar.getInstance();
/* 216 */     cal1.setTime(value);
/* 217 */     cal1.set(cal1.get(1), cal1.get(2), cal1.get(5), 0, 0, 0);
/* 218 */     return cal1.getTime();
/*     */   }
/*     */   
/*     */   public static Date getEndTimeOfDate(Date value) {
/* 222 */     Calendar cal1 = Calendar.getInstance();
/* 223 */     cal1.setTime(value);
/* 224 */     cal1.set(cal1.get(1), cal1.get(2), cal1.get(5), 23, 59, 59);
/* 225 */     return cal1.getTime();
/*     */   }
/*     */   
/*     */   public static String getMonthByStr(String str) {
/* 229 */     String month = null;
/* 230 */     for (String s : monthMap.keySet()) {
/* 231 */       if (s.toLowerCase().contains(str.toLowerCase())) {
/* 232 */         month = monthMap.get(s);
/*     */         break;
/*     */       } 
/*     */     } 
/* 236 */     return month;
/*     */   }
/*     */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/DateUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */