/*Question:  Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK

Solution thinking:
First keep going forward until you get stuck. That's a good main path already. Remaining tickets form cycles which are found on the way back and get merged into that main path. By writing down the path backwards when retreating from recursion, merging the cycles into the main path is easy - the end part of the path has already been written, the start part of the path hasn't been written yet, so just write down the cycle now and then keep backwards-writing the path.

Example:

From JFK we first visit JFK -> A -> C -> D -> A. There we're stuck, so we write down A as the end of the route and retreat back to D. There we see the unused ticket to B and follow it: D -> B -> C -> JFK -> D. Then we're stuck again, retreat and write down the airports while doing so: Write down D before the already written A, then JFK before the D, etc. When we're back from our cycle at D, the written route is D -> B -> C -> JFK -> D -> A. Then we retreat further along the original path, prepending C, A and finally JFK to the route, ending up with the route JFK -> A -> C -> D -> B -> C -> JFK -> D -> A.

*/
import java.util.*;

public class Itinerary{

	Map<String, PriorityQueue<String>> targets = new HashMap<>();
	List<String> route = new LinkedList();
	
	public Itinerary(){
		String[][] tickets = {{"JFK","A"},{"A","C"},{"C","D"},{"D","B"},{"B","C"},{"C","JFK"},{"D","A"},{"JFK","D"}};
		List<String> it = findItinerary(tickets);
		Iterator i = it.iterator();
		while(i.hasNext()){
			System.out.print(i.next()+", ");
		}
	}
	
	public static void main(String[] args){
		new Itinerary();
	}

	public List<String> findItinerary(String[][] tickets) {
		for (String[] ticket : tickets)
			targets.computeIfAbsent(ticket[0], k -> new PriorityQueue()).add(ticket[1]);
		visit("JFK");
		return route;
	}


	void visit(String airport) {
		while(targets.containsKey(airport) && !targets.get(airport).isEmpty())
			visit(targets.get(airport).poll());
		route.add(0, airport);
	}
}