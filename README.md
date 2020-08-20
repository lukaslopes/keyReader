# keyReader

- Projeto desenvolvido usando Clean Architecture

- O projeto usa Lombok (https://projectlombok.org/), para a IDE reconhecer é necessario
instalar o plugin para Eclipse ou IntelliJ: https://www.baeldung.com/lombok-ide

- A aplicação usa o banco H2 em memória. Para acessa-lo: http://localhost:8080/h2-console - Username: sa - 
Password: - (senha vazia)

- A pasta contendo os arquivos deve ser apontada na properties "reader.location".
Caso não seja encontrado, será lido da pasta "keys" no resources

- A leitura é realizada no método KeyReader.readFiles(). O cronJob está configurado
para rodar a cada 3 minutos (180 segundos)

- A api rest está disponibilizada via Swagger. Para acessar: http://localhost:8080/swagger-ui.html

- To-do: Testes unitários dos usecases: ValidateCompanyDocument e GetCompanyDocument
- To-do: Integration tests do Controller
- To-do: Mapeamento de exceções para respostas do controller