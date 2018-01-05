package simulation;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import controller.DBManager;
import model.Robot;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class tests {

    public static void main(String[] args) {
        runUpdateTests(DBManager.dbConnect(), 100);

        // Get data into a list
        ArrayList<Robot> robotList = DBManager.getDataFromDB(DBManager.dbConnect());

        System.out.println(robotList.get(robotList.size()-1).getSignal1());



    }

    public static void runGetTests() {
        long startTime = System.currentTimeMillis();
        // Get data into a list
        ArrayList<Robot> robotList = DBManager.getDataFromDB(DBManager.dbConnect());

        System.out.println(robotList.size());

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Got " + robotList.size() + " robots in " + elapsedTime + "ms");
    }

    public static void runInsertTests(MongoDatabase db) {

        MongoCollection<Document> collection = db.getCollection("restaurants");

        Document document = new Document("name", "Café Con Leche")
                .append("contact", new Document("phone", "228-555-0149")
                        .append("email", "cafeconleche@example.com")
                        .append("location", Arrays.asList(-73.92502, 40.8279556)))
                .append("stars", 3)
                .append("categories", Arrays.asList("Bakery", "Coffee", "Pastries"));

        collection.insertOne(document);
    }


    public static void runUpdateTests(MongoDatabase db, int nClusters) {

        // Get count of the documents stored in the db
        long robotCount = db.getCollection("robot").count();

        MongoCollection<Document> collection = db.getCollection("robot");

        long startTime = System.currentTimeMillis();

        // Update every robot in the collection
        for (int i = 1; i <= nClusters; i++) {
            collection.updateMany(

                    // Update every document that matches the fieldName id
                    eq("cluster", i),
                    combine(set("signal1", 7), set("signal2", 0)));
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Updated " + nClusters + " containing " +robotCount +" robot entries in " + elapsedTime + "ms");



    }


}
