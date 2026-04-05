# Hibernate Learning Project

Учебный backend-проект, демонстрирующий работу с **Hibernate ORM**, **Spring Framework** и **PostgreSQL**.

Проект реализует консольное приложение для управления клиентами, заказами, профилями и купонами.

---

## Используемые технологии

### Основной стек

- **Java 17+**
- **Spring Framework**
  - Spring Context (IoC контейнер)
  - Spring Transactions (`@Transactional`)
- **Hibernate ORM**
- **Jakarta Persistence API (JPA)**

### База данных

- **PostgreSQL**
- **HikariCP** — пул соединений

### Инфраструктура

- **Docker**
- **Docker Compose**

### Сборка

- **Maven**

---

## Архитектура проекта

- **UI слой** (`console_UI`)
  - обработка пользовательского ввода (консоль)

- **Service слой** (`service`)
  - бизнес-логика
  - транзакции

- **Config слой** (`config`)
  - настройка Hibernate
  - настройка DataSource
  - конфигурация Spring

- **Domain слой** (`core`)
  - JPA-сущности

---

## Используемые связи (JPA)

В проекте реализованы основные типы связей:

- `@OneToOne` — Client ↔ Profile
- `@OneToMany` — Client → Orders
- `@ManyToOne` — Order → Client
- `@ManyToMany` — Client ↔ Coupon

## Docker

Проект использует `docker-compose` для запуска:

- PostgreSQL
