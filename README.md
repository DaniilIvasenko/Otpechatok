# API обработки заказов и отслеживания подукции

## Для закпуска программы:

* Необходимо запустить два docker контейнера с базами данных для каждого из сервисов. 
Конфигурации этих контейнеров сохранены в [.config](.config).
По дефолту проброшены порты 5002 для контейнера [Otpechatok_OrderService.run.xml](.config%2FOtpechatok_OrderService.run.xml)
и 5001 для контейнера [Otpechatok_ProductServiceDB.run.xml](.config%2FOtpechatok_ProductServiceDB.run.xml)
* запустить конфигурацию ProgramRun [ProgrammRun.run.xml](.config%2FProgrammRun.run.xml) для одновременного запуска всех сервисов.
затем вручную запустить [ApiGateWayRun.run.xml](.config%2FApiGateWayRun.run.xml).
Сервисы запускаются на портах 8080 для ApiGateWay, 8081, 8082.

## Ендпойнты API

Для [OrderService](OrderService) RequestMapping("order")

1. @GetMapping("all_orders"). - получить список всех заказов.В качестве параметра запроса передается id (id пользователя сделавшего заказ). в ответ на запрос приходит список заказов сделанных пользователем
2. @PostMapping("create"). создать новый заказ. В качестве параметра запроса передается id(id пользователя). 
В теле запроса передается список деталей заказа [OrderDetailsDTO.java](OrderService%2Fsrc%2Fmain%2Fjava%2Fru%2Fotpechatok%2Fdto%2FOrderDetailsDTO.java)
в формате

```
[   
    {           
    "productType": "productType",
    "amount": 10,
    "modelFileName": "test.img",
    "description": "деталь to add 2",
    "priceForOne": 10.0
    },
    {
    "productType": "productType",
    "amount": 5,
    "modelFileName": "test1.img",
    "description": "деталь to add 1",
    "priceForOne": 5.0
    }
]
```
3. @PostMapping("cancel/{orderId}") - отмена заказа. в пути указывается id заказа который надо отменить

Для [ProductService](ProductService)  @RequestMapping("product")

1. @GetMapping() - получить список всех продуктов. В ответ приходит List<[Product.java](ProductService%2Fsrc%2Fmain%2Fjava%2Fru%2Fotpechatok%2Fdata%2FProduct.java)> 
2. @GetMapping("/{productType}") - олучить список продукции определенного типа с возможностью сортировки
в качестве переменной пути указывается тип продукта для поиска(либо "STAMP" либо BUSINESS_CARD).
в параметрах запроса можно указать sortField (название поля по которому будет выполнена сортировка).
А также sortType (либо "ASC" по возрастанию либо "DESC" по убыванию)  по умолчанию выполняется сортировка по возрастанию.
В ответе вернется список продуктов указанного типа.
