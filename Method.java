/**
 * [CleanScapes] : 
 * Class for Methods
 * 
 * @author Kathryn Bondoc
 * @author Michelle Nguyen
 * @version 2024-08-04
 */


/* IMPORT STATEMENTS */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class Method {
    
    private static ArrayList<String> users;
    private static ArrayList<Location> map = new ArrayList<Location>(); // sorted list of places

    /* HELPER METHODS */
    
    // Read entries from the database
    public static ArrayList<Location> loadData(String fileName) throws IOException
    {
        File data = new File(fileName);
        Scanner in = new Scanner(data);

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

    // Returns address and votes in String format
    public static String readData(ArrayList<Location> locs)
    {
        String str = "";
        for (Location loc : locs)
        {
            str += loc.getAddress() + " | " + loc.getVotes() + " votes \n";
        }
        return str;
    }
    
    // Return the Location object given a String address, if it exists
    public static Location getLocation(String address) {
        for (Location l: map) {
            if (l.getAddress().equalsIgnoreCase(address)) {
                return l;
            }
        }
        return null;
    }

    // Check if address already exists in database
    public static boolean addressExists(String address) {
        for (Location p: map) {
            if (p.getAddress().equalsIgnoreCase(address)) {
                return true;
            }
        }
        return false;
    }

    // Sort locations from most to least votes, highest to lowest priority
    public static void sortLocations(){
        Collections.sort(map, new VoteComparator());
    }
    static class VoteComparator implements Comparator<Location> {
        public int compare(Location a, Location b) {
            int n = a.getVotes() - b.getVotes();
            if (n > 0) 
                return -1;
            
            else if (n < 0)
                return 1;
      
            return 0;
        }
    }
    
    // Writes the address and its users to a file
    public static void writeToFile() throws IOException {
        File f = new File("database.txt");
        FileWriter fw = new FileWriter(f);
        
        for (Location p: map) {
            String u = p.printUsers();
            fw.append(p.getAddress() + "\t" + u + "\n");
        }
        fw.close();
    }
    
    /* APP FEATURES */

    // Voting a location that needs to be cleaned
    public static String voteLocation(String address, String user) throws IOException {
    	String returnMsg = "";
        
    	// Initialize map 
        if (map.isEmpty()) {
            users = new ArrayList<>();
            users.add(user); // Adding new location & user
            map.add(new Location(address, users));
            returnMsg = "Successfully voted";
        }

        // Adding user to existing location
        else if (addressExists(address)) {
            Location location = getLocation(address);
            // Ensure user has not already vote on that location
            if (!location.isUser(user)) {
                // Iterate through map to update its users
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
        // Adding new location if it is not already in the database
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

    // Removing a location from list
    public static String removeLocation(String address, String user) throws IOException {
    	String returnMsg = "";
    	
        Location l = getLocation(address);
        
        // Database is empty or given address cannot be found
        if (map.isEmpty() || !addressExists(address)) {
            returnMsg = "Address not found in database";
        }

        // If user is the only person who has voted on that location
        else if (l.isUser(user) && l.onlyUser()) {
            returnMsg = "Unable to remove address from database";
        }

        // Allow user to submit a clean on that location
        else {
            int i = map.indexOf(l);
            map.remove(i);
            returnMsg = "Address successfully removed from database";
        }

        sortLocations();
        writeToFile();
        return returnMsg;
    }

    /* TEXT DRIVER METHODS */
    
    // Lists the address and users of each entry in String format
    public String listAllLocations() {
    	String returnMsg = "";
    	
        // Print each address
        for (Location p: map) {
            String u = p.printUsers();
            returnMsg += (p.getAddress() + "\t" + u + "\n");
        }
        return returnMsg;
    }

    // Lists the address, users, and votes of each entry in String format
    public String listLocations() {
    	String returnMsg = "";
    	
        for (Location p: map) {
            returnMsg += p.printInfo();
        }
        return returnMsg;
    }
}