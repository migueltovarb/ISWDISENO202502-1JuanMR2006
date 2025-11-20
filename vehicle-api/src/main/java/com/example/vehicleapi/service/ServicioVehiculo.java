package com.example.vehicleapi.service;

import com.example.vehicleapi.model.Vehiculo;
import com.example.vehicleapi.repository.VehicleRepository;
import com.example.vehicleapi.exception.VehicleNotFoundException;
import com.example.vehicleapi.exception.DuplicatePlacaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServicioVehiculo {

    @Autowired
    private VehicleRepository vehicleRepository; // usa VehicleRepository y variable vehicleRepository

    public List<Vehiculo> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehiculo createVehicle(Vehiculo vehiculo) {
        if (vehiculo.getPlaca() != null && vehicleRepository.existsByPlaca(vehiculo.getPlaca().toUpperCase())) {
            throw new DuplicatePlacaException("Ya existe un vehículo con la placa: " + vehiculo.getPlaca());
        }
        if (vehiculo.getPlaca() != null) vehiculo.setPlaca(vehiculo.getPlaca().toUpperCase());
        if (vehiculo.getMarca() != null) vehiculo.setMarca(vehiculo.getMarca().toUpperCase());
        if (vehiculo.getModelo() != null) vehiculo.setModelo(vehiculo.getModelo().toUpperCase());
        if (vehiculo.getColor() != null) vehiculo.setColor(vehiculo.getColor().toUpperCase());
        if (vehiculo.getTipoCombustible() != null) vehiculo.setTipoCombustible(vehiculo.getTipoCombustible().toUpperCase());
        vehiculo.setFechaCreacion(LocalDateTime.now());
        vehiculo.setFechaActualizacion(LocalDateTime.now());
        return vehicleRepository.save(vehiculo);
    }

    public Vehiculo getVehicleById(String id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehículo no encontrado con ID: " + id));
    }

    public Vehiculo getVehicleByPlaca(String placa) {
        return vehicleRepository.findByPlaca(placa.toUpperCase())
                .orElseThrow(() -> new VehicleNotFoundException("Vehículo no encontrado con placa: " + placa));
    }

    public Vehiculo updateVehicle(String id, Vehiculo vehiculoDetails) {
        Vehiculo vehiculo = getVehicleById(id);

        if (vehiculoDetails.getPlaca() != null && !vehiculoDetails.getPlaca().equalsIgnoreCase(vehiculo.getPlaca())) {
            if (vehicleRepository.existsByPlaca(vehiculoDetails.getPlaca().toUpperCase())) {
                throw new DuplicatePlacaException("Ya existe un vehículo con la placa: " + vehiculoDetails.getPlaca());
            }
        }

        if (vehiculoDetails.getMarca() != null) vehiculo.setMarca(vehiculoDetails.getMarca().toUpperCase());
        if (vehiculoDetails.getModelo() != null) vehiculo.setModelo(vehiculoDetails.getModelo().toUpperCase());
        if (vehiculoDetails.getAnio() != null) vehiculo.setAnio(vehiculoDetails.getAnio());
        if (vehiculoDetails.getColor() != null) vehiculo.setColor(vehiculoDetails.getColor().toUpperCase());
        if (vehiculoDetails.getPlaca() != null) vehiculo.setPlaca(vehiculoDetails.getPlaca().toUpperCase());
        if (vehiculoDetails.getTipoCombustible() != null) vehiculo.setTipoCombustible(vehiculoDetails.getTipoCombustible().toUpperCase());
        if (vehiculoDetails.getKilometraje() != null) vehiculo.setKilometraje(vehiculoDetails.getKilometraje());
        if (vehiculoDetails.getPrecio() != null) vehiculo.setPrecio(vehiculoDetails.getPrecio());
        if (vehiculoDetails.getDescripcion() != null) vehiculo.setDescripcion(vehiculoDetails.getDescripcion());
        if (vehiculoDetails.getDisponible() != null) vehiculo.setDisponible(vehiculoDetails.getDisponible());

        vehiculo.setFechaActualizacion(LocalDateTime.now());
        return vehicleRepository.save(vehiculo);
    }

    public void deleteVehicle(String id) {
        Vehiculo vehiculo = getVehicleById(id);
        vehicleRepository.delete(vehiculo);
    }

    public List<Vehiculo> getVehiclesByMarca(String marca) {
        return vehicleRepository.findByMarcaIgnoreCase(marca);
    }

    public List<Vehiculo> getVehiclesByMarcaAndModelo(String marca, String modelo) {
        return vehicleRepository.findByMarcaIgnoreCaseAndModeloIgnoreCase(marca, modelo);
    }

    public List<Vehiculo> getVehiclesByYearRange(Integer startYear, Integer endYear) {
        return vehicleRepository.findByAnioBetween(startYear, endYear);
    }

    public List<Vehiculo> getVehiclesByTipoCombustible(String tipoCombustible) {
        return vehicleRepository.findByTipoCombustibleIgnoreCase(tipoCombustible);
    }

    public List<Vehiculo> getVehiclesByColor(String color) {
        return vehicleRepository.findByColorIgnoreCase(color);
    }

    public List<Vehiculo> getVehiclesByDisponibilidad(Boolean disponible) {
        return vehicleRepository.findByDisponible(disponible);
    }

    public List<Vehiculo> getVehiclesByPriceRange(Double precioMin, Double precioMax) {
        return vehicleRepository.findByPrecioBetween(precioMin, precioMax);
    }

    public List<Vehiculo> getVehiclesByMaxKilometraje(Double maxKilometraje) {
        return vehicleRepository.findByKilometrajeLessThanEqual(maxKilometraje);
    }

    public List<Vehiculo> searchVehicles(String marca, String modelo, Integer anio, String tipoCombustible) {
        return vehicleRepository.findByMultipleCriteria(marca, modelo, anio, tipoCombustible);
    }

    public long countVehiclesByMarca(String marca) {
        return vehicleRepository.countByMarcaIgnoreCase(marca);
    }

    public boolean existsByPlaca(String placa) {
        return vehicleRepository.existsByPlaca(placa.toUpperCase());
    }
}