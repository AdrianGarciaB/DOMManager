package com.company;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DOMManager {
    private Document doc;
    private File file;
    public enum MODE{NEW, ADD};
    private Element actualElement;
    private MODE mode;
    NodeList rootElement;
    private int lenght;

    public DOMManager(MODE mode, File file, String rootElement) throws ParserConfigurationException, IOException, SAXException {
        this.file = file;
        this.mode = mode;
        if (mode == MODE.NEW) {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            actualElement = doc.createElement(rootElement);
            doc.appendChild(actualElement);
        }
        else if (mode == MODE.ADD) {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            doc.getDocumentElement().normalize();
            this.rootElement = doc.getElementsByTagName(rootElement);
            actualElement = doc.getDocumentElement();
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
        if (writeInto) actualElement = child;
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


    public int getLength(){
        return lenght;
    }

    public void add(){

    }

    public void close() throws TransformerException {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

    }

    public void remove(){

    };


    class ModeException extends Exception {
        public ModeException(String message){
            super(message);
        }
    }
}
