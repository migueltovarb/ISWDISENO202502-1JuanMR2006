package mascotasVeterinaria;

import java.util.ArrayList;

public class Mascota {
    private String nombre;
    private String especie;
    private int edad;
    private Dueno dueno;
    private ArrayList<ControlVeterinario> controles;

    public Mascota(String nombre, String especie, int edad, Dueno dueno) {
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
    }
}
