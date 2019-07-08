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
import com.dirtracker.domain.XMLBasicProperty;
import com.dirtracker.exception_handlers.InvalidFileExtensionException;
import com.dirtracker.viewresolver.ContentReaderType;
import com.dirtracker.viewresolver.FileContentReader;


public class StAXPullParser extends ContentReaderType {

	private XMLBasicProperty xmlProperty;

	public StAXPullParser(FileContentReader fileContentReader) {
		super(fileContentReader);
		setFileExtention("xml");
	}

	@Override
	public List<FileResource<? extends Object>> readFile(Path filePath) {
		setFileName(filePath.getFileName().toString());
		List<FileResource<? extends Object>> container = fileContentReader.readFile(filePath);
		try {
			if (!hasValidExtension(getFileName())){
				container = null;
				throw new InvalidFileExtensionException("File is not an xml");
			}else {
				xmlProperty = new XMLBasicProperty();
				xmlProperty.setName(getFileName());
				xmlProperty.setRootEntityTagName(getRootEntityTagName(filePath));
				container.add(xmlProperty);
			}
		} catch (InvalidFileExtensionException e) {
			System.out.println(e.getMessage());
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return container;
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
}
