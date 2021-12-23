package bgu.spl.net.api.bidi;

import bgu.spl.net.api.bidi.Messages.Message;
import bgu.spl.net.api.bidi.Messages.Register;
import bgu.spl.net.srv.DB;
import bgu.spl.net.srv.User;

public class BidiMessagingProtocolImpl implements BidiMessagingProtocol<Message> {

    private int ownerId;
    private Connections<Message> connections;
    private DB db = DB.getInstance();
    private boolean shouldTerminate =false;

    @Override
    public void start(int connectionId, Connections<Message> connections) {
        this.ownerId = connectionId;
        this.connections= connections;
        
    }

    @Override
    public void process(Message message) {
        
        switch (message.getOpcode()) {
            case REGISTER:
                register((Register)message);
                break;
        
            default:
                break;
        }
        
    }

    @Override
    public boolean shouldTerminate() {
        return this.shouldTerminate;
    }

    public void register(Register message){
        db.register(new User(message.getUsername(),message.getPassword(),message.getBirthday()));
    }
    
}
