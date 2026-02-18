🚀 Distributed Transaction Processing System (DTPS)

📌 **Overview**
DTPS (Distributed Transaction Processing System) is a production-grade Spring Boot backend application deployed on AWS and orchestrated using Kubernetes.
The system processes financial transactions, stores them securely in AWS RDS (PostgreSQL), generates transaction receipts in AWS S3, and is deployed using Docker (Distroless image) with Kubernetes auto-scaling.
The application follows:
IAM-based authentication (no hardcoded credentials)
Principle of Least Privilege
Resilience patterns (Circuit Breaker)
Virtual Threads for concurrency
Kubernetes HPA for scalability

🏗 **Architecture**
Client (API Call)
↓
Kubernetes Service (NodePort)
↓
Spring Boot Application (Running in Pods)
↓
AWS RDS (PostgreSQL via IAM Authentication)
↓
AWS S3 (Receipt Storage)

☁ **Cloud Infrastructure**
Compute: AWS EC2
Container Runtime: Docker
Orchestration: Kubernetes (Minikube for development)
Database: AWS RDS (PostgreSQL)
Object Storage: AWS S3
Authentication: IAM Role (No hardcoded credentials)

🛠 **Tech Stack**
Java 24
Spring Boot 3.x
Spring Data JPA (Hibernate)
Resilience4j (Circuit Breaker)
PostgreSQL
Maven
Docker (Multi-stage + Distroless)
Kubernetes (ReplicaSet + HPA)
AWS EC2
AWS RDS
AWS S3

⚙ **Phase 1 – Linux Administration & Security**
Ubuntu 22.04 LTS provisioned
provision.sh script to install:
OpenJDK 24
Docker
Maven
systemd service to manage application lifecycle
logrotate configured (compress logs older than 24 hours)
Non-privileged service user created for security

⚙ **Phase 2 – Backend Development**
✅ REST APIs
POST /api/tx
→ Validates and stores transaction data
→ Generates receipt (.txt)
→ Uploads receipt to S3
GET /api/health
→ Returns:
JVM Memory Usage
Database Connection Status
✅ Resilience
Implemented Circuit Breaker using Resilience4j
Prevents cascading failures when DB/S3 is unavailable
✅ Concurrency
Implemented Java Virtual Threads
Optimized handling of high-volume concurrent requests

⚙ **Phase 3 – Cloud Integration**
Database
AWS RDS (PostgreSQL)
IAM Database Authentication
No hardcoded passwords
Object Storage
Receipt generated for every transaction
Stored in AWS S3 bucket
Security
IAM Role attached to EC2
Principle of Least Privilege implemented

⚙ **Phase 4 – Kubernetes Orchestration**
🐳 Containerization
Multi-stage Dockerfile
Distroless base image (enhanced security)
☸ Kubernetes Manifest Includes:
Deployment with minimum 3 replicas
Horizontal Pod Autoscaler (CPU > 70%)
Liveness Probe
Readiness Probe
ConfigMap for configuration
Kubernetes Secrets for sensitive values

📁 **Project Structure**
src/
 ├── main/
 │   ├── java/com/dtps/app/
 │   │   ├── controller/
 │   │   │   ├── HealthController.java
 │   │   │   └── TransactionController.java
 │   │   ├── service/
 │   │   │   ├── S3Service.java
 │   │   │   └── ReceiptService.java
 │   │   ├── config/
 │   │   │   ├── S3Config.java
 │   │   │   └── VirtualThreadConfig.java
 │   │   ├── repository/
 │   │   │   └── TransactionRepository.java
 │   │   ├── model/
 │   │   │   └── Transaction.java
 │   │   └── DtpsApplication.java
 │   └── resources/
 │       └── application.properties
 ├── Dockerfile
 ├── k8s-manifest.yaml
 └── provision.sh
 
 🚀 **Deployment Steps**
Build Dockedocker build -t dtps:5.0 .
Load into Minikube
   minikube image load dtps:5.0
Deploy to Kubernetes
   kubectl apply -f k8s-manifest.yaml
Verify
   kubectl get pods
   kubectl get hpa

📊 **Horizontal Pod Autoscaling**
Minimum Pods: 3
Maximum Pods: 6
Target CPU Utilization: 70%
Metrics Server enabled

🔐 **Security Highlights**
IAM Role-based authentication
No plaintext credentials
Non-root container (Distroless)
Non-privileged Linux user
Log rotation configured
Principle of Least Privilege

🎯 **Key Features**
✔ Production-ready architecture
✔ Cloud-native deployment
✔ Auto-scaling
✔ Secure IAM authentication
✔ Circuit breaker implementation
✔ Virtual Thread concurrency
✔ Distroless container securityr Image
