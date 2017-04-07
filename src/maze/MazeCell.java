package maze;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *  The <code>MazeCell</code> class stores information about each individual cell
 *  in the maze.  The boolean values <code>north</code>, <code>east</code>,
 *  <code>west</code>, and <code>south</code> store which walls are up; e.g., if
 *  <code>north</code> is <code>true</code>, then the north wall is up.
 *  
 *  Each cell also has pointer to its four <code>MazeCell</code> neighbors,
 *  <code>neighborN</code>, <code>neighborE</code>, <code>neighborS</code>, and
 *  <code>neighborW</code>.  If any of these values are null, it means this cell
 *  is on the border of the maze.  
 *
 *
 */
public class MazeCell {
    
    private boolean north, east, south, west;
    private boolean visited, examined;
    private MazeCell neighborN, neighborE, neighborS, neighborW;
    private MazeCell predecessor;
    public MazeCell getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(MazeCell predecessor) {
		this.predecessor = predecessor;
	}

	public MazeCell getNeighborN() {
		return neighborN;
	}

	public MazeCell getNeighborE() {
		return neighborE;
	}

	public MazeCell getNeighborS() {
		return neighborS;
	}

	public MazeCell getNeighborW() {
		return neighborW;
	}

	private Random generator;
    private MazeCell parent;
    private int rank;


    public MazeCell getParent() {
		return parent;
	}

	public void setParent(MazeCell p) {
		this.parent = p;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	/** 
     *  Sets all the walls to <code>true</code> and initializes
     *  the random number generator.
     */
    public MazeCell() {
        north = true;
        east  = true;
        south = true;
        west  = true;
        generator = new Random();
        visited = false;
        examined = false;
        parent = null;
        rank = 0;
    }

    /**
     *  Sets the visited flag to <code>true</code>.
     */
    public void visit() {
        visited = true;
    }

    /**
     *  Returns whether or not this cell has been visited.
     *  @return <code>true</code> if the cell has been visited.
     */
    public boolean visited() {
        return visited;
    }

    /**
     *  Sets the examined flag to <code>true</code>.
     */
    public void examine() {
        examined = true;
    }

    /**
     *  Returns whether or not this cell has been examined.
     *  @return <code>true</code> if the cell has been examined.
     */
    public boolean examined() {
        return examined;
    }
    
    /**
     *  Sets the pointers to the neighbors of this cell.  If a pointer 
     *  is null, that means this cell is along the border of the maze.
     *  @param n  The north neighbor of this cell.
     *  @param e  The east neighbor of this cell.
     *  @param s  The south neighbor of this cell.
     *  @param w  The west neighbor of this cell.
     */
    public void setNeighbors(MazeCell n, MazeCell e, MazeCell s, MazeCell w) {
        neighborN = n;
        neighborE = e;
        neighborS = s;
        neighborW = w;
    }

    /**
     *  Sets whether or not there are walls up to the north, east, south, and 
     *  west of this cell.
     *  @param north <code>true</code> if there's a wall on the north side of the cell.
     *  @param east <code>true</code> if there's a wall on the east side of the cell.
     *  @param south <code>true</code> if there's a wall on the south side of the cell.     
     *  @param west <code>true</code> if there's a wall on the west side of the cell.
     */
    public void setWalls(boolean north, boolean east, boolean south, boolean west) {
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }
    
    /**
     *  Returns whether or not this cell has all its walls up.
     *  @return <code>true</code> if all walls are up.
     */
    public boolean hasAllWalls() {
        //TODO - fix this
    	return this.north && this.south && this.east && this.west;
        //return false;
    }

    /**
     *  Returns whether or not this cell has its north wall up.
     *  @return <code>true</code> if the cell's north wall is up.
     */
    public boolean north() {
     //TODO - fix this
    	return this.north;
        //return false;
    }

    /**
     *  Returns whether or not this cell has its south wall up.
     *  @return <code>true</code> if the cell's south wall is up.
     */
    public boolean south() {
        //TODO - fix this.
    	return this.south;
        //return false;
    }

    /**
     *  Returns whether or not this cell has its east wall up.
     *  @return <code>true</code> if the cell's east wall is up.
     */
    public boolean east() {
        //TODO - fix this.
    	return this.east;
       // return false;
    }

    /**
     *  Returns whether or not this cell has its west wall up.
     *  @return <code>true</code> if the cell's west wall is up.
     */
    public boolean west() {
     //TODO - fix this
    	return this.west;
        //return false;
    }

    /**
     *  Gets the accessible neighbors of this cell.
     *  Returns an array of those neighbors.  The length of the array
     *  is the number of neighbors this cell has.
     *  @return  An array with the neighbors contained within it.
     */
    public MazeCell[] getNeighbors() {
        //TODO - fix this.
    	LinkedList<MazeCell> neighborsList = new LinkedList<MazeCell>();
    	if (!this.east() && this.getNeighborE() != null) {
			neighborsList.add(this.getNeighborE());
		}
		if (!this.west() && this.getNeighborW() != null) {
			neighborsList.add(this.getNeighborW());
		}
		if (!this.south() && this.getNeighborS() != null) {
			neighborsList.add(this.getNeighborS());
		}
		if (!this.north() && this.getNeighborN() != null) {
			neighborsList.add(this.getNeighborN());
		}
		
		int accessNeighborsNum = neighborsList.size();
		MazeCell[] neighborsArray = new MazeCell[accessNeighborsNum];
		for (int i = 0; i < accessNeighborsNum; i++) {
			neighborsArray[i] = neighborsList.get(i);
		}
    	
        return neighborsArray;
    	//return null;
    }

    /**
     *  Knocks down the wall between this cell and the neighbor cell.
     *  The neighbor cell must actually be a neighbor of this cell.
     *  This method is used in conjunction with <code>neighborWithWalls</code>.
     *  @param neighbor  The neighboring cell; must be one of the neighbors
     *                   set in <code>setNeighbors</code>.
     */
    public void knockDownWall(MazeCell neighbor) {
        //TODO - fix this. Remember that any wall that is knocked down
    	if (neighbor == this.neighborN) {
    		this.north = false;
    		neighbor.south = false;
    	} else if (neighbor == this.neighborE) {
    		this.east = false;
    		neighbor.west = false;
    	} else if (neighbor == this.neighborS) {
    		this.south = false;
    		neighbor.north = false;
    	} else if (neighbor == this.neighborW) {
    		this.west = false;
    		neighbor.east = false;
    	} else {
    		System.out.println("Not a valid neighbor when knocking down the walls");
    	}
        // will require you to change values for both this and neighbor.
    }
    
    /**
     * Use this function whenever you want to randomly pick one of the neighbours
     * @return - random choice of one of the neighbours.
     */
    public MazeCell getRandomNeighbor() {
        //TODO - fix this
    	int n = this.generator.nextInt(4);
    	switch (n) {
    	case 0:
    		return this.neighborN;
    	case 1:
    		return this.neighborE;
    	case 2:
    		return this.neighborS;
    	case 3:
    		return this.neighborW;
    	}
    	return null;
        //return null;
    }

    /**
     *  Returns a neighbor that has all its walls intact.
     *  @return Neighbor with all its walls intact.
     */
    public MazeCell neighborWithWalls() {
       //TODO - correct this.
    	if (this.north && this.neighborN.north && this.neighborN.east
    			&& this.neighborN.west && this.neighborN.south) {
    		return this.neighborN;
    	} else if (this.east && this.neighborE.north && this.neighborE.east
    			&& this.neighborE.west && this.neighborE.south) {
    		return this.neighborE;
    	} else if (this.south && this.neighborS.north && this.neighborS.east
    			&& this.neighborS.west && this.neighborS.south) {
    		return this.neighborS;
    	} else if (this.west && this.neighborW.north && this.neighborW.east
    			&& this.neighborW.west && this.neighborW.south) {
    		return this.neighborW;
    	}
    		
        return null;
    }

   
}

