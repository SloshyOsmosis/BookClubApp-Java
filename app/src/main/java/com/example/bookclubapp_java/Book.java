package com.example.bookclubapp_java;

public class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private String ISBN;
    private String status;

    public Book(int id, String title, String author, String genre, String ISBN, String status){
        this.id = String.valueOf(id);
        this.title = String.valueOf(title);
        this.author = String.valueOf(author);
        this.genre = String.valueOf(genre);
        this.ISBN = String.valueOf(ISBN);
        this.status = String.valueOf(status);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return ISBN;
    }

    public void setIsbn(String isbn) {
        this.ISBN = isbn;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LibraryBook(" + "id = " + id + ", title = " + title + ", author = " + author + ", genre = " + genre + ", ISBN = " + ISBN + ", status = " + status +")";
    }
}
