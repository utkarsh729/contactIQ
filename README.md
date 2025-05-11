# SCM 2.0

A Spring Boot application for Supply Chain Management.

## Prerequisites

- Java 21
- Maven
- MySQL
- Git

## Local Development Setup

1. Clone the repository:
```bash
git clone https://github.com/your-username/scm2.0.git
cd scm2.0
```

2. Set up environment:
```bash
# On Unix/Linux/Mac
chmod +x setup-env.sh
./setup-env.sh

# On Windows
.\setup-env.sh
```

3. Update the configuration files:
   - `.env` - Update with your local credentials
   - `src/main/resources/application.properties` - Update with your local configuration

4. Run the application:
```bash
./mvnw spring-boot:run
```

## Deployment on Render

### 1. Database Setup

1. Go to Render Dashboard
2. Click "New +" → "Database"
3. Select MySQL
4. Configure:
   - Name: scm2-db
   - Database: scm20
   - User: scm2_user
   - Region: Choose closest to your users

### 2. Environment Variables

Set these in Render's dashboard:

```
# OAuth2
GOOGLE_CLIENT_ID=your-google-client-id
GOOGLE_CLIENT_SECRET=your-google-client-secret
REDIRECT_GOOGLE_URI=https://your-app.onrender.com/login/oauth2/code/google

GITHUB_CLIENT_ID=your-github-client-id
GITHUB_CLIENT_SECRET=your-github-client-secret
REDIRECT_GITHUB_URI=https://your-app.onrender.com/login/oauth2/code/github

# Cloudinary
CLOUDINARY_NAME=your-cloud-name
CLOUDINARY_API_KEY=your-api-key
CLOUDINARY_API_SECRET=your-api-secret

# Email
EMAIL_USERNAME=your-email@gmail.com
EMAIL_PASSWORD=your-app-password

# Security
JWT_SECRET=your-secure-jwt-secret
JWT_EXPIRATION=86400000
```

### 3. Deploy

1. Connect your GitHub repository to Render
2. Select the repository
3. Configure:
   - Name: scm2
   - Environment: Java
   - Build Command: ./mvnw clean package -DskipTests
   - Start Command: java -jar target/scm2.0-0.0.1-SNAPSHOT.jar

## Security Best Practices

1. Never commit sensitive information to Git
2. Use environment variables for all secrets
3. Keep your .env file local and never commit it
4. Regularly rotate passwords and API keys
5. Use strong, unique passwords for each service

## Features

- User Authentication (OAuth2 with Google and GitHub)
- File Upload with Cloudinary
- Email Verification
- JWT Security
- MySQL Database
- RESTful APIs

## Tech Stack

- Spring Boot 3.4.3
- Spring Security
- Spring Data JPA
- MySQL
- Thymeleaf
- Cloudinary
- JWT
- OAuth2 