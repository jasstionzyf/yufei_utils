/*    */ package com.yufei.utils.tree;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
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
/*    */ public class TreeNode<T>
/*    */   implements Serializable
/*    */ {
/*    */   private T value;
/* 22 */   private List<TreeNode> parents = new ArrayList<>();
/* 23 */   private List<TreeNode> childs = new ArrayList<>();
/*    */   
/*    */   public TreeNode(T value) {
/* 26 */     this.value = value;
/*    */   }
/*    */   
/*    */   public T getValue() {
/* 30 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(T value) {
/* 34 */     this.value = value;
/*    */   }
/*    */   
/*    */   public List<TreeNode> getParents() {
/* 38 */     return this.parents;
/*    */   }
/*    */   
/*    */   public void setParents(List<TreeNode> parents) {
/* 42 */     this.parents = parents;
/*    */   }
/*    */   
/*    */   public List<TreeNode> getChilds() {
/* 46 */     return this.childs;
/*    */   }
/*    */   
/*    */   public void setChilds(List<TreeNode> childs) {
/* 50 */     this.childs = childs;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 55 */     return this.value.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 60 */     if (obj == null) {
/* 61 */       return false;
/*    */     }
/* 63 */     if (getClass() != obj.getClass()) {
/* 64 */       return false;
/*    */     }
/* 66 */     TreeNode<?> other = (TreeNode)obj;
/* 67 */     if (!Objects.equals(this.value, other.value)) {
/* 68 */       return false;
/*    */     }
/* 70 */     return true;
/*    */   }
/*    */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/tree/TreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */