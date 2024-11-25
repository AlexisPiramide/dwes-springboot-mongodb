package com.cpifppiramide.animalitos.animalito.domain;

public class Animalito {

    private final String id,nombre, tipo;

    public Animalito(String id, String nombre, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
