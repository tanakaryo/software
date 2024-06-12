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
    text: 'UPDATE users SET email=$1 WHERE id=$2',
    values: ['babapapa@gmail.com', 3],
};

client.query(query, (err, res) => {

    if (err) {
        console.log(err.stack());
        return;
    }
    console.log('update success.');
});