package com.example.spring5webapp.bootstrap;

import com.example.spring5webapp.model.Author;
import com.example.spring5webapp.model.Book;
import com.example.spring5webapp.model.Publisher;
import com.example.spring5webapp.repositories.AuthorRepository;
import com.example.spring5webapp.repositories.BookRepository;
import com.example.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }
    private void initData()

    {
        Publisher papierowy = new Publisher("Papierowy Ksiezyc", "Zduny dolne 6");

        publisherRepository.save(papierowy);

        Author lewis = new Author("Clive Staples", "Lewis");
        Book narnia = new Book("The Chronicles of Narnia", "480214", papierowy);
        lewis.getBooks().add(narnia);
        narnia.getAuthors().add(lewis);
        narnia.setPublisher(papierowy);

        authorRepository.save(lewis);
        bookRepository.save(narnia);

        Author gromyko = new Author("Olga", "Gromyko");
        Book w_redna = new Book("Zawod Wiedzma cz.1", "482714214", papierowy);
        gromyko.getBooks().add(w_redna);
        w_redna.getAuthors().add(gromyko);

        authorRepository.save(gromyko);
        bookRepository.save(w_redna);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
       initData();
    }
}
