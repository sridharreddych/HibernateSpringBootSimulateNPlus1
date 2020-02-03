package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            System.out.println("\nFetch all books and authors  ...");
            bookstoreService.fetchBooksAndAuthors();

            System.out.println("\nFetch all authors and books  ...");
            bookstoreService.fetchAuthorsAndBooks();
        };
    }
}



/*
 * 
 * 
 * How To Quickly Reproduce The N+1 Performance Issue

Description: The N+1 is an issue of lazy fetching (but, eager is not exempt). This application reproduce the N+1 behavior.

Key points:

define two entities, Author and Book in a lazy bidirectional @OneToMany association
fetch all Book lazy, so without Author (results in 1 query)
loop the fetched Book collection and for each entry fetch the corresponding Author (results N queries)
or, fetch all Author lazy, so without Book (results in 1 query)
loop the fetched Author collection and for each entry fetch the corresponding Book (results N queries)
Output example:
 * 
 * 
 * 
 * 
 * 
 * 
 */
