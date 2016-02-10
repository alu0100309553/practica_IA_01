//Esta clase gestiona la parte lógica del programa, e ella se almacenan los datos del problema
public class matriz {
	private int [][] matriz;
	private int iniciox, inicioy;
	private int finalx, finaly;
	private int calto;
	private int cancho;
	public int totalobstaculos;

	public matriz (int alto, int ancho){
		calto=alto;
		cancho=ancho;
		totalobstaculos=0;
		iniciox=0;
		inicioy=0;
		finalx=0;
		finaly=0;
		matriz = new int [alto][ancho];
		for (int i = 0; i<calto;i++){
			for (int j = 0 ; j<cancho;j++){
				matriz [i][j]=0;
			}
		}
	}
	public void setmatriz(int x, int y, int estado){
		if(matriz[x][y]!=1 && estado == 1){
			totalobstaculos++;
		}
		if(matriz[x][y]==1 && estado != 1){
			totalobstaculos--;
		}
		if(estado==2){
			iniciox=x;
			inicioy=y;
		}
		if(estado==3){
			finalx=x;
			finaly=y;
		}
		matriz[x][y] = (estado); 
	}
	public boolean esinicio(int x, int y){
		return matriz[x][y]==matriz [iniciox][inicioy];
	}
	

	public int getmatriz(int x, int y){
		return matriz [x][y];
	}

	public int getiniciox(){
		return iniciox;
	}

	public int getinicioy(){
		return inicioy;
	}

	public int getfinalx(){
		return finalx;
	}

	public int getfinaly(){
		return finaly;
	}


	public int getcalto(){
		return calto;
	}

	public int getcancho(){
		return cancho;
	}

}
