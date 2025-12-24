package com.freq.arvand.bases.config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ArvandBatisConfigParser {

    /**
     * arvand-batis.xml رو می‌خونه و
     * پکیج‌های mapper رو استخراج می‌کنه
     */
    public List<MapperInfo> parse(InputStream inputStream) {
        try {
            Document doc = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(inputStream);

            Element root = doc.getDocumentElement();
            NodeList mapNodes = root.getElementsByTagName("map");

            List<MapperInfo> mapperInfos = new ArrayList<>();

            for (int i = 0; i < mapNodes.getLength(); i++) {
                Element mapElement = (Element) mapNodes.item(i);

                // خواندن تگ class
                NodeList classNodes = mapElement.getElementsByTagName("class");
                if (classNodes.getLength() > 0) {
                    Element classElement = (Element) classNodes.item(0);
                    String className = classElement.getAttribute("name");
                    String classAddr = classElement.getAttribute("addr");

                    // خواندن تگ xmlFile
                    NodeList xmlFileNodes = mapElement.getElementsByTagName("xmlFile");
                    if (xmlFileNodes.getLength() > 0) {
                        Element xmlFileElement = (Element) xmlFileNodes.item(0);
                        String xmlFileName = xmlFileElement.getAttribute("name");
                        String xmlFileAddr = xmlFileElement.getAttribute("addr");

                        MapperInfo info = new MapperInfo();
                        info.setClassName(className);
                        info.setClassPackage(classAddr);
                        info.setXmlFileName(xmlFileName);
                        info.setXmlFilePackage(xmlFileAddr);

                        mapperInfos.add(info);
                    }
                }
            }
            System.out.println(mapperInfos);
            return mapperInfos;

        } catch (Exception e) {
            throw new RuntimeException("خطا در پردازش فایل XML: " + e.getMessage(), e);
        }
    }
}
