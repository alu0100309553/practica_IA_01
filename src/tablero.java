import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

public class tablero extends JPanel {
	
	private int celdasalto;
	private int celdasancho;
	private int lado;
	private int [][] matriz;
	private boolean iniciado = false;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (iniciado==false){
			matriz = new int [celdasancho][celdasalto];
			for (int i = 0; i<celdasancho;i++){
				for (int j = 0 ; j<celdasalto;j++){
					matriz [i][j]=0;
				}
			}
		}
		cuadricula(g);
		iniciado=true;
		for (int i = 0 ; i < celdasancho ; i++){
			for (int j = 0 ; j < celdasalto ; j++){
				if (matriz [i][j]==1){
					creaobstaculo(i, j, g);
				}
			}
		}
		
	}
	
	public void setmatriz(int x, int y, int estado){
		matriz [x][y]=estado; 
	}
	public int getmatriz(int x, int y){
		return matriz [x][y];
	}
	
	public void inicilizar(int alto, int ancho, int ladocelda){
		celdasalto = alto;
		celdasancho = ancho;
		lado = ladocelda;
		iniciado = false;
		
	}
	public void cuadricula(Graphics dibujar){
		if ((1400/celdasancho)<=(900/celdasalto)){//Comparación para ver que lado me limita el tamaño de celda, se coje el más pequeño para quepa en el lienzo.
			lado = (1400/celdasancho);
		}
		else {
			lado = (900/celdasalto);
		}
		dibujar.setColor(Color.BLACK);
		for (int i = 1; i<celdasalto; i++ ){  //Bucle que dibuja las líneas del tablero horizontales
			int posy = i*lado;
			int posx = celdasancho*lado;
			dibujar.drawLine(0,posy,posx,posy);	
		}
		for (int i = 1; i<celdasancho; i++ ){  //Bucle que dibuja las líneas del tablero verticales
			int posx = i*lado;
			int posy = celdasalto*lado;
			dibujar.drawLine(posx,0,posx,posy);	
		}
		
	}
	public void creaobstaculo(int x, int y,Graphics dibujar){
		//super.paintComponent(dibujar);
		dibujar.setColor(Color.BLACK);
		dibujar.fillRect(x*lado,y*lado,lado,lado);
		//repaint();
	}

}
