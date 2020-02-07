/*     */ package com.yufei.utils;
/*     */ 
/*     */ import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FileUtil
/*     */ {
/*  44 */   private static Logger logger = Logger.getLogger(FileUtil.class);
/*     */   
/*     */   public static void getAllFiles(File fd, String fileR, List<File> files) {
/*  47 */     if (fd.isDirectory()) {
/*  48 */       for (File f : fd.listFiles()) {
/*  49 */         getAllFiles(f, fileR, files);
/*     */       
/*     */       }
/*     */     }
/*  53 */     else if (PatternUtils.match(fd.getName(), fileR)) {
/*  54 */       files.add(fd);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<List> listToBeans(List<List<String>> source, List<String> beans, List<String> fieldOrders) {
/*  61 */     List<String> rowValues = null;
/*  62 */     String beanName = null;
/*  63 */     Object obj = null;
/*  64 */     List<String> columns = null;
/*  65 */     List<List> list = new ArrayList<>();
/*  66 */     List<Object> objList = null;
/*  67 */     Object[] objs = null;
/*  68 */     for (int i = 0; i < source.size(); i++) {
/*  69 */       objList = new ArrayList();
/*     */       
/*  71 */       rowValues = source.get(i);
/*  72 */       objs = new Object[beans.size()];
/*     */       
/*  74 */       for (int q = 0; q < beans.size(); q++) {
/*  75 */         beanName = beans.get(q);
/*     */         
/*     */         try {
/*  78 */           objs[q] = Class.forName(beanName).newInstance();
/*  79 */         } catch (InstantiationException e) {
/*     */           
/*  81 */           e.printStackTrace();
/*  82 */         } catch (IllegalAccessException e) {
/*     */           
/*  84 */           e.printStackTrace();
/*  85 */         } catch (ClassNotFoundException e) {
/*     */           
/*  87 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */       
/*  91 */       for (int j = 0; j < rowValues.size(); j++) {
/*     */         
/*  93 */         for (int k = 0; k < beans.size(); k++) {
/*     */           
/*     */           try {
/*  96 */             BeanUtils.setProperty(objs[k], fieldOrders.get(j), rowValues
/*  97 */                 .get(j));
/*     */           
/*     */           }
/* 100 */           catch (IllegalAccessException e) {
/*     */ 
/*     */           
/* 103 */           } catch (InvocationTargetException e) {}
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 110 */       for (Object obj1 : objs) {
/* 111 */         objList.add(obj1);
/*     */       }
/* 113 */       list.add(objList);
/*     */     } 
/* 115 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List listToBeans(List<List<String>> source, String beanName, List<String> fieldOrders) {
/* 120 */     List<String> rowValues = null;
/* 121 */     Object obj = null;
/* 122 */     List<String> columns = null;
/* 123 */     List<Object> objList = new ArrayList();
/* 124 */     Object objs = null;
/* 125 */     for (int i = 0; i < source.size(); i++) {
/*     */       
/* 127 */       rowValues = source.get(i);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 133 */         objs = Class.forName(beanName).newInstance();
/* 134 */       } catch (InstantiationException e) {
/*     */         
/* 136 */         e.printStackTrace();
/* 137 */       } catch (IllegalAccessException e) {
/*     */         
/* 139 */         e.printStackTrace();
/* 140 */       } catch (ClassNotFoundException e) {
/*     */         
/* 142 */         e.printStackTrace();
/*     */       } 
/*     */ 
/*     */       
/* 146 */       for (int j = 0; j < rowValues.size(); j++) {
/*     */ 
/*     */         
/*     */         try {
/*     */           
/* 151 */           BeanUtils.setProperty(objs, fieldOrders.get(j), rowValues
/* 152 */               .get(j));
/*     */         
/*     */         }
/* 155 */         catch (IllegalAccessException e) {
/*     */ 
/*     */         
/* 158 */         } catch (InvocationTargetException e) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 164 */       objList.add(objs);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 171 */     return objList;
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
/*     */   public static List<List<String>> parseExcelFile(String filePath) throws Exception {
/* 183 */     Workbook wb = null;
/* 184 */     WorkbookSettings ws = new WorkbookSettings();
/* 185 */     ws.setEncoding("cp1252");
/* 186 */     wb = Workbook.getWorkbook(new FileInputStream(filePath), ws);
/* 187 */     Sheet sheet = wb.getSheet(0);
/* 188 */     int rows = sheet.getRows();
/* 189 */     int cols = sheet.getColumns();
/* 190 */     String content = null;
/*     */     
/* 192 */     List<String> rowValue = null;
/* 193 */     List<List<String>> rowValueList = new ArrayList();
/* 194 */     for (int rowNumber = 1; rowNumber < rows + 1; rowNumber++) {
/* 195 */       Cell[] rowCells = null;
/*     */       try {
/* 197 */         rowValue = new ArrayList<>();
/* 198 */         rowCells = sheet.getRow(rowNumber - 1);
/* 199 */         for (int cellNumber = 1; cellNumber < cols + 1; cellNumber++) {
/* 200 */           content = rowCells[cellNumber - 1].getContents();
/*     */ 
/*     */           
/* 203 */           rowValue.add(content);
/*     */         }
/*     */       
/* 206 */       } catch (Exception e) {
/* 207 */         logger.info("error when read the row :" + rowNumber + "");
/* 208 */         int number = rowCells.length;
/* 209 */         if (cols > number) {
/* 210 */           for (int i = 0; i < cols - number; i++) {
/* 211 */             rowValue.add("");
/*     */           }
/*     */         }
/*     */       } 
/*     */       
/* 216 */       rowValueList.add(rowValue);
/*     */     } 
/*     */     
/* 219 */     return rowValueList;
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
/*     */   public static <T> String gernerateExcelFromList(List<T> source, List<String> fieldOder, String savePath, List<String> headers) throws Exception {
/* 232 */     return gernerateExcelFromList(source, fieldOder, savePath, headers, null);
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
/*     */   public static <T> String gernerateExcelFromList(List<T> source, List<String> fieldOder, String savePath, List<String> headers, String fileName) throws Exception {
/* 249 */     if (CommonUtil.isEmptyOrNull(source))
/*     */     {
/* 251 */       return null;
/*     */     }
/*     */
/* 254 */     if (CommonUtil.isEmptyOrNull(fieldOder)) {
/* 255 */       fieldOder = new ArrayList<>();
/*     */
/* 257 */       List<Field> fields = CommonUtil.getAllDeclareFields(source.get(0).getClass());
/* 258 */       for (Field field : fields) {
/* 259 */         fieldOder.add(field.getName());
/*     */       }
/*     */     }
/*     */
/*     */
/* 264 */     if (!(new File(savePath)).exists()) {
/* 265 */       (new File(savePath)).mkdir();
/*     */     }
/* 267 */     if (CommonUtil.isEmptyOrNull(fileName)) {
/* 268 */       fileName = System.currentTimeMillis() + ".xls";
/*     */     }
/*     */
/* 271 */     WritableWorkbook workbook = Workbook.createWorkbook(new File(savePath + fileName));
/* 272 */     WritableSheet sheet = workbook.createSheet("sheet", 0);
/* 273 */     Label label = null;
/* 274 */     String[] strs = new String[headers.size()];
/* 275 */     int rowNumber = 0, columnNumber = 0;
/* 276 */     if (!CommonUtil.isEmptyOrNull(headers)) {
/* 277 */       for (int j = 0; j < strs.length; j++) {
/*     */
/*     */
/* 280 */         label = new Label(columnNumber, rowNumber, headers.get(j));
/* 281 */         sheet.addCell((WritableCell)label);
/* 282 */         columnNumber++;
/*     */       }
/* 284 */       rowNumber++;
/*     */     }
/*     */
/*     */
/*     */
/* 289 */     String[] strs1 = new String[fieldOder.size()];
/*     */
/* 291 */     for (T t : source) {
/* 292 */       columnNumber = 0;
/*     */
/*     */
/* 295 */       for (int i = 0; i < fieldOder.size(); i++) {
/* 296 */         strs1[i] = BeanUtils.getProperty(t, fieldOder
/* 297 */             .get(i));
/*     */       }
/* 299 */       for (int j = 0; j < strs1.length; j++) {
/*     */
/*     */
/* 302 */         label = new Label(columnNumber, rowNumber, strs1[j]);
/* 303 */         sheet.addCell((WritableCell)label);
/* 304 */         if (strs1[j].contains("http")) {
/* 305 */           sheet.addHyperlink(new WritableHyperlink(columnNumber, rowNumber, new URL(strs1[j])));
/*     */         }
/*     */
/*     */
/* 309 */         columnNumber++;
/*     */       }
/* 311 */       rowNumber++;
/*     */     }
/*     */
/*     */
/* 315 */     workbook.write();
/* 316 */     workbook.close();
/* 317 */     return fileName;
/*     */   }
/*     */
/*     */   
/*     */   public static String gernerateExcelFromList(List<String> source, String savePath) {
/* 322 */     if (CommonUtil.isEmptyOrNull(source))
/*     */     {
/* 324 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 328 */     String fileName = System.currentTimeMillis() + ".xls";
/*     */     
/*     */     try {
/* 331 */       WritableWorkbook workbook = Workbook.createWorkbook(new File(savePath + fileName));
/* 332 */       WritableSheet sheet = workbook.createSheet("sheet", 0);
/* 333 */       Label label = null;
/*     */       
/* 335 */       int rowNumber = 0, columnNumber = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 340 */       for (String t : source) {
/* 341 */         columnNumber = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 347 */         label = new Label(columnNumber, rowNumber, t);
/* 348 */         sheet.addCell((WritableCell)label);
/* 349 */         columnNumber++;
/*     */         
/* 351 */         rowNumber++;
/*     */       } 
/*     */ 
/*     */       
/* 355 */       workbook.write();
/* 356 */       workbook.close();
/* 357 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 360 */     return fileName;
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
/*     */   public static void makeFile(String savedDirectory, String saveFileName, InputStream input) throws IOException {
/* 381 */     File file = new File(savedDirectory + saveFileName); if (!file.exists()) {
/* 382 */       file.createNewFile();
/*     */     }
/*     */     
/* 385 */     FileOutputStream fileOutputStream = new FileOutputStream(file); int bufferFilled;
/* 386 */     for (byte[] buffer = new byte[512]; (bufferFilled = input.read(buffer, 0, buffer.length)) != -1;)
/*     */     {
/* 388 */       fileOutputStream.write(buffer, 0, bufferFilled); } 
/* 389 */     fileOutputStream.close();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void makeFile(String savedDirectory, String saveFileName, String input) throws IOException {
/* 395 */     if (CommonUtil.isEmptyOrNull(savedDirectory) || CommonUtil.isEmptyOrNull(saveFileName) || CommonUtil.isEmptyOrNull(input)) {
/* 396 */       throw new IllegalArgumentException();
/*     */     }
/* 398 */     File file = new File(savedDirectory + saveFileName);
/* 399 */     if (!file.exists())
/* 400 */       file.createNewFile(); 
/* 401 */     byte[] bytes = input.getBytes();
/* 402 */     FileOutputStream fileOutputStream = new FileOutputStream(file);
/* 403 */     byte[] buffer = new byte[512];
/* 404 */     fileOutputStream.write(bytes);
/* 405 */     fileOutputStream.close();
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
/*     */   public static void writeToFile(String content, String filePath) throws IOException {
/* 447 */     File file = new File(filePath);
/*     */     
/* 449 */     if (!file.exists()) {
/* 450 */       file.createNewFile();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 455 */     FileWriter fileWriter = new FileWriter(file, true);
/* 456 */     fileWriter.append(content + "\r\n");
/* 457 */     fileWriter.flush();
/* 458 */     fileWriter.close();
/*     */   }
/*     */   
/*     */   public static List<String> readLines(String filePath) {
/* 462 */     List<String> lines = new ArrayList<>();
/* 463 */     File file = new File(filePath);
/* 464 */     BufferedReader reader = null;
/*     */     try {
/* 466 */       System.out.println("以行为单位读取文件内容，一次读一整行：");
/* 467 */       reader = new BufferedReader(new FileReader(file));
/* 468 */       String tempString = null;
/* 469 */       int line = 1;
/*     */       
/* 471 */       while ((tempString = reader.readLine()) != null) {
/* 472 */         lines.add(tempString.trim());
/* 473 */         line++;
/*     */       } 
/* 475 */       reader.close();
/* 476 */     } catch (IOException e) {
/* 477 */       e.printStackTrace();
/*     */     } finally {
/* 479 */       if (reader != null) {
/*     */         try {
/* 481 */           reader.close();
/* 482 */         } catch (IOException iOException) {}
/*     */       }
/*     */     } 
/*     */     
/* 486 */     return lines;
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
/*     */   public static void main(String[] args) throws IOException {
/* 536 */     writeToFile("test1", "D:\\temp\\test");
/* 537 */     writeToFile("test2", "D:\\temp\\test");
/*     */   }
/*     */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/FileUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */