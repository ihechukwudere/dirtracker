package com.dirtracker.viewresolver.xml;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.dirtracker.domain.FileResource;
import com.dirtracker.viewresolver.FileContentReader;

public class XMLContentReader extends FileContentReader {

	@Override
	public List<FileResource<? extends Object>> readFile(Path filePath) {
		setFileResourceContainer(new ArrayList<FileResource<? extends Object>>());
		return getFileResourceContainer();
	}
}
