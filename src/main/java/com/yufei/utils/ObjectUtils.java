/*     */ package com.yufei.utils;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ObjectUtils
/*     */ {
/*     */   private static final int INITIAL_HASH = 7;
/*     */   private static final int MULTIPLIER = 31;
/*     */   private static final String EMPTY_STRING = "";
/*     */   private static final String NULL_STRING = "null";
/*     */   private static final String ARRAY_START = "{";
/*     */   private static final String ARRAY_END = "}";
/*     */   private static final String EMPTY_ARRAY = "{}";
/*     */   private static final String ARRAY_ELEMENT_SEPARATOR = ", ";
/*     */   
/*     */   public static boolean isCheckedException(Throwable ex) {
/*  65 */     return (!(ex instanceof RuntimeException) && !(ex instanceof Error));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCompatibleWithThrowsClause(Throwable ex, Class<?>... declaredExceptions) {
/*  76 */     if (!isCheckedException(ex)) {
/*  77 */       return true;
/*     */     }
/*  79 */     if (declaredExceptions != null) {
/*  80 */       for (Class<?> declaredException : declaredExceptions) {
/*  81 */         if (declaredException.isInstance(ex)) {
/*  82 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isArray(Object obj) {
/*  95 */     return (obj != null && obj.getClass().isArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEmpty(Object[] array) {
/* 104 */     return (array == null || array.length == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean containsElement(Object[] array, Object element) {
/* 115 */     if (array == null) {
/* 116 */       return false;
/*     */     }
/* 118 */     for (Object arrayEle : array) {
/* 119 */       if (nullSafeEquals(arrayEle, element)) {
/* 120 */         return true;
/*     */       }
/*     */     } 
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean containsConstant(Enum<?>[] enumValues, String constant) {
/* 134 */     return containsConstant(enumValues, constant, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean containsConstant(Enum<?>[] enumValues, String constant, boolean caseSensitive) {
/* 145 */     for (Enum<?> candidate : enumValues) {
/* 146 */       if (caseSensitive ? candidate
/* 147 */         .toString().equals(constant) : candidate
/* 148 */         .toString().equalsIgnoreCase(constant)) {
/* 149 */         return true;
/*     */       }
/*     */     } 
/* 152 */     return false;
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
/*     */   public static <E extends Enum<?>> E caseInsensitiveValueOf(E[] enumValues, String constant) {
/* 164 */     for (E candidate : enumValues) {
/* 165 */       if (candidate.toString().equalsIgnoreCase(constant)) {
/* 166 */         return candidate;
/*     */       }
/*     */     } 
/* 169 */     throw new IllegalArgumentException(
/* 170 */         String.format("constant [%s] does not exist in enum type %s", new Object[] {
/* 171 */             constant, enumValues.getClass().getComponentType().getName()
/*     */           }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <A, O extends A> A[] addObjectToArray(A[] array, O obj) {
/* 182 */     Class<?> compType = Object.class;
/* 183 */     if (array != null) {
/* 184 */       compType = array.getClass().getComponentType();
/*     */     }
/* 186 */     else if (obj != null) {
/* 187 */       compType = obj.getClass();
/*     */     } 
/* 189 */     int newArrLength = (array != null) ? (array.length + 1) : 1;
/*     */     
/* 191 */     A[] newArr = (A[])Array.newInstance(compType, newArrLength);
/* 192 */     if (array != null) {
/* 193 */       System.arraycopy(array, 0, newArr, 0, array.length);
/*     */     }
/* 195 */     newArr[newArr.length - 1] = (A)obj;
/* 196 */     return newArr;
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
/*     */   public static Object[] toObjectArray(Object source) {
/* 209 */     if (source instanceof Object[]) {
/* 210 */       return (Object[])source;
/*     */     }
/* 212 */     if (source == null) {
/* 213 */       return new Object[0];
/*     */     }
/* 215 */     if (!source.getClass().isArray()) {
/* 216 */       throw new IllegalArgumentException("Source is not an array: " + source);
/*     */     }
/* 218 */     int length = Array.getLength(source);
/* 219 */     if (length == 0) {
/* 220 */       return new Object[0];
/*     */     }
/* 222 */     Class<?> wrapperType = Array.get(source, 0).getClass();
/* 223 */     Object[] newArray = (Object[])Array.newInstance(wrapperType, length);
/* 224 */     for (int i = 0; i < length; i++) {
/* 225 */       newArray[i] = Array.get(source, i);
/*     */     }
/* 227 */     return newArray;
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
/*     */   public static boolean nullSafeEquals(Object o1, Object o2) {
/* 247 */     if (o1 == o2) {
/* 248 */       return true;
/*     */     }
/* 250 */     if (o1 == null || o2 == null) {
/* 251 */       return false;
/*     */     }
/* 253 */     if (o1.equals(o2)) {
/* 254 */       return true;
/*     */     }
/* 256 */     if (o1.getClass().isArray() && o2.getClass().isArray()) {
/* 257 */       if (o1 instanceof Object[] && o2 instanceof Object[]) {
/* 258 */         return Arrays.equals((Object[])o1, (Object[])o2);
/*     */       }
/* 260 */       if (o1 instanceof boolean[] && o2 instanceof boolean[]) {
/* 261 */         return Arrays.equals((boolean[])o1, (boolean[])o2);
/*     */       }
/* 263 */       if (o1 instanceof byte[] && o2 instanceof byte[]) {
/* 264 */         return Arrays.equals((byte[])o1, (byte[])o2);
/*     */       }
/* 266 */       if (o1 instanceof char[] && o2 instanceof char[]) {
/* 267 */         return Arrays.equals((char[])o1, (char[])o2);
/*     */       }
/* 269 */       if (o1 instanceof double[] && o2 instanceof double[]) {
/* 270 */         return Arrays.equals((double[])o1, (double[])o2);
/*     */       }
/* 272 */       if (o1 instanceof float[] && o2 instanceof float[]) {
/* 273 */         return Arrays.equals((float[])o1, (float[])o2);
/*     */       }
/* 275 */       if (o1 instanceof int[] && o2 instanceof int[]) {
/* 276 */         return Arrays.equals((int[])o1, (int[])o2);
/*     */       }
/* 278 */       if (o1 instanceof long[] && o2 instanceof long[]) {
/* 279 */         return Arrays.equals((long[])o1, (long[])o2);
/*     */       }
/* 281 */       if (o1 instanceof short[] && o2 instanceof short[]) {
/* 282 */         return Arrays.equals((short[])o1, (short[])o2);
/*     */       }
/*     */     } 
/* 285 */     return false;
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
/*     */   public static int nullSafeHashCode(Object obj) {
/* 305 */     if (obj == null) {
/* 306 */       return 0;
/*     */     }
/* 308 */     if (obj.getClass().isArray()) {
/* 309 */       if (obj instanceof Object[]) {
/* 310 */         return nullSafeHashCode((Object[])obj);
/*     */       }
/* 312 */       if (obj instanceof boolean[]) {
/* 313 */         return nullSafeHashCode((boolean[])obj);
/*     */       }
/* 315 */       if (obj instanceof byte[]) {
/* 316 */         return nullSafeHashCode((byte[])obj);
/*     */       }
/* 318 */       if (obj instanceof char[]) {
/* 319 */         return nullSafeHashCode((char[])obj);
/*     */       }
/* 321 */       if (obj instanceof double[]) {
/* 322 */         return nullSafeHashCode((double[])obj);
/*     */       }
/* 324 */       if (obj instanceof float[]) {
/* 325 */         return nullSafeHashCode((float[])obj);
/*     */       }
/* 327 */       if (obj instanceof int[]) {
/* 328 */         return nullSafeHashCode((int[])obj);
/*     */       }
/* 330 */       if (obj instanceof long[]) {
/* 331 */         return nullSafeHashCode((long[])obj);
/*     */       }
/* 333 */       if (obj instanceof short[]) {
/* 334 */         return nullSafeHashCode((short[])obj);
/*     */       }
/*     */     } 
/* 337 */     return obj.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(Object[] array) {
/* 345 */     if (array == null) {
/* 346 */       return 0;
/*     */     }
/* 348 */     int hash = 7;
/* 349 */     for (Object element : array) {
/* 350 */       hash = 31 * hash + nullSafeHashCode(element);
/*     */     }
/* 352 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(boolean[] array) {
/* 360 */     if (array == null) {
/* 361 */       return 0;
/*     */     }
/* 363 */     int hash = 7;
/* 364 */     for (boolean element : array) {
/* 365 */       hash = 31 * hash + hashCode(element);
/*     */     }
/* 367 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(byte[] array) {
/* 375 */     if (array == null) {
/* 376 */       return 0;
/*     */     }
/* 378 */     int hash = 7;
/* 379 */     for (byte element : array) {
/* 380 */       hash = 31 * hash + element;
/*     */     }
/* 382 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(char[] array) {
/* 390 */     if (array == null) {
/* 391 */       return 0;
/*     */     }
/* 393 */     int hash = 7;
/* 394 */     for (char element : array) {
/* 395 */       hash = 31 * hash + element;
/*     */     }
/* 397 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(double[] array) {
/* 405 */     if (array == null) {
/* 406 */       return 0;
/*     */     }
/* 408 */     int hash = 7;
/* 409 */     for (double element : array) {
/* 410 */       hash = 31 * hash + hashCode(element);
/*     */     }
/* 412 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(float[] array) {
/* 420 */     if (array == null) {
/* 421 */       return 0;
/*     */     }
/* 423 */     int hash = 7;
/* 424 */     for (float element : array) {
/* 425 */       hash = 31 * hash + hashCode(element);
/*     */     }
/* 427 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(int[] array) {
/* 435 */     if (array == null) {
/* 436 */       return 0;
/*     */     }
/* 438 */     int hash = 7;
/* 439 */     for (int element : array) {
/* 440 */       hash = 31 * hash + element;
/*     */     }
/* 442 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(long[] array) {
/* 450 */     if (array == null) {
/* 451 */       return 0;
/*     */     }
/* 453 */     int hash = 7;
/* 454 */     for (long element : array) {
/* 455 */       hash = 31 * hash + hashCode(element);
/*     */     }
/* 457 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int nullSafeHashCode(short[] array) {
/* 465 */     if (array == null) {
/* 466 */       return 0;
/*     */     }
/* 468 */     int hash = 7;
/* 469 */     for (short element : array) {
/* 470 */       hash = 31 * hash + element;
/*     */     }
/* 472 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int hashCode(boolean bool) {
/* 480 */     return bool ? 1231 : 1237;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int hashCode(double dbl) {
/* 488 */     return hashCode(Double.doubleToLongBits(dbl));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int hashCode(float flt) {
/* 496 */     return Float.floatToIntBits(flt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int hashCode(long lng) {
/* 504 */     return (int)(lng ^ lng >>> 32L);
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
/*     */   public static String identityToString(Object obj) {
/* 519 */     if (obj == null) {
/* 520 */       return "";
/*     */     }
/* 522 */     return obj.getClass().getName() + "@" + getIdentityHexString(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getIdentityHexString(Object obj) {
/* 531 */     return Integer.toHexString(System.identityHashCode(obj));
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
/*     */   public static String getDisplayString(Object obj) {
/* 544 */     if (obj == null) {
/* 545 */       return "";
/*     */     }
/* 547 */     return nullSafeToString(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String nullSafeClassName(Object obj) {
/* 557 */     return (obj != null) ? obj.getClass().getName() : "null";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String nullSafeToString(Object obj) {
/* 568 */     if (obj == null) {
/* 569 */       return "null";
/*     */     }
/* 571 */     if (obj instanceof String) {
/* 572 */       return (String)obj;
/*     */     }
/* 574 */     if (obj instanceof Object[]) {
/* 575 */       return nullSafeToString((Object[])obj);
/*     */     }
/* 577 */     if (obj instanceof boolean[]) {
/* 578 */       return nullSafeToString((boolean[])obj);
/*     */     }
/* 580 */     if (obj instanceof byte[]) {
/* 581 */       return nullSafeToString((byte[])obj);
/*     */     }
/* 583 */     if (obj instanceof char[]) {
/* 584 */       return nullSafeToString((char[])obj);
/*     */     }
/* 586 */     if (obj instanceof double[]) {
/* 587 */       return nullSafeToString((double[])obj);
/*     */     }
/* 589 */     if (obj instanceof float[]) {
/* 590 */       return nullSafeToString((float[])obj);
/*     */     }
/* 592 */     if (obj instanceof int[]) {
/* 593 */       return nullSafeToString((int[])obj);
/*     */     }
/* 595 */     if (obj instanceof long[]) {
/* 596 */       return nullSafeToString((long[])obj);
/*     */     }
/* 598 */     if (obj instanceof short[]) {
/* 599 */       return nullSafeToString((short[])obj);
/*     */     }
/* 601 */     String str = obj.toString();
/* 602 */     return (str != null) ? str : "";
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
/*     */   public static String nullSafeToString(Object[] array) {
/* 615 */     if (array == null) {
/* 616 */       return "null";
/*     */     }
/* 618 */     int length = array.length;
/* 619 */     if (length == 0) {
/* 620 */       return "{}";
/*     */     }
/* 622 */     StringBuilder sb = new StringBuilder();
/* 623 */     for (int i = 0; i < length; i++) {
/* 624 */       if (i == 0) {
/* 625 */         sb.append("{");
/*     */       } else {
/*     */         
/* 628 */         sb.append(", ");
/*     */       } 
/* 630 */       sb.append(String.valueOf(array[i]));
/*     */     } 
/* 632 */     sb.append("}");
/* 633 */     return sb.toString();
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
/*     */   public static String nullSafeToString(boolean[] array) {
/* 646 */     if (array == null) {
/* 647 */       return "null";
/*     */     }
/* 649 */     int length = array.length;
/* 650 */     if (length == 0) {
/* 651 */       return "{}";
/*     */     }
/* 653 */     StringBuilder sb = new StringBuilder();
/* 654 */     for (int i = 0; i < length; i++) {
/* 655 */       if (i == 0) {
/* 656 */         sb.append("{");
/*     */       } else {
/*     */         
/* 659 */         sb.append(", ");
/*     */       } 
/*     */       
/* 662 */       sb.append(array[i]);
/*     */     } 
/* 664 */     sb.append("}");
/* 665 */     return sb.toString();
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
/*     */   public static String nullSafeToString(byte[] array) {
/* 678 */     if (array == null) {
/* 679 */       return "null";
/*     */     }
/* 681 */     int length = array.length;
/* 682 */     if (length == 0) {
/* 683 */       return "{}";
/*     */     }
/* 685 */     StringBuilder sb = new StringBuilder();
/* 686 */     for (int i = 0; i < length; i++) {
/* 687 */       if (i == 0) {
/* 688 */         sb.append("{");
/*     */       } else {
/*     */         
/* 691 */         sb.append(", ");
/*     */       } 
/* 693 */       sb.append(array[i]);
/*     */     } 
/* 695 */     sb.append("}");
/* 696 */     return sb.toString();
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
/*     */   public static String nullSafeToString(char[] array) {
/* 709 */     if (array == null) {
/* 710 */       return "null";
/*     */     }
/* 712 */     int length = array.length;
/* 713 */     if (length == 0) {
/* 714 */       return "{}";
/*     */     }
/* 716 */     StringBuilder sb = new StringBuilder();
/* 717 */     for (int i = 0; i < length; i++) {
/* 718 */       if (i == 0) {
/* 719 */         sb.append("{");
/*     */       } else {
/*     */         
/* 722 */         sb.append(", ");
/*     */       } 
/* 724 */       sb.append("'").append(array[i]).append("'");
/*     */     } 
/* 726 */     sb.append("}");
/* 727 */     return sb.toString();
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
/*     */   public static String nullSafeToString(double[] array) {
/* 740 */     if (array == null) {
/* 741 */       return "null";
/*     */     }
/* 743 */     int length = array.length;
/* 744 */     if (length == 0) {
/* 745 */       return "{}";
/*     */     }
/* 747 */     StringBuilder sb = new StringBuilder();
/* 748 */     for (int i = 0; i < length; i++) {
/* 749 */       if (i == 0) {
/* 750 */         sb.append("{");
/*     */       } else {
/*     */         
/* 753 */         sb.append(", ");
/*     */       } 
/*     */       
/* 756 */       sb.append(array[i]);
/*     */     } 
/* 758 */     sb.append("}");
/* 759 */     return sb.toString();
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
/*     */   public static String nullSafeToString(float[] array) {
/* 772 */     if (array == null) {
/* 773 */       return "null";
/*     */     }
/* 775 */     int length = array.length;
/* 776 */     if (length == 0) {
/* 777 */       return "{}";
/*     */     }
/* 779 */     StringBuilder sb = new StringBuilder();
/* 780 */     for (int i = 0; i < length; i++) {
/* 781 */       if (i == 0) {
/* 782 */         sb.append("{");
/*     */       } else {
/*     */         
/* 785 */         sb.append(", ");
/*     */       } 
/*     */       
/* 788 */       sb.append(array[i]);
/*     */     } 
/* 790 */     sb.append("}");
/* 791 */     return sb.toString();
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
/*     */   public static String nullSafeToString(int[] array) {
/* 804 */     if (array == null) {
/* 805 */       return "null";
/*     */     }
/* 807 */     int length = array.length;
/* 808 */     if (length == 0) {
/* 809 */       return "{}";
/*     */     }
/* 811 */     StringBuilder sb = new StringBuilder();
/* 812 */     for (int i = 0; i < length; i++) {
/* 813 */       if (i == 0) {
/* 814 */         sb.append("{");
/*     */       } else {
/*     */         
/* 817 */         sb.append(", ");
/*     */       } 
/* 819 */       sb.append(array[i]);
/*     */     } 
/* 821 */     sb.append("}");
/* 822 */     return sb.toString();
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
/*     */   public static String nullSafeToString(long[] array) {
/* 835 */     if (array == null) {
/* 836 */       return "null";
/*     */     }
/* 838 */     int length = array.length;
/* 839 */     if (length == 0) {
/* 840 */       return "{}";
/*     */     }
/* 842 */     StringBuilder sb = new StringBuilder();
/* 843 */     for (int i = 0; i < length; i++) {
/* 844 */       if (i == 0) {
/* 845 */         sb.append("{");
/*     */       } else {
/*     */         
/* 848 */         sb.append(", ");
/*     */       } 
/* 850 */       sb.append(array[i]);
/*     */     } 
/* 852 */     sb.append("}");
/* 853 */     return sb.toString();
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
/*     */   public static String nullSafeToString(short[] array) {
/* 866 */     if (array == null) {
/* 867 */       return "null";
/*     */     }
/* 869 */     int length = array.length;
/* 870 */     if (length == 0) {
/* 871 */       return "{}";
/*     */     }
/* 873 */     StringBuilder sb = new StringBuilder();
/* 874 */     for (int i = 0; i < length; i++) {
/* 875 */       if (i == 0) {
/* 876 */         sb.append("{");
/*     */       } else {
/*     */         
/* 879 */         sb.append(", ");
/*     */       } 
/* 881 */       sb.append(array[i]);
/*     */     } 
/* 883 */     sb.append("}");
/* 884 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/ObjectUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */