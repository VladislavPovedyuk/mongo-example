package ua.vlpo.custom.samples;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import java.util.Collections;
import org.bson.Document;
import ua.vlpo.mongo.MongoClientSingleton;
import ua.vlpo.util.DBPopulator;

public class Main {
    public static void main(String[] args) {
        MongoCollection<Document> collection = MongoClientSingleton.createCollection();
        collection.insertOne(DBPopulator.getOne());
        collection.insertMany(DBPopulator.getMany());
        System.out.println("=== Number of documents in collection ===");
        System.out.println(collection.countDocuments());

        printCurrentCollectionState(collection);

        Document firstDocument = collection.find().first();
        System.out.println("=== First document ===");
        System.out.println(firstDocument.toJson());

        System.out.println("=== Find all who have cars and age is over 27 ===");
        for (Document document : collection.find(Filters.and(Filters.exists("cars", true), Filters.gt("age", 27)))) {
            System.out.println(document.toJson());
        }

        System.out.println("=== Delete where age equals 29 ===");
        for (Document document : collection.find(Filters.eq("age", 29))) {
            collection.deleteOne(document);
        }

        printCurrentCollectionState(collection);

        System.out.println("=== Give Oleksii a car ===");
        Document noCar = collection.find(Filters.exists("cars", false)).first();
        collection.updateOne(noCar, new Document("$set", new Document().append("cars", Collections.singletonList("YA3 Patriot"))));

        printCurrentCollectionState(collection);

        System.out.println("=== Mass update ===");
        UpdateResult updateResult = collection.updateMany(Filters.lte("age", 23), Updates.inc("age", 1));
        System.out.println("=== Updated records count === ");
        System.out.println(updateResult.getModifiedCount());
        printCurrentCollectionState(collection);

        System.out.println("=== Find YA3 (as second car) owner ===");
        Document ya3_patriot = collection.find(Filters.eq("cars.1", "YA3 Patriot")).first();
        System.out.println(ya3_patriot.toJson());

        collection.drop();
    }

    private static void printCurrentCollectionState(MongoCollection<Document> collection) {
        System.out.println("=== Documents present ===");
        for (Document document : collection.find()) {
            System.out.println(document.toJson());
        }
    }
}