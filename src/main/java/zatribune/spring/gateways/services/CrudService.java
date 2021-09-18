package zatribune.spring.gateways.services;

import zatribune.spring.gateways.data.models.Gateway;

import java.util.List;
import java.util.UUID;

public interface CrudService <T>{
    T getById(UUID uuid);
    T save(T input);
    T update(T input);
    void delete(T input);
    List<T>getAll();
}
