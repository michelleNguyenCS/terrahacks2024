/**
 * [CleanScapes] : 
 * Text-Based Interface, Alternative for GUI
 * 
 * @author Kathryn Bondoc
 * @version 2024-08-04
 */


/* IMPORT STATEMENTS */
import java.io.IOException;
import java.util.Scanner;


public class TextDriver {

	public static void main(String[] args) throws IOException {
		Method method = new Method();

		Scanner scanner = new Scanner(System.in);
		System.out.print("-> ");

		while (scanner.hasNextLine()) {
			String action = scanner.nextLine();

      		if (action == null || action.equals("")) {		
       			System.out.print("\n>");
        		continue;
    		}
      		
      		// Quit the app
      		else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
        		return;

      		// Vote for a location to be cleaned
			else if (action.equalsIgnoreCase("ADDLOCATION")) {
				String address = "";
				String user = "";

				System.out.print("address: ");
				if (scanner.hasNextLine()) {
					address = scanner.nextLine();
				}
				System.out.print("user: ");
				if (scanner.hasNextLine()) {
					user = scanner.nextLine();
				}
				method.voteLocation(address, user);
			}

      		// List address and users
			else if (action.equalsIgnoreCase("LISTALLLOCATIONS")) {
				System.out.println(method.listAllLocations());
			}

      		// List address, users, votes
			else if (action.equalsIgnoreCase("LISTLOCATIONS")) {
				System.out.println(method.listLocations());
			}

      		// Submit a claim that you have cleaned an area
			else if (action.equalsIgnoreCase("REMOVELOCATION")) {
				String address = "";
				String user = "";

				System.out.print("address: ");
				if (scanner.hasNextLine()) {
					address = scanner.nextLine();
				}
				System.out.print("user: ");
				if (scanner.hasNextLine()) {
					user = scanner.nextLine();
				}
				Method.removeLocation(address, user);
			}
			System.out.print("\n-> ");
		}
	}
}