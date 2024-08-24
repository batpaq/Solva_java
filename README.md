# Solva_java
Описание
Этот микросервис предназначен для управления транзакциями и лимитами расходов. Он записывает транзакции в разных валютах, управляет месячными лимитами и интегрируется с внешним API для получения валютных курсов. Микросервис предоставляет REST API для обработки транзакций, установки новых лимитов и получения отчетов о превышении лимита.

Архитектура
Основные компоненты
Transaction Service: Обрабатывает транзакции, проверяет превышение лимита и сохраняет данные о транзакциях в базе данных.
Expense Limit Service: Управляет установкой и хранением месячных лимитов расходов.
Currency Service: Интегрируется с внешним API для получения актуальных валютных курсов и сохраняет их в базе данных.
Используемые технологии
Spring Boot: Фреймворк для упрощения разработки и развертывания приложений на Java.
Spring Data JPA: Позволяет работать с базой данных, используя объекты и репозитории.
H2 Database: Встроенная реляционная база данных для тестирования и разработки .
RestTemplate: Используется для выполнения HTTP-запросов к внешнему API для получения валютных курсов.
JUnit: Фреймворк для написания тестов на Java.
Выбор инструментов
Spring Boot был выбран для упрощения конфигурации и запуска приложения, а также для быстрого создания REST API и интеграции с базой данных.
Spring Data JPA упрощает работу с базой данных, предоставляя удобные репозитории и возможности для выполнения запросов.
H2 Database используется для простоты настройки в процессе разработки и тестирования. В продакшене  использован более производительную базу данных ORACLE.
RestTemplate позволяет выполнять запросы к внешнему API для получения валютных курсов. Альтернативой может быть WebClient, если требуется асинхронное выполнение запросов.
JUnit используется для написания тестов, что помогает поддерживать качество кода и проверять функциональность приложения.
Документация
Для получения дополнительной информации о каждом из используемых инструментов и фреймворков, пожалуйста, ознакомьтесь с их официальной документацией:

Spring Boot
Spring Data JPA
H2 Database
RestTemplate
JUnit
