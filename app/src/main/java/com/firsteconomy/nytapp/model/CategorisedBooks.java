package com.firsteconomy.nytapp.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * Created by Gaurav on 07-09-2018.
 */

public class CategorisedBooks {

    @Embedded
    public BookCategory category;

    @Relation(parentColumn = "list_id", entityColumn = "category_id")
    public List<Book> books;
}
