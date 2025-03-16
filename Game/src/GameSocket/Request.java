package GameSocket;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Request {
    private ProtocolOperation operation;
    private int bytesLength;
    private byte[] data;

    public Request(ProtocolOperation operation) {
        this.operation = operation;
        this.data = new byte[0];
    }

    public Request(ProtocolOperation operation, byte[] data) {
        this.operation = operation;
        this.setData(data);
    }

    public Request(ProtocolOperation operation, String data) {
        this.operation = operation;
        this.setData(data);
    }

    public ProtocolOperation getOperation() {
        return operation;
    }

    public void setOperation(ProtocolOperation operation) {
        this.operation = operation;
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

    public String getDataUTF() {
        return new String(data,0,bytesLength,StandardCharsets.UTF_8);
    }

    public void setData(byte[] data) {
        this.data = data;
        this.bytesLength = this.data.length;
    }

    public void setData(String data) {
        this.data = data.getBytes(StandardCharsets.UTF_8);
        this.bytesLength = this.data.length;
    }

    public Request appendData(byte[] data) {
        ByteBuffer bf = ByteBuffer.allocate(this.bytesLength + data.length);
        bf.clear().put(this.data).put(data).flip();
        this.data = bf.array();
        this.bytesLength = this.data.length;
        return this;
    }

    public Request appendData(String data) {
        byte[] newBytes = data.getBytes(StandardCharsets.UTF_8);
        ByteBuffer bf = ByteBuffer.allocate(this.bytesLength + newBytes.length);
        bf.clear().put(this.data).put(newBytes).flip();
        this.data = bf.array();
        this.bytesLength = this.data.length;
        return this;
    }

    public Request appendData(int integer) {
        ByteBuffer bf = ByteBuffer.allocate(this.bytesLength + Integer.BYTES);

        bf.clear().put(this.data).putInt(integer).flip();
        this.data = bf.array();
        this.bytesLength = this.data.length;
        return this;
    }

    public byte[] encodeBytes(){
        ByteBuffer bf = ByteBuffer.allocate(8 + bytesLength);
        bf.putInt(operation.ordinal());
        bf.putInt(bytesLength);
        bf.put(data);
        return bf.array();
    }

    public static Request decodeBytes(byte[] bytes){
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            int opCode = in.readInt();
            ProtocolOperation operation = ProtocolOperation.values()[opCode];
            int dataBytes = in.readInt();
            byte[] dataGet = in.readNBytes(dataBytes);
            return new Request(operation,dataGet);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return new Request(ProtocolOperation.ACKN);
    }

    @Override
    public String toString() {
        return "Request{" +
                "operation=" + operation +
                ", bytesLength=" + bytesLength +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
