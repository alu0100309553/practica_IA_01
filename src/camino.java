
import java.util.ArrayList;

public class camino {

        private ArrayList<nodo> nodos = new ArrayList<nodo>();


        public camino() {
        }

        public int getLongitud() {
                return nodos.size();
        }

        public nodo getnodo(int i) {
                return nodos.get(i);
        }

        public ArrayList<nodo> getcamino() {
            return nodos;
    }

        public int getX(int i) {
                return getnodo(i).getX();
        }


        public int getY(int i) {
                return getnodo(i).getY();
        }


        public void anadirNodo(nodo n) {
                nodos.add(n);
        }


        public void anadirNodoInicio(nodo n) {
                nodos.add(0, n);
        }


        public boolean contiene(int x, int y) {
                for(nodo nodo : nodos) {
                        if (nodo.getX() == x && nodo.getY() == y)
                                return true;
                }
                return false;
        }

}

