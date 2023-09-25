package step.learning.OOP;

public class OOPDemo {
    public void run(){
        Library library = new Library() ;
        try {
            library.add(new Book("D. Knuth", "Art of programming", 255));
            library.add(new Newspaper("Daily Mail", "2023-09-25"));
            library.add(new Journal("Quantium Mechanics", 157, 32));
            library.add(new Book("Richter", "Platform .NET", 333));
            library.add(new Newspaper("Washington Post", "2023-09-25"));
            library.add(new Journal("Amogus Spawning", 32, 26));

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
