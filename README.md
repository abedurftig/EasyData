# EasyData
###Generate a Java DataModel and Repositories from JSON definition.

The idea is to help serialize different formates into a Java Object Model and make the data available using Repository classes. Repository classes hold the data and provide access to the data. Note: Only read-access is provided.

The Java Oject Model classes are generated based on a JSON definition file. EasyData parses the JSON file to an EasyModel. EasyData uses JCodeModel (https://codemodel.java.net) to generate the Java Oject Model classes and Repository classes. 

####JSON Definition

Create one JSON file for every Java class that should be generated. But note that EasyData can generate these JSON files from CSV files.

```javascript

{
  "fileName" : "Employee.csv",
  "targetClassName" : "Employee",
  "fields" : [ {
    "index" : 0, // every column needs an unique index as the index defines the mapping between CSV input file and Java Object.
    "key" : true, // indicates that this column should be used as key. This is important when generating a CSV repository.
    "targetFieldName" : "PublicId",
    "type" : "text"
  }, {
    "index" : 2,
    "key" : false,
    "targetFieldName" : "LastName",
    "type" : "text" // text, date, int, boolean, decimal
  }, {
    "index" : 1,
    "key" : false,
    "targetFieldName" : "FirstName",
    "type" : "text"
  }, {
    "index" : 3,
    "key" : false,
    "targetFieldName" : "DateOfBirth",
    "type" : "date"
  }, {
    "index" : 4,
    "key" : false,
    "targetFieldName" : "AddressID",
    "type" : "text",
    "ref" : "Address" // value in this column references a record in another repository.
  }, {
    "index" : 5,
    "key" : false,
    "targetFieldName" : "BossID",
    "type" : "text",
    "ref" : "Boss"
  } ]
}
```

####CSV

Currently EasyData works with CSV only. EasyData can read the header of an CSV file to create a JSON definition file which can be enhanced with furher details like keys and references. Once happy, the code generation will create the POJO classes representing the Java Object Model and the repository classes. The CSV implementation will initialize the data reading from CSV files. 

###Trello

Check here for stuff that still needs to be done. https://trello.com/b/wtjZ1H8I/easydata


