curl -X POST -H "Content-Type: application/json" -d '{"classGrade":"Elementary","classRank":"5th","classTeacher":"David Jackson","classSubject":"mathmatics"}' http://localhost:8081/api/v1/school-classes
curl -X POST -H "Content-Type: application/json" -d '{"classGrade":"Elementary","classRank":"3th","classTeacher":"Mary Atkinson","classSubject":"phylosophy"}' http://localhost:8081/api/v1/school-classes
curl -X GET http://localhost:8081/api/v1/school-classes/1

curl -X POST -H "Content-Type: application/json" -d '{"firstName":"Michael","lastName":"Chevoler","classId":"1", "studentGrade":"Good"}' http://localhost:8082/api/v1/students
curl -X POST -H "Content-Type: application/json" -d '{"firstName":"Sara","lastName":"Samantha","classId":"2", "studentGrade":"Good Enough"}' http://localhost:8082/api/v1/students
curl -X GET http://localhost:8082/api/v1/students/1
curl -X GET http://localhost:8082/api/v1/students/2
