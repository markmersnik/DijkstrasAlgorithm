package HW6;

public class Dijkstra {
	
}

class Vertex {
	public char label;
	public boolean isInTree;
	
	public Vertex(char lab) {
		label = lab;
		isInTree = false;
	}
	
}

class Graph {
	
	private final int MAX_VERTS = 20;
	private final int INFINITY = 1000000;
	private Vertex vertexList[];
	private int adjMat[][]; //adjacency matrix
	private int nVerts; //number of vertices
	private int nTree; //number of vertices in tree
	private int sPath[]; //array for shortest path

	
	
	public Graph() {
		
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		sPath = new int[MAX_VERTS];
		vertexList = new Vertex[MAX_VERTS];
		nVerts = 0;
		
		for(int i = 0; i < MAX_VERTS; i++) {
			sPath[i] = INFINITY;
			for(int j = 0; j <MAX_VERTS; j++) {
				adjMat[i][j] = INFINITY;
			}
		}
		
		
	}
	
	public void addVertex(char label) {
		vertexList[nVerts++] = new Vertex(label);
	}
	
	public void addEdge(int start, int end, int distance) {
		adjMat[start][end] = distance;
	}
	
	private int getMin() {
		int min = INFINITY;
		int loc = 0;
		for(int i = 0; i < nVerts; i++) {
			if(!vertexList[i].isInTree && sPath[i] < min) {
				min = sPath[i];
				loc = i;
			}
		}
		return loc;
	}
	
	public char getVertexLabel(int x) {
		return vertexList[x].label;
	}
	
	public void reset() {
		for(int i = 0; i < nVerts; i++) {
			sPath[i] = INFINITY;
			vertexList[i].isInTree = false;
		}
	}

	
	public void path() {
		int startTree = 0;
		
		while(startTree < nVerts) {
			sPath[startTree] = 0;
			vertexList[startTree].isInTree = true;
			nTree = 1;

			System.out.println("Shortest paths for source " + vertexList[startTree].label + ":");
			int forDist = 0;
			int backDist = 0;

			for(int i = 0; i < 5; i++) {
				if(startTree != i) {
					sPath[i] = adjMat[startTree][i];				
				}
			}

			while(nTree < nVerts) {
				int min = this.getMin();

				int tempMin = sPath[min];
				for(int i = 0; i < nVerts; i++) {
					forDist = adjMat[nTree][i] + sPath[nTree];
					backDist = adjMat[i][nTree] + sPath[i];
					if(!vertexList[i].isInTree && forDist < sPath[i]) {
						sPath[i] = forDist;
					}	
					if(!vertexList[nTree].isInTree && backDist < sPath[nTree]) {
						sPath[nTree] = backDist;
					}	
				}
				if(tempMin == sPath[min]) {
					vertexList[min].isInTree = true;
					nTree++;
				}	
			}


			for(int i = 0; i < nVerts; i++) {
				System.out.println("From " + vertexList[startTree].label + " to " + this.getVertexLabel(i) + " is " + sPath[i]);
			}
			System.out.println();
			this.reset();
			startTree++;
		}
	}
	
}

















