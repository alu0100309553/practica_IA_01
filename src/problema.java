//Esta clase gestiona la parte lógica del programa, e ella se almacenan los datos del problema
public class problema {
	private int [][] matriz;
	private int calto;
	private int cancho;
	private int totalobstaculos;
	
	
	public problema(int alto, int ancho){
		calto=alto;
		cancho=ancho;
		totalobstaculos=0;
		matriz = new int [alto][ancho];
		for (int i = 0; i<cancho;i++){
			for (int j = 0 ; j<calto;j++){
				matriz [j][i]=0;
			}
		}
	}
	public void setmatriz(int x, int y, int estado){
		if(matriz [x][y]!=1 && estado == 1){
			totalobstaculos++;
		}
		if(matriz [x][y]==1 && estado != 1){
			totalobstaculos--;
		}
		matriz [x][y]=estado; 
	}
	public int getmatriz(int x, int y){
		return matriz [x][y];
	}
	public void totalobstaculosadd(){
		totalobstaculos++;
	}
	public void totalobstaculosminus(){
		totalobstaculos--;
	}
	public int totalobstaculosreturn(){
		return totalobstaculos;
	}
}
