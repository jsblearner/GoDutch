# GoDutch - Expense Sharing Application

GoDutch is an expense sharing application similar to Splitwise, built with Java Spring Boot backend and React.js frontend.

## Features

- User registration and authentication
- Create and manage groups
- Add and split expenses
- Track balances between users
- Settle debts
- Expense history and analytics

## Tech Stack

- **Backend**: Java Spring Boot, Spring Security, JPA/Hibernate
- **Frontend**: React.js, Material-UI, Axios
- **Database**: H2 (development), MySQL/PostgreSQL (production)
- **Authentication**: JWT tokens

## Project Structure

```
/
├── backend/          # Spring Boot application
└── frontend/         # React.js application
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Node.js 18 or higher
- Maven 3.6+

### Running the Application

1. **Backend Setup**:
   ```bash
   cd backend
   mvn spring-boot:run
   ```

2. **Frontend Setup**:
   ```bash
   cd frontend
   npm install
   npm start
   ```

The backend will run on `http://localhost:8080` and frontend on `http://localhost:3000`.

## API Documentation

API documentation will be available at `http://localhost:8080/swagger-ui.html` when the backend is running.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request
