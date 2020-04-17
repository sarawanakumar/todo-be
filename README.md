# todo-be
#### Java Springboot RESTful Service to manage todos


## Endpoints


**Test Service**
GET /hello => "hello"

**Create Todo**
```POST /todo

{   "description": "Dummy Task",
        "priority": "High",
        "status": "Pending",
        "completionDate": "17-Apr-2020"
    }
 ```

---
**Update Todo**
```PUT /todo

{   "description": "Real Task",
        "priority": "High",
        "status": "Completed",
        "completionDate": "16-Apr-2020"
    }
 ```
 ---
 
 **Delete Todo**
 ```DELETE /todo/{id}
 ```
 
 ---
 
 **Query Todo**
 
`GET /todo/{id} => Optional<Todo>`

`GET /todo?user_id={id}&priority={high|medium|low}&status={pending|completed} => List<Todo>`
