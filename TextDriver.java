import java.io.IOException;
import java.util.Scanner;

/**
 * @author Kathryn Bondoc
 * @version 2024-08-03
 */

public class TextDriver {

	public static void main(String[] args) throws IOException {

		Location location = new Location();

		Scanner scanner = new Scanner(System.in);
		System.out.print("->");

		while (scanner.hasNextLine()) {
			String action = scanner.nextLine();

      		if (action == null || action.equals("")) {		
       			System.out.print("\n>");
        		continue;
    		}
      		// Quit the App
      		else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
        		return;

			else if (action.equalsIgnoreCase("ADDLOCATION")) {
				String address = "";
				String user = "";

				System.out.print("address:");
				if (scanner.hasNextLine()) {
					address = scanner.nextLine();
				}
				System.out.print("user:");
				if (scanner.hasNextLine()) {
					user = scanner.nextLine();
				}
				location.addLocation(address, user);
			}

			else if (action.equalsIgnoreCase("LISTALLLOCATIONS")) {
				location.listAllLocations();
			}

			else if (action.equalsIgnoreCase("LISTLOCATIONLIST")) {
				location.listLocationList();
			}


			System.out.print("\n->");

		}

	}
}
