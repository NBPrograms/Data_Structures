import java.util.ArrayList;
import java.util.Scanner;

public class Graph_Driver {
    public static void main(String[] args) {
        int vertices = 6;
        System.out.printf("Started");

        //Build and print graph
        WeightedGraph wGraph = buildgraph(vertices);
        wGraph.printGraph();

        //Get source city and find its secondary routes
        String source = "Chicago";
        String startCity = getStartCity(wGraph.getSourceKeys());
        ArrayList<String> routes = wGraph.getPossibleRoutes(source, startCity);
        showTheRoutes(source, startCity, routes);
    }

    private static void showTheRoutes(String source, String startCity, ArrayList<String> routes) {
        System.out.printf("\nStart Node:%s to:%s", source, startCity);
        System.out.printf("\n-------------------Cities connected to %s starting at %s", startCity, source);

        //Check if there are routes
        if (routes.isEmpty()) {
            System.out.printf("\n-------------------There are no direct routes from %s to %s.", source, startCity);
        } else {
            for (String route : routes) {
                System.out.printf("\n-------------------%s", route);
            }
        }
    }

    private static String getStartCity(ArrayList<String> sourceKeys) {
        //Create CSV list
        String cityList = createCSVList(sourceKeys);

        while (true) {
            //Get scanner input
            Scanner input = new Scanner(System.in);
            System.out.printf("\nSelect a source city (%s)->", cityList);
            String city = input.nextLine();

            //Check scanner input
            for (String source : sourceKeys) {
                if (city.equals(source)) {
                    return city;
                }
            }
        }
    }

    private static String createCSVList(ArrayList<String> sourceKeys) {
        String csvList = null;
        boolean first = true;

        for (String item : sourceKeys) {
            if (first) {
                csvList = item;
                first = false;
            } else {
                csvList += ", " + item;
            }
        }
        return csvList;
    }

    private static WeightedGraph buildgraph(int vertices) {
        WeightedGraph graph = new WeightedGraph();

        //Add vertices
        graph.addVertex("Chicago");
        graph.addVertex("Dallas");
        graph.addVertex("Atlanta");
        graph.addVertex("New York");
        graph.addVertex("Houston");
        graph.addVertex("Orlando");

        //Add edges
        graph.addEdge("Chicago", "Dallas", 968);
        graph.addEdge("Chicago", "Atlanta", 718);
        graph.addEdge("Chicago", "New York", 790);
        graph.addEdge("Dallas", "Houston", 239);
        graph.addEdge("Dallas", "Orlando", 1120);
        graph.addEdge("Atlanta", "Dallas", 781);
        graph.addEdge("Atlanta", "New York", 870);
        graph.addEdge("Atlanta", "Orlando", 438);
        graph.addEdge("New York", "Houston", 1647);
        graph.addEdge("New York", "Orlando", 1080);
        graph.addEdge("Houston", "Orlando", 967);

        return graph;
    }
}
