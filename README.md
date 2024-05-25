# API de Usuários

Esta é uma API de usuários desenvolvida em Java utilizando Spring Boot. Ela oferece operações CRUD (Criar, Ler, Atualizar, Deletar) para usuários, bem como outras funcionalidades, como busca por nome, quantidade de usuários cadastrados e validação de senha.

## Endpoints

### Usuários

- **GET /usuarios**: Lista todos os usuários.
- **POST /usuarios/cadastro**: Cria um novo usuário.
- **PUT /usuarios/{id}**: Atualiza as informações de um usuário existente pelo ID.
- **DELETE /usuarios/{id}**: Deleta um usuário existente pelo ID.
- **GET /usuarios/nome/{nome}**: Lista usuários por nome.
- **GET /usuarios/id/{id}**: Retorna um usuário pelo ID.
- **GET /usuarios/quantidade-usuarios**: Retorna a quantidade de usuários cadastrados.

### Autenticação

- **POST /usuarios/login**: Valida a senha de um usuário.

## Front-end

Esta API possui um front-end em HTML, CSS e JavaScript para cadastro e visualização de usuários. O front-end permite realizar operações básicas apenas de criar e listar usuarios

## Como Executar

1. Clone este repositório para sua máquina local.
2. Importe o projeto em sua IDE preferida.
3. Certifique-se de ter o Java e o Spring Boot configurados.
4. Execute a aplicação.
5. Acesse os endpoints conforme documentado acima.

## Contato

Se você tiver alguma dúvida ou feedback sobre esta API, sinta-se à vontade para entrar em contato conosco.
