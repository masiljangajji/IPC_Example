package ipc.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {


        try (
                Socket socket = new Socket("localhost", 32007);

                // socket 통신을 위한 I/O Stream
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {


            String line;

            while (!(line = reader.readLine()).equals("Close")) {
                pw.println(line);
                pw.flush();

                String reponse = bf.readLine();

                System.out.println("수신 " + reponse);

            }
        } catch (IOException e) {
            throw new RuntimeException();
        }


    }

}
