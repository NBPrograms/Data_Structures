import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

public class Truck_Main {
    public static void main(String[] args) {
        //Filename (change based on need)
        String fname = "input/truckOrder.txt";

        //Main Body
        ArrayList<String> fileData = opensTheFile(fname);
        Stack<Truck_Object> trucks = readsTruckObjects(fileData);
        Truck_Driver.startTruckProgram(trucks);
    }

    public static Stack<Truck_Object> readsTruckObjects(ArrayList<String> fileData) {
        //Initializations
        Stack<Truck_Object> trucks = new Stack<>();
        int ct = 0;
        int invalid = 0;

        //Parses fileData, puts attributes in a truck object, and pushes objects unto a stack
        for (String row : fileData) {
            String[] toks = row.split(",");

            try {
                int id = Integer.parseInt(toks[0]);
                int zip = Integer.parseInt(toks[1]);
                int numPackages = Integer.parseInt(toks[2]);
                ++ct;

                Truck_Object truck = new Truck_Object(id, zip, numPackages, ct);
                trucks.push(truck);
            } catch (NumberFormatException e) {
                invalid++;
                e.getMessage();
            }
        }
        return trucks;
    }

    public static ArrayList<String> opensTheFile(String fname){
        //Initializations
        ArrayList<String> fileRows = new ArrayList<>();
        FileIOManager file = new FileIOManager(fname);

        //Opens file
        try {
            file.setFileData();
            fileRows = file.getFileData();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return fileRows;
    }
}