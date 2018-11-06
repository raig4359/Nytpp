package com.firsteconomy.nytapp.ui.books;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firsteconomy.nytapp.databinding.FragmentBookBinding;
import com.firsteconomy.nytapp.model.Book;
import com.firsteconomy.nytapp.network.Resource;
import com.firsteconomy.nytapp.ui.common.RetryCallback;

import java.util.List;


public class BookFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private BookInteraction mListener;
    private FragmentBookBinding bookBinding;
    private BookViewModel bookViewModel;
    private BookAdapter bookAdapter;
    private Observer<Resource<List<Book>>> observer;

    public BookFragment() {
        // Required empty public constructor
    }

    public static BookFragment newInstance(String param1, String param2) {
        BookFragment fragment = new BookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        BookViewModel.Factory factory = new BookViewModel.Factory(getContext());
        bookViewModel = ViewModelProviders.of(this, factory).get(BookViewModel.class);
        initObserver();
    }

    private void initObserver() {
        observer = new Observer<Resource<List<Book>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<Book>> listResource) {
                List<Book> books = listResource.data == null ? null :
                        listResource.data.size() == 0 ? null : listResource.data;
                bookBinding.setBooksOverviewResource(listResource);
                bookBinding.executePendingBindings();
                if (listResource.data != null)
                    bookAdapter.updateList(listResource.data);
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bookBinding = FragmentBookBinding.inflate(inflater, container, false);
        bookBinding.setRetryCallback(new RetryCallback() {
            @Override
            public void retry() {
                bookViewModel.getBookOverviewList().observe(BookFragment.this, observer);
            }
        });
        return bookBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bookAdapter = new BookAdapter();
        bookBinding.rvBooks.setAdapter(bookAdapter);
        bookViewModel.getBookOverviewList().observe(this, observer);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BookInteraction) {
            mListener = (BookInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement BookInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface BookInteraction {
    }
}
