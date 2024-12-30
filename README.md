# README for Bookstore Backend (Spring Framework)

## Overview
This is the backend service for the Bookstore project, built using Spring Boot. It provides APIs to manage books, authors, categories, user authentication, and orders.

## Features
- RESTful APIs for managing:
  - Books
  - Authors
  - Categories
  - Users
  - Orders
- JWT-based authentication and authorization
- Exception handling for robust API responses
- Integration with a relational database (MySQL)
- Pagination and filtering support
- Data seeding using SQL files

## Technologies Used
- **Spring Boot**
- **Spring Security**
- **Hibernate/JPA**
- **MySQL**
- **Maven**
- **Java 21**

## Prerequisites
- Java 21
- Maven 3.8+
- MySQL Server

## Getting Started

### Setup Database
1. Create a database named `bookstore` in your MySQL server.
2. Import the SQL data files located in the `src/main/resources/data` directory. There are four SQL files:
   - `authors.sql`: Inserts data into the authors table.
   - `books.sql`: Inserts data into the books table.
   - `categories.sql`: Inserts data into the categories table.
   - `users.sql`: Inserts data into the users table.

### Clone Repository
```bash
git clone https://github.com/your-username/bookstore-project.git
cd bookstore-project/backend
```

### Configuration
1. Update the `application.properties` file located in `src/main/resources/`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   spring.jpa.hibernate.ddl-auto=update
   jwt.secret=your-secret-key
   ```

### Build and Run
```bash
mvn clean install
mvn spring-boot:run
```
The backend will start on `http://localhost:8080`.

### API Endpoints
- **Books:** `/api/bookstore/books`
- **Best Sellers:** `/api/bookstore/best_seller`
- **New Arrivals:** `/api/bookstore/new_arrivals`
- **Authentication:**
  - Register: `/auth/register`
  - Login: `/auth/login`

## License
This project is licensed under the MIT License.

---

# README for Bookstore Frontend (React + Next.js)

## Overview
This is the frontend for the Bookstore project, built using React and Next.js. It provides a user-friendly interface to browse books, view details, and manage a shopping cart.

## Features
- Browse books by categories: Best Sellers, New Arrivals, and All Books
- Add books to the shopping cart
- View and manage cart items with quantity adjustment
- User registration and login
- Responsive design

## Technologies Used
- **React**
- **Next.js**
- **TypeScript**
- **Tailwind CSS**
- **Axios**

## Prerequisites
- Node.js 18+
- npm or Yarn

## Getting Started

### Clone Repository
```bash
git clone https://github.com/your-username/bookstore-project.git
cd bookstore-project/frontend
```

### Install Dependencies
```bash
npm install
```

### Environment Variables
Create a `.env.local` file in the root directory and add:
```env
NEXT_PUBLIC_API_BASE_URL=http://localhost:8080
```

### Run the Development Server
```bash
npm run dev
```
The frontend will start on `http://localhost:3000`.

## Folder Structure
```plaintext
frontend/
|-- public/         # Static assets
|-- src/
    |-- app/        # Next.js App Router
    |-- components/ # Reusable UI components
    |-- styles/     # Global styles
    |-- ds/         # Data structures (e.g., DTOs)
```

## Key Pages
- **Home:** Displays featured books
- **Book Details:** `/book-detail/[bookTitle]`
- **Cart:** `/cart`
- **All Books:** `/all-books`

## Scripts
- `npm run dev`: Starts the development server
- `npm run build`: Builds the application for production
- `npm start`: Runs the production server

## License
This project is licensed under the MIT License.
