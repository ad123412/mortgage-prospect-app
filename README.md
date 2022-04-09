# Getting Started

- In the root application folder run the command "**mvn clean package**". This will run the **_unit test cases_**, build the project and _**also read the prospects.txt file**_ from the classpath and _print the monthly payment for all the prospects mentioned in the file_.

The following output will be shown in the console,

===============================================================
Prospect 1:Juha wants to borrow 1000.00 € for a period of 2 years and pay 43.87 € each month
Prospect 2:Karvinen wants to borrow 4356.00 € for a period of 6 years and pay 62.87 € each month
Prospect 3:Claes Månsson wants to borrow 1300.55 € for a period of 2 years and pay 59.22 € each month
Prospect 4:"Clarencé,Andersson" wants to borrow 2000.00 € for a period of 4 years and pay 46.97 € each month
===============================================================


### Prospect Loan Calculator API Documentation

- To run the web application and access the swagger use the command "mvn spring-boot:run". Then the rest application will be up at 8080 port. Access  the api from the following link.
* [Prospect loan calculator API documentation](http://localhost:8080/swagger-ui/index.html)

From the above swagger run the api /mortgage/loan with the prospect as input,

For example,

{
"customer": "Abhishek",
"totalLoan": 1000,
"yearlyInterestRate": 5,
"numberOfYears": 2,
"monthlyPayment": 0
}

Then the api will return the prospect information along with the monthlyPayment value,

{
"customer": "Abhishek",
"totalLoan": 1000,
"yearlyInterestRate": 5,
"numberOfYears": 2,
**"monthlyPayment": 43.87**
}

- [The prospect ui](http://localhost:8080/) to calculate loan for prospect and view the list of prospects.

### Using docker version of the app
- Go to the root of the application. Build and package the project using "**mvn clean package**"
- then create the docker image "**docker build . --tag prospectdemo**"
- Then run the application in port 8080 using "**docker run -it -p8080:8080 prospectdemo:latest**"
- The swagger and ui is available in the same path as mentioned above.