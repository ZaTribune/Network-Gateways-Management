# Gateways Management
  
A Simple Spring Boot Web Application for managing Network Gateways.

<p align="center">
  <img src="src/main/resources/static/images/spring.svg" width="200" height="200"/>
</p>

## Detailed Requirements  
This sample project is managing gateways - master devices that control multiple peripheral devices.  
Your task is to create a REST service (JSON/HTTP) for storing information about these gateways, and their associated devices. This information must be stored in the database.  
When storing a gateway, any field marked as “to be validated” must be validated, and an error returned if it is invalid. Also, no more than 10 peripheral devices are allowed for a gateway.  
The service must also offer an operation for displaying information about all stored gateways (and their devices) and an operation for displaying details for a single gateway.  
Finally, it must be possible to add and remove a device from a gateway.


## Steps to deploy
- Just load this project on your preferred IDE e.g. Intellij or Netbeans ...etc and Maven will load all dependencies
  and configure directories.  
- This project uses an embedded "h2 database" so there's no need for a dbms e.g. MySQL or Postgres ...etc  

## Authors  
   **Muhammad Ali** - find me on : [LinkedIn](https://www.linkedin.com/in/zatribune).    
