package com.cat201.librarysystem6;

import java.io.*;
import java.util.ArrayList;

public class Library{
    private static Library instance;
    private final ArrayList<Book> books;

    // Default constructor
    public Library(){
        books = new ArrayList<Book>();
    }

    public ArrayList<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public void addBook(Book book, String filename){
        books.add(book);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) { // Append mode
            writer.write(book.formatCSV());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }

    public ArrayList<Book> byTitle(String title)
    {
        ArrayList<Book> searchResult = new ArrayList<Book>();
        for (Book book : books){
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())){
                searchResult.add(book);
            }
        }
        return searchResult;
    }

    public ArrayList<Book> byAuthor(String author)
    {
        ArrayList<Book> searchResult = new ArrayList<Book>();
        for (Book book : books){
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())){
                searchResult.add(book);
            }
        }
        return searchResult;
    }

    public Book byISBN(String isbn)
    {
        for (Book book : books){
            if (book.getISBN().trim().equals(isbn.trim())){
                return book;
            }
        }
        return null;
    }

    public void loadBooks(String csvFilePath) throws IOException {
        books.clear(); // Reset the list of books before reloading

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int year = Integer.parseInt(values[3]); // Convert year to int
                boolean available = values[5].trim().equalsIgnoreCase("true"); // Convert availability to boolean
                if (values.length == 7) {
                    books.add(new Book(values[0].trim(), values[1].trim(), values[2].trim(), year, values[4].trim(), available, values[6].trim()));
                }
            }
        }
    }

    public void writeFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) { // Overwrite mode
            for (Book book : books) {
                writer.write(book.formatCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public void updateBook(Book updatedBook) {
        for(int i=0; i<books.size(); i++){
            Book book = books.get(i);

            if(book.getISBN().equals(updatedBook.getISBN())){
                books.set(i, updatedBook);
                writeFile("src/main/java/com/cat201/librarysystem6/Book.csv");
                return;
            }
        }
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public boolean isBookExists(String isbn) {
        for (Book book : books) {
            if (book.getISBN().equalsIgnoreCase(isbn)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        String total = "\n";
        for (int i=0;i<books.size();i++)
        {
            Book b = books.get(i);
            total += b.toString();
        }
        return total;
    }
}