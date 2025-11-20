package com.example.vehicleapi.repository;

import com.example.vehicleapi.model.Vehiculo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends MongoRepository<Vehiculo, String> {
    Optional<Vehiculo> findByPlaca(String placa);
    List<Vehiculo> findByMarcaIgnoreCase(String marca);
    List<Vehiculo> findByMarcaIgnoreCaseAndModeloIgnoreCase(String marca, String modelo);
    List<Vehiculo> findByAnioBetween(Integer yearStart, Integer yearEnd); // <-- método derivado correcto
    List<Vehiculo> findByTipoCombustibleIgnoreCase(String tipoCombustible);
    List<Vehiculo> findByColorIgnoreCase(String color);
    List<Vehiculo> findByDisponible(Boolean disponible);
    List<Vehiculo> findByPrecioBetween(Double precioMin, Double precioMax);
    List<Vehiculo> findByKilometrajeLessThanEqual(Double maxKilometraje);

    @Query("{ $and: [" +
           "{ $or: [ { 'marca': { $regex: ?0, $options: 'i' } }, { ?0: null } ] }," +
           "{ $or: [ { 'modelo': { $regex: ?1, $options: 'i' } }, { ?1: null } ] }," +
           "{ $or: [ { 'año': ?2 }, { ?2: null } ] }," + // usa el nombre del campo en MongoDB si en el modelo usas @Field("año")
           "{ $or: [ { 'tipoCombustible': { $regex: ?3, $options: 'i' } }, { ?3: null } ] }" +
           "] }")
    List<Vehiculo> findByMultipleCriteria(String marca, String modelo, Integer year, String tipoCombustible);

    long countByMarcaIgnoreCase(String marca);
    boolean existsByPlaca(String placa);
}