import java.util.*;

class GraphBFS {
    private int vertices;
    private List<List<Integer>> adjList;
    private Map<String, Integer> vertexMap;
    private Map<Integer, String> reverseMap;
    
    public GraphBFS(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) adjList.add(new ArrayList<>());
        vertexMap = new HashMap<>();
        reverseMap = new HashMap<>();
    }
    
    public void addVertex(int index, String name) {
        vertexMap.put(name, index);
        reverseMap.put(index, name);
    }
    
    public void addEdge(String u, String v) {
        int uIndex = vertexMap.get(u), vIndex = vertexMap.get(v);
        adjList.get(uIndex).add(vIndex);
        adjList.get(vIndex).add(uIndex);
    }
    
    public String getVertexName(int index) { return reverseMap.get(index); }
    public List<Integer> getNeighbors(int vertex) { return adjList.get(vertex); }
    public int getVertexIndex(String name) { return vertexMap.get(name); }
}

public class BFS_Rute {
    private GraphBFS graph;
    private int targetVertex;
    private int nodeCount;
    private List<List<Integer>> allRoutes;
    
    public BFS_Rute(GraphBFS graph) {
        this.graph = graph;
        this.allRoutes = new ArrayList<>();
    }
    
    public void findAllRoutesToZ(int startVertex) {
        this.targetVertex = graph.getVertexIndex("Z");
        this.nodeCount = 0;
        this.allRoutes.clear();
        
        System.out.println("\n==========================================");
        System.out.println("BFS - SEMUA RUTE KE Z");
        System.out.println("==========================================");
        
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> startPath = new ArrayList<>();
        startPath.add(startVertex);
        queue.add(startPath);
        
        while (!queue.isEmpty()) {
            List<Integer> currentPath = queue.poll();
            int currentVertex = currentPath.get(currentPath.size() - 1);
            nodeCount++;
            
            if (currentVertex == targetVertex) {
                allRoutes.add(new ArrayList<>(currentPath));
                continue;
            }
            
            for (int neighbor : graph.getNeighbors(currentVertex)) {
                if (!currentPath.contains(neighbor)) {
                    List<Integer> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }
        
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
    
    public static void main(String[] args) {
        GraphBFS graph = createGraph();
        
        // TAMPILKAN STRUKTUR GRAPH
        System.out.println("\n=== STRUKTUR GRAPH ===");
        System.out.println("S -> A, B");
        System.out.println("A -> C, D");
        System.out.println("B -> E, F");
        System.out.println("C -> Z, D");
        System.out.println("D -> Z, C");
        System.out.println("E -> Z, F");
        System.out.println("F -> Z, E");
        System.out.println("Z -> C, D, E, F");
        
        BFS_Rute bfs = new BFS_Rute(graph);
        bfs.findAllRoutesToZ(graph.getVertexIndex("S"));
    }
    
    private static GraphBFS createGraph() {
        GraphBFS graph = new GraphBFS(8);
        graph.addVertex(0, "S");
        graph.addVertex(1, "A");
        graph.addVertex(2, "B");
        graph.addVertex(3, "C");
        graph.addVertex(4, "D");
        graph.addVertex(5, "E");
        graph.addVertex(6, "F");
        graph.addVertex(7, "Z");
        
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