package bgu.spl.net.api.bidi.Messages;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import bgu.spl.net.api.MessageEncoderDecoder;

public class BidiMessageEncoderDecoder implements MessageEncoderDecoder<Message> {
    
    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;
    

    @Override
    public Message decodeNextByte(byte nextByte) {
       
        return null; 
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
   
    
}
