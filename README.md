# API обработки заказов и отслеживания подукции

## Для закпуска программы:

* Необходимо запустить два docker контейнера с базами данных для каждого из сервисов. 
Конфигурации этих контейнеров сохранены в [.config](.config).
По дефолту проброшены порты 5002 для контейнера [Otpechatok_OrderService.run.xml](.config%2FOtpechatok_OrderService.run.xml)
и 5001 для контейнера [Otpechatok_ProductServiceDB.run.xml](.config%2FOtpechatok_ProductServiceDB.run.xml)
* запустить конфигурацию ProgramRun [ProgrammRun.run.xml](.config%2FProgrammRun.run.xml) для одновременного запуска всех сервисов.

