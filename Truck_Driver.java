import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Truck_Driver {
    public static void startTruckProgram(Stack<Truck_Object> trucks) {
        //Initialization and opening text for program
        String reprompt;
        outputTruckOrder(trucks);

        //Main Body that checks the stack, gets user input, and attempts to find truck from user input
        do {
            if (trucks.isEmpty()){
                System.out.printf("\nALL TRUCKS HAVE BEEN REMOVED! CANNOT SEARCH ZIP UNTIL TRUCKS RETURN!");
            } else {
                int userZip = getUserInputtedZipCode();
                findTruck(trucks, userZip);
            }
            reprompt = askToReprompt();
        } while (reprompt.equals("Y"));
    }

    private static String askToReprompt() {
        Scanner s = new Scanner(System.in);
        while (true){
            System.out.printf("\nWould you like to search for another zip (Y|N) ->");
            String ans = s.nextLine();
            if (ans.equals("Y") || ans.equals("N")) {
                return ans;
            }
        }
    }

    private static void findTruck(Stack<Truck_Object> trucks, int userZip) {
        //Initializations
        ArrayList<Truck_Object> trucksWithZip = new ArrayList<>();
        String zipCheck = "";
        int ct = 0;

        //Checks truck data with user input
        for (Truck_Object truck : trucks){
            if (userZip == truck.getZip()) {
                zipCheck = "!";
                trucksWithZip.add(truck);
            }
            ++ct;
        }

        //Outputs matching trucks, prompts to remove first (smallest position #) truck and reorders trucks
        String removePrompt;
        if (zipCheck.isEmpty()){
            System.out.printf("\nSorry, did not find a truck for zip:%s. Looked through %s trucks", userZip, ct);
        } else {
            outputTruckWithZip(trucksWithZip, userZip);
            removePrompt = askToRemove(userZip);
            if (removePrompt.equals("Y")) {
                removeAndOrderTrucks(trucks, trucksWithZip);
                if (!trucks.isEmpty()){
                    outputTruckOrder(trucks);
                }
            }
        }
    }

    private static void removeAndOrderTrucks(Stack<Truck_Object> trucks, ArrayList<Truck_Object> trucksWithZip) {
        //Initializations
        Stack<Truck_Object> poppedTrucks = new Stack<>();
        Truck_Object truckToRemove = trucksWithZip.get(0);
        String removeCheck = "";

        int truckStackSize = trucks.size();
        for (int i = truckStackSize; i > 0; i--) {
            //Finds position of the first truck with the user's zip and pops the truck from the stack
            if (trucks.get(i-1) == truckToRemove) {
                System.out.printf("\nREMOVING truck %s for zipcode %s in position %s with %s packages.", truckToRemove.getTruckID(),
                        truckToRemove.getZip(), truckToRemove.getPos(), truckToRemove.getNumPackages());
                trucks.pop();
                removeCheck = "!";
            }
            //Non-matching truck are popped and then pushed onto a separate stack (also resets their position)
            else if (removeCheck.isEmpty()) {
                Truck_Object popTruck = trucks.pop();
                System.out.printf("\nNeed to move truck %s for zip %s with %s packages in position %s.",
                        popTruck.getTruckID(), popTruck.getZip(), popTruck.getNumPackages(), popTruck.getPos());
                popTruck.setPos(i-1);
                poppedTrucks.push(popTruck);
            }
        }

        //Re-pushes popped trucks onto the original stack
        int poppedTruckStackSize = poppedTrucks.size();
        for (int i = poppedTruckStackSize; i > 0; i--) {
            trucks.push(poppedTrucks.get(i-1));
        }
    }

    private static String askToRemove(int zip) {
        Scanner s = new Scanner(System.in);
        while (true){
            System.out.printf("\nWould you like to remove the first truck for zip: %s (Y|N) ->", zip);
            String ans = s.nextLine();
            if (ans.equals("Y") || ans.equals("N")) {
                return ans;
            }
        }
    }

    private static void outputTruckWithZip(ArrayList<Truck_Object> trucksWithZip, int userZip) {
        //Prints all available trucks with user inputted zip
        System.out.printf("\n-----Found Trucks with Zipcode: %s-----", userZip);
        for (Truck_Object truck : trucksWithZip) {
            System.out.printf("\nTruck %s with %s packages is in position %s",
                    truck.getTruckID(), truck.getNumPackages(), truck.getPos());
        }
    }

    private static int getUserInputtedZipCode() {
        Scanner s = new Scanner(System.in);
        while (true) {
            try {
                System.out.printf("\n\nWhat ZipCode are you interested in? ->");
                int zip = Integer.parseInt(s.nextLine());
                return zip;
            } catch (NumberFormatException e) {
                System.out.printf("\nPlease enter a valid zipcode.");
            }
        }
    }

    private static void outputTruckOrder(Stack<Truck_Object> trucks) {
        //Prints all available trucks in stack order
        System.out.printf("\nHere is the Truck Order....");
        for (Truck_Object truck : trucks) {
            System.out.printf("\n----Pos:%s \tTruck ID:%s \tZip:%s \tPackages:%s-----",
                    truck.getPos(), truck.getTruckID(), truck.getZip(), truck.getNumPackages());
        }
    }
}