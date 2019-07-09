package ua.vlpo.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.vlpo.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {
    CustomerEntity findTopByOrderByIdAsc();
    List<CustomerEntity> findAllByCarsExistsAndAgeGreaterThan(boolean hasCars, Integer age);
    void deleteByAgeEquals(Integer age);
    CustomerEntity findTopByCarsExistsOrderByIdAsc(boolean hasCars);
    List<CustomerEntity> findAllByAgeLessThanEqual(Integer age);
    List<CustomerEntity> findAllByCarsContains(String car);
}