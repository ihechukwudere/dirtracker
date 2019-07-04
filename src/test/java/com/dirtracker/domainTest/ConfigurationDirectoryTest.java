package com.dirtracker.domainTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Comparator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dirtracker.domain.ConfiguredDirectory;
import com.dirtracker.domain.Directory;

public class ConfigurationDirectoryTest {

	static Directory configDir;
	static Path dirPath = Paths.get("src/main/resources/temp_configured_directory");
	static String[] files = {"test1.xml", "test2.xml"};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		Files.createDirectories(dirPath);
		for (String file : files) {
			new File(dirPath.toString()+"/" +file).createNewFile();
		}
		configDir = new ConfiguredDirectory(dirPath);
	}

	/**
	 * Should delete temporary files and directories after tests
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Files.walk(dirPath).sorted(Comparator.reverseOrder())
		.map(Path::toFile)
		.forEach(File::delete);

	}

	@After
	public void tearDown() throws Exception {
	}


	@Before
	public void setUp() throws Exception {

	}

	/**
	 * 
	 * Should test that files in directory are streamed
	 * and the time since file creation of file is greater
	 * that 35 milliseconds. 35 to 70 milliseconds is the estimated 
	 * time for the files from the time they are created till when
	 * they are deleted.
	 */
	@Test
	public void streamDirectoryFilesTest() throws IOException {
		configDir.streamDirectoryFiles().forEach((path) -> {

			try {
				BigDecimal fileTimeCreation = 
						configDir.getElapsedTimeInMillisSinceFileCreation(path);
				
				assertThat(fileTimeCreation.intValue(), greaterThan(35));
				assertThat(fileTimeCreation.intValue(), lessThan(70));
				
			} catch (NumberFormatException numberFormatException) {
				System.out.println(numberFormatException);
			} catch (IOException ioException) {
				System.out.println(ioException);
			}
		});
	}

}
