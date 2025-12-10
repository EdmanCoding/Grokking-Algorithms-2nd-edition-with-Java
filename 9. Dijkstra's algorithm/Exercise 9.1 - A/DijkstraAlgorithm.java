import java.util.*;
// the fastest path for the exercise 9.1 - A. graph
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        // Set up the graph
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        // Add edges from START
        Map<String, Integer> startEdges = new HashMap<>();
        startEdges.put("A", 5);
        startEdges.put("B", 2);
        graph.put("START", startEdges);

        // Add edges from A
        Map<String, Integer> aEdges = new HashMap<>();
        aEdges.put("C", 4);
        aEdges.put("D", 2);
        graph.put("A", aEdges);

        // Add edges from B
        Map<String, Integer> bEdges = new HashMap<>();
        bEdges.put("A", 8);
        bEdges.put("D", 7);
        graph.put("B", bEdges);

        // Add edges from C
        Map<String, Integer> cEdges = new HashMap<>();
        cEdges.put("D", 6);
        cEdges.put("FIN", 3);
        graph.put("C", cEdges);

        // Add edges from D
        Map<String, Integer> dEdges = new HashMap<>();
        dEdges.put("FIN", 1);
        graph.put("D", dEdges);

        // Add edges from FIN (no outgoing edges)
        Map<String, Integer> finEdges = new HashMap<>();
        graph.put("FIN", finEdges);

        // Initialize costs table
        Map<String, Integer> costs = new HashMap<>();
        costs.put("A", 5);
        costs.put("B", 2);
        costs.put("C", Integer.MAX_VALUE); // Infinity
        costs.put("D", Integer.MAX_VALUE); // Infinity
        costs.put("FIN", Integer.MAX_VALUE); // Infinity

        // Initialize parents table
        Map<String, String> parents = new HashMap<>();
        parents.put("A", "START");
        parents.put("B", "START");
        parents.put("C", null);
        parents.put("D", null);
        parents.put("FIN", null);

        // Track processed nodes
        Set<String> processed = new HashSet<>();

        // Run Dijkstra's algorithm
        dijkstra(graph, costs, parents, processed);

        // Print the shortest path to FIN
        System.out.println("Shortest path to FIN:");
        printPath(parents, "FIN");

        // Print the cost to FIN
        System.out.println("Total cost to FIN: " + costs.get("FIN"));
    }

    private static void dijkstra(Map<String, Map<String, Integer>> graph,
                                 Map<String, Integer> costs,
                                 Map<String, String> parents,
                                 Set<String> processed) {
        String node = findLowestCostNode(costs, processed);
        while (node != null) {
            int cost = costs.get(node);
            Map<String, Integer> neighbors = graph.get(node);

            // Update costs for all neighbors
            for (String n : neighbors.keySet()) {
                int newCost = cost + neighbors.get(n);
                // If it's cheaper to get to this neighbor through this node
                if (costs.get(n) > newCost) {
                    // Update the cost for the neighbor
                    costs.put(n, newCost);
                    // This node becomes the new parent for this neighbor
                    parents.put(n, node);
                }
            }
            // Mark this node as processed
            processed.add(node);
            // Find the next node to process
            node = findLowestCostNode(costs, processed);
        }
    }
    private static String findLowestCostNode(Map<String, Integer> costs, Set<String> processed) {
        int lowestCost = Integer.MAX_VALUE;
        String lowestCostNode = null;
        // Go through all nodes
        for (String node : costs.keySet()) {
            int cost = costs.get(node);
            // If it's the lowest cost so far and hasn't been processed yet
            if (cost < lowestCost && !processed.contains(node)) {
                lowestCost = cost;
                lowestCostNode = node;
            }
        }
        return lowestCostNode;
    }
    private static void printPath(Map<String, String> parents, String endNode) {
        List<String> path = new ArrayList<>();
        String currentNode = endNode;
        while (currentNode != null) {
            path.add(0, currentNode);
            currentNode = parents.get(currentNode);
        }
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}
