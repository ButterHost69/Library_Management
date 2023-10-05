package com.example.librarymanagement.Logic;

public class Faculty extends LibraryMember{
    public Faculty(String memberId, String name, String password, String type) {
        super(memberId, name, password, type);
    }

    public Faculty(){
        super();
    }
    @Override
    public void borrowBook() {

    }

    @Override
    public void returnBook() {

    }
}
