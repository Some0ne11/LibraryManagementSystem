package com.cat201.librarysystem6;
public class Book{
    private final String isbn;
    private final String title;
    private final String author;
    private final int year;
    private final String publisher;
    private boolean availability;
    private String borrowerName;

    // Default Constructor
    public Book(){
        this.isbn = "";
        this.title = "";
        this.author = "";
        this.year = 0;
        this.publisher = "";
        this.availability = true;
        this.borrowerName = "null";
    }

    // Constructor
    public Book(String isbn, String title, String author, int year, String publisher,boolean availability, String borrowerName){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.publisher = publisher;
        this.availability = availability;
        this.borrowerName = borrowerName;
    }

    public void borrowBook(String borrowerName){
        if (borrowerName == null || borrowerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Borrower name cannot be empty or null.");
        }
        if(availability){
            this.availability = false;
            this.borrowerName = borrowerName.trim();
        }
        else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook(){
        this.availability = true;
        this.borrowerName = "null";
    }

    public String formatCSV(){
        return isbn + "," + title + "," + author + "," + year + "," + publisher +","+ availability + "," + borrowerName;
    }
    public String toString(){
        return "Title: "+ title + "\nAuthor: "+author+"\nISBN: "+isbn+"\nAvailability: "+availability;
    }

    // Getters
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getISBN(){
        return isbn;
    }
    public int getYear(){
        return year;
    }
    public String getPublisher(){
        return publisher;
    }
    public boolean isAvailable(){
        return availability;
    }
    public String getBorrowerName(){
        return borrowerName;
    }

}