### Spring Boot Application - Transaction Processor

This Spring Boot application provides a REST API for processing transactions.

### Installation and Setup:

1. Clone the repository from GitHub , https://github.com/Savita-Bhatia/transaction-mgmt.
2. Make sure you have the following tools installed:

    Java JDK 11 

    Gradle
3. Build the application using the following command:
   
    gradle clean build

4. Once the build is complete, start the application using the following command:
java -jar build/libs/<jar-file-name>.jar

    Replace <jar-file-name> with the actual name of the jar file generated in the previous step.

5. The application will start on port 8080 by default. You can access the API at http://localhost:8080.
6. To run the application on docker , run below commands:
   
   docker build -t myapp .

   docker run -p 8080:8080 myapp



### API Endpoints

The following API endpoint is available:

Process Transactions:

This endpoint processes a list of transactions and returns a list of rejected transactions.

POST /processTransactions

Request Body::
The request body should contain a JSON array of transaction objects. Each transaction object should have the following properties:

 `transactionId: string
   transactionCost: number
   firstName: string
   lastName: string
   emailId: string
`

Example request body:
```[

{
"transactionId":"ttt",
"transactionCost":78,
"firstName":"simmi",
"lastName":"bhatia",
"emailId":"savita.bhatia@gmail.com"
},
{
"transactionId":"TR0003",
"transactionCost":550,
"firstName":"simmi",
"lastName":"bhatia",
"emailId":"savita.bhatia@gmail.com"
}
]
```

#### Response

The response body will contain a JSON object with the following properties:

- `rejectedTransactions`: an array of transaction objects that were rejected. 

Example response body:

`{
"Rejected Transactions":
[
{
"First Name": "simmi",
"Last Name": "bhatia",
"Email Id": "savita.bhatia@gmail.com",
"Transaction Number": "TR0003"
}
]
}

`
### Dependencies

This application uses the following dependencies:

- Spring WebFlux: for building reactive web applications.
- Lombok: for reducing boilerplate code in Java classes.

