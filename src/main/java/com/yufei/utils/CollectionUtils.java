/*     */ package com.yufei.utils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CollectionUtils
/*     */ {
/*     */   public static boolean isEmpty(Collection collection) {
/*  37 */     return (collection == null || collection.isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEmpty(Map map) {
/*  47 */     return (map == null || map.isEmpty());
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
/*     */   public static List arrayToList(Object source) {
/*  60 */     return Arrays.asList(ObjectUtils.toObjectArray(source));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void mergeArrayIntoCollection(Object array, Collection<Object> collection) {
/*  70 */     if (collection == null) {
/*  71 */       throw new IllegalArgumentException("Collection must not be null");
/*     */     }
/*  73 */     Object[] arr = ObjectUtils.toObjectArray(array);
/*  74 */     for (Object elem : arr) {
/*  75 */       collection.add(elem);
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
/*     */   public static void mergePropertiesIntoMap(Properties props, Map<String, Object> map) {
/*  89 */     if (map == null) {
/*  90 */       throw new IllegalArgumentException("Map must not be null");
/*     */     }
/*  92 */     if (props != null) {
/*  93 */       for (Enumeration<?> en = props.propertyNames(); en.hasMoreElements(); ) {
/*  94 */         String key = (String)en.nextElement();
/*  95 */         Object value = props.getProperty(key);
/*  96 */         if (value == null)
/*     */         {
/*  98 */           value = props.get(key);
/*     */         }
/* 100 */         map.put(key, value);
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
/*     */   public static boolean contains(Iterator iterator, Object element) {
/* 113 */     if (iterator != null) {
/* 114 */       while (iterator.hasNext()) {
/* 115 */         Object candidate = iterator.next();
/* 116 */         if (ObjectUtils.nullSafeEquals(candidate, element)) {
/* 117 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 121 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean contains(Enumeration enumeration, Object element) {
/* 131 */     if (enumeration != null) {
/* 132 */       while (enumeration.hasMoreElements()) {
/* 133 */         Object candidate = enumeration.nextElement();
/* 134 */         if (ObjectUtils.nullSafeEquals(candidate, element)) {
/* 135 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 139 */     return false;
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
/*     */   public static boolean containsInstance(Collection collection, Object element) {
/* 151 */     if (collection != null) {
/* 152 */       for (Object candidate : collection) {
/* 153 */         if (candidate == element) {
/* 154 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 158 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean containsAny(Collection source, Collection candidates) {
/* 169 */     if (isEmpty(source) || isEmpty(candidates)) {
/* 170 */       return false;
/*     */     }
/* 172 */     for (Object candidate : candidates) {
/* 173 */       if (source.contains(candidate)) {
/* 174 */         return true;
/*     */       }
/*     */     } 
/* 177 */     return false;
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
/*     */   public static Object findFirstMatch(Collection source, Collection candidates) {
/* 190 */     if (isEmpty(source) || isEmpty(candidates)) {
/* 191 */       return null;
/*     */     }
/* 193 */     for (Object candidate : candidates) {
/* 194 */       if (source.contains(candidate)) {
/* 195 */         return candidate;
/*     */       }
/*     */     } 
/* 198 */     return null;
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
/*     */   public static <T> T findValueOfType(Collection<?> collection, Class<T> type) {
/* 210 */     if (isEmpty(collection)) {
/* 211 */       return null;
/*     */     }
/* 213 */     T value = null;
/* 214 */     for (Object element : collection) {
/* 215 */       if (type == null || type.isInstance(element)) {
/* 216 */         if (value != null)
/*     */         {
/* 218 */           return null;
/*     */         }
/* 220 */         value = (T)element;
/*     */       } 
/*     */     } 
/* 223 */     return value;
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
/*     */   public static Object findValueOfType(Collection<?> collection, Class<?>[] types) {
/* 236 */     if (isEmpty(collection) || ObjectUtils.isEmpty((Object[])types)) {
/* 237 */       return null;
/*     */     }
/* 239 */     for (Class<?> type : types) {
/* 240 */       Object value = findValueOfType(collection, type);
/* 241 */       if (value != null) {
/* 242 */         return value;
/*     */       }
/*     */     } 
/* 245 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasUniqueObject(Collection collection) {
/* 255 */     if (isEmpty(collection)) {
/* 256 */       return false;
/*     */     }
/* 258 */     boolean hasCandidate = false;
/* 259 */     Object candidate = null;
/* 260 */     for (Object elem : collection) {
/* 261 */       if (!hasCandidate) {
/* 262 */         hasCandidate = true;
/* 263 */         candidate = elem; continue;
/*     */       } 
/* 265 */       if (candidate != elem) {
/* 266 */         return false;
/*     */       }
/*     */     } 
/* 269 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Class<?> findCommonElementType(Collection collection) {
/* 279 */     if (isEmpty(collection)) {
/* 280 */       return null;
/*     */     }
/* 282 */     Class<?> candidate = null;
/* 283 */     for (Object val : collection) {
/* 284 */       if (val != null) {
/* 285 */         if (candidate == null) {
/* 286 */           candidate = val.getClass(); continue;
/*     */         } 
/* 288 */         if (candidate != val.getClass()) {
/* 289 */           return null;
/*     */         }
/*     */       } 
/*     */     } 
/* 293 */     return candidate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <A, E extends A> A[] toArray(Enumeration<E> enumeration, A[] array) {
/* 302 */     ArrayList<A> elements = new ArrayList<>();
/* 303 */     while (enumeration.hasMoreElements()) {
/* 304 */       elements.add((A)enumeration.nextElement());
/*     */     }
/* 306 */     return elements.toArray(array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <E> Iterator<E> toIterator(Enumeration<E> enumeration) {
/* 315 */     return new EnumerationIterator<>(enumeration);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class EnumerationIterator<E>
/*     */     implements Iterator<E>
/*     */   {
/*     */     private Enumeration<E> enumeration;
/*     */ 
/*     */ 
/*     */     
/*     */     public EnumerationIterator(Enumeration<E> enumeration) {
/* 329 */       this.enumeration = enumeration;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 333 */       return this.enumeration.hasMoreElements();
/*     */     }
/*     */     
/*     */     public E next() {
/* 337 */       return this.enumeration.nextElement();
/*     */     }
/*     */     
/*     */     public void remove() throws UnsupportedOperationException {
/* 341 */       throw new UnsupportedOperationException("Not supported");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/CollectionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */