package ua.vlpo.custom.samples;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import java.util.Arrays;
import ua.vlpo.model.Customer;
import ua.vlpo.mongo.MongoSerializedClientSingleton;
import ua.vlpo.util.DBPopulator;

public class SerializedMain {
    public static void main(String[] args) {
        MongoCollection<Customer> collection = MongoSerializedClientSingleton.createCollection(true);
        collection.insertOne(DBPopulator.getOneSerialized());
        collection.insertMany(DBPopulator.getManySerialized());
        System.out.println("=== Number of documents in collection ===");
        System.out.println(collection.countDocuments());

        printCurrentCollectionState(collection);

        Customer firstDocument = collection.find().first();
        System.out.println("=== First document ===");
        System.out.println(firstDocument);

        System.out.println("=== Find with name all who have cars and age is over 25 ===");
        for (Customer document : collection.find(Filters.and(Filters.exists("cars", true), Filters.gt("age", 27)))) {
            System.out.println(document);
        }

        System.out.println("=== Delete where age equals 29 ===");
        collection.deleteOne(Filters.eq("age", 29));

        printCurrentCollectionState(collection);

        System.out.println("=== Give Oleksii a car ===");
        collection.updateOne(Filters.exists("cars", false), Updates.combine(Updates.set("cars", Arrays.asList("YA3 Patriot"))));

        printCurrentCollectionState(collection);

        System.out.println("=== Mass update ===");
        UpdateResult updateResult = collection.updateMany(Filters.lte("age", 23), Updates.inc("age", 1));
        System.out.println("=== Updated records count === ");
        System.out.println(updateResult.getModifiedCount());
        printCurrentCollectionState(collection);

        System.out.println("=== Find YA3 (as second car) owner ===");
        Customer ya3_patriot = collection.find(Filters.eq("cars.1", "YA3 Patriot")).first();
        System.out.println(ya3_patriot);

        collection.drop();
    }

    private static void printCurrentCollectionState(MongoCollection<Customer> collection) {
        System.out.println("=== Documents present ===");
        for (Customer customer : collection.find()) {
            System.out.println(customer);
        }
    }
}