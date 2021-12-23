package bgu.spl.net.api.bidi.Messages;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.text.DateFormatSymbols;

public class Register extends Message {
    private String username;
    private String password;
    private String birthday;//TODO change from String t

    
    public Register(String username, String password, String birthday) {
        this.opcode = Opcode.REGISTER;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
    }


    @Override
    public byte[] encode(Message message) {
        byte [] encodedMessgae;
        byte [] opcodeBytes = shortToBytes(getOpcodeValue());
        byte [] usernameBytes = this.username.getBytes(StandardCharsets.UTF_8);
        byte seperator =0;
        byte [] passwordBytes = this.password.getBytes(StandardCharsets.UTF_8);
        byte [] birthdayBytes = this.birthday.getBytes(StandardCharsets.UTF_8);
        encodedMessgae = ArrayUtils.addAll(opcodeBytes,seperator);
        encodedMessgae = ArrayUtils.addAll(encodedMessgae, usernameBytes);
        encodedMessgae = ArrayUtils.addAll(encodedMessgae, seperator);
        encodedMessgae = ArrayUtils.addAll(encodedMessgae, passwordBytes);
        encodedMessgae = ArrayUtils.addAll(encodedMessgae, seperator);
        encodedMessgae = ArrayUtils.addAll(encodedMessgae, birthdayBytes);
        encodedMessgae = ArrayUtils.addAll(encodedMessgae, seperator);
        return encodedMessgae;
    }

    


    
}
