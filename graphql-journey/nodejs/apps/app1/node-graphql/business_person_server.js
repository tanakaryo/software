const express = require('express');
const express_gql = require('express-graphql').graphqlHTTP;
const { buildSchema } = require('graphql');

const appServer = express();

var schema = buildSchema(`
    type Query {
    person(name: String!): BusinessPerson
    department(dept: String!): [BusinessPerson]
    },
    type BusinessPerson {
    name: String,
    age: Int,
    country: String,
    dept: String
    }
    `);

    var businessPersonData = [
        {
            name: 'David Schott',
            age: 14,
            country: "United States",
            dept: "dept1"
        },
        {
            name: 'Satoshi Yoshida',
            age: 15,
            country: "Japan",
            dept: "dept1"
        },
        {
            name: 'Jackson Lee',
            age: 16,
            country: "England",
            dept: "dept2"
        }
    ]

    var getPerson = function(args) {
        var name = args.name;
        return businessPersonData.filter(person => {
            return person.name == name;
        })[0];
    }

    var getDepartment = function(args) {
        if (args.dept) {
            var dept = args.dept;
            return businessPersonData.filter(person => {
                return person.dept == dept;
            });
        } else {
            return businessPersonData;
        }
    }
    
    var root = {
      person: getPerson,
      department : getDepartment   
    };
    
    appServer.use('/graphql', express_gql({
        schema: schema,
        rootValue: root,
        graphiql: true
    }));
    
    appServer.listen(4000, () => console.log('GqlServer is running...'));