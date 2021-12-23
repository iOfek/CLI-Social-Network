package bgu.spl.net.api.bidi;

public class BidiMessagingProtocolImpl<T> implements BidiMessagingProtocol<T> {

    private int ownerId;;
    private Connections<T> connections;


    @Override
    public void start(int connectionId, Connections<T> connections) {
        // TODO Auto-generated method stub
        this.ownerId = connectionId;
        this.connections= connections;
        
    }

    @Override
    public void process(T message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean shouldTerminate() {
        // TODO Auto-generated method stub
        return false;
    }
    
}
