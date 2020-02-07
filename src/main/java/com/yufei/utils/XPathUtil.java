/*     */ package com.yufei.utils;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.StringReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.xml.namespace.NamespaceContext;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import javax.xml.xpath.XPath;
/*     */ import javax.xml.xpath.XPathConstants;
/*     */ import javax.xml.xpath.XPathExpression;
/*     */ import javax.xml.xpath.XPathExpressionException;
/*     */ import javax.xml.xpath.XPathFactory;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.bootstrap.DOMImplementationRegistry;
/*     */ import org.w3c.dom.ls.DOMImplementationLS;
/*     */ import org.w3c.dom.ls.LSSerializer;
/*     */ import org.xml.sax.EntityResolver;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XPathUtil
/*     */ {
/*     */   public static List<String> getNodeListTextByXPath(InputStream source, String xpath) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
/*  58 */     String value = null;
/*  59 */     DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
/*  60 */     domFactory.setNamespaceAware(false);
/*     */     
/*  62 */     DocumentBuilder builder = domFactory.newDocumentBuilder();
/*  63 */     Document doc = builder.parse(source);
/*     */     
/*  65 */     XPathFactory factory = XPathFactory.newInstance();
/*  66 */     XPath path = factory.newXPath();
/*     */     
/*  68 */     XPathExpression expr = path.compile(xpath);
/*     */     
/*  70 */     NodeList nodeList = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
/*  71 */     List<String> strs = new ArrayList<>();
/*  72 */     for (int i = 0; i < nodeList.getLength(); i++) {
/*  73 */       strs.add(nodeList.item(i).getTextContent());
/*     */     }
/*  75 */     return strs;
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
/*     */   public static List<String> getNodeListTextByXPath(String source, String xpath) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
/*  88 */     String value = null;
/*  89 */     DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
/*  90 */     domFactory.setNamespaceAware(false);
/*     */     
/*  92 */     InputSource inputSource = new InputSource(new StringReader(source));
/*  93 */     DocumentBuilder builder = domFactory.newDocumentBuilder();
/*  94 */     Document doc = builder.parse(inputSource);
/*     */     
/*  96 */     XPathFactory factory = XPathFactory.newInstance();
/*  97 */     XPath path = factory.newXPath();
/*     */     
/*  99 */     XPathExpression expr = path.compile(xpath);
/*     */     
/* 101 */     NodeList nodeList = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
/* 102 */     List<String> strs = new ArrayList<>();
/* 103 */     for (int i = 0; i < nodeList.getLength(); i++) {
/* 104 */       strs.add(nodeList.item(i).getTextContent());
/*     */     }
/* 106 */     return strs;
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
/*     */   public static String getNodeTextByXPath(InputStream source, String xpath) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
/* 120 */     String value = null;
/* 121 */     DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
/* 122 */     domFactory.setNamespaceAware(false);
/* 123 */     DocumentBuilder builder = domFactory.newDocumentBuilder();
/* 124 */     Document doc = builder.parse(source);
/*     */     
/* 126 */     XPathFactory factory = XPathFactory.newInstance();
/* 127 */     XPath path = factory.newXPath();
/*     */     
/* 129 */     XPathExpression expr = path.compile(xpath);
/*     */     
/* 131 */     Node node = (Node)expr.evaluate(doc, XPathConstants.NODE);
/* 132 */     if (node == null) {
/* 133 */       return null;
/*     */     }
/* 135 */     value = node.getTextContent();
/*     */     
/* 137 */     return value;
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
/*     */   public static NodeList getNodeListtByXPath(InputStream source, String xpath) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
/* 151 */     String value = null;
/* 152 */     DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
/* 153 */     domFactory.setNamespaceAware(false);
/* 154 */     DocumentBuilder builder = domFactory.newDocumentBuilder();
/* 155 */     builder.setEntityResolver(new EntityResolver()
/*     */         {
/*     */           
/*     */           public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException
/*     */           {
/* 160 */             return new InputSource(new StringReader(""));
/*     */           }
/*     */         });
/* 163 */     Document doc = builder.parse(source);
/*     */     
/* 165 */     XPathFactory factory = XPathFactory.newInstance();
/* 166 */     XPath path = factory.newXPath();
/*     */     
/* 168 */     XPathExpression expr = path.compile(xpath);
/*     */     
/* 170 */     NodeList nodeList = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
/*     */     
/* 172 */     return nodeList;
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
/*     */   public static NodeList getNodeListtByXPath(String source, String xpath) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
/* 186 */     String value = null;
/* 187 */     DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
/* 188 */     domFactory.setNamespaceAware(false);
/* 189 */     InputSource inputSource = new InputSource(new StringReader(source));
/*     */     
/* 191 */     DocumentBuilder builder = domFactory.newDocumentBuilder();
/* 192 */     Document doc = builder.parse(inputSource);
/*     */     
/* 194 */     XPathFactory factory = XPathFactory.newInstance();
/* 195 */     XPath path = factory.newXPath();
/*     */     
/* 197 */     XPathExpression expr = path.compile(xpath);
/*     */     
/* 199 */     NodeList nodeList = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
/*     */     
/* 201 */     return nodeList;
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
/*     */   public static void getNodeListtByNodeName(Node pNode, String nodeName, List<Node> nodeList) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
/* 217 */     Node node = null;
/* 218 */     if (pNode == null) {
/* 219 */       throw new IllegalArgumentException();
/*     */     }
/* 221 */     if (pNode.getNodeName().equals(nodeName)) {
/*     */       return;
/*     */     }
/* 224 */     NodeList childNodes = pNode.getChildNodes();
/* 225 */     if (childNodes == null || childNodes.getLength() == 0) {
/*     */       return;
/*     */     }
/* 228 */     for (int i = 0; i < childNodes.getLength(); i++) {
/* 229 */       if (childNodes.item(i).getNodeName().equals(nodeName)) {
/*     */         
/* 231 */         nodeList.add(childNodes.item(i));
/*     */       } else {
/* 233 */         getNodeListtByNodeName(childNodes.item(i), nodeName, nodeList);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NodeList getNodeListtByXPath(String source, String xpath, NamespaceContext namespaceContext) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
/* 252 */     String value = null;
/* 253 */     DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
/* 254 */     domFactory.setNamespaceAware(true);
/* 255 */     InputSource inputSource = new InputSource(new StringReader(source));
/*     */     
/* 257 */     DocumentBuilder builder = domFactory.newDocumentBuilder();
/* 258 */     Document doc = builder.parse(inputSource);
/*     */     
/* 260 */     XPathFactory factory = XPathFactory.newInstance();
/* 261 */     XPath path = factory.newXPath();
/* 262 */     path.setNamespaceContext(namespaceContext);
/*     */     
/* 264 */     XPathExpression expr = path.compile(xpath);
/*     */     
/* 266 */     NodeList nodeList = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
/*     */     
/* 268 */     return nodeList;
/*     */   }
/*     */   
/*     */   public static NodeList getNodeListtByXPath(InputStream inputSource, String xpath, NamespaceContext namespaceContext) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
/* 272 */     String value = null;
/* 273 */     DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
/* 274 */     domFactory.setNamespaceAware(true);
/*     */     
/* 276 */     DocumentBuilder builder = domFactory.newDocumentBuilder();
/* 277 */     Document doc = builder.parse(inputSource);
/*     */     
/* 279 */     XPathFactory factory = XPathFactory.newInstance();
/* 280 */     XPath path = factory.newXPath();
/* 281 */     path.setNamespaceContext(namespaceContext);
/*     */     
/* 283 */     XPathExpression expr = path.compile(xpath);
/*     */     
/* 285 */     NodeList nodeList = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
/*     */     
/* 287 */     return nodeList;
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
/*     */   public static String getNodeTextByXPath(String source, String xpath) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
/* 300 */     String value = null;
/* 301 */     DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
/* 302 */     domFactory.setNamespaceAware(false);
/*     */     
/* 304 */     InputSource inputSource = new InputSource(new StringReader(source));
/* 305 */     DocumentBuilder builder = domFactory.newDocumentBuilder();
/* 306 */     Document doc = builder.parse(inputSource);
/*     */     
/* 308 */     XPathFactory factory = XPathFactory.newInstance();
/* 309 */     XPath path = factory.newXPath();
/*     */     
/* 311 */     XPathExpression expr = path.compile(xpath);
/*     */     
/* 313 */     Node node = (Node)expr.evaluate(doc, XPathConstants.NODE);
/*     */     
/* 315 */     if (node == null) {
/* 316 */       return null;
/*     */     }
/* 318 */     return node.getTextContent();
/*     */   }
/*     */   
/*     */   public static Map<String, String> getMapOfNodeInfo(Node node) {
/* 322 */     Map<String, String> result = Maps.newHashMap();
/* 323 */     if (node == null) {
/* 324 */       return null;
/*     */     }
/* 326 */     if (node.getChildNodes().getLength() > 1) {
/* 327 */       throw new IllegalArgumentException("node should text node!");
/*     */     }
/* 329 */     NamedNodeMap attrs = node.getAttributes();
/* 330 */     if (attrs != null) {
/* 331 */       for (int i = 0; i < attrs.getLength(); i++) {
/* 332 */         Attr attribute = (Attr)attrs.item(i);
/* 333 */         result.put(attribute.getName(), attribute.getValue());
/*     */       } 
/*     */     }
/* 336 */     result.put("text", node.getTextContent());
/*     */     
/* 338 */     return result;
/*     */   }
/*     */   
/*     */   public static String getPlainXmlOfNode(Node node) {
/* 342 */     String result = null;
/* 343 */     if (node == null) {
/* 344 */       return null;
/*     */     }
/*     */     
/*     */     try {
/* 348 */       DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
/*     */       
/* 350 */       DOMImplementationLS lsImpl = (DOMImplementationLS)registry.getDOMImplementation("LS");
/* 351 */       LSSerializer serializer = lsImpl.createLSSerializer();
/* 352 */       result = serializer.writeToString(node);
/*     */     }
/* 354 */     catch (Exception ex) {
/* 355 */       Logger.getLogger(XPathUtil.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/* 357 */     return result;
/*     */   }
/*     */   public static String getStrOfNodeInfo(Node node) {
/* 360 */     if (node == null) {
/* 361 */       return null;
/*     */     }
/* 363 */     StringBuffer sb = new StringBuffer();
/* 364 */     if (node.getChildNodes().getLength() > 1) {
/* 365 */       throw new IllegalArgumentException("node should text node!");
/*     */     }
/* 367 */     NamedNodeMap attrs = node.getAttributes();
/* 368 */     if (attrs != null) {
/* 369 */       for (int i = 0; i < attrs.getLength(); i++) {
/* 370 */         Attr attribute = (Attr)attrs.item(i);
/*     */         
/* 372 */         sb.append(attribute.getName() + "=" + attribute.getValue() + "*");
/*     */       } 
/*     */     }
/*     */     
/* 376 */     sb.append("text=" + node.getTextContent() + "");
/*     */     
/* 378 */     return sb.toString();
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
/*     */   public static String getAttributeValue(Node node, String attrName) {
/* 390 */     String attrValue = null;
/* 391 */     NamedNodeMap attrs = node.getAttributes();
/* 392 */     if (attrs == null) {
/* 393 */       return null;
/*     */     }
/* 395 */     for (int i = 0; i < attrs.getLength(); i++) {
/* 396 */       Attr attribute = (Attr)attrs.item(i);
/* 397 */       if (attribute.getName().equals(attrName)) {
/* 398 */         attrValue = attribute.getValue();
/*     */         break;
/*     */       } 
/*     */     } 
/* 402 */     return attrValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getValueOfNode(Node node, String nodeName) {
/* 413 */     String value = null;
/* 414 */     if (node == null) {
/* 415 */       throw new IllegalArgumentException();
/*     */     }
/* 417 */     if (node.getNodeName().equals(nodeName)) {
/* 418 */       return node.getTextContent();
/*     */     }
/* 420 */     NodeList childNodes = node.getChildNodes();
/* 421 */     for (int i = 0; i < childNodes.getLength(); i++) {
/* 422 */       if (childNodes.item(i).getNodeName().equals(nodeName))
/*     */       {
/* 424 */         return childNodes.item(i).getTextContent();
/*     */       }
/*     */       
/* 427 */       value = getValueOfNode(childNodes.item(i), nodeName);
/* 428 */       if (value != null) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 435 */     return value;
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
/*     */   public static List<String> getValuesOfNode(Node node, String nodeName, List<String> values) {
/* 448 */     if (node == null) {
/* 449 */       throw new IllegalArgumentException();
/*     */     }
/* 451 */     if (node.getNodeName().equals(nodeName)) {
/* 452 */       values.add(node.getTextContent());
/*     */     }
/* 454 */     NodeList childNodes = node.getChildNodes();
/* 455 */     for (int i = 0; i < childNodes.getLength(); i++) {
/* 456 */       if (childNodes.item(i).getNodeName().equals(nodeName)) {
/*     */         
/* 458 */         values.add(childNodes.item(i).getTextContent());
/*     */       } else {
/*     */         
/* 461 */         getValuesOfNode(childNodes.item(i), nodeName, values);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 466 */     return values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Node getNode(Node pNode, String nodeName) {
/* 477 */     Node node = null;
/* 478 */     if (pNode == null) {
/* 479 */       throw new IllegalArgumentException();
/*     */     }
/* 481 */     if (pNode.getNodeName().equals(nodeName)) {
/* 482 */       return null;
/*     */     }
/* 484 */     NodeList childNodes = pNode.getChildNodes();
/* 485 */     for (int i = 0; i < childNodes.getLength(); i++) {
/* 486 */       if (childNodes.item(i).getNodeName().equals(nodeName))
/*     */       {
/* 488 */         return childNodes.item(i);
/*     */       }
/* 490 */       node = getNode(childNodes.item(i), nodeName);
/* 491 */       if (node != null) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 498 */     return node;
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
/*     */   public static String setAttributeValueOfNode(String xpath, String attributeName, String setValue, String filePath) throws Exception {
/* 514 */     DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
/* 515 */     domFactory.setNamespaceAware(false);
/*     */     
/* 517 */     DocumentBuilder builder = domFactory.newDocumentBuilder();
/* 518 */     Document doc = builder.parse(new FileInputStream(new File(filePath)));
/*     */     
/* 520 */     XPathFactory factory = XPathFactory.newInstance();
/* 521 */     XPath path = factory.newXPath();
/*     */     
/* 523 */     XPathExpression expr = path.compile(xpath);
/*     */     
/* 525 */     NodeList nodeList = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
/* 526 */     if (nodeList.getLength() < 1) {
/* 527 */       throw new Exception("xpath :" + xpath + " node not existed!");
/*     */     }
/* 529 */     Node node = nodeList.item(0);
/* 530 */     Attr attributeNode = (Attr)node.getAttributes().getNamedItem(attributeName);
/* 531 */     attributeNode.setValue(setValue);
/* 532 */     TransformerFactory transformerFactory = TransformerFactory.newInstance();
/* 533 */     Transformer transformer = transformerFactory.newTransformer();
/* 534 */     DOMSource source = new DOMSource(doc);
/* 535 */     StreamResult result = new StreamResult(new FileOutputStream(new File(filePath)));
/* 536 */     transformer.transform(source, result);
/* 537 */     return setValue;
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
/*     */   public static List<Node> getNodeListByAttributeValue(Node pNode, String attributeName, String regexValue) {
/* 549 */     List<Node> nodeList = new ArrayList<>();
/* 550 */     NodeList childNodes = pNode.getChildNodes();
/* 551 */     for (int i = 0; i < childNodes.getLength(); i++) {
/* 552 */       Node node = childNodes.item(i);
/* 553 */       String attributeValue = getAttributeValue(node, attributeName);
/* 554 */       if (!CommonUtil.isEmptyOrNull(attributeValue))
/*     */       {
/*     */         
/* 557 */         if (PatternUtils.match(attributeValue, regexValue))
/* 558 */           nodeList.add(node); 
/*     */       }
/*     */     } 
/* 561 */     return nodeList;
/*     */   }
/*     */ }


/* Location:              /Volumes/v1/data/apps/commService/lib/utils-0.0.1-SNAPSHOT.jar!/com/yufei/utils/XPathUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */