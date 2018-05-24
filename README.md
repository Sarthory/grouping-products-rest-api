# Grouping Products REST API

**API implementada em Java com o framework Spring Boot visando cumprir os requisitos do desafio técnico proposto.**

Esta aplicação tem como objetivo satisfazer ao máximo as necessidades implicadas no teste, possuindo as seguintes funcionalidades:

 - Receber um arquivo JSON composto por um array de objetos (produtos)

 ```json
[{
    "id": "123",
    "ean": "7898100848355",
    "title": "Cruzador espacial Nikana - 3000m - sem garantia",
    "brand": "nikana",
    "price": 820900.90,
    "stock": 1
    },
    {
    "id": "u7042",
    "ean": "7898054800492",
    "title": "Espada de fótons Nikana Azul",
    "brand": "nikana",
    "price": 2199.90,
    "stock": 82
    },
    {
    "id": "bb2r3s0",
    "ean": "2059251400402",
    "title": "Corredor POD 3000hp Nikana",
    "brand": "nikana",
    "price": 17832.90,
    "stock": 8
    },
    {
    "id": "321",
    "ean": "7898100848355",
    "title": "Cruzador espacial Nikana - 3000m - sem garantia",
    "brand": "trek",
    "price": 790300.90,
    "stock": 0
    },
    {
    "id": "80092",
    "ean": "",
    "title": "Espada de Fótons REDAV Azul",
    "brand": "redav",
    "price": 1799.90,
    "stock": 0
    },
    {
    "id": "7728uu",
    "ean": "7898100848355",
    "title": "Cruzador espacial Ekul - 3000m - sem garantia",
    "brand": "ekul",
    "price": 1300000.00,
    "stock": 1
}]
```
 - Processar e agrupar estes produtos de acordo com critérios impostos
 - Filtrar os produtos conforme os critérios opcionais
 - Ordenar os produtos conforme os critérios opcionais
 - Devolver um JSON no formato requisitado como resposta de seu payload

## Executando o programa pelo diretório
Para executar esta aplicação web em seu diretório local, é necessário possuir o [Maven](https://maven.apache.org/install.html) instalado na máquina. Navegue até o diretório e:

> $ cd grouping-products-rest-api

> $ mvn spring-boot:run

## Executando o programa utilizando o arquivo .jar
Está disponibilizado neste repositório no diretório /standalone um arquivo .jar para execução standalone.
Para executar esta aplicação web com o arquivo `GoupingProductsAPI.jar`:

> $ cd grouping-products-rest-api/standalone

> $ java -jar GoupingProductsAPI.jar

## REST Endpoints

Após inicializada, a aplicação estará disponível no seguinte endereço:
> http://localhost:8080

| HTTP        | URI           | Ação  |
| ------------- |-------------|:-----|
| `POST` | `/products` | Recebe o payload de entrada e o prepara para o processamento |
| `GET` | `/products` | Resgata uma lista já processada e agrupada por EANs idênticos de acordo com o primeiro critério de agrupamento padrão |
| `GET` | `/products/group_by={term}` | Resgata uma lista de produtos agrupada de acordo com o critério passado no lugar de *{term}*, os critérios aceitos e possíveis são: <ul><li>`ean` agrupa produtos com EANs idênicos</li><li>`title` agrupa produtos com similaridade de tíulo</li><li>`brand` agrupa produtos de mesma marca</li></ul> |
| `GET` | `/products/filter={term}:{value}` | Resgata uma lista de produtos ou um único produto de acordo com o termo passado no lugar de *{term}*, os termos aceitos e possíveis são: <ul><li>`id` filtra produtos pelo ID</li><li>`ean` filtra produtos pelo EAN</li><li>`title`  filtra produtos pelo título</li><li>`brand` filtra produtos pela marca</li></ul> Já o valor passado no lugar de *{value}* é definido pelo usuário que está se utilizando do filtro |
| `GET` | `/products/order_by={criteria}:{direction}` | Resgata uma lista de produtos ordenada de acordo com o critério passado no lugar de *{criteria}*, os critérios aceitos e possíveis são: <ul><li>`stock` ordena os produtos por quantidade de estoque</li><li>`price` ordena os produtos por preço</li></ul> A ordem crescente ou decrescente é passada no lugar de *{direction}*, os termos aceitos e possíveis são: <ul><li>`asc` faz a ordenação em ordem crescente</li><li>`desc` faz a ordenação em ordem decrescente</li></ul> |
