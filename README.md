# Инструкция по запуску
### Для запуска нужны JDK17, Maven, Docker, Git
___

Клонировать репозиторий, в терминале установить путь в корневой каталог проекта
и ввести следующие команды
- ```mvn clean package```
- ```docker compose -f .\docker-compose.yml up```

Перейти в браузер
- Интерфейс Swagger доступен по адресу ```localhost:8080/swagger-ui.html```
- В верхней форме ввести ```/v3/api-docs``` и нажать ```Explore```