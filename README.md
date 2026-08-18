# AppZZ — Helpdesk Ticket System

AppZZ is a helpdesk ticket management system with a Flutter mobile app, Angular web portals, a Spring Boot backend, and PostgreSQL persistence.

## Product Scope

### Mobile App — Flutter

The mobile app is designed for end users who need to report and follow up on workplace issues.

Core features:

- Login
- Create ticket
- Capture issue photos
- Upload images
- View tickets
- Comment on tickets
- Receive notifications

### Web App — Angular

The Angular web app supports end users who prefer to work from a browser.

Core features:

- Login
- Create ticket
- View tickets
- Comment on tickets
- Upload images
- Track ticket status

### IT/Admin Web — Angular

IT and admin users use the web interface to manage ticket operations and system data.

Core features:

- Dashboard
- View all tickets
- Categorize tickets
- Assign tickets to IT staff
- Change ticket status
- Comment on tickets
- Manage users
- Manage departments
- View ticket statistics
- View ticket handling history

## Backend — Spring Boot

Spring Boot acts as the central API and business logic layer for the mobile app, user web app, and IT/admin web app.

Main modules:

- Authentication
- User Management
- Department Management
- Ticket Management
- Comment
- Attachment
- Notification
- Dashboard / Report

## Database — PostgreSQL

PostgreSQL stores the core operational data for the system.

Primary entities:

- User
- Department
- Ticket
- TicketComment
- TicketAttachment
- TicketHistory
- Notification

## High-Level Architecture

```text
Flutter Mobile App  ─┐
Angular Web App    ─┼─> Spring Boot API ─> PostgreSQL
Angular IT/Admin   ─┘
```

## Suggested Repository Structure

```text
appzz/
├── backend/          # Spring Boot API (implemented)
├── mobile/           # Flutter mobile application
├── web/              # Angular user web application (implemented)
├── admin/            # Angular IT/Admin web application (implemented)
└── docs/             # Architecture and product documentation
```

## Suggested Backend Domain Model

```text
User
├── id
├── fullName
├── email
├── passwordHash
├── role
├── departmentId
└── status

Department
├── id
├── name
└── description

Ticket
├── id
├── title
├── description
├── status
├── priority
├── category
├── requesterId
├── assigneeId
├── departmentId
├── createdAt
└── updatedAt

TicketComment
├── id
├── ticketId
├── authorId
├── content
└── createdAt

TicketAttachment
├── id
├── ticketId
├── uploadedBy
├── fileName
├── fileUrl
├── contentType
└── createdAt

TicketHistory
├── id
├── ticketId
├── changedBy
├── action
├── oldValue
├── newValue
└── createdAt

Notification
├── id
├── userId
├── title
├── message
├── readAt
└── createdAt
```

## Recommended Ticket Status Flow

```text
NEW -> ASSIGNED -> IN_PROGRESS -> RESOLVED -> CLOSED
              └───────────────> CANCELLED
```

## Recommended Roles

- `USER`: Creates tickets, uploads attachments, comments, and tracks status.
- `IT_STAFF`: Receives assigned tickets, updates status, comments, and resolves issues.
- `ADMIN`: Manages users, departments, reports, assignments, and system-level settings.

## Backend Quick Start

Run the Spring Boot API from the `backend/` directory:

```bash
cd backend
mvn spring-boot:run
```

The API starts on port `8080` by default and exposes initial endpoints for users, departments, tickets, comments, attachments, assignment, status changes, and dashboard summary.

## Frontend Quick Start

Install dependencies and run the user web portal:

```bash
cd web
npm install
npm start
```

Install dependencies and run the IT/Admin portal:

```bash
cd admin
npm install
npm start
```

The user portal defaults to port `4200`; the admin portal defaults to port `4300`. Both call the Spring Boot API at `http://localhost:8080/api`.
