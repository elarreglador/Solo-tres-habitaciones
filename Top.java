public class Top {
    private int[] puntos;
    private String[] nombre;

    public Top(int numero) {
        puntos = new int[3];
        nombre = new String[3];
    }

    public static void main(String[] args) {
        Top top3 = new Top(3);
        top3.setTop("Pedro", 14);
        top3.setTop("Juan", 2);
        top3.setTop("Luca", 10);
        top3.setTop("Isidoro", 49);
        System.out.println(top3.getTop());
    }

    // DEVUELVE EL TOP PARA SER MOSTRADO AL JUGADOR
    public String getTop() {
        String retorno = "";
        for (int i = 0; i < puntos.length; i++) {
            retorno = retorno + "["+(i + 1) + "]" + nombre[i] + " - " + puntos[i] + " puntos.\n";
            if(i == 2){ //salto de linea entre posicion 3 y 4
                retorno = retorno + "\n";
            }
        }
        return retorno;
    }

    // SI EL JUGADOR TIENE UNA POSICION ACEPTABLE ENTRARA EN EL TOP
    public void setTop(String jugador, int puntuacion) {
        // Buscar la posición del jugador con la menor puntuación
        int posMenor = 0;
        for (int i = 1; i < puntos.length; i++) {
            if (puntos[i] < puntos[posMenor]) {
                posMenor = i;
            }
        }

        // Si el nuevo jugador tiene mayor puntuación que el jugador del
        // top con menos puntos, reemplazamos a este ultimo por el nuevo
        // jugador y luego ordenamos el array.
        if (puntuacion > puntos[posMenor]) {
            puntos[posMenor] = puntuacion;
            nombre[posMenor] = jugador;
            ordenar(puntos, nombre);
        }
    }

    // ORDENA LOS DOS ARRAYS AL MISMO TIEMPO
    private void ordenar(int[] puntos, String[] nombre) {
        for (int i = 0; i < puntos.length - 1; i++) {
            for (int j = i + 1; j < puntos.length; j++) {
                if (puntos[i] < puntos[j]) {
                    // Intercambiar puntuaciones
                    int tempPuntos = puntos[i];
                    puntos[i] = puntos[j];
                    puntos[j] = tempPuntos;
                    // Intercambiar nombres
                    String tempNombre = nombre[i];
                    nombre[i] = nombre[j];
                    nombre[j] = tempNombre;
                }
            }
        }
    }

}
