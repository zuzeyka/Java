package step.learning.OOP;

public class OOPDemo {
    public void run(){
        Book book = new Book("D. Knuth", "Art of programming");
        Book book1 = new Book("D. Knuth", "Art of programming 2");
        System.out.println(book.getCard());
        Library library = new Library() ;
        library.add( book ) ;
        library.add( book1 ) ;
        library.printAllCards() ;
    }
}
