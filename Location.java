
/**
 * @author Kathryn Bondoc
 * @version 2024-08-03
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


public class Location {

    private String address;
    private ArrayList<String> users;
    private int votes;

    private HashMap<String, ArrayList<String>> locations = new HashMap<>(); // initial list of locations
    private ArrayList<Place> locationList = new ArrayList<Place>(); // sorted list of locations

    // getters
    public String getAddress() {
        return address;
    }

    public ArrayList<String> getUsers(String address) {
        return locations.get(address);
    }

    public int getVotes() {
        return votes;
    }

    // if it exists
    public boolean isUser(String user) {
        return users.contains(user);
    }

    public boolean addressExists(String address) {
        return locations.keySet().contains(address);
    }

    // to sort locations from most to least votes 
    public void sortLocations(){
        Collections.sort(locationList, new VoteComparator());
    }

    class VoteComparator implements Comparator<Place> {
        public int compare(Place a, Place b) {
            int n = a.getVotes() - b.getVotes();
            if (n > 0) 
                return -1;
            
            else if (n < 0)
                return 1;
      
            return 0;
        }
    }

    // adding location to list
    public void addLocation(String address, String user) throws IOException {
        
        // initialize map 
        if (locations.isEmpty()) {
            users = new ArrayList<>();
            users.add(user); // adding new location & user
            locations.put(address, users);
            locationList.add(new Place(address, users));
            
        }

        // adding user to location
        else if (addressExists(address)) {
            users = getUsers(address);
            if (!isUser(user)) {
                locations.get(address).add(user);
                users = getUsers(address);
                Place place = new Place(address, users);

                // iterate through locationList to update its users
                for (Place p: locationList) {
                    if (p.equals(place)) {
                        p.setUsers(users);
                        p.setVotes();
                        break;
                    }
                }
            }
        }

        else {
            users = new ArrayList<>();
            users.add(user);
            locations.put(address, users);
            locationList.add(new Place(address, users));
        }

        sortLocations();
        writeToFile();
    }

    public void listAllLocations() {
        System.out.println();

        // print each address
        for (Place p: locationList) {
            String u = p.printUsers();
            System.out.print(p.getAddress() + "\t" + u + "\n");
        }
    }

    public void listLocationList() {
        for (Place p: locationList) {
            p.printInfo();
        }
    }

    public void writeToFile() throws IOException {
        File f = new File("database.txt");
        FileWriter fw = new FileWriter(f);
        
        for (Place p: locationList) {
            String u = p.printUsers();
            fw.append(p.getAddress() + "\t" + u + "\n");
        }
        fw.close();
    }

}
