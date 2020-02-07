/*     */ package com.yufei.utils;
/*     */ 
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.lang.StringUtils;
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
/*     */ public class DomainUtil
/*     */ {
/*     */   private static final String PROTOCOL = "://";
/*     */   private static final String SLASH = "/";
/*     */   private static final String PORT = ":";
/*     */   private static final String DOT = "\\.";
/*     */   private static final String DOT2 = ".";
/*  28 */   public static final Pattern IPPattern = Pattern.compile("[\\d]{1,3}\\.[\\d]{1,3}\\.[\\d]{1,3}\\.[\\d]{1,3}");
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DomainPostfixs
/*     */   {
/*  34 */     public static final Pattern POSTFIXS = Pattern.compile("^(com)+|(org)+|(net)+|(edu)+|(mil)+|(int)+|(pro)+|(idv)+|(biz)+|(museum)+|(coop)+|(aero)+|(info)+|(name)+|(cc)+|(tv)+|(gov)+|(travel)+|(asia)+|(jobs)+|(mobi)+|(tel)+|(cat)+$");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  39 */     public static final Pattern LOCATION_POSTFIXS = Pattern.compile("^(ac)+|(ad)+|(ae)+|(af)+|(ag)+|(ai)+|(al)+|(am)+|(an)+|(ao)+|(aq)+|(ar)+|(as)+|(at)+|(au)+|(aw)+|(ax)+|(az)+|(ba)+|(bb)+|(bd)+|(be)+|(bf)+|(bg)+|(bh)+|(bi)+|(bj)+|(bm)+|(bn)+|(bo)+|(br)+|(bs)+|(bt)+|(bv)+|(bw)+|(by)+|(bz)+|(ca)+|(cc)+|(cd)+|(cf)+|(cg)+|(ch)+|(ci)+|(ck)+|(cl)+|(cm)+|(cn)+|(co)+|(cr)+|(cu)+|(cv)+|(cx)+|(cy)+|(cz)+|(de)+|(dj)+|(dk)+|(dm)+|(do)+|(dz)+|(ec)+|(ee)+|(eg)+|(eh)+|(er)+|(es)+|(et)+|(eu)+|(fi)+|(fj)+|(fk)+|(fm)+|(fo)+|(fr)+|(ga)+|(gb)+|(gd)+|(ge)+|(gf)+|(gg)+|(gh)+|(gi)+|(gl)+|(gm)+|(gn)+|(gp)+|(gq)+|(gr)+|(gs)+|(gt)+|(gu)+|(gw)+|(gy)+|(hk)+|(hm)+|(hn)+|(hr)+|(ht)+|(hu)+|(id)+|(ie)+|(il)+|(im)+|(in)+|(io)+|(iq)+|(ir)+|(is)+|(it)+|(je)+|(jm)+|(jo)+|(jp)+|(ke)+|(kg)+|(kh)+|(ki)+|(km)+|(kn)+|(kp)+|(kr)+|(kw)+|(ky)+|(kz)+|(la)+|(lb)+|(lc)+|(li)+|(lk)+|(lr)+|(ls)+|(lt)+|(lu)+|(lv)+|(ly)+|(ma)+|(mc)+|(md)+|(me)+|(mg)+|(mh)+|(mk)+|(ml)+|(mm)+|(mn)+|(mo)+|(mp)+|(mq)+|(mr)+|(ms)+|(mt)+|(mu)+|(mv)+|(mw)+|(mx)+|(my)+|(mz)+|(na)+|(nc)+|(ne)+|(nf)+|(ng)+|(ni)+|(nl)+|(no)+|(np)+|(nr)+|(nu)+|(nz)+|(om)+|(pa)+|(pe)+|(pf)+|(pg)+|(ph)+|(pk)+|(pl)+|(pm)+|(pn)+|(pr)+|(ps)+|(pt)+|(pw)+|(py)+|(qa)+|(re)+|(ro)+|(rs)+|(ru)+|(rw)+|(sa)+|(sb)+|(sc)+|(sd)+|(se)+|(sg)+|(sh)+|(si)+|(sj)+|(sk)+|(sl)+|(sm)+|(sn)+|(so)+|(sr)+|(st)+|(su)+|(sv)+|(sy)+|(sz)+|(tc)+|(td)+|(tf)+|(tg)+|(th)+|(tj)+|(tk)+|(tl)+|(tm)+|(tn)+|(to)+|(tp)+|(tr)+|(tt)+|(tv)+|(tw)+|(tz)+|(ua)+|(ug)+|(uk)+|(um)+|(us)+|(uy)+|(uz)+|(va)+|(vc)+|(ve)+|(vg)+|(vi)+|(vn)+|(vu)+|(wf)+|(ws)+|(ye)+|(yt)+|(yu)+|(za)+|(zm)+|(zw)+$");
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
/*     */     public static boolean findPostfixs(String domainSegment) {
/*  65 */       return POSTFIXS.matcher(domainSegment).matches();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static boolean isLocaionPostfixs(String domainSegment) {
/*  75 */       return LOCATION_POSTFIXS.matcher(domainSegment).matches();
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
/*     */   public static String removeUrlProtocol(String url) {
/*  88 */     StringBuilder sb = new StringBuilder(url.toLowerCase());
/*     */     
/*  90 */     if (sb.indexOf("://") >= 0)
/*     */     {
/*  92 */       sb.delete(0, sb.indexOf("://") + "://".length());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     if (sb.indexOf("/") >= 0)
/*     */     {
/* 102 */       sb.delete(sb.indexOf("/"), sb.length());
/*     */     }
/* 104 */     return sb.toString();
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
/*     */   public static String removeUrlProtocolAndPort(String url) {
/* 116 */     StringBuilder sb = new StringBuilder(url.toLowerCase());
/*     */     
/* 118 */     if (sb.indexOf("://") >= 0)
/*     */     {
/* 120 */       sb.delete(0, sb.indexOf("://") + "://".length());
/*     */     }
/*     */     
/* 123 */     if (sb.indexOf(":") >= 0)
/*     */     {
/* 125 */       sb.delete(sb.indexOf(":"), sb.length());
/*     */     }
/*     */     
/* 128 */     if (sb.indexOf("/") >= 0)
/*     */     {
/* 130 */       sb.delete(sb.indexOf("/"), sb.length());
/*     */     }
/* 132 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getSite(String url) {
/* 137 */     if (StringUtils.isNotEmpty(url))
/*     */     {
/* 139 */       return removeUrlProtocolAndPort(url);
/*     */     }
/*     */     
/* 142 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getTopDomain(String url) {
/* 152 */     if (!StringUtils.isNotEmpty(url.trim()))
/*     */     {
/* 154 */       return null;
/*     */     }
/*     */     
/* 157 */     String tmp1 = removeUrlProtocolAndPort(url);
/* 158 */     if (isIp(tmp1)) {
/*     */ 
/*     */       
/* 161 */       String tmp2 = removeUrlProtocol(url);
/* 162 */       if (tmp1.equals(tmp2))
/*     */       {
/* 164 */         return tmp1;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 169 */       return tmp2;
/*     */     } 
/*     */ 
/*     */     
/* 173 */     String domain_tmp = tmp1;
/* 174 */     removeUrlProtocolAndPort(url);
/* 175 */     if (isIp(domain_tmp))
/*     */     {
/* 177 */       return domain_tmp;
/*     */     }
/* 179 */     String[] domain_segments = domain_tmp.split("\\.");
/* 180 */     String domainReturn = null;
/*     */     
/* 182 */     if (domain_segments.length <= 1) {
/* 183 */       return null;
/*     */     }
/* 185 */     int lastIndex = domain_segments.length - 1;
/*     */     
/* 187 */     if (domain_segments.length >= 2) {
/*     */ 
/*     */       
/* 190 */       if (DomainPostfixs.findPostfixs(domain_segments[lastIndex])) {
/*     */         
/* 192 */         domainReturn = domain_segments[lastIndex - 1] + "." + domain_segments[lastIndex];
/* 193 */         return domainReturn;
/*     */       } 
/*     */       
/* 196 */       if (DomainPostfixs.isLocaionPostfixs(domain_segments[lastIndex])) {
/*     */         
/* 198 */         if (DomainPostfixs.findPostfixs(domain_segments[lastIndex - 1])) {
/* 199 */           if (domain_segments.length >= 3) {
/*     */             
/* 201 */             domainReturn = domain_segments[lastIndex - 2] + "." + domain_segments[lastIndex - 1] + "." + domain_segments[lastIndex];
/*     */           }
/*     */           else {
/*     */             
/* 205 */             domainReturn = domain_segments[lastIndex - 1] + "." + domain_segments[lastIndex];
/*     */           } 
/*     */           
/* 208 */           return domainReturn;
/*     */         } 
/*     */         
/* 211 */         domainReturn = domain_segments[lastIndex - 1] + "." + domain_segments[lastIndex];
/* 212 */         return domainReturn;
/*     */       } 
/*     */     } 
/* 215 */     return null;
/*     */   }
/*     */   
/*     */   public static boolean isSite(String url) {
/* 219 */     if (url == null || "".equals(url)) {
/* 220 */       return false;
/*     */     }
/* 222 */     if (url.endsWith("/")) {
/* 223 */       return false;
/*     */     }
/* 225 */     StringBuffer sb = new StringBuffer(url);
/* 226 */     boolean flag = true;
/* 227 */     if (StringUtils.isNotEmpty(getTopDomain(url))) {
/* 228 */       if (sb.indexOf("://") >= 0)
/*     */       {
/* 230 */         sb.delete(0, sb.indexOf("://") + "://".length());
/*     */       }
/* 232 */       if (url.endsWith("/")) {
/* 233 */         sb.delete(sb.lastIndexOf("/"), sb.lastIndexOf("/") + 1);
/*     */       }
/* 235 */       if (!sb.toString().trim().equalsIgnoreCase(getSite(url))) {
/* 236 */         if (sb.indexOf("/index") >= 0) {
/* 237 */           sb.delete(sb.indexOf("/index"), sb.length());
/*     */         }
/* 239 */         if (sb.indexOf("/default") >= 0) {
/* 240 */           sb.delete(sb.indexOf("/default"), sb.length());
/*     */         }
/* 242 */         if (sb.indexOf("/home") >= 0) {
/* 243 */           sb.delete(sb.indexOf("/home"), sb.length());
/*     */         }
/* 245 */         flag = sb.toString().trim().equalsIgnoreCase(getSite(url));
/*     */       } 
/*     */     } else {
/*     */       
/* 249 */       flag = (StringUtils.countMatches(url, "/") <= 1 && (url.indexOf("index") >= 0 || url.indexOf("default") >= 0 || url.indexOf("home") >= 0));
/*     */     } 
/* 251 */     return flag;
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
/*     */   public static boolean isIp(String url) {
/* 263 */     return IPPattern.matcher(url).matches();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 271 */     String url = "http://www.yufei.com/index.html/";
/* 272 */     System.out.println(getTopDomain(url));
/*     */   }
/*     */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/DomainUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */