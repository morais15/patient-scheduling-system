# [EN-US] Patient Scheduling System
Created using Java with Spring Boot and Angular with Material Design.

## Running the project
### Back end configuration
Assuming you have already installed and configured:
- JDK version 17 (Used Oracle version in development).
- MySQL version 8.

1. Go to backend folder.
2. Configure the database url and credentials in .env file according .env_example file.
3. Run '.\gradlew.bat bootRun' in Windows PowerShell or '.\gradlew bootRun' in Linux Shell.
4. Enjoy.

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
5. Enjoy.

# [PT-BR] Sistema de Agendamento de Pacientes
Criado utilizando Java com Spring Boot e Angular com Material Design.

## Executando o projeto
### Configuração do back end
Assumindo que você já possui instalado e configurado:
- JDK versão 17 (Usado a versão Oracle durante o desenvolvimento).
- MySQL versão 8.

1. Vá para a pasta backend.
2. Configure a url do banco de dados e suas credenciais no arquivo .env de acordo com o arquivo .env_example.
3. Execute '.\gradlew.bat bootRun' no PowerShell do Windows ou '.\gradlew bootRun' no Shell do Linux.
4. Aproveite.

A documentação Swagger pode ser acessada em 'http://localhost:8080/api/docs/swagger'.

#### (Opcional) Banco de dados de exemplo
Essa ação irá criar o banco de dados 'pss' com os dados de exemplo.
- Importe o arquivo 'database_example_schema.sql' no banco de dados MySQL.


### Configuração do frontend
Assumindo que você já possui instalado e configurado:
- Node versão 18 e npm versão 9.
- Angular CLI versão 16.

1. Vá para a pasta frontend.
2. Configure a url da api no arquivo .env de acordo com o arquivo .env_example.
3. Execute 'npm install && ng serve' no PowerShell do Windows ou no Shell do Linux.
4. Vá para 'http://localhost:4200'.
5. Aproveite.
