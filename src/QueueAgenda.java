import java.util.LinkedList;

public class QueueAgenda extends Agenda{
	// Your code goes here
	private LinkedList<MazeGridLocation> queue;

    public QueueAgenda() {
        this.queue = new LinkedList<>();
    }

    @Override
    public void addLocation(MazeGridLocation loc) {
        queue.offer(loc);
    }

    @Override
    public MazeGridLocation removeLocation() {
        return queue.poll();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}