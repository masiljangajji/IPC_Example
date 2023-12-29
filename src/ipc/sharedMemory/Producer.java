package ipc.sharedMemory;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class Producer {

    public static void main(String[] args) {

        try (
                // 읽기 및 쓰기 모드로 생성
                RandomAccessFile file = new RandomAccessFile("src/ipc/sharedMemory/Example.txt", "rw");
                // 파일과 상호작용 하는 채널 가져오기
                FileChannel channel = file.getChannel()) {

            String message = "Shared Memory로 통신";
            // message를 Byte단위로
            byte[] data = message.getBytes(StandardCharsets.UTF_8);

            // 파일 내용을 메모리에 매핑 , 시작위치 0 부터 , message 크기 만큼
            MappedByteBuffer mappedBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, data.length);

            // 메모리 매핑된 버퍼에 data 배열 쓰겠다.
            mappedBuffer.put(data);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
