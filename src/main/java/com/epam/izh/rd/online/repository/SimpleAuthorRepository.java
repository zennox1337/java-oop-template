package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            authors = Arrays.copyOf(authors, authors.length + 1);
            authors[authors.length - 1] = author;
            return true;
        }
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author authName : authors) {
            if (authName.getName().equals(name) && authName.getLastName().equals(lastname)) {
                return authName;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (this.findByFullName(author.getName(), author.getLastName()) != null) {
            Author[] copyAuthors = Arrays.copyOf(authors, authors.length);
            authors = new Author[authors.length - 1];
            int count = 0;
            for (Author itemAuthors : copyAuthors) {
                if (!itemAuthors.equals(author)) {
                    authors[count] = itemAuthors;
                    count++;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return authors.length;
    }
}
