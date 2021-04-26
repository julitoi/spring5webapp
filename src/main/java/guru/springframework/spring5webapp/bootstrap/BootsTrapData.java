package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootsTrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootsTrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "29348234");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noejb = new Book("J2EE without EJB", "97342934");
        rod.getBooks().add(noejb);
        noejb.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noejb);

        Publisher hanser = new Publisher("Hanser", "Hauptstraße 1", "Vienna", "Vienna", "1010");

        noejb.setPublisher(hanser);
        hanser.getBooks().add(noejb);
        ddd.setPublisher(hanser);
        hanser.getBooks().add(ddd);

        publisherRepository.save(hanser);
        bookRepository.save(ddd);
        bookRepository.save(noejb);

        System.out.println("All publishers: " + publisherRepository.findAll());
        System.out.println("Number of books of Hanser: " + hanser.getBooks().size());
        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
    }
}
