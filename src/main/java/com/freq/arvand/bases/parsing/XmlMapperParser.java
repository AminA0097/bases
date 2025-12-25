package com.freq.arvand.bases.parsing;


import com.freq.arvand.bases.config.MapperInfo;
import com.freq.arvand.bases.config.XmlConfiguration;
import com.freq.arvand.bases.mapping.MappedStatement;
import com.freq.arvand.bases.mapping.SqlCommandType;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlMapperParser {
    private final XmlConfiguration configuration;

    public XmlMapperParser(XmlConfiguration configuration) {
        this.configuration = configuration;
    }
    public void parse(List<MapperInfo> mapperInfos) {

        for (MapperInfo mapperInfo : mapperInfos) {
            try {
                InputStream xmlStream = this.getClass().getClassLoader()
                        .getResourceAsStream(mapperInfo.getXmlFilePackage());
                loadXml(xmlStream);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadXml(InputStream xmlStream) {
        try {
            Document doc = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(xmlStream);

            Element mapper = doc.getDocumentElement();
            String namespace = mapper.getAttribute("namespace");

            NodeList children = mapper.getChildNodes();

            for (int i = 0; i < children.getLength(); i++) {

                if (children.item(i).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                Element e = (Element) children.item(i);

                String id = namespace + "." + e.getAttribute("id");
                String query = e.getTextContent().trim();
                String resultType = e.getAttribute("resultType");
                String type = e.getAttribute("type");
                MappedStatement ms = new MappedStatement(
                        id, query, resultType, type);
                configuration.add(ms);
            }

        } catch (Exception e) {
            throw new RuntimeException("❌ Error parsing Mapper XML", e);
        }
    }
}
