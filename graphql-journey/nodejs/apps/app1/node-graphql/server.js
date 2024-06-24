const express = require('express');
const { graphqlHTTP } = require('express-graphql');
const expressGraphQL = require('express-graphql').graphqlHTTP;
const { buildSchema } = require('graphql');

const appServer = express();
// GraphQL schema
var schema = buildSchema(`
 type Query {
 message: String
 }
`);
// Root resolver
var root = {
 message: () => 'Hello World!'
};
// Create an express server and a GraphQL endpoint
appServer.use('/graphql', expressGraphQL({
 schema: schema,
 rootValue: root,
 graphiql: true
}));
appServer.listen(4000, () => console.log('Express GraphQL Server Now Running On localhost:4000/graphql'));