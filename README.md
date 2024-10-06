**Traini8: Training Center Registry (MVP)**

This project is an implementation of a Training Center Registry system for Government-funded training centers. It's part of the Traini8 assignment and is developed using Spring Boot with PostgreSql as the database. The system provides REST APIs to manage training centers, including the ability to add new centers and retrieve them with filtering options in Paginated format.

**Features**

**Add Training Center**: Allows the creation of a new training center with the following fields:
**Center Name**: Required, with a max length of 40 characters.
**Center Code**: Required, must be exactly 12 alphanumeric characters.
**Address**: Embedded object with detailed address, city, state, and pincode.
**Student Capacity**: Optional integer field.
**Courses Offered**: List of courses that the training center provides.
**Contact Email**: Optional, validated email field.
**Contact Phone**: Required, must be exactly 10 digits.
**Automatically captures CreatedOn timestamp.**

**Fetch Training Centers**: Supports fetching all centers, with optional filtering:
**Filter by City and/or State.**
**Case-insensitive search for city and state fields.**
**Pagination with customizable page size and offset.**
**Default sorting by createdOn and id fields in descending order.**

**Project Structure**

**Spring Boot**: The project uses Spring Boot to handle the RESTful APIs and service layers.
**PostgreSql**: PostgreSql is used as the SQL database for storing training center data.
**DTO Pattern**: Data Transfer Objects (DTOs) are used for input/output handling to keep the business logic clean.
**Validation**: Fields are validated using Java Bean Validation (@NotBlank, @Size, @Pattern, @Email, etc.).
**Exception Handling**: Global exception handling is implemented to return user-friendly error messages for validation errors and other exceptions.
**Pagination and Sorting**: The GET API uses pagination to improve performance and provides sorting by creation date and id.

**Technologies Used**

-Spring Boot
-PostgreSql
-Maven
-ModelMapper for DTO to entity conversion
-Lombok for cleaner code (automatic generation of getters, setters, etc.)
-Java Bean Validation for input validations

**Endpoints**

**POST /api/training-centers**
Create a new training center by providing the required details:

Request body:
json
{
  "centerName": "Center ABC",
  "centerCode": "ABC123456789",
  "address": {
    "detailedAddress": "123 Main St",
    "city": "New York",
    "state": "NY",
    "pincode": "10001"
  },
  "studentCapacity": 150,
  "coursesOffered": ["Java", "Python"],
  "contactEmail": "contact@abc.com",
  "contactPhone": "9876543210"
}
Response:
json
{
  "id": "1",
  "centerName": "Center ABC",
  "centerCode": "ABC123456789",
  "address": {
    "detailedAddress": "123 Main St",
    "city": "New York",
    "state": "NY",
    "pincode": "10001"
  },
  "studentCapacity": 150,
  "coursesOffered": ["Java", "Python"],
  "contactEmail": "contact@abc.com",
  "contactPhone": "9876543210",
}

**GET /api/training-centers**
Fetch all training centers with optional filtering by city and state:

Parameters:
city (optional): Filter centers by city (case-insensitive).
state (optional): Filter centers by state (case-insensitive).
pageOffset (default = 0): Pagination page number.
pageSize (default = 10): Number of centers to display per page.

**Example request:**

/api/training-centers?city=New York&state=NY&pageOffset=0&pageSize=5
Response:
json
{
  "content": [
    {
      "id": "1",
      "centerName": "Center ABC",
      "centerCode": "ABC123456789",
      "address": {
        "detailedAddress": "123 Main St",
        "city": "New York",
        "state": "NY",
        "pincode": "10001"
      },
      "studentCapacity": 150,
      "coursesOffered": ["Java", "Python"],
      "contactEmail": "contact@abc.com",
      "contactPhone": "9876543210",
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 5,
    "offset": 0
  },
  "totalElements": 1,
  "totalPages": 1,
  "last": true
}

**Setup Instructions**

**Clone the repository**:
git clone https://github.com/SantoshMane07/Backend_Traini8_SantoshMane.git
**Configure PostgreSql**:
Add your PostgreSql connection details in application.properties.
**Build and Run**:
Using Maven:
mvn clean install
mvn spring-boot:run

**Test the APIs:**
You can use Postman to test the APIs.
