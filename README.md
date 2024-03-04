# Location Service Application

## Table of contents
* [General info](#general-info)
* [Features](#features)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
Location Service is a full-stack web application with a frontend built using Angular 17 and a backend developed with Spring Boot. This application is designed to provide location-based services, and the event path and it's detiails represented in the form of map. Both components are containerized using Docker and served using Docker-compose.

## Features
- User can view the path of spacecraft with events happened at different location coordinates.
- User can view the event details on click of the marker in the app.
	
## Technologies
Project is created with:
- **Backend**: Spring Boot
- **Frontend**: Angular 17
- **Containerization**: Docker, Docker-compose

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.


### Prerequisites

- Java [17.0.10]
- Maven [3.6.3] (for Spring Boot)
- Node.js [18.19.1] (for Angular)
- Angular CLI [17.2.2]
- Docker [20.10.17]
	
## Setup
To run this project using docker, follow the below steps:

```
$ Clone the project and execute the below commands in the root directory of the project.
$ docker-compose build
$ docker-compose up
```
To run, follow the below steps:

### Setting Up the frontend and backend separately

1. **Setup backend (Springboot)**:
```
$ Clone the project and execute the below commands in the root directory of the project.
$ cd backend
$ docker build -t backend .
$ docker run -p 8080:8080 backend
```

2. **Setup frontend (Angular)**:
```
$ Clone the project and execute the below commands in the root directory of the project.
$ cd frontend
$ docker build -t frontend .
$ docker run -p 4300:80 frontend
```
Alternatively, you could also run them locally using mvn and ng-serve



