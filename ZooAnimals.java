import java.io.File;
import java.util.BitSet;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.HashMap;


public class ZooAnimals {
    public static void main(String[] args){
        HashMap<String, String[]> animalData = new HashMap<String, String[]>();
        HashMap<String, Boolean> usedNames = new HashMap<String, Boolean>();
        Random rand = new Random();

        // Get Names
        String animalNamesFilePath = new File("").getAbsolutePath();
        animalNamesFilePath = animalNamesFilePath.concat("\\animalNames.txt");
        String[] animalNames = new String[0];
        try {
            String names = "";
            File namesFile = new File(animalNamesFilePath);
            Scanner scannerPhil = new Scanner(namesFile);
            while (scannerPhil.hasNextLine()){
                String data = scannerPhil.nextLine();
                if (data.contains(",")){
                    names = names.concat(data + ";");
                }
            }
            scannerPhil.close();
            animalNames = names.split(";");
        } catch (FileNotFoundException e){
            System.out.println("who");
            e.printStackTrace();
        }

        String[][] allNames = {animalNames[0].split(", "),animalNames[1].split(", "),animalNames[2].split(", "),animalNames[3].split(", ")};


        // Get Arriving Animal Data
        String arrivingDataFilePath = new File("").getAbsolutePath();
        arrivingDataFilePath = arrivingDataFilePath.concat("\\arrivingAnimals.txt");
        String[][] unParsedData = new String[0][0];
        int lineCount = 0;
        try {
            File dataFile = new File(arrivingDataFilePath);
            Scanner scannerBob = new Scanner(dataFile);
            while (scannerBob.hasNextLine()){
                lineCount++;
                scannerBob.nextLine();
            }
            scannerBob.close();

        } catch (FileNotFoundException e){
            System.out.println("Bad");
            e.printStackTrace();
        }

        try {
            File dataFile = new File(arrivingDataFilePath);
            unParsedData = new String[lineCount][0];
            Scanner scannerBob = new Scanner(dataFile);
            int i = 0;
            while (scannerBob.hasNextLine()){
                unParsedData[i] = scannerBob.nextLine().split(", ");
                i++;
            }
            scannerBob.close();
        } catch (FileNotFoundException e){
            System.out.println("Bad");
            e.printStackTrace();
        }



        // Parse Data
        int dataNumber = 7;
        String[] currentAnimalData = new String[8];
        currentAnimalData[0] = unParsedData[dataNumber][0].split(" ")[0];
        currentAnimalData[1] = genBirthDay(currentAnimalData[0], unParsedData[dataNumber][1]);
        currentAnimalData[2] = unParsedData[dataNumber][2].replace(" color", "");
        currentAnimalData[3] = unParsedData[dataNumber][3].split(" ")[0];
        currentAnimalData[4] = unParsedData[dataNumber][4].replace("from ", "");
        currentAnimalData[5] = unParsedData[dataNumber][5];
        currentAnimalData[6] = unParsedData[dataNumber][0].split(" ")[4].substring(0, 1).toUpperCase() + unParsedData[dataNumber][0].split(" ")[4].substring(1, 2);
        currentAnimalData[7] = genName(usedNames, allNames, currentAnimalData[6]);


        //Put data into animalData HashMap
        genId(animalData, currentAnimalData[6], currentAnimalData);
        System.out.println(animalData.get("Li01")[1]);
    }
    public static void genId(HashMap<String, String[]> animals, String species, String[] data){
        for (int i = 0; i < 4; i++){
                if (!animals.containsKey((species + 0) + (i+1))){
                    animals.put((species + 0) + (i+1), data);
                }
        }
    }

    public static String genBirthDay(String age, String unparsedSeason){
        String birthday = "";
        if (unparsedSeason.contains("spring")){
            birthday = birthday.concat("Mar 21, ");
        } else if (unparsedSeason.contains("summer")){
            birthday = birthday.concat("Jun 21, ");
        } else if (unparsedSeason.contains("fall")){
            birthday = birthday.concat("Sept 21, ");
        } else if (unparsedSeason.contains("winter")){
            birthday = birthday.concat("Dec 21, ");
        } else {
            birthday = birthday.concat("Jan 1, ");
        }
        birthday = birthday.concat("" + (2022 - Integer.parseInt(age)));
        return birthday;
    }

    public static String genName(HashMap<String, Boolean> used, String[][] names, String species){
        int speciesId;
        Random rand = new Random();
        switch (species){
            case "Hy":
                speciesId = 0;
                break;
            case "Li":
                speciesId = 1;
                break;
            case "Be":
                speciesId = 2;
                break;
            case "Ti":
                speciesId = 3;
                break;
            default:
                speciesId = 0;
        }
        String name = "";
        do {
            name = names[speciesId][rand.nextInt(names[speciesId].length)];
        } while (used.containsKey(name));
        used.put(name, true);
        return name;
    }
}
