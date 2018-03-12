package jri.agile.game;

public class BoardPosition {
	private int yPos;
	private int xPos;
		
	public BoardPosition (int yPos, int xPos) {
		this.yPos = yPos;
		this.xPos = xPos;
	}
	
	public int getXPos () {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public void setXPos (int xPos) {
		this.xPos = xPos;
	}
	
	public void setYPos (int yPos) {
		this.yPos = yPos;
	}

	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!BoardPosition.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final BoardPosition otherPosition = (BoardPosition) obj;
	    
	    if (xPos != otherPosition.getXPos() || yPos != otherPosition.getYPos()) {
	    	return false;
	    }
	    
	    return true;
	}

	@Override
	public int hashCode() {
	    int hash = 3;
	    hash = 53 * hash + this.xPos + this.yPos;
	    return hash;
	}
	
	@Override
	public String toString () {
		return xPos + "," + yPos;
	}
}
