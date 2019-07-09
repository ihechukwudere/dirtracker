# dirtracker
Actively keeps track of only newly created files in the configured directory on a periodic basis. Displays the file name and root entity tag name in the console.

### Program features
  - [ ] Periodically checks the configured directory
  - [ ] Reads XML files only
  - [ ] Should be able to read XML file that is up to 1GB
  - [ ] Reads configuration file (XML) only once after it is created.
  - [ ] Extract root entity tag name and print it to console along with file name.
  
  
#### Documentation
##### Periodically checks the configured directory
	Implemented the design Command pattern for structuring a behaviour 
	that periodically checks the configured directory. The Task 
	interface declares a command that lets the ScheduleTaskService
	class set the request and call the method (scheduleTask), this 
	operation lets the Timer run methods that periodically checks directory
	in the DirectoryRepeatableCheck class.

##### Reads XML files only
	I encapsulated the algorithms for reading and displaying directory
	contents and their properties using the strategy pattern. The 
	ContentViewResolver field in the Directory class could have different
	implementations that let any file format in the directory readable and 
	viewable on any platform. Underneath the strategy pattern, I have 
	implemented the decorator pattern for additional functionality that lets
	FileContentReader class to be easily extended to incorporate new behavior 
	without code modifications. In this application, the targeted file format
	to read is XML and the implementations are StAXPullPaser and XMLContentReader
	classes.
	
##### Should be able to read XML file that is up to 1GB
	Considering the capability of the application to read XML files sized up to
	1GB, I have used a simple approach in reading the XML file (SAX streaming 
	APIs from Java XML). SAX is much faster and can use less memory than a 
	tree-based processor like DOM.
	
##### Reads configuration file (XML) only once after it is created.

##### Handled exceptions
	All the possible exceptions that might be generated in the application
	is handled by the Java built-in exceptions handlers except for the 
	exception that might be thrown when the ContentReader object is null. 
	This exception is handled with a custom exception (ContentReaderException).

