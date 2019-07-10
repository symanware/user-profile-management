# User profile management REST API

<h3>About</h3>
This is a demo of User profile management using JSON Web Token (JWT) with Spring Security and Spring Boot 2

<h3>Implementation of User profile management REST API service using</h3> 

<ul>
<li>SpringBoot 2.1.6</li>
<li>JWT 0.9.1</li>
<li>Java 1.8</li>
<li>H2Database</li>
<li>Apache Maven 3.6.1</li>
<li>lombok 1.18.8</li>
<li>Mockito</li>
</ul>

<h3>Steps to Setup</h3>

<ol>

<li><h5> Clone the repository</h5>
    git clone https://github.com/symanware/UserManagement.git
</li>
<li><h5>Run the app using maven</h5>
    mvn spring-boot:run
</li>    
<li><h5>Swagger File location</h5>
    src\main\resources
</li>
</ol>

<h3>How to run </h3>
The application can be accessed at <strong> http://localhost:8080 </strong></br>

<h3>Here is what this application demonstrates:</h3>

This API provides REST endpoints to manage the user profiles based on Role

There are two user accounts present to demonstrate the different levels of access to the endpoints in the API and the different authorization exceptions<br>

<code>Admin - admin:admin<br>
User - john.doe
</code>

There are three endpoints that are reasonable for the demo:

<code>/api/v1/token - authentication endpoint to retrieve JWT with unrestricted access<br>
/api/v1/admin/users - endpoint that is restricted to authorized users with the role 'ADMIN' (a valid JWT token must be present in the request header)<br>
/api/v1/users - endpoint that is restricted to authorized users with the role 'USER' and 'ADMIN' (a valid JWT token must be present in the request header)
</code>

<ul>
<li>
   To generate token<br>
   <code>
   POST : /api/v1/token/ <br>
   Accept: application/json <br>
   Content-Type: application/json <br>
   Request Body :
      {
         
          "username": "Tim.admin",
          "password": "jwtpass",
       }
   http://localhost:8080/api/v1/token/</code><br><br>
   
</li>

<li>
   Get User information for role "USER" and "ADMIN"<br>
   <code>
   GET : /api/v1/users/ <br>
   Headers:<br>
   Key:Authorization<br>
   Value:Bearer JWT token<br>
   http://localhost:8080/api/v1/users/</code><br><br>
</li>
<li>
   Delete User for role "USER" and "ADMIN" <br>
    <code>
    DELETE : /api/v1/users/{id} <br>
    Headers:<br>
    Key:Authorization<br>
    Value:Bearer JWT token<br>
   http://localhost:8080/api/v1/users/{id}</code><br><br>
  
</li>
<li>
   Get all User information for role "ADMIN"<br>
   <code>
   GET :/api/v1/admin/users/ <br>
   Headers:<br>
   Key:Authorization<br>
   Value:Bearer JWT token<br>
   http://localhost:8080/api/v1/admin/users/</code><br><br>
</li>

<li>
   Get User information for given id - autherized to role "ADMIN"<br>
   <code>
   GET :/api/v1/admin/users/ <br>
   Headers:<br>
   Key:Authorization<br>
   Value:Bearer JWT token<br>
   http://localhost:8080/api/v1/admin/users/</code><br><br>
</li>

<li>
   Create User - autherized to role "ADMIN"<br>
   <code>
   POST :/api/v1/admin/users/ <br>
   Headers:<br>
   Key:Authorization<br>
   Value:Bearer JWT token<br>
   Request Body:
      {
         
          "username": "Tim.admin",
          "password": "$2a$10$qtH0F1m488673KwgAfFXEOWxsoZSeHqqlB/8BTt3a6gsI5c2mdlfe",
          "firstName": "Tim",
          "lastName": "Deo",
          "roles": [
              {
                  "id": 1,
                  "roleType": "USER",
                  "description": "User - Has no admin rights"
              },
              {
                  "id": 2,
                  "roleType": "ADMIN",
                  "description": "Admin - Has permission to perform admin tasks"
              }
          ],
          "addresses": [
              {
                  
                 "type": "HOME",
                 "line1": "555",
                 "line2": "KANGAROO STREET",
                 "city": "MELBOURNE",
                 "state": "VIC",
                 "zipcode": "4444",
                 "emails": "tim.deo@gmail.com"
              }
          ]
      }

   http://localhost:8080/api/v1/admin/users/</code><br><br>
</li>

<li>
   Update User information for given id - autherized to role "ADMIN"<br>
   <code>
   PUT :/api/v1/admin/users/{id} <br>
   Headers:<br>
   Key:Authorization<br>
   Value:Bearer JWT token<br>
   Request body:
   {
      
       "username": "Tim.admin",
       "password": "$2a$10$qtH0F1m488673KwgAfFXEOWxsoZSeHqqlB/8BTt3a6gsI5c2mdlfe",
       "firstName": "Tim",
       "lastName": "Deo",
       "roles": [
           {
               "id": 1,
               "roleType": "USER",
               "description": "User - Has no admin rights"
           },
           {
               "id": 2,
               "roleType": "ADMIN",
               "description": "Admin - Has permission to perform admin tasks"
           }
       ],
       "addresses": [
           {
               
               "type": "HOME",
               "line1": "777",
               "line2": "COORIGIL STREET",
               "city": "MELBOURNE",
               "state": "VIC",
               "zipcode": "7777",
               "emails": "tim.deo@gmail.com"
           }
       ]
   }
   http://localhost:8080/api/v1/admin/users/{id}</code><br><br>
</li>

<li>
   Assign role to User - autherized to role "ADMIN"<br>
   <code>
   PATCH :/api/v1/admin/users/{id} <br>
   Headers:<br>
   Key:Authorization<br>
   Value:Bearer JWT token<br>
   Request Body:
   {
            
          "roles": [
                     {
                        "id": 1,
                        "roleType": "USER",
                        "description": "User - Has no admin rights"
                     },
                     {
                         "id": 2,
                         "roleType": "ADMIN",
                         "description": "Admin - Has permission to perform admin tasks"
                     }
                   ],
        }
   http://localhost:8080/api/v1/admin/users/{id}</code><br><br>
</li>

<li>
   Delete User for given id - autherized to role "ADMIN"<br>
   <code>
   DELETE :/api/v1/admin/users/{id} <br>
   Headers:<br>
   Key:Authorization<br>
   Value:Bearer JWT token<br>
   http://localhost:8080/api/v1/admin/users/{id}</code><br><br>
</li>
</ul>

<h3>Generating password hash for new users</h3>
I'm using bcrypt to encode paswords. Your can generate your hashes with this simple tool: [Bcrypt Generator](https://bcrypt-generator.com/)

<h5>It took one week to complete this assignment</h5>

<h3>Challenges I have faced while developing this assignment</h3>
<ol>
<li>
Initially, I had created single controller to handle both user and admin requests.
But then created different controllers for role based operations.
AdminController would handle all Admin related operations and 
UserController would handle all User related operations.
</li>
</ol>