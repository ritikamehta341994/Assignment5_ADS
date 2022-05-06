import java.util.Comparator;

/***
 * This class is used to store the details of the edges in the graph
 * ie. source, destination and weight between the edges
 * @author ritzm
 *
 */
class GraphEdge implements Comparator<GraphEdge>{
	//source of the edge ie. start point
	private int source;
	//destination of the edge ie. end point
	private int destination;
	//Weight associated with the edge
	private int weight;
			
	//default constructor
	public GraphEdge() {
		
	}
	
	//constructor for the class
	public GraphEdge(int source,int destination,int weight){
		this.source = source;
		this.destination = destination;
		this.weight = weight;
		
	}
	
	//get source vertex
	public int getSource() {
		return source;
	}

	//set source vertex
	public void setSource(int source) {
		this.source = source;
	}

	//get destination vertex
	public int getDestination() {
		return destination;
	}

	//set destination vertex
	public void setDestination(int destination) {
		this.destination = destination;
	}

	//get weight of the edge
	public int getWeight() {
		return weight;
	}

	//set weight of the edge
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	@Override 
	public int compare(GraphEdge vertex1, GraphEdge vertex2)
    {
		//if vertex 1 weight is less than vertex 2 weight return -1
        if (vertex1.weight < vertex2.weight)
            return -1;
        //if vertex 1 weight is greater than vertex 2 weight return 1
        if (vertex1.weight > vertex2.weight)
            return 1;
 
        return 0;
    }
	
}
