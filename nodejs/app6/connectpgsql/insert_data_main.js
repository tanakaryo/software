const { Client } = require('pg')

const client = new Client({
    user: 'postgres',
    host: 'localhost',
    database: 'employee_db',
    password: 'postgres',
    port: 5432
});

client.connect();

const query = {
    text: 'INSERT INTO users(id, email, first_name, last_name) VALUES($1, $2, $3, $4)',
    values: [3, 'fredrickson@gmail.com', 'fred', 'hopkins'],
};

client.query(query, (err, res) => {
    if (err) {
        console.log(err.stack());
        return;
    }
    console.log("insert success.");
});