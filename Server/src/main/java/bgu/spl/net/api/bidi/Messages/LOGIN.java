package bgu.spl.net.api.bidi.Messages;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.text.DateFormatSymbols;

public class LOGIN extends Message {
    private String username;
    private String password;
    private byte captcha;

    public LOGIN(String username, String password, byte captcha) {
        if(captcha==0)
            throw new IllegalStateException("Couldn't log in. Captcha is 0");
        this.opcode = Opcode.REGISTER;
        this.username = username;
        this.password = password;
        //this.captcha = captcha;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

//    public byte getCaptcha() {
//        return captcha;
//    }


    @Override
    public byte[] encode(Message message) {
        byte [] encodedMessgae;
        byte [] opcodeBytes = shortToBytes(getOpcodeValue());
        byte [] usernameBytes = this.username.getBytes(StandardCharsets.UTF_8);
        byte seperator =0;
        byte [] passwordBytes = this.password.getBytes(StandardCharsets.UTF_8);
        byte captcha = this.captcha;
        encodedMessgae = ArrayUtils.addAll(opcodeBytes,seperator);
        encodedMessgae = ArrayUtils.addAll(encodedMessgae, usernameBytes);
        encodedMessgae = ArrayUtils.addAll(encodedMessgae, seperator);
        encodedMessgae = ArrayUtils.addAll(encodedMessgae, passwordBytes);
        encodedMessgae = ArrayUtils.addAll(encodedMessgae, seperator);
        encodedMessgae = ArrayUtils.addAll(encodedMessgae, captcha);
        return encodedMessgae;
    }
}