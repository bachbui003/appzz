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
├── backend/          # Spring Boot API
├── mobile/           # Flutter mobile application
├── web/              # Angular user web application
├── admin/            # Angular IT/Admin web application
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
