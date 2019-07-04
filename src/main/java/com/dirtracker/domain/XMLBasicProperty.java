package com.dirtracker.domain;

/**
 * An object that represents the basic properties extracted from the 
 * an XML file 
 * @author Ihechukwudere Okoroego
 *
 */
public class XMLBasicProperty implements FileResource<XMLBasicProperty> {

	private String fileName;
	private String rootEntityTagName;
	
	public XMLBasicProperty() {}

	public String getName() {
		return fileName;
	}

	public void setName(String name) {
		this.fileName = name;
	}

	public String getRootEntityTagName() {
		return rootEntityTagName;
	}

	public void setRootEntityTagName(String rootEntityTagName) {
		this.rootEntityTagName = rootEntityTagName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((rootEntityTagName == null) ? 0 : rootEntityTagName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		XMLBasicProperty other = (XMLBasicProperty) obj;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (rootEntityTagName == null) {
			if (other.rootEntityTagName != null)
				return false;
		} else if (!rootEntityTagName.equals(other.rootEntityTagName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "XMLBasicProperty [fileName=" + fileName + ", rootEntityTagName=" + rootEntityTagName + "]";
	}

}
