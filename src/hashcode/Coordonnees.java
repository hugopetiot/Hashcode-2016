package hashcode;

public class Coordonnees {
	int x,y;

	public Coordonnees(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public int distance(Coordonnees c){
		double distance = Math.sqrt((x-c.getX())*(x-c.getX())+(y-getY())*(y-getY()));
		return (int) Math.ceil(distance);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
