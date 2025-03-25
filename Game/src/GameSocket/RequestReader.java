package GameSocket;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RequestReader implements AutoCloseable {
    private Request request;
    private DataInputStream dataInputStream;
    private int readByte;
    public RequestReader(Request request){
        this.request = request;
        this.dataInputStream = new DataInputStream(new ByteArrayInputStream(request.getData()));
        readByte = 0;
    }

    public ProtocolOperation getOperation(){
        return request.getOperation();
    }

    public byte[] readByteData() throws IOException {
        int byteLen = dataInputStream.readInt();
        readByte += 4;
        byte[] bytes = dataInputStream.readNBytes(byteLen);
        readByte += byteLen;
        return bytes;
    }

    public Integer readInt() throws IOException {
        int i = dataInputStream.readInt();
        readByte += 4;
        return i;
    }

    public String readString() throws IOException {
        int strLen = dataInputStream.readInt();
        readByte += 4;
        byte[] strByte = dataInputStream.readNBytes(strLen);
        readByte += strLen;
        System.out.println(strLen+ " " +new String(strByte, StandardCharsets.UTF_8));
        return new String(strByte, StandardCharsets.UTF_8);
    }
    public boolean reachTheEnd(){
        return readByte == request.getBytesLength();
    }

    @Override
    public void close() throws Exception {
        dataInputStream.close();
    }
}
