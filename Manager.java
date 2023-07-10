

public class Manager {

	 private int[][] mat, secMat, result;
	 
	 private int current = 0, secCurrent = 0;		// current = row, SecCurent = column
     private int nextTurn = 1;
	 
	 public Manager(int[][] mat, int[][] secMat) {
		 this.mat = mat;
		 this.secMat = secMat;
		 this.result = new int[mat.length][secMat[0].length];
	 }
	 
    
     public synchronized int[][] allocateRowCol(int r, int c){
    	 int[][] a = new int[2][mat[0].length];
    	 if (this.current == this.mat.length) {
    		 return null;
    	 }
    	/* if ( secCurrent == secMat[0].length) {
    		 current++;
    		 secCurrent = 0;
    	 }
    	 a[0] = mat[current];*/
    	 for (int i = 0; i<mat[0].length; i++) {
    		 a[0][i] =  mat[r][i];			// row
	    	 a[1][i] = secMat[i][c];		// column
	   	 }
    	 
    	 return a;
     }
     
	public synchronized void insertCell(int cell) {
		if(this.current == 0) {
			if(this.secCurrent == 0) {
				this.result[this.current][this.secCurrent] = cell;
				return;
			}
			this.result[this.current][this.secCurrent-1] = cell;
			return;
		}
		if(this.secCurrent == 0) {
			this.result[this.current-1][this.secCurrent] = cell;
			return;
		}
		this.result[this.current-1][this.secCurrent-1] = cell;
		return;
	}     
	

 	public synchronized void waitForMyTurn(int threadNumber) {
 		while (threadNumber >= nextTurn) {
 			try {
 				wait();                                   // the thread that called the method waits for his turn
 			} catch (InterruptedException e) {
 			}
 		}		// end of while
 		//System.out.println("threadNumber is: " + threadNumber);
 	}

	public synchronized void imDone() 	{
 		nextTurn++;
 		notifyAll();   // wake up all the waiting threads
 	}

	public synchronized int[] getCurrentRowCol() {
		
		int[] temp = new int[2];
		if ( this.secCurrent == this.secMat[0].length) {
   		 this.current++;
   		 this.secCurrent = 1;
   		 temp[0] = this.current;
   		 temp[1] = 0;
   		//System.out.println("00000Column is: " + secCurrent);
		//System.out.println("row is: " + current);
   		 return temp;
   	 }
		temp[0] = this.current;
  		temp[1] = this.secCurrent;
  		//System.out.println("Column is: " + secCurrent);
		//System.out.println("row is: " + current);
		secCurrent++;
		return temp;
	}

    public int[][] getMat() {
		return mat;
	}

	public int[][] getSecMat() {
		return secMat;
	}

	public int[][] getResult() {
		return result;
	}

	/*
	 * 
     public synchronized int[] allocateCol() {
    	 int temp[] = new int[mat.length];
    	 if (secCurrent == mat.length) {
    		 secCurrent = 0;
    	 }
    	 for (int i = 0; i<mat.length; i++) {
	    	 temp[i] = secMat[secCurrent][i];
	   	 }
    	 secCurrent++;
		 System.out.println(secCurrent + " secCurrent");
	   	 return temp;
	     
     }
      public synchronized int[] allocateRow() {
    	 if (current == mat.length) {
    		 return null;
    	 }
    	 if ( secCurrent == mat.length) {
    		 current++;
    	 }
    	 System.out.println("Current: " + current);
	    	 return mat[current];
     }
     
     */
	 
	
}
