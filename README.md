# Spring-Twitter-Stream [![Build Status](https://travis-ci.org/RawSanj/Spring-Twitter-Stream.svg?branch=master)](https://travis-ci.org/RawSanj/spring-twitter-stream) 

Spring Boot - Spring Social Twitter - D3.Js webapp for Streaming Live #HashTags and source location of Tweets.

## Demo - Live Demo @ [Heroku](https://twitter-stream-cloud.herokuapp.com)
![Spring Twitter Stream Demo](/demo.gif?raw=true "Spring Twitter Stream Demo")
## Installation

#### Clone the Github repository
```sh
$ git clone https://github.com/RawSanj/Spring-Twitter-Stream.git
```

#### Twitter App and Configuration
1. Login to https://apps.twitter.com
2. Create a New Application and note down the *Consumer Key, Consumer Secret, Access Token and Access Token Secret*. 
3. Edit the `/src/main/resources/application.properties` and add above noted keys.

#### Run the application
```sh
$ mvn spring-boot:run
```
Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

## Deploy to Cloud Foundry

#### Package the application (creates spring-twitter-stream-0.1.0.war file)
```sh
$ mvn clean package
```

#### Pre-requisite:

1. Account @ http://run.pivotal.io. $87 Credit Free Account.
2. cf cli is installed - http://docs.cloudfoundry.org/cf-cli

#### Login to Pivotal Cloud Foundry 
```sh
$ cf login -a https://api.run.pivotal.io
```
#### Deploy the application
```sh
$ cf push spring-twitter-app -p target/spring-twitter-stream-0.1.0.war --random-route
```

## Deploy to Heroku

#### Package the application (creates spring-twitter-stream-0.1.0.war file)
```sh
$ mvn clean package
```

#### Pre-requisite:

1. Account @ https://www.heroku.com. Free Account.
2. heroku cli is installed - https://devcenter.heroku.com/articles/heroku-cli

#### Login to Heroku 
```sh
$ heroku login
```
#### Install Heroku cli deploy plugin
```sh
$ heroku plugins:install heroku-cli-deploy
```
#### Create the application in Heroku
```sh
$ heroku create spring-tweets-app
```
#### Deploy the application
```sh
$ heroku war:deploy target/spring-twitter-stream-0.1.0.war --app spring-tweets-app
```


## Tools and Tech

The following tools, technologies and libraries are used to create this project :

* [Spring Boot] - (Spring Social Twitter, Spring SseEmitter)
* [Thymeleaf] - (Thymeleaf is a template engine capable of processing and generating HTML, XML, JavaScript, CSS and text.)
* [D3Js] - D3.js is a JavaScript library for manipulating documents based on data.
* [Spring Tool Suite]
* [Git]

## License
----

The MIT License (MIT)

Copyright (c) 2017. Sanjay Rawat

[Thymeleaf]: http://www.thymeleaf.org/
[Spring Boot]: http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/
[Spring Tool Suite]: https://spring.io/tools
[Git]: https://git-scm.com/
[D3Js]: https://d3js.org/
