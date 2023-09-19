package step.learning;

import step.learning.basics.BasicsDemo;
import step.learning.basics.FilesDemo;
import step.learning.homework.code.Explover;


public class App 
{
    public static void main( String[] args )
    {
        new BasicsDemo().run();
        new FilesDemo().run();
        Explover.ls("./");
    }
}
