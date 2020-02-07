package com.yufei.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ExcelFieldOrder {
  String fieldOrders();
}


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/annotation/ExcelFieldOrder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */