
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
import java.util.Scanner;


public class Method {
    
    private ArrayList<String> users;
    private ArrayList<Location> map = new ArrayList<Location>(); // sorted list of places
    private String returnMsg;

    public Location getLocation(String address) {
        for (Location l: map) {
            if (l.getAddress().equalsIgnoreCase(address)) {
                return l;
            }
        }
        return null;
    }

    // if address already exists in database
    public boolean addressExists(String address) {
        for (Location p: map) {
            if (p.getAddress().equalsIgnoreCase(address)) {
                return true;
            }
        }
        return false;
    }

    // to sort locations from most to least votes 
    public void sortLocations(){
        Collections.sort(map, new VoteComparator());
    }
    class VoteComparator implements Comparator<Location> {
        public int compare(Location a, Location b) {
            int n = a.getVotes() - b.getVotes();
            if (n > 0) 
                return -1;
            
            else if (n < 0)
                return 1;
      
            return 0;
        }
    }

    // adding location to list
    public String voteLocation(String address, String user) throws IOException {
        // initialize map 
        if (map.isEmpty()) {
            users = new ArrayList<>();
            users.add(user); // adding new location & user
            map.add(new Location(address, users));
            returnMsg = "Successfully voted";
        }

        // adding user to existing location
        else if (addressExists(address)) {
            Location location = getLocation(address);
            // ensure user didn't already vote
            if (!location.isUser(user)) {
                // iterate through map to update its users
                for (Location l: map) {
                    if (l.getAddress().equalsIgnoreCase(address)) {
                        users = l.getUsers();
                        users.add(user);
                        l.setUsers(users);
                        l.setVotes();
                        returnMsg = "Successfully voted";
                        break;
                    }
                }
            }
            else
                returnMsg = "User already voted";
        }
        // adding new location
        else {
            users = new ArrayList<>();
            users.add(user);
            map.add(new Location(address, users));
            returnMsg = "Successfully voted";
        }

        sortLocations();
        writeToFile();
        return returnMsg;
    }

    // removing a location from list
    public String removeLocation(String address, String user) throws IOException {
        
        if (map.isEmpty() || !addressExists(address)) {
            returnMsg = "Address not found in database";
        }

        Location l = getLocation(address);

        if (l == null) {
            returnMsg = "Address not found in database";
        }

        else if (l.isUser(user) && l.onlyUser()) {
            returnMsg = "Unable to remove address from database";
        }

        else {
            int i = map.indexOf(l);
            map.remove(i);
            returnMsg = "Address successfully removed from database";
        }

        sortLocations();
        writeToFile();
        return returnMsg;
    }

    // lists the address and users of each place
    public String listAllLocations() {
        // print each address
        for (Location p: map) {
            String u = p.printUsers();
            returnMsg += (p.getAddress() + "\t" + u + "\n");
        }
        return returnMsg;
    }

    // lists the address, users, and votes of each place
    public String listLocations() {
        for (Location p: map) {
            returnMsg += p.printInfo();
        }
        return returnMsg;
    }

    // writes the address and its users to a file
    public void writeToFile() throws IOException {
        File f = new File("database.txt");
        FileWriter fw = new FileWriter(f);
        
        for (Location p: map) {
            String u = p.printUsers();
            fw.append(p.getAddress() + "\t" + u + "\n");
        }
        fw.close();
    }

    public static ArrayList<Location> loadData(String fileName) throws IOException
    {
        File data = new File(fileName);
        Scanner in = new Scanner(data);
        in.nextLine();

        ArrayList<Location> locations = new ArrayList<>();

        while (in.hasNextLine())
        {
            String line = in.nextLine();
            Scanner partScanner = new Scanner(line).useDelimiter("\t");
            String address = partScanner.next();
            String usersString = partScanner.next();

            ArrayList<String> users = new ArrayList<>();
            Scanner userScanner = new Scanner(usersString).useDelimiter(", ");
            while (userScanner.hasNext())
            {
                users.add(userScanner.next());
            }
            userScanner.close();
            partScanner.close();

            Location location = new Location(address, users);
            locations.add(location);
        }
        in.close();
        return locations;
    }

    // prints address and votes
    public static String printData(ArrayList<Location> locs)
    {
        String str = "";
        for (Location loc : locs)
        {
            str += loc.getAddress() + " | " + loc.getVotes() + "\n";
        }
        return str;
    }
}
