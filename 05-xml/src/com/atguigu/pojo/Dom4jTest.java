package com.atguigu.pojo;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

/**
 * @author 浪断天涯丶
 * @version 1.0
 * @ClassName: Dom4jTest
 * @description: TODO
 * @date 2021/10/22 17:17
 **/
public class Dom4jTest {
    @Test
    public void test1() throws DocumentException {
        //创建一个SAXReader的输入流，来读取xml文件，生成Document对象
        SAXReader saxreader = new SAXReader();
//        String url = "src/books.xml";
        Document document = saxreader.read("src/books.xml");

        System.out.println(document);
    }

    /**
     * 读取books.xml生成Book类
     */
    @Test
    public void test2() throws DocumentException {
        //1,读取books.xml
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/books.xml");

        //2,通过Document对象获取根元素
        Element rootElement = document.getRootElement();
//        System.out.println(rootElement);
        //3,通过根元素获取book标签对象
        //element()和elements()都是通过标签名查找子元素
        List<Element> books = rootElement.elements("book");

        //4,遍历，处理每个book标签转换成Book类
        for(Element book:books){
            //asXML():把标签对象转换成标签字符串
//            System.out.println(book.asXML());
            Element nameElement = book.element("name");
            //getText():可以获取标签中的所有内容
            String nameText = nameElement.getText();

            //elementText():通过标签名直接获取子元素的文本内容
            String priceText = book.elementText("price");

            String authorText = book.elementText("author");

            String snValue = book.attributeValue("sn");


            System.out.println(new Book(snValue,nameText,new BigDecimal(priceText),authorText));

//            Element priceElement = book.element("price");
//            String priceText = priceElement.getText();
//            Element authorElement = book.element("author");
//            String authorText = authorElement.getText();
        }


    }
}
