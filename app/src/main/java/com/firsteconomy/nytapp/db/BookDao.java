package com.firsteconomy.nytapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.firsteconomy.nytapp.model.Book;
import com.firsteconomy.nytapp.model.BookCategory;
import com.firsteconomy.nytapp.model.CategorisedBooks;

import java.util.List;

/**
 * Created by Gaurav on 07-09-2018.
 */

@Dao
public interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertCategories(BookCategory... categories);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertBooks(List<Book> books);

    @Query("SELECT * FROM Book")
    List<Book> getBooks();

    @Query("SELECT * FROM Book")
    LiveData<List<Book>> getBooksAsLiveData();

    @Query("SELECT * FROM BookCategory")
    List<BookCategory> getBookCategories();

    @Query("SELECT * FROM BookCategory")
    List<CategorisedBooks> getCategorisedBooks();

}
