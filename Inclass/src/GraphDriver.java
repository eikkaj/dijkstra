
import java.util.Stack;

public class GraphDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GraphDriver me = new GraphDriver();
		me.doIt();
	}
	
	public void doIt() {

        int GRAPH_SIZE = 10;
        int[] distances;
        int[] links;
        AdjacencyMatrix myGraph;

        for (int v = 0; v < GRAPH_SIZE; v++) {
		
            myGraph = new AdjacencyMatrix(GRAPH_SIZE, true);

    //		System.out.println(myGraph);

            myGraph.addAdjacency(0, 1, 3);
            myGraph.addAdjacency(0, 2, 5);
            myGraph.addAdjacency(1, 5, 14);
            myGraph.addAdjacency(1, 7, 7);
            myGraph.addAdjacency(2, 9, 12);
            myGraph.addAdjacency(2, 4, 1);
            myGraph.addAdjacency(2, 7, 5);
            myGraph.addAdjacency(3, 4, 4);
            myGraph.addAdjacency(4, 6, 2);
            myGraph.addAdjacency(5, 2, 2);
            myGraph.addAdjacency(6, 5, 7);
            myGraph.addAdjacency(7, 8, 6);
            myGraph.addAdjacency(8, 5, 3);
            myGraph.addAdjacency(9, 7, 8);
            myGraph.addAdjacency(9, 3, 4);

//            System.out.println(myGraph);

            myGraph.solver(v);
            distances = myGraph.getDistances();

            System.out.println("Distance list for node " + v);

            for (int i = 0; i < distances.length; i++) {

                if (distances[i] == Integer.MAX_VALUE || distances[i] ==
                        -Integer.MAX_VALUE+1)
                    System.out.print("Inf ");
                else
                    System.out.print(distances[i] + " ");

            }

            System.out.println();

            for(int j = 0; j < GRAPH_SIZE; j++) {

                System.out.println("Optimal node path from " + v + " to " + j);

                if (distances[j] == Integer.MAX_VALUE || distances[j] ==
                        -Integer.MAX_VALUE+1) {
                    System.out.println("No path exists.");
                    continue;
                }

                links = myGraph.getLinks();
                Stack<Integer> stack = new Stack<Integer>();
                int current_vertex = j;

                while (current_vertex != v) {
                    stack.push(current_vertex);
                    current_vertex = links[current_vertex];
                }
                stack.push(v);

                while (!stack.isEmpty()) {
                    System.out.print(stack.pop() + " ");
                }
                System.out.println();

            }

        }
		
	}

}
