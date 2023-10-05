package com.example.librarymanagement.Logic;

public class Student extends LibraryMember {


    public Student(String memberId, String name, String password, String type) {
        super(memberId, name, password, type);
    }

    public Student(){
        super();

    }

    @Override
    public void borrowBook() {

    }

    @Override
    public void returnBook() {

    }
}
