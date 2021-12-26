package bgu.spl.net.srv;

import java.util.LinkedList;

public class User {
    private String username;
    private String password;
    private String birthday;
    private LinkedList<User> following;
    private LinkedList<User> followers;
    private int numOfPosts;
    private int connectionId;
    private boolean LoggedIn;


    public User(String username, String password, String birthday) {
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.following = new LinkedList<>();
        this.followers = new LinkedList<>();
        this.numOfPosts = 0;
        this.LoggedIn=false;
    }

    public boolean getLoggedIn() {return LoggedIn;}

    public void setLoggedIn(boolean LoggedIn) {
        this.LoggedIn = LoggedIn;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getBirthday() {
        return birthday;
    }


    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


   

    public boolean addFollower(User user){
        if(following.contains(user))
            return false;
        return following.add(user);
    }
    public boolean removeFollower(User user){
        return following.remove(user);
    }
    
    
    public LinkedList<User> getFollowing() {
        return following;
    }
    public void setFollowing(LinkedList<User> following) {
        this.following = following;
    }


    public LinkedList<User> getFollowers() {
        return followers;
    }


    public void setFollowers(LinkedList<User> followers) {
        this.followers = followers;
    }


    public int getNumOfPosts() {
        return numOfPosts;
    }


    public void setNumOfPosts(int numOfPosts) {
        this.numOfPosts = numOfPosts;
    }


    public int getConnectionId() {
        return connectionId;
    }


    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }
}
