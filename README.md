[![sg-hackathon2020](https://circleci.com/gh/sg-hackathon2020/tinyurl-cards-api.svg?style=svg)](https://app.circleci.com/pipelines/github/sg-hackathon2020/tinyurl-cards-api)
# url-cards-api
This API is built for crating and distributing URLs. Usually inside an organization URL's can be huge with hosting addresses attached to it.
The api can `create short urls`, `create specific groups` as per organization structure and also can destribute them.

## Overview
The api is built with spring boot. It needs a database connection to function properly. The DB documentation is also provided. 
The API is also using hazelcast distributed cache so that sort urls can be despensed with at quick turn around.
A swagger documentation is also created when application starts. Also along with that is API-DOCS.
The application is also fully configured with circle-ci CI/CD pipeline. Application get built with all the tests running and 
then deployed directly to heroku apps. There is also inclusion of both `Dockerfile` and `docker-compose` file for deployment purpose.
The `kubernetes` config is also being provided.

## FRAMEWORKS AND TECHNOLOGY
| Framework |
| ------------------------------------------------------------------------------------------ |  
| Spring Boot |
| Spring boot starter data JPA |
| Hazelcast |
| Google guava |
| Firebase for oauth2 |
| Spring security |


## Security
The application is secured by FIREBASE and spring security. The client application fetches valid token from `firebase` and includes it in each request to the API.

## Usage
you can pull the codebase, create the new DB with provided sql files. Then also create a firebase account and add those.
Now run `MVN CLEAN INSTALL` when build is successful you can go ahead and run `java -jar tinyurl-0.0.1-SNAPSHOT.jar` in target folder.