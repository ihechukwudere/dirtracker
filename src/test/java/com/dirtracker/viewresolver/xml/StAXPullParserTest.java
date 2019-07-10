package com.dirtracker.viewresolver.xml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dirtracker.domain.FileResource;
import com.dirtracker.domain.XMLFileSimplePropertyBean;
import com.dirtracker.exception_handlers.InvalidFileExtensionException;
import com.dirtracker.testHelpers.Employee;
import com.dirtracker.testHelpers.Employees;
import com.dirtracker.viewresolver.ContentReaderType;

public class StAXPullParserTest {

	Path dirPath = Paths.get("src/test/java/tmp_dir/");
	
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
		
		Files.createDirectory(dirPath);
		/*Employee object*/
		Employee emp1 = new Employee();
		emp1.setEmpId(1);
		emp1.setFirstName("Kelvins");
		emp1.setLastName("George");
		Employees employees = new Employees();
		employees.setEmployees(new ArrayList<>());
		employees.getEmployees().add(emp1);

		
		JAXBContext jaxbContext = JAXBContext.newInstance(employees.getClass());
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		jaxbMarshaller.marshal(employees, dirPath.resolve("employees.xml").toFile());
	}

	@After
	public void tearDown() throws Exception {
		Files.walk(dirPath).sorted(Comparator.reverseOrder())
		.map(Path::toFile)
		.forEach(File::delete);
	}

	
	/**
	 * Should test that readFile(Path filePath), method accepts a file
	 * path for an XML file, reads XML files only, retrieves the file name
	 * and root entity element tag, creates an instance of the XMLFileSimplePropertyBean
	 * class, setting its fields with these retrieved properties, and add the bean
	 * to the FileResource list.
	 */
	@Test
	public void shouldSetBeanFieldsWithXMlFilePropertiesAndAddBeanToList() throws Exception {
		XMLFileSimplePropertyBean xmlFileBean = new XMLFileSimplePropertyBean();
		xmlFileBean.setFileName("employees.xml");
		xmlFileBean.setRootEntityTagName("employees");
		
		fileResourceContainer = xmlStreamer.readFile(dirPath.resolve("employees.xml"));
		fileResourceContainer.forEach((beanItem) -> {
			XMLFileSimplePropertyBean bean = (XMLFileSimplePropertyBean) beanItem;
			assertTrue(bean.equals(xmlFileBean));
		});
		assertThat(fileResourceContainer.size(), greaterThan(0));
	}

}
