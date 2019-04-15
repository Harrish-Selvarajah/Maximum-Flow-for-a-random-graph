import java.util.LinkedList;

import java.util.Queue;


public class MaxFlow {

    private int[] parent;
    private Queue<Integer> queue;
    private int numberOfVertices;
    private boolean[] visited;


    public MaxFlow (int numberOfVertices) {

        this.numberOfVertices = numberOfVertices;
        this.queue = new LinkedList<Integer>();
        parent = new int[numberOfVertices + 1];
        visited = new boolean[numberOfVertices + 1];
    }


    public boolean bfs (int source, int target, int graph[][]) {

        boolean pathFound = false;
        int destination, element;

        for (int vertex = 1; vertex <= numberOfVertices; vertex++) {

            parent[vertex] = -1;
            visited[vertex] = false;
        }

        queue.add(source);
        parent[source] = -1;
        visited[source] = true;

        while (!queue.isEmpty()) {

            element = queue.remove();
            destination = 1;

            while (destination <= numberOfVertices) {

                if (graph[element][destination] > 0 &&  !visited[destination]) {
                    parent[destination] = element;
                    queue.add(destination);
                    visited[destination] = true;
                }

                destination++;
            }
        }

        if (visited[target]) {
            pathFound = true;
        }
        // If we reached sink in BFS starting from source, then
        // return true, else false
        return pathFound;
    }

    // Returns tne maximum flow from s to t in the given graph
    public int  networkFlow (int graph[][], int source, int destination) {

        int u, v;
        int maxFlow = 0;
        int pathFlow;
        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph

        // Residual graph where residualGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If residualGraph[i][j] is 0, then there is
        // not)
        int[][] residualGraph = new int[numberOfVertices + 1][numberOfVertices + 1];

        for (int sourceVertex = 1; sourceVertex <= numberOfVertices; sourceVertex++){

            for (int destinationVertex = 1; destinationVertex <= numberOfVertices; destinationVertex++) {

                residualGraph[sourceVertex][destinationVertex] = graph[sourceVertex][destinationVertex];
            }
        }

        while (bfs(source, destination, residualGraph)) {
            // Find minimum residual capacity of the edges
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            pathFlow = Integer.MAX_VALUE;

            for (v = destination; v != source; v = parent[v]) {

                u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
            }

            for (v = destination; v != source; v = parent[v]){

                u = parent[v];

                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }
            // Add path flow to overall flow
            maxFlow += pathFlow;
        }

        return maxFlow;

    }
}



