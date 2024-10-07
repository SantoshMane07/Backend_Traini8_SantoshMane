**Traini8: Training Center Registry (MVP)**

This project is an implementation of a Training Center Registry system for Government-funded training centers. It's part of the Traini8 assignment and is developed using Spring Boot with PostgreSql as the database. The system provides REST APIs to manage training centers, including the ability to add new centers and retrieve them with filtering options in Paginated format.

**Features**

**Add Training Center**: Allows the creation of a new training center with the following fields:<br>
**Center Name**: Required, with a max length of 40 characters.<br>
**Center Code**: Required, must be exactly 12 alphanumeric characters.<br>
**Address**: Embedded object with detailed address, city, state, and pincode.<br>
**Student Capacity**: Optional integer field.<br>
**Courses Offered**: List of courses that the training center provides.<br>
**Contact Email**: Optional, validated email field.<br>
**Contact Phone**: Required, must be exactly 10 digits.<br>
**Automatically captures CreatedOn timestamp.**

**Fetch Training Centers**: Supports fetching all centers, with optional filtering:<br>
**Filter by City and/or State.**<br>
**Case-insensitive search for city and state fields.**<br>
**Pagination with customizable page size and offset.**<br>
**Default sorting by createdOn and id fields in descending order.**

**Project Structure**

**Spring Boot**: The project uses Spring Boot to handle the RESTful APIs and service layers.<br>
**PostgreSql**: PostgreSql is used as the SQL database for storing training center data.<br>
**DTO Pattern**: Data Transfer Objects (DTOs) are used for input/output handling to keep the business logic clean.<br>
**Validation**: Fields are validated using Java Bean Validation (@NotBlank, @Size, @Pattern, @Email, etc.).<br>
**Exception Handling**: Global exception handling is implemented to return user-friendly error messages for validation errors and other exceptions.<br>
**Pagination and Sorting**: The GET API uses pagination to improve performance and provides sorting by creation date and id.<br>

**Technologies Used**

-Spring Boot<br>
-PostgreSql<br>
-Maven<br>
-ModelMapper for DTO to entity conversion<br>
-Lombok for cleaner code (automatic generation of getters, setters, etc.)<br>
-Java Bean Validation for input validations<br>

**Endpoints**

**POST /api/training-centers**<br>
Create a new training center by providing the required details:

Request body:<br>
json<br>
{<br>
  "centerName": "Center ABC",<br>
  "centerCode": "ABC123456789",<br>
  "address": {<br>
    "detailedAddress": "123 Main St",<br>
    "city": "New York",<br>
    "state": "NY",<br>
    "pincode": "10001"<br>
  },<br>
  "studentCapacity": 150,<br>
  "coursesOffered": ["Java", "Python"],<br>
  "contactEmail": "contact@abc.com",<br>
  "contactPhone": "9876543210"<br>
}<br>
Response:<br>
json<br>
{<br>
  "id": "1",<br>
  "centerName": "Center ABC",<br>
  "centerCode": "ABC123456789",<br>
  "address": {<br>
    "detailedAddress": "123 Main St",<br>
    "city": "New York",<br>
    "state": "NY",<br>
    "pincode": "10001"<br>
  },<br>
  "studentCapacity": 150,<br>
  "coursesOffered": ["Java", "Python"],<br>
  "contactEmail": "contact@abc.com",<br>
  "contactPhone": "9876543210",<br>
}<br>

**GET /api/training-centers**<br>
Fetch all training centers with optional filtering by city and state:

Parameters:<br>
city (optional): Filter centers by city (case-insensitive).<br>
state (optional): Filter centers by state (case-insensitive).<br>
pageOffset (default = 0): Pagination page number.<br>
pageSize (default = 10): Number of centers to display per page.<br>

**Example request:**

/api/training-centers?city=New York&state=NY&pageOffset=0&pageSize=5<br>
Response:<br>
json<br>
{<br>
  "content": [<br>
    {<br>
      "id": "1",<br>
      "centerName": "Center ABC",<br>
      "centerCode": "ABC123456789",<br>
      "address": {<br>
        "detailedAddress": "123 Main St",<br>
        "city": "New York",<br>
        "state": "NY",<br>
        "pincode": "10001"<br>
      },<br>
      "studentCapacity": 150,<br>
      "coursesOffered": ["Java", "Python"],<br>
      "contactEmail": "contact@abc.com",<br>
      "contactPhone": "9876543210",<br>
    }<br>
  ],<br>
  "pageable": {<br>
    "pageNumber": 0,<br>
    "pageSize": 5,<br>
    "offset": 0<br>
  },<br>
  "totalElements": 1,<br>
  "totalPages": 1,<br>
  "last": true<br>
}<br>

**Setup Instructions**

**Clone the repository**:<br>
git clone https://github.com/SantoshMane07/Backend_Traini8_SantoshMane.git

**Configure PostgreSql**:<br>
Add your PostgreSql connection details in application.properties.

**Build and Run**:<br>
Using Maven:<br>
mvn clean install<br>
mvn spring-boot:run

**Test the APIs:**<br>
You can use Postman to test the APIs.
