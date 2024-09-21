package game.pojo;
public class Position implements Comparable<Position> {
    private int x;
    private int y;

    private int priority ;
    
    
    public Position(int position) {
    	priority = 0 ; // default priority = 0 
        switch (position) {
            case 1:
                x = 0;
                y = 0;
                break;

            case 2:
                x = 0;
                y = 1;
                break;

            case 3:
                x = 0;
                y = 2;
                break;

            case 4:
                x = 1;
                y = 0;
                break;

            case 5:
                x = 1;
                y = 1;
                break;

            case 6:
                x = 1;
                y = 2;
                break;

            case 7:
                x = 2;
                y = 0;
                break;

            case 8:
                x = 2;
                y = 1;
                break;

            case 9:
                x = 2;
                y = 2;
                break;

            default:
                System.out.println("invalid position:"+position);
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPriority() {
    	return priority ;
    }
    
    
    public void setPriority(int priority) {
    	this.priority = priority ;
    }
    
    
	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int compareTo(Position other) {
		return Integer.compare(this.priority, other.priority);
	}

    
}
