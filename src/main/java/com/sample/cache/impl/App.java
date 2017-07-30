package com.sample.cache.impl;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheStats;
import com.sample.cache.model.Book;
import com.sample.cache.service.BookService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, ExecutionException {
        Book book = BookService.getBookDetails("9780596009205").get();
        System.out.println(Util.getObjectMapper().writeValueAsString(book));
        book = BookService.getBookDetails("9780596009205").get();
        book = BookService.getBookDetails("9780596009205").get();
        book = BookService.getBookDetails("9780596009205").get();
        book = BookService.getBookDetails("9780596009205").get();
        CacheStats cacheStats = BookService.getCacheStats();
        System.out.println(cacheStats.toString());
    }
}
