package controller;

import java.util.Arrays;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;



public class DBManager {

    /* Connect to the DB */
    public static MongoDatabase dbConnect () {

        MongoCredential credential = MongoCredential.createCredential("mikesh", "tests", "test".toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017), Arrays.asList(credential));
        MongoDatabase db = mongoClient.getDatabase( "tests" );

        return db;
    }

    public static void main(String[] args) {

        MongoDatabase db = dbConnect();
        for (String name : db.listCollectionNames()) {
            System.out.println(name);

        }
    }
}
