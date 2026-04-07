import java.util.*;

class GraphHC {
    private int vertices;
    private List<List<Integer>> adjList;
    private Map<String, Integer> vertexMap;
    private Map<Integer, String> reverseMap;
    private Map<Integer, Integer> heuristicValues;
    
    public GraphHC(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) adjList.add(new ArrayList<>());
        vertexMap = new HashMap<>();
        reverseMap = new HashMap<>();
        heuristicValues = new HashMap<>();
    }
    
    public void addVertex(int index, String name) {
        vertexMap.put(name, index);
        reverseMap.put(index, name);
    }
    
    public void setHeuristic(int vertex, int value) {
        heuristicValues.put(vertex, value);
    }
    
    public void addEdge(String u, String v) {
        int uIndex = vertexMap.get(u), vIndex = vertexMap.get(v);
        adjList.get(uIndex).add(vIndex);
        adjList.get(vIndex).add(uIndex);
    }
    
    public String getVertexName(int index) { return reverseMap.get(index); }
    public List<Integer> getNeighbors(int vertex) { return adjList.get(vertex); }
    public int getVertexIndex(String name) { return vertexMap.get(name); }
    public int getHeuristic(int vertex) { return heuristicValues.getOrDefault(vertex, 100); }
}

public class HillClimbing_Rute {
    private GraphHC graph;
    private int targetVertex;
    private int nodeCount;
    private List<List<Integer>> allRoutes;
    
    public HillClimbing_Rute(GraphHC graph) {
        this.graph = graph;
        this.allRoutes = new ArrayList<>();
    }
    
    public void findAllRoutesToZ(int startVertex) {
        this.targetVertex = graph.getVertexIndex("Z");
        this.nodeCount = 0;
        this.allRoutes.clear();
        
        System.out.println("\n==========================================");
        System.out.println("HILL CLIMBING - SEMUA RUTE KE Z");
        System.out.println("==========================================");
        
        List<Integer> startPath = new ArrayList<>();
        startPath.add(startVertex);
        
        exploreAllPaths(startVertex, startPath);
        
        // TAMPILKAN HASIL
        if (allRoutes.isEmpty()) {
            System.out.println("\nTidak ada rute menuju Z");
        } else {
            for (int i = 0; i < allRoutes.size(); i++) {
                System.out.print("Rute " + (i+1) + ": ");
                for (int j = 0; j < allRoutes.get(i).size(); j++) {
                    if (j > 0) System.out.print(" -> ");
                    System.out.print(graph.getVertexName(allRoutes.get(i).get(j)));
                }
                System.out.println();
            }
        }
        System.out.println("\nTotal node dieksplorasi: " + nodeCount);
    }
    
    private void exploreAllPaths(int currentVertex, List<Integer> path) {
        nodeCount++;
        
        if (currentVertex == targetVertex) {
            allRoutes.add(new ArrayList<>(path));
            return;
        }
        
        List<Integer> neighbors = graph.getNeighbors(currentVertex);
        List<Integer> unvisitedNeighbors = new ArrayList<>();
        
        for (int neighbor : neighbors) {
            if (!path.contains(neighbor)) {
                unvisitedNeighbors.add(neighbor);
            }
        }
        
        if (unvisitedNeighbors.isEmpty()) {
            return;
        }
        
        // Urutkan berdasarkan heuristic (terkecil = terbaik / paling dekat ke Z)
        Collections.sort(unvisitedNeighbors, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return Integer.compare(graph.getHeuristic(a), graph.getHeuristic(b));
            }
        });
        
        for (int neighbor : unvisitedNeighbors) {
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(neighbor);
            exploreAllPaths(neighbor, newPath);
        }
    }
    
    public static void main(String[] args) {
        GraphHC graph = createGraph();
        
        // TAMPILKAN STRUKTUR GRAPH
        System.out.println("\n=== STRUKTUR GRAPH ===");
        System.out.println("S [h=10] -> A, B");
        System.out.println("A [h=8] -> C, D");
        System.out.println("B [h=6] -> E, F");
        System.out.println("C [h=3] -> Z, D");
        System.out.println("D [h=4] -> Z, C");
        System.out.println("E [h=2] -> Z, F");
        System.out.println("F [h=2] -> Z, E");
        System.out.println("Z [h=0] -> C, D, E, F");
        
        System.out.println("\n[ TARGET TUJUAN: Z ]");
        
        HillClimbing_Rute hc = new HillClimbing_Rute(graph);
        hc.findAllRoutesToZ(graph.getVertexIndex("S"));
    }
    
    private static GraphHC createGraph() {
        GraphHC graph = new GraphHC(8);
        
        graph.addVertex(0, "S");
        graph.addVertex(1, "A");
        graph.addVertex(2, "B");
        graph.addVertex(3, "C");
        graph.addVertex(4, "D");
        graph.addVertex(5, "E");
        graph.addVertex(6, "F");
        graph.addVertex(7, "Z");
        
        // Heuristik: semakin kecil nilai, semakin dekat ke Z
        graph.setHeuristic(0, 10);  // S
        graph.setHeuristic(1, 8);   // A
        graph.setHeuristic(2, 6);   // B
        graph.setHeuristic(3, 3);   // C
        graph.setHeuristic(4, 4);   // D
        graph.setHeuristic(5, 2);   // E
        graph.setHeuristic(6, 2);   // F
        graph.setHeuristic(7, 0);   // Z
        
        graph.addEdge("S", "A");
        graph.addEdge("S", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("B", "E");
        graph.addEdge("B", "F");
        graph.addEdge("C", "Z");
        graph.addEdge("D", "Z");
        graph.addEdge("E", "Z");
        graph.addEdge("F", "Z");
        graph.addEdge("C", "D");
        graph.addEdge("E", "F");
        
        return graph;
    }
}