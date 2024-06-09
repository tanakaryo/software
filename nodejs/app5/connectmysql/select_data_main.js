const mysql = require('mysql')

const conn = mysql.createConnection({
    host:'localhost',
    user:'root',
    password:'password',
    database:'employee_db'
})

conn.connect((err) => {
    if (err) {
        console.log('error connecting:' + err.stack);
        return;
    }
    console.log('connecting success');
});

conn.query('SELECT * FROM users', (error, resultSet) => {
    console.log(resultSet);
})

