
public class MultiplyThread extends Thread{

    private Manager m;								// manager for all the threads
    private int threadNumber;						// specific cell index
    private int[] index = new int[2];				// number of row and column for the multiplication
    
    public MultiplyThread(Manager m) {
	          this.m = m;
    }
    
    public void run() {
    	super.run();
    	this.index = m.getCurrentRowCol();
    	
    	threadNumber(this.index[0], this.index[1]);
    	//System.out.println("length[0]-col is: " + m.getMat()[0].length);
    	int[][] a = new int[2][m.getMat()[0].length];			/*	NEED TO LOCK THE ALLOCATION OF ROWS AND COLUMNS	*/
    	a = m.allocateRowCol(this.index[0], this.index[1]);
    	//printRow(a);
       	int sum = 0;
    	for (int i = 0; i < a[0].length; i++) {
    		sum = sum + (a[0][i] * a[1][i]);
    		}
    	m.waitForMyTurn(threadNumber);
    	m.insertCell(sum);
    	if(index[1] == m.getSecMat()[0].length-1) {
    		System.out.println("cell " + this.threadNumber +" is: " + "\t" + sum);
    	}
    	else {
    		System.out.print("cell " + this.threadNumber +" is: " + "\t" + sum + "\t");
    	}
    	//System.out.println("cell " + this.threadNumber +" is: " + sum);
    	m.imDone();
    }
    
    private void threadNumber(int r, int c) {
    	if( r == 0 ) {
    		this.threadNumber = c;
    		//System.out.println("c is: " + this.threadNumber);
    		return;
    	}
    	
    	if( c == 0 ) {
    		this.threadNumber = r*this.m.getSecMat()[0].length;
    		//System.out.println("threadNumber is: " + this.threadNumber);
    		return;
    	}
    	
    	this.threadNumber = r * this.m.getSecMat()[0].length + c;
    	return;
    }
    
	
}
