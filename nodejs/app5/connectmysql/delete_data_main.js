const mysql = require('mysql')

const conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'password',
    database: 'employee_db'
});

conn.connect((err) => {
    if (err) {
        console.log('connection establish is failed.' + err.stack());
        return;
    }
    console.log('connection established.');
});

conn.query('DELETE FROM users WHERE id=?',
  [5], (error, results) => {
    console.log(results);
  }
);

console.log('deleting data is success.');