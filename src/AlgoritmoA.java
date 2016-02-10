import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class AlgoritmoA {
	    private int heuristica;
        private mapa map;
        private ArrayList<nodo> listaCerrada;
        private Listaordenada listaAbierta;
        private camino caminominimo;
        public ArrayList<nodo> visitados;
        public int contador;

        AlgoritmoA(mapa map, int heuristica) {
                this.map = map;
                this.heuristica=heuristica;
                this.contador=0;
                listaCerrada = new ArrayList<nodo>();
                listaAbierta = new Listaordenada();
        }

        public float getCosteEstimado(int comienzoX, int comienzoY, int destinoX, int destinoY) {


        	float dx = destinoX - comienzoX;
            float dy = destinoY - comienzoY;
            float resultado=0;
            if (heuristica==1)
           resultado = (float) Math.sqrt((dx*dx)+(dy*dy));
            else
            	if (heuristica==0)
            		 resultado = (float) Math.abs(dx)+Math.abs(dy);



            return resultado;
    }

        public camino calcaminominimo(int comienzoX, int comienzoY, int destinoX, int destinoY) {

                map.setPuntoPartida(comienzoX, comienzoY);
                map.setPuntoDestino(destinoX, destinoY);


                if (map.getnodo(destinoX, destinoY).esObstaculo) {
                        return null;
                }

                
                listaCerrada.clear();
                listaAbierta.clear();
                //Establecer distancias nodo inicio
                map.getnodoInicio().setdistanciaDesdeComienzo(0);
                map.getnodoInicio().setDistanciaHeuristica(getCosteEstimado(map.getnodoInicio().getX(), map.getnodoInicio().getY(), map.getPuntoDestinoX(), map.getPuntoDestinoY()));
                listaAbierta.anadir(map.getnodoInicio());
                visitados=new ArrayList<nodo>();

                while(listaAbierta.tamano() != 0) {
                	contador++;	

                        nodo actual = listaAbierta.getPrimero();
                        visitados.add(actual);
                        if(actual.getX() == map.getPuntoDestinoX() && actual.getY() == map.getPuntoDestinoY()) {
                                return reconstruircamino(actual);
                        }


                        listaAbierta.eliminar(actual);
                        listaCerrada.add(actual);


                        for(nodo vecino : actual.getListaVecinos()) {
                                boolean elvecinoEsMejor;


                                if (listaCerrada.contains(vecino))
                                        continue;


                                if (!vecino.esObstaculo) {


                                        float DistanciaDesdeComienzoVecino = (actual.getdistanciaDesdeComienzo() + 1);/*map.getDistanciaEntre(actual, vecino));*/


                                        if(!listaAbierta.contiene(vecino)) { //si lista abierta no contiene vecino añadirlo y actualizarlo
                                                listaAbierta.anadir(vecino);
                                                vecino.setDistanciaHeuristica(getCosteEstimado(vecino.getX(), vecino.getY(), map.getPuntoDestinoX(), map.getPuntoDestinoY()));
                                                elvecinoEsMejor = true;

                                        } else if(vecino.getdistanciaDesdeComienzo()< DistanciaDesdeComienzoVecino) { //si lo contiene pero su distancia es mejor, dejarlo como esta
                                                elvecinoEsMejor = false;
                                        } else {
                                                elvecinoEsMejor = true; //si lo contiene pero la nueva distancia es mejor, actualizarlo
                                        }

                                        if (elvecinoEsMejor) {
                                                vecino.setNodoprevio(actual);
                                                vecino.setdistanciaDesdeComienzo(DistanciaDesdeComienzoVecino);
                                        }
                                }

                        }

                }
                return null;
        }

        public ArrayList<nodo> mostrarvisitados() {
            return visitados;
        }

        public ArrayList<nodo> mostrarcamino() {
            return caminominimo.getcamino();
        }

        private camino reconstruircamino(nodo nodo) {
                camino camino = new camino();
                while(!(nodo.getNodoprevio() == null)) {
                        camino.anadirNodoInicio(nodo);
                        nodo = nodo.getNodoprevio();
                }
                this.caminominimo = camino;
                return camino;
        }

        private class Listaordenada {

                private ArrayList<nodo> list = new ArrayList<nodo>();

                public nodo getPrimero() {
                        return list.get(0);
                }

                public void clear() {
                        list.clear();
                }

                public void anadir(nodo nodo) {
                        list.add(nodo);
                        Collections.sort(list);
                }

                public void eliminar(nodo n) {
                        list.remove(n);
                }

                public int tamano() {
                        return list.size();
                }

                public boolean contiene(nodo n) {
                        return list.contains(n);
                }
        }

}
