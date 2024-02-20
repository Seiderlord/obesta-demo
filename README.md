# Welcome to Obesta Demo!

This is a tutorial to create a software solution for an online business.
The tutorial consists mainly of thoughts and suggestions,
it does not involve any details of the Obesta business process.

This project is inspired by the Obesta software accessible under <obesta.at>. 


## Problem

Imagine you have a good idea for a e-commerce business.
And your idea needs a software that offers various services for their users.
The software should be:
- easily-accessible on all devices
- highly-automated for sending requests and responses
- have business-guided processes for guiding users
- have adjustable-sortiment for different regions
- and so on ...

But it should also be on budget to limit the costs and time during development.

This can already be a challenge to fulfil all needs. Additionally, you need to take the software characteristics like reliability, maintainibility, ... into account to achieve a high software quality.


## Solution

The problem can be broken down into smaller challenges that need to be overcome.
For a website, we need a frontend server that can be accessed on all devices, provides business information, products and guides for users.
To separate the business logic from the frontend, we additionally need a backend server for automating processes and workflows. 
To store the data of various products, we can use a database and to host all our servers we need to select a cloud service.

For my approach, I also use an authentication server to speed up the development by using a keycloak server (view https://www.keycloak.org/).

![simpleArchitecture.png](simpleArchitecture.png)

Note: The authentication server gets a scheme in the database to store its non-business data. 

### Technology Stack

I recommend reviewing various technologies before choosing one.
Check whether the technology can meet future requirements.
Avoid using abandoned technologies such as Jakarta Server Faces (JSF).

For example, my choices were:
- Frontend - Angular, Material
- Backend - Java, Springboot, Maven
- DB - PostgreSql
- Cloud Service - AWS

### Architecture Overview

The proposed architecture is designed to meet the requirements of functionality, security, and scalability.

### Functionality
To ensure the functionality, the communication between the servers needs to be defined.
Keycloak offers seamless integration with **OpenID Connect** (OIDC), that is the best fit for the task.
Between frontend and backend, it is good fit to use **REST** API's.
For the database, we use JDBC.

### Security

To avoid any exploitation, the traffic needs to be controlled or limited.
Firstly, the **REST** API's can contain sensitive functions that should be restricted to authorized users only.
To achieve that, we can use **JWT Tokens**, provided by keycloak, to authorize requests to the backend server.
Secondly, the architecture can be placed inside a **VPC** (Virtual Private Cloud) to limit the access on the servers.
For that, we define gateways to allow controlled requests to the servers.
Lastly, it is advisable to use HTTPS Encryption.

### Scalability

The load on the servers can vary over time.
The cloud service provides additional options to make servers scalable.
In **AWS** you can use an **AWS Elastic Beanstalk** to achieve the scalability with multiple instances combined with a load balancer
(view https://aws.amazon.com/elasticbeanstalk/).

![scalableDiagram.png](scalableDiagram.png)

Note: LB is short for load balancer.
The green boxes represent the gateway accessible from the internet.


