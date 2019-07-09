package ua.vlpo.entity;

import java.util.List;
import org.springframework.data.annotation.Id;

public class CustomerEntity {
    @Id
    private String id;
    private String name;
    private Integer age;
    private List<String> cars;

    public CustomerEntity() {
    }

    public CustomerEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public CustomerEntity(String name, Integer age, List<String> cars) {
        this.name = name;
        this.age = age;
        this.cars = cars;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "CustomerEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", cars=" + cars +
                '}';
    }
}