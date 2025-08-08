
# 📧 Sistema de Autenticação com Spring Boot ( Não finalizado )

Este projeto é um sistema de autenticação seguro desenvolvido com **Spring Boot**, utilizando **JWT**, **Cookies HttpOnly**, **confirmação de e-mail via JavaMailSender** e **Thymeleaf** para renderização de mensagens de feedback. Ideal para aplicações que exigem autenticação robusta com confirmação de identidade por e-mail.

---

## 🚀 Tecnologias Utilizadas

- **Spring Boot** – Framework principal para a aplicação backend.
- **Java** - Java versão 21.
- **PostgreSQL** - Banco de dados.
- **Spring Security** – Controle de autenticação e autorização.
- **JWT (JSON Web Token)** – Autenticação baseada em token.
- **Cookie HttpOnly** – Armazenamento seguro do `refreshToken` no navegador.
- **JavaMailSender** – Envio de e-mails de verificação e confirmação.
- **Thymeleaf** – Renderização de templates HTML para mensagens de verificação e sucesso.
- **Flyway ou Liquibase** – Gerenciamento de migrações de banco de dados.

---

## 🔐 Funcionalidades

### 1. Cadastro de Usuário com Verificação de E-mail
- O usuário se cadastra com nome, e-mail e senha.
- Um **e-mail com um código de 6 dígitos** é enviado para confirmação.
- O e-mail contém um link ou página com template em **Thymeleaf** para digitação do código.

### 2. Confirmação de E-mail
- Após digitar o código corretamente, o e-mail do usuário é confirmado.
- Um **segundo e-mail é enviado**, informando que a conta foi confirmada com sucesso.

### 3. Autenticação com JWT
- Ao fazer login, o backend gera um **Access Token (JWT)** e um **Refresh Token**.
- O **Access Token** é enviado no corpo da resposta.
- O **Refresh Token** é armazenado de forma segura em um **Cookie HttpOnly**, impedindo o acesso por scripts maliciosos.

### 4. Renovação de Token
- Caso o Access Token expire, o usuário pode utilizar o Refresh Token (armazenado no cookie) para obter um novo.

---

## 📦 Execução do Projeto

### Passos
```bash
# Clone o repositório
git clone https://github.com/seu-usuario/seu-projeto.git

# Acesse o diretório do projeto
cd seu-projeto

# Configure o application.properties ou application.yml com suas credenciais de email e banco

# Rode as migrações
./mvnw flyway:migrate

# Execute a aplicação
./mvnw spring-boot:run
```

---

## ⚙️ Configurações importantes

### application.properties (exemplo básico)
```properties
# Configurações do banco
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
spring.datasource.username=root
spring.datasource.password=senha

# JWT
jwt.secret=minha-chave-secreta (SHA-256)
jwt.expiration=900000

# Email
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=seuemail@gmail.com
spring.mail.password=suasenha
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

---

## 📬 Exemplo de E-mails

### E-mail 1: Código de Confirmação

> Olá!  
> Use o código **123456** para confirmar seu e-mail.

### E-mail 2: Confirmação de Sucesso

> Parabéns!  
> Seu e-mail foi confirmado com sucesso. Agora você pode acessar sua conta.

---

## 🛡️ Segurança

- Cookies com flag **HttpOnly** impedem acesso via JavaScript.
- Tokens JWT assinados com chave secreta.
- Expiração controlada e renovação segura com Refresh Token.

---

