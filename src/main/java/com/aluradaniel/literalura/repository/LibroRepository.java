package com.aluradaniel.literalura.repository;

import com.aluradaniel.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
    List<Libro> findAll();
    @Query("SELECT l FROM Libro l WHERE l.idiomas = :idioma")
    List<Libro> librosPorIdioma(String idioma);
//    @Query("SELECT l FROM Libro l WHERE l.autor_id = :Id")
//    @Query("SELECT titulo FROM libros WHERE autor_id = :Id")
//    List<String> librosPorAutor(Long Id);
    @Query(value = "select titulo from libros where autor_id = :Id", nativeQuery = true)
    List<String> librosPorAutor(Long Id);
    @Query(value = "select id from autor where autor = :autor", nativeQuery = true)
    Long buscarIdPorAutor(String autor);
//    @Query("SELECT l FROM Libro l WHERE autor_id = :Id")
//    List<Libro> librosPorAutor(Long Id);
}
