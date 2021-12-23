package bgu.spl.net.api.bidi.Messages;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import bgu.spl.net.api.MessageEncoderDecoder;

public class BidiMessageEncoderDecoder implements MessageEncoderDecoder<Message> {
    
    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;
    

    @Override
    public Message decodeNextByte(byte nextByte) {
       /*  if (nextByte == ';') {
            return popMessage();
        } */

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
   /*  private Message popString() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
        
        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        len = 0;
        return result;
    } */
   
    
}
