package com.dirtracker.viewresolver;

import java.nio.file.Path;

import com.dirtracker.domain.FileResource;

public interface ContentReader {

	public abstract FileResource<? extends Object> readFile(Path filePath);
}
