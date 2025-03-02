package GameSocket;

import java.nio.charset.StandardCharsets;

public class Request {
    private ClientOperation Operation;
    private int bytesLength;
    private byte[] data;

    public Request(ClientOperation operation) {
        Operation = operation;
    }

    public Request(ClientOperation operation, byte[] data) {
        Operation = operation;
        this.setData(data);
    }

    public Request(ClientOperation operation, String data) {
        Operation = operation;
        this.setData(data);
    }

    public ClientOperation getOperation() {
        return Operation;
    }

    public void setOperation(ClientOperation operation) {
        Operation = operation;
    }

    public int getBytesLength() {
        return bytesLength;
    }

    public void setBytesLength(int dataBytes) {
        this.bytesLength = dataBytes;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setData(String data) {
        this.data = data.getBytes(StandardCharsets.UTF_8);
        this.bytesLength = this.data.length;
    }

    public byte[] encode(){
        return data;
    }
}
