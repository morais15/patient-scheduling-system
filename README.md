# Patient Scheduling System
Created using Java with Spring Boot and Angular with Material Design.

## Running the project
### Back end configuration
Assuming you have already installed and configured:
- JDK version 17 (Used Oracle version in development)
- MySQL version 8

1. Go to backend folder.
2. Configure the database url and credentials in .env file according .env_example file.
3. Run '.\gradlew.bat bootRun' in Windows PowerShell or '.\gradlew bootRun' in Linux Shell.
4. Enjoy

Swagger documentation can be acessed at 'http://localhost:8080/api/docs/swagger'.

#### (Optional) Database example schema
This action will create database 'pss' with data examples.
- Import file 'database_example_schema.sql' in MySQL database.


### Front end configuration
Assuming you have already installed and configured:
- Node version 18 and npm version 9.
- Angular CLI version 16.

1. Go to frontend folder.
2. Configure the backend api url in .env file according .env_example file.
3. Run 'npm install && ng serve' in Windows PowerShell or Linux Shell.
4. Go to 'http://localhost:4200'.
5. Enjoy

