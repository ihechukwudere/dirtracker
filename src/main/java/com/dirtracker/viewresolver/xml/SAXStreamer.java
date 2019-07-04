package com.dirtracker.viewresolver.xml;

import java.io.IOException;
import java.nio.file.Path;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.dirtracker.domain.FileResource;
import com.dirtracker.domain.XMLBasicProperty;

public class SAXStreamer extends DefaultHandler implements XMLFileReader {

	private int rootPosition = 0;
	XMLBasicProperty xmlProperty;
	
	@Override
	public FileResource<? extends Object> readFile(Path filePath) {
		xmlProperty = new XMLBasicProperty();
		xmlProperty.setName(filePath.getFileName().toString());
		readData(filePath);
		return xmlProperty;
	}

	@Override
	public void startElement(String uri, String localName,
			String qName, Attributes attributes) throws SAXException {
		if (rootPosition <= 0) {
			rootPosition++;
			xmlProperty.setRootEntityTagName(qName);
		}
	}
	
	private void readData(Path filePath) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(filePath.toFile(), this);
		} catch (SAXException | IOException e) {
			System.out.println(e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
