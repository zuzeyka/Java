package step.learning;

import com.google.inject.Guice;
import com.google.inject.Injector;
import step.learning.OOP.OOPDemo;
import step.learning.async.AsyncDemo;
import step.learning.async.TaskDemo;
import step.learning.async.TaskHw;
import step.learning.basics.BasicsDemo;
import step.learning.basics.FilesDemo;
import step.learning.homework.code.Explover;
import step.learning.homework.code.RandomString;
import step.learning.ioc.ConfigModule;
import step.learning.ioc.IocDemo;

import java.util.Scanner;


public class App 
{
    public static void main( String[] args )
    {
        //new BasicsDemo().run();
        //new FilesDemo().run();
        //Explover.ls("./");
        /*
        Scanner kbscaner = new Scanner(System.in);
        System.out.print("Type min length: ");
        int minLength = Integer.parseInt(kbscaner.next()); // Минимальная длина строки
        System.out.print("Type min length: ");
        int maxLength = Integer.parseInt(kbscaner.next()); // Максимальная длина строки
        System.out.println(RandomString.generateString(minLength, maxLength));
        */
        //OOPDemo demo = new OOPDemo();
        //demo.run2();
        //demo.run1();
        //demo.run3();
        //demo.run();
        //Injector injector = Guice.createInjector(new ConfigModule());
        //IocDemo iocDemo = injector.getInstance(IocDemo.class); // вместо new IocDemo();
        //iocDemo.run();
        //Guice.createInjector(new ConfigModule()).getInstance(AsyncDemo.class).run();
        //Guice.createInjector(new ConfigModule()).getInstance(TaskDemo.class).run();
        Guice.createInjector(new ConfigModule()).getInstance(TaskHw.class).run();
    }
}
