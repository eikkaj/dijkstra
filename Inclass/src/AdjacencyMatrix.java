import java.util.*;

public class AdjacencyMatrix {
	
	private GraphEdge [][] graph;
	private boolean isDirected;
	
	//number of vertices
	private int num_vertices;
	//keeps track of which vertices are done
	private ArrayList<Integer> settled, unsettled;
	
	//array of distance for particular node
	private int[] distances;
	//keeps track of the paths to each node
	private int[] links;
	
	public AdjacencyMatrix(int vertices, boolean isDirected) {
		//creates matrix of a specific number of vertices, none are adjacent initially,
		//decision between being directed or not
		this.isDirected = isDirected;
		this.num_vertices = vertices;
		
		graph = new GraphEdge[vertices][vertices];
		
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				graph[i][j] = new GraphEdge(false);
			}
		}
		
		//create the settled and unsettled arraylists
		settled = new ArrayList<Integer>();
		unsettled = new ArrayList<Integer>();
		
		distances = new int[vertices];
		links = new int[vertices];	
	}
	
	public void addAdjacency(int a, int b, int weight) {
		graph[a][b].setExists(true);
		graph[a][b].setWeight(weight);
		
		if (!isDirected) {
			graph[b][a].setExists(true);
			graph[b][a].setWeight(weight);
		}
	}
	
	public void solver(int s) {
		//solve shortest path problem starting at node s
		int min_distance_value, min_distance_vertex;
		
		//add all of the nodes in matrix to unsettled array, set distance value to infinity
		//set links array to -1 indicating no set path yet
		for (int i = 0; i < num_vertices; i++) {
			unsettled.add(i);
			distances[i] = Integer.MAX_VALUE;
			links[i] = -1;
		}
		distances[s] = 0; //path to first node is always 0
		
		//keep going into unsettled bucket is empty, meaning all work is done
		while (!unsettled.isEmpty()) {
            // Find the vertex x that's unsettled and that has the least
            // distance from s.
			min_distance_vertex = unsettled.get(0);
			min_distance_value = distances[min_distance_vertex];
			
			for (int i=1; i<unsettled.size(); i++) {
				if (distances[unsettled.get(i)] < min_distance_value) {
					min_distance_value = distances[unsettled.get(i)];
					min_distance_vertex = unsettled.get(i);
				}
			}
			
            // Add x to the settled list of vertices and remove it from the
            // unsettled.
			unsettled.remove((Object)(min_distance_vertex));
			settled.add(min_distance_vertex);
			
            // Look at every vertex j adjacent to min_distance_vertex that's
            // not settled; if d(min_distance_vertex)
            // + d(min_distance_vertex,j) < d(j), then replace d(j),
            // and set links[j] = min_distance_vertex.
			
			for (int j = 0; j < num_vertices; j++) {
				//if graph contains [shortest distance][j] and j hasnt been settled yet (testing for adjacency)
				if (graph[min_distance_vertex][j].isExists() && unsettled.contains(j)) {
					if (distances[min_distance_vertex] + graph[min_distance_vertex][j].getWeight() < distances[j]) {
						distances[j] = distances[min_distance_vertex] + graph[min_distance_vertex][j].getWeight();
                        links[j] = min_distance_vertex;
					}
				}
			}
		}
	}
	
	public int[] getDistances() {
		return distances;
	}
	
	public int[] getLinks() {
		return links;
	}
	
	public String toString() {
		String rtn = "";
		
		rtn += "    ";
		for (int i = 0; i < graph.length; i++) {
			rtn += "V" + i + " ";
		}
		rtn += "\n";
		
		for (int i = 0; i < graph.length; i++) {
			rtn += "V" + i + ": ";
			for (int j = 0; j < graph.length; j++) {
				if (graph[i][j].isExists()) {
					rtn += "T  ";
				} else {
					rtn += "F  ";
				}
			}
			rtn += "\n";
		}
		return rtn;
	}

}
