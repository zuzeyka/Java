package step.learning.OOP;

import java.util.LinkedList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new LinkedList<>();
    }

    public void add(Book book){
        books.add(book);
    }

    public void printAllCards(){
        for (int i = 0; i < books.size(); i++){
            System.out.println(books.get(i).getCard());
        }
    }
}
