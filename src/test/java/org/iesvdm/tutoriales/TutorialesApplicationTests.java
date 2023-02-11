package org.iesvdm.tutoriales;

import jakarta.persistence.Table;
import org.iesvdm.tutoriales.domain.*;
import org.iesvdm.tutoriales.repository.PeliculaRepository;
import org.iesvdm.tutoriales.repository.SocioRepository;
import org.iesvdm.tutoriales.repository.TutorialRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assert.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Period;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest
class TutorialesApplicationTests {

    @Autowired
    TutorialRepository tutorialRepository;

    @Autowired
    SocioRepository socioRepository;

    @Autowired
    PeliculaRepository peliculaRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @Order(1)
    void testSocioRepository() {

        Tarjeta tarjeta = Tarjeta.builder()
                .numero("5277497866899365")
                .caducidad("04/27")
                .build();

        Socio socio = Socio.builder()
                .dni("00110011X")
                .nombre("Pedro")
                .apellidos("Picapiedra García")
                .tarjeta(tarjeta)
                .build();

        tarjeta.setSocio(socio);

        socioRepository.save(socio);

        List<Socio> socioList = socioRepository.findAll();

    }

    @Test
    @Order(2)
    void testFindAllSocios() {

        List<Socio> socioList = socioRepository.findAll();

        socioList.forEach(System.out::println);

    }

    @Test
    void testSaveTutorialOnlyRepository() {

        Tutorial tutorial1 = Tutorial.builder().title("Tutorial JPA")
                .description("Se describen aspectos de modelo/entidad con JPA/Hibernate")
//                .comments(commentList)
                .build();

        Tutorial tutorialSved =  tutorialRepository.save(tutorial1);

        List<Tutorial> tutorialList =  tutorialRepository.findAll();
        tutorialList.forEach(System.out::println);

        assertThat(tutorialList).hasSize(1);

    }

    @Test
    void testTutorialWithCommentsRepository() {

        Comment comment1 = Comment.builder().content("El tutorial no está mal, pero hay cosillas que no cuenta").build();
        Comment comment2 = Comment.builder().content("Genial!").build();

        List<Comment> commentList = Arrays.asList(comment1, comment2);

        Tutorial tutorial1 = Tutorial.builder().title("Tuto2 JPA")
                .description("Otro tuto sobre modelo/entidad con JPA/Hibernate")
                .build();

        Tutorial tutorialSave = tutorialRepository.save(tutorial1);

        //Seteamos el tutorial
        commentList.forEach( c -> c.setTutorial(tutorialSave));

        tutorialSave.setComments(commentList);

        tutorialRepository.save(tutorialSave);

        List<Tutorial> tutorialList =  tutorialRepository.findAll();
        tutorialList.forEach(System.out::println);

    }

    @Test
    void testSaveOnePelicula() {

        Pelicula pelicula = Pelicula.builder().titulo("Indiana Jones")
                .descripcion("Película para toda la familia de aventura")
                .anyoLanzamiento("1990")
                .idioma("Español")
                .idiomaOriginal("Inglés")
                .duracion(Duration.parse("PT1H40M"))
                .precioAlquiler(new BigDecimal("20.50"))
                .periodoAlquiler(Period.of(0,1,15))
                .clasificacion(Clasificacion.R)
                .caracteristicasEspecialesStr("Trailers,Commentaries")
                .ultimaModificacion(new Date())
                .build();

        peliculaRepository.save(pelicula);

        List<Pelicula> peliculaList = peliculaRepository.findAll();

        assertThat(peliculaList.get(0).getTitulo()).isEqualTo("Indiana Jones");

        //Asserts de Duration
        assertThat(peliculaList.get(0).getDuracion().toHours())
                .isEqualTo(1L);
        assertThat(peliculaList.get(0).getDuracion().toMinutesPart())
                .isEqualTo(40L);

        //Asserts de Period
        assertThat(peliculaList.get(0).getPeriodoAlquiler().getMonths()).isEqualTo(1L);
        assertThat(peliculaList.get(0).getPeriodoAlquiler().getDays()).isEqualTo(15L);

    }
}