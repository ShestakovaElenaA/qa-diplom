# Процедура запуска автотестов.
## ПО для запуска автотестов: 
* IntelliJ IDEA 2023.2.5 (Ultimate Edition).
* Docker Desktop
## Шаги:
Открыть скопированный проект в IDEA, выполнить команды:

1.	docker-compose up   - для запуска контейнера c MySQL, с PostgreSQL и Эмулятором банковского сервиса.
2.	В терминале запустить приложение aqa-shop.jar:

*	Для тестирования запросов в БД MySQL команда java -jar ./artifacts/aqa-shop.jar
*	Для тестирования запросов в БД PostgreSQL
     
  -java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/kuularbase  

  -string.datasource.username=kuular 

  -Dspring.datasource.password=12345v -jar aqa-shop.jar. 

Возможно, в windows потребуется добавить кавычки: 

 -java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/kuularbase"

 -Dspring.datasource.username=kuular" 

 "-Dspring.datasource.password=12345v" -jar aqa-shop.jar 
3. Для запуска автотестов нажать два раза ctrl и выполнить команду gradle clean test.
4. Для получения отчета по результатам прогона автотестов выполнить команду gradle allureServe.
