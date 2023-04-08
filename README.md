# todo-BE

[![CI with Gradle](https://github.com/dipankr/todo-BE/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/dipankr/todo-BE/actions/workflows/gradle.yml)

Backed APIs for todo list 

### **[getTodoList](src/main/java/com/todolist/todo/controller/TodoListController.java)** response:

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
### **[addTodo](src/main/java/com/todolist/todo/controller/TodoListController.java)** request:

---

```json
{
    "title": "title postman 1",
    "description": "description postman 1"
}
```

