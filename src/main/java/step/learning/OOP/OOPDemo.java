package step.learning.OOP;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Objects;

public class OOPDemo {
    public void run(){
        Library library = new Library();
        try {
            library.load();
        }
        catch (RuntimeException ex){
            System.err.println(ex.getMessage());
        }
        library.printAllCards();
    }
    public void run3() {

        // JSON - засобами Gson у задачі фабричного типу
        // уявимо, що ми не знаємо тип (книга, газета,...) до того як парсимо рядок
        String str = "{\"author\": \"D. Knuth\", \"title\": \"Art of programming\" }" ;
        // узагальнений парсер - створює JsonObject як ~Map<K,V>
        JsonObject literatureObject = JsonParser.parseString( str ).getAsJsonObject() ;
        Literature literature;
        if( literatureObject.has( "author" ) ) {
            literature = new Book(
                    literatureObject.get("title").getAsString(),
                    literatureObject.get("author").getAsString()
            );
        }
        if( literatureObject.has( "number" ) ) {
            literature = new Journal(
                    literatureObject.get("title").getAsString(),
                    literatureObject.get("number").getAsInt()
            );
        }
        if( literatureObject.has( "date" ) ) {

            try {
                literature = new Newspaper(
                        literatureObject.get("title").getAsString(),
                        literatureObject.get("date").getAsString()
                );
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void run2() {
        // JSON - засобами Gson з відомим типом об'єкту
        Gson gson = new Gson() ;
        String str = "{\"author\": \"D. Knuth\", \"title\": \"Art of programming\" }" ;
        Book book = gson.fromJson( str, Book.class ) ;  // ~ typeof
        System.out.println( book.getCard() ) ;
        // оптимізований рядок - з мінімальною кількістю символів
        System.out.println( gson.toJson( book ) ) ;  // {"author":"D. Knuth","title":"Art of programming"}
        // В оптимізованому режимі поля зі значенням null взагалі не включаються до json
        book.setAuthor( null ) ;
        System.out.println( gson.toJson( book ) ) ;  // {"title":"Art of programming"}

        // Для налаштувань серіалізатора використовується GsonBuilder
        Gson gson2 = new GsonBuilder()
                .setPrettyPrinting()   // додаткові відступи та розриви
                .serializeNulls()      // включати до json поля з null
                .create();
        System.out.println( gson2.toJson( book ) ) ;

        try(
                InputStream bookStream =                            // Одержуємо доступ до ресурсу
                        this.getClass()                             // Оскільки файл копіюється до папки
                                .getClassLoader()                       // з класами, знаходимо її через getClassLoader
                                .getResourceAsStream("book.json");
                InputStreamReader bookReader =                      // Для використання gson.fromJson
                        new InputStreamReader(                      // необхідний Reader, відповідно
                                Objects.requireNonNull( bookStream ) )      // створюємо InputStreamReader
        ) {
            book = gson.fromJson( bookReader, Book.class ) ;
            System.out.println( book.getCard() ) ;
        }
        catch( IOException ex ) {
            System.err.println( ex.getMessage() ) ;
        }
    }
    public void run1(){
        Library library = new Library() ;
        try {
            library.add(new Book("D. Knuth", "Art of programming", 255));
            library.add(new Newspaper("Daily Mail", "2023-09-25"));
            library.add(new Journal("Quantium Mechanics", 157, 32));
            library.add(new Book("Richter", "Platform .NET", 333));
            library.add(new Newspaper("Washington Post", "2023-09-25"));
            library.add(new Journal("Amogus Spawning", 32, 26));
            library.add(new Hologram("Elder Scroll", "Very old scroll", "1011-09-25"));
            library.save();
        }
        catch (Exception ex){
            System.err.println("Literature creation error: " + ex.getMessage());
        }
        System.out.println("----------------All--------------------");
        library.printAllCards() ;
        System.out.println("--------------Copyable-----------------");
        library.printCopyable();
        System.out.println("---------------NonCopyable-------------");
        library.printNonCopyable();
        System.out.println("--------------Periodic-----------------");
        library.printPeriodic();
        System.out.println("---------------NonPeriodic-------------");
        library.printNonPeriodic();
        System.out.println("--------------Printable-----------------");
        library.printPrintable();
        System.out.println("---------------NonPrintable-------------");
        library.printNonPrintable();
        System.out.println("--------------Multiple-----------------");
        library.printMultiple();
        System.out.println("---------------NonMultiple-------------");
        library.printNonMultiple();
        /*
        Библиотека
        Моделируем многохранилище (библиотеку) в котором есть каталог (список имеющихся изданий)
        Издание есть разного типа: книги, газеты, журналы и т.д.
        Каждый тип имеет одинаковую карточку в каталоге
        Абстрагирование
        Литература – термин, сочетающий реальные сущности (книги)
        * */

        /*
        ООП – объектно-ориентированная парадигма программирования
        Программа – управление объектами и их взаимодействием
        Программирование - настройка объектов и связей
        Виды связей:
                - наследование
                - ассоциация
                - композиция
                - агрегация
                - зависимость
       * */
    }
}
