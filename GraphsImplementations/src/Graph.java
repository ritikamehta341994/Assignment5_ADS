import java.util.ArrayList;

/***
 * Interface for graph implementations
 * @author ritzm
 *
 */
public interface Graph {
	
	//method to add edges into a graph
	public void addEdges(ArrayList<ArrayList<GraphEdge>> graphVertices,GraphEdge graphEdge,boolean isDirected );

	//method to print the graph
	public void printGraph(ArrayList<ArrayList<GraphEdge>> graphVertices);
	
}
