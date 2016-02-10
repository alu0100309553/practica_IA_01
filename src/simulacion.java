import java.util.ArrayList;

public class simulacion {

	private mapa map;
	private AlgoritmoA robot;
	private matriz M;


    simulacion (matriz matrix, int heuristica){
		this.map= new mapa (matrix.getcancho(), matrix.getcalto(), matrix);
		this.robot = new AlgoritmoA (map, heuristica);
		this.M= matrix;
	}

	public ArrayList<nodo> encontrarcamino(){
		robot.calcaminominimo(M.getiniciox(), M.getinicioy(), M.getfinalx(), M.getfinaly());
		return robot.mostrarcamino();
	
	}
	public int contador(){
		return robot.contador;
	}

	public ArrayList<nodo> visitados()
	{
		return robot.mostrarvisitados();
	
	}

}
