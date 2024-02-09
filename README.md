## Kafka Made Simple
A practical real-world example that uses kafka consumer and producer to produce messages then consume them and save them to a ```MongoDB```

### Table Of Content
- [Prerequisites](#Prerequisites)
- [Build & Run instructions](#Build-and-Run-Instructions)
- [Endpoints](#Endpoints)
- [Componenets](#Components)

### Prerequisites
1- Java >= 17
```shell
#verify jdk version
java --version
```

2- Maven
```shell
#verify maven installed
mvn --version
```

3- Kafka (for simplicity we used docker containers to set up our kafka environment)
```shell
#verify kafka container running
#should have 4 containers 1-kafka 2-mongo 3-our springboot app 4-zookeeper
docker-compose up -d
docker-compose ps
```

4- MongoDB
```shell
#verify db is connected
mongo --eval "db.runCommand({ connectionStatus: 1 })"
```

### Build and Run Instructions
We are using ```docker-compose``` to run our application and all the images we depend on

- Compile & Package
```shell
mvn clean package
```

- Build our image
```shell
docker build -t kafka-made-simple .
```

- Run our application
```shell
docker-compose up
```

- Restart docker compose services (if needed!)
```shell
docker-compose down
docker-compose up --build
```

### Endpoints
Navigate to [Swagger API documentation](http://localhost:8080/swagger-ui/index.html) to view swagger documentation of `messages` endpoints.

| Endpoint  | onSuccess | onFailure |
|-----------|-----------|-----------|
| /messages | 200       | 404       |
| /send     | 201       | 5xx       |

### Components

Our system is designed to showcase the integration of a Spring Boot application with Apache Kafka for messaging and MongoDB for data persistence. 
The application produces messages to a Kafka topic and consumes them from the same topic, with the consumed messages being persisted in MongoDB. 
Docker is used to containerize the application and its dependencies, providing a consistent development and deployment environment.

1. Spring Boot Application (kafka-made-simple)
Serves as the core of the system, handling HTTP requests, producing messages to Kafka, consuming messages from Kafka, and persisting consumed messages to MongoDB.

- Kafka Producer: Sends messages to a specified Kafka topic in response to HTTP requests. 
- Kafka Consumer: Listens for messages on the Kafka topic and processes them. 
- MongoDB Integration: Persists consumed messages into a MongoDB database for later retrieval.

2. Apache Kafka
Acts as the messaging backbone, facilitating asynchronous communication between different parts of the application.

- Scalability: Can handle high-throughput message streams.
- Durability: Stores messages on disk, ensuring they're not lost.
- Fault Tolerance: Replicates data across multiple nodes to prevent data loss.
- Broker: Handles message storage and retrieval.
- Zookeeper: Manages cluster metadata and broker coordination

4. MongoDB
Serves as the system's database, storing processed messages for future analysis or retrieval.

- Schema-less: Allows storing data in a flexible, JSON-like format. 
- Scalability: Supports sharding for horizontal scalability. 
- Queryability: Offers powerful querying capabilities for JSON data.

5. Docker
Containerizes the application and its dependencies, ensuring consistency across development, testing, and production environments.

- Dockerfiles: Define the setup for the Spring Boot application, Kafka, Zookeeper, and MongoDB containers.
- Docker Compose: Orchestrates the deployment of multiple containers, managing their lifecycle and interconnections.

6. Docker Compose Configuration
Automates the deployment of the multi-container Docker application.

- Service Definition: Specifies individual services, such as the Spring Boot application, Kafka, Zookeeper, and MongoDB.
- Networking: Automatically creates a network for inter-service communication, using service names as hostnames for discovery.
- Volume Management: (Not explicitly covered but implied for Kafka and MongoDB) Manages data persistence beyond the lifecycle of containers.


## Contributing

Contributions are welcome! If you have improvements or additions, please submit a pull request or open an issue.

-------------------------------------------------------------
Happy coding!