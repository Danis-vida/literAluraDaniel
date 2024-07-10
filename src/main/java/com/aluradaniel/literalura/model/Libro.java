package com.aluradaniel.literalura.model;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
//    private String autor;
//    private String fechaNacimiento;
//    private String fechaMuerte;
    private String idiomas;
    private Double numeroDeDescargas;
    @ManyToOne
    private Autor autor;

    public Libro(){}

    public Libro(RLibro rLibro){
        this.titulo = rLibro.libros().get(0).titulo();
//        this.autor = rLibro.libros().get(0).autor().get(0).nombre();
//        this.fechaNacimiento = rLibro.libros().get(0).autor().get(0).fechaDeNacimiento();
//        this.fechaMuerte = rLibro.libros().get(0).autor().get(0).fechaDeMuerte();
        this.idiomas = rLibro.libros().get(0).idiomas().get(0);
        this.numeroDeDescargas = rLibro.libros().get(0).numeroDeDescargas();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    //
//    public String getFechaNacimiento() {
//        return fechaNacimiento;
//    }
//
//    public void setFechaNacimiento(String fechaNacimiento) {
//        this.fechaNacimiento = fechaNacimiento;
//    }
//
//    public String getFechaMuerte() {
//        return fechaMuerte;
//    }
//
//    public void setFechaMuerte(String fechaMuerte) {
//        this.fechaMuerte = fechaMuerte;
//    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
//                ", autor='" + autor + '\'' +
//                ", fechaNacimiento='" + fechaNacimiento + '\'' +
//                ", fechaMuerte='" + fechaMuerte + '\'' +
                ", idiomas='" + idiomas + '\'' +
                ", numeroDeDescargas=" + numeroDeDescargas +
                '}';
    }
}
