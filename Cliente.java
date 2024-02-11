import java.net.*;
import java.io.*;

public class Cliente {

	public static void main(String args[]) throws IOException {
		int puerto = 9009;
		DataInputStream flujoEntrada = null;
		DataOutputStream flujoSalida = null;

		BufferedReader flujoTeclado = null;

		int maximo = 59;

		try (Socket socket = new Socket("localhost", puerto)) {
			// CREA FLUJOS DE ENTRADA Y SALIDA
			flujoEntrada = new DataInputStream(socket.getInputStream());
			flujoSalida = new DataOutputStream(socket.getOutputStream());

			flujoTeclado = new BufferedReader(new InputStreamReader(System.in));

			// COMUNICACION CON EL SERVIDOR
			// enviamos el numero maximo
			flujoSalida.writeInt(maximo);

			System.out.println("                           SOLO TRES HABITACIONES");
            System.out.println("                                por David M.");
			System.out.println();
			System.out.println(
					"La noche ha caido hace un par de horas, a duras penas has podido seguir el sendero, por suerte\n" +
							"la luna menguante te ha llevado hasta unas puertas de madera iluminadas levemente por dos \n"
							+
							"faroles de aceite. \n" +
							"\n" +
							"La portezuela incrustada en la puerta se abre durante unos instantes, alguien te observa de arriba a \n"
							+ "abajo, se vuelve a cerrar y los segundos pasan uno tras otro hasta convertirse en una eternidad... \n"
							+ "\n" +
							"El silencio de la noche te permite escuchar algunos pasos tras la puerta seguidos de estas palabras: \n"
							+
							"\n" +
							"- QUIEN VA?\n" +
							"- Un viajero buscando techo -con ciertas dudas y para darte algo de peso agregas- Los dioses me gian.\n"
							+
							"- En ese caso seran ellos quien decidan si pernoctas en este lugar!\n" +
							"\n" +
							"La hoja izquierda de la puerta se abre pesadamante cortando el silencio de la noche, una mano te \n"
							+
							"muestra diez dados mientras te dicen:\n" +
							"\n" +
							"- Solo tres habitaciones tenemos esta noche, acercate al "+maximo+" lanzando los diez dados, repite tu tirada \n"
							+
							"tantas veces desees, siempre diez dados tiraras, si estas entre los tres mejores aqui podras quedar \n."
							+
							"hasta el amanecer, pero evita superar este numero o volveras al camino.");

			int tirada1 = flujoEntrada.readInt();
			System.out.println("- Viajero, cual es tu nombre?");
			System.out.print("- Yo soy ");
			String nombre = flujoTeclado.readLine();
			System.out.println("- Adelante " + nombre + ", dejemos que la fortuna decida...");
			System.out.println();
			System.out.println(
					"Los dados ruedan presurosos, uno a uno sumas cada una de las caras hasta alcanzar " + tirada1);

			int sumatorio = tirada1;
			Boolean jugamos;
			do {
				System.out.println();
				if (sumatorio < maximo) { // aun no nos hemos pasado
					System.out.print("- Bien, ahora llevais " + sumatorio + ", debeis alcanzar el "+maximo+" decidme, repetireis tirada? [s/n]");
					String repetimos = flujoTeclado.readLine();
					if (repetimos.toUpperCase().equals("S")) {
						System.out.println("- Si, veamos que me deparan los dados...");
						flujoSalida.writeBoolean(true);
						jugamos = true;
					} else {
						System.out.println("- No gracias.");
						flujoSalida.writeBoolean(false);
						jugamos = false;
					}
				} else {
					System.out
							.println("- Uhmmm... " + sumatorio
									+ ", diria que los dioses no estan de tu lado viajero... La fria noche te espera.");
					flujoSalida.writeBoolean(false);
					jugamos = false;
				}
				if (jugamos) {
					sumatorio = sumatorio + flujoEntrada.readInt();
				}
			} while (jugamos == true);

			// enviamos nombre y puntos al servidor
			flujoSalida.writeUTF(nombre);
			flujoSalida.writeInt(sumatorio);

			// recibimos top de mejores jugadores
			String top = flujoEntrada.readUTF();
			System.out.println();
			System.out.println("- Esta noche dormiran bajo techo estos viajeros:");
			System.out.println();
			System.out.println(top);

		} finally {
			// CIERRA FLUJOS Y SOCKET
			System.out.println("Recuerda viajero, la suerte es como una mariposa: si la persigues, se te escapa; pero si te quedas/n"+
			"quieto, puede que se pose en tu mano.");
			flujoSalida.close();
			flujoEntrada.close();
			flujoTeclado.close();
		}
	}
}
