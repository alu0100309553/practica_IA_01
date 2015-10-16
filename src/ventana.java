import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D; 
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import static java.lang.Math.abs;

import java.awt.Canvas;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;


public class ventana {

	public JFrame frame;
	private JTextField alto;
	private JTextField ancho;
	public JPanel contenedor;
	public int ladocelda;
	public int altovalue;
	public int anchovalue;
	public int slider;
	tablero panel = new tablero();
	JRadioButton creaobst = new JRadioButton("Obstáculos");
	JRadioButton borrarobst = new JRadioButton("Borrar");
	private JTextField frecuencia;
	public final ButtonGroup buttonGroup = new ButtonGroup();
	public ventana() {
		initialize();
	}

	public void obstaculosaleatorios(){
		int freq = Integer.parseInt( frecuencia.getText());
		freq = freq*anchovalue*altovalue/100;
		Random rn = new Random();
		for (int i=0; i < freq;){
			int random = rn.nextInt(altovalue*anchovalue);
			int nfila = random/anchovalue;
			int ncol = random%anchovalue;
			if (panel.getmatriz(ncol, nfila)==0){ //condicional para comprobar si ya existe obstáculo en la celda
				panel.setmatriz(ncol, nfila, 1);
				i++; 
			}
		}
		panel.repaint();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Biohazard Agent");
		frame.setBounds(100, 100, 1600, 950);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		contenedor = new JPanel();
		
		contenedor.setBackground(Color.GRAY);
		contenedor.setBounds(180, 0, 1400, 900);
		frame.getContentPane().add(contenedor);
	

		JButton btnPulsar = new JButton("Cuadrícula");
		btnPulsar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				altovalue = Integer.parseInt( alto.getText());
				anchovalue = Integer.parseInt( ancho.getText());
				ladocelda = 0;
				if ((1400/anchovalue)<=(900/altovalue)){//Comparación para ver que lado me limita el tamaño de celda, se coje el más pequeño para quepa en el lienzo.
					ladocelda = (1400/anchovalue);
				}
				else {
					ladocelda = (900/altovalue);
				}
				
				panel.inicilizar(altovalue, anchovalue,ladocelda);				
				panel.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel.setBackground(Color.WHITE);
				int pospanelx = (700-(anchovalue*ladocelda/2));
				int pospanely = (450-(altovalue*ladocelda/2));
				panel.setBounds(pospanelx, pospanely, ladocelda*anchovalue, ladocelda*altovalue);
				contenedor.add(panel);
				contenedor.repaint();
			}
		}
		);
		
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
		frecuencia.setBounds(22, 235, 114, 19);
		frame.getContentPane().add(frecuencia);
		frecuencia.setColumns(10);
		
		JButton btnObstculos = new JButton("Obstáculos");
		btnObstculos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				obstaculosaleatorios();	
			}
		}
		);
		btnObstculos.setBounds(33, 278, 117, 25);
		frame.getContentPane().add(btnObstculos);
		
		
		buttonGroup.add(creaobst);
		creaobst.setBounds(11, 342, 141, 23);
		frame.getContentPane().add(creaobst);
		
		
		buttonGroup.add(borrarobst);
		borrarobst.setBounds(11, 377, 141, 23);
		frame.getContentPane().add(borrarobst);
		
		JRadioButton iniciorobot = new JRadioButton("Inicio");
		buttonGroup.add(iniciorobot);
		iniciorobot.setBounds(11, 412, 141, 23);
		frame.getContentPane().add(iniciorobot);
		
		JRadioButton finalrobot = new JRadioButton("Final");
		buttonGroup.add(finalrobot);
		finalrobot.setBounds(11, 440, 141, 23);
		frame.getContentPane().add(finalrobot);
		
		JLabel lblFuncionesDelMouse = new JLabel("Funciones del Mouse");
		lblFuncionesDelMouse.setBounds(20, 315, 148, 16);
		frame.getContentPane().add(lblFuncionesDelMouse);
	
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int pX = e.getX();
				int pY = e.getY();
				
				if(creaobst.isSelected()==true){
					panel.setmatriz(pX/ladocelda, pY/ladocelda, 1);
					panel.repaint();
				}
				if(borrarobst.isSelected()==true){
					panel.setmatriz(pX/ladocelda, pY/ladocelda, 0);
					panel.repaint();
				}
			}
		});
		
		
		
		
	}
}

