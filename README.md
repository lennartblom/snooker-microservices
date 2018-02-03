# Snooker Microservices
Project attempt for creating a microservices architecture implementing the rules of Snooker with SpringBoot based on Domain Driven Design.

This project is meant to show the basic communication principles (REST, Messaging) using a SpringBoot application in combination with frameworks like [lombok](https://projectlombok.org/) or [Netflix's feign](https://github.com/OpenFeign/feign).

Todos
- [ ] Player Switched after Break Finished
- [ ] Dynamic Player ID in Potted Ball Event
- [ ] Dynamic Player ID in Foul Occurred Event
- [ ] Hystrix for Shot Service?
- [ ] Include Eureka/Zuul Service discovery
- [ ] Persistent data in services
- [ ] Services must be stateless

## The infrastructure

## The context map



## Project setup
### Project requirments
Additionally to a suitable IDE (IntelliJ IDEA) you'll need to install the Java SDK 8 + Gradle.

### RabbitMQ
For providing a messaging infrastructure, you have to start the following docker image

- rabbitmq:management


The following ports must be published for viewing the admin webinterface and using RabbitMQ within the SpringBoot appliction:

- tcp 15672
- tcp 5672

### Postman Environment
To have an overview of the different service ports and addresses, the following JSON snippets represents a suitable Postman environment


```

{
  "id": "03f0eaef-3761-5890-55ff-179ba9e0bedd",
  "name": "Snooker Microservices",
  "values": [
    {
      "enabled": true,
      "key": "Game Service",
      "value": "http://localhost:8080",
      "type": "text"
    },
    {
      "enabled": true,
      "key": "Potting Service",
      "value": "http://localhost:8082",
      "type": "text"
    },
    {
      "enabled": true,
      "key": "Scoring Service",
      "value": "http://localhost:8084",
      "type": "text"
    }
  ],
  "timestamp": 1517306301096,
  "_postman_variable_scope": "environment",
  "_postman_exported_at": "2018-01-31T10:40:25.809Z",
  "_postman_exported_using": "Postman/5.5.2"
}

```
