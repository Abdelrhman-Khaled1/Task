# Task Management System
A simple task management system that allows users to manage and track their tasks efficiently, with additional admin controls.

 ## Features
### Core Features
* ### User Task Management

  * Each user can create, update, and delete multiple tasks.
  * Each task is assigned to one user and cannot be shared with others.
  * Tasks have attributes like title, description, status, priority, and due date.

* ### Task Filtering
  #### The system allows filtering tasks based on the following criteria:
    * Title: Search tasks where the title contains a specific substring.
    * Description: Search tasks where the description contains a specific substring.
    * Status: Filter tasks by specific status (e.g., TODO, IN_PROGRESS, DONE).
    * Priority: Filter tasks by priority (e.g., HIGH, MEDIUM, LOW).
    * Due Date Range: Filter tasks by a specified due date range (e.g., tasks due between two dates).<br/><br/>
 The filtering is dynamic, meaning if a field is not provided, it will be ignored in the query, and only the provided criteria will be used.

* ### Admin Task Access with Pagination
  * Admin Access: Admin users have the ability to view tasks for all users in the system.
  * Pagination: To manage large sets of tasks efficiently, the GET /api/admin/tasks endpoint supports pagination. Admins can retrieve tasks in pages, with configurable page size and sorting.
* Example API Usage:
  * Request: `` GET /api/admin/tasks?page=1&size=10&sort=dueDate,desc ``
  * Response: `` Returns a paginated list of tasks (10 tasks per page, sorted by dueDate in descending order). `` <br/><br/>
This allows admins to efficiently manage and view large datasets of tasks.

* ###  Task History Tracking

  * Every change to a task is recorded in a Task History table for tracking purposes.
  * Task history records include task status changes, updates to due dates, and other modifications.
 
* ### Notification System

  * A record is saved in the Notification table for every email notification sent.
  *  Daily at 7 am, the system checks tasks due within 24 hours and sends a reminder email to the task owner.
* ### Admin Controls

  * Admins can access all tasks created by any user.
  * Admins can filter all tasks 

* ### System Limitations

* Each task can be assigned to only one user.

* If a user has thousands of tasks due in the same 24-hour period, the system may send a high volume of emails, which could overwhelm the user or the email server.

* ### Potential Improvements

* Enable tasks to be assigned to multiple users for collaborative work.
* Super Admin Role

  * Introduce a super admin role who can create admin accounts, replacing the public endpoint for admin creation.

* Ensure no duplicate emails are allowed at the user creation stage.
* Task History Validation and DTOs

  * Add validation to track who made specific changes to task history.
  * Use DTOs (Data Transfer Objects) instead of directly exposing database entities in responses.
  
* Bulk Email Optimization

  * For users with a large number of tasks ending within 24 hours, explore batch notifications or limit the number of active tasks per user to prevent email overload.
  
## Setup Instructions
## Prerequisites
* Java 17
* Spring Boot 3.x
* Docker (optional, for running dependencies such as RabbitMQ, MongoDB, PostgreSQL in containers)

### Running the Application
Clone the repository. <br/>
Install dependencies: mvn clean install. <br/>
Configure environment variables for email notifications and database settings. <br/>
Start the application: mvn spring-boot:run. <br/>

### Running Tests
Unit Tests: Run with mvn test.

### API Endpoints
  ### Admin Endpoints
Get All Tasks: ``` http://localhost:8080/api/v1/tasks/admin/all?page=0&size=10 ``` <br/>
Admin Search: ``` http://localhost:8080/api/v1/tasks/admin/search ```

## Database Schema Overview
### Tables
Users: Stores user data and roles. <br/>
Tasks: Holds all tasks with title, description, status, priority, due date, and user ownership. <br/>
Task History: Logs all task changes with a timestamp and the status. <br/>
Notifications: Tracks all sent notifications for record-keeping. <br/>

# Postman for testing 

[Task Management System.postman_collection.json](https://github.com/user-attachments/files/17745038/Task.Management.System.postman_collection.json)
