public class Truck_Object {
    private int truckID;
    private int zip;
    private int numPackages;
    private int pos;

    //Constructor
    public Truck_Object(int truckID, int zip, int numPackages, int pos) {
        this.truckID = truckID;
        this.zip = zip;
        this.numPackages = numPackages;
        this.pos = pos;
    }

    //Getters and Setter
    public int getTruckID() {
        return truckID;
    }

    public int getZip() {
        return zip;
    }

    public int getNumPackages() {
        return numPackages;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getPos(){
        return pos;
    }
}