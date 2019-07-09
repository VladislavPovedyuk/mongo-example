package ua.vlpo.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public final class MongoClientSingleton {
    private static MongoClient INSTANCE;
    public static final String CUSTOMERS_COLLECTION = "customers";

    private MongoClientSingleton() {
    }

    private static MongoClient getInstance() {
        if (INSTANCE == null) INSTANCE = new MongoClient("localhost", 27017);
        return INSTANCE;
    }

    private static MongoDatabase getDatabase() {
        return getInstance().getDatabase("vlpo");
    }

    public static MongoCollection<Document> createCollection() {
        getDatabase().createCollection(CUSTOMERS_COLLECTION);
        return getDatabase().getCollection(CUSTOMERS_COLLECTION);
    }
}