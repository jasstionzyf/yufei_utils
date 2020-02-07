/*      */ package com.yufei.utils;
/*      */

import com.google.common.base.Preconditions;
import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.impl.dv.util.Base64;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */ 
/*      */ public class CommonUtil {
/*   37 */   static Log mLog = LogFactory.getLog(CommonUtil.class);
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String encodingPattern = "charset=(.*?)\"";
/*      */ 
/*      */ 
/*      */   
/*      */   public static synchronized void addExtTerm(String term) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public static void serializeObj(Object obj, String filePath) throws IOException {
/*   50 */     FileOutputStream fos = null;
/*      */     
/*   52 */     ObjectOutputStream ooS = null;
/*      */ 
/*      */     
/*      */     try {
/*   56 */       fos = new FileOutputStream(filePath);
/*      */       
/*   58 */       ooS = new ObjectOutputStream(fos);
/*      */       
/*   60 */       ooS.writeObject(obj);
/*      */     }
/*   62 */     catch (Exception ex) {
/*      */       
/*   64 */       ex.printStackTrace();
/*      */     }
/*      */     finally {
/*      */       
/*   68 */       if (fos != null) {
/*   69 */         fos.close();
/*      */       }
/*      */       
/*   72 */       if (ooS != null)
/*      */       {
/*   74 */         ooS.close();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object deSerializeObj(String filePath) throws IOException {
/*   83 */     Object serializeResult = null;
/*   84 */     FileInputStream fiStream = null;
/*      */     
/*   86 */     ObjectInputStream oiStream = null;
/*      */ 
/*      */     
/*      */     try {
/*   90 */       fiStream = new FileInputStream(filePath);
/*      */       
/*   92 */       oiStream = new ObjectInputStream(fiStream);
/*      */       
/*   94 */       serializeResult = oiStream.readObject();
/*      */     }
/*   96 */     catch (Exception ex) {
/*      */       
/*   98 */       ex.printStackTrace();
/*      */     }
/*      */     finally {
/*      */       
/*  102 */       if (fiStream != null) {
/*  103 */         fiStream.close();
/*      */       }
/*      */       
/*  106 */       if (oiStream != null)
/*      */       {
/*  108 */         oiStream.close();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  113 */     return serializeResult;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Object deSerializeObj(InputStream fiStream) throws IOException {
/*  118 */     Object serializeResult = null;
/*      */     
/*  120 */     ObjectInputStream oiStream = null;
/*      */ 
/*      */     
/*      */     try {
/*  124 */       oiStream = new ObjectInputStream(fiStream);
/*      */       
/*  126 */       serializeResult = oiStream.readObject();
/*      */     }
/*  128 */     catch (Exception ex) {
/*      */       
/*  130 */       ex.printStackTrace();
/*      */     }
/*      */     finally {
/*      */       
/*  134 */       if (fiStream != null) {
/*  135 */         fiStream.close();
/*      */       }
/*      */       
/*  138 */       if (oiStream != null)
/*      */       {
/*  140 */         oiStream.close();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  145 */     return serializeResult;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setPropertyForEntity(Object entity, Object value, String propertyName) {
/*      */     try {
/*  160 */       BeanUtils.setProperty(entity, propertyName, value);
/*  161 */     } catch (Exception e) {
/*      */       
/*  163 */       mLog.info("对属性:" + propertyName + "设置值:" + value + "出错!");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String generateXmlStrForObject(Object obj, Class cla) {
/*  175 */     Writer outputWriter = new StringWriter();
/*      */     
/*      */     try {
/*  178 */       JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { cla });
/*  179 */       Marshaller marshaller = jaxbContext.createMarshaller();
/*  180 */       marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
/*  181 */       marshaller.marshal(obj, outputWriter);
/*  182 */     } catch (Exception e) {
/*      */       
/*  184 */       throw new RuntimeException(e);
/*      */     } 
/*  186 */     return outputWriter.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isMediaType(String fieldName) {
/*  199 */     if (fieldName.endsWith("Media")) {
/*  200 */       return true;
/*      */     }
/*      */     
/*  203 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getStringFromFile(File file) {
/*  214 */     String str = null;
/*      */ 
/*      */ 
/*      */     
/*  218 */     if (file != null) {
/*      */       
/*      */       try {
/*  221 */         byte[] bytes = IOUtils.translantStreamToByte(new FileInputStream(file));
/*  222 */         str = IOUtils.translantByteStreamToString(bytes, "utf-8");
/*  223 */       } catch (FileNotFoundException e) {
/*      */         
/*  225 */         mLog.debug(file.getAbsoluteFile() + ": not found!");
/*      */       }
/*  227 */       catch (Exception e) {
/*      */         
/*  229 */         mLog.debug(e.getMessage());
/*      */       } 
/*      */     }
/*      */     
/*  233 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Method getMethod(Class obj, String methodName) {
/*  245 */     Method[] methods = obj.getMethods();
/*  246 */     for (Method method1 : methods) {
/*  247 */       if (method1.getName().equals(methodName))
/*      */       {
/*  249 */         return method1;
/*      */       }
/*      */     } 
/*      */     
/*  253 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getBaseUrl(String urlStr) {
/*  262 */     urlStr = formatUrl(urlStr);
/*  263 */     String baseUrl = null;
/*  264 */     if (urlStr.startsWith("http") && 
/*  265 */       urlStr.contains("?")) {
/*  266 */       baseUrl = urlStr.substring(0, urlStr.indexOf("?"));
/*      */     }
/*      */ 
/*      */     
/*  270 */     return baseUrl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getCharNumber(char ch, String sourceString) {
/*  281 */     int charNumber = 0;
/*  282 */     char[] charArray = sourceString.toCharArray();
/*  283 */     for (char ch1 : charArray) {
/*  284 */       if (ch1 == ch) {
/*  285 */         charNumber++;
/*      */       }
/*      */     } 
/*  288 */     return charNumber;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String replaceByIndex(String sourceStr, String targetString, String[] values) {
/*  302 */     StringBuffer stringBuffer = new StringBuffer();
/*  303 */     String[] strs = sourceStr.split(targetString);
/*  304 */     int position = 0;
/*  305 */     for (String str : strs) {
/*  306 */       if (position < values.length) {
/*  307 */         stringBuffer.append(str).append(values[position]);
/*      */       } else {
/*  309 */         stringBuffer.append(str);
/*      */       } 
/*  311 */       position++;
/*      */     } 
/*      */     
/*  314 */     return stringBuffer.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String subString(char ch, int position, String sourceString) {
/*  325 */     StringBuffer buffer = new StringBuffer();
/*  326 */     int tempPosition = 0;
/*  327 */     char[] chars = sourceString.toCharArray();
/*  328 */     for (char ch1 : chars) {
/*  329 */       if (tempPosition == position) {
/*      */         break;
/*      */       }
/*  332 */       buffer.append(ch1);
/*  333 */       if (ch1 == ch) {
/*  334 */         tempPosition++;
/*      */       }
/*      */     } 
/*  337 */     return buffer.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String normalizeUrl(String url, String upUrl) {
/*  346 */     url = formatUrl(url);
/*  347 */     upUrl = formatUrl(upUrl);
/*      */     
/*  349 */     String returnUrl = null;
/*  350 */     if (url.startsWith("http")) {
/*  351 */       returnUrl = url;
/*  352 */       return returnUrl;
/*      */     } 
/*      */     
/*  355 */     if (url.startsWith("//")) {
/*  356 */       returnUrl = "http:" + url;
/*  357 */       return returnUrl;
/*      */     } 
/*      */     
/*  360 */     if (!url.contains("/")) {
/*  361 */       returnUrl = "http://" + DomainUtil.getSite(upUrl) + "/" + url;
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  366 */     else if (url.startsWith("/") || url.startsWith("../")) {
/*  367 */       url = url.replace("../", "");
/*  368 */       url = url.replaceFirst("/", "");
/*      */       
/*  370 */       returnUrl = subString('/', 3, upUrl) + url;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  397 */     return returnUrl;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String formatUrl(String url) {
/*  406 */     String[] filterdStrs = { "amp;", " " };
/*  407 */     for (String str : filterdStrs) {
/*  408 */       url = url.replace(str, "");
/*      */     }
/*  410 */     if (url.contains("#")) {
/*  411 */       url = url.substring(0, url.indexOf("#"));
/*      */     }
/*  413 */     return url;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main(String[] args) {
/*  447 */     List<String> strs = groupIndexByRange(101304, 1000);
/*  448 */     for (String str : strs) {
/*  449 */       System.out.print(str + "\n");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String upFirstChar(String str) {
/*  460 */     char temp = str.charAt(0);
/*  461 */     temp = Character.toUpperCase(temp);
/*  462 */     str = str.replaceFirst(String.valueOf(str.charAt(0)), 
/*  463 */         String.valueOf(temp));
/*  464 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String downFirstChar(String str) {
/*  475 */     char temp = str.charAt(0);
/*  476 */     temp = Character.toLowerCase(temp);
/*  477 */     str = str.replaceFirst(String.valueOf(str.charAt(0)), 
/*  478 */         String.valueOf(temp));
/*  479 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String cleanHtml(String about) {
/*  491 */     String[] regexs = { "\"", "'", "<.*?>", "'", "<", ">", "nbsp", "[\\s\\r\\n]{1,}" };
/*      */     
/*  493 */     Pattern p = null;
/*  494 */     Matcher match = null;
/*  495 */     for (String regex : regexs) {
/*  496 */       p = Pattern.compile(regex, 32);
/*  497 */       match = p.matcher(about);
/*  498 */       if (match.find()) {
/*  499 */         about = match.replaceAll("");
/*      */       }
/*      */     } 
/*  502 */     return about.trim();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isValidUrl(String url) {
/*  516 */     if (isEmptyOrNull(url)) {
/*  517 */       return false;
/*      */     }
/*      */     
/*  520 */     if (url.endsWith(".js") || url.endsWith(".css")) {
/*  521 */       return false;
/*      */     }
/*  523 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T> List<T> convertToList(T[] strs) {
/*  533 */     List<T> list = new ArrayList<>();
/*  534 */     for (T str : strs) {
/*  535 */       list.add(str);
/*      */     }
/*  537 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void deleteFile(File file) {
/*  546 */     if (file.isFile()) {
/*  547 */       file.delete();
/*      */     }
/*  549 */     if (file.isDirectory()) {
/*      */       
/*  551 */       for (File file1 : file.listFiles()) {
/*  552 */         deleteFile(file1);
/*      */       }
/*      */ 
/*      */       
/*  556 */       file.delete();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void cleanSvnFolder(String directory) {
/*  566 */     File file = new File(directory);
/*  567 */     File[] files = file.listFiles();
/*  568 */     for (File tempFile : files) {
/*  569 */       if (tempFile.isDirectory()) {
/*  570 */         if (tempFile.getName().endsWith(".svn")) {
/*  571 */           deleteFile(tempFile);
/*      */         } else {
/*  573 */           cleanSvnFolder(tempFile.getAbsolutePath() + "\\");
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static void batchReplaceTextUnderFile(String directory) throws Exception, Exception {
/*  592 */     String[] acceptFileNames = { ".java" };
/*  593 */     File file = new File(directory);
/*  594 */     File[] files = file.listFiles();
/*  595 */     for (File tempFile : files) {
/*  596 */       if (tempFile.isDirectory()) {
/*  597 */         batchReplaceTextUnderFile(tempFile.getAbsolutePath() + "\\");
/*      */       
/*      */       }
/*  600 */       else if (tempFile.getName().endsWith(".java")) {
/*  601 */         String className = null;
/*  602 */         File file1 = tempFile;
/*  603 */         String classFullName = tempFile.getName();
/*  604 */         className = classFullName.substring(0, classFullName
/*  605 */             .indexOf("."));
/*  606 */         file1 = new File(directory + classFullName);
/*  607 */         String fileContent = IOUtils.translantByteStreamToString(
/*  608 */             IOUtils.translantStreamToByte(new FileInputStream(file1)), "utf-8");
/*      */         
/*  610 */         Pattern pattern = Pattern.compile("public[\\s]{1,}enum[\\s]{1,}(.*?)[\\s]{1,}\\{", 2);
/*      */ 
/*      */         
/*  613 */         List<Pattern> patterns = new ArrayList<>();
/*  614 */         patterns.add(pattern);
/*  615 */         List<String> classNames = new ArrayList<>();
/*  616 */         classNames = PatternUtils.getListStrByPattern(patterns, fileContent);
/*      */         
/*  618 */         for (String className1 : classNames) {
/*  619 */           String regex = "public [\\s]{0,}" + className1;
/*      */           
/*  621 */           fileContent = fileContent.replaceAll(regex, "private " + className1);
/*      */         } 
/*      */ 
/*      */         
/*  625 */         file.delete();
/*  626 */         FileOutputStream ref = new FileOutputStream(directory + classFullName);
/*      */         
/*  628 */         ref.write(fileContent.getBytes());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object getObjectFromXml(InputStream in, Class cla) {
/*  643 */     Object obj = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  649 */       JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { cla });
/*  650 */       Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
/*  651 */       obj = unmarshaller.unmarshal(in);
/*  652 */     } catch (Exception e) {
/*      */       
/*  654 */       mLog.error("reading Xml wrong!" + ExceptionUtil.getExceptionDetailsMessage(e) + "");
/*  655 */       throw new RuntimeException(e);
/*      */     } 
/*      */     
/*  658 */     return obj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String native2Ascii(String str) {
/*  669 */     char[] chars = str.toCharArray();
/*      */     
/*  671 */     StringBuilder sb = new StringBuilder();
/*      */     
/*  673 */     for (int i = 0; i < chars.length; i++)
/*      */     {
/*  675 */       sb.append(char2Ascii(chars[i]));
/*      */     }
/*      */ 
/*      */     
/*  679 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static String char2Ascii(char c) {
/*  685 */     if (c > 'ÿ') {
/*      */       
/*  687 */       StringBuilder sb = new StringBuilder();
/*      */       
/*  689 */       sb.append("\\u");
/*      */       
/*  691 */       int code = c >> 8;
/*      */       
/*  693 */       String tmp = Integer.toHexString(code);
/*      */       
/*  695 */       if (tmp.length() == 1)
/*      */       {
/*  697 */         sb.append("0");
/*      */       }
/*      */ 
/*      */       
/*  701 */       sb.append(tmp);
/*      */       
/*  703 */       code = c & 0xFF;
/*      */       
/*  705 */       tmp = Integer.toHexString(code);
/*      */       
/*  707 */       if (tmp.length() == 1)
/*      */       {
/*  709 */         sb.append("0");
/*      */       }
/*      */ 
/*      */       
/*  713 */       sb.append(tmp);
/*      */       
/*  715 */       return sb.toString();
/*      */     } 
/*      */ 
/*      */     
/*  719 */     return Character.toString(c);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getStrFromInputStream(InputStream inputStream, String defaultEncoding) {
/*  733 */     if (inputStream == null) {
/*  734 */       return null;
/*      */     }
/*      */     
/*  737 */     byte[] translantStreamToByte = IOUtils.translantStreamToByte(inputStream);
/*      */     
/*  739 */     int usedBytesNumber = 5000;
/*  740 */     if (translantStreamToByte.length > 5000) {
/*  741 */       usedBytesNumber = 5000;
/*      */     } else {
/*  743 */       usedBytesNumber = translantStreamToByte.length;
/*      */     } 
/*  745 */     if (isEmptyOrNull(defaultEncoding))
/*      */     {
/*  747 */       if (isValidUtf8(translantStreamToByte, usedBytesNumber)) {
/*  748 */         defaultEncoding = "utf-8";
/*      */       } else {
/*  750 */         defaultEncoding = "GBK";
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  755 */     return IOUtils.translantByteStreamToString(translantStreamToByte, defaultEncoding);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String generateXmlForObject(Object obj, Class cla) {
/*  768 */     Writer outputWriter = new StringWriter();
/*      */     
/*      */     try {
/*  771 */       JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { cla });
/*  772 */       Marshaller marshaller = jaxbContext.createMarshaller();
/*  773 */       marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
/*  774 */       marshaller.marshal(obj, outputWriter);
/*  775 */     } catch (Exception e) {
/*      */       
/*  777 */       throw new RuntimeException(e);
/*      */     } 
/*  779 */     return outputWriter.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object getObjectFromXml(StringReader in, Class cla) {
/*  791 */     Object obj = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  797 */       JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { cla });
/*  798 */       Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
/*  799 */       obj = unmarshaller.unmarshal(in);
/*  800 */     } catch (Exception e) {
/*      */       
/*  802 */       mLog.error("reading Xml wrong!");
/*  803 */       throw new RuntimeException(e);
/*      */     } 
/*      */     
/*  806 */     return obj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getPercentageOfReallyCInHtml(String htmlContent) {
/*  815 */     double percentage = 0.0D;
/*  816 */     int totalCount = 0, contentCount = 0;
/*  817 */     totalCount = htmlContent.length();
/*  818 */     htmlContent = htmlContent.replaceAll("<.*>", "");
/*      */     
/*  820 */     return percentage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double calculateSimilartity(List<Float> data1, List<Float> data2) {
/*  831 */     if (data1 == null || data2 == null || data1.size() != data2.size()) {
/*  832 */       throw new IllegalArgumentException();
/*      */     }
/*  834 */     double similartity = 0.0D;
/*  835 */     Float value1 = Float.valueOf(0.0F), value2 = Float.valueOf(0.0F), value3 = Float.valueOf(0.0F);
/*  836 */     for (int i = 0; i < data1.size(); i++) {
/*  837 */       value1 = Float.valueOf(value1.floatValue() + ((Float)data1.get(i)).floatValue() * ((Float)data2.get(i)).floatValue());
/*  838 */       value2 = Float.valueOf(value2.floatValue() + ((Float)data1.get(i)).floatValue() * ((Float)data1.get(i)).floatValue());
/*  839 */       value3 = Float.valueOf(value3.floatValue() + ((Float)data2.get(i)).floatValue() * ((Float)data2.get(i)).floatValue());
/*      */     } 
/*  841 */     similartity = value1.floatValue() / Math.sqrt((value2.floatValue() * value3.floatValue()));
/*      */     
/*  843 */     return similartity;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double calculateSimilartityByInteger(List<Integer> data1, List<Integer> data2) {
/*  854 */     if (data1 == null || data2 == null || data1.size() != data2.size()) {
/*  855 */       throw new IllegalArgumentException();
/*      */     }
/*  857 */     double similartity = 0.0D;
/*  858 */     Float value1 = Float.valueOf(0.0F), value2 = Float.valueOf(0.0F), value3 = Float.valueOf(0.0F);
/*  859 */     for (int i = 0; i < data1.size(); i++) {
/*  860 */       value1 = Float.valueOf(value1.floatValue() + ((Integer)data1.get(i)).floatValue() * ((Integer)data2.get(i)).floatValue());
/*  861 */       value2 = Float.valueOf(value2.floatValue() + (((Integer)data1.get(i)).intValue() * ((Integer)data1.get(i)).intValue()));
/*  862 */       value3 = Float.valueOf(value3.floatValue() + (((Integer)data2.get(i)).intValue() * ((Integer)data2.get(i)).intValue()));
/*      */     } 
/*  864 */     similartity = value1.floatValue() / Math.sqrt((value2.floatValue() * value3.floatValue()));
/*      */     
/*  866 */     return similartity;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<String, Integer> getVectorofString(String sourceStr) {
/*  875 */     char[] chars = sourceStr.toCharArray();
/*  876 */     Map<String, Integer> charCountMap_str = new HashMap<>();
/*  877 */     String temp = null;
/*  878 */     for (char ch : chars) {
/*  879 */       temp = String.valueOf(ch);
/*  880 */       if (charCountMap_str.get(temp) == null) {
/*  881 */         charCountMap_str.put(temp, Integer.valueOf(1));
/*      */       } else {
/*  883 */         charCountMap_str.put(temp, Integer.valueOf(((Integer)charCountMap_str.get(temp)).intValue() + 1));
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  888 */     return charCountMap_str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Integer>[] getVectorofString(String str, String targetStr) {
/*  897 */     ArrayList[] arrayOfArrayList = new ArrayList[2];
/*  898 */     if (str == null || targetStr == null) {
/*  899 */       throw new RuntimeException();
/*      */     }
/*  901 */     Map<String, Integer> charCountMap_str = new HashMap<>();
/*  902 */     Map<String, Integer> charCountMap_targetStr = new HashMap<>();
/*      */     
/*  904 */     char[] targetChars = targetStr.toCharArray();
/*  905 */     char[] chars = str.toCharArray();
/*      */     
/*  907 */     String temp = null;
/*      */ 
/*      */     
/*  910 */     char[] allChars = (str + targetStr).toCharArray();
/*  911 */     for (char ch : allChars) {
/*  912 */       temp = String.valueOf(ch);
/*      */       
/*  914 */       charCountMap_str.put(temp, Integer.valueOf(0));
/*  915 */       charCountMap_targetStr.put(temp, Integer.valueOf(0));
/*      */     } 
/*      */ 
/*      */     
/*  919 */     for (char ch : chars) {
/*  920 */       temp = String.valueOf(ch);
/*      */       
/*  922 */       charCountMap_str.put(temp, Integer.valueOf(((Integer)charCountMap_str.get(temp)).intValue() + 1));
/*      */     } 
/*      */     
/*  925 */     for (char ch : targetChars) {
/*  926 */       temp = String.valueOf(ch);
/*      */       
/*  928 */       charCountMap_targetStr.put(temp, 
/*  929 */           Integer.valueOf(((Integer)charCountMap_targetStr.get(temp)).intValue() + 1));
/*      */     } 
/*      */ 
/*      */     
/*  933 */     if (charCountMap_targetStr.get("/") != null && charCountMap_str
/*  934 */       .get("/") != null && 
/*  935 */       charCountMap_targetStr.get("/") != charCountMap_str.get("/")) {
/*  936 */       charCountMap_targetStr.put("/", Integer.valueOf(0));
/*  937 */       charCountMap_str.put("/", Integer.valueOf(100));
/*      */     } 
/*      */ 
/*      */     
/*  941 */     arrayOfArrayList[0] = new ArrayList(charCountMap_str.values());
/*  942 */     arrayOfArrayList[1] = new ArrayList(charCountMap_targetStr.values());
/*      */     
/*  944 */     return (List<Integer>[])arrayOfArrayList;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String removeSpace(String str) {
/*  954 */     str = str.replaceAll("\r", "");
/*  955 */     str = str.replaceAll("\n", "");
/*  956 */     str = str.replaceAll(" ", "");
/*      */     
/*  958 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String[]> getDKL(List<String[]> strs) {
/*  967 */     if (strs.size() > 1) {
/*  968 */       String[] temp = new String[((String[])strs.get(0)).length * ((String[])strs.get(1)).length];
/*  969 */       int index0 = 0;
/*      */       
/*  971 */       for (String str0 : (String[])strs.get(0)) {
/*      */         
/*  973 */         for (String str1 : (String[])strs.get(1)) {
/*  974 */           temp[index0] = str0 + "@" + str1;
/*  975 */           index0++;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  981 */       strs.remove(0);
/*  982 */       strs.remove(0);
/*      */       
/*  984 */       strs.add(0, temp);
/*  985 */       return getDKL(strs);
/*      */     } 
/*  987 */     List<String[]> returnStrs = (List)new ArrayList<>();
/*  988 */     String[] temp0 = null;
/*  989 */     for (String s : (String[])strs.get(0)) {
/*      */       
/*  991 */       temp0 = s.split("@");
/*  992 */       returnStrs.add(temp0);
/*      */     } 
/*      */     
/*  995 */     return returnStrs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<String, Double> getEigenvector(List<String> demoTexts) {
/* 1004 */     DecimalFormat df2 = new DecimalFormat("###.00");
/*      */ 
/*      */     
/* 1007 */     Map<String, Double> vectorMap = new HashMap<>();
/*      */     
/* 1009 */     List<Map<String, Integer>> charNumberMaps = new ArrayList<>();
/* 1010 */     for (String str : demoTexts) {
/* 1011 */       charNumberMaps.add(getVectorofString(str));
/*      */     }
/* 1013 */     boolean charIsAllOwned = false;
/* 1014 */     float i = 0.0F;
/*      */     
/* 1016 */     Map<String, Integer> tempMap = charNumberMaps.get(0);
/* 1017 */     mLog.info("抽取字符的文本内容为：" + (String)demoTexts.get(0));
/* 1018 */     Iterator<String> charIterator = tempMap.keySet().iterator();
/*      */     
/* 1020 */     Double chCount = null;
/*      */     
/* 1022 */     float chSumCount = 0.0F, mapNumber = demoTexts.size();
/* 1023 */     while (charIterator.hasNext()) {
/* 1024 */       charIsAllOwned = false;
/* 1025 */       chSumCount = 0.0F;
/*      */       
/* 1027 */       i = 0.0F;
/* 1028 */       String ch = charIterator.next();
/* 1029 */       for (Map<String, Integer> map : charNumberMaps) {
/* 1030 */         if (map.keySet().contains(ch)) {
/* 1031 */           i++;
/* 1032 */           chSumCount += ((Integer)map.get(ch)).intValue();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1053 */     return vectorMap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String convertHtmlCharacter(String htmlStr) {
/* 1062 */     String[] character = { "&", "<", ">" }, updateStrs = { "&amp;", "&lt;", "&gt;" };
/*      */ 
/*      */     
/* 1065 */     int length = character.length;
/* 1066 */     for (int i = 0; i < length; i++) {
/* 1067 */       htmlStr = htmlStr.replaceAll(character[i], updateStrs[i]);
/*      */     }
/*      */     
/* 1070 */     return htmlStr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static boolean isEmptyOrNull(Collection collection) {
/* 1081 */     return (collection == null || collection.size() == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static boolean isEmptyOrNull(String str) {
/* 1092 */     return (str == null || str.trim().length() == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean ifFieldHasGetAndSetMethod(Class classz, Field field) {
/* 1103 */     Method[] methods = classz.getMethods();
/* 1104 */     String getMethodName = null, setMethodName = null;
/* 1105 */     int i = 0;
/* 1106 */     getMethodName = "get" + upFirstChar(field.getName());
/* 1107 */     setMethodName = "set" + upFirstChar(field.getName());
/* 1108 */     for (Method method : methods) {
/*      */       
/* 1110 */       if (getMethodName.equals(method.getName()) || setMethodName
/* 1111 */         .equals(method.getName())) {
/* 1112 */         i++;
/*      */       }
/* 1114 */       if (i == 2) {
/* 1115 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1119 */     return false;
/*      */   }
/*      */   
/*      */   private static void getInterfaceOfCla(Class cla, List<Class<?>> interfaces) {
/* 1123 */     Class<?>[] interfaces1 = cla.getInterfaces();
/* 1124 */     if (interfaces1.length == 0) {
/*      */       return;
/*      */     }
/* 1127 */     for (Class<?> class1 : interfaces1) {
/* 1128 */       interfaces.add(class1);
/* 1129 */       getInterfaceOfCla(class1, interfaces);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<Class<?>> getALlInterfaces(Class clas) {
/* 1141 */     List<Class<?>> interfaces = new ArrayList<>();
/* 1142 */     getInterfaceOfCla(clas, interfaces);
/*      */     
/* 1144 */     return interfaces;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Field getFieldByFieldName(Class classz, String fieldName) {
/* 1155 */     List<Field> fields = getAllDeclareFields(classz);
/* 1156 */     for (Field field0 : fields) {
/* 1157 */       if (field0.getName().equalsIgnoreCase(fieldName)) {
/* 1158 */         return field0;
/*      */       }
/*      */     } 
/* 1161 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <T> List<Field> getAllDeclareFields(Class<T> classz) {
/* 1170 */     List<Field> fields = new ArrayList<>();
/* 1171 */     Field[] fields1 = null;
/*      */     
/* 1173 */     for (; classz != Object.class; classz = (Class)classz.getSuperclass()) {
/* 1174 */       fields1 = classz.getDeclaredFields();
/*      */       
/* 1176 */       for (Field field : fields1) {
/*      */         
/* 1178 */         if (ifFieldHasGetAndSetMethod(classz, field)) {
/* 1179 */           fields.add(field);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1187 */     return fields;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object[] sort(Object[] results) throws Exception {
/* 1197 */     if (results == null) {
/* 1198 */       throw new IllegalArgumentException();
/*      */     }
/*      */     try {
/* 1201 */       Arrays.sort(results);
/* 1202 */     } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */     
/* 1206 */     return results;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void generateXmlForObject(Object obj, Class cla, String path) throws IOException {
/* 1217 */     String tempDirectory = path;
/* 1218 */     String fileName = System.currentTimeMillis() + ".xml";
/* 1219 */     File file = new File(tempDirectory + fileName);
/* 1220 */     if (!file.exists()) {
/* 1221 */       file.createNewFile();
/*      */     }
/*      */     try {
/* 1224 */       JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { cla });
/* 1225 */       Marshaller marshaller = jaxbContext.createMarshaller();
/* 1226 */       marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
/* 1227 */       marshaller.marshal(obj, file);
/* 1228 */     } catch (Exception e) {
/*      */       
/* 1230 */       throw new RuntimeException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void generateXmlForObjectA(Object obj, Class cla, String path) throws IOException {
/* 1244 */     File file = new File(path);
/* 1245 */     if (!file.exists()) {
/* 1246 */       file.createNewFile();
/*      */     } else {
/* 1248 */       file.delete();
/*      */     } 
/*      */     try {
/* 1251 */       JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { cla });
/* 1252 */       Marshaller marshaller = jaxbContext.createMarshaller();
/* 1253 */       marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
/* 1254 */       marshaller.marshal(obj, file);
/* 1255 */     } catch (Exception e) {
/*      */       
/* 1257 */       throw new RuntimeException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isCustomerObjType(Class<?> type) {
/* 1266 */     if (type.isPrimitive()) {
/* 1267 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1271 */     boolean b1 = (isCollection(type) || type.isAssignableFrom(String.class) || type.isAssignableFrom(Date.class));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1276 */     boolean b2 = (type.isAssignableFrom(Double.class) || type.isAssignableFrom(Character.class) || type.isAssignableFrom(Long.class) || type.isAssignableFrom(Float.class) || type.isAssignableFrom(Integer.class) || type.isEnum());
/*      */     
/* 1278 */     if (b1 || b2) {
/* 1279 */       return false;
/*      */     }
/* 1281 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isCustomerObjType(Field property) {
/* 1291 */     Class<?> type = property.getType();
/* 1292 */     if (type.isPrimitive()) {
/* 1293 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1297 */     boolean b1 = (isCollection(type) || type.isAssignableFrom(String.class) || type.isAssignableFrom(Date.class));
/*      */ 
/*      */ 
/*      */     
/* 1301 */     boolean b2 = (type.isAssignableFrom(Character.class) || type.isAssignableFrom(Long.class) || type.isAssignableFrom(Float.class) || type.isAssignableFrom(Integer.class) || type.isEnum());
/*      */     
/* 1303 */     if (b1 || b2) {
/* 1304 */       return false;
/*      */     }
/* 1306 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isCollection(Class<?> type) {
/* 1316 */     if ((type.getInterfaces()).length == 0) {
/* 1317 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1324 */     List<Class<?>> interfaces = getALlInterfaces(type);
/* 1325 */     if (interfaces.contains(Collection.class)) {
/* 1326 */       return true;
/*      */     }
/* 1328 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int[] getRGBFromPixel(int pixel) {
/* 1337 */     int[] colorValues = new int[3];
/* 1338 */     colorValues[0] = (pixel & 0xFF0000) >> 16;
/* 1339 */     colorValues[1] = (pixel & 0xFF00) >> 8;
/* 1340 */     colorValues[2] = pixel & 0xFF;
/* 1341 */     return colorValues;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getPixelFromRGB(int red, int green, int blue) {
/* 1352 */     return (red << 16) + (green << 8) + blue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double getFiguresSimilartity() {
/* 1361 */     double similartity = 0.0D;
/* 1362 */     return similartity;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object objectConvert(Class<?> targetCla, Object value) {
/* 1375 */     if (value == null) {
/* 1376 */       return null;
/*      */     }
/*      */     
/* 1379 */     mLog.debug("此次转换目标类为：" + targetCla.getName() + ";要转换的值的类型为：" + value.getClass().getName());
/* 1380 */     Object object = value;
/*      */     
/* 1382 */     if (targetCla.isAssignableFrom(String.class) && value.getClass().isAssignableFrom(String.class)) {
/* 1383 */       object = value;
/*      */     }
/* 1385 */     if (Double.class.isAssignableFrom(targetCla) && value.getClass().isAssignableFrom(String.class)) {
/* 1386 */       object = Double.valueOf(Double.parseDouble((String)value));
/*      */     }
/*      */     
/* 1389 */     if (Integer.class.isAssignableFrom(targetCla) && value.getClass().isAssignableFrom(String.class)) {
/* 1390 */       object = Integer.valueOf((String)value);
/*      */     }
/*      */     
/* 1393 */     if (targetCla.isEnum()) {
/* 1394 */       object = object.toString();
/*      */     }
/* 1396 */     if (Long.class.isAssignableFrom(targetCla) && value.getClass().isAssignableFrom(String.class)) {
/* 1397 */       object = Long.valueOf((String)value);
/*      */     }
/* 1399 */     if (Boolean.class.isAssignableFrom(targetCla) && value.getClass().isAssignableFrom(String.class)) {
/* 1400 */       object = Boolean.valueOf((String)value);
/*      */     }
/* 1402 */     return object;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String detectorEncodingFromBytes(byte[] bytes) {
/* 1412 */     String encodingName = null;
/* 1413 */     CharsetDetector detector = new CharsetDetector();
/* 1414 */     detector.setText(bytes);
/* 1415 */     CharsetMatch[] matches = detector.detectAll();
/* 1416 */     matches[0].getConfidence();
/* 1417 */     matches[0].getName();
/*      */     
/* 1419 */     return encodingName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <K> List<List<K>> splitCollection(Collection<K> ks, int splits) {
/* 1431 */     Preconditions.checkArgument((splits > 0), "splits should big then 0!");
/*      */     
/* 1433 */     List<List<K>> urlsGroup = new ArrayList<>();
/* 1434 */     for (int i = 0; i < splits; i++) {
/* 1435 */       List<K> list = new ArrayList<>();
/* 1436 */       urlsGroup.add(list);
/*      */     } 
/*      */     
/* 1439 */     Iterator<K> iterator = ks.iterator();
/* 1440 */     int j = 0;
/* 1441 */     while (iterator.hasNext()) {
/* 1442 */       K k = iterator.next();
/* 1443 */       ((List<K>)urlsGroup.get(j)).add(k);
/* 1444 */       j++;
/* 1445 */       if (j == splits) {
/* 1446 */         j = 0;
/*      */       }
/*      */     } 
/*      */     
/* 1450 */     return urlsGroup;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static <K> List<List<K>> splitKs(Collection<K> ks, int blockSize) {
/* 1462 */     if (isEmptyOrNull(ks)) {
/* 1463 */       return null;
/*      */     }
/* 1465 */     if (blockSize <= 0) {
/* 1466 */       throw new IllegalArgumentException("请设置blockSize！");
/*      */     }
/* 1468 */     List<List<K>> urlsGroup = new ArrayList<>();
/* 1469 */     int totalUrlSize = ks.size();
/* 1470 */     List<K> tempUrls = null;
/* 1471 */     int groupCount = totalUrlSize / blockSize;
/* 1472 */     int lastGroupElementCount = totalUrlSize % blockSize;
/* 1473 */     if (groupCount == 0) {
/* 1474 */       groupCount++;
/*      */     }
/* 1476 */     K o = null;
/* 1477 */     for (int i = 0; i < groupCount; i++) {
/* 1478 */       tempUrls = new ArrayList<>();
/* 1479 */       for (int index = i * blockSize; index < (i + 1) * blockSize; index++) {
/*      */         
/* 1481 */         if (index >= totalUrlSize) {
/*      */           break;
/*      */         }
/* 1484 */         o = (K)ks.toArray()[index];
/* 1485 */         tempUrls.add(o);
/*      */       } 
/* 1487 */       urlsGroup.add(tempUrls);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1492 */     if (lastGroupElementCount != 0 && groupCount >= 1)
/*      */     {
/* 1494 */       for (int index = groupCount * blockSize; index < totalUrlSize; index++) {
/*      */ 
/*      */         
/* 1497 */         if (index >= totalUrlSize) {
/*      */           break;
/*      */         }
/*      */         
/* 1501 */         o = (K)ks.toArray()[index];
/* 1502 */         ((List<K>)urlsGroup.get(groupCount - 1)).add(o);
/*      */       } 
/*      */     }
/* 1505 */     return urlsGroup;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String LinkStringWithSpecialSymbol(Collection<String> strCollection, String specialSymbol) {
/* 1516 */     if (isEmptyOrNull(strCollection) || specialSymbol == null) {
/* 1517 */       throw new IllegalArgumentException();
/*      */     }
/* 1519 */     StringBuffer str = new StringBuffer();
/* 1520 */     for (Object tempStr : strCollection.toArray())
/*      */     {
/* 1522 */       str.append(tempStr).append(specialSymbol);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1527 */     return str.toString().substring(0, str.toString().lastIndexOf(specialSymbol));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Class getSubClassOfSuperClass(Class superClass) {
/* 1535 */     Class<?> subClass = null;
/*      */     
/* 1537 */     subClass = superClass.getClass();
/*      */     
/* 1539 */     return subClass;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isValidUtf8(byte[] b, int aMaxCount) {
/* 1549 */     int lLen = b.length, lCharCount = 0;
/*      */     
/* 1551 */     for (int i = 0; i < lLen && lCharCount < aMaxCount; lCharCount++) {
/*      */       
/* 1553 */       byte lByte = b[i++];
/*      */       
/* 1555 */       if (lByte < 0) {
/*      */ 
/*      */         
/* 1558 */         if (lByte < -64 || lByte > -3) {
/* 1559 */           return false;
/*      */         }
/*      */         
/* 1562 */         int lCount = (lByte > -4) ? 5 : ((lByte > -8) ? 4 : ((lByte > -16) ? 3 : ((lByte > -32) ? 2 : 1)));
/*      */ 
/*      */         
/* 1565 */         if (i + lCount > lLen) {
/* 1566 */           return false;
/*      */         }
/*      */         
/* 1569 */         for (int j = 0; j < lCount; j++, i++) {
/* 1570 */           if (b[i] >= -64) {
/* 1571 */             return false;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1577 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List convertArrayToList(Object[] arrays) {
/* 1586 */     if (arrays == null || arrays.length == 0) {
/* 1587 */       return null;
/*      */     }
/* 1589 */     List<Object> list = new ArrayList();
/* 1590 */     for (Object obj : arrays) {
/* 1591 */       list.add(obj);
/*      */     }
/* 1593 */     return list;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getJarParentPath(Class cla) {
/* 1601 */     String jarPath = null;
/*      */     
/* 1603 */     jarPath = cla.getProtectionDomain().getCodeSource().getLocation().getFile();
/*      */     try {
/* 1605 */       jarPath = URLDecoder.decode(jarPath, "UTF-8");
/* 1606 */     } catch (UnsupportedEncodingException ex) {
/* 1607 */       mLog.info(ExceptionUtil.getExceptionDetailsMessage(ex));
/*      */     } 
/* 1609 */     File jarFile = new File(jarPath);
/*      */     
/* 1611 */     File parent = jarFile.getParentFile();
/* 1612 */     if (parent != null) {
/* 1613 */       jarPath = parent.getAbsolutePath();
/*      */     }
/* 1615 */     return jarPath;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void quickSort(int[] nums, int i, int j) {
/* 1745 */     int left = i;
/* 1746 */     int right = j;
/* 1747 */     int temp = 0;
/* 1748 */     boolean r_or_l = true;
/* 1749 */     if (left >= right) {
/*      */       return;
/*      */     }
/* 1752 */     while (left < right) {
/* 1753 */       if (nums[left] > nums[right]) {
/* 1754 */         temp = nums[left];
/* 1755 */         nums[left] = nums[right];
/* 1756 */         nums[right] = temp;
/* 1757 */         r_or_l = !r_or_l;
/*      */       } 
/* 1759 */       if (r_or_l) {
/* 1760 */         right--; continue;
/*      */       } 
/* 1762 */       left++;
/*      */     } 
/*      */     
/* 1765 */     left--;
/* 1766 */     right++;
/*      */ 
/*      */     
/* 1769 */     quickSort(nums, 0, left);
/* 1770 */     quickSort(nums, right, j);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object instanceClassByConstructor(String className, Class[] pType, Object[] paras) {
/* 1781 */     Object result = null;
/* 1782 */     Class<?> cla = null;
/*      */     try {
/* 1784 */       cla = Class.forName(className);
/*      */       
/* 1786 */       Constructor<?> ctor = cla.getConstructor(pType);
/*      */       
/* 1788 */       result = ctor.newInstance(paras);
/* 1789 */     } catch (ClassNotFoundException e) {
/*      */       
/* 1791 */       e.printStackTrace();
/* 1792 */     } catch (InstantiationException e) {
/*      */       
/* 1794 */       e.printStackTrace();
/* 1795 */     } catch (IllegalAccessException e) {
/*      */       
/* 1797 */       e.printStackTrace();
/* 1798 */     } catch (IllegalArgumentException e) {
/*      */       
/* 1800 */       e.printStackTrace();
/* 1801 */     } catch (InvocationTargetException e) {
/*      */       
/* 1803 */       e.printStackTrace();
/* 1804 */     } catch (NoSuchMethodException e) {
/*      */       
/* 1806 */       e.printStackTrace();
/* 1807 */     } catch (SecurityException e) {
/*      */       
/* 1809 */       e.printStackTrace();
/*      */     } 
/* 1811 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void quickSort1(int[] nums, int left, int right) {
/* 1821 */     if (left >= right) {
/*      */       return;
/*      */     }
/* 1824 */     int temp = 0;
/* 1825 */     int key = nums[left];
/* 1826 */     int index = -1;
/* 1827 */     while (left < right) {
/* 1828 */       left++;
/* 1829 */       if (nums[left] < key) {
/* 1830 */         nums[left - 1] = nums[left];
/* 1831 */         nums[left] = key;
/* 1832 */         index = left;
/*      */       } 
/*      */       
/* 1835 */       if (nums[right] < key) {
/* 1836 */         temp = nums[right];
/* 1837 */         nums[right] = key;
/* 1838 */         nums[left] = temp;
/* 1839 */         index = right;
/*      */       } 
/* 1841 */       right--;
/*      */     } 
/*      */     
/* 1844 */     quickSort1(nums, 0, index - 1);
/* 1845 */     quickSort1(nums, index + 1, right);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Map<String, String> loadProperties(String filePath) {
/* 1855 */     Map<String, String> properties = new HashMap<>();
/* 1856 */     InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
/* 1857 */     BufferedReader br = new BufferedReader(new InputStreamReader(in));
/* 1858 */     String line = null;
/*      */     while (true) {
/*      */       try {
/* 1861 */         line = br.readLine();
/* 1862 */       } catch (IOException e) {
/*      */         
/* 1864 */         throw new RuntimeException("read " + filePath + "error!");
/*      */       } 
/* 1866 */       if (line != null && !"".equals(line.trim())) {
/* 1867 */         properties.put(line.split("=")[0], line.split("=")[1]);
/*      */       }
/* 1869 */       if (line == null) {
/* 1870 */         return properties;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getVlaue(String xpath, String fileName) {
/* 1881 */     String value = null;
/*      */     try {
/* 1883 */       value = XPathUtil.getNodeTextByXPath(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName), xpath);
/* 1884 */     } catch (Exception e) {
/*      */       
/* 1886 */       return null;
/*      */     } 
/* 1888 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String> groupIndexByRange(int totalNumber, int splits) {
/* 1900 */     if (totalNumber < splits) {
/* 1901 */       splits = totalNumber;
/*      */     }
/* 1903 */     List<String> groups = new ArrayList<>();
/* 1904 */     int offset = totalNumber / splits + ((totalNumber % splits > 0) ? 1 : 0);
/* 1905 */     String str = null;
/* 1906 */     for (int i = 0; i < splits; i++) {
/* 1907 */       str = "";
/* 1908 */       str = str + (i * offset + 1);
/* 1909 */       str = str + "-";
/* 1910 */       if (i * offset + 1 + offset > totalNumber) {
/* 1911 */         str = str + (totalNumber - i * offset + 2);
/*      */       } else {
/* 1913 */         str = str + offset;
/*      */       } 
/* 1915 */       groups.add(str);
/*      */     } 
/* 1917 */     return groups;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static List<String> groupIndexByRange(int startPosition, int totalNumber, int splits) {
/* 1928 */     if (totalNumber < splits) {
/* 1929 */       throw new IllegalArgumentException();
/*      */     }
/* 1931 */     List<String> groups = new ArrayList<>();
/* 1932 */     int offset = totalNumber / splits + ((totalNumber % splits > 0) ? 1 : 0);
/* 1933 */     String str = null;
/* 1934 */     for (int i = 0; i < splits; i++) {
/* 1935 */       str = "";
/* 1936 */       str = str + (i * offset + startPosition);
/* 1937 */       str = str + "-";
/*      */       
/* 1939 */       if (i * offset + startPosition + 1 + offset > totalNumber) {
/* 1940 */         str = str + (totalNumber - i * offset + startPosition + 1);
/*      */       } else {
/* 1942 */         str = str + offset;
/*      */       } 
/* 1944 */       groups.add(str);
/*      */     } 
/* 1946 */     return groups;
/*      */   }
/*      */   
/*      */   public static String convertImageToString(String imageFilePath) throws Exception {
/* 1950 */     String imageEncodeString = null;
/* 1951 */     byte[] bytes = IOUtils.translantStreamToByte(new FileInputStream(imageFilePath));
/*      */ 
/*      */     
/* 1954 */     imageEncodeString = Base64.encode(bytes);
/*      */     
/* 1956 */     return imageEncodeString;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static InputStream imageStringToStream(String imageDecodeString) throws Exception {
/* 1962 */     byte[] bytearray = Base64.decode(imageDecodeString);
/*      */ 
/*      */     
/* 1965 */     return new ByteArrayInputStream(bytearray);
/*      */   }
/*      */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/CommonUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */