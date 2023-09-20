package step.learning.basics;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class FilesDemo {
    public void run(){
        String filename = "test.txt";
        Path path = Paths.get(filename);
        try(OutputStream writer = Files.newOutputStream(path)) {
            writer.write("Hello, World!".getBytes());
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write("\nNew Line");
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
        StringBuilder sb = new StringBuilder();

        try(InputStream reader = Files.newInputStream(path)) {
            int c; // symbol to read
            while ((c = reader.read()) != -1){ // -1 -- End of file EOF
                sb.append((char) c);
            }
            System.out.println(sb.toString());
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream(4096);
        byte[] buf = new byte[512];
        try (InputStream reader = new BufferedInputStream(
                Files.newInputStream(path)
        )) {
            int cnt;
            while ((cnt = reader.read(buf)) > 0){
                byteBuilder.write(buf, 0, cnt);
            }
            String content = new String(
                    byteBuilder.toByteArray(),
                    StandardCharsets.UTF_16
            );
            System.out.println(content);
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
        System.out.println("-------------------------------------------");
        try (InputStream reader = Files.newInputStream(path);
             Scanner scanner = new Scanner(reader)) {
            while (scanner.hasNext()){
                System.out.println(scanner.next());
            }
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
        Scanner kbscaner = new Scanner(System.in);
        System.out.print("Your name: ");
        String name = kbscaner.next();
        System.out.printf("Hello, %s%n", name);

    }
    public void run2(){
        File dir = new File("./uploads");
        if (dir.exists()){
            if(dir.isDirectory())
            {
                System.out.printf("'%s' already exists%n", dir.getName());
            }
            else{
                System.out.printf("'%s' already exists BUT NOT AS DIRECTORY%n", dir.getName());
            }
        }
        else{
            if (dir.mkdir()){
                System.out.printf("Directory %s created%n", dir.getName());
            }
            else{
                System.out.printf("Directory %s already exists%n", dir.getName());
            }
        }
        File file = new File("./uploads/whitelist.txt");
        if (file.exists()){
            if(dir.isFile())
            {
                System.out.printf("'%s' already exists%n", dir.getName());
            }
            else{
                System.out.printf("'%s' already exists BUT NOT AS FILE%n", dir.getName());
            }
        }
        else{
            try {
                if(file.createNewFile()){
                    System.out.printf("File %s created%n", dir.getName());
                }
                else{
                    System.out.printf("File %s creation error%n", dir.getName());
                }
            }
            catch (IOException ex){
                System.err.println(ex.getMessage());
            }
        }
    }
    public void run1(){
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
