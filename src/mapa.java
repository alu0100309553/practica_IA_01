
import java.util.ArrayList;


public class mapa {

        private int anchoMapa;
        private int alturaMapa;
        private ArrayList<ArrayList<nodo>> map;
        private int puntoPartidaX = 0;
        private int puntoPartidaY = 0;
        private int PuntoDestinoX = 0;
        private int puntoDestinoY = 0;
        private matriz mapaObstaculos;


        mapa(int anchoMapa, int alturaMapa, matriz mapaobstaculos) {
                this.anchoMapa = anchoMapa;
                this.alturaMapa = alturaMapa;
                this.mapaObstaculos = mapaobstaculos;

                crearMapa();
                definirVecinos();

        }
        private void crearMapa() {
                nodo nodo;
                map = new ArrayList<ArrayList<nodo>>();
                for (int x=0; x<alturaMapa; x++) {
                        map.add(new ArrayList<nodo>());
                        for (int y=0; y<anchoMapa; y++) {
                                nodo = new nodo(x,y);
                                if (mapaObstaculos.getmatriz(x, y) == 1)
                                        nodo.setObstaculo(true);
                                map.get(x).add(nodo);
                        }
                }
        }


        private void definirVecinos() {
                for ( int x = 0; x <= alturaMapa-1; x++ ) {
                        for ( int y = 0; y <= anchoMapa-1; y++ ) {
                                nodo nodo = map.get(x).get(y);
                                if (!(y==0))
                                        nodo.setnorte(map.get(x).get(y-1));

                                if (!(x==alturaMapa-1))
                                        nodo.seteste(map.get(x+1).get(y));

                                if (!(y==anchoMapa-1))
                                        nodo.setsur(map.get(x).get(y+1));

                                if (!(x==0))
                                        nodo.setoeste(map.get(x-1).get(y));

                        }
                }
        }



        public ArrayList<ArrayList<nodo>> getnodos() {
                return map;
        }
        public void setObstaculo(int x, int y, boolean isObstical) {
                map.get(x).get(y).setObstaculo(isObstical);
        }

        public nodo getnodo(int x, int y) {
                return map.get(x).get(y);
        }

        public void setPuntoPartida(int x, int y) {
                map.get(puntoPartidaX).get(puntoPartidaY).setInicio(false);
                map.get(x).get(y).setInicio(true);
                puntoPartidaX = x;
                puntoPartidaY = y;
        }

        public void setPuntoDestino(int x, int y) {
                map.get(PuntoDestinoX).get(puntoDestinoY).setDestino(false);
                map.get(x).get(y).setDestino(true);
                PuntoDestinoX = x;
                puntoDestinoY = y;
        }

        public int getpuntoPartidaX() {
                return puntoPartidaX;
        }

        public int getpuntoPartidaY() {
                return puntoPartidaY;
        }

        public nodo getnodoInicio() {
                return map.get(puntoPartidaX).get(puntoPartidaY);
        }

        public int getPuntoDestinoX() {
                return PuntoDestinoX;
        }

        public int getPuntoDestinoY() {
                return puntoDestinoY;
        }

        public nodo getPuntoDestino() {
                return map.get(PuntoDestinoX).get(puntoDestinoY);
        }

        public float getDistanciaEntre (nodo nodo1, nodo nodo2) {

                if (nodo1.getX() == nodo2.getX() || nodo1.getY() == nodo2.getY()){
                        return 1*(alturaMapa+anchoMapa);
                } else {
                        return (float) 1.7*(alturaMapa+anchoMapa);
                }
        }

        public int getanchoMapa() {
                return anchoMapa;
        }
        public int getalturaMapa() {
                return alturaMapa;
        }
        public void limpiar() {
                puntoPartidaX = 0;
                puntoPartidaY = 0;
                PuntoDestinoX = 0;
                puntoDestinoY = 0;
                crearMapa();
                definirVecinos();
        }
}

