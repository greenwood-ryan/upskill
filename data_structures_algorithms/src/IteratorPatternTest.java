/*
In object-oriented programming, the iterator pattern is a design pattern in which an iterator is used to traverse a container and access the container's elements. The iterator pattern decouples algorithms from containers; in some cases, algorithms are necessarily container-specific and thus cannot be decoupled.
What problems can the Iterator design pattern solve? [2]

	The elements of an aggregate object should be accessed and traversed without exposing its representation (data structures).
	New traversal operations should be defined for an aggregate object without changing its interface.
	Defining access and traversal operations in the aggregate interface is inflexible because it commits the aggregate to particular access and traversal operations and makes it impossible to add new operations later without having to change the aggregate interface.

What solution does the Iterator design pattern describe?

	Define a separate (iterator) object that encapsulates accessing and traversing an aggregate object.
	Clients use an iterator to access and traverse an aggregate without knowing its representation (data structures).
*/
import java.util.*;

public class IteratorPatternTest{
	public static void main(String[] args){
		DiscJockey dj = new DiscJockey(new Seventies(), new Eighties(), new Nineties());
		dj.showSongs();
	}
}

class DiscJockey{
	
	Nineties nineties;
	Eighties eighties;
	Seventies seventies;
	

	public DiscJockey(Seventies svtys, Eighties etys, Nineties ntys){
		seventies = svtys;
		eighties = etys;
		nineties = ntys;
	}
	
	public void showSongs(){
		Iterator it = seventies.iterator();
		System.out.println();
		showDecade(it);
		it = eighties.iterator();
		System.out.println();
		showDecade(it);
		it = nineties.iterator();
		System.out.println();
		showDecade(it);
	}
	
	private void showDecade(Iterator it){
		while(it.hasNext()){
			Song song = (Song) it.next();
			System.out.println(song.getName()+" "+song.getArtist()+" "+song.getYear());
		}
	}
}

class Nineties implements SongIterator{
	Hashtable<Integer, Song> songs;
	int key = 0;
	
	public Nineties(){
		songs = new Hashtable<Integer, Song>();
		addSong("Creep", "Radio Head", 1993);
		addSong("Smells like teen spirit", "Nirvana", 1996);
		addSong("Walk on the Ocean", "Toad the Wet Sprocket", 1994);
	}
	
	public void addSong(String name, String artist, int year){
		songs.put(key, new Song(name, artist, year));
		key++;
	}
	
	public Iterator iterator(){
		return songs.values().iterator();
	}
}

class Eighties implements SongIterator{
	Song[] songs;
	int index = 0;
	
	public Eighties(){
		songs = new Song[3];
		addSong("Cruel Summer", "Bananarama", 1983);
		addSong("Cry Wolf", "Aha", 1986);
		addSong("Our House", "Madness", 1983);
	}
	
	public void addSong(String name, String artist, int year){
		songs[index] = new Song(name, artist, year);
		index++;
	}
	
	public Iterator iterator(){
		return Arrays.asList(songs).iterator();
	}
}

class Seventies implements SongIterator{
	List<Song> songs;
	
	public Seventies(){
		songs = new ArrayList();
		addSong("Imagine", "John Lennon", 1971);
		addSong("American Pie", "Don Maclean", 1971);
		addSong("I will survive", "Gloria Gaynor", 1973);
	}
	
	public void addSong(String name, String artist, int year){
		songs.add(new Song(name, artist, year));
	}
	
	public Iterator iterator(){
		return songs.iterator();
	}
}

interface SongIterator{
	public Iterator iterator();
}

class Song{
	private String name, artist;
	private int year;
	
	public Song(String n, String a, int y){
		name = n;
		artist = a;
		year = y;
	}
	
	public String getName(){
		return name;
	}
	public String getArtist(){
		return artist;
	}
	public int getYear(){
		return year;
	}
}
