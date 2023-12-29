package ipc.file;

import java.io.FileWriter;
import java.io.IOException;

public class Producer {

    public static void main(String[] args) {

        try (FileWriter writer = new FileWriter("src/ipc/file/Example.txt")) {
            String dataToWrite = "파일 기반 IPC";
            writer.write(dataToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
