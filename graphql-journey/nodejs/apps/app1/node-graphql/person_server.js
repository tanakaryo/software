const express = require('express');
const expGql = require('express-graphql').graphqlHTTP;
const { buildSchema } = require('graphql');

const appServer = express();

var schema = buildSchema(`
    type Query {
    person(name: String!): Person
    },
    type Person {
    name: String,
    age: Int,
    country: String
    }
    `);

var personData = [
    {
        name: 'David Schott',
        age: 14,
        country: "United States"
    },
    {
        name: 'Satoshi Yoshida',
        age: 15,
        country: "Japan"
    },
    {
        name: 'Jackson Lee',
        age: 16,
        country: "England"
    }
]

var getPerson = function(args) {
    var name = args.name;
    return personData.filter(person => {
        return person.name == name;
    })[0];
};

var root = {
  person: getPerson   
};

appServer.use('/graphql', expGql({
    schema: schema,
    rootValue: root,
    graphiql: true
}));

appServer.listen(4000, () => console.log('GqlServer is running...'));