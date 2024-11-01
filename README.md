# API de Carrito de Compras

Este proyecto es una API de carrito de compras construida con **Spring Boot**, **JPA** y **PostgreSQL**. Proporciona funcionalidades para manejar productos, carritos, cupones y productos en el carrito. La documentación de la API está disponible en **Swagger**.

## Requisitos Previos

- **Java 17** o superior
- **PostgreSQL** (para la base de datos)
- **Gradle** (para construir y correr el proyecto)
- **Git** (para clonar el repositorio)

## Configuración de la Base de Datos

1. **Crear la base de datos**: Abre tu terminal o consola de PostgreSQL y ejecuta los siguientes comandos para crear la base de datos y el usuario:

   ```sql
   CREATE DATABASE carrito_compras;
   CREATE USER carrito_user WITH ENCRYPTED PASSWORD 'password';
   GRANT ALL PRIVILEGES ON DATABASE carrito_compras TO carrito_user;

2. **Ejecutar el script SQL**: Una vez creada la base de datos, ejecuta el siguiente script para crear las tablas necesarias (ajusta según tus necesidades):

    ```sql
   CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW());
    
   CREATE TABLE carts (
    cart_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    total_price NUMERIC(10, 2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW());

    CREATE TABLE coupons (
    coupon_id SERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL,
    discount_percentage NUMERIC(5, 2) NOT NULL,
    valid_from DATE,
    valid_until DATE,
    is_active BOOLEAN DEFAULT TRUE); 
   
   CREATE TABLE cart_items (
    cart_item_id SERIAL PRIMARY KEY,
    cart_id INTEGER REFERENCES carts(cart_id),
    product_id INTEGER REFERENCES products(product_id),
    quantity INTEGER NOT NULL,
    subtotal NUMERIC(10, 2) NOT NULL);
   
   CREATE TABLE cart_coupons (
    cart_coupon_id SERIAL PRIMARY KEY,
    cart_id INTEGER REFERENCES carts(cart_id),
    coupon_id INTEGER REFERENCES coupons(coupon_id));
   
   CREATE TABLE seasonal_discounts (
    seasonal_discount_id SERIAL PRIMARY KEY,
    product_id INTEGER REFERENCES products(product_id),
    discount_percentage NUMERIC(5, 2),
    valid_from DATE,
    valid_until DATE);
   
3. **Configurar el archivo** application.properties: Asegúrate de que el archivo application.properties en src/main/resources esté configurado con la información de conexión a la base de datos.

    ```sql
   spring.datasource.url=jdbc:postgresql://localhost:5432/carrito_compras
   spring.datasource.username=carrito_user
   spring.datasource.password=password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true

## Configuración del Proyecto

1. Clonar el repositorio:

    ```sql
    git clone https://github.com/sebmenca10/pruebaindra.git
    cd nombre-del-repositorio

2. Construir el proyecto con Gradle:

    ```sql
    ./gradlew build

3. Ejecutar la aplicación:

    ```sql
    ./gradlew bootRun

- **La aplicación se ejecutará en:** http://localhost:8080 por defecto.

## Documentación de la API

La documentación de Swagger está disponible una vez que la aplicación esté en ejecución. Puedes acceder a la documentación en la siguiente URL:
- **Swagger UI:** http://localhost:8080/swagger-ui/index.html

**Pruebas en Postman**

-URL Base: http://localhost:8080
-Endpoints disponibles:
   -Productos: /api/products
   -Carritos: /api/carts
   -Productos en el carrito: /api/cart-items
   -Cupones: /api/coupons
   
**Ejemplos de Peticiones**
Crear un Producto
   -Método: POST
   -URL: http://localhost:8080/api/products
   -Cuerpo (JSON):
   
       {
       "name": "Producto Ejemplo",
       "price": 5500
       }
       
Obtener todos los Productos
   -Método: GET
   -URL: http://localhost:8080/api/products
Crear un Carrito
   -Método: POST
   -URL: http://localhost:8080/api/carts
   -Cuerpo (JSON):
   
       {
       "userId": 1
       }
       
Agregar un Producto al Carrito
   -Método: POST
   -URL: http://localhost:8080/api/cart-items/{cartId}/{productId}
   -Parámetro de Query: quantity (ej. ?quantity=3)

## Ejecutar Pruebas Unitarias

Para ejecutar las pruebas unitarias, usa el siguiente comando:

    ./gradlew test

Los resultados de las pruebas se mostrarán en la consola, y los reportes estarán en el directorio build/reports/tests
