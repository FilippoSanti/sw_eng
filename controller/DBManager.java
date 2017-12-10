package controller;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;


public class DBManager {

    /* Connect to the DB */
    public static MongoDatabase dbConnect() {

        MongoClientURI uri = new MongoClientURI("mongodb://valendrino:sonoscemo@80.183.230.129/?authSource=unnamedb");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase("unnamedb");

        return db;

    }

    public static void main(String[] args) {

        MongoDatabase db = dbConnect();
        for (String name : db.listCollectionNames()) {
            System.out.println(name);

        }
    }
}