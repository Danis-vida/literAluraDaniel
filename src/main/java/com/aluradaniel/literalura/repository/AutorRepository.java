package com.aluradaniel.literalura.repository;

import com.aluradaniel.literalura.model.Autor;
import com.aluradaniel.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {
    Autor findByAutorIgnoreCase(String autor);
}
