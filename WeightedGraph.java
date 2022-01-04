import java.util.ArrayList;
import java.util.HashMap;

public class WeightedGraph {
    private HashMap<String, HashMap<String, Integer>> dirGraph = new HashMap<>();

    //Adds a vertex to the graph
    public void addVertex(String node) {
        HashMap<String, Integer> connections= new HashMap<>();
        dirGraph.put(node, connections);
    }

    //Add an edge or connection to the graph
    public void addEdge(String source, String dest, Integer weight) {
        dirGraph.get(source).put(dest,weight);
    }

    //Print the graph vertices, edges, and weights
    public void printGraph() {
        for (String source : dirGraph.keySet()) {
            HashMap<String, Integer> sourceMap = dirGraph.get(source);
            for (String dest : sourceMap.keySet()) {
                Integer weight = sourceMap.get(dest);
                System.out.printf("\nNode:%s is connected to:%s with weight:%s", source, dest, weight);
            }
        }
    }

    //Gets the keys to the source hash map
    public ArrayList<String> getSourceKeys() {
        ArrayList<String> sourceKeys = new ArrayList<>();
        for (String source : dirGraph.keySet()) {
            sourceKeys.add(source);
        }
        return sourceKeys;
    }

    //Finds the secondary route after finding route from source city to destination
    public ArrayList<String> getPossibleRoutes(String source, String startCity) {
        ArrayList<String> routes = new ArrayList<>();
        HashMap<String, Integer> sourceNode = dirGraph.get(source);

        if (sourceNode.containsKey(startCity)){
            HashMap<String, Integer> secNode = dirGraph.get(startCity);
            for (String secDestNode : secNode.keySet()) {
                String route = createRouteSyntax(startCity, sourceNode, secDestNode, secNode);
                routes.add(route);
            }
        }
        return routes;
    }

    //Creates the route syntax
    private String createRouteSyntax(String destNode, HashMap<String, Integer> startNode,
                                     String secDestNode, HashMap<String, Integer> secNode) {
        String route;
        int sum = startNode.get(destNode) + secNode.get(secDestNode);
        route = String.format("%s : %s+%s=%s", secDestNode, startNode.get(destNode), secNode.get(secDestNode), sum);
        return route;
    }
}
