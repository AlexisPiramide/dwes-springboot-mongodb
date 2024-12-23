package com.cpifppiramide.animalitos;

import com.cpifppiramide.animalitos.animalito.application.AnimalitosUseCases;
import com.cpifppiramide.animalitos.animalito.domain.Animalito;
import com.cpifppiramide.animalitos.animalito.domain.AnimalitosRepository;
import com.cpifppiramide.animalitos.animalito.infrastructure.AnimalitosRepositoryMongoDB;
import com.cpifppiramide.animalitos.context.MongoDBConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AnimalitosApplicationTests {

	AnimalitosRepository animalitosRepository = new AnimalitosRepositoryMongoDB();
	AnimalitosUseCases animalitosUseCases = new AnimalitosUseCases(animalitosRepository);

	@BeforeAll()
	public static void clean(){
		MongoDBConnection.getDatabase().getCollection("animalitos").drop();
	}

	@AfterEach()
	public void cleanAfter(){
		MongoDBConnection.getDatabase().getCollection("animalitos").drop();
	}

	@Test
	public void save(){
		Animalito animalito = new Animalito("Pez");
		Animalito devolver = animalitosUseCases.save(animalito);
		assertEquals(animalito.getNombre(),devolver.getNombre());
	}

	@Test
	public void list(){
		Animalito animalito = new Animalito("Pez");
		animalitosUseCases.save(animalito);
		List<Animalito> animalitos = animalitosUseCases.getAll();
		assertEquals(1, animalitos.size());
	}

}
