# Отчёт о проведённой автоматизации:

Реализованы все запланированные сценарии:

**Сценарии оформления тура с оплатой по дебетовой карте:**
1. Покупка тура при заполнении формы валидными данными по дебетовой карте APPROVED.
2. Отказ банка в покупке тура по дебетовой карте при заполнении формы валидными данными по карте DECLINED.
3. Отказ банка в покупке тура по дебетовой карте при заполнении формы данными карты, незарегистрированной в системе.

**Сценарии оформления тура в кредит по данным карты:** 
1. Успешная покупка тура с оплатой в кредит.
2. Отказ банка в покупке тура в кредит при заполнении формы валидными данными по карте DECLINED.
3. Отказ банка в покупке тура в кредит при заполнении формы данными карты, незарегистрированной в системе.

**Сценарии с ошибками при отправке заявки на покупку тура с невалидными данными на форме:**
1. Получение ошибки при отправке формы с буквами в номере карты.
2. Получение ошибки при заполнении формы оплаты по карте данными карты с истекшим сроком действия.
3. Ошибка при покупке тура с невалидными данными владельца карты на форме.
4. Ошибка при отправке заполненной формы с невалидными данными CVC/CVV.
5. Получение ошибки при отправке пустой формы заявки на покупку тура по дебетовой карте.

**При автоматизации сработали риски:**
* Была попытка уйти в слишком широкий охват тестирования, потребовалась оптимизация количества сценариев и проверок.
* Недостаток навыков и знаний в части подключения к нескольким БД и поиск селекторов при отсутствии тестовых меток на странице приложения.

**Общий итог по времени:** 

Запланировано было 30 часов, с учетом рисков 36 часов. На выполнение потрачено 22 с учетом сработавших рисков, и оптимизации перечня проверок в тестах. 