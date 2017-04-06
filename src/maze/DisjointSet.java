package maze;

public class DisjointSet {

    //TODO - write all the methods of this class
    
    /**
     * make a set out of the cells in the maze
     * @param maze
     */
	public DisjointSet(MazeCell[][] maze) {
		makeSet(maze);
	}
    public void makeSet(MazeCell[][] maze) {
        for (int i = 0; i < maze.length; i++){
        	for (int j = 0; j < maze[0].length; j++) {
        		MazeCell x = maze[i][j];
        		x.setParent(x);
        		x.setRank(0);
        	}
        }
    }

    /**
     * Create the union of the sets that contain cell1 and cell2.
     * If the two cells are in the same set, nothing changes,
     * else create the new set as a union. 
     * Please see the union-find algorithm.
     * @param cell1
     * @param cell2
     */
    public void union(MazeCell cell1, MazeCell cell2){
    	link(find(cell1), find(cell2));
    }
    
    public void link(MazeCell cell1, MazeCell cell2) {
    	if (cell1.getRank() > cell2.getRank()) {
        	cell2.setParent(cell1);
        } else {
        	cell1.setParent(cell2);
        	if (cell1.getRank() == cell2.getRank()) {
        		cell2.setRank(cell2.getRank() + 1);
        	}
        }
    }

    /**
     * Find the set that the cell is a part of.
     * While finding the set, do the path compression as well.
     * @param cell
     * @return
     */
    public MazeCell find(MazeCell cell){
    	if (cell != cell.getParent()) {
    		cell.setParent(find(cell.getParent()));
    	}
        return cell.getParent();
    }

}

