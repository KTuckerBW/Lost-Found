Instructions on how to download the csv parser package
(FOR INTELLIJ)
1) go to https://repo1.maven.org/maven2/org/apache/commons/commons-csv/1.14.1/
2) download commons csv-1.14.1.jar

Apparently this lib comes with more dependencies of its own. 
Get Commons Codec 
1) go to https://mvnrepository.com/artifact/commons-codec/commons-codec/1.19.0
2) Scroll until you see "Commons Codec 1.19.0"
3) Look at the files row, between date and repo 
4) Install the jar file into the same folder as the jar you installed before 

Get Commons IO
1) go to https://mvnrepository.com/artifact/commons-io/commons-io/2.20.0
2) Scroll until you see the files row 
3) Install the jar file into the same folder 

Add dependencies 
1) Do Ctrl+Alt+Shift+S (windows) or Cmd+; (Mac)
1.5) Or just go to your Project structure screen if this isn't working
2) On the left sidebar, click Modules and select Dependencies
3) Click on the + icon
4) Select JARS or Directories
5) Choose the filepath for csv-1.14.1.jar
6) Repeat for codec-1.19.0.jar and commons-io-2.20.0.jar
7) Apply