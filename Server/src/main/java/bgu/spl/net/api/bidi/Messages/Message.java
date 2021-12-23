package bgu.spl.net.api.bidi.Messages;

import javax.swing.plaf.synth.Region;

public abstract class Message {
    public enum Opcode{
        REGISTER,LOGIN,LOGOUT,FOLLOW,POST,PM,LOGSTAT,STAT,
        NOTIFICATION,ACK,ERROR,BLOCK
    }
    protected Opcode opcode;


    public abstract byte[] encode(Message message);

    public short bytesToShort(byte[] byteArr){
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }

    public byte[] shortToBytes(short num){
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }

    public Opcode getOpcode() {
        return opcode;
    }

    public void setOpcode(Opcode opcode) {
        this.opcode = opcode;
    }

    public short getOpcodeValue() {
        return (short)opcode.ordinal();
    }
    public Class<? extends Message> f(){
        if(opcode == Opcode.REGISTER)
            return Register.class;
        return null;
    }
    

}
