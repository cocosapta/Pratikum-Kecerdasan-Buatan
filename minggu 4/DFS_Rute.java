import java.util.*;

class GraphDFS {
    private int vertices;
    private List<List<Integer>> adjList;
    private Map<String, Integer> vertexMap;
    private Map<Integer, String> reverseMap;
    
    public GraphDFS(int vertices) {
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

public class DFS_Rute {
    private GraphDFS graph;
    private int targetVertex;
    private int nodeCount;
    private List<List<Integer>> allRoutes;
    private String targetName;
    
    public DFS_Rute(GraphDFS graph) {
        this.graph = graph;
        this.allRoutes = new ArrayList<>();
    }
    
    public void findAllRoutes(int startVertex, String target) {
        this.targetName = target;
        this.targetVertex = graph.getVertexIndex(target);
        this.nodeCount = 0;
        this.allRoutes.clear();
        
        System.out.println("\n==========================================");
        System.out.println("DFS - SEMUA RUTE KE " + target);
        System.out.println("==========================================");
        
        List<Integer> startPath = new ArrayList<>();
        startPath.add(startVertex);
        dfs(startVertex, startPath);
        
        if (allRoutes.isEmpty()) {
            System.out.println("\nTidak ada rute menuju " + target);
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
    
    private void dfs(int current, List<Integer> path) {
        nodeCount++;
        
        if (current == targetVertex) {
            allRoutes.add(new ArrayList<>(path));
            return;
        }
        
        for (int neighbor : graph.getNeighbors(current)) {
            if (!path.contains(neighbor)) {
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(neighbor);
                dfs(neighbor, newPath);
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        GraphDFS graph = createGraph();
        
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
        
        System.out.println("\nNode yang tersedia: S, A, B, C, D, E, F, Z");
        
        // LANGSUNG TULIS HURUF TUJUAN
        System.out.print("\nMasukkan target tujuan (contoh: Z atau A atau F): ");
        String target = sc.next().toUpperCase();
        
        DFS_Rute dfs = new DFS_Rute(graph);
        dfs.findAllRoutes(graph.getVertexIndex("S"), target);
        
        sc.close();
    }
    
    private static GraphDFS createGraph() {
        GraphDFS graph = new GraphDFS(8);
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