/*    */ package com.yufei.utils;
/*    */ 
/*    */ import java.net.URI;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class URIResolver
/*    */ {
/* 27 */   protected final Log log = LogFactory.getLog(getClass());
/* 28 */   static final String[] IGNORED_PREFIX = new String[] { "javascript:", "mailto:" };
/*    */   protected URI base;
/* 30 */   protected LinkNormalizer linkNormalizer = new LinkNormalizer();
/*    */   
/*    */   public URIResolver(URI base) {
/* 33 */     if (base == null) throw new IllegalArgumentException("base cannot be null"); 
/* 34 */     this.base = base;
/*    */   }
/*    */   
/*    */   public URI resolve(String link) {
/* 38 */     URI result = null;
/*    */     try {
/* 40 */       String target = this.linkNormalizer.normalize(link);
/* 41 */       target = this.linkNormalizer.escape(target);
/*    */       
/* 43 */       for (String ignored : IGNORED_PREFIX) {
/* 44 */         if (target.indexOf(ignored) != -1) return null;
/*    */       
/*    */       } 
/* 47 */       int queryStringIndex = target.indexOf('?');
/* 48 */       int namedAnchorIndex = (target.length() > 0) ? target.indexOf('#') : -1;
/* 49 */       if (namedAnchorIndex != -1 && namedAnchorIndex > queryStringIndex) target = target.substring(0, namedAnchorIndex);
/*    */       
/* 51 */       if (target.length() == 0) {
/* 52 */         result = this.base;
/* 53 */       } else if (target.substring(0, (queryStringIndex != -1) ? queryStringIndex : target.length()).indexOf("://") != -1) {
/* 54 */         result = new URI(target);
/*    */       } else {
/* 56 */         if (target.charAt(0) == '?') {
/* 57 */           target = this.base.getPath() + target;
/* 58 */         } else if (target.startsWith("./")) {
/* 59 */           target = target.substring("./".length(), target.length());
/*    */         } 
/*    */         
/* 62 */         if ((this.base.getPath() == null || "".equals(this.base.getPath())) && !target.startsWith("/")) {
/* 63 */           target = "/" + target;
/*    */         }
/*    */ 
/*    */         
/* 67 */         result = this.base.resolve(target);
/*    */       } 
/* 69 */     } catch (Exception e) {
/* 70 */       this.log.error("URI is not valid - link: " + link + ", base: " + this.base + ", e: " + e.getMessage());
/*    */     } 
/* 72 */     if (this.log.isTraceEnabled())
/* 73 */       this.log.trace("resolve() - resolved - result: " + result + ", link: " + link + ", base: " + this.base); 
/* 74 */     return result;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 78 */     return super.toString() + ": base: " + this.base;
/*    */   }
/*    */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/URIResolver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */