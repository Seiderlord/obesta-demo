# Welcome to Obesta Demo!

This is a tutorial to create a software solution for a online business.
The tutorial consists mainly on thoughts and suggestions, it does not involve any details of the Obesta business process.

This project is inspiered from the Obesta software accessable under <obesta.at>. 


## Problem

Imagine you have a good idea for a e-commerce business.
And your idea needs a software that offers various services for their users.
The software should be:
- easily-accessible on all devices
- highly-automated for sending requests and responses
- have business-guided-process for guiding users
- have adjustible-sortiment for different regions
- and so on ...

But it should also be on budget to limit the costs and time during development.

This can be already a challange to fulfil all needs. Additionally, you need to take the software characteristics like reliability, maintainibility, ... into account to achieve a high software quality.


## Solution

The problem can be broken down into smaller challenges that need to be overcome.
For a website we need a frontend server that can be accessed on all devices and provides business information and guides for users.
To separate the business logic from the frontend we additionally need a backend server to automate processes and workflows. 
To store the data of various product we can use a database and to host host all our servers we need to select a cloud service.

For my approach I also an authentification server to speed up the development by using a keycloak server.


### Technology Stack

I recommend reviewing various technologies before choosing one.
Check whether the technology can meet future requirements.
Avoid using abandoned technologies such as Jakarta Server Faces (JSF).

My choices were:
- Frontend - Angular, Material
- Backend - Java, Springboot, Maven
- DB - PostgreSql
- Cloud Service - AWS
