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
    text: 'DELETE FROM users WHERE id=$1',
    values: [3],
};

client.query(query, (err, res) => {

    if (err) {
        console.log(err.stack());
        return;
    }
    console.log('delete success.');
    console.log(res);
});