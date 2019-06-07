Credit Card  Assignment

This assignment is designed on spring-boot framework.

Installation
Clone the source code/download from folder and unzip
git clone https://github.com/srishtia125/CreditCardProject.git
Go to the checked out source code and start the server locally (Server will start on port 8081 , make sure no other is using the same port)

Once the server is started,
http://localhost:8080

APIs
Get All Credit Card Details /creditcard
This Api returns the credit card deetails.
Get all credicards /creditcards – This returns the phone numbers of customer with id : 3


Validations:
Luhn10Algorithm to validate if a credit card is vald or not
Created Unique creditcard number constraint.

Exceptions
CreditCardNotFoundException : if the api input consists of the number is which is not valid creditcard in the system.
InvalidCreditCardNumber : if we try to add invalid creditcard number

Test Cases
Execute mvn test command to run test cases:

test if all credicards  are returned from the system
test if exception is thrown when wrong cardnumber is provided
test if correct card is returned using credicardnumber
