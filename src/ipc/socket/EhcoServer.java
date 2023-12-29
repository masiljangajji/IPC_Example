package ipc.socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EhcoServer {


    public static void main(String[] args) {

        try (
                // 32007 포트로 서버열기
                ServerSocket socket = new ServerSocket(32007);
                // 클라이언트 요청 대기
                Socket connection = socket.accept();

                // Socket 통신을 위한 I/O Stream
                BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()))
        ) {

            String line;

            while ((line = bf.readLine()) != null) {
                System.out.println(line);
                pw.println(line);
                // 클라이언트로 부터 받은 메시지 재전송
                pw.flush();
            }

            System.out.println("클라이언트 연결 종료");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


