import java.util.ArrayList;
import java.util.Stack;

public class MazeSolver{
	private Agenda agenda;  
	private MazeGridLocation[][] predecessors;
    private Stack<MazeGridLocation> history = new Stack<>();

	public MazeSolver(Agenda agenda) {
        this.agenda = agenda;
    }

    public MazeGridLocation getPreviousLocation() {
        if (history.isEmpty()) {
            return null; 
        }
        return history.peek(); 
    }
    
    public void addToHistory(MazeGridLocation newLocation) {
        if (newLocation != null) {
            history.push(newLocation);
        }
    }

	private ArrayList<MazeGridLocation> setValidNeighbors(MazeGridLocation loc, Maze m) {
		ArrayList<MazeGridLocation> neighbors = new ArrayList<>();
		int x = loc.getRow();
		int y = loc.getColumn();

		if (x > 0 && !m.getLocation(x - 1, y).isWall() && !m.isVisited(m.getLocation(x - 1, y))) {  
            neighbors.add(m.getLocation(x - 1, y));
        }
        if (x < m.getNumRows() - 1 && !m.getLocation(x + 1, y).isWall() && !m.isVisited(m.getLocation(x + 1, y))) {  
            neighbors.add(m.getLocation(x + 1, y));
        }
        if (y < m.getNumColumns() - 1 && !m.getLocation(x, y + 1).isWall() && !m.isVisited(m.getLocation(x, y + 1))) {  
            neighbors.add(m.getLocation(x, y + 1));
        }
        if (y > 0 && !m.getLocation(x, y - 1).isWall() && !m.isVisited(m.getLocation(x, y - 1))) {
            neighbors.add(m.getLocation(x, y - 1));
        }

        return neighbors;
    }

	
	
	private ArrayList<MazeGridLocation> buildPath(MazeGridLocation goal) {
		ArrayList<MazeGridLocation> path = new ArrayList<>();
		MazeGridLocation current = goal;
	
		while (current != null) {
            path.add(0, current);
            current = predecessors[current.getRow()][current.getColumn()];
        }
		return path;
	}
	
		
	public ArrayList<MazeGridLocation> solveMaze(Maze m, MazeGUI mg) {
        agenda.clear();
		predecessors = new MazeGridLocation[m.getNumRows()][m.getNumColumns()];
        MazeGridLocation startLocation = m.getStartLocation();
        agenda.addLocation(startLocation);
		predecessors[startLocation.getRow()][startLocation.getColumn()] = null; 
        mg.visitLoc(startLocation);

        while (!agenda.isEmpty()) {
            MazeGridLocation currentLocation = agenda.removeLocation();
            m.markVisited(currentLocation);
			mg.visitLoc(currentLocation);
    
            if (m.getGoalLocation().equals(currentLocation)) {
                return buildPath(currentLocation);
            }
			
            for (MazeGridLocation neighbor : setValidNeighbors(currentLocation, m)) {
                if (!m.isVisited(neighbor) && !neighbor.isWall()) {
                    agenda.addLocation(neighbor);
					predecessors[neighbor.getRow()][neighbor.getColumn()] = currentLocation;
                    mg.addLocToAgenda(neighbor);
                }
            }
        }
        return new ArrayList<>(); 
    }
}
