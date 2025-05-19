# ContactIQ

ContactIQ is an intelligent contact manager to organize, connect, and grow your network effortlessly. It supports secure login (Gmail, GitHub, OTP), advanced search, favorites, birthday notifications, export/backup, and a smart dashboard.

## Features
- Secure login via Gmail, GitHub, or OTP
- Complete contact management (name, phone, email, image, address, description, birthday, etc.)
- Advanced search by name, email, phone, LinkedIn, Instagram, etc.
- Favorites & birthday notifications
- Export & backup contacts
- Smart dashboard with recent activity and stats

## Tech Stack
- Java 21, Spring Boot 3
- Spring Security, Spring Data JPA
- Thymeleaf
- PostgreSQL (on Render)
- Cloudinary (for image storage)
- OAuth2 (Google, GitHub)
- Deployed on Render.com

## Local Development

1. **Clone the repository:**
   ```bash
   git clone <your-repo-url>
   cd scm2.0
   ```
2. **Configure environment variables:**
   - Copy `src/main/resources/application.properties` and set your local DB, email, and API keys.
3. **Run the app:**
   ```bash
   ./mvnw spring-boot:run
   ```
   or
   ```bash
   mvn spring-boot:run
   ```

## Deployment on Render.com

1. **Push your code to GitHub.**
2. **Create a new Web Service on Render:**
   - Connect your GitHub repo.
   - Use Docker as the environment.
   - Dockerfile and system.properties are already provided.
3. **Provision a free PostgreSQL database on Render.**
4. **Set the following environment variables in Render:**
   - `PGHOST`, `PGPORT`, `PGDATABASE`, `PGUSER`, `PGPASSWORD` (from Render Postgres dashboard)
   - `PORT` (optional, Render sets this automatically)
   - `GOOGLE_CLIENT_ID`, `GOOGLE_CLIENT_SECRET`, `GITHUB_CLIENT_ID`, `GITHUB_CLIENT_SECRET`
   - `CLOUDINARY_NAME`, `CLOUDINARY_API_KEY`, `CLOUDINARY_API_SECRET`
   - `EMAIL_USERNAME`, `EMAIL_PASSWORD`, `EMAIL_HOST`, `EMAIL_PORT`, `EMAIL_PROTOCOL`
   - `REDIRECT_GOOGLE_URI`, `REDIRECT_GITHUB_URI`
   - `SHOW_SQL`, `DDL_AUTO`, `MAX_FILE_SIZE`, `MAX_REQUEST_SIZE`
5. **Update `application.properties` for Postgres:**
   ```properties
   spring.datasource.url=jdbc:postgresql://${PGHOST}:${PGPORT}/${PGDATABASE}
   spring.datasource.username=${PGUSER}
   spring.datasource.password=${PGPASSWORD}
   spring.datasource.driver-class-name=org.postgresql.Driver
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
   server.port=${PORT:8080}
   ```
6. **Deploy!**
   - Render will build and deploy your app automatically.

## Custom Domain Setup

1. **Add your domain (e.g., contactiq.site) in Render's Custom Domains section.**
2. **Update your DNS provider (e.g., Hostinger):**
   - Add an A record for `@` pointing to Render's provided IP.
   - Add a CNAME for `www` pointing to your Render app URL (e.g., `contactiq.onrender.com`).
3. **Wait for DNS propagation and SSL certificate issuance.**

## Database Schema Notes
- For large text fields, use `@Column(columnDefinition = "TEXT")` in your entities for Postgres compatibility.
- If migrating from MySQL, ensure your Postgres columns are of type `text` (not `oid` or `bytea`).

## Troubleshooting
- **502 Bad Gateway:** Check Render logs for startup errors, especially database or LOB errors.
- **LOB errors:** Ensure `spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true` is set and your entity fields use `@Column(columnDefinition = "TEXT")`.
- **OAuth errors:** Make sure your Google/GitHub redirect URIs match your deployed domain.

## License
MIT 