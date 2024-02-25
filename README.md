# UberPopugSystem


![](domains.png)


### Auth Domain

| Actor | Command        | Data    | Event          |
|-------|----------------|---------|----------------|
| User  | Create Account | Account | AccountCreated |
| User  | Update Account | Account | AccountUpdated |
| User  | Delete Account | Account | AccountDeleted |

### Task Manager Domain

| Actor                | Command           | Data | Event         |
|----------------------|-------------------|------|---------------|
| User                 | Create Task       | Task | TaskCreated   |
| TaskCreated event    | Assign task       | Task | TaskAssigned  |
| User                 | Complete Task     | Task | TaskCompleted |
| User (manager role)  | Assign All Tasks  | Task | TaskAssigned  |

### Accounting & Analytics Domain

| Actor                   | Command                    | Data              | Event             |
|-------------------------|----------------------------|-------------------|-------------------|
| TaskAssigned event      | Debit account              | Balance Operation | AccountDebited    |
| TaskCompleted event     | Credit account             | Balance Operation | AccountCredited   |
| Job                     | Calculate payment per day  | Balance Operation | PaymentCalculated |
| PaymentCalculated event | Send notification          | Account           | NotificationSent  |
| NotificationSent event  | Balance reset              | Balance           |                   |


### Event List

| Event Name        | Event type     | Producer            | Consumers                        |
|-------------------|----------------|---------------------|----------------------------------|
| AccountCreated    | CUD Event      | Auth Service        | Accounting Service, Task Service |
| AccountUpdated    | CUD Event      | Auth Service        | Accounting Service, Task Service |
| AccountDeleted    | CUD Event      | Auth Service        | Accounting Service, Task Service |
| TaskCreated       | Business Event | Task Service        | Task Service                     |
| TaskAssigned      | Business Event | Task Service        | Accounting Service               |
| TaskCompleted     | Business Event | Task Service        | Accounting Service               |
| AccountDebited    | Business Event | Accounting Service  |                                  |
| AccountCredited   | Business Event | Accounting Service  |                                  |
| PaymentCalculated | Business Event | Accounting Service  | Notification Service             |
