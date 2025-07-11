# Quick Setup Guide

## 🚀 Get Started in 5 Minutes

### 1. Prerequisites Check
Make sure you have:
- ✅ Java 17+ installed (`java -version`)
- ✅ Maven 3.6+ installed (`mvn -version`)
- ✅ Git installed (`git --version`)

### 2. Database Setup (IMPORTANT!)
**⚠️ You need to configure your own database before running the app!**

#### Quick Option: Use H2 (Recommended for Development)
1. Copy the template: `cp application.properties.template src/main/resources/application.properties`
2. Edit `src/main/resources/application.properties` and uncomment the H2 configuration:
```properties
# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:blogcraft_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=create-drop
```

#### Alternative: Use MySQL
1. Install MySQL
2. Create database and user (see README.md for details)
3. Copy the template: `cp application.properties.template src/main/resources/application.properties`
4. Update `application.properties` with your MySQL credentials

### 3. Build & Run
```bash
# Navigate to project directory
cd blogcraft

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### 4. Access the Application
- **Homepage**: http://localhost:8080
- **Login**: http://localhost:8080/login
- **Register**: http://localhost:8080/register
- **H2 Console** (if using H2): http://localhost:8080/h2-console

### 5. Test Accounts
- **Admin**: admin@blogcraft.com / admin123
- **User**: user@blogcraft.com / user123

**Note**: If using H2 database, the application will create fresh data on each startup. If using MySQL, you may need to run the SQL scripts in the root directory to populate sample data.

### 6. Start Development
- Read `DEVELOPMENT_TASKS.md` for detailed task breakdown
- Focus on priority tasks first
- Check `README.md` for full documentation

## 🐛 Troubleshooting

### Common Issues:
1. **Port 8080 already in use**: Change port in `application.properties`
2. **Java version error**: Update to Java 17+
3. **Maven build fails**: Run `mvn clean` then `mvn install`

### Need Help?
- Check existing issues in the repository
- Create a new issue with detailed description
- Review the full `README.md` for comprehensive documentation

---
**Next Step**: Read `DEVELOPMENT_TASKS.md` to understand what needs to be completed. 