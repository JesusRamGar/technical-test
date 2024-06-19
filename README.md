# Inditex Technical Test

- [Inditex Technical Test](#inditex-technical-test)
    * [Task statement](#task-statement)

## Task statement

Finish implementing the REST API for the Offer domain entity.

The project is delivered with a simple package structure following a hexagonal architecture and Domain-Driven Design (DDD), including a controller, DTO, entity, and exception for the REST layer. Data should be stored in an in-memory database. Essentially, implement a CRUD for Offer.

The only peculiarity is that the `productPartnumber` in the database should be stored in multiple columns. For example, if the input is 000100233 (TTMMMMQQQ), it should be stored as:

- Size = 00
- Model = 0100
- Quality = 233

Note that the Offer entity is not defined this way initially and can be changed. This data should be displayed in both input and output with this format:

```json
"productPartnumber": "000100233"
```

Evaluation criteria:
* Unit and integration testing. 
* Use of hexagonal architecture and DDD. 
* Use of design patterns. 
* Validations. 
* Exception handling.

### Project Details

* The dependencies provided are sufficient to set up everything required, but you can add more if necessary.
* You are free to make changes to the code, packages, etc.
* The project must compile, be operational, and store data in an in-memory database.
* Include a sample Postman collection in the repository.

Example JSON input for creating the Offer entity:

```json
[
    {
        "offerId": 1,
        "brandId": 1,
        "startDate": "2020-06-14T00.00.00Z",
        "endDate": "2020-12-31T23.59.59Z",
        "priceListId": 1,
        "productPartnumber": "000100233",
        "priority": 0,
        "price": 35.50,
        "currencyIso": "EUR"
    }
]
```
