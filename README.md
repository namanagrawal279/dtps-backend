🚀 Overview
DTPS (Distributed Transaction Processing System) is a Spring Boot backend application deployed on AWS that processes financial transactions and persists them in an AWS RDS PostgreSQL database.
The application is deployed on an EC2 instance and communicates securely with RDS, following a proper 3-tier cloud architecture.

🏗️ Architecture
Client (API Call)
→ EC2 (Spring Boot Application)
→ AWS RDS (PostgreSQL Database)
Compute: AWS EC2
Database: AWS RDS (PostgreSQL)
Backend Framework: Spring Boot
ORM: Spring Data JPA (Hibernate)
Build Tool: Maven

🛠️ Tech Stack
Java 17+
Spring Boot
Spring Data JPA
PostgreSQL
Maven
AWS EC2
AWS RDS

📁 Project Structure 
src/
 ├── main/
 │   ├── java/com/dtps/app/
 │   │   ├── controller/
 │   │   │   ├── HealthController.java
 │   │   │   └── TransactionController.java
 │   │   ├── model/
 │   │   │   └── Transaction.java
 │   │   ├── repository/
 │   │   │   └── TransactionRepository.java
 │   │   └── DtpsApplication.java
 │   └── resources/
 │       └── application.properties

⚙️ Configuration
application.properties
server.port=8081

spring.datasource.url=jdbc:postgresql://<RDS-ENDPOINT>:5432/dtpsdb
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

▶️ Running the Application
1️⃣ Build the project
mvn clean install
2️⃣ Run the application
mvn spring-boot:run

🧪 API Endpoints
🔹 Health Check
GET /actuator/health

🔹 Create Transaction
POST /api/tx

🗄️ Database Verification
Connect to RDS:
psql -h <RDS-ENDPOINT> -U postgres -d dtpsdb

Check stored transactions:
select * from transactions;

🔐 Security Groups
EC2:
Port 22 (SSH)
Port 8081 (Application)
RDS:
Port 5432 (PostgreSQL)
Allowed from EC2 Security Group

📸 Deployment Proof
Spring Boot running on EC2
API tested via curl
Data verified in PostgreSQL
RDS instance active
EC2 instance running
Code hosted on GitHub

✅ Features Implemented
REST API for transaction processing
Persistent storage using RDS
JPA entity mapping
Automatic table creation
Health monitoring via Actuator
Cloud deployment on AWS

📌 Future Improvements
Authentication & Authorization (Spring Security)
Docker containerization
CI/CD pipeline
Transaction validation logic
Logging & Monitoring (CloudWatch)
