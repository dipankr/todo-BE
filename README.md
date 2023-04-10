# todo-BE

[![CI - TodoBE](https://github.com/dipankr/todo-BE/actions/workflows/maven.yml/badge.svg)](https://github.com/dipankr/todo-BE/actions/workflows/maven.yml)
[![API test](https://github.com/dipankr/todo-BE/actions/workflows/postmanCLI.yml/badge.svg)](https://github.com/dipankr/todo-BE/actions/workflows/postmanCLI.yml)

Backed APIs for todo list </br>
Fronted project : [Todo-FE](https://github.com/dipankr/todo-FE)

### Endpoints

---

**test endpoints**
```json
  {
    "test endpoints": [
      {
        "GET": "/",
        "res": {
          "response": {
            "message":"Todo API is up and running."
          }
        }
      },
      {
        "GET": "/test",
        "res": {
          "response": {
            "message": "/test is accessible.."
          }
        }
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
        "res": {
          "response": {
            "data": [
              {
                "id": "1001",
                "title": "title 1",
                "description": "description 1",
                "completed": false
              },
              {
                "id": "1002",
                "title": "title postman 1",
                "description": "description postman 1",
                "completed": false
              }
            ],
            "message": "success"
          }
        }
      },
      {
        "POST": "/api/todolist",
        "res" :
        [
          {
            "success" : {
              "response": {
                "message": "added todo item to the list"
              }
            }
          },
          {
            "failed" : {
              "response": {
                "error": true,
                "message": "title can not be empty"
              }
            }
          }
        ]
      }
    ]
  }
```


