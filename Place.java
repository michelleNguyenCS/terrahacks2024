import java.util.ArrayList;

/**
 * @author Kathryn Bondoc
 * @version 2024-08-03
 */

public class Place {

    private String address;
    private ArrayList<String> users;
    private int votes;

    public Place(String address, ArrayList<String> users) {
        this.address = address;
        this.users = users;
        this.votes = users.size();
    }

    public String getAddress(){
        return address;
    }

    public ArrayList<String> getUsers(){
        return users;
    }

    public int getVotes() {
        return votes;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public void setVotes() {
        this.votes = users.size();
    }

    public void printInfo() {
        System.out.println(address + " " + users + " " + votes);
    }

    public String printUsers() {
        String u = "";
        for (String user: users) {
            if (users.getLast().equals(user)) {
                u += user;
            }
            u += (user + ", ");
        }
        return u;
    }

    public boolean equals(Object other) {
        Place othPlace = (Place) other;
        return (this.getAddress().equalsIgnoreCase(othPlace.getAddress()));
                
    }
}