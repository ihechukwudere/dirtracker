package com.dirtracker.viewresolver.xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import com.dirtracker.domain.FileResource;
import com.dirtracker.domain.XMLFileSimplePropertyBean;
import com.dirtracker.exception_handlers.InvalidFileExtensionException;
import com.dirtracker.viewresolver.ContentReaderType;
import com.dirtracker.viewresolver.FileContentReader;

/**
 * An implementation of ContentReaderType class that use XML 
 * streaming APIs to read XML file.
 * @author Ihechukwudere Okoroego
 */
public class StAXPullParser extends ContentReaderType {

	private XMLFileSimplePropertyBean xmlPropertyBean;

	public StAXPullParser(FileContentReader fileContentReader) {
		super(fileContentReader);
		setFileExtention("xml");
	}

	/**
	 * Reads an XML file, retrieves the file name and root element tag, and creates
	 * an instance of the XMLFileSimplePropertyBean class, binds its fields with these properties,
	 * and adds the bean to the FileResource container.
	 * XMLFileSimplePropertyBean is a JavaBean that represents the simple properties retrieved from
	 * the XML file.
	 * @return
	 */
	@Override
	public List<FileResource> readFile(Path filePath) {
		setFileName(filePath.getFileName().toString());
		try {
			if (isValidFileExtension(getFileName())) {
				xmlPropertyBean = 
						bindXMLSimplePropertiesToBeanFields(getFileName(), getRootEntityTagName(filePath));
				setFileResourceContainer(fileContentReader.readFile(filePath));
				addBeanToResourceContainer(xmlPropertyBean);
			}
				
		} catch (InvalidFileExtensionException e) {
			// Clears container/sets to null to prevent displaying empty container.
			setFileResourceContainer(null);
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return getFileResourceContainer();
	}


	private String getRootEntityTagName(Path filePath) throws IOException {
		return getXMLStreamReader(filePath).getLocalName();
	}


	private XMLStreamReader getXMLStreamReader(Path filePath) throws IOException {
		XMLStreamReader reader  = null;
		try(InputStream in = new FileInputStream(filePath.toFile())) {
			XMLInputFactory factory = XMLInputFactory.newInstance();
			reader = factory.createXMLStreamReader(in);
			reader.next();
		} catch (FileNotFoundException | XMLStreamException e) {
			System.out.println(e.getMessage());
		}
		return reader;
	}
	
	private XMLFileSimplePropertyBean bindXMLSimplePropertiesToBeanFields(String fileName, String rootElementName) {
		xmlPropertyBean = new XMLFileSimplePropertyBean();
		xmlPropertyBean.setFileName(fileName);
		xmlPropertyBean.setRootEntityTagName(rootElementName);
		return xmlPropertyBean;
	}
	
	private void addBeanToResourceContainer(FileResource xmlPropertyBean) {
		getFileResourceContainer().add(xmlPropertyBean);
	}
}
