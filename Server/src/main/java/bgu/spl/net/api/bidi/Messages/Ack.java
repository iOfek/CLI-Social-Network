package bgu.spl.net.api.bidi.Messages;

import java.nio.charset.StandardCharsets;
import org.apache.commons.lang3.ArrayUtils;

public class Ack  extends Message{

    private Opcode messageOpcode ;
    private String optional;

    public Ack(Opcode messageOpcode, String optional) {
        this.opcode = Opcode.ACK;
        this.messageOpcode = messageOpcode;
        this.optional = optional;
    }

    public Opcode getMessageOpcode() {
        return messageOpcode;
    }

    public void setMessageOpcode(Opcode messageOpcode) {
        this.messageOpcode = messageOpcode;
    }

    public String getOptional() {
        return optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }

    @Override
    public byte[] encode(Message message) {
        byte [] encodedMessgae;
        byte [] opcodeBytes = shortToBytes(getOpcodeValue());
        byte [] messageOpcodeBytes = shortToBytes(getOpcodeValue(messageOpcode));
        
        byte [] optionalBytes = this.optional.getBytes(StandardCharsets.UTF_8);
        encodedMessgae = ArrayUtils.addAll(opcodeBytes, messageOpcodeBytes);
        encodedMessgae = ArrayUtils.addAll(encodedMessgae, messageOpcodeBytes);
        String bye = ";";
        encodedMessgae = ArrayUtils.addAll(encodedMessgae, bye.getBytes(StandardCharsets.UTF_8));
        return encodedMessgae;
    }
    
}
