import java.util.ArrayList;

public class nodo implements Comparable<nodo> {
        nodo norte;
        nodo este;
        nodo sur;
        nodo oeste;
        ArrayList<nodo> listaVecinos;
        boolean visitado;
        float distanciaDesdeComienzo;
        float DistanciaHeuristica;
        nodo Nodoprevio;
        int x;
        int y;
        boolean esObstaculo;
        boolean esInicio;
        boolean esDestino;

        nodo(int x, int y) {
                listaVecinos = new ArrayList<nodo>();
                this.x = x;
                this.y = y;
                this.visitado = false;
                this.distanciaDesdeComienzo = Integer.MAX_VALUE;
                this.esObstaculo = false;
                this.esInicio = false;
                this.esDestino = false;
        }

        nodo (int x, int y, boolean visitado, int distanciaDesdeComienzo, boolean esObstaculo, boolean esInicio, boolean esDestino) {
                listaVecinos = new ArrayList<nodo>();
                this.x = x;
                this.y = y;
                this.visitado = visitado;
                this.distanciaDesdeComienzo = distanciaDesdeComienzo;
                this.esObstaculo = esObstaculo;
                this.esInicio = esInicio;
                this.esDestino = esDestino;
        }

        public nodo getnorte() {
                return norte;
        }

        public void setnorte(nodo norte) {
                if (listaVecinos.contains(this.norte))
                        listaVecinos.remove(this.norte);
                listaVecinos.add(norte);

                this.norte = norte;
        }


        public nodo geteste() {
                return este;
        }

        public void seteste(nodo este) {
                if (listaVecinos.contains(this.este))
                        listaVecinos.remove(this.este);
                listaVecinos.add(este);

                this.este = este;
        }


        public nodo getsur() {
                return sur;
        }

        public void setsur(nodo sur) {
                if (listaVecinos.contains(this.sur))
                        listaVecinos.remove(this.sur);
                listaVecinos.add(sur);

                this.sur = sur;
        }



        public nodo getoeste() {
                return oeste;
        }

        public void setoeste(nodo oeste) {
                if (listaVecinos.contains(this.oeste))
                        listaVecinos.remove(this.oeste);
                listaVecinos.add(oeste);

                this.oeste = oeste;
        }



        public ArrayList<nodo> getListaVecinos() {
                return listaVecinos;
        }

        public boolean esVisitado() {
                return visitado;
        }

        public void setVisitado(boolean visitado) {
                this.visitado = visitado;
        }

        public float getdistanciaDesdeComienzo() {
                return distanciaDesdeComienzo;
        }

        public void setdistanciaDesdeComienzo(float f) {
                this.distanciaDesdeComienzo = f;
        }

        public nodo getNodoprevio() {
                return Nodoprevio;
        }

        public void setNodoprevio(nodo Nodoprevio) {
                this.Nodoprevio = Nodoprevio;
        }

        public float getDistanciaHeuristica() {
                return DistanciaHeuristica;
        }

        public void setDistanciaHeuristica(float DistanciaHeuristica) {
                this.DistanciaHeuristica = DistanciaHeuristica;
        }

        public int getX() {
                return x;
        }

        public void setX(int x) {
                this.x = x;
        }

        public int getY() {
                return y;
        }

        public void setY(int y) {
                this.y = y;
        }

        public boolean esObstaculo() {
                return esObstaculo;
        }

        public void setObstaculo(boolean esObstaculo) {
                this.esObstaculo = esObstaculo;
        }

        public boolean esInicio() {
                return esInicio;
        }

        public void setInicio(boolean esInicio) {
                this.esInicio = esInicio;
        }

        public boolean esDestino() {
                return esDestino;
        }

        public void setDestino(boolean esDestino) {
                this.esDestino = esDestino;
        }

        public boolean equivale(nodo nodo) {
                return (nodo.x == x) && (nodo.y == y);
        }

        public int compareTo(nodo othernodo) {
                float estaDistanciaTotalconDestino = DistanciaHeuristica + distanciaDesdeComienzo;
                float otraDistanciaTotalconDestino = othernodo.getDistanciaHeuristica() + othernodo.getdistanciaDesdeComienzo();

                if (estaDistanciaTotalconDestino < otraDistanciaTotalconDestino) {
                        return -1;
                } else if (estaDistanciaTotalconDestino > otraDistanciaTotalconDestino) {
                        return 1;
                } else {
                        return 0;
                }
        }
}
