package GameSocket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Read {
    public static void main(String[] args) {
        try{
            BufferedReader fr = new BufferedReader(new FileReader("temp/0.dat"));
            String line;
            while ((line = fr.readLine()) != null){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}