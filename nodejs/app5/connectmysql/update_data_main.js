const mysql = require('mysql')

const conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'password',
    database: 'employee_db'
});

conn.connect((err) => {
    if (err) {
        console.log('connection failed.' + err.stack());
        return;
    }
    console.log('connection success.');
})

conn.query('UPDATE users SET email=? where id=?',
    ['sample@gmail.com', 5], (error, results) => {
        console.log(results);
    }
);

console.log('updating data complited.');