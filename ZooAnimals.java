import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Random;

public class ZooAnimals {
    public static void main(String[] args){
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("\\animalNames.txt");
        Random rand = new Random();
        String[] animalNames = new String[0];
        try {
            String names = "";
            File file = new File(filePath);
            Scanner scannerPhil = new Scanner(file);
            while (scannerPhil.hasNextLine()){
                String data = scannerPhil.nextLine();
                if (data.contains(",")){
                    names = names.concat(data + ";");
                }
            }
            animalNames = names.split(";");
        } catch (FileNotFoundException e){
            System.out.println("who");
            e.printStackTrace();
        }
        String[][] allNames = {animalNames[0].split(", "),animalNames[1].split(", "),animalNames[2].split(", "),animalNames[3].split(", ")};

        while (true) {
            int num = rand.nextInt(4);
            System.out.println(allNames[num][rand.nextInt(allNames[num].length)]);
        }
//        for (String[] test:allNames){
//            for(String e:test){
//                System.out.println(e);
//            }
//        }
    }
}
