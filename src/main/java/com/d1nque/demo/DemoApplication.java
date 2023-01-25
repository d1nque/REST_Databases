package com.d1nque.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
// mysql script
//        create database bookdb;
//        use bookdb;
//
//        create table category(
//        Id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        CategoryName varchar(30)
//        );
//
//        create table book(
//        Id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        Title VARCHAR(60),
//        Author VARCHAR(60),
//        CategoryId BIGINT,
//        FOREIGN KEY (CategoryId)  REFERENCES Categories (Id)
//        );
//
//        INSERT INTO category (CategoryName)
//        VALUES ('Fiction'), ('Non-Fiction'), ('Science');
//
//        INSERT INTO book (Title, Author, CategoryId)
//        VALUES
//        ('The Lord of the Rings', 'J.R.R. Tolkien', 1),
//        ('The Catcher in the Rye', 'J.D. Salinger', 1),
//        ('The Bible', 'Various', 2),
//        ('The Origin of Species', 'Charles Darwin', 3),
//        ('A Brief History of Time', 'Stephen Hawking', 3),
//        ('The Elegant Universe', 'Brian Greene', 3),
//        ('The Demon-Haunted World', 'Carl Sagan', 3),
//        ('The Double Helix', 'James Watson', 3),
//        ('The Selfish Gene', 'Richard Dawkins', 3),
//        ('The Structure of Scientific Revolutions', 'Thomas Kuhn', 3),
//        ('Thinking, Fast and Slow', 'Daniel Kahneman', 3),
//        ('The Innovators', 'Walter Isaacson', 3),
//        ('The Structure of Scientific Revolutions', 'Thomas S. Kuhn', 3),
//        ('The Black Swan', 'Nassim Nicholas Taleb', 3),
//        ('The God Delusion', 'Richard Dawkins', 3),
//        ('Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 2),
//        ('The Guns, Germs, and Steel', 'Jared Diamond', 2),
//        ('Collapse: How Societies Choose to Fail or Succeed', 'Jared Diamond', 2),
//        ('The Wealth of Nations', 'Adam Smith', 2);



