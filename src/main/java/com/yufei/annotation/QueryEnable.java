package com.yufei.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface QueryEnable {
  boolean enable();
}


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/annotation/QueryEnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */