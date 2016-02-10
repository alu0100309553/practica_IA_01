// En esta clase se definen todos los métodos para la gestión de la ventana del programa
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseMotionAdapter;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ventana {

    public JFrame frame;
    int frameancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    int framealto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    int contenedoralto;
    int contenedorancho;
    private JTextField alto;
    private JTextField ancho;
    public int ladocelda;
    public int altovalue;
    public int anchovalue;
    public int heuristica;
    tablero panel = new tablero();
    JRadioButton creaobst = new JRadioButton("Obstaculos");
    JRadioButton borrarobst = new JRadioButton("Borrar");
    JRadioButton iniciorobot = new JRadioButton("Inicio");
    JRadioButton finalrobot = new JRadioButton("Destino");
    JRadioButton rdbtnEuclidea = new JRadioButton("Euclidea");
    JRadioButton rdbtnManhattan = new JRadioButton("Manhattan");
    private JTextField frecuencia;
    public final ButtonGroup mouse = new ButtonGroup();
    public final ButtonGroup heuristicas = new ButtonGroup();
    boolean inicioset = false;
    boolean finalset = false;
    private JTextField pasos;
    private boolean simulprinted=false;
    
    public ventana() {
        initialize();
    }

 
    private void initialize() {
    	heuristica=0;
        frame = new JFrame();
        frame.setTitle("Biohazard Agent");
        frame.setBounds(0, 0, frameancho, framealto);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
       
        //Acción del botón Cuadrícula
        JButton btnPulsar = new JButton("Cuadricula"); //Al Pulsar el botón se genera la cuadrícula
	        btnPulsar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {
	            	contenedoralto=frame.getHeight(); //Se obtiene el alto de la ventana
	                contenedorancho=frame.getWidth()-180; //Se obtiene el ancho de la ventana
	                altovalue = Integer.parseInt( alto.getText()); //Se obtiene el valor introducido de casillas alto y casillas ancho
	                anchovalue = Integer.parseInt( ancho.getText());
	                ladocelda = 0;
	                inicioset = false;
	                finalset = false;
	                if (((contenedorancho)/anchovalue)<=((contenedoralto)/altovalue)){//Comparación para ver que lado limita el tamaño de celda, se coje el más pequeño para quepa en el lienzo.
	                    ladocelda = ((contenedorancho)/anchovalue);
	                }
	                else {
	                    ladocelda = ((contenedoralto)/altovalue);
	                }
	
	                panel.inicilizar(altovalue, anchovalue,ladocelda); //se inicializa un tablero con los valores calculados
	                panel.setBorder(new LineBorder(new Color(0, 0, 0)));
	                panel.setBackground(Color.WHITE);
	                int pospanelx = (((contenedorancho)/2)-(anchovalue*ladocelda/2))+180;//Se calcula la posición del tablero en la ventana
	                int pospanely = (((contenedoralto)/2)-(altovalue*ladocelda/2));
	                panel.setBounds(pospanelx, pospanely, ladocelda*anchovalue, ladocelda*altovalue);
	                frame.getContentPane().add(panel);
	                frame.repaint();
	            
	            }
	        }
        );

        //Acción del botón obstáculos aleatorios
        JButton btnObstculos = new JButton("Obstaculos");
        btnObstculos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                obstaculosaleatorios();
            }
        }
        );
        //Acción del botón simular
        JButton btnSimular = new JButton("SIMULAR");
        btnSimular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (simulprinted){  //Se controla si hay un camino impreso en pantalla para borrarlo
            		panel.limpiarcamino();
            		panel.repaint();
            		simulprinted=false;
            	}
            	simulprinted=true;  //Se controla la eurística elegida
            	if (rdbtnManhattan.isSelected()){
            		heuristica=1;
            	}
            	else{
            		heuristica=0;
            	}
                simulacion simul1 = new simulacion(panel.getmatriz(), heuristica); //Se crea un objeto simulación del cual se obtienen los nodos visitados, el camino mínimo y los pasos
                ArrayList<nodo> caminominimo;
                ArrayList<nodo> visitados;
                caminominimo=null;
                visitados=null;
                
                try{
                    caminominimo = simul1.encontrarcamino();
                    visitados=simul1.visitados();
                }
                catch (NullPointerException e1)
                {
                    JOptionPane.showMessageDialog(frame, "No se pudo encontrar ningún camino al contenedor"); //Si no se encuentra camino se imprime un mensaje indicando el error
                }
                finally{
                	
                	for (int i=1; i<visitados.size()-1; i++){
                         panel.setmatriz(visitados.get(i).getX(), visitados.get(i).getY(),5);
           
                         panel.repaint();                          
                    }
                	
                	for (int i=0; i<caminominimo.size()-1; i++){
                         panel.setmatriz(caminominimo.get(i).getX(), caminominimo.get(i).getY(), 4);
                         panel.repaint();
                    }
                    pasos.setText(Integer.toString(simul1.contador()));

                }
               
               
            }
        }
        );
        //Definición de acción de pulsar y arrastrar sobre tablero
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int pX = e.getX();
                int pY = e.getY();

                if(creaobst.isSelected()==true){

                    panel.setmatriz(pX/ladocelda, pY/ladocelda, 1);
                    panel.repaint();
                }
                if(borrarobst.isSelected()==true){
                    if (panel.getmatriz(pX/ladocelda, pY/ladocelda)==2){
                        inicioset=false;
                    }
                    if (panel.getmatriz(pX/ladocelda, pY/ladocelda)==3){
                        finalset=false;
                    }

                    panel.setmatriz(pX/ladocelda, pY/ladocelda, 0);
                    panel.repaint();
                    if (panel.getmatriz(pX/ladocelda, pY/ladocelda)==2){
                        inicioset=false;
                    }
                    if (panel.getmatriz(pX/ladocelda, pY/ladocelda)==3){
                        finalset=false;
                    }

                }
            }
        }
        );
        //Definición de acción de pulsar sobre tablero
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int pX = e.getX();
                int pY = e.getY();
                if(creaobst.isSelected()==true){
                    if (panel.getmatriz(pX/ladocelda, pY/ladocelda)==2){
                        inicioset=false;
                    }
                    if (panel.getmatriz(pX/ladocelda, pY/ladocelda)==3){
                        finalset=false;
                    }

                    panel.setmatriz(pX/ladocelda, pY/ladocelda, 1);
                    panel.repaint();
                }
                if(borrarobst.isSelected()==true){
                    if (panel.getmatriz(pX/ladocelda, pY/ladocelda)==2){
                        inicioset=false;
                    }
                    if (panel.getmatriz(pX/ladocelda, pY/ladocelda)==3){
                        finalset=false;
                    }
                    panel.setmatriz(pX/ladocelda, pY/ladocelda, 0);
                    panel.repaint();
                    if (panel.getmatriz(pX/ladocelda, pY/ladocelda)==2){
                        inicioset=false;
                    }
                    if (panel.getmatriz(pX/ladocelda, pY/ladocelda)==3){
                        finalset=false;
                    }

                }

                if (iniciorobot.isSelected()==true && inicioset == false){
                    inicioset = true;
                    panel.setmatriz(pX/ladocelda, pY/ladocelda, 2);
                    panel.repaint();
                }
                if (finalrobot.isSelected()==true && finalset == false){
                    finalset = true;
                    panel.setmatriz(pX/ladocelda, pY/ladocelda, 3);
                    panel.repaint();
                }

            }
        });
        //Definición de parámetros de botones, casillas de formularios y radiobuttons.
        btnPulsar.setBounds(33, 103, 117, 25);
        frame.getContentPane().add(btnPulsar);

        alto = new JTextField();
        alto.setBounds(99, 28, 69, 19);
        frame.getContentPane().add(alto);
        alto.setColumns(10);

        ancho = new JTextField();
        ancho.setBounds(99, 59, 69, 19);
        frame.getContentPane().add(ancho);
        ancho.setColumns(10);

        JLabel lblAlto = new JLabel("Alto");
        lblAlto.setBounds(11, 30, 70, 15);
        frame.getContentPane().add(lblAlto);

        JLabel lblAncho = new JLabel("Ancho");
        lblAncho.setBounds(11, 61, 70, 19);
        frame.getContentPane().add(lblAncho);

        frecuencia = new JTextField();
        frecuencia.setText("50");
        frecuencia.setBounds(33, 203, 114, 19);
        frame.getContentPane().add(frecuencia);
        frecuencia.setColumns(10);

        
        btnObstculos.setBounds(33, 234, 117, 25);
        frame.getContentPane().add(btnObstculos);


        mouse.add(creaobst);
        creaobst.setBounds(9, 399, 141, 23);
        frame.getContentPane().add(creaobst);


        mouse.add(borrarobst);
        borrarobst.setBounds(9, 437, 141, 23);
        frame.getContentPane().add(borrarobst);

        mouse.add(iniciorobot);
        iniciorobot.setBounds(9, 478, 141, 23);
        frame.getContentPane().add(iniciorobot);

        mouse.add(finalrobot);
        finalrobot.setBounds(11, 514, 157, 23);
        frame.getContentPane().add(finalrobot);


        JLabel lblFuncionesDelMouse = new JLabel("Funciones del Mouse");
        lblFuncionesDelMouse.setBounds(10, 364, 172, 16);
        frame.getContentPane().add(lblFuncionesDelMouse);

        JLabel lblObstculosAleatorios = new JLabel("Obstaculos Aleatorios");
        lblObstculosAleatorios.setBounds(12, 172, 157, 15);
        frame.getContentPane().add(lblObstculosAleatorios);


        
        btnSimular.setBounds(31, 543, 119, 23);
        frame.getContentPane().add(btnSimular);

        JLabel lblHeuristica = new JLabel("Heuristica");
        lblHeuristica.setBounds(11, 272, 139, 14);
        frame.getContentPane().add(lblHeuristica);

        

        pasos = new JTextField();
        pasos.setHorizontalAlignment(SwingConstants.CENTER);
        pasos.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pasos.setEditable(false);
        pasos.setBounds(11, 603, 86, 20);
        frame.getContentPane().add(pasos);
        pasos.setColumns(10);

        JLabel lblPasos = new JLabel("Pasos");
        lblPasos.setBounds(31, 578, 46, 14);
        frame.getContentPane().add(lblPasos);

        heuristicas.add(rdbtnManhattan);
        rdbtnManhattan.setBounds(11, 319, 109, 23);
        frame.getContentPane().add(rdbtnManhattan);
        
        heuristicas.add(rdbtnEuclidea);
        rdbtnEuclidea.setBounds(11, 293, 109, 23);
        frame.getContentPane().add(rdbtnEuclidea);

    }
    public void obstaculosaleatorios(){
		int freq = Integer.parseInt( frecuencia.getText());
		if ((anchovalue*altovalue)-panel.controlobstaculoreturn() < freq*anchovalue*altovalue/100){
			freq=(anchovalue*altovalue)-panel.controlobstaculoreturn();
			
		}
		else{
			freq=freq*anchovalue*altovalue/100;
			
		}
		
		Random rn = new Random();
		for (int i=0; i < freq;){
			int random = rn.nextInt(altovalue*anchovalue);
			int nfila = random/anchovalue; //Se divide el aleatorio entre las celdas de ancho para obtener la fila
			int ncol = random%anchovalue; //Se usa el resto de la division para calcular la columna
			if (panel.getmatriz(ncol, nfila)==2){
				inicioset=false;						
			}
			if (panel.getmatriz(ncol, nfila)==3){
				finalset=false;						
			}
			if (panel.getmatriz(ncol, nfila)!=1){ //condicional para comprobar si ya existe obstáculo en la celda
				panel.setmatriz(ncol, nfila, 1);
				i++; 
			}
		}
		panel.repaint();
	}
}

