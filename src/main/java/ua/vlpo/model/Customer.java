package ua.vlpo.model;

import java.io.Serializable;
import java.util.List;
import org.bson.types.ObjectId;

public class Customer implements Serializable {
    ObjectId id;
    String name;
    Integer age;
    List<String> cars;

    public Customer() {
    }

    public Customer(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Customer(String name, Integer age, List<String> cars) {
        this.name = name;
        this.age = age;
        this.cars = cars;
    }

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getCars() {
        return cars;
    }

    public void setCars(List<String> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cars=" + cars +
                '}';
    }
}