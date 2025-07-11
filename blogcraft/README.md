# BlogCraft - A Professional Blogging Platform

A modern, Twitter/X-inspired blogging platform built with Spring Boot and Thymeleaf, featuring user authentication, admin management, and a professional dashboard interface.

## 🚀 Features

- **User Authentication & Authorization**
  - User registration and login
  - Role-based access (User/Admin)
  - Secure session management

- **Blog Management**
  - Create, edit, and delete blog posts
  - Draft system with save functionality
  - Category and tag support
  - Rich text editing

- **Admin Dashboard**
  - User management interface
  - Post moderation tools
  - System statistics

- **Professional UI/UX**
  - Twitter/X-inspired design
  - Dark/light theme toggle
  - Responsive layout
  - Modern navigation

## 🛠️ Technology Stack

- **Backend**: Spring Boot 3.x, Spring Security, Spring Data JPA
- **Frontend**: Thymeleaf, Bootstrap 5, JavaScript
- **Database**: H2 (development), MySQL/PostgreSQL (production ready)
- **Build Tool**: Maven

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- Git

## 🚀 Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/blogcraft.git
cd blogcraft
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

### 4. Access the Application
- **Homepage**: http://localhost:8080
- **Login**: http://localhost:8080/login
- **Register**: http://localhost:8080/register

### 5. Default Admin Account
- **Username**: admin@blogcraft.com
- **Password**: admin123

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/blogcraft/
│   │   ├── config/          # Configuration classes
│   │   ├── controller/      # REST controllers
│   │   ├── model/          # Entity classes
│   │   ├── repository/     # Data access layer
│   │   └── service/        # Business logic
│   └── resources/
│       ├── templates/      # Thymeleaf templates
│       └── application.properties
```

## 🔧 Configuration

### Database Configuration
**⚠️ IMPORTANT**: This project is configured to use MySQL. You need to set up your own database configuration.

#### Option 1: Use H2 (In-Memory Database) - Recommended for Development
1. Update `application.properties` to use H2:
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

2. The H2 dependency is already included in `pom.xml`

#### Option 2: Use MySQL (Production-like Setup)
1. Install MySQL on your system
2. Create a new database:
```sql
CREATE DATABASE blogcraft_db;
CREATE USER 'blogcraft_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON blogcraft_db.* TO 'blogcraft_user'@'localhost';
FLUSH PRIVILEGES;
```

3. Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blogcraft_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=blogcraft_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

**Note**: The current configuration uses the original developer's MySQL credentials. You must change these to your own database setup.

## 🎯 Remaining Tasks for Partner

### High Priority Issues to Fix:

1. **User Management Page** - Currently empty, needs:
   - User list display with pagination
   - User actions (edit, delete, ban/unban)
   - Search and filter functionality

2. **Dark Theme Toggle** - Not working properly:
   - Fix theme persistence across sessions
   - Ensure all components respect theme setting
   - Add smooth transitions

3. **Admin Profile Enhancement** - Admin and user profiles are identical:
   - Add admin-specific features (user management links)
   - Enhanced dashboard with admin statistics
   - Quick access to moderation tools

4. **Navigation Improvements**:
   - Add back arrow/return navigation
   - Breadcrumb navigation
   - Better mobile navigation

5. **Footer Positioning** - Appears too high on pages with few posts:
   - Implement sticky footer
   - Ensure footer stays at bottom on all pages

### Medium Priority Polish:

6. **UI/UX Enhancements**:
   - Improve form validation feedback
   - Add loading states for actions
   - Enhance error messages
   - Optimize mobile responsiveness

7. **Performance Optimizations**:
   - Implement pagination for blog lists
   - Add caching for frequently accessed data
   - Optimize database queries

8. **Additional Features**:
   - Comment system for blog posts
   - Social sharing buttons
   - Email notifications
   - Image upload for blog posts

## 🧪 Testing

### Run Tests
```bash
mvn test
```

### Database Setup
The application includes sample data scripts:
- `insert_test_user.sql` - Creates test users
- `insert_sample_posts.sql` - Adds sample blog posts
- `fix_posts_table.sql` - Database schema fixes

## 📝 Development Guidelines

1. **Code Style**: Follow Java conventions and Spring Boot best practices
2. **Commits**: Use descriptive commit messages
3. **Testing**: Write unit tests for new features
4. **Documentation**: Update README for new features

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🆘 Support

For questions or issues:
1. Check existing issues in the repository
2. Create a new issue with detailed description
3. Contact the development team

---

**Note**: This project is currently in active development. Some features may be incomplete or have known issues that are being addressed.

## 🛣️ Planned & Upcoming Features

The following features are planned for future development, as per the project requirements:

- **SEO Assistant**: Provides keyword, title, and readability suggestions for blog posts.
- **Collaboration Tools**: Allow co-authoring, commenting, and feedback on drafts.
- **Publishing Workflow**: Implement a full workflow: Draft → Review → Approve → Publish.
- **Enhanced Tagging & Categorization**: Enable classification by topic, domain, or audience.
- **Content Dashboard**: Track blog status, readership, and engagement metrics.

See `DEVELOPMENT_TASKS.md` for detailed breakdown and progress on these modules.