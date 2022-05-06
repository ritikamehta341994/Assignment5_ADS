import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/***
 * Driver class to call the graph algorithms
 * @author ritzm
 *
 */
public class Main {
	public static ArrayList<ArrayList<GraphEdge>> graphVertices = new ArrayList<>();
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		
		int operation = 0;
		
		
		while(operation != 9) {
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Enter the operation number you want to perform from the list below : "
					+ "\n1.\tCreate Graph"
					+ "\n2.\tPrint Graph"
					+ "\n3.\tBreadth First Search (BFS)"
					+ "\n4.\tExperimental Analysis Breadth First Search (BFS)"
					+ "\n5.\tDepth First Search (DFS)"
					+ "\n6.\tExperimental Analysis Depth First Search (DFS)"
					+ "\n7.\tDijkstra�s Algorithm for shortest path calculation"
					+ "\n8.\tExperimental Analysis Shortest Path - Dijkstra�s"
					+ "\n9.\tEnd\n");
			BufferedReader reader = new BufferedReader(
	            new InputStreamReader(System.in));
			operation = Integer.parseInt(reader.readLine());
			
			switch(operation) {
			
			case 1: System.out.println("\n"+"*********************************************************************************************************************************");
					graphVertices=buildGraph();
					System.out.println("\n"+"\t\t\t\tGraph with "+graphVertices.size()+" vertices created successfully!!!!");
				    System.out.println("\n"+"*********************************************************************************************************************************");
				    break;
				    
			case 2: System.out.println("\n"+"*********************************************************************************************************************************");
					System.out.println("\n"+"\t\t\tDisplay Graph");
					System.out.println("\n"+"*********************************************************************************************************************************");
					printGraph(graphVertices);
					break;
					
			case 3: System.out.println("\n"+"*********************************************************************************************************************************");
	   	    		System.out.println("\n"+"\t\t\t\tBreadth First Search\t\t\t\t\t\t\t\t\t\t");
	   	    		System.out.println("\n"+"*********************************************************************************************************************************");
	   	    		BFS(false);
	   	    		break;
		    
			case 4: System.out.println("\n"+"*********************************************************************************************************************************");
			   	    System.out.println("\n"+"\t\t\t\tExperimental Analysis Breadth First Search\t\t\t\t\t\t\t\t\t\t");
			        System.out.println("\n"+"*********************************************************************************************************************************");
			        BFS(true);
			        break;
				   
			case 5: System.out.println("\n"+"*********************************************************************************************************************************");
	    			System.out.println("\n"+"\t\t\t\tDepth First Search\t\t\t\t\t\t\t\t\t\t");
	    			System.out.println("\n"+"*********************************************************************************************************************************");
	    			DFS(false);
	    			break;
	    			
			case 6: System.out.println("\n"+"*********************************************************************************************************************************");
	   	    		System.out.println("\n"+"\t\t\t\tExperimental Analysis Depth First Search\t\t\t\t\t\t\t\t\t\t");
	   	    		System.out.println("\n"+"*********************************************************************************************************************************");
	   	    		DFS(true);
	   	    		break;
			
			case 7: System.out.println("\n"+"*********************************************************************************************************************************");
					System.out.println("\n"+"\t\t\t\tDijkstra's Algorithm\t\t\t\t\t\t\t\t\t\t");
					System.out.println("\n"+"*********************************************************************************************************************************");
					DijkstraAlgorithm(false); // Exit the system
					break;
					
			case 8: System.out.println("\n"+"*********************************************************************************************************************************");
	    			System.out.println("\n"+"\t\t\t\tExperimental Analysis Shortest Path - Dijkstra�s\t\t\t\t\t\t\t\t\t\t");
	    			System.out.println("\n"+"*********************************************************************************************************************************");

	    			DijkstraAlgorithm(true); 
					break;
		
			case 9: System.out.println("Process Completed, System Exiting!");
					System.exit(0); // Exit the system
			 	    break;
			
			default : System.out.println("Invalid operation");
					  break;
			}
			
		}
}
	
	/***
	 * 
	 * @return ArrayList of the raw CSV data containing the trip details of source,destination and fare
	 */
	public static ArrayList<TripDetails> fetchDataFromCsv() {
		FileManager fileManager = new FileManager(); //Initialize the FileManager class
		ArrayList<TripDetails> tripDetails = new ArrayList<>(); // initilize linked list to store the data
		tripDetails = fileManager.getTripData();// Fetch the array list of csv data
		return tripDetails;
	}
	
	/***
	 * This method calls the breadthFirstSearch method in GraphImplementation class
	 * @param isExperimentalAnalysis : Flag is true if its experimental analysis
	 */
	public static void BFS(boolean isExperimentalAnalysis) {
		
		int numberOfIterations = isExperimentalAnalysis? 5:1;//For experimental analysis number of iterations is 5 else 1
		GraphImplementation graphImplementation = new GraphImplementation(); // object of GraphImplementation class
		ArrayList<ArrayList<GraphEdge>> graphVertices = buildGraph(); // build the graph
		long timeForBFS = 0; // time taken to run the BFS algorithm
		int[] verticeSize = new int[] {50,104,158,212,266}; //The number of vertices processed per run
		
		//Print timing only for the experimental analysis
		if(isExperimentalAnalysis)
			System.out.println("\t\t\tNumber of vertices\t\t|\t\tTime Taken");
		//Have 5 runs on different set of data
		for(int i=0;i<numberOfIterations;i++) {
			//For each consecutive run incrementally add new nodes
			ArrayList<ArrayList<GraphEdge>> listForTheRun = isExperimentalAnalysis?experimentalData(graphVertices,i+1):graphVertices;//fetch experimental data in case it is experimental analysis else use the original graph
			timeForBFS = 0;
			//Run the breadthFirstSearch algorithm 5 times over the same data
			for(int j = 0; j<numberOfIterations; j++) {
				long startTimeBFS = System.nanoTime(); // start time for BFS
				//Call the breadthFirstSearch algorithm
				graphImplementation.breadthFirstSearch(listForTheRun, 3,isExperimentalAnalysis);
				long endTimeBFS = System.nanoTime();// end time for BFS
				timeForBFS += endTimeBFS - startTimeBFS; //Summed time for each iteration
			}
			if(isExperimentalAnalysis) {
				//Print the average time for processing the vertices of each run
				System.out.println("\t\t\t\t"+verticeSize[i]+"\t\t\t|\t\t"+(double)(timeForBFS/numberOfIterations));
			}	
		}
	}
	
	/***
	 * 
	 * @param isExperimentalAnalysis : Flag is true if its experimental analysis
	 */
	
	public static void DFS(boolean isExperimentalAnalysis){
		
		int numberOfIterations = isExperimentalAnalysis? 5:1;//For experimental analysis number of iterations is 5 else 1
		long timeForDFS = 0;//time taken to run the DFS algorithm
		GraphImplementation graphImplementation = new GraphImplementation();// object of GraphImplementation class
		ArrayList<ArrayList<GraphEdge>> graphVertices = buildGraph();//build the graph
		
		//Print timing only for the experimental analysis
		if(isExperimentalAnalysis)
			System.out.println("\t\t\tNumber of vertices\t\t|\t\tTime Taken");
		
		int[] verticeSize = new int[] {50,104,158,212,266};//The number of vertices processed per run
		//Have 5 runs on different set of data
		for(int i=0;i<numberOfIterations;i++) {
			timeForDFS = 0;
			//For each consecutive run incrementally add new nodes
			ArrayList<ArrayList<GraphEdge>> listForTheRun = isExperimentalAnalysis?experimentalData(graphVertices,i+1):graphVertices;//fetch experimental data in case it is experimental analysis else use the original graph
			
			//For each consecutive iteration incrementally add new nodes
			for(int j = 0; j<numberOfIterations; j++) {
				long startTimeDFS = System.nanoTime();//start time for DFS
				//Run the depthFirstSearch algorithm 5 times over the same data
				graphImplementation.depthFirstSearch(listForTheRun, 3,isExperimentalAnalysis);
				long endTimeDFS = System.nanoTime(); //end time for DFS
				timeForDFS += endTimeDFS - startTimeDFS;//Summed time for each iteration
			}
			
			if(isExperimentalAnalysis) {
				//Print the average time for processing the vertices of each run
				System.out.println("\t\t\t\t"+verticeSize[i]+"\t\t\t|\t\t"+(double)(timeForDFS/numberOfIterations));
			}
		}

	}

	/***
	 * 
	 * This method builds the graph for  data from the CSV and returns the ArrayList representation of the graph
	 * @return ArrayList containing ArrayList of the GraphEdge
	 */
	public static ArrayList<ArrayList<GraphEdge>> buildGraph(){
		// Creating a graph with 266 vertices
		int numberOfVertices=266;
				
		ArrayList<ArrayList<GraphEdge> > graphVertices
        = new ArrayList<ArrayList<GraphEdge>>(numberOfVertices); // Initialise a array list with defined number of vertices
	
		GraphImplementation graphImplementation = new GraphImplementation();
		ArrayList<TripDetails> tripDetail = fetchDataFromCsv(); // form array list out of the raw CSV data
				
		//initialise arraylist for the set of vertices
		for(int i=0;i<numberOfVertices;i++) {
			graphVertices.add(new ArrayList<GraphEdge>());
		}
		
		//For every row in the arraylist created from csv, add an entry into the graph for each distinct vertex
		tripDetail.forEach(row->{
			GraphEdge g = new GraphEdge(Integer.parseInt(row.getPickUpLocationId()),
					Integer.parseInt(row.getDropOffLocationId()),
					row.getFare());
			graphImplementation.addEdges(graphVertices,g,true);
		});
		
		//return the graph as an array list of array list of GraphEdges
		return graphVertices;
	}
	
	/***
	 * This method prints the graph
	 * @param graphVertices
	 */
	public static void printGraph(ArrayList<ArrayList<GraphEdge>> graphVertices) {
		GraphImplementation graphImplementation = new GraphImplementation();
		graphImplementation.printGraph(graphVertices);		
	}
	
	
	/***
	 * Helper method to call the Dijkstras algorithm for shortest path calculation
	 * @param isExperimentalAnalysis : true if it is experimental analysis false otherwises
	 */
	public static void  DijkstraAlgorithm(boolean isExperimentalAnalysis) {
		int numberOfIterations = isExperimentalAnalysis? 5:1;//For experimental analysis number of iterations is 5 else 1
		long timeForDA = 0;//time taken to run the Dijkstra's algorithm for shortest path
		GraphImplementation graphImplementation = new GraphImplementation();// object of GraphImplementation class
		ArrayList<ArrayList<GraphEdge>> graphVertices = buildGraph();//build the graph
		
		//Print timing only for the experimental analysis
		if(isExperimentalAnalysis)
			System.out.println("\t\t\tNumber of vertices\t\t|\t\tTime Taken");
		
		int[] verticeSize = new int[] {50,104,158,212,266};//The number of vertices processed per run
		
		//Have 5 runs on different set of data
		for(int i=0;i<numberOfIterations;i++) {
			timeForDA = 0;
			//For each consecutive run incrementally add new nodes
			ArrayList<ArrayList<GraphEdge>> listForTheRun = isExperimentalAnalysis?experimentalData(graphVertices,i+1):graphVertices;
			
			//For each consecutive iteration incrementally add new nodes
			for(int j = 0; j<numberOfIterations; j++) {
				long startTimeDA = System.nanoTime();//start time for Dijkstra's algorithm
				DA(listForTheRun,isExperimentalAnalysis,7);// call the Dijkstra's algorithm
				long endTimeDA = System.nanoTime();//start time for Dijkstra's algorithm
				timeForDA += endTimeDA  - startTimeDA;//Summed time for each iteration
			}
			
			if(isExperimentalAnalysis) {
				//Print number of vertices and  average time for the processed vertices
				System.out.println("\t\t\t\t"+verticeSize[i]+"\t\t\t|\t\t"+(double)(timeForDA/numberOfIterations));
			}
		}
		
	}
	

	/**
	 * This method implements the Dijkstra's algorithm for shortest path calculation from source node to every other node
	 * We use the priority queue implementation
	 * @param graphVertices
	 * @param isExperimentalAnalysis
	 */
	public static void DA(ArrayList<ArrayList<GraphEdge>> graphVertices,boolean isExperimentalAnalysis,int source) {
		
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
	
	
	/***
	 * This method forms the data for the different runs for experimental analysis
	 * @param graphVertices
	 * @param runNumber
	 * @return graph as the ArrayList for the data
	 */
	public static ArrayList<ArrayList<GraphEdge>> experimentalData(ArrayList<ArrayList<GraphEdge>> graphVertices,int runNumber){
		// initialize list length to be same as the input arraylist
		int listLength = graphVertices.size();
		
		// initialize the list 
		ArrayList<ArrayList<GraphEdge> > listForTheRun
        = new ArrayList<ArrayList<GraphEdge>>(listLength);
		
		// for initialize items of the list
		for(int i=0;i<listLength;i++) {
			listForTheRun.add(new ArrayList<GraphEdge>());
		}	
		
		//update data as per the run number
		if(runNumber == 1) {
			for(int i=0;i<50;i++) {
				listForTheRun.set(i, graphVertices.get(i));
			}
		}
		//update data as per the run number
		else if(runNumber == 2) {
			for(int i=0;i<104;i++) {
				listForTheRun.set(i, graphVertices.get(i));
			}
		}
		//update data as per the run number
		else if(runNumber == 3){
			for(int i=0;i<158;i++) {
				listForTheRun.set(i, graphVertices.get(i));
			}
		}
		//update data as per the run number
		else if(runNumber == 4){
			for(int i=0;i<212;i++) {
				listForTheRun.set(i, graphVertices.get(i));
			}
		}
		//update data as per the run number
		else if(runNumber == 5){
			for(int i=0;i<266;i++) {
				listForTheRun.set(i, graphVertices.get(i));
			}
		}

		return listForTheRun;
	}
}
