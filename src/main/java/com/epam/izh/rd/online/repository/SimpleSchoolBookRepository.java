package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        for (SchoolBook book : schoolBooks) {
            if (book.getName().equals(name)) {
                return schoolBooks;
            }
        }
        return new SchoolBook[0];
    }

    @Override
    public boolean removeByName(String name) {
        int countRemoveByName = this.findByName(name).length;
        if (countRemoveByName > 0) {
            SchoolBook[] copySchoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length);
            schoolBooks = new SchoolBook[schoolBooks.length - countRemoveByName];
            int count = 0;
            for (SchoolBook itemSchoolBooks : copySchoolBooks) {
                if (!itemSchoolBooks.getName().equals(name)) {
                    schoolBooks[count] = itemSchoolBooks;
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
        return schoolBooks.length;
    }
}
