# API BIRDS

Spring Boot - Accessing data with MySQL :: Learn how to set up and manage user details on MySQL and how to configure Spring Boot to connect to it at run time.


## **Setup Application:**

### Create a new database

>- mysql> Create database dbbirds by running SQL script /src/main/resources/templates/query.sql; -- Create the new database

> **Note:** Find the mySql connection details in "/src/main/resources/application.properties"

### Check application.properties file

>- In the sources folder, you should set a username and password in the resource file src/main/resources/application.properties
>- SET your username filling this property
	 ``` field spring.datasource.username= ```

>- SET your password filling this property
	``` field spring.datasource.password= ```
```
  spring.jpa.hibernate.ddl-auto=update
  spring.datasource.url=jdbc:mysql://localhost:3306/dbbirds
  spring.datasource.username=root
  spring.datasource.password=
```

### These are the available End points for this Application

@RequestMapping("/api")

@PostMapping("/bird")

@GetMapping("/birds")

@GetMapping("/bird/{id}")

@GetMapping("/bird/name/{commonName}")

@DeleteMapping("/bird/{id}")

@PutMapping("/bird/{id}/update")


### Authors
Beatriz Fernandes Teixeira
João Batista Miguel Silva
Marlon Vinicius de Souza
Ricardo Keiti Kurita Matsumura
