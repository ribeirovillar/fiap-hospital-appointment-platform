# FIAP Hospital Appointment Platform 🏥

Sistema backend modular desenvolvido para gestão hospitalar, com foco em consultas, históricos médicos, autenticação segura e notificações automatizadas.

## 📌 Objetivo

Criar uma plataforma escalável, segura e baseada em microserviços independentes, que atenda a diferentes perfis de usuários (médicos, enfermeiros e pacientes), utilizando autenticação com JWT, autorização baseada em papéis e comunicação assíncrona.

---

## ⚙️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Security
- GraphQL
- Docker & Docker Compose
- JWT (JSON Web Tokens)
- RabbitMQ (simulado via log)
- MongoDB / PostgreSQL
- Lombok
- MapStruct
- Maven

---

## 🔐 Perfis de Usuário e Permissões

- **Médicos**: visualizar e editar histórico e consultas.
- **Enfermeiros**: registrar consultas e acessar histórico.
- **Pacientes**: visualizar apenas suas consultas.

---

## 📌 Funcionalidades por Serviço

### 🔐 Auth Service (`localhost:8080`)
- Registro e login de usuários
- Emissão e validação de tokens JWT
- Exposição de dados do usuário autenticado

### 📅 Appointment Service (`localhost:8081`)
- Registro, listagem e atualização de consultas médicas
- Valida tokens JWT via Auth Service
- Consulta dados do usuário autenticado via Auth Service
- Simula envio de lembretes automáticos de consultas via RabbitMQ

### 📢 Notification Service (`localhost:8082`)
- Consome mensagens e registra logs para simular envio

### 📁 History Service (`localhost:8083`)
- Armazena e disponibiliza histórico de atendimentos
- Suporte a GraphQL para consultas flexíveis
- Suporte a filtros por usuário
- Valida tokens JWT via Auth Service
- Consulta dados do usuário autenticado via Auth Service

---

## 🚀 Como Rodar Localmente

> Certifique-se de ter Docker e Docker Compose instalados

```bash
# Clonar o repositório
git clone https://github.com/ribeirovillar/fiap-hospital-appointment-platform.git
cd fiap-hospital-appointment-platform

# Subir os serviços com Docker
docker-compose up --build -d
```

---

## 🔌 Endpoints dos Serviços

| Serviço              | URL                    |
|----------------------|------------------------|
| Auth Service         | http://localhost:8080  |
| Appointment Service  | http://localhost:8081  |
| Notification Service | http://localhost:8082  |
| History Service      | http://localhost:8083  |

---

## 🧪 Testes e Integrações

- As coleções Postman estão disponíveis no diretório `postman/`
- Os testes cobrem autenticação, agendamento, histórico e notificações

---

## 📁 Estrutura de Pastas

- `auth-service/` – Serviço de autenticação e autorização
- `appointment-service/` – Serviço de agendamento de consultas
- `notification-service/` – Serviço de notificações via RabbitMQ
- `history-service/` – Serviço de histórico de pacientes
- `postman/` – Coleções de testes
- `docker-compose.yml` – Orquestração dos containers
- `README.md` – Documentação principal do projeto

---