abstract class Agenda{
	// Your code goes here
	public abstract void addLocation(MazeGridLocation location);
	public abstract MazeGridLocation removeLocation();
	public abstract boolean isEmpty();
	public abstract void clear();
	public abstract String toString();
}