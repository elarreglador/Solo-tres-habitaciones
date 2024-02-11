import java.util.Random;

// Tiramos 10 dados de 6 caras cada vez, 
public class estadistica {
    public static void main(String[] args) {
        Random random = new Random();
        String[] arrayList = new String[120];

        for (int juegos = 0; juegos < 2000; juegos++) {

            // ESTADISTICA DEL GRUPO DE JUGADORES QUE TIRAN LOS DADOS repetimos VECES
            int sumatorio = 0;
            for (int repetimos = 0; repetimos < 1; repetimos++) { 
                for (int i = 0; i < 10; i++) { // tiramos X veces el dado (id6)
                    int numeroAlAzar = random.nextInt(6) + 1; // este es nuestro dado
                    sumatorio = sumatorio + numeroAlAzar;
                }
            }

            // apunta el resultado del sumatorio
            arrayList[sumatorio] = arrayList[sumatorio] + "*";

        }

        // ***********************************************
        // ESTADISTICA DEL GRUPO DE JUGADORES QUE TIRAN LOS DADOS repetimos VECES

        for (int juegos = 0; juegos < 2000; juegos++) {

            int sumatorio = 0;
            for (int repetimos = 0; repetimos < 2; repetimos++) {
                for (int i = 0; i < 10; i++) {
                    int numeroAlAzar = random.nextInt(6) + 1;
                    sumatorio = sumatorio + numeroAlAzar;
                }
            }

            // apunta el resultado del sumatorio
            arrayList[sumatorio] = arrayList[sumatorio] + "*";

        }

        // ***********************************************
        // MUESTRA LA GRAFICA DE AMBOS GRUPOS DE JUGADORES
        for (int i = 0; i < arrayList.length; i++) {
            System.out.println(i + " - " + arrayList[i]);
        }
    }
}

// CONCLUIMOS QUE YA SEAMOS DEL GRUPO QUE TIRA LOS DADOS 1 O 2 VECES, OBTENER COMO SUMA
// EL 51 ES MUY POCO PROBABLE