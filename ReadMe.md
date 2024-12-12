# Ticket Management System - Cruiser

## Introduction
The Cruiser Ticket Management System is a simulation application designed to manage ticketing operations for customers and vendors efficiently. The system incorporates multi-threading to simulate real-world dynamics such as customer demand and vendor supply, ensuring thread-safe interactions between various entities. The application leverages Java, Spring Boot, and external libraries like Gson for JSON handling and Log4j for logging.

## Setup Instructions

### Prerequisites
Before running the application, ensure the following prerequisites are met:

- **Java Development Kit (JDK)**: Version 11 or later.
- **Apache Maven**: For building the project.
- **Node.js**: (Optional, if integrating with a frontend).
- **External Libraries**:
  - Gson (`com.google.code.gson`)
  - Log4j (`org.apache.logging.log4j`)

### Installation Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/CruiserTicketManagement.git
   ```

2. Navigate to the project directory:
   ```bash
   cd CruiserTicketManagement
   ```

3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   java -cp target/cruiser-1.0-SNAPSHOT.jar com.cruiser.Main
   ```

## Usage Instructions

### Configuration

#### Automatic Configuration
If a configuration file (`configuration.json`) is detected, the application will prompt you to load the saved settings. Choose `Y` to load or `N` to input new settings.

#### Manual Configuration
To configure manually, the system will request the following inputs:
1. **Initial Ticket Count**: The number of tickets available initially.
2. **Ticket Release Rate**: The rate at which tickets are added by vendors.
3. **Ticket Retrieval Rate**: The rate at which tickets are purchased by customers.
4. **Max Ticket Capacity**: The maximum capacity of the ticket pool.

The configuration will be saved in the `Configuration/configuration.json` file for future use.

### Starting the System
1. Run the application following the setup instructions.
2. Upon launch, the application starts:
   - **Customer threads** simulating ticket purchases.
   - **Vendor threads** simulating ticket releases.
3. Monitor system dynamics through logs printed on the console.

### Terminating the System
- To terminate the simulation, enter `Q` in the terminal. This will gracefully stop all threads and end the program.

## UI Controls
While this is primarily a CLI-based application, interactions include:
- **Input Prompts**: For initial configuration.
- **Live Console Updates**: Displays logs of ticket transactions, waiting rooms, and errors.
- **Termination Input**: Use `Q` to stop the program.

## Features
- Multi-threaded ticket buying and selling simulation.
- Thread-safe operations using `ReentrantLock` and `Condition`.
- Configuration persistence with JSON serialization (via Gson).
- Dynamic logging with Log4j.
- Fair allocation of resources among threads.

## Project Structure
- **`com.cruiser.Configuration`**: Handles configuration loading, saving, and management.
- **`com.cruiser.Person`**: Abstract class representing common functionality for `Customer` and `Vendor`.
- **`com.cruiser.Customer` and `com.cruiser.Vendor`**: Concrete implementations of `Person`.
- **`com.cruiser.Event`**: Central event manager coordinating the ticket pool and interactions.
- **`com.cruiser.TicketPool`**: Manages ticket availability and thread-safe access.
- **`com.cruiser.Main`**: Entry point of the application.

## Logs
All logs are stored in the `logs` directory, providing details of transactions and system status. Customize logging levels in the `log4j2.xml` configuration file.

## Troubleshooting
- **Issue: Application does not start.**
  - Ensure all prerequisites are installed.
  - Verify the `configuration.json` file exists and is correctly formatted.

- **Issue: Threads are not behaving as expected.**
  - Check for proper synchronization in the `TicketPool` class.
  - Ensure the configuration values are realistic.

## License
This project is licensed under the MIT License. See `LICENSE` for more details.

---
For further assistance, please contact [support@cruiser.com](mailto:support@cruiser.com).

