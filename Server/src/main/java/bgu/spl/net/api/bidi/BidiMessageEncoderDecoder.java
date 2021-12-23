package bgu.spl.net.api.bidi;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import bgu.spl.net.api.MessageEncoderDecoder;
import bgu.spl.net.api.bidi.Messages.Message;
import bgu.spl.net.api.bidi.Messages.Register;
import bgu.spl.net.api.bidi.Messages.Message.Opcode;

public class BidiMessageEncoderDecoder implements MessageEncoderDecoder<Message> {
    
    private byte[] bytes ; //start with 1k
    private int len = 0;

    public BidiMessageEncoderDecoder(){
        
        bytes = new byte[1 << 10];
    }

    @Override
    public Message decodeNextByte(byte nextByte) {
        
         if (nextByte == ';') {
            return popMessage();
        } 

        pushByte(nextByte);
        return null; //not a Message yet
    }

    @Override
    public byte[] encode(Message message) {
        return message.encode(message);
    }

    
    
    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }

        bytes[len++] = nextByte;
    }
    public short bytesToShort(byte[] byteArr){
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }

   
    
    
    /* public String bytesToString(byte[] byteArr){
        String result = new String(bytes,StandardCharsets.UTF_8);
        return result;
    }

    public byte[] shortToBytes(short num){
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    } */
    private Message popMessage() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
        Message result = null;
        short opcode = bytesToShort(new byte[]{bytes[0],bytes[1]});
        switch (opcode) {
            case 1:
                result =new Register(bytes);
                System.out.println("Name "+((Register)result).getUsername());
                System.out.println("Pass "+((Register)result).getPassword());
                System.out.println("Birth "+((Register)result).getBirthday());
            break;
            case 2:
                
            break;
            case 3:
                
            break;
            case 4:
                
            break;
            case 5:
                
            break;
            case 6:
                
            break;
            case 7:
                
            break;
            case 8:
                
            break;
            case 9:
                
            break;
            case 10:
                
            break;
            case 11:
                
            break;
            case 12:
                
            break;
                
            
        }
         
        
        return result;
    }
   
    
}
