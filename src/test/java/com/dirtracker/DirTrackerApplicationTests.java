package com.dirtracker;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dirtracker.domain.XMLFileSimplePropertyBean;
import com.dirtracker.service.DirectoryService;
import com.dirtracker.service.TaskSchedulerService;
import com.dirtracker.testHelpers.RepeatableDirectoryCheckTester;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DirTrackerApplicationTests {

	RepeatableDirectoryCheckTester tester;

	@Autowired
	private DirectoryService dirService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	/**
	 * Should delete temporary files and directories after tests
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}


	@Test
	public void contextLoads() {}

	/**
	 * Should perform an integration test. 
	 * @throws IOException
	 */
	@Test
	public void scheduleRepeatableCheckForConfiguredDirectoryTest() throws IOException {
		// A list that holds a collection of objects that are marshalled from xml files. 
		List<XMLFileSimplePropertyBean> XmlPropertyContainer = new ArrayList<>();
		try {
			TaskSchedulerService taskService = dirService.scheduleRepeatableCheckForConfiguredDirectory(1000, 0);
			tester = new RepeatableDirectoryCheckTester(dirService.getDirectory());
			taskService.start();
			
			/* Instance of a Timer that lets TimerTask run to marshal objects
			 * to XML file using JAXB. The JAXB implementation is in the 
			 * RepeatableDirectoryCheckTester class.*/
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					tester.writeJAXBObjectToXMLFile(tester.createJABXTestObjects()); // marshall objects
					try {
						if(tester.getFileResourceContainer() != null) {
							XmlPropertyContainer.add((XMLFileSimplePropertyBean) tester.getFileResourceContainer().get(0));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, 500, 2000);

			// Thread waits for 4 milliseconds
			Thread.sleep(5000);
			tester.deleteTestDirectoryAndFiles();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Tests equlity of the marshalled objects to explicitly created objects
		assertTrue(tester.getEmployeeBean().get(0).equals(XmlPropertyContainer.get(0)));
		assertTrue(tester.getCustomerBean().get(0).equals(XmlPropertyContainer.get(1)));
	}


	
}
