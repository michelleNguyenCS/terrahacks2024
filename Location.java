/**
 * [CleanScapes] : 
 * Location Class
 * 
 * @author Kathryn Bondoc
 * @author Michelle Nguyen
 * @version 2024-08-04
 */


/* IMPORT STATEMENTS */
import java.util.ArrayList;


public class Location {

    private String address;
    private ArrayList<String> users;
    private int votes;

    public Location(String address, ArrayList<String> users) {
        this.address = address;
        this.users = users;
        this.votes = users.size();
    }

    /* GET METHODS */
    
    public String getAddress(){
        return address;
    }

    public ArrayList<String> getUsers(){
        return users;
    }

    public int getVotes() {
        return votes;
    }
    
    /* METHODS */

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public void setVotes() {
        this.votes = users.size();
    }

    public boolean isUser(String user) {
        for (String u: users) {
            if (u.equalsIgnoreCase(user)){
                return true;
            }
        }
        return false;
    }

    public boolean onlyUser() {
        return users.size() == 1;
    }

    public String printInfo() {
       return address + " " + users + " " + votes;
    }

    public String printUsers() {
        String u = "";
        for (String user: users) {
            if (users.getLast().equals(user)) {
                u += user;
                break;
            }
            u += (user + ", ");
        }
        return u;
    }
    
    /* OVERRIDE METHODS */
    
    public String toString() {
    	return address + " | " + votes + " votes";
    }
}