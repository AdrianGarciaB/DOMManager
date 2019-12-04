package com.examen;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMManager {
    private Document doc;
    private File file;
    public enum MODE{NEW, READ, ADD, REMOVE};
    private Element actualElement;
    private MODE mode;

    public DOMManager(MODE mode, File file, String rootElement) throws ParserConfigurationException {
        this.file = file;
        this.mode = mode;
        if (mode == MODE.NEW) {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            actualElement = doc.createElement(rootElement);
            doc.appendChild(actualElement);
        }

    }

    public void setParent(int nLevelJump){
        for (int i = 0; i < nLevelJump; i++) {
            actualElement = (Element) actualElement.getParentNode();
        }
    }



    public void write(String key, String value){
        Element child = doc.createElement(key);
        child.appendChild(doc.createTextNode(value));
        actualElement.appendChild(child);
    }

    public void write(String key, boolean writeInto){
        Element child = doc.createElement(key);
        actualElement.appendChild(child);
        if (writeInto) actualElement = child;;
    }

    public void writeAttribute(String key, String value){
        Attr createAttribute = doc.createAttribute(key);
        createAttribute.setValue(value);
        actualElement.setAttributeNode(createAttribute);
    }

    public void writeAttribute(String key){
        Attr createAttribute = doc.createAttribute(key);
        actualElement.setAttributeNode(createAttribute);
    }



    public void read(){

    }

    public void add(){}

    public void close() throws TransformerException {
        if (mode == MODE.NEW){
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        }
    }

    public void remove(){

    };
}
