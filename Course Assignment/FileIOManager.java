import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIOManager {
    private ArrayList<String> fileRows = new ArrayList<>();
    private  String fName;

    public FileIOManager(String fName) {
        this.fName = fName;
    }

    public void setFileData() throws FileNotFoundException{
        //Gets pointer to the file
        String path = new File(fName).getAbsolutePath();
        File inFileHandle = new File(path);

        try {
            //Opens the file
            Scanner sc = new Scanner(inFileHandle);
            String line = null;

            while(sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.isEmpty()) {
                    continue;
                } else {
                    fileRows.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            //System.out.printf("\nFile Not Found");
            //throw exception - need in method signature as well
            String msg = "File Not Found";
            throw new FileNotFoundException(msg);
        }
    }

    public ArrayList<String> getFileData() {
        return fileRows;
    }
}
