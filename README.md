# todo-be
#### Java Springboot RESTful Service to manage todos


## Endpoints
---

### Create User Account

**Sign up**
```
POST /signup
{
	"name": "My Super User",
	"username": "mysuser",
	"password": "password"
}
```
---
**_All the below services need to be authenticated with above created user credentials._**
**_Use Basic Authentication and provide username & password in Postman_**

### CRUD Todo

**Test Service**
```
GET /hello => "hello"
```

**Create Todo**
```
POST /todo
    {   
        "description": "Dummy Task",
        "priority": "High",
        "status": "Pending",
        "completionDate": "17-Apr-2020"
    }
 ```

---
**Update Todo**
```
PUT /todo
    {
        "description": "Real Task",
        "priority": "High",
        "status": "Completed",
        "completionDate": "16-Apr-2020"
    }
 ```
 ---
 
 **Delete Todo**
 ```
 DELETE /todo/{id}

 ```
 
 ---
 
 **Query Todo**
 
`GET /todo/{id} => Optional<Todo>`

`GET /todo?priority={high|medium|low}&status={pending|completed} => List<Todo>`
