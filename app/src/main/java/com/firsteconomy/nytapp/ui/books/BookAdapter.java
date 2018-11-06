package com.firsteconomy.nytapp.ui.books;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.firsteconomy.nytapp.databinding.LayoutBookBinding;
import com.firsteconomy.nytapp.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaurav on 04-09-2018.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    //private ArrayList<BookCategory> lists = new ArrayList<>();
    private ArrayList<Book> books = new ArrayList<>();

    public void updateList(List<Book> books) {
        this.books.addAll(books);
        notifyDataSetChanged();
        //for (BookCategory bookCategory : categories) {
        //books.addAll(bookCategory.books);
        //}
        //lists.addAll(categories);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutBookBinding binding = LayoutBookBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BookViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.setBinding(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {

        private LayoutBookBinding binding;

        public BookViewHolder(LayoutBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setBinding(Book book) {
            Glide.with(binding.getRoot().getContext())
                    .load(book.bookImage)
                    .transition(DrawableTransitionOptions.withCrossFade(400))
                    .into(binding.ivBook);
            binding.setBook(book);
            binding.executePendingBindings();
        }
    }
}
