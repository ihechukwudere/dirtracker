package com.dirtracker.domain;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class ConfiguredDirectory extends Directory {

	public ConfiguredDirectory(Path dirPath) throws NoSuchFileException  {
		super(dirPath);
	}

}
