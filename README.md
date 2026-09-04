# 🔗 URL Shortener

Современный сервис для сокращения URL-ссылок, построенный на Spring Boot с использованием PostgreSQL и Redis.

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## ✨ Особенности

- 🚀 **Быстрый редирект** — кэширование в Redis ускоряет переход по ссылкам
- 🔐 **Валидация URL** — проверка корректности входящих ссылок
- 📊 **Аналитика** — подсчет переходов по каждой ссылке
- 🎯 **Rate Limiting** — защита от злоупотреблений (5 запросов/мин с IP)
- 🐳 **Docker** — полная контейнеризация приложения
- 📚 **Swagger UI** — автоматическая документация API
- ✅ **Тесты** — unit и интеграционные тесты

## 🛠 Технологии

- **Backend**: Java 17, Spring Boot 4.1.0
- **База данных**: PostgreSQL 17
- **Кэширование**: Redis
- **Документация**: Springdoc OpenAPI (Swagger)
- **Тестирование**: JUnit 5, Mockito, Testcontainers
- **Сборка**: Maven
- **Контейнеризация**: Docker, Docker Compose

## 📋 Требования

- Java 17 или выше
- Docker и Docker Compose
- Maven 3.9+

## 🚀 Быстрый старт

### Запуск через Docker (рекомендуется)

```bash
# Клонируйте репозиторий
git clone https://github.com/yourusername/url-shortener.git
cd url-shortener

# Запустите все сервисы одной командой
docker-compose up -d