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
	Implemented the design Command desdign pattern for structuring 
	a behaviour that periodically checks the configured directory.
	The Task interface declares a command that lets a client code set the 
	request and trigger the operation using the declared method. This 
	operation lets subclasses run the appropriate function in the request
	object. PeriodicallyReadNewConfigFileInDirectory is command that passes
	the receiver (Directory class and its implementations) of the request.

##### Reads XML files only
	I encapsulated the algorithms for reading and displaying directory
	contents and their properties using the strategy design pattern. The 
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
	1GB, I have used a simple approach in reading the XML file (StAX streaming 
	APIs from Java XML). StAX is much faster and can use less memory than a 
	tree-based processor like DOM.
	
##### Reads configuration file (XML) only once after it is created.
	The getElapsedTimeInMillisSinceFileCreation(), method in the Directory 
	class calculate the period of time since each file creation. Newly created files
	are determined by comparing the time since the file creation to
	the task time-interval.

##### Handled exceptions
	All the possible exceptions that might be generated in the application
	is handled by the Java built-in exceptions handlers except for the 
	exception that might be thrown when the ContentReader object is null. 
	This exception is handled with a custom exception (ContentReaderException).
	
##### Integration and testing
	To be able perform functional testing, few simple objects (Customer, Customers,
	Employees, and Employee objects) are marshalled to XML files and added to
	the confgured directory. The main application program reads these xml files
	periodically as they are added and displays their names and root entity tags 
	in the console. To assert these properties an instance of a TimerTask is started
	in the DirTrackerApplicationTests class. The TimerTask instance lets an
	instance of the RepeatableDirectoryCheckTester class to marshal object instances
	to an XML file and periodically adds them in the configured directory.
	The main application code is started to also periodically read these newly added
	xml files. An instance of RepeatableDirectoryCheckTester class is used to get the
	extracted data from the main application and then checks the equality of theses 
	Objects to an explicitly created objects.

