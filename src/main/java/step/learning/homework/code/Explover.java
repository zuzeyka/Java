package step.learning.homework.code;

import java.io.File;
import java.text.SimpleDateFormat;

public class Explover {
    public static void ls(String dir){
        File directory = new File(dir);
        if (directory.exists() && directory.isDirectory()) {
            File[] filesAndDirectories = directory.listFiles();
            if (filesAndDirectories != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                System.out.println("Directory content: " + directory.getAbsolutePath());
                System.out.printf("%-40s %-12s %-20s%n", "Name", "Type", "Last changed");
                System.out.println("------------------------------------------------------------");

                for (File file : filesAndDirectories) {
                    String name = file.getName();
                    String type = file.isDirectory() ? "Folder" : "File";
                    String modificationDate = dateFormat.format(file.lastModified());

                    System.out.printf("%-40s %-12s %-20s%n", name, type, modificationDate);
                }
            } else {
                System.out.println("Path is empty.");
            }
        } else {
            System.err.println("Path does not exist.");
        }
    }
}
