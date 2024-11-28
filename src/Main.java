/*
Game Plan:
- make a method to get data within a text file
- make a method to compress data
- make a method to decompress the data
- make a method to insert data into other files

PS compressing data in the manner of reporting the number of characters and then
the characters in question becomes inacurate when dealing with a list of consecutive
matches that can only be defined using 2 or more digits. Therefore I added a Colon
in between the numbers to didstinguish them better.

EXAMPLE OF MODIFACATION:
1111111111 = 10:1:

EXAMPLE WITHOUT MODIFACATION:
1111111111 = 101
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
public class Main {
    public static void main(String[] args) {
        String data = getTextFile("C:/Users/38278012/IdeaProjects/cse-2130-mastery-project-Jeffreyy7-jpg/src/data.txt");
        //String data = "11111222223333344444";
        String compData = compresser(data);
        System.out.println(compData);
        String decompData = decompressor(compData);
        System.out.println(decompData);
        System.out.println(FileWriter2000(compData , "C:/Users/38278012/IdeaProjects/cse-2130-mastery-project-Jeffreyy7-jpg/src/CompressedData.txt"));

    }




    public static String getTextFile(String fileName) {
        String fileThingy = "";
        try {
            Scanner fileScan = new Scanner(new File(fileName));
            while (fileScan.hasNext()) {
                fileThingy += fileScan.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to locate " + fileName);
        }
        return fileThingy;
    }

    public static String compresser(String data){
        String letter;
        String compression = "";
        int counter = 0;
        try {
            if (data.length() == 1) {
                compression = "1:" + data;
            } else {
                for (int i = 0; i < data.length(); i++) {
                    letter = data.substring(i, i + 1);
                    if (i != 0) {
                        if (!(letter.equals(data.substring(i - 1, i)))) {
                            compression += Integer.toString(counter) + ":" + data.substring(i - 1, i) + ":";
                            counter = 0;
                        }
                    }
                    counter++;
                    if (i + 1 == data.length()) {
                        compression += Integer.toString(counter) + ":" + data.substring(i) + ":";
                        counter = 0;
                    }
                }
                if ((compression.substring(compression.length() - 1).equals(":"))) {
                    compression = compression.substring(0, compression.length() - 1);
                }
            }
        } catch (Exception e){
            System.out.println("ERROR IN COMPRESSING D:");
        }
        return compression;
    }
    public static String decompressor(String compData){
        String letter;
        String decompData = "";
        try {
            for (int i = 1; i < compData.length(); i += 4) {
                letter = compData.substring(i, i + 1);
                if (letter.equals(":")) {
                    for (int n = 0; n < Integer.parseInt(compData.substring(i - 1, i)); n++) {
                        decompData += compData.substring(i + 1, i + 2);
                    }
                }
            }
        } catch (Exception e){
            System.out.println("ERROR IN DECOMPRESSING D:");
        }
        return decompData;
    }

    public static String FileWriter2000(String msg , String fileName){
        try{
            FileWriter writer = new FileWriter(fileName);
            writer.write(msg);
            writer.close();
        } catch (Exception e) {
            System.out.println("ERROR IN WRITING D:");
        }
        return "";
    }

}