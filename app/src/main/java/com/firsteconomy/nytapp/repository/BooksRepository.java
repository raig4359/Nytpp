package com.firsteconomy.nytapp.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.firsteconomy.nytapp.db.BookDao;
import com.firsteconomy.nytapp.db.DBManager;
import com.firsteconomy.nytapp.model.Book;
import com.firsteconomy.nytapp.model.BookCategory;
import com.firsteconomy.nytapp.network.ApiResponse;
import com.firsteconomy.nytapp.network.NetworkBoundResource;
import com.firsteconomy.nytapp.network.Resource;
import com.firsteconomy.nytapp.network.RestClient;
import com.firsteconomy.nytapp.network_responses.OverviewBookListResponse;

import java.util.List;

/**
 * Created by Gaurav on 05-09-2018.
 */

public class BooksRepository {

    Context context;
    private String TAG = "BookRepo";

    public BooksRepository(Context context) {
        this.context = context;
    }

    public LiveData<Resource<List<Book>>> getBookListOverview() {
        return new NetworkBoundResource<List<Book>, OverviewBookListResponse>() {

            @Override
            protected void saveCallResult(OverviewBookListResponse item) {
                BookDao bookDao = DBManager.getDbManager()
                        .getDatabase(context)
                        .bookDao();
                for (BookCategory bookCategory : item.results.bookCategories) {
                    bookDao.insertCategories(bookCategory);
                    for (Book book : bookCategory.books) {
                        book.categoryId = bookCategory.listId;
                    }
                    bookDao.insertBooks(bookCategory.books);
                }
            }

            @Override
            protected boolean shouldFetch(List<Book> item) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Book>> loadFromDb() {
                final BookDao bookDao = DBManager.getDbManager()
                        .getDatabase(context)
                        .bookDao();
                return bookDao.getBooksAsLiveData();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<OverviewBookListResponse>> createCall() {
                return RestClient.webServices().getOverviewBookList();
            }
        }.getAsLiveData();
    }

}
