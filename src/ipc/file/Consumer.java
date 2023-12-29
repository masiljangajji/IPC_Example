package ipc.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Consumer {


    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/ipc/file/Example.txt"))) {
            String dataRead = reader.readLine();
            System.out.println("받아온 데이터 : " + dataRead);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
