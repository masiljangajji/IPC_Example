package ipc.sharedMemory;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class Consumer {


    /**
     * RandomAccessFile은 Java에서 파일에 대한 무작위 액세스를 제공하는 클래스입니다. 이 클래스는 파일에 대한 입력 및 출력 작업을 할 수 있으며, 파일의 임의 위치에서 데이터를 읽거나 쓸 수 있는 특징을 가지고 있습니다.
     * <p>
     * FileChannel은 Java NIO(Non-blocking I/O) 패키지에서 제공되는 클래스로, 파일을 읽고 쓰는 작업에 사용됩니다.
     * <p>
     * <p>
     * MappedByteBuffer는 Java NIO(Non-blocking I/O) 패키지에서 제공되는 클래스로, 파일의 특정 부분을 메모리에 매핑하여 사용할 수 있게 해주는 클래스입니다.
     */

    public static void main(String[] args) {

        try (
                // 읽기 및 쓰기 모드로 생성
                RandomAccessFile file = new RandomAccessFile("src/ipc/sharedMemory/Example.txt", "rw");
                // 파일과 상호작용 하는 채널 가져오기
                FileChannel channel = file.getChannel()) {

            // 파일 내용을 메모리에 매핑 , 시작위치 0 부터 파일의 길이까지 매핑
            MappedByteBuffer mappedBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, file.length());

            // 공유 메모리에서 데이터 읽어오기
            byte[] data = new byte[mappedBuffer.remaining()];
            mappedBuffer.get(data);

            String message = new String(data, StandardCharsets.UTF_8);
            System.out.println("받아온 데이터 : " + message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
