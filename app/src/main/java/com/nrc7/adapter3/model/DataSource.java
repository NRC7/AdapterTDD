package com.nrc7.adapter3.model;

import java.util.ArrayList;
import java.util.List;

// Fuente de datos de prueba (Api, DB)
public class DataSource {

    public DataSource() {
    }

    // Devuelve una lista de Book estatica para pruebas
    public List<Book> getBooks() {
        // List<> o Map<> = response.body();
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Book book = new Book();
            book.setName("Libro " + i);
            book.setAuthor("Author " + i);
            books.add(book);
        }
        return books;
    }
}
