import java.util.*;
public class MangoSellerSearch {

    // Graph representation using HashMap
    private static Map<String, List<String>> graph = new HashMap<>();
    static {
        // Initialize the graph (based on your picture)
        graph.put("you", Arrays.asList("alice", "bob", "claire"));
        graph.put("bob", Arrays.asList("anuj", "peggy"));
        graph.put("alice", Arrays.asList("peggy"));
        graph.put("claire", Arrays.asList("thom", "jonny"));
        graph.put("anuj", Collections.emptyList());
        graph.put("peggy", Collections.emptyList());
        graph.put("thom", Collections.emptyList());
        graph.put("jonny", Collections.emptyList());
    }
    // Equivalent to person_is_seller function
    public static boolean personIsSeller(String name) {
        return name.charAt(name.length() - 1) == 'm';
    }
    // Breadth-first search implementation
    public static boolean search(String name) {
        Queue<String> searchQueue = new LinkedList<>();
        Set<String> searched = new HashSet<>();
        // Add initial connections to queue
        searchQueue.addAll(graph.get(name));

        while (!searchQueue.isEmpty()) {
            String person = searchQueue.poll();
            // Only search if not already searched
            if (!searched.contains(person)) {
                if (personIsSeller(person)) {
                    System.out.println(person + " is a mango seller!");
                    return true;
                } else {
                    // Add this person's connections to the queue
                    searchQueue.addAll(graph.get(person));
                    searched.add(person); // Mark as searched
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        search("you");
    }
}
