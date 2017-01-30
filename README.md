# EasyData
###Generate a Java DataModel and Repositories from JSON definition.

The idea is to help serialize different formates into a Java Object Model and make the data available using Repository classes. Repository classes hold the data and provide access to the data. Note: Only read-access is provided.

The Java Oject Model classes are generated based on a JSON definition file. EasyData parses the JSON file to an EasyModel. EasyData uses JCodeModel (https://codemodel.java.net) to generate the Java Oject Model classes and Repository classes. 

####CSV

Currently EasyData works with CSV only. EasyData can read the header of an CSV file to create a JSON definition file which can be enhanced with furher details like keys and references. Once happy, the code generation will create the POJO classes representing the Java Object Model and the repository classes. The CSV implementation will initialize the data reading from CSV files. 

###Trello

Check here for stuff that still needs to be done. https://trello.com/b/wtjZ1H8I/easydata


