package bgu.spl.net.srv;
/**
 * thread- safe singletopn containing all the server's data 
 */

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class DB {
    private ConcurrentHashMap<String,User> namesToUsesrsMap;

    private DB(){
        namesToUsesrsMap = new ConcurrentHashMap<>();
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

    public void login(String userName,String password) throws IllegalStateException{
        if (!namesToUsesrsMap.containsKey(userName))
            throw new IllegalStateException("User is not registered");
        else {
            User user=namesToUsesrsMap.get(userName);
            if (user.getLoggedIn())
                throw new IllegalStateException("User is already logged in");
            else{
                if(!(user.getPassword()==password))
                    throw new IllegalStateException("Incorrect password");
                else
                    namesToUsesrsMap.get(user.getUsername()).setLoggedIn(true);
            }
        }
    }
}
