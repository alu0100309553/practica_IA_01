//En esta clase se definen todos los métodos para el manejo de la parte gráfica del matriz

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;

import javax.swing.JPanel;
import java.awt.Color;

public class tablero extends JPanel {

	private int celdasalto;
	private int celdasancho;
	private int lado;
	private matriz prob;
	private boolean iniciado = false;
	private Image img1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("wall.png"));
	private Image img2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("robot.png"));
	private Image img3 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("fin.png"));

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		if (iniciado==false){
			prob = new matriz(celdasancho, celdasalto);
		}
		cuadricula(g);
		iniciado=true;
		for (int i = 0 ; i < celdasancho ; i++){
			for (int j = 0 ; j < celdasalto ; j++){
				if (prob.getmatriz(i, j)==1){
					creaobstaculo(i, j, g);
				}
				if (prob.getmatriz(i, j)==2){
					creainicio(i, j, g);
				}
				if (prob.getmatriz(i, j)==3){
					creafin(i, j, g);
				}
				if (prob.getmatriz(i, j)==4){
					creacamino(i, j, g);
				}
				if (prob.getmatriz(i, j)==5){
					visitado(i, j, g);
				}
			}
		}
	}

	public void setmatriz(int x, int y, int estado){
		prob.setmatriz(x, y, estado);
	}
	public int getmatriz(int x, int y){
		return prob.getmatriz(x,y);
	}

	public matriz getmatriz(){
		return prob;
	}

	public void inicilizar(int alto, int ancho, int ladocelda){
		celdasalto = alto;
		celdasancho = ancho;
		lado = ladocelda;
		iniciado = false;
	}

	public void cuadricula(Graphics dibujar){
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
		dibujar.drawImage(img1, x*lado, y*lado,lado, lado,this);
	}
	public void creainicio(int x, int y,Graphics dibujar){
		dibujar.drawImage(img2, x*lado, y*lado,lado, lado,this);
	}
	public void creafin(int x, int y,Graphics dibujar){
		dibujar.drawImage(img3, x*lado, y*lado,lado, lado,this);
	}

	public void creacamino (int x, int y, Graphics dibujar)
	{
		dibujar.setColor(Color.BLUE);
		dibujar.fillRect((x*lado)+1,(y*lado)+1,lado-1,lado-1);
	}
	public void visitado (int x, int y, Graphics dibujar)
	{
		dibujar.setColor(Color.RED);
		dibujar.fillRect((x*lado)+1,(y*lado)+1,lado-1,lado-1);
	}


	public void controlobstaculosadd(){
		prob.totalobstaculos++;
	}
	public void controlobstaculosminus(){
		prob.totalobstaculos--;
	}
	public int controlobstaculoreturn(){
		return prob.totalobstaculos;
	}
	public void limpiarcamino(){
		for(int i=0; i<celdasancho; i++){
			for (int j=0 ; j<celdasalto; j++){
				if (prob.getmatriz(i, j)>=4){
					prob.setmatriz(i, j, 0);
				}

			}

		}
	}
}
