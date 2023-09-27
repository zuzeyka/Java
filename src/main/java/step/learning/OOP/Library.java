package step.learning.OOP;

import com.google.gson.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Library {
    private final List<Literature> funds;
    

    public Library() {
        funds = new LinkedList<>();
    }

    public void add(Literature literature){
        funds.add(literature);
    }

    public void save() throws IOException {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();
        FileWriter writer = new FileWriter( "./src/main/resources/library.json" ) ;
        writer.write( gson.toJson( this.getSerialazibleFunds() ) ) ;
        writer.close() ;
    }

    public void load() throws RuntimeException {
        try (InputStreamReader reader = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("library.json")))
                 {
            this.funds.clear();
            for (JsonElement item : JsonParser.parseReader(reader).getAsJsonArray()){
                this.funds.add(
                        this.fromJson(item.getAsJsonObject())
                );
            }
        }
        catch (IOException | ParseException ex){
            throw new RuntimeException(ex);
        }
        catch (NullPointerException ignored){
            throw new RuntimeException("Resourse not found");
        }
    }

    private Literature fromJson( JsonObject jsonObject ) throws ParseException {
        List<Class<?>> literatures = this.getSerialazibleClasses();
        String className = Literature.class.getName();
        String packageName = className.substring(0, className.lastIndexOf('.'));
        String packagePath = packageName.replace(".", "/");
        String absolutePath = Literature.class.getClassLoader().getResource(packagePath).getPath();

        File[] files = new File (absolutePath).listFiles();
        if(files == null){
            throw new ParseException("Class path inaccessible", 0);
        }
        for(File file : files){
            if(file.isFile()){
                String fileName = file.getName();
                if(fileName.endsWith(".class")){
                    String fileClassName = fileName.substring(0, fileName.lastIndexOf('.'));
                    try {
                        Class<?> fileClass = Class.forName(packageName + "." + fileClassName);
                        if (fileClass.isAnnotationPresent(Serializable.class)){
                            literatures.add(fileClass);
                        }
                    } catch (ClassNotFoundException ignored) {
                        continue;
                    }
                }
            }
            else if (file.isDirectory()){
                continue;
            }
        }
        // Class<?>[] literatures = {Book.class, Journal.class, Newspaper.class};
        try {
            for (Class<?> literature : literatures) {
                Method isParseableFromJson = literature.getDeclaredMethod("isParseableFromJson", JsonObject.class);
                for ( Method method : literature.getDeclaredMethods()){
                    if (method.isAnnotationPresent(ParseChecker.class)){
                        if (isParseableFromJson != null){
                            isParseableFromJson = method;
                        }
                    }
                }
                if (isParseableFromJson == null){
                    continue;
                }
                isParseableFromJson.setAccessible(true);
                boolean res = (boolean) isParseableFromJson.invoke(null, jsonObject);
                if (res) {
                    Method fromJson = literature.getDeclaredMethod("fromJson", JsonObject.class);
                    fromJson.setAccessible(true);
                    return (Literature) fromJson.invoke(null, jsonObject);
                }
            }
        } catch (Exception ex) {
            throw new ParseException("Reflection error: " + ex.getMessage(), 0);
        }
        throw new ParseException( "Literature type unrecognized", 0 ) ;
    }

    private List<Literature> getSerialazibleFunds(){
        List<Literature> serializableFunds = new ArrayList<>();
        for ( Literature literature : getFunds()){
            if (literature.getClass().isAnnotationPresent(Serializable.class)){
                serializableFunds.add(literature);
            }
        }
        return serializableFunds;
    }

    private List<Class<?>> getSerialazibleClasses(){
        List<Class<?>> literatures = new LinkedList<>();
        String className = Literature.class.getName();
        String packageName = className.substring(0, className.lastIndexOf('.'));
        String packagePath = packageName.replace(".", "/");
        String absolutePath = Literature.class.getClassLoader().getResource(packagePath).getPath();

        File[] files = new File (absolutePath).listFiles();
        if(files == null){
            throw new RuntimeException("Class path inaccessible");
        }
        for(File file : files){
            if(file.isFile()){
                String fileName = file.getName();
                if(fileName.endsWith(".class")){
                    String fileClassName = fileName.substring(0, fileName.lastIndexOf('.'));
                    try {
                        Class<?> fileClass = Class.forName(packageName + "." + fileClassName);
                        if (fileClass.isAnnotationPresent(Serializable.class)){
                            literatures.add(fileClass);
                        }
                    } catch (ClassNotFoundException ignored) {
                        continue;
                    }
                }
            }
            else if (file.isDirectory()){
                continue;
            }
        }
        return literatures;
    }


    
    public void printAllCards(){
        for (Literature literature : funds){
            System.out.println(literature.getCard());
        }
    }

    public void printCopyable(){
        for( Literature literature : funds){
            if(isCopyable( literature)){
                System.out.println(literature.getCard());
            }
        }
    }

    public void printNonCopyable(){
        for( Literature literature : funds){
            if(!isCopyable( literature)){
                System.out.println(literature.getCard());
            }
        }
    }

    public boolean isCopyable(Literature literature){
        return literature instanceof Copyable;
    }

    public boolean isPeriodic(Literature literature){
        return literature instanceof Periodic;
    }

    public void printPeriodic(){
        for (Literature literature : funds){
            if (isPeriodic(literature)){
                Periodic listAsPeriodic = (Periodic) literature;
                System.out.println(listAsPeriodic.getPeriod() + " " + literature.getCard());
            }
        }
    }

    public void printPeriodic2()
    {
        for(Literature literature: funds)
        {
            try{
                Method getPeriodMethod = literature.getClass().getDeclaredMethod("GetPeriod");
                System.out.println(getPeriodMethod.invoke(literature) + " " + literature.getCard());
            }
            catch (Exception ignored)
            {

            }
        }
    }

    public void printNonPeriodic(){
        for (Literature literature : funds) {
            if (!isPeriodic(literature)) {
                System.out.println(literature.getCard());
            }
        }
    }

    public void printPrintable(){
        for( Literature literature : funds){
            if(isPrintable( literature)){
                System.out.println(literature.getCard());
            }
        }
    }

    public void printNonPrintable(){
        for( Literature literature : funds){
            if(!isPrintable( literature)){
                System.out.println(literature.getCard());
            }
        }
    }

    public boolean isPrintable(Literature literature){
        return literature instanceof Printable;
    }

    public void printMultiple(){
        for( Literature literature : funds){
            if(isMultiple( literature)){
                System.out.println(literature.getCard());
            }
        }
    }

    public void printNonMultiple(){
        for( Literature literature : funds){
            if(!isMultiple( literature)){
                System.out.println(literature.getCard());
            }
        }
    }

    public boolean isMultiple(Literature literature){
        return literature instanceof Multiple;
    }

    public Collection<Literature> getFunds() {
        return funds;
    }

}
