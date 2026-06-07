# 🔍 Intelligent Error Monitor
A full stack AI-powered error monitoring system that captures application errors in real time and provides intelligent fix suggestions.

## 🚀 Live Demo
**[https://error-monitor-app.vercel.app](https://error-monitor-app.vercel.app)**

> Sample errors with AI suggestions are pre-loaded. Click **Mark as Resolved** to test the resolve feature!

## ✨ Features
- Real time error capture via REST API
- AI powered fix suggestions using Groq LLaMA model
- PostgreSQL database storage
- React dashboard with severity color coding
- Mark errors as resolved

## 🛠️ Tech Stack
**Backend:**
- Java, Spring Boot, PostgreSQL, Spring Security, Spring Data JPA

**Frontend:**
- React, JavaScript, CSS

**AI:**
- Groq API — LLaMA 3.1 model

## 📁 Project Structure
- **backend/** — Spring Boot REST API with PostgreSQL database
- **frontend/** — React Dashboard UI

## 🔌 API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/errors | Submit new error |
| GET | /api/errors | Get all errors |
| GET | /api/errors/{id} | Get one error |
| PUT | /api/errors/{id}/resolve | Resolve error |

## 💡 How It Works
1. Application throws an error
2. Error sent to Spring Boot REST API
3. Groq AI analyses error and generates specific fix suggestion
4. Error saved to PostgreSQL database with AI suggestion
5. React dashboard displays all errors with severity colors and fix suggestions
6. Developer clicks Resolve button when error is fixed

## 🏃 Run Locally

**Backend:**
```bash
cd backend
./mvnw spring-boot:run
```

**Frontend:**
```bash
cd frontend
npm install
npm start
```

**Environment Variables (backend):**
```
DATABASE_URL=jdbc:postgresql://localhost:5432/errormonitor
DATABASE_USERNAME=postgres
DATABASE_PASSWORD=yourpassword
GROQ_API_KEY=your_groq_api_key
```

## 🎯 Similar To
This project is similar to Sentry and Datadog but with AI powered remediation built in.
