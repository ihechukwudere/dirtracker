<<<<<<< Upstream, based on origin/master
# dirtracker
Actively keeps track of only newly created files in the configured directory on a periodic basis. Displays the file name and root entity tag name in the console.

### Program features
  - [ ] Periodically checks the configured directory
  - [ ] Reads xml files only
  - [ ] Should be able to read xml file that is up to 1GB
  - [ ] Reads configuration file (xml) only once after it is created.
  - [ ] Extract root entity tag name and print it to console along with file name.
 

=======
# dirtracker
Actively keeps track of only newly created files in the configured directory on a periodic basis and displays file name, and root entity tag name in the console.

### Program features
  - [ ] Periodically checks the configured directory
  - [ ] Reads xml files only
  - [ ] Should be able to read xml file that is up to 1GB
  - [ ] Reads configuration file (xml) only once after it is created.
  - [ ] Extract root entity tag name and print it to console along with file name.
  
  
#### Documentation
##### Periodically checks the configured directory
	I implemented the design Command pattern for structuring a behaviour 
	that periodically checks the configured directory. The Task 
	interface declares a command that lets the ScheduleTaskService
	class set the request and call the method (scheduleTask), this 
	operation lets the Timer run methods that periodically checks directory
	in the DirectoryRepeatableCheck class.  
 

>>>>>>> 7cfdb96 Update documentation
