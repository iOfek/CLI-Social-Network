package bgu.spl.net.srv;
/**
 * thread- safe singletopn containing all the server's data 
 */

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class DB {
    private ConcurrentHashMap<String,User> namesToUsesrsMap;
    private ConcurrentHashMap<Integer,String> connectionidToUsername;
    private DB(){
        namesToUsesrsMap = new ConcurrentHashMap<>();
        connectionidToUsername = new ConcurrentHashMap<>();
    }
    /**
     * {@link DB} Singleton Holder.
     */

	private static class SingletonHolder {
        private static DB instance = new DB();
    }

	
	/**
     * Retrieves the single instance of this class.
     */

	public static DB getInstance() {
		return SingletonHolder.instance;
	}
    
    public void register(User user) throws IllegalStateException{
        if (!namesToUsesrsMap.containsKey(user.getUsername()))
            namesToUsesrsMap.put(user.getUsername(), user);
        else
            throw new IllegalStateException("User is already registered");
    }

    public void login(String userName,String password, int connectionId) throws IllegalStateException{
        if (!namesToUsesrsMap.containsKey(userName))
            throw new IllegalStateException("User is not registered");
        else {
            User user=namesToUsesrsMap.get(userName);
            if (user.getLoggedIn())
                throw new IllegalStateException("User is already logged in");
            else{
                if(!(user.getPassword().equals(password)))
                    throw new IllegalStateException("Incorrect password");
                else{
                    namesToUsesrsMap.get(user.getUsername()).setLoggedIn(true);
                    connectionidToUsername.put(connectionId, userName);
                }
                    
            }
        }
    }

    public void logout(int connectionId){
        if(!connectionidToUsername.containsKey(connectionId))
            throw new IllegalStateException("User is not logged in");
        namesToUsesrsMap.get(connectionidToUsername.get(connectionId)).setLoggedIn(false);
        connectionidToUsername.remove(connectionId);
        
    }

    public void follow(int connectionId,boolean isFollow,String usernameTofollow) {
        if(!connectionidToUsername.containsKey(connectionId))
            throw new IllegalStateException("User is not logged in");
        User currUser =  namesToUsesrsMap.get(connectionidToUsername.get(connectionId));
        User userToFollow = namesToUsesrsMap.get(usernameTofollow);
        if(userToFollow == null)
            throw new IllegalStateException("User to follow is not registered");
        if(isFollow){
            if(!currUser.addFollower(userToFollow))
                throw new IllegalStateException("User is already being followed");
        }
        else{
            if(!currUser.removeFollower(userToFollow))
                throw new IllegalStateException("User is already not being followed");
        }
       
    }    
}
