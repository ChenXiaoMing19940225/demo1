package com.demo.controller.Util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

//dom4j解析
public class test {
    public static void main(String []args){
//      test.add(insert("C:\\Users\\Administrator\\Desktop\\新建文件夹\\books.xml"));
        getchild(insert("C:\\Users\\Administrator\\Desktop\\新建文件夹\\books.xml"));
    }

    //添加节点
    public static Document insert(String path){
        Document document = null;
        SAXReader reader = new SAXReader();
        try {
            document =reader.read(new File(path));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }
    //
     public static void add(Document document){
         Element root = document.getRootElement();

         Element myElement= root.addElement("tabchen");
         myElement.addAttribute("age","25"); //当前节点添加属性
         myElement.addAttribute("name","chenxiaoming");
         myElement.addAttribute("sex","26");
         myElement.addElement("child1").setText("child1"); //添加子节点并设置字符串内容

         OutputFormat format = OutputFormat.createPrettyPrint();
//         OutputFormat format = OutputFormat.createCompactFormat();//紧凑的格式（全部弄到一行）
         OutputStream os = null;
         try {
             os = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\新建文件夹\\books.xml");
             XMLWriter xmlWriter = null;   //有中文使用formant格式
             try {
                 xmlWriter = new XMLWriter(os,format);
                 xmlWriter.write(document);
                 xmlWriter.close();
             } catch (Exception e) {
                 e.printStackTrace();
             }


         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }

     }

    /**
     * 遍历所有的元素的下一级节点
     * @param document
     */
     public static void getchild(Document document){
        List<Object> list = document.getRootElement().elements();
        List<Object> list1 = document.getRootElement().elements("elementName");//获取某个节点的所有下级子节点
        for (Iterator<?> iterator=list.iterator();iterator.hasNext();){
         Element element = (Element) iterator.next();
         String name = element.attributeValue("name");
         if("chenxiaoming".equals(name)){
//             element.remove(element.attribute("name")); //移除某个节点的属性值
         }else if("25".equals(element.attributeValue("age"))){
               document.getRootElement().remove(element);
         }
         System.out.println(element);
        }

         OutputFormat format = OutputFormat.createPrettyPrint();
//         OutputFormat format = OutputFormat.createCompactFormat();//紧凑的格式（全部弄到一行）
         OutputStream os = null;
         try {
             os = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\新建文件夹\\books.xml");
             XMLWriter xmlWriter = null;   //有中文使用formant格式
             try {
                 xmlWriter = new XMLWriter(os,format);
                 xmlWriter.write(document);
                 xmlWriter.close();
             } catch (Exception e) {
                 e.printStackTrace();
             }


         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
     }

     public static void addOrRemove(Document document, JSONObject object){
         JSONArray array=object.getJSONArray("data");
         if(judgeRepeat(array)){
             //直接返回提示不能有重复
         }
         List<Object> list = document.getRootElement().elements();
         Map<String,Object> map =new HashMap<>();

         for (Iterator<?> iterator=list.iterator();iterator.hasNext();){//遍历文档下级子节点
             Element element = (Element) iterator.next();
             map.put(element.attributeValue("listenPort"),element); //把文档中已存在的记录存入到一个map中去重
         }
         for (int i=0;i<array.size();i++) {

             String name=array.getJSONObject(i).getString("name");
             String inputPort=array.getJSONObject(i).getString("listenPort");
             String enable=array.getJSONObject(i).getString("enable");

             if(map.containsKey(inputPort)){
                 map.remove(inputPort); //
                 for (Iterator<?> iterator=list.iterator();iterator.hasNext();){//遍历文档下级子节点
                     Element element = (Element) iterator.next();
                    if(element.attributeValue("listenPort").equals(inputPort)){
                        document.getRootElement().remove(element);
                    }
                 }
             }else{
                 Element newElement = document.getRootElement().addElement("Router");
                 newElement.addAttribute("name",name);
                 newElement.addAttribute("listenPort",inputPort);
                 newElement.addAttribute("enable",enable);
             }
         }
         OutputFormat format = OutputFormat.createPrettyPrint();
         OutputStream os = null;
         try {
             os = new FileOutputStream("F:\\powerRouter\\routerConfig.xml");
             XMLWriter xmlWriter = null;   //有中文使用formant格式
             try {
                 xmlWriter = new XMLWriter(os,format);
                 xmlWriter.write(document);
                 xmlWriter.close();
             } catch (Exception e) {
                 e.printStackTrace();
             }


         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
     }

    /**
     * 判断数组中是否有重复元素
     * @param array
     * @return
     */
     public static boolean judgeRepeat(JSONArray array){
         boolean bool=false;
         Set set=new CopyOnWriteArraySet();
         for (int i=0;i<array.size();i++){
             set.add(array.getJSONObject(i).get("listenPort"));
         }
         if(set.size()==array.size()){
            bool=true;
         }
         return bool;
     }
}
