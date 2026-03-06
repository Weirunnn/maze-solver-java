import java.util.LinkedList;

public class StackAgenda extends Agenda{
	// Your code goes here
	private LinkedList<MazeGridLocation> stack;

    public StackAgenda() {
        stack = new LinkedList<>();
    }

    @Override
    public void addLocation(MazeGridLocation loc) {
        stack.push(loc);
    }

    @Override
    public MazeGridLocation removeLocation() {
        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = stack.size() - 1; i >= 0; i--) {
            sb.append(stack.get(i));
            if (i > 0) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

