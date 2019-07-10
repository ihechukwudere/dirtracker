package com.dirtracker.viewresolver.xml;

import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertTrue;

import com.dirtracker.domain.FileResource;
import com.dirtracker.domain.XMLFileSimplePropertyBean;
import com.dirtracker.exception_handlers.InvalidFileExtensionException;
import com.dirtracker.viewresolver.ContentReaderType;

public class StAXPullParserTest {

	static ContentReaderType xmlStreamer;
	static List<FileResource> fileResourceContainer;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		xmlStreamer = new StAXPullParser(new XMLContentReader());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	/**
	 * Should test that readFile(Path filePath), method accepts a file
	 * path for an XML file, reads XML files only, retrieves the file name
	 * and root element tag, creates an instance of the XMLFileSimplePropertyBean
	 * class, setting its fields with these retrieved properties, and add the bean
	 * to the FileResource container.
	 */
	@Test
	public void shouldSetBeanFieldsWithXMlFilePropertiesAndAddBeanToContainer() throws Exception {
		String fileName = "book-config.xml";
		XMLFileSimplePropertyBean xmlFileBean = new XMLFileSimplePropertyBean();
		xmlFileBean.setFileName(fileName);
		xmlFileBean.setRootEntityTagName("books");
		fileResourceContainer = createFileResourceContainerWithPath(fileName);
		fileResourceContainer.forEach((beanItem) -> {
			XMLFileSimplePropertyBean bean = (XMLFileSimplePropertyBean) beanItem;
			assertTrue(bean.equals(xmlFileBean));
		});
		assertThat(fileResourceContainer.size(), greaterThan(0));
	}
	
	private List<FileResource> createFileResourceContainerWithPath(String fileName) throws InvalidFileExtensionException {
		 return xmlStreamer.readFile(Paths.get("src/test/java/tmp_dir/" + fileName));
	}

}
