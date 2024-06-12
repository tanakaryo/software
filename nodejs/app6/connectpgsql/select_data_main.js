const { Client } = require('pg')

const client = new Client({
    user: 'postgres',
    host: 'localhost',
    database: 'employee_db',
    password: 'postgres',
    port: 5432
})

client.connect();

const query = {
    text: 'SELECT * FROM users'
};

client.query(query, (err, res) => {
    if (err) {
        console.log(err.stack());
    } else {
        for (const row of res.rows) {
            console.log(row);
        }
    }
})