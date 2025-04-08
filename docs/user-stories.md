# User Stories
This document contains the uses stories for the OOP Sample.

## US01: Register a New Customer
**As a** CRM user,
**I want to** register a new customer with their contact information,
**So that** I can track and manage customer details effectively.

### Acceptance Criteria
- **Scenario: Successful Customer Registration**
- - **Given** a valid customer name, email, and address details are provided,
- - **When** the system creates a new customer,
- - **Then** a customer is registered with a unique ID, and the provided details are stored.

- **Scenario: Invalid Customer Information**
- - **Given** an empty name or email is provided,
- - **When** the system attempts to register the customer,
- - **Then** and exception is raised indicating the required fields and validation errors found.

