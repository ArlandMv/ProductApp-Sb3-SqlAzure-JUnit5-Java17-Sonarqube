# CURL COMMANDS

## CREATE
error: 
```
curl -X POST -H "Content-Type: application/json" -d "{\"name\": \"New Product\", \"price\": 19.99}" http://localhost:8080/api/v1/products
```


## READ 
`curl http://localhost:8080/api/v1/products`
```
curl -X GET http://localhost:8080/api/v1/products
```

## READ1

`curl http://localhost:8080/api/v1/products/20`
```
curl -X POST -H "Content-Type: application/json" -d '{"name": "New Product", "price": 19.99}' http://localhost:8080/api/v1/products
```
error
## update
```
curl -X PUT http://localhost:8080/api/v1/products/20 -H "Content-Type: application/json" -d '{"idProduct": 20, "name": "Updated Product", "price": 19.99 }'
```

## DELETE
```
curl -X DELETE http://localhost:8080/api/v1/products/21
```

