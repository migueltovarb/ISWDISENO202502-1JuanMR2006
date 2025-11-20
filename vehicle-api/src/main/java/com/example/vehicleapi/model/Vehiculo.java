package com.example.vehicleapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Document(collection = "vehicles")
public class Vehiculo {

    @Id
    private String id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String marca;

    @NotBlank
    @Size(min = 1, max = 50)
    private String modelo;

    @NotNull
    @Positive
    private Integer anio; // cambiado de "año" a "anio"

    @NotBlank
    @Size(min = 3, max = 20)
    private String color;

    @Pattern(regexp = "^[A-Z]{3}-[0-9]{3}$|^[A-Z]{3}[0-9]{3}$")
    @Indexed(unique = true)
    private String placa;

    @NotBlank
    @Pattern(regexp = "GASOLINA|DIESEL|ELECTRICO|HIBRIDO")
    private String tipoCombustible;

    @Positive
    private Double kilometraje;

    @Positive
    private Double precio;

    private String descripcion;
    private Boolean disponible = true;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    public Vehiculo() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    public Vehiculo(String marca, String modelo, Integer anio, String color, String placa, String tipoCombustible, Double kilometraje, Double precio) {
        this();
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.color = color;
        this.placa = placa;
        this.tipoCombustible = tipoCombustible;
        this.kilometraje = kilometraje;
        this.precio = precio;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) {
        this.placa = placa;
        this.fechaActualizacion = LocalDateTime.now();
    }

    public String getTipoCombustible() { return tipoCombustible; }
    public void setTipoCombustible(String tipoCombustible) { this.tipoCombustible = tipoCombustible; }

    public Double getKilometraje() { return kilometraje; }
    public void setKilometraje(Double kilometraje) {
        this.kilometraje = kilometraje;
        this.fechaActualizacion = LocalDateTime.now();
    }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) {
        this.precio = precio;
        this.fechaActualizacion = LocalDateTime.now();
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        this.fechaActualizacion = LocalDateTime.now();
    }

    public Boolean getDisponible() { return disponible; }
    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
        this.fechaActualizacion = LocalDateTime.now();
    }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

    @Override
    public String toString() {
        return "Vehiculo{" +
            "id='" + id + '\'' +
            ", marca='" + marca + '\'' +
            ", modelo='" + modelo + '\'' +
            ", anio=" + anio +
            ", color='" + color + '\'' +
            ", placa='" + placa + '\'' +
            ", tipoCombustible='" + tipoCombustible + '\'' +
            ", kilometraje=" + kilometraje +
            ", precio=" + precio +
            ", disponible=" + disponible +
            '}';
    }
}