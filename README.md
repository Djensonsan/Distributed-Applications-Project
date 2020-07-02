# Distributed Applications Project

In this demo we will use various Jakarta EE features and consume them through an Angular front-end. 
The application server used is Payara, the server does require some configuration in order for all functionalities to work.
An Azure MySQL database was used to store the entities. 

This project was part of the course "Distributed Applications", taught at Campus GroepT, KU Leuven.

## Application Idea
The idea of this project was to make a custom e-commerce webshop in Jakarta EE with an Angular Frontend

## Installation

In order to run the application, first start the Glassfish server.
Then, run the following commands to start the Angular CLI.

```bash
npm install
ng serve --proxy-config proxyconfig.json
```

## Screenshot Landing Page

![Home](https://i.imgur.com/SVPvD19.png)

## ER-Diagram

![ER-Diagram](https://i.imgur.com/cVGxoM5.png)

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[KU Leuven](https://admin.kuleuven.be/sab/jd/en/student-thesis-copyright)

This project was made by: Jens Leysen & August Martens.
