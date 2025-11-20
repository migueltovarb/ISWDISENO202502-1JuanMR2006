package com.example.vehicleapi.controller;

import com.example.vehicleapi.model.Vehiculo;
import com.example.vehicleapi.service.ServicioVehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
@Validated
@CrossOrigin(origins = "*")
public class controlVehiculo {

    @Autowired
    private ServicioVehiculo servicioVehiculo;

    @GetMapping
    public ResponseEntity<List<Vehiculo>> getAllVehicles() {
        return ResponseEntity.ok(servicioVehiculo.getAllVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehicleById(@PathVariable String id) {
        return ResponseEntity.ok(servicioVehiculo.getVehicleById(id));
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<Vehiculo> getVehicleByPlaca(@PathVariable @NotBlank String placa) {
        return ResponseEntity.ok(servicioVehiculo.getVehicleByPlaca(placa));
    }

    @PostMapping
    public ResponseEntity<Vehiculo> createVehicle(@Valid @RequestBody Vehiculo vehiculo) {
        Vehiculo newVehiculo = servicioVehiculo.createVehicle(vehiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newVehiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> updateVehicle(@PathVariable String id, @Valid @RequestBody Vehiculo vehiculoDetails) {
        return ResponseEntity.ok(servicioVehiculo.updateVehicle(id, vehiculoDetails));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vehiculo> partialUpdateVehicle(@PathVariable String id, @RequestBody Vehiculo vehiculoDetails) {
        return ResponseEntity.ok(servicioVehiculo.updateVehicle(id, vehiculoDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteVehicle(@PathVariable String id) {
        servicioVehiculo.deleteVehicle(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Vehículo eliminado exitosamente");
        response.put("id", id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Vehiculo>> getVehiclesByMarca(@PathVariable String marca) {
        return ResponseEntity.ok(servicioVehiculo.getVehiclesByMarca(marca));
    }

    @GetMapping("/marca/{marca}/modelo/{modelo}")
    public ResponseEntity<List<Vehiculo>> getVehiclesByMarcaAndModelo(@PathVariable String marca, @PathVariable String modelo) {
        return ResponseEntity.ok(servicioVehiculo.getVehiclesByMarcaAndModelo(marca, modelo));
    }

    @GetMapping("/years")
    public ResponseEntity<List<Vehiculo>> getVehiclesByYearRange(@RequestParam Integer startYear, @RequestParam Integer endYear) {
        return ResponseEntity.ok(servicioVehiculo.getVehiclesByYearRange(startYear, endYear));
    }

    @GetMapping("/combustible/{tipo}")
    public ResponseEntity<List<Vehiculo>> getVehiclesByTipoCombustible(@PathVariable String tipo) {
        return ResponseEntity.ok(servicioVehiculo.getVehiclesByTipoCombustible(tipo));
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Vehiculo>> getVehiclesByColor(@PathVariable String color) {
        return ResponseEntity.ok(servicioVehiculo.getVehiclesByColor(color));
    }

    @GetMapping("/disponibilidad/{disponible}")
    public ResponseEntity<List<Vehiculo>> getVehiclesByDisponibilidad(@PathVariable Boolean disponible) {
        return ResponseEntity.ok(servicioVehiculo.getVehiclesByDisponibilidad(disponible));
    }

    @GetMapping("/precio")
    public ResponseEntity<List<Vehiculo>> getVehiclesByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        return ResponseEntity.ok(servicioVehiculo.getVehiclesByPriceRange(min, max));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Vehiculo>> searchVehicles(@RequestParam(required = false) String marca,
                                                        @RequestParam(required = false) String modelo,
                                                        @RequestParam(required = false) Integer año,
                                                        @RequestParam(required = false) String tipoCombustible) {
        return ResponseEntity.ok(servicioVehiculo.searchVehicles(marca, modelo, año, tipoCombustible));
    }

    @GetMapping("/stats/marca/{marca}")
    public ResponseEntity<Map<String, Object>> getStatsByMarca(@PathVariable String marca) {
        long count = servicioVehiculo.countVehiclesByMarca(marca);
        Map<String, Object> stats = new HashMap<>();
        stats.put("marca", marca);
        stats.put("total", count);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/check-placa/{placa}")
    public ResponseEntity<Map<String, Object>> checkPlacaAvailability(@PathVariable String placa) {
        boolean exists = servicioVehiculo.existsByPlaca(placa);
        Map<String, Object> response = new HashMap<>();
        response.put("placa", placa.toUpperCase());
        response.put("disponible", !exists);
        response.put("mensaje", exists ? "Placa ya está en uso" : "Placa disponible");
        return ResponseEntity.ok(response);
    }
}