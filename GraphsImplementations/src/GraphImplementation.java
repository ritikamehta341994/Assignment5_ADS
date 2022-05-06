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
	
	
	/**
	 * This method implements the Dijkstra's algorithm for shortest path calculation from source node to every other node
	 * We use the priority queue implementation
	 * @param graphVertices
	 * @param isExperimentalAnalysis
	 */
	public static void dijkstraAlgorithm(ArrayList<ArrayList<GraphEdge>> graphVertices,boolean isExperimentalAnalysis,int source) {
		
		int numberOfVertices = graphVertices.size(); // initialize the number of vertices in the graph 
		
		PriorityQueue<GraphEdge> pq = new PriorityQueue<>(numberOfVertices,new GraphEdge()); // initialize the priority queue
				
		Set<GraphEdge> settled = new HashSet<>(); // set to track the vertices which has been visited and parsed
				
		int distance[] = new int[graphVertices.size()];// array storing the shortest distance of every vertex from the source vertex
		
		//initialise the distance array to store the maximum value
		for(int i=0;i<distance.length;i++) 
			distance[i] = Integer.MAX_VALUE;
		
		//distance between the source and itself is set to 0
		distance[source] = 0;
		
		//Add source node to the priority queue
		pq.add(new GraphEdge(-1, source, 0));
		
		//Keep traversing as long as all the vertices hae been visited
		while(settled.size()!=numberOfVertices) {
			
			//Exit the method when the priority queue is empty
			if(pq.isEmpty())
				return;
			
			//Remove the minimum distance node from the priority queue
			GraphEdge vertex = pq.remove();
			
			//Process the neighboring vertices if they have not been traversed
			if(settled.contains(vertex))
				continue;
			settled.add(vertex);
			
			processNeighborsOfVertex(vertex,graphVertices,settled,distance,pq,source);
			
		}
		//Print the shortest distance information from the source vertex for every vertex in the graph 
		if(!isExperimentalAnalysis) {
			System.out.println("The shorted path from source vertex : "+source);
	        
			for (int i = 0; i < distance.length; i++)
				if(distance[i] != Integer.MAX_VALUE)
					System.out.println(source + " to " + i + " is "
	                             + distance[i]);
		}
		

	}
	
	/**
	 * This method processes the neighbors of the passed vertex and updates the minimum distance of the vertex from the source
	 * @param vertex
	 * @param graph
	 * @param settled
	 * @param dist
	 * @param pq
	 * @param source
	 */
	public static void processNeighborsOfVertex(GraphEdge vertex,
			ArrayList<ArrayList<GraphEdge>> graph,
			Set<GraphEdge> settled,
			int[] dist,
			PriorityQueue<GraphEdge> pq,int source) {
		int edgeDist = 0; // initialise the edge distance
		int newDist = 0; // initialise the weight distance
		
		 // Traverse all the neighbors of the input vertex
        for (int i = 0; i < graph.get(vertex.getDestination()).size(); i++) {
            GraphEdge vertexToProcess = graph.get(vertex.getDestination()).get(i);
 
            // If the vertex has not been processed
            if (!settled.contains(vertexToProcess)) {
            	//fetch its edge weight
            	edgeDist = vertexToProcess.getWeight();
            	//Sum up the edge weight with the current weight of the vertex in distance matrix
            	newDist = dist[vertex.getDestination()] + edgeDist;
 
                // If new distance is less than the existing one, update the same
                if (newDist < dist[vertexToProcess.getDestination()])
                    dist[vertexToProcess.getDestination()] = newDist;
 
                // Add the current node to the queue
                pq.add(new GraphEdge(-1,vertexToProcess.getDestination(), dist[vertexToProcess.getDestination()]));
            }
        }

        
                
	}


}
