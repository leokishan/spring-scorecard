## Demo spring boot application with kafka queue

**Live URL:** [something](github.com)

A spring boot demo with all kinds of database relationships.
It uses JPA functions and also a custom query mapped to POJO class
for querying database.

Example of content moderation with kafka is also integrated with example.
Publish commentary API is created which takes commentary request objecy
and puts that object to the kafka queue. Another listener is kept in place
which takes objects from kafka queue checks for "bad_word" in commentary dialogue
and replaces it with "*****" before adding it to the database.

To run the example install mysql and kafka to the machine and replace
credentials in ```application.properties```. Then visit
```http://localhost:8080/swagger-ui.html/``` to try out things in swagger.