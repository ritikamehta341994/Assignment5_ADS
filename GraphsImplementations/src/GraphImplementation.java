import java.util.*;

/**
 * This class contains implementations of various graph algorithms:
 * 1. Breadth first search
 * 2. Depth first search
 * 3. Dijkstra's Algorithm
 * @author ritzm
 *
 */
public class GraphImplementation implements Graph{

	/***
	 * 
	 * @param graphVertices : arraylist of arraylist of GraphEdge representing the graph
	 * @param graphEdge : edge to the added into the graph
	 * @param isDirected : whether the graph is directed or undirected
	 */
	
	@Override
	public void addEdges(ArrayList<ArrayList<GraphEdge>> graphVertices,GraphEdge graphEdge,boolean isDirected ) {
		//Implementation for directed graph
		if(isDirected) {
			graphVertices.get(graphEdge.getSource()).add(graphEdge);
		}
		
	}
	
	
	/***
	 * 
	 * @param graphVertices : Display the graph for input arraylist containing the details of graph vertices
	 */
	
	@Override
	public void printGraph(ArrayList<ArrayList<GraphEdge>> graphVertices) {
		for(int i=0;i<graphVertices.size();i++) {
			
			if(graphVertices.get(i).size() >0) {
				//Print connections for each of the vertex
				System.out.println("\nFor Vertice : "+i);
				System.out.println("----------------------------------------------------------------------------------------------------------------------------");
				System.out.print("Connections");
				for(int j=0;j<graphVertices.get(i).size();j++) {
					//Print vertex and the edge weight connecting to the vertex
					System.out.print("->{"+graphVertices.get(i).get(j).getDestination()+","
							+ ""+graphVertices.get(i).get(j).getWeight()+"}");
				}
				System.out.println("\n----------------------------------------------------------------------------------------------------------------------------");
			}
		}
	}
	
	/**
	 * This method performs the Breadth First Search on input graph starting from the startVertex
	 * @param graphVertices : Graph input as arraylist of arraylist of GraphEdge
	 * @param startVertex : start vertex for BFS
	 * @param isExperimentalAnalysis : true if this is for experimental analysis
	 */
	public static void breadthFirstSearch(ArrayList<ArrayList<GraphEdge>> graphVertices,int startVertex,boolean isExperimentalAnalysis) {
		//Queue to store the vertex to be traversed
		Queue<GraphEdge> queue = new LinkedList<>();
		//Add initial vertex into the queue
		queue.add(new GraphEdge(startVertex, startVertex, 0));
		//Have an array to track all the visited vertex
		boolean[] visited = new boolean[graphVertices.size()];
		//Default the visited array to false
		for(int i = 0;i<visited.length;i++) {
			visited[i] = false;
		}
		long fareAmount = 0;
		//While the queue is not empty pop the vertex at the head of the queue and traverse its neighbors
		while(!queue.isEmpty()) {
			//fetch the vertex at the head of the queue
			GraphEdge vertex = queue.poll();
			//visit the vertex only if it has not been visited
			if(visited[vertex.getDestination()] != true) {
				//mark the vertex as visited = true
				visited[vertex.getDestination()] = true;
				fareAmount += vertex.getWeight();
				//Print the vertex
				if(!isExperimentalAnalysis) {
					System.out.print(vertex.getDestination()+"->");
				}
				//If the vertex has neighbors , add them into the queue
				if(graphVertices.get(vertex.getDestination()).size()>0) {
					graphVertices.get(vertex.getDestination()).forEach(element-> queue.add(new GraphEdge(-1, element.getDestination(), element.getWeight())));
				}
			}
			
		}

		if(!isExperimentalAnalysis) {
			System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\nBFS Total Fare : "+fareAmount);
		}
		
	}
	
	/**
	 * This method performs the Depth First Search on input graph starting from the startVertex
	 * @param graphVertices : Graph input as arraylist of arraylist of GraphEdge
	 * @param startVertex : start vertex for DFS
	 * @param isExperimentalAnalysis : true if this is for experimental analysis
	 */
	public static void depthFirstSearch(ArrayList<ArrayList<GraphEdge>> graphVertices,int startVertex,boolean isExperimentalAnalysis) {
		//stack to store the vertex to be traversed
		Deque<GraphEdge> stack = new LinkedList<>();
		stack.push(new GraphEdge(startVertex, startVertex, 0));
		//Have an array to track all the visited vertex
		boolean[] visited = new boolean[graphVertices.size()];
		//Default the visited array to false
		for(int i = 0;i<visited.length;i++) {
			visited[i] = false;
		}
		long fareAmount = 0;
		if(!isExperimentalAnalysis)
			{System.out.println();}
		while(!stack.isEmpty()) {
			GraphEdge vertex = stack.pop();
			//visit the vertex only if it has not been visited
			if(visited[vertex.getDestination()] != true) {
				//mark the vertex as visited
				visited[vertex.getDestination()] = true;
				
				fareAmount += vertex.getWeight();
				//print the vertex
				if(!isExperimentalAnalysis)
					System.out.print(vertex.getDestination()+"->");
				//while the vertex has neighbors, push them onto the stack
				if(graphVertices.get(vertex.getDestination()).size()>0) {
					graphVertices.get(vertex.getDestination()).forEach(element-> stack.push(new GraphEdge(-1,element.getDestination(),element.getWeight())));
				}
			}
		}
		
		if(!isExperimentalAnalysis) {
			System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\nDFS Total Fare : "+fareAmount);
		}
	}
	
	
	


}
