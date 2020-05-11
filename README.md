# recipes
Mendix recipes app

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info

This is a web site of recipes . Where you user can see all the recipes present in the system. Also user can see the recipes present in the system as per specific category. Main user stories covered as part of requirements as mentioned as below

* Story 1 - 	System can query recipes from the back-end system with the capability to query subsets so I don't get all recipes at once
* Story 2 -	    System can  query all recipe categories from the back-end so user can have easy selection mechanism on the web site
* Story 3 - 	System can  add new recipes to the store
* Story 4 - 	User can visit a web site for recipes listing me available recipes on the landing page
	
## Technologies
Project is being created with below technolories:
* Java 8
* Spring boot 2.1.6.RELEASE
    * Spring boot
    * Spring DI
    * Spring JPA
* H2
* Mendix studio pro 8.9.0
* Docker
* Junit test coverage with maven and jacoco-maven-plugin 0.8.2
* Swagger 2.0
	
## Setup
To run Java project, please follow below steps:

```
$ import as a maven project
$ run mvn clean install (form project home)
$ to have docker build  run mvn package docker:build
$ to get junit test coverage report go to \target\site\jacoco\index.html
$ if you want to run the project from intelij or eclipse go to com.mendix.App.java and run as a java program
$ All the data preparetions are done via post man 
$ after having this java project up and running user should use post man to create prepare backend data and run post man test scripts
$ You need to import recipes\src\test\resources\recipe.postman_collection.json and then run all the the test scripts
```