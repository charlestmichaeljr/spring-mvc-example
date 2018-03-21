package com.charlie.spring5webapp.bootstrap;

import com.charlie.spring5webapp.model.Author;
import com.charlie.spring5webapp.model.Book;
import com.charlie.spring5webapp.model.Publisher;
import com.charlie.spring5webapp.repositories.AuthorRepository;
import com.charlie.spring5webapp.repositories.BookRepository;
import com.charlie.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(BookRepository bookRepository, AuthorRepository authorRepository,PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {

        Author aaMilne = new Author("A.A.","Milne");
        Publisher dunderMifflin = new Publisher("Dunder Mifflin", "123 Main St.", "Chilicothe","Ohio","12345");
        Book winnieThePooh = new Book("WinnieThePooh","123fn45",dunderMifflin);
        Book theHouse = new Book("The House at Pooh Corner", "38749765",dunderMifflin);
        aaMilne.getBooks().add(winnieThePooh);
        aaMilne.getBooks().add(theHouse);
        winnieThePooh.getAuthors().add(aaMilne);
        theHouse.getAuthors().add(aaMilne);

        authorRepository.save(aaMilne);
        publisherRepository.save(dunderMifflin);
        bookRepository.save(winnieThePooh);
        bookRepository.save(theHouse);

        Author billClinton = new Author("Bill","Clinton");
        Publisher whitewaterPublishing = new Publisher("White Water Publishing", "200 Bubba St", "Fayetteville","Arkansas","20909");
        Book myLife = new Book("My Life","96875426555898",whitewaterPublishing);
        billClinton.getBooks().add(myLife);
        myLife.getAuthors().add(billClinton);

        authorRepository.save(billClinton);
        publisherRepository.save(whitewaterPublishing);
        bookRepository.save(myLife);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
