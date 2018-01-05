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

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;


public class DBManager {

    /* Conenct to the DB */
    public static MongoDatabase dbConnect() {

        MongoClientURI uri = new MongoClientURI("mongodb://admin:testadmin123@87.18.236.85/?authSource=unnamedb");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase("unnamedb");

        return db;

    }

    /* Insert robots in the DB */
    public static void saveDataToDB(MongoDatabase database, ArrayList<Robot> robotList) throws IOException {

        MongoCollection<Document> collection = database.getCollection("robot");
        List<Document> documents = new ArrayList<Document>();

        for (int i = 0; i < robotList.size(); i++) {

            // Save every robot into a temporary variable and add it to a list
            Document roboTemp = new Document("id", robotList.get(i).getId())
                    .append("cluster", robotList.get(i).getCluster())
                    .append("signal1", robotList.get(i).getSignal1())
                    .append("signal2", robotList.get(i).getSignal2())
                    .append("signal3", robotList.get(i).getSignal3())
                    .append("signal4", robotList.get(i).getSignal4())
                    .append("signal5", robotList.get(i).getSignal5())
                    .append("signal6", robotList.get(i).getSignal6())
                    .append("signal7", robotList.get(i).getSignal7())
                    .append("signal1Time", robotList.get(i).getSignal1Time())
                    .append("signal2Time", robotList.get(i).getSignal2Time())
                    .append("signal3Time", robotList.get(i).getSignal3Time())
                    .append("signal4Time", robotList.get(i).getSignal4Time())
                    .append("signal5Time", robotList.get(i).getSignal5Time())
                    .append("signal6Time", robotList.get(i).getSignal6Time())
                    .append("signal7Time", robotList.get(i).getSignal7Time());

            documents.add(roboTemp);
        }

        // Write the documents into the DB
        collection.insertMany(documents);

    }

    /* Get the list of robots from the database */
    public static ArrayList<Robot> getDataFromDB(MongoDatabase database) {

        MongoCollection<Document> collection = database.getCollection("robot");

        List<Document> robots = (List<Document>) collection.find().into(
                new ArrayList<Document>());

        ArrayList<Robot> robotList = new ArrayList<Robot>();

        for (Document robot : robots) {

            Robot roboTemp = new Robot(robot.getInteger("id"), robot.getInteger("cluster"), robot.getInteger("signal1"), robot.getInteger("signal2"),
                    robot.getInteger("signal3"), robot.getInteger("signal4"), robot.getInteger("signal5"), robot.getInteger("signal6"),
                    robot.getInteger("signal7"), robot.getDate("signal1Time"), robot.getDate("signal2Time"), robot.getDate("signal3Time"),
                    robot.getDate("signal4Time"), robot.getDate("signal5Time"), robot.getDate("signal6Time"), robot.getDate("signal7Time"));

            robotList.add(roboTemp);

        }

        return robotList;
    }

    // TODO: Use threads
    /* Update elements in the DB*/
    public static void runUpdateTests(MongoDatabase db, int nClusters) {

        // Get count of the documents stored in the db
        long robotCount = db.getCollection("robot").count();

        MongoCollection<Document> collection = db.getCollection("robot");

        long startTime = System.currentTimeMillis();

        // Update every robot in the collection
        // The procedure is done by cluster to improve speed
        for (int i = 1; i <= nClusters; i++) {
            collection.updateMany(

                    // Update every document that matches the fieldName 'id'
                    eq("cluster", i),
                    combine(set("signal1", 7), set("signal2", 0)));
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Updated " + nClusters + " containing " + robotCount + " robot entries in " + elapsedTime + "ms");

    }

}