package com.crm.library.service;


import com.crm.library.entity.Book;
import com.crm.library.util.Status;

import java.util.List;

public interface BookService {

    List<Book> findAllWithStatus(Status status);

    Book findById(int id);

    void save(Book book);

    void delete(int id);
}
