# API BIRDS

Spring Boot - Accessing data with MySQL :: Learn how to set up and manage user details on MySQL and how to configure Spring Boot to connect to it at run time.

## **Requisites:**
- JDK 17 required to compile and Run the Java Application.
- MySql Workbench, required to create and manage the Mysql Database.
- Postman, required to send the Http requets. 

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

- `/api`: This is the root endpoint for the application. This endpoint does not return any data.

- `/bird ` This endpoint takes a JSON object as input, which contains the data for the new bird. The JSON object must contain the following fields:

`commonName`: The common name of the bird.

`scientificName`: The scientific name of the bird.

`description`: A description of the bird.
- As the following example:
```
{
    "commonName":"Pardal",
    "scientificName":"Passer domesticus",
    "description":"Common found bird"
}
```
- `/birds`: This endpoint is used to get a list of all birds, it does not take any input and returns a JSON object that contains a list of all birds.

- `/bird/{id}`: This endpoint is used to get a specific bird by id. This endpoint takes the id of the bird as input and returns a JSON object that contains the data for the bird.

- `/bird/name/{commonName}`: This endpoint is used to get the oldest entry with the specific Common Name and returns a JSON object.

- `/bird/{id}`: This endpoint is used to delete a bird by id. It uses the id of the bird as input and deletes the bird from the database.

- `/bird/{id}/update`: This endpoint takes a JSON object as input, which contains the data for the updated bird. The JSON object must contain the following fields:

`commonName`: The common name of the bird.

`scientificName`: The scientific name of the bird.

`description`: A description of the bird.


### Authors
- Beatriz Fernandes Teixeira
- João Batista Miguel Silva
- Marlon Vinicius de Souza
- Ricardo Keiti Kurita Matsumura
