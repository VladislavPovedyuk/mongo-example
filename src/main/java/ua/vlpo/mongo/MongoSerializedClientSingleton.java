package ua.vlpo.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import ua.vlpo.model.Customer;

public class MongoSerializedClientSingleton {
    private static MongoClient INSTANCE;
    public static final String CUSTOMERS_COLLECTION = "customers";

    private MongoSerializedClientSingleton() {
    }

    private static MongoClient getInstance(boolean isWholeClientSerialized) {
        if (isWholeClientSerialized) {
            //POJO Serialization applied for whole client
            if (INSTANCE == null) {
                PojoCodecProvider codecProvider = PojoCodecProvider.builder()
                        .automatic(true)
                        .build();

                CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(codecProvider));
                INSTANCE = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
            }
        } else {
            //POJO Serialization applied for database
            if (INSTANCE == null) INSTANCE = new MongoClient("localhost", 27017);
        }

        return INSTANCE;
    }

    private static MongoDatabase getDatabase(boolean isWholeClientSerialized) {
        if (isWholeClientSerialized) {
            //POJO Serialization applied for whole client
            return getInstance(isWholeClientSerialized).getDatabase("vlpo");
        } else {
            //POJO Serialization applied for database
            PojoCodecProvider codecProvider = PojoCodecProvider.builder()
                    .automatic(true)
                    .build();

            CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(codecProvider));
            return getInstance(isWholeClientSerialized).getDatabase("vlpo").withCodecRegistry(pojoCodecRegistry);
        }
    }

    public static MongoCollection<Customer> createCollection(boolean isWholeClientSerialized) {
        getDatabase(isWholeClientSerialized).createCollection(CUSTOMERS_COLLECTION);
        return getDatabase(isWholeClientSerialized).getCollection(CUSTOMERS_COLLECTION, Customer.class);
    }
}
