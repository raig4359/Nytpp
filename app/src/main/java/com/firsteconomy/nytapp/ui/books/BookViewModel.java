package com.firsteconomy.nytapp.ui.books;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import com.firsteconomy.nytapp.model.Book;
import com.firsteconomy.nytapp.model.BookCategory;
import com.firsteconomy.nytapp.network.Resource;
import com.firsteconomy.nytapp.repository.BooksRepository;

import java.util.List;

/**
 * Created by Gaurav on 05-09-2018.
 */

public class BookViewModel extends ViewModel {

    private BooksRepository booksRepository;
    private LiveData<Resource<List<Book>>> bookOverviewList;

    public BookViewModel(final BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public LiveData<Resource<List<Book>>> getBookOverviewList() {
        if (bookOverviewList == null) {
            bookOverviewList = booksRepository.getBookListOverview();
        }
        return bookOverviewList;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory{

        private final Context context;
        private final BooksRepository booksRepository;

        public Factory(Context context) {
            this.context = context;
            this.booksRepository = new BooksRepository(context);
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new BookViewModel(booksRepository);
        }
    }
}
