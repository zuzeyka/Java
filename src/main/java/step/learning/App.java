package step.learning;

import step.learning.OOP.OOPDemo;
import step.learning.basics.BasicsDemo;
import step.learning.basics.FilesDemo;
import step.learning.homework.code.Explover;
import step.learning.homework.code.RandomString;

import java.util.Scanner;


public class App 
{
    public static void main( String[] args )
    {
        //new BasicsDemo().run();
        //new FilesDemo().run();
        //Explover.ls("./");
        Scanner kbscaner = new Scanner(System.in);
        System.out.print("Type min length: ");
        int minLength = Integer.parseInt(kbscaner.next()); // Минимальная длина строки
        System.out.print("Type min length: ");
        int maxLength = Integer.parseInt(kbscaner.next()); // Максимальная длина строки
        System.out.println(RandomString.generateString(minLength, maxLength));
        new OOPDemo().run();
    }
}
