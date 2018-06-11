# FalconData

## Restful API +  WebSocket messaging using Spring Boot and Redis Pub/Sub

That application is to show the concepts of (REST, Messaging, Websockets).
scalable Spring Boot Chat using Redis [Pub/Sub] with [WebSocket using STOMP] protocol to sync messages between different instances and also a REST endpoint to take a dummy JSON input and payload on Redis.

### Deploy to Play-with-Docker

Ctrl + Click this button to deploy multiple instances of the FalconData load balanced by NGINX:

[![Deploy to Heroku](deploy-to-pwd.png)](https://labs.play-with-docker.com/?stack=https://raw.githubusercontent.com/juanmarques/falcon-data/master/src/main/docker/docker-compose.yml#)

### Installation and Configuration

##### Pre-requisite:
Install and run [Redis] locally or on Docker.

To run Redis in Docker:
```sh
$ docker run -d bitnami/redis -p 6379:6379 -e
```
 
##### Clone repo:
```sh
$ git clone https://github.com/juanmarques/falcon-data
```

#### Build and Run the applications:

Build and run the **FalconData** application:
```sh
$ cd FalconData

$ mvn clean package

$ mvn spring-boot:run
```

### Run in Docker
#### Build and run the *FalconData* locally in Docker:

Build the JAR file:
```sh
$ mvn clean package
```

Build docker image:
```sh
$ mvn docker:build
```

Run docker image:
```sh
$ docker run -d -p 8080:8080 \
$ 1juanmarques/falcon-data
```

#### Or try [Play with Docker] to quickly setup Docker and run in browser:
1. Click Create Instance to quickly setup Docker host.

2. Install git by running: 
```sh
$ apk add git --no-cache
```
3. Clone the repository:
```sh
$ git clone https://github.com/juanmarques/falcon-data
```
4. Run multiple instances of *FalconData*:     
```sh
$ cd falcon-data/src/main/docker
$ docker-compose up
```


#### Technologies

* [Spring Boot] - An opinionated framework for building production-ready Spring applications. It favors convention over configuration and is designed to get you up and running as quickly as possible.
* [Spring Data Redis] - Spring Data Redis provides easy configuration and access to Redis from Spring applications.
* [Redis] - Redis is an open source (BSD licensed), in-memory data structure store, used as a database and message broker.
* [Bootstrap] - Bootstrap is an open source toolkit for developing with HTML, CSS, and JS. Custom Bootstrap theme - [Bootswatch Sketch]. 
* [Docker] - Docker is an open platform for developers and sysadmins to build, ship, and run distributed applications.
* [NGINX] - NGINX is High Performance Load Balancer, Web Server, & Reverse Proxy.
* [Restful]- REST is an acronym for Representational State Transfer, and its primary purpose is to define key features for building Web applications following best practices.


### What is missing?
[Spring Security] - To implement REST and Method Level Security.
[Testing] - Should have more test to the Message queue and restful endpoints.

[//]: #

   [Spring Boot]:<https://projects.spring.io/spring-boot/>
   [Redis]: <https://redis.io>
   [Spring Data Redis]: <https://projects.spring.io/spring-data-redis/>
   [Bootstrap]: <https://getbootstrap.com>
   [Bootswatch Sketch]: <https://bootswatch.com/sketchy/>
   [Docker]: <https://www.docker.com>
   [NGINX]: <https://www.nginx.com>
   [Pub/Sub]: <https://redis.io/topics/pubsub>
   [WebSocket using STOMP]: <https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#websocket-stomp>
   [Play with Docker]: <https://labs.play-with-docker.com   
   [Restful]: <https://spring.io/guides/gs/rest-service/>
   [Spring Security]: <https://spring.io/guides/gs/securing-web/