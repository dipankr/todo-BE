# todo-BE

[![CI with Gradle](https://github.com/dipankr/todo-BE/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/dipankr/todo-BE/actions/workflows/gradle.yml)

Backed APIs for todo list 

### Endpoints

---

**test endpoints**
```json
  {
    "test endpoints": [
      {
        "GET": "/",
        "response": "Todo API is running."
      },
      {
        "GET": "/test",
        "response": "Todo API is running.."
      }
    ]
  }
```

**todo endpoints**

```json
  {
    "todolist endpoints": [
      {
        "GET": "/api/todolist",
        "response": [
          {
            "id": "1001",
            "title": "title 1",
            "description": "description 1",
            "completed": false
          },
          {
            "id": "1002",
            "title": "title 2",
            "description": "description 2",
            "completed": false
          }
        ]
      },
      {
        "POST": "/api/todolist",
        "response" :
        [
          {
            "success" : "added todo item to the list"
          },
          {
            "failed" : "{error: error parsing request body}"
          }
        ]
      }
    ]
  }
```

### **[getTodoList](src/main/java/com/todolist/todo/controller/TodoListController.java)** response

---

```json
[{
    "id": "1001",
    "title": "title postman 1",
    "description": "description postman 1",
    "completed": false
    },
    {
    "id": "1002",
    "title": "title postman 2",
    "completed": false
}]
```
### **[addTodo](src/main/java/com/todolist/todo/controller/TodoListController.java)** request

---

```json
{
    "title": "title postman 1",
    "description": "description postman 1"
}
```

