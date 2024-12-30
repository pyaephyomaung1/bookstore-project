package org.example.bookapi.service;

import org.example.bookapi.dao.*;
import org.example.bookapi.dto.AuthorDTO;
import org.example.bookapi.dto.BookDTO;

import org.example.bookapi.entity.BestSeller;

import org.example.bookapi.entity.NewArrival;
import org.example.bookapi.util.EntityUtils;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookStoreService {
    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final BestSellerDao bestSellerDao;
    private final NewArrivalDao newArrivalDao;


    public BookStoreService(AuthorDao authorDao, BookDao bookDao, BestSellerDao bestSellerDao, NewArrivalDao newArrivalDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
        this.bestSellerDao = bestSellerDao;
        this.newArrivalDao = newArrivalDao;
    }

    public List<BookDTO> getAllBooks() {
        return bookDao.findAll().stream().map(EntityUtils::toBookDTO).collect(Collectors.toList());
    }


    public List<AuthorDTO> getAllAuthors() {
        return authorDao.findAll().stream().map(EntityUtils::toAuthorDTO).collect(Collectors.toList());
    }

    public List<BookDTO> getNewArrivals() {
        List<NewArrival> newArrivals = newArrivalDao.findAll();
        return newArrivals.stream().map(newArrival -> EntityUtils.toBookDTO(newArrival.getBook()) ).collect(Collectors.toList());
    }

    public List<BookDTO> getBestSellers() {
        List<BestSeller> bestSellers = bestSellerDao.findAll();
        return bestSellers.stream().map(bestSeller -> EntityUtils.toBookDTO(bestSeller.getBooks()) ).collect(Collectors.toList());
    }


    public Optional<AuthorDTO> getAuthorByAuthorCode(String authorCode) {
        return authorDao.findByAuthorCode(authorCode).map(EntityUtils::toAuthorDTO);
    }

    public Optional<BookDTO> getBookByTitle(String title) {
        return bookDao.findByTitle(title).map(EntityUtils::toBookDTO);
    }

    public Optional<BookDTO> getBookByID(int id){
        return bookDao.findById(id).map(EntityUtils::toBookDTO);
    }


}
