package com.aluradaniel.literalura.principal;

import com.aluradaniel.literalura.model.Autor;
import com.aluradaniel.literalura.model.DatosAutor;
import com.aluradaniel.literalura.model.Libro;
import com.aluradaniel.literalura.model.RLibro;
import com.aluradaniel.literalura.repository.AutorRepository;
import com.aluradaniel.literalura.repository.LibroRepository;
import com.aluradaniel.literalura.service.ConsumoAPI;
import com.aluradaniel.literalura.service.ConvierteDatos;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    //Para buscar datos: https://gutendex.com/books?search=Romeo%20and%20Juliet
    private ConvierteDatos conversor = new ConvierteDatos();
    private AutorRepository autorRepositorio;
    private LibroRepository libroRepositorio;
    private List<Libro> libros;
    private List<Autor> autores;

    public Principal(AutorRepository autorRepositorio, LibroRepository libroRepositorio) {
        this.autorRepositorio = autorRepositorio;
        this.libroRepositorio = libroRepositorio;
    }

    public void inicio() {
        int eleccion=12;
        String menu = """
                1.- Buscar libro por titulo
                2.- Listar libros registrados
                3.- Listar autores
                4.- Listar autores vivos en determinado año
                5.- Listar libros por idioma
                0.- Salir
                """;
        while(eleccion!=0){
            System.out.println(menu);
            eleccion = teclado.nextInt();
            teclado.nextLine();
            switch (eleccion){
                case 1:
                    buscarLibrosPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    ListarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                default:
                    System.out.println("Opcion no disponible");
                    break;
            }
        }
    }
    private RLibro getLibro(){
        System.out.println("Escribe el nombre del titulo que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
        System.out.println(URL_BASE + nombreLibro.replace(" ", "%20"));
        System.out.println("Este es mi valor: " + json);
        RLibro DatosLibro = conversor.obtenerDatos(json, RLibro.class);
        return DatosLibro;
    }

    private void buscarLibrosPorTitulo() {
        RLibro datos = getLibro();
        Libro libro = new Libro(datos);
        List<DatosAutor> datosAutor = datos
                .libros()
                .get(0)
                .autor();
        Autor autor = new Autor(datosAutor);
        mostrarLibrosBuscados();
        Optional<Libro> libroBuscado = libros.stream()
                .filter(s->s.getTitulo().toLowerCase()
                        .contains(libro.getTitulo().toLowerCase())).findFirst();
        String resultado = """
                    -------- Libro ---------
                    Título: """+ libro.getTitulo() +"""
                    
                    Autor: """+ autor.getAutor() +"""
                    
                    Idioma: """+ libro.getIdiomas() +"""
                    
                    N° de descargas: """+ libro.getNumeroDeDescargas() +"""
                    
                    -----------------------
                    """;
        if (libroBuscado.isPresent()){
//            System.out.println(autor);
//            System.out.println(libro);
            System.out.println(resultado);
        }else {
            Autor autorBuscado = autorRepositorio.findByAutorIgnoreCase("Austen, Jane");
            if (!autorBuscado.getAutor().equals(autor.getAutor())){
                autorRepositorio.save(autor);
            }
            else {
                autor = autorBuscado;
            }
            libro.setAutor(autor);
            libroRepositorio.save(libro);
            System.out.println(resultado);
//           Optional<Autor> autorBuscado = autorRepositorio.findAll().stream()
//                   .filter(s->s.getAutor()
//                           .toLowerCase()
//                           .contains(autor.getAutor().toLowerCase()))
//                   .findFirst();
//            if(autorBuscado.isEmpty()){
//                autorRepositorio.save(autor);
//            }
//            libro.setAutor(autor);
//            libroRepositorio.save(libro);
        }
    }

    private void listarLibrosRegistrados() {
        //libros = libroRepositorio.findAll();
        mostrarAutoresBuscados();
        mostrarLibrosBuscados();

        libros.stream().forEach(l-> System.out.println("""
                    -------- Libro ---------
                    Título: """+ l.getTitulo() +"""
                    
                    Autor: """+ l.getAutor() +"""
                    
                    Idioma: """+ l.getIdiomas() +"""
                    
                    N° de descargas: """+ l.getNumeroDeDescargas() +"""
                    
                    -----------------------
                    """));
//        autores.stream().forEach(a-> System.out.println("""
//                    -------- Autor ---------
//                    Autor: """+ a.getAutor() +"""
//
//                    Autor: """+ a.getLibro() +"""
//
//                    Idioma: """+ a.getLibro().get(0).getIdiomas() +"""
//
//                    N° de descargas: """+ a.getLibro().get(0).getNumeroDeDescargas() +"""
//
//                    -----------------------
//                    """));
    }

    private void ListarAutores() {
        mostrarAutoresBuscados();

        autores.stream().forEach(a-> System.out.println("""
                    -------- Autor ---------
                    Autor: """+ a.getAutor() +"""

                    Fecha de nacimiento: """+ a.getFechaDeNacimiento() +"""

                    Fecha de muerte: """+ a.getFechaDeMuerte() +"""

                    Libros: """+ libroRepositorio.librosPorAutor(libroRepositorio.buscarIdPorAutor(a.getAutor())) +"""

                    -----------------------
                    """));
    }
    private void listarAutoresVivos() {
        System.out.println("Ingresa la fecha del año en que seguian vivos los autores");
        int fecha = teclado.nextInt();
        teclado.nextLine();

        mostrarAutoresBuscados();

        List<Autor> autorVivo = autores.stream().filter(a -> Integer.parseInt(a.getFechaDeNacimiento()) <= fecha
                && fecha <= Integer.parseInt(a.getFechaDeMuerte()))
                .collect(Collectors.toList());

        autorVivo.stream().forEach(a-> System.out.println("""
                    -------- Autor ---------
                    Autor: """+ a.getAutor() +"""

                    Fecha de nacimiento: """+ a.getFechaDeNacimiento() +"""

                    Fecha de muerte: """+ a.getFechaDeMuerte() +"""

                    Libros: """+ libroRepositorio.librosPorAutor(libroRepositorio.buscarIdPorAutor(a.getAutor())) +"""

                    -----------------------
                    """));

    }
    private void listarLibrosPorIdioma() {
        mostrarLibrosBuscados();
        mostrarAutoresBuscados();
        System.out.println("Elige el idioma del libro, por ejemplo es o en");
        String idioma = teclado.nextLine();
        List<Libro> librosPorIdioma=libroRepositorio.librosPorIdioma(idioma);
//        List<Libro> librosPorIdioma= libros.stream()
//                .filter(i->i.getIdiomas()
//                        .toLowerCase().contains(idioma.toLowerCase()))
//                .collect(Collectors.toList())
//                .findAll();
        librosPorIdioma.forEach(l-> System.out.println("""
                    -------- Libro ---------
                    Título: """+ l.getTitulo() +"""
                    
                    Autor: """+ l.getAutor().getAutor() +"""
                    
                    Idioma: """+ l.getIdiomas() +"""
                    
                    N° de descargas: """+ l.getNumeroDeDescargas() +"""
                    
                    -----------------------
                    """));
    }

    private void mostrarLibrosBuscados() {
        libros = libroRepositorio.findAll();
    }
    private void mostrarAutoresBuscados() {
        autores = autorRepositorio.findAll();
    }
}
