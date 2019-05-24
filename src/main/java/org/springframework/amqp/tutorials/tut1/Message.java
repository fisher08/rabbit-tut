package org.springframework.amqp.tutorials.tut1;

import java.io.*;

/**
 * Created by Kirill on 5/24/19.
 */
public class Message implements Serializable {
    private Integer data1 = 1;
    private String data2 = "test test key";

    public byte[] toBytes() {
        byte[]bytes;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.flush();
            oos.reset();
            bytes = baos.toByteArray();
            oos.close();
            baos.close();
        } catch(IOException e){
            bytes = new byte[] {};
            System.out.println(e.getMessage());
        }
        return bytes;
    }

    public static Message fromBytes(byte[] body) {
        Message obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (body);
            ObjectInputStream ois = new ObjectInputStream (bis);
            obj = (Message)ois.readObject();
            ois.close();
            bis.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    @Override
    public String toString() {
        return "Message{" +
                "data1=" + data1 +
                ", data2='" + data2 + '\'' +
                '}';
    }
}
