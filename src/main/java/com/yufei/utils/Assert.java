/*     */ package com.yufei.utils;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Assert
/*     */ {
/*     */   public static void isTrue(boolean expression, String message) {
/*  57 */     if (!expression) {
/*  58 */       throw new IllegalArgumentException(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void isTrue(boolean expression) {
/*  70 */     isTrue(expression, "[Assertion failed] - this expression must be true");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void isNull(Object object, String message) {
/*  81 */     if (object != null) {
/*  82 */       throw new IllegalArgumentException(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void isNull(Object object) {
/*  93 */     isNull(object, "[Assertion failed] - the object argument must be null");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void notNull(Object object, String message) {
/* 104 */     if (object == null) {
/* 105 */       throw new IllegalArgumentException(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void notNull(Object object) {
/* 116 */     notNull(object, "[Assertion failed] - this argument is required; it must not be null");
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
/*     */   public static void hasLength(String text, String message) {
/* 128 */     if (!StringUtils.hasLength(text)) {
/* 129 */       throw new IllegalArgumentException(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void hasLength(String text) {
/* 141 */     hasLength(text, "[Assertion failed] - this String argument must have length; it must not be null or empty");
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
/*     */   public static void hasText(String text, String message) {
/* 154 */     if (!StringUtils.hasText(text)) {
/* 155 */       throw new IllegalArgumentException(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void hasText(String text) {
/* 167 */     hasText(text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
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
/*     */   public static void doesNotContain(String textToSearch, String substring, String message) {
/* 179 */     if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) && textToSearch
/* 180 */       .contains(substring)) {
/* 181 */       throw new IllegalArgumentException(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void doesNotContain(String textToSearch, String substring) {
/* 192 */     doesNotContain(textToSearch, substring, "[Assertion failed] - this String argument must not contain the substring [" + substring + "]");
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
/*     */   public static void notEmpty(Object[] array, String message) {
/* 206 */     if (ObjectUtils.isEmpty(array)) {
/* 207 */       throw new IllegalArgumentException(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void notEmpty(Object[] array) {
/* 219 */     notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
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
/*     */   public static void noNullElements(Object[] array, String message) {
/* 231 */     if (array != null) {
/* 232 */       for (Object element : array) {
/* 233 */         if (element == null) {
/* 234 */           throw new IllegalArgumentException(message);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void noNullElements(Object[] array) {
/* 248 */     noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
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
/*     */   public static void notEmpty(Collection collection, String message) {
/* 260 */     if (CollectionUtils.isEmpty(collection)) {
/* 261 */       throw new IllegalArgumentException(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void notEmpty(Collection collection) {
/* 273 */     notEmpty(collection, "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
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
/*     */   public static void notEmpty(Map map, String message) {
/* 286 */     if (CollectionUtils.isEmpty(map)) {
/* 287 */       throw new IllegalArgumentException(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void notEmpty(Map map) {
/* 299 */     notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
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
/*     */   public static void isInstanceOf(Class<?> clazz, Object obj) {
/* 312 */     isInstanceOf(clazz, obj, "");
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
/*     */   public static void isInstanceOf(Class<?> type, Object obj, String message) {
/* 328 */     notNull(type, "Type to check against must not be null");
/* 329 */     if (!type.isInstance(obj)) {
/* 330 */       throw new IllegalArgumentException((
/* 331 */           StringUtils.hasLength(message) ? (message + " ") : "") + "Object of class [" + ((obj != null) ? obj
/* 332 */           .getClass().getName() : "null") + "] must be an instance of " + type);
/*     */     }
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
/*     */   public static void isAssignable(Class<?> superType, Class<?> subType) {
/* 345 */     isAssignable(superType, subType, "");
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
/*     */   public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
/* 360 */     notNull(superType, "Type to check against must not be null");
/* 361 */     if (subType == null || !superType.isAssignableFrom(subType)) {
/* 362 */       throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
/*     */     }
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
/*     */   public static void state(boolean expression, String message) {
/* 377 */     if (!expression) {
/* 378 */       throw new IllegalStateException(message);
/*     */     }
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
/*     */   public static void state(boolean expression) {
/* 392 */     state(expression, "[Assertion failed] - this state invariant must be true");
/*     */   }
/*     */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/Assert.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */