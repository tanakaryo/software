curl -X POST -H "Content-Type: application/json" -d '{"departmentName":"BBUT02","departmentAddress":"TEXAS","departmentCode":"DPT002"}' http://localhost:8081/api/v1/departments
curl -X GET http://localhost:8081/api/v1/departments/1
curl -X GET http://localhost:8081/api/v1/departments/2

curl -X POST -H "Content-Type: application/json" -d '{"firstName":"Kevin","lastName":"Maccoy","email":"kevin.maccoy@gmail.com", "departmentId":"1"}' http://localhost:8082/api/v1/users
curl -X POST -H "Content-Type: application/json" -d '{"firstName":"Mcdonald","lastName":"Happyset","email":"mac.happy@gmail.com", "departmentId":"1"}' http://localhost:8082/api/v1/users
curl -X GET http://localhost:8082/api/v1/users/1
curl -X GET http://localhost:8082/api/v1/users/2
