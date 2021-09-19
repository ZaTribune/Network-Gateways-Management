package zatribune.spring.gateways.services;

import zatribune.spring.gateways.data.models.Gateway;

import java.util.List;
import java.util.UUID;

public interface CrudService <T>{
    T getById(UUID uuid);
    T save(T input);
    T update(UUID id,T input);
    void delete(T input);
    void deleteById(UUID id);
    List<T>getAll();
}
