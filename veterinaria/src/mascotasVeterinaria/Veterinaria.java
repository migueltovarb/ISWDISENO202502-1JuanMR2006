package mascotasVeterinaria;

import java.util.ArrayList;

public class Veterinaria {

    static class Dueno {
        private String nombreCompleto;
        private String documento;
        private String telefono;
        private ArrayList<Mascota> mascotas;

        public Dueno(String nombreCompleto, String documento, String telefono) {
            this.nombreCompleto = nombreCompleto;
            this.documento = documento;
            this.telefono = telefono;
            this.mascotas = new ArrayList<>();
        }

        public String getNombreCompleto() {
            return nombreCompleto;
        }

        public void setNombreCompleto(String nombreCompleto) {
            this.nombreCompleto = nombreCompleto;
        }

        public String getDocumento() {
            return documento;
        }

        public void setDocumento(String documento) {
            this.documento = documento;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public ArrayList<Mascota> getMascotas() {
            return mascotas;
        }

        public void agregarMascota(Mascota mascota) {
            for (Mascota m : mascotas) {
                if (m.getNombre().equalsIgnoreCase(mascota.getNombre())) {
                    System.out.println(" Ya existe una mascota con ese nombre para este dueño.");
                    return;
                }
            }
            mascotas.add(mascota);
            System.out.println(" Mascota registrada exitosamente para " + nombreCompleto);
        }
    }

    static class Mascota {
        private String nombre;
        private String especie;
        private int edad;
        private Dueno dueno;
        private ArrayList<ControlVeterinario> controles;

        public Mascota(String nombre, String especie, int edad, Dueno dueno) {
            if (edad < 0) {
                throw new IllegalArgumentException(" La edad no puede ser negativa.");
            }
            this.nombre = nombre;
            this.especie = especie;
            this.edad = edad;
            this.dueno = dueno;
            this.controles = new ArrayList<>();
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEspecie() {
            return especie;
        }

        public void setEspecie(String especie) {
            this.especie = especie;
        }

        public int getEdad() {
            return edad;
        }

        public void setEdad(int edad) {
            if (edad < 0) {
                throw new IllegalArgumentException("La edad no puede ser negativa.");
            }
            this.edad = edad;
        }

        public Dueno getDueno() {
            return dueno;
        }

        public void setDueno(Dueno dueno) {
            this.dueno = dueno;
        }

        public ArrayList<ControlVeterinario> getControles() {
            return controles;
        }

        public void agregarControl(ControlVeterinario control) {
            controles.add(control);
            System.out.println(" Control veterinario agregado a " + nombre);
        }

        public void mostrarResumen() {
            System.out.println("\n Resumen de la mascota:");
            System.out.println("Nombre: " + nombre);
            System.out.println("Especie: " + especie);
            System.out.println("Edad: " + edad + " años");
            System.out.println("Dueño: " + dueno.getNombreCompleto());
            System.out.println("Controles registrados: " + controles.size());
        }
    }

    static class ControlVeterinario {
        private String fecha;
        private String tipoControl;
        private String observaciones;
        private Mascota mascota;

        public ControlVeterinario(String fecha, String tipoControl, String observaciones, Mascota mascota) {
            this.fecha = fecha;
            this.tipoControl = tipoControl;
            this.observaciones = observaciones;
            this.mascota = mascota;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public String getTipoControl() {
            return tipoControl;
        }

        public void setTipoControl(String tipoControl) {
            this.tipoControl = tipoControl;
        }

        public String getObservaciones() {
            return observaciones;
        }

        public void setObservaciones(String observaciones) {
            this.observaciones = observaciones;
        }

        public Mascota getMascota() {
            return mascota;
        }

        public void setMascota(Mascota mascota) {
            this.mascota = mascota;
        }

        public void mostrarDetalle() {
            System.out.println("\n Detalle del control:");
            System.out.println("Fecha: " + fecha);
            System.out.println("Tipo: " + tipoControl);
            System.out.println("Observaciones: " + observaciones);
            System.out.println("Mascota: " + mascota.getNombre());
        }
    }
}
