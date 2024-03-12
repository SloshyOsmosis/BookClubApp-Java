package com.example.bookclubapp_java;

public class Books {
    private int bookID;
    private String bookName;
    private String bookAuthor;
    private String bookGenre;
    private String bookISBN;
    private String bookPublisher;

    public String getBookName(){
        return bookName;
    }
    public void setBookName(String bookName){
        this.bookName = bookName;
    }

    public String getBookAuthor(){
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor){
        this.bookAuthor = bookAuthor;
    }

    public String getBookGenre(){
        return bookGenre;
    }

    public void setBookGenre(String bookGenre){
        this.bookGenre = bookGenre;
    }

    public String getBookISBN(){return bookISBN; }
    public void setBookISBN(String bookISBN){this.bookISBN = bookISBN; }

    public String getBookPublisher(){return bookPublisher; }
    public void setBookPublisher(String bookPublisher){this.bookPublisher = bookPublisher; }

    public int getBookID() {return bookID;}
    public void setBookID(int bookID) {this.bookID = bookID; }

    public Books(String bookName,
                 String bookAuthor,
                 String bookGenre,
                 String bookISBN,
                 String bookPublisher){
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;
        this.bookISBN = bookISBN;
        this.bookPublisher = bookPublisher;
    }
}
