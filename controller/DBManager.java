package controller;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Robot;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DBManager {

    public static MongoDatabase dbConnect() {

        MongoClientURI uri = new MongoClientURI("mongodb://admin:testadmin123@95.247.218.128/?authSource=unnamedb");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase("unnamedb");

        return db;

    }

    public static void addObjectToDB(MongoDatabase database, ArrayList<Robot> robotList) throws IOException {

        MongoCollection<Document> collection = database.getCollection("robot");
        List<Document> documents = new ArrayList<Document>();

        for (int i = 0; i < robotList.size(); i++ ) {

            // Save every robot into a temporary variable and add it to a list
            Document roboTemp = new Document("id", robotList.get(i).getId())
                    .append("cluster", robotList.get(i).getCluster())
                    .append("signal1", robotList.get(i).getSignal1())
                    .append("signal2", robotList.get(i).getSignal2())
                    .append("signal3", robotList.get(i).getSignal3())
                    .append("signal4", robotList.get(i).getSignal4())
                    .append("signal5", robotList.get(i).getSignal5())
                    .append("signal6", robotList.get(i).getSignal6())
                    .append("signal7", robotList.get(i).getSignal7());

            documents.add(roboTemp);
        }

        // Write the documents into the DB
        collection.insertMany(documents);

    }
}