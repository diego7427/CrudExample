This example is to simulte an app that creates customers. 
The customers can have a list of notes wich is stored as a list in the customer and can have a status.

The values of the status are stored in te database in a table called status,the notes are stored in a table called notes with a foreign key to the customer. The status is save in every customer with a filed called status_id in the customers table.

The application is a web app  made with Angluar+freemaker in the wiew part, and  springboot, JPA and Hibernate. 
In the client part are two important files,  CustomerController.js and CustomerService.js they act as the controller to manage the business logic and the service to connect with the server.

In the view at the beginning the application lists all of the customers, when you want to add new customer all the fileds must be filled, only one note appears to be introduced. When the customer is edited then appears below a place to create or edit or delete the notes of the customer. 

In the server part the Controller is the RestApiController, that maps the requests. 
Method GET to request all the users, or get one By id
Method POST to create a new customer
Method PUT to update a customer
Method DELETE to delete a customer
...

These requests are submitted to a service, called CustomerServer. The comunication with the service is made with DTO, java beans to separate the objects in the service and the objects that are persisted. Some methods are added just to add more operacions. Althouguh the exercise says nothig about, It is not allowed to insert two customers with the same name.If a ciustomer is tried to be inserted with empty notes, that notes will not be added. If a note is tenad of being deleted in the view is cleared, then is deleted. 

The servce basically calls the persistency to store, delete, get or update the customers.

The persistency is made using JPA and Hibernate. To configure the database uses a yml file called application.yml inside the rsources folder.

There are two different configurations for the local profile, using H2 database, and for a production enviroment one with info of MySQL.

To execute the application it can be installed inside Eclipse, choose SpringBootaplication in the runAs choose CustomerCRUDApp.class and put local in the profile box. 
