//Esta clase gestiona la parte lógica del programa, e ella se almacenan los datos del problema
public class problema {
	private int [][] matriz;
	private int calto;
	private int cancho;
	
	public problema(int alto, int ancho){
		calto=alto;
		cancho=ancho;
		matriz = new int [alto][ancho];
		for (int i = 0; i<cancho;i++){
			for (int j = 0 ; j<calto;j++){
				matriz [i][j]=0;
			}
		}
	}
	public void setmatriz(int x, int y, int estado){
		matriz [x][y]=estado; 
	}
	public int getmatriz(int x, int y){
		return matriz [x][y];
	}

	
	
}
