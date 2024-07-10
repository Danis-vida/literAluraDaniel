package com.aluradaniel.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String fechaDeNacimiento;
    private String fechaDeMuerte;
    @Column(unique = true)
    private  String autor;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libro;

    public Autor(){}

    public Autor(List<DatosAutor> datosAutor){
        fechaDeNacimiento = datosAutor.get(0).fechaDeNacimiento();
        fechaDeMuerte = datosAutor.get(0).fechaDeMuerte();
        autor = datosAutor.get(0).nombre();
    }

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        libro.forEach(e->e.setAutor(this));
        this.libro = libro;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(String fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "fechaDeNacimiento='" + fechaDeNacimiento + '\'' +
                ", fechaDeMuerte='" + fechaDeMuerte + '\'' +
                ", autor='" + autor + '\'' +
                ", libro=" + libro +
                '}';
    }
}
