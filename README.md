**myRetail RESTful Service**

**Getting Started**

myRetail restful service implements the GET and PUT methods. 

GET method – retrieves the product details like name and price information for the given product ID.

PUT method – updates the price information of the product.

myRetail service is developed using Spring Boot framework which includes maven for building and Junit for test cases. myRetail service gets the name of the product by using external API. Cassandra is used to store the price information of the product

**Prerequisites**

•	[1] Spring Tool suite for ease of use.

•	[2] Maven

•	[3] Cassandra

•	[4] PostMan is used as a client to access Restful webservices

**Steps to setup Cassandra**

1.	To install Cassandra follow the steps given in the below link
https://www.datastax.com/2012/01/working-with-apache-cassandra-on-mac-os-x 
2.	Start the Cassandra server with the command “./cassandra” by navigating to the bin directory in the Cassandra folder.
3.	Run the following command

cqlsh -f cassandra_database.cql

to create the key space, table and insert data into the table. The cassandra_database.cql file is present in the cassandra folder.


**Steps to start webservices server:**

•	Cassandra should be running on port number that was mentioned in the properties file before starting the webservices server.

•	Clone the project and then import the project as maven project into the spring tool suite.

•	Right click on the project and run it as a spring boot application which runs the application on port 8080.


**Steps to test the webservices using PostMan client :**

**•	Test GET Webservice:**

Select the GET option in the PostMan client and in the url section copy the below url
http://localhost:8080/products/13860428

The response should be as follows
             {
    		"productId": 13860428,
    		"productName": "The Big Lebowski (Blu-ray)",
    		"priceDetails": {
        			"price": 6.15,
        			"currency": "USD"
    		}
}

**•	Test PUT Webservice:**

Select the PUT option in the PostMan client and in the url section copy the below url
http://localhost:8080/products/13860428

In the body section select the data type as JSON and then copy the below JSON data

{
    "productId": 13860428,
    "productName": "The Big Lebowski (Blu-ray)",
    "priceDetails": {
        "price": 55.55,
        "currency": "USD"
    }
}
The response should be as follows
Given product with id 13860428 is successfully updated

**To Run the Test Cases:**

Execute the following command in the root folder where pom.xml is present.
mvn clean install
		or
Right click the project, first run it a “maven clean” and then right click the project and run it as “maven install”.

