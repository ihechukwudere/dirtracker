package com.dirtracker.testHelpers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.dirtracker.domain.Directory;
import com.dirtracker.domain.FileResource;
import com.dirtracker.domain.XMLFileSimplePropertyBean;
import com.dirtracker.viewresolver.ContentViewResolver;
import com.dirtracker.viewresolver.FileContentReader;

public class RepeatableDirectoryCheckTester {
	
	private Directory directory;
	private int count = 0;
	
	
	public RepeatableDirectoryCheckTester(Directory directory) {
		this.directory = directory;
	}

	public  void writeJAXBObjectToXMLFile(List<JAXBTestObject> jaxbObjects) {
		if (count < 2) {
			Path xmlFilePath = directory.getDirPath();
			JAXBTestObject jaxbObject = jaxbObjects.get(count++);
			try {
				System.out.println("Converting " + jaxbObject.getClass().getSimpleName() + " object xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(jaxbObject.getClass());
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				xmlFilePath = xmlFilePath.resolve(createNewXMLFileNameWithClassName(jaxbObject.getClass()));
				jaxbMarshaller.marshal(jaxbObject, xmlFilePath.toFile());
				System.out.println("A " + createNewXMLFileNameWithClassName(jaxbObject.getClass()) + " file was created.");
			} 
			catch (JAXBException e) {
				e.printStackTrace();
			}
		} else {
			try {
				directory.addNewFile("hello.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static Path createNewXMLFileNameWithClassName(Class<?> clazz) {
		return Paths.get(clazz.getSimpleName().toLowerCase() + ".xml");
	}
	
	public List<JAXBTestObject> createJABXTestObjects() {
		List<JAXBTestObject> jaxbObjects = new ArrayList<>();
		
		/*Employee object*/
		Employee emp1 = new Employee();
		emp1.setEmpId(1);
		emp1.setFirstName("Kelvins");
		emp1.setLastName("George");
		Employees employees = new Employees();
		employees.setEmployees(new ArrayList<>());
		employees.getEmployees().add(emp1);
		
		/* Customer object*/
		Customer cus1 = new Customer();
		cus1.setCusId(1);
		cus1.setFirstName("Malcom");
		cus1.setLastName("Aarons");
		Customers customers = new Customers();
		customers.setCustomers(new ArrayList<>());
		customers.getCustomers().add(cus1);
		
		jaxbObjects.add(employees);
		jaxbObjects.add(customers);
		return jaxbObjects;
	}

	
	public void deleteTestDirectoryAndFiles() throws IOException {
		directory.deleteAll();
	}
	
	public List<FileResource> getFileResourceContainer() throws Exception {
		return getFileContentReader().getFileResourceContainer();
	}
	
	private FileContentReader getFileContentReader() throws Exception {
		return getContentViewResolver().getContentReader();
	}
	
	private ContentViewResolver getContentViewResolver() {
		return directory.getContentView();
	}
	
	public List<FileResource> getEmployeeBean() {
		XMLFileSimplePropertyBean employeeXmlFilePropertyBean = new XMLFileSimplePropertyBean();
		employeeXmlFilePropertyBean.setFileName("employees.xml");
		employeeXmlFilePropertyBean.setRootEntityTagName("employees");
		List<FileResource> container = new ArrayList<FileResource>();
		container.add(employeeXmlFilePropertyBean);
		return container;
	}
	
	public List<FileResource> getCustomerBean() {
		XMLFileSimplePropertyBean customerXmlFilePropertyBean = new XMLFileSimplePropertyBean();
		customerXmlFilePropertyBean.setFileName("customers.xml");
		customerXmlFilePropertyBean.setRootEntityTagName("customers");
		List<FileResource> container = new ArrayList<FileResource>();
		container.add(customerXmlFilePropertyBean);
		return container;
	}
}
