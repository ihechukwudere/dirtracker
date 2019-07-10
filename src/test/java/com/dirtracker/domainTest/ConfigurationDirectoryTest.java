package com.dirtracker.domainTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.dirtracker.domain.ConfiguredDirectory;
import com.dirtracker.domain.Directory;

public class ConfigurationDirectoryTest {

	static Directory configDir;
	static Path dirPath = Paths.get("src/main/resources/tmp_dir/");
	static String[] files = {"test1.xml", "test2.xml"};
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		configDir = new ConfiguredDirectory(dirPath);
		for (String file : files) {
			configDir.addNewFile(file);
		}
	}

	/**
	 * Should delete temporary files and directories after tests
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Deletes directory and all its contents
		configDir.deleteAll();
	}

	@After
	public void tearDown() throws Exception {
	}


	@Before
	public void setUp() throws Exception {

	}

	/**
	 * Should test that files in directory are streamed
	 * and the time since file creation of file is greater
	 * that 35 milliseconds. 35 to 200 milliseconds is the estimated 
	 * time from when the files are created till when they are deleted.
	 */
	@Test
	public void streamDirectoryFilesTest() throws IOException {
		configDir.streamDirectoryFiles().forEach((filePath) -> {
			try {
				BigDecimal fileTimeCreation = 
						configDir.getElapsedTimeInMillisSinceFileCreation(filePath);
				assertThat(fileTimeCreation.intValue(), greaterThan(35));
				assertThat(fileTimeCreation.intValue(), lessThan(100));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		});
	}
}
