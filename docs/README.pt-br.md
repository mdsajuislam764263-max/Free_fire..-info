# 🎮 Free Fire Telegram Bot

> [🇺🇸 Read in English](README.md)

Bot para Telegram feito com **Spring Boot** que fornece informações de jogadores e verificação de banimento do Free Fire usando APIs públicas.

## ✨ Funcionalidades

- `/ban <UID>` — Verificar se uma conta está banida (permanente ou temporário)
- `/player <UID>` — Ver perfil completo do jogador (nick, nível, rank, região, likes, guild, etc.)
- Validação de entrada e tratamento de erros
- Arquitetura limpa com services separados

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot 4**
- **Spring WebFlux** (WebClient para chamadas de API)
- **TelegramBots 6.9.7.1**
- **Lombok**
- **Docker**

## 📡 APIs Utilizadas

| Funcionalidade | API |
|----------------|-----|
| Info do Jogador | `freefire-api-six.vercel.app` (0xMe/FreeFire-Api) |
| Check Ban | `ff.garena.com/api/antihack/check_banned` (Garena Oficial) |

## 🚀 Como Rodar

### Pré-requisitos

- Java 21+
- Maven
- Token de Bot do Telegram (crie um com o [@BotFather](https://t.me/BotFather))

### Configuração

1. Clone o repositório:
```bash
git clone https://github.com/SEU_USER/freefirebot.git
cd freefirebot
```

2. Configure as variáveis de ambiente:
```bash
export BOT_TOKEN=seu_token
export BOT_USERNAME=seu_username
```

3. Build e execução:
```bash
mvn clean package -DskipTests
java -jar target/freefirebot-0.0.1-SNAPSHOT.jar
```

### Docker

```bash
docker build -t ff-bot .
docker run -d -e BOT_TOKEN=seu_token -e BOT_USERNAME=seu_username ff-bot
```

## 📁 Estrutura do Projeto

```
com.devlil0.freefirebot
├── bot/                → Bot do Telegram & configuração
├── service/            → Comunicação com APIs (WebClient)
├── model/dto/          → Data Transfer Objects
└── helper/             → Classes utilitárias
```

## 📌 Comandos

| Comando | Descrição |
|---------|-----------|
| `/start` | Mensagem de boas-vindas e lista de comandos |
| `/ban <uid>` | Verificar status de ban de uma conta Free Fire |
| `/player <uid>` | Obter informações do perfil do jogador |

## 🤝 Contribuindo

Fique à vontade para fazer fork, abrir issues ou enviar pull requests.

## 📄 Licença

Este projeto é apenas para fins educacionais. Não é afiliado à Garena ou Free Fire.
