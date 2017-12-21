// Zakee Jabbar (zjabba2)
// Abdul Rehman (arehma7)
// CS 342
// Project 4



public class Ship {
	
	private char type;		//ship type
	private String orient;	//vertical or horizontal
	private char vessal[];	//holds the status of the ship segment (hit(X)/normal(M))
	private int hitCount;
	private int row;		//row of beginning segment
	private int col;		//col of beginning segment
	private int size;
	/**
	 * creates the ships and holds their statuses.
	 * @param o holds the orientation of the ship.
	 * @param t holds the type of the ship (ex: patrol, sub, etc).
	 * @param size holds the size of the ship.
	 */
	public Ship( String o, char t, int size) {
		this.orient = o;
		this.type = t;
		this.size = size;
		this.hitCount = 0;
		this.vessal = new char[size];
		for (int i = 0; i < size; ++i){
			this.vessal[i] = 'M';
		}
		
	}
	
	/**
	 * checks if the ship is destroyed
	 */
	public boolean isSunk(){
		if (this.hitCount == this.size){
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * gets the type of the ship.
	 * @return type of the ship
	 */
	public char getType(){
		return this.type;
	}
	
	/**
	 * gets orientation of the ship
	 * @return 'V' or 'H'
	 */
	public String getOrient(){
		return this.orient;
	}
	
	public void setOrient(String o){
		this.orient = o;
	}
	
	/**
	 * gets the array of statuses of the ship
	 * @return array of the ship
	 */
	public char[] getVessal(){
		return this.vessal;
	}
	
	/**
	 * sets the beginning of the ship
	 * @param r row on where the ship starts
	 */
	public void setRow(int r){
		this.row = r;
	}
	
	/**
	 * sets the beginning of the ship
	 * @param c col on where the ship starts
	 */
	public void setCol(int c){
		this.col = c;
	}
	
	/**
	 * gets the beginning of the ship
	 * @return row of where it begins
	 */
	public int getRow(){
		return this.row;
	}
	
	/**
	 * gets the beginning of the ship
	 * @return row of where it begins
	 */
	public int getCol(){
		return this.col;
	}
	
	public int getSize(){
		return this.size;
	}

    public int getHitCount() {
        return hitCount;
    }

    public void incrementCount()
    {
        hitCount++;
    }
}
