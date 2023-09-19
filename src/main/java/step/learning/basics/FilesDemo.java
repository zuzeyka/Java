package step.learning.basics;

import java.io.File;

public class FilesDemo {
    public void run(){
        File dir = new File("./");
        if(dir.exists()){
            System.out.println("Path exists");
        }
        else{
            System.out.println("Path does not exist");
        }
        System.out.printf("Path is %s %n",
                dir.isDirectory() ? "directory" : "file");
        System.out.println(dir.getAbsolutePath());
        for ( String filename : dir.list()){ // dir.list() - только имена (String)
            System.out.println( filename); // dir.listFiles() - объекты (File)
        }
    }
}
