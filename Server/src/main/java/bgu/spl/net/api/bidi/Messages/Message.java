package bgu.spl.net.api.bidi.Messages;

import javax.swing.plaf.synth.Region;

public abstract class Message {
    public enum Opcode{
        TEST, REGISTER,LOGIN,LOGOUT,FOLLOW,POST,PM,LOGSTAT,STAT,
        NOTIFICATION,ACK,ERROR,BLOCK
    }
    
    protected Opcode opcode;


    public abstract byte[] encode(Message message);


    public Opcode getOpcode() {
        return opcode;
    }

    public void setOpcode(Opcode opcode) {
        this.opcode = opcode;
    }

    public short getOpcodeValue() {
        return (short)opcode.ordinal();
    }
    /* public Class<? extends Message> f(byte []bytes){
        if(opcode == Opcode.REGISTER)
            return Register.class;
        return null;
    } */
    public byte[] shortToBytes(short num){
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }
   
    

}
