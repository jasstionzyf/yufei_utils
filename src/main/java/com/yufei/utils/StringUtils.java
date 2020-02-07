/*      */ package com.yufei.utils;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Properties;
/*      */ import java.util.Set;
/*      */ import java.util.StringTokenizer;
/*      */ import java.util.TreeSet;
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
/*      */ public abstract class StringUtils
/*      */ {
/*      */   private static final String FOLDER_SEPARATOR = "/";
/*      */   private static final String WINDOWS_FOLDER_SEPARATOR = "\\";
/*      */   private static final String TOP_PATH = "..";
/*      */   private static final String CURRENT_PATH = ".";
/*      */   private static final char EXTENSION_SEPARATOR = '.';
/*      */   
/*      */   public static boolean isEmpty(Object str) {
/*   91 */     return (str == null || "".equals(str));
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
/*      */   public static boolean hasLength(CharSequence str) {
/*  108 */     return (str != null && str.length() > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean hasLength(String str) {
/*  119 */     return hasLength(str);
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
/*      */   public static boolean hasText(CharSequence str) {
/*  139 */     if (!hasLength(str)) {
/*  140 */       return false;
/*      */     }
/*  142 */     int strLen = str.length();
/*  143 */     for (int i = 0; i < strLen; i++) {
/*  144 */       if (!Character.isWhitespace(str.charAt(i))) {
/*  145 */         return true;
/*      */       }
/*      */     } 
/*  148 */     return false;
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
/*      */   public static boolean hasText(String str) {
/*  161 */     return hasText(str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean containsWhitespace(CharSequence str) {
/*  172 */     if (!hasLength(str)) {
/*  173 */       return false;
/*      */     }
/*  175 */     int strLen = str.length();
/*  176 */     for (int i = 0; i < strLen; i++) {
/*  177 */       if (Character.isWhitespace(str.charAt(i))) {
/*  178 */         return true;
/*      */       }
/*      */     } 
/*  181 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean containsWhitespace(String str) {
/*  192 */     return containsWhitespace(str);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String trimWhitespace(String str) {
/*  202 */     if (!hasLength(str)) {
/*  203 */       return str;
/*      */     }
/*  205 */     StringBuilder sb = new StringBuilder(str);
/*  206 */     while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
/*  207 */       sb.deleteCharAt(0);
/*      */     }
/*  209 */     while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
/*  210 */       sb.deleteCharAt(sb.length() - 1);
/*      */     }
/*  212 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String trimAllWhitespace(String str) {
/*  223 */     if (!hasLength(str)) {
/*  224 */       return str;
/*      */     }
/*  226 */     StringBuilder sb = new StringBuilder(str);
/*  227 */     int index = 0;
/*  228 */     while (sb.length() > index) {
/*  229 */       if (Character.isWhitespace(sb.charAt(index))) {
/*  230 */         sb.deleteCharAt(index);
/*      */         continue;
/*      */       } 
/*  233 */       index++;
/*      */     } 
/*      */     
/*  236 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String trimLeadingWhitespace(String str) {
/*  246 */     if (!hasLength(str)) {
/*  247 */       return str;
/*      */     }
/*  249 */     StringBuilder sb = new StringBuilder(str);
/*  250 */     while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
/*  251 */       sb.deleteCharAt(0);
/*      */     }
/*  253 */     return sb.toString();
/*      */   }
/*      */   public static String formatWhiteSpace(String str) {
/*  256 */     str = trimTrailingWhitespace(str);
/*  257 */     str = trimLeadingWhitespace(str);
/*  258 */     str = str.replaceAll("\\s{1,}", " ");
/*  259 */     return str;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String trimTrailingWhitespace(String str) {
/*  268 */     if (!hasLength(str)) {
/*  269 */       return str;
/*      */     }
/*  271 */     StringBuilder sb = new StringBuilder(str);
/*  272 */     while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
/*  273 */       sb.deleteCharAt(sb.length() - 1);
/*      */     }
/*  275 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String trimLeadingCharacter(String str, char leadingCharacter) {
/*  285 */     if (!hasLength(str)) {
/*  286 */       return str;
/*      */     }
/*  288 */     StringBuilder sb = new StringBuilder(str);
/*  289 */     while (sb.length() > 0 && sb.charAt(0) == leadingCharacter) {
/*  290 */       sb.deleteCharAt(0);
/*      */     }
/*  292 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String trimTrailingCharacter(String str, char trailingCharacter) {
/*  302 */     if (!hasLength(str)) {
/*  303 */       return str;
/*      */     }
/*  305 */     StringBuilder sb = new StringBuilder(str);
/*  306 */     while (sb.length() > 0 && sb.charAt(sb.length() - 1) == trailingCharacter) {
/*  307 */       sb.deleteCharAt(sb.length() - 1);
/*      */     }
/*  309 */     return sb.toString();
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
/*      */   public static boolean startsWithIgnoreCase(String str, String prefix) {
/*  321 */     if (str == null || prefix == null) {
/*  322 */       return false;
/*      */     }
/*  324 */     if (str.startsWith(prefix)) {
/*  325 */       return true;
/*      */     }
/*  327 */     if (str.length() < prefix.length()) {
/*  328 */       return false;
/*      */     }
/*  330 */     String lcStr = str.substring(0, prefix.length()).toLowerCase();
/*  331 */     String lcPrefix = prefix.toLowerCase();
/*  332 */     return lcStr.equals(lcPrefix);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean endsWithIgnoreCase(String str, String suffix) {
/*  343 */     if (str == null || suffix == null) {
/*  344 */       return false;
/*      */     }
/*  346 */     if (str.endsWith(suffix)) {
/*  347 */       return true;
/*      */     }
/*  349 */     if (str.length() < suffix.length()) {
/*  350 */       return false;
/*      */     }
/*      */     
/*  353 */     String lcStr = str.substring(str.length() - suffix.length()).toLowerCase();
/*  354 */     String lcSuffix = suffix.toLowerCase();
/*  355 */     return lcStr.equals(lcSuffix);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
/*  366 */     for (int j = 0; j < substring.length(); j++) {
/*  367 */       int i = index + j;
/*  368 */       if (i >= str.length() || str.charAt(i) != substring.charAt(j)) {
/*  369 */         return false;
/*      */       }
/*      */     } 
/*  372 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int countOccurrencesOf(String str, String sub) {
/*  381 */     if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
/*  382 */       return 0;
/*      */     }
/*  384 */     int count = 0;
/*  385 */     int pos = 0;
/*      */     int idx;
/*  387 */     while ((idx = str.indexOf(sub, pos)) != -1) {
/*  388 */       count++;
/*  389 */       pos = idx + sub.length();
/*      */     } 
/*  391 */     return count;
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
/*      */   public static String replace(String inString, String oldPattern, String newPattern) {
/*  403 */     if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
/*  404 */       return inString;
/*      */     }
/*  406 */     StringBuilder sb = new StringBuilder();
/*  407 */     int pos = 0;
/*  408 */     int index = inString.indexOf(oldPattern);
/*      */     
/*  410 */     int patLen = oldPattern.length();
/*  411 */     while (index >= 0) {
/*  412 */       sb.append(inString.substring(pos, index));
/*  413 */       sb.append(newPattern);
/*  414 */       pos = index + patLen;
/*  415 */       index = inString.indexOf(oldPattern, pos);
/*      */     } 
/*  417 */     sb.append(inString.substring(pos));
/*      */     
/*  419 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String delete(String inString, String pattern) {
/*  429 */     return replace(inString, pattern, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String deleteAny(String inString, String charsToDelete) {
/*  440 */     if (!hasLength(inString) || !hasLength(charsToDelete)) {
/*  441 */       return inString;
/*      */     }
/*  443 */     StringBuilder sb = new StringBuilder();
/*  444 */     for (int i = 0; i < inString.length(); i++) {
/*  445 */       char c = inString.charAt(i);
/*  446 */       if (charsToDelete.indexOf(c) == -1) {
/*  447 */         sb.append(c);
/*      */       }
/*      */     } 
/*  450 */     return sb.toString();
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
/*      */   public static String quote(String str) {
/*  465 */     return (str != null) ? ("'" + str + "'") : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object quoteIfString(Object obj) {
/*  476 */     return (obj instanceof String) ? quote((String)obj) : obj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String unqualify(String qualifiedName) {
/*  485 */     return unqualify(qualifiedName, '.');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String unqualify(String qualifiedName, char separator) {
/*  495 */     return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String capitalize(String str) {
/*  506 */     return changeFirstCharacterCase(str, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String uncapitalize(String str) {
/*  517 */     return changeFirstCharacterCase(str, false);
/*      */   }
/*      */   
/*      */   private static String changeFirstCharacterCase(String str, boolean capitalize) {
/*  521 */     if (str == null || str.length() == 0) {
/*  522 */       return str;
/*      */     }
/*  524 */     StringBuilder sb = new StringBuilder(str.length());
/*  525 */     if (capitalize) {
/*  526 */       sb.append(Character.toUpperCase(str.charAt(0)));
/*      */     } else {
/*      */       
/*  529 */       sb.append(Character.toLowerCase(str.charAt(0)));
/*      */     } 
/*  531 */     sb.append(str.substring(1));
/*  532 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getFilename(String path) {
/*  542 */     if (path == null) {
/*  543 */       return null;
/*      */     }
/*  545 */     int separatorIndex = path.lastIndexOf("/");
/*  546 */     return (separatorIndex != -1) ? path.substring(separatorIndex + 1) : path;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getFilenameExtension(String path) {
/*  556 */     if (path == null) {
/*  557 */       return null;
/*      */     }
/*  559 */     int extIndex = path.lastIndexOf('.');
/*  560 */     if (extIndex == -1) {
/*  561 */       return null;
/*      */     }
/*  563 */     int folderIndex = path.lastIndexOf("/");
/*  564 */     if (folderIndex > extIndex) {
/*  565 */       return null;
/*      */     }
/*  567 */     return path.substring(extIndex + 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String stripFilenameExtension(String path) {
/*  578 */     if (path == null) {
/*  579 */       return null;
/*      */     }
/*  581 */     int extIndex = path.lastIndexOf('.');
/*  582 */     if (extIndex == -1) {
/*  583 */       return path;
/*      */     }
/*  585 */     int folderIndex = path.lastIndexOf("/");
/*  586 */     if (folderIndex > extIndex) {
/*  587 */       return path;
/*      */     }
/*  589 */     return path.substring(0, extIndex);
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
/*      */   public static String applyRelativePath(String path, String relativePath) {
/*  601 */     int separatorIndex = path.lastIndexOf("/");
/*  602 */     if (separatorIndex != -1) {
/*  603 */       String newPath = path.substring(0, separatorIndex);
/*  604 */       if (!relativePath.startsWith("/")) {
/*  605 */         newPath = newPath + "/";
/*      */       }
/*  607 */       return newPath + relativePath;
/*      */     } 
/*      */     
/*  610 */     return relativePath;
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
/*      */   public static String cleanPath(String path) {
/*  623 */     if (path == null) {
/*  624 */       return null;
/*      */     }
/*  626 */     String pathToUse = replace(path, "\\", "/");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  632 */     int prefixIndex = pathToUse.indexOf(":");
/*  633 */     String prefix = "";
/*  634 */     if (prefixIndex != -1) {
/*  635 */       prefix = pathToUse.substring(0, prefixIndex + 1);
/*  636 */       pathToUse = pathToUse.substring(prefixIndex + 1);
/*      */     } 
/*  638 */     if (pathToUse.startsWith("/")) {
/*  639 */       prefix = prefix + "/";
/*  640 */       pathToUse = pathToUse.substring(1);
/*      */     } 
/*      */     
/*  643 */     String[] pathArray = delimitedListToStringArray(pathToUse, "/");
/*  644 */     List<String> pathElements = new LinkedList<>();
/*  645 */     int tops = 0;
/*      */     int i;
/*  647 */     for (i = pathArray.length - 1; i >= 0; i--) {
/*  648 */       String element = pathArray[i];
/*  649 */       if (!".".equals(element))
/*      */       {
/*      */         
/*  652 */         if ("..".equals(element)) {
/*      */           
/*  654 */           tops++;
/*      */         
/*      */         }
/*  657 */         else if (tops > 0) {
/*      */           
/*  659 */           tops--;
/*      */         }
/*      */         else {
/*      */           
/*  663 */           pathElements.add(0, element);
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  669 */     for (i = 0; i < tops; i++) {
/*  670 */       pathElements.add(0, "..");
/*      */     }
/*      */     
/*  673 */     return prefix + collectionToDelimitedString(pathElements, "/");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean pathEquals(String path1, String path2) {
/*  683 */     return cleanPath(path1).equals(cleanPath(path2));
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
/*      */   public static Locale parseLocaleString(String localeString) {
/*  695 */     String[] parts = tokenizeToStringArray(localeString, "_ ", false, false);
/*  696 */     String language = (parts.length > 0) ? parts[0] : "";
/*  697 */     String country = (parts.length > 1) ? parts[1] : "";
/*  698 */     validateLocalePart(language);
/*  699 */     validateLocalePart(country);
/*  700 */     String variant = "";
/*  701 */     if (parts.length >= 2) {
/*      */ 
/*      */       
/*  704 */       int endIndexOfCountryCode = localeString.lastIndexOf(country) + country.length();
/*      */       
/*  706 */       variant = trimLeadingWhitespace(localeString.substring(endIndexOfCountryCode));
/*  707 */       if (variant.startsWith("_")) {
/*  708 */         variant = trimLeadingCharacter(variant, '_');
/*      */       }
/*      */     } 
/*  711 */     return (language.length() > 0) ? new Locale(language, country, variant) : null;
/*      */   }
/*      */   
/*      */   private static void validateLocalePart(String localePart) {
/*  715 */     for (int i = 0; i < localePart.length(); i++) {
/*  716 */       char ch = localePart.charAt(i);
/*  717 */       if (ch != '_' && ch != ' ' && !Character.isLetterOrDigit(ch)) {
/*  718 */         throw new IllegalArgumentException("Locale part \"" + localePart + "\" contains invalid characters");
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
/*      */   public static String toLanguageTag(Locale locale) {
/*  731 */     return locale.getLanguage() + (hasText(locale.getCountry()) ? ("-" + locale.getCountry()) : "");
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
/*      */   public static String[] addStringToArray(String[] array, String str) {
/*  747 */     if (ObjectUtils.isEmpty((Object[])array)) {
/*  748 */       return new String[] { str };
/*      */     }
/*  750 */     String[] newArr = new String[array.length + 1];
/*  751 */     System.arraycopy(array, 0, newArr, 0, array.length);
/*  752 */     newArr[array.length] = str;
/*  753 */     return newArr;
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
/*      */   public static String[] concatenateStringArrays(String[] array1, String[] array2) {
/*  765 */     if (ObjectUtils.isEmpty((Object[])array1)) {
/*  766 */       return array2;
/*      */     }
/*  768 */     if (ObjectUtils.isEmpty((Object[])array2)) {
/*  769 */       return array1;
/*      */     }
/*  771 */     String[] newArr = new String[array1.length + array2.length];
/*  772 */     System.arraycopy(array1, 0, newArr, 0, array1.length);
/*  773 */     System.arraycopy(array2, 0, newArr, array1.length, array2.length);
/*  774 */     return newArr;
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
/*      */   public static String[] mergeStringArrays(String[] array1, String[] array2) {
/*  788 */     if (ObjectUtils.isEmpty((Object[])array1)) {
/*  789 */       return array2;
/*      */     }
/*  791 */     if (ObjectUtils.isEmpty((Object[])array2)) {
/*  792 */       return array1;
/*      */     }
/*  794 */     List<String> result = new ArrayList<>();
/*  795 */     result.addAll(Arrays.asList(array1));
/*  796 */     for (String str : array2) {
/*  797 */       if (!result.contains(str)) {
/*  798 */         result.add(str);
/*      */       }
/*      */     } 
/*  801 */     return toStringArray(result);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] sortStringArray(String[] array) {
/*  810 */     if (ObjectUtils.isEmpty((Object[])array)) {
/*  811 */       return new String[0];
/*      */     }
/*  813 */     Arrays.sort((Object[])array);
/*  814 */     return array;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] toStringArray(Collection<String> collection) {
/*  825 */     if (collection == null) {
/*  826 */       return null;
/*      */     }
/*  828 */     return collection.<String>toArray(new String[collection.size()]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] toStringArray(Enumeration<String> enumeration) {
/*  839 */     if (enumeration == null) {
/*  840 */       return null;
/*      */     }
/*  842 */     List<String> list = Collections.list(enumeration);
/*  843 */     return list.<String>toArray(new String[list.size()]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] trimArrayElements(String[] array) {
/*  853 */     if (ObjectUtils.isEmpty((Object[])array)) {
/*  854 */       return new String[0];
/*      */     }
/*  856 */     String[] result = new String[array.length];
/*  857 */     for (int i = 0; i < array.length; i++) {
/*  858 */       String element = array[i];
/*  859 */       result[i] = (element != null) ? element.trim() : null;
/*      */     } 
/*  861 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] removeDuplicateStrings(String[] array) {
/*  871 */     if (ObjectUtils.isEmpty((Object[])array)) {
/*  872 */       return array;
/*      */     }
/*  874 */     Set<String> set = new TreeSet<>();
/*  875 */     for (String element : array) {
/*  876 */       set.add(element);
/*      */     }
/*  878 */     return toStringArray(set);
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
/*      */   public static String[] split(String toSplit, String delimiter) {
/*  891 */     if (!hasLength(toSplit) || !hasLength(delimiter)) {
/*  892 */       return null;
/*      */     }
/*  894 */     int offset = toSplit.indexOf(delimiter);
/*  895 */     if (offset < 0) {
/*  896 */       return null;
/*      */     }
/*  898 */     String beforeDelimiter = toSplit.substring(0, offset);
/*  899 */     String afterDelimiter = toSplit.substring(offset + delimiter.length());
/*  900 */     return new String[] { beforeDelimiter, afterDelimiter };
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
/*      */   public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter) {
/*  915 */     return splitArrayElementsIntoProperties(array, delimiter, null);
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
/*      */   public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter, String charsToDelete) {
/*  935 */     if (ObjectUtils.isEmpty((Object[])array)) {
/*  936 */       return null;
/*      */     }
/*  938 */     Properties result = new Properties();
/*  939 */     for (String element : array) {
/*  940 */       if (charsToDelete != null) {
/*  941 */         element = deleteAny(element, charsToDelete);
/*      */       }
/*  943 */       String[] splittedElement = split(element, delimiter);
/*  944 */       if (splittedElement != null)
/*      */       {
/*      */         
/*  947 */         result.setProperty(splittedElement[0].trim(), splittedElement[1].trim()); } 
/*      */     } 
/*  949 */     return result;
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
/*      */   public static String[] tokenizeToStringArray(String str, String delimiters) {
/*  968 */     return tokenizeToStringArray(str, delimiters, true, true);
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
/*      */   public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {
/*  993 */     if (str == null) {
/*  994 */       return null;
/*      */     }
/*  996 */     StringTokenizer st = new StringTokenizer(str, delimiters);
/*  997 */     List<String> tokens = new ArrayList<>();
/*  998 */     while (st.hasMoreTokens()) {
/*  999 */       String token = st.nextToken();
/* 1000 */       if (trimTokens) {
/* 1001 */         token = token.trim();
/*      */       }
/* 1003 */       if (!ignoreEmptyTokens || token.length() > 0) {
/* 1004 */         tokens.add(token);
/*      */       }
/*      */     } 
/* 1007 */     return toStringArray(tokens);
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
/*      */   public static String[] delimitedListToStringArray(String str, String delimiter) {
/* 1022 */     return delimitedListToStringArray(str, delimiter, null);
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
/*      */   public static String[] delimitedListToStringArray(String str, String delimiter, String charsToDelete) {
/* 1039 */     if (str == null) {
/* 1040 */       return new String[0];
/*      */     }
/* 1042 */     if (delimiter == null) {
/* 1043 */       return new String[] { str };
/*      */     }
/* 1045 */     List<String> result = new ArrayList<>();
/* 1046 */     if ("".equals(delimiter)) {
/* 1047 */       for (int i = 0; i < str.length(); i++) {
/* 1048 */         result.add(deleteAny(str.substring(i, i + 1), charsToDelete));
/*      */       }
/*      */     } else {
/*      */       
/* 1052 */       int pos = 0;
/*      */       int delPos;
/* 1054 */       while ((delPos = str.indexOf(delimiter, pos)) != -1) {
/* 1055 */         result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
/* 1056 */         pos = delPos + delimiter.length();
/*      */       } 
/* 1058 */       if (str.length() > 0 && pos <= str.length())
/*      */       {
/* 1060 */         result.add(deleteAny(str.substring(pos), charsToDelete));
/*      */       }
/*      */     } 
/* 1063 */     return toStringArray(result);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] commaDelimitedListToStringArray(String str) {
/* 1072 */     return delimitedListToStringArray(str, ",");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Set<String> commaDelimitedListToSet(String str) {
/* 1082 */     Set<String> set = new TreeSet<>();
/* 1083 */     String[] tokens = commaDelimitedListToStringArray(str);
/* 1084 */     for (String token : tokens) {
/* 1085 */       set.add(token);
/*      */     }
/* 1087 */     return set;
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
/*      */   public static String collectionToDelimitedString(Collection<?> coll, String delim, String prefix, String suffix) {
/* 1100 */     if (CollectionUtils.isEmpty(coll)) {
/* 1101 */       return "";
/*      */     }
/* 1103 */     StringBuilder sb = new StringBuilder();
/* 1104 */     Iterator<?> it = coll.iterator();
/* 1105 */     while (it.hasNext()) {
/* 1106 */       sb.append(prefix).append(it.next()).append(suffix);
/* 1107 */       if (it.hasNext()) {
/* 1108 */         sb.append(delim);
/*      */       }
/*      */     } 
/* 1111 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String collectionToDelimitedString(Collection<?> coll, String delim) {
/* 1122 */     return collectionToDelimitedString(coll, delim, "", "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String collectionToCommaDelimitedString(Collection<?> coll) {
/* 1132 */     return collectionToDelimitedString(coll, ",");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String arrayToDelimitedString(Object[] arr, String delim) {
/* 1143 */     if (ObjectUtils.isEmpty(arr)) {
/* 1144 */       return "";
/*      */     }
/* 1146 */     if (arr.length == 1) {
/* 1147 */       return ObjectUtils.nullSafeToString(arr[0]);
/*      */     }
/* 1149 */     StringBuilder sb = new StringBuilder();
/* 1150 */     for (int i = 0; i < arr.length; i++) {
/* 1151 */       if (i > 0) {
/* 1152 */         sb.append(delim);
/*      */       }
/* 1154 */       sb.append(arr[i]);
/*      */     } 
/* 1156 */     return sb.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String arrayToCommaDelimitedString(Object[] arr) {
/* 1166 */     return arrayToDelimitedString(arr, ",");
/*      */   }
/*      */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/StringUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */