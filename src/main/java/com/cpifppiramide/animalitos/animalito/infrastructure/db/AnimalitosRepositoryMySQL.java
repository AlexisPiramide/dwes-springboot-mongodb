package com.cpifppiramide.animalitos.animalito.infrastructure.db;

import com.cpifppiramide.animalitos.animalito.domain.Animalito;
import com.cpifppiramide.animalitos.animalito.domain.AnimalitosRepository;
import com.cpifppiramide.animalitos.context.MySQLDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalitosRepositoryMySQL implements AnimalitosRepository {


    @Override
    public List<Animalito> getAll() {
        List<Animalito> animalitos = new ArrayList<>();
        try {
            ResultSet rs = MySQLDBConnection.getInstance().prepareStatement("select * from animales").executeQuery();
            while (rs.next()){
                Animalito animalito = new Animalito(rs.getString("id"),rs.getString("nombre"),rs.getString("tipo"));
                animalitos.add(animalito);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return animalitos;
    }

    @Override
    public Animalito get(String id) {
        Animalito animalito =null;
        try {
            ResultSet rs = MySQLDBConnection.getInstance().prepareStatement("select * from animales where id = "+id).executeQuery();
            rs.next();
            animalito = new Animalito(rs.getString("id"), rs.getString("nombre"),rs.getString("tipo"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return animalito;
    }

    @Override
    public Animalito save(Animalito animalito) {
        Animalito animal = null;
        try {
            PreparedStatement preparedStatemen =  MySQLDBConnection.getInstance().prepareStatement("INSERT INTO animales (nombre,tipo) VALUES (?,?)");
            preparedStatemen.setString(1,animalito.getNombre());
            preparedStatemen.setString(2,animalito.getNombre());
            ResultSet rs =preparedStatemen.executeQuery();
            rs.next();
            animal = new Animalito(rs.getString("id"), rs.getString("nombre"),rs.getString("tipo"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return animal;
    }
}
