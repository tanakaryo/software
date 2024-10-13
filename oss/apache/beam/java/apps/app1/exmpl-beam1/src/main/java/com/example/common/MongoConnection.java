package com.example.common;

import static java.util.Collections.singletonList;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.connection.ClusterSettings;

public class MongoConnection {
    public static void main(String[] args) throws Exception {
        MongoCredential credential = MongoCredential.createCredential("root", "admin", "pass".toCharArray());
        Block<ClusterSettings.Builder> localhost = builder -> builder.hosts(singletonList(new ServerAddress("localhost", 27017)));
        MongoClientSettings settings = MongoClientSettings.builder()
        .applyToClusterSettings(localhost)
        .credential(credential)
        .build();

        try( MongoClient client = MongoClients.create(settings)) {
        MongoCollection<Document> col = client.getDatabase("test").getCollection("users");
        System.out.println(col.countDocuments());
        FindIterable<Document> iterable = col.find();
        iterable.forEach((doc) -> System.out.println(doc));
        }
    }
}
