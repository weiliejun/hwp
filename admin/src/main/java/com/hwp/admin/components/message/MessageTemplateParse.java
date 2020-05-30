package com.hwp.admin.components.message;


import com.hwp.common.components.exception.MessageBusiTypeTmplInexistenceException;
import com.hwp.common.components.exception.MessageTypeTmplInexistenceException;
import com.hwp.common.components.exception.ParameterNullPointerException;
import com.hwp.common.components.exception.TemplateInexistenceException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



/*
 * ===========================================================================
 * Copyright 2007 WEBTRANING Corp. All Rights Reserved.
 * WEBTRANING PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * ===========================================================================
 * @version 1.0, 2014-4-13
 * @author  zqs
 * ===========================================================================
 *
 */

public class MessageTemplateParse {

    private static final String tmplXMLPath = "/config/template/message-template.xml";

    public static Map<String, Map<String, Map<String, String>>> tmpls = null;

    static {
        tmpls = tmplParse();
    }

    public static Map<String, String> getTmpl(String busiType, String type) throws ParameterNullPointerException, TemplateInexistenceException, MessageBusiTypeTmplInexistenceException {

        Map<String, String> result = null;

        if (StringUtils.isBlank(busiType)) {
            throw new ParameterNullPointerException("busiType");
        }

        if (StringUtils.isBlank(type)) {
            throw new ParameterNullPointerException("type");
        }

        Map<String, Map<String, Map<String, String>>> tmplMap = tmpls;
        if (MapUtils.isEmpty(tmplMap)) {
            throw new TemplateInexistenceException("未定义任何模板！");
        }

        Map<String, Map<String, String>> busiMsgTmplMap = tmplMap.get(busiType);
        if (MapUtils.isEmpty(busiMsgTmplMap)) {
            throw new MessageBusiTypeTmplInexistenceException(busiType);
        }

        result = busiMsgTmplMap.get(type);
        if (MapUtils.isEmpty(result)) {
            throw new MessageTypeTmplInexistenceException(type);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Map<String, Map<String, String>>> tmplParse() throws TemplateInexistenceException {
        Map<String, Map<String, Map<String, String>>> result = new HashMap<String, Map<String, Map<String, String>>>();

        SAXReader saxReader = new SAXReader();
        Document document = null;
//        try {
//            document = saxReader.read(MessageRepository.class.getResourceAsStream(tmplXMLPath), "UTF-8");
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }

        Element template = document.getRootElement();

        List<Element> elements = template.elements();

        if (CollectionUtils.isEmpty(elements)) {
            throw new TemplateInexistenceException("根标签元素下不存在子标签元素！");
        }

        for (Iterator<Element> iterator = elements.iterator(); iterator.hasNext(); ) {
            Element element = iterator.next();

            String elementName = element.getName();

            Map<String, Map<String, String>> childElementMap = new HashMap<String, Map<String, String>>();
            List<Element> childElements = element.elements();
            for (Iterator<Element> iterator2 = childElements.iterator(); iterator2.hasNext(); ) {
                Element childElement = iterator2.next();

                String childElementName = childElement.getName();

                Map<String, String> sunElementMap = new HashMap<String, String>();
                List<Element> sunElements = childElement.elements();
                for (Iterator<Element> iterator3 = sunElements.iterator(); iterator3.hasNext(); ) {
                    Element sunElement = iterator3.next();

                    String sunElementName = sunElement.getName();
                    String sunElementVal = sunElement.getStringValue();

                    sunElementMap.put(sunElementName, sunElementVal);
                }

                childElementMap.put(childElementName, sunElementMap);
            }

            result.put(elementName, childElementMap);
        }

        return result;
    }
}