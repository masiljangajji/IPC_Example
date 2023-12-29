package ipc.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class AnonymousPipe {

    public static void main(String[] args) {
        try {
            // 파이프 생성
            PipedOutputStream parentOutputStream = new PipedOutputStream();
            PipedInputStream parentInputStream = new PipedInputStream(parentOutputStream);

            PipedOutputStream childOutputStream = new PipedOutputStream();
            PipedInputStream childInputStream = new PipedInputStream(childOutputStream);

            // 자식 프로세스 시작
            new ChildProcess(parentInputStream, childOutputStream).start();

            // 부모 프로세스에서 파이프를 통해 데이터 전송
            String messageToChild = "파이프를 이용한 통신1";
            parentOutputStream.write(messageToChild.getBytes());

            // 자식 프로세스에서 보낸 데이터 수신
            byte[] buffer = new byte[1024];
            int bytesRead = childInputStream.read(buffer);
            String receivedMessageFromChild = new String(buffer, 0, bytesRead);
            System.out.println("부모 프로세스에서 수신 : " + receivedMessageFromChild);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ChildProcess extends Thread {
    private PipedInputStream parentInputStream;
    private PipedOutputStream childOutputStream;

    public ChildProcess(PipedInputStream parentInputStream, PipedOutputStream childOutputStream) {
        this.parentInputStream = parentInputStream;
        this.childOutputStream = childOutputStream;
    }

    @Override
    public void run() {
        try {
            // 자식 프로세스에서 부모 프로세스로부터 데이터 수신
            byte[] buffer = new byte[1024];
            int bytesRead = parentInputStream.read(buffer);
            String receivedMessageFromParent = new String(buffer, 0, bytesRead);
            System.out.println("자식 프로세스에서 부모로부터 수신 : " + receivedMessageFromParent);

            // 부모 프로세스로 데이터 전송
            String messageToParent = "파이프를 이용한 통신2";
            childOutputStream.write(messageToParent.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
