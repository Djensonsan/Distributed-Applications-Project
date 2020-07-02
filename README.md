# Distributed Applications Project

In this demo we will use various Jakarta EE features and consume them through an Angular front-end. 
The application server used is Payara, the server does require some configuration in order for all functionalities to work.
An Azure MySQL database was used to store the entities. 

This project was part of the course "Distributed Applications", taught at Campus GroepT, KU Leuven.

## Application Idea
The idea of this project was to make a custom e-commerce webshop in Jakarta EE with an Angular Frontend. The following requirements had to be implemented:

EJB:
- At least one stateless and one stateful EJB session bean
- At least one Singleton bean
- At least one interceptor
- At least one timer
- A MDB (Message Driven Bean) with a Message Queue (and some code that posts a message in the queue)
- A SOAP web service
- A RESTFul web service

JPA:
- A few entity beans, with at least a one-to-many and a many-to-many relationship
- Corresponding tables
- Some "special" JPA annotations:@Enumerated, @Elementcollection, @Inheritance, @Embeddable/@Embedded
- Web-client
- One Servlet
- One Filter

Framework: 
- A JavaScript client-side framework with RESTful calls
- Web service clients
- One client for your own web services
- One SOAP client
- One client for the RESTFul service (Json or XML)

## Installation

In order to run the application, first start the Glassfish server.
Then, run the following commands to start the Angular CLI.

```bash
npm install
ng serve --proxy-config proxyconfig.json
```

## Screenshot Landing Page
![Screenshots] (https://imgur.com/xF3ygDi.png)


## ER-Diagram

![ER-Diagram](https://i.imgur.com/cVGxoM5.png)

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[KU Leuven](https://admin.kuleuven.be/sab/jd/en/student-thesis-copyright)

This project was made by: Jens Leysen & August Martens.
