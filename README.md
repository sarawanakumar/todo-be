# todo-be
##Springboot RESTful Service to manage todos


## Endpoints

### GET /hello => "hello"

### GET /todo => `List<Todo>`

### GET /todo/{id} => `Optional<Todo>`

### GET /todo/filter?user_id={id}&priority={high|medium|low}&status={pending|completed} => `List<Todo>`
