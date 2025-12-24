package com.freq.arvand.bases.parsing;


import com.freq.arvand.bases.config.XmlConfiguration;
import com.freq.arvand.bases.mapping.MappedStatement;
import com.freq.arvand.bases.mapping.SqlCommandType;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class XmlMapperParser {

    /**
     * XML Mapper رو می‌خونه و
     * MappedStatement ها رو وارد Configuration می‌کنه
     */
    public void parse(InputStream inputStream, XmlConfiguration configuration) {
        try {
            Document doc = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(inputStream);

            Element mapper = doc.getDocumentElement();
            String namespace = mapper.getAttribute("namespace");

            NodeList children = mapper.getChildNodes();

            for (int i = 0; i < children.getLength(); i++) {

                if (children.item(i).getNodeType() != Node.ELEMENT_NODE)
                    continue;

                Element e = (Element) children.item(i);

                // select / insert / update ...
                SqlCommandType type =
                        SqlCommandType.valueOf(e.getTagName().toUpperCase());

                String id = namespace + "." + e.getAttribute("id");
                String sql = e.getTextContent().trim();

                configuration.add(
                        new MappedStatement(id, sql, type, Object.class)
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("❌ Error parsing Mapper XML", e);
        }
    }
}
