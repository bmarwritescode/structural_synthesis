public class TwoDArray {

    Piece[] arr;
    int N, M;
    int length;
    
    public TwoDArray(int n, int m) {
	N = n;
	M = m;
	arr = new Piece[n*m];
	length = n;
    }

    public void set(int i, int j, Piece val) {
    	arr[(i*M)+j] = val;
    }

    public Piece get(int i, int j) {
    	return arr[(i*M)+j];
    }

    public void setRow(int i, Piece[] row) {
	int count = 0;
	for (int j=i*M; j<(i*M)+M; j++) {
	    arr[j] = row[count];
	    count ++;
	}
    }

    public Piece[] getRow(int i) {
	int count = 0;
	Piece[] row = new Piece[M];
	for (int j=i*M; j<(i*M)+M; j++) {
	    row[count] = arr[j];
	    count ++;
	}
	return row;
    }
}
