import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class ZooAnimals {
    public static void main(String[] args){
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("\\animalNames.txt");
        System.out.println(filePath);
        try {
            File file = new File(filePath);
            Scanner scannerPhil = new Scanner(file);
            System.out.println(scannerPhil.nextLine());
        } catch (FileNotFoundException e){
            System.out.println("who");
            e.printStackTrace();
        }
    }
}
