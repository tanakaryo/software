const mysql = require('mysql')

const conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'password',
    database: 'employee_db'
});

conn.connect((err) => {
    if (err) {
        console.log('connection failed. ' + err.stack);
        return;
    }
    console.log('connection success.');
});

conn.query('INSERT INTO users(id, department_id, email, first_name, last_name) VALUES (?,?,?,?,?)'
    , [5, '1', 'example@gmail.com', 'J.M', 'Weston'], (error, results) => {
    console.log(results);
})

