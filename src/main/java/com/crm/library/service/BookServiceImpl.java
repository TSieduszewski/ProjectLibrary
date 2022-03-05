package com.crm.library.service;

import com.crm.library.dao.BookRepository;
import com.crm.library.entity.Book;
import com.crm.library.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAllWithStatus(Status status) {
        return bookRepository.findAllByStatus(status);
    }

    @Override
    public Book findById(int id) {
        Optional<Book> book = bookRepository.findById(id);

        Book tempBook;

        if(book.isPresent()){
            tempBook = book.get();
        } else {
            throw new RuntimeException("Nie znalazłem książki");
        }
        return tempBook;
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(int id) {
        bookRepository.deleteById(id);
    }
}
