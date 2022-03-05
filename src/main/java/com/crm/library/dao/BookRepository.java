package com.crm.library.dao;

import com.crm.library.entity.Book;
import com.crm.library.util.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByStatus(Status status);

}
