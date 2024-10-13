package com.example.common;

import java.util.ArrayList;
import static java.util.Collections.singletonList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.connection.ClusterSettings;

public class MongoInsertMany {
    public static void main(String[] args) throws Exception {
                MongoCredential credential = MongoCredential.createCredential("root", "admin", "pass".toCharArray());
        Block<ClusterSettings.Builder> localhost = builder -> builder.hosts(singletonList(new ServerAddress("localhost", 27017)));
        MongoClientSettings settings = MongoClientSettings.builder()
        .applyToClusterSettings(localhost)
        .credential(credential)
        .build();

        try( MongoClient client = MongoClients.create(settings)) {
        MongoCollection<Document> col = client.getDatabase("test").getCollection("users");

        List<Document> userList = new ArrayList<>();
        Document user1 = new Document();
        user1.append("username", "hashimoto");
        Document user2 = new Document();
        user2.append("username", "hayashi");

        userList.add(user1);
        userList.add(user2);

        col.insertMany(userList);
        }
    }
}
