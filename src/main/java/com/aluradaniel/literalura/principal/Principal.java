package com.aluradaniel.literalura.principal;

import com.aluradaniel.literalura.model.Libro;
import com.aluradaniel.literalura.service.ConsumoAPI;
import com.aluradaniel.literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    //Para buscar datos: https://gutendex.com/books?search=Romeo%20and%20Juliet
    private ConvierteDatos conversor = new ConvierteDatos();
    //private LibroRepository repositorio;

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
//    private Libro getLibro(){
//        System.out.println("Escribe el nombre de la serie que deseas buscar");
//        var nombreSerie = teclado.nextLine();
//        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
//        System.out.println(json);
//        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
//        return datos;
//    }
    private void buscarLibrosPorTitulo() {
    }
    private void listarLibrosRegistrados() {
    }
    private void ListarAutores() {
    }
    private void listarAutoresVivos() {
    }
    private void listarLibrosPorIdioma() {
    }


}
