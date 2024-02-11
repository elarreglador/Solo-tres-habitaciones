import java.io.*;
import java.net.*;
import java.util.Random;

public class ServidorHilo extends Thread {
	DataInputStream flujoEntrada = null;
	DataOutputStream flujoSalida = null;
	Socket socket = null;

	Top top3;

	// constructor FilServidor
	public ServidorHilo(Socket s, Top top3) throws IOException {
		System.out.println("[SERVIDOR_HILO] Constructor ServidorHilo().");
		socket = s;
		this.top3 = top3;
	}

	// LOGICA DEL SERVIDOR.
	public void run() {
		try {
			// CREA FLUJOS DE ENTRADA Y SALIDA
			flujoEntrada = new DataInputStream(socket.getInputStream());
			flujoSalida = new DataOutputStream(socket.getOutputStream());

			// COMUNICACION CON EL CLIENTE 
			// recibimos el numero maximo
			int maximo = flujoEntrada.readInt();

			// enviamos la primera tirada
			flujoSalida.writeInt(tirada());

			// enviamos el resto de tiradas
			while (flujoEntrada.readBoolean()) {
				flujoSalida.writeInt(tirada());
			}

			// recibimos nombre y puntos del jugador
			String nombre = flujoEntrada.readUTF();
			int sumatorio = flujoEntrada.readInt();

			// enviamos top de mejores jugadores
			if (sumatorio <= maximo) {
				top3.setTop(nombre, sumatorio);
			}
			flujoSalida.writeUTF(top3.getTop());

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			// CIERRO FLUJOS Y SOCKET
			System.out.println("[SERVIDOR_HILO] Cierro comunicacion con:");
			System.out.println("   " + socket.toString());
			System.out.println();
			try {
				flujoSalida.close();
			} catch (IOException e) {
				System.out.println("IOException en ServidorHilo al cerrar flujoSalida : " + e);
			}
			try {
				flujoEntrada.close();
			} catch (IOException e) {
				System.out.println("IOException en ServidorHilo al cerrar flujoEntrada: " + e);
			}
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("IOException en ServidorHilo al cerrar socket: " + e);
			}
		}
	}

	public int tirada() {
		Random random = new Random();
		int num;
		int suma = 0;
		for (int i = 0; i < 10; i++) {
			num = random.nextInt(6) + 1;
			suma = suma + num;
		}
		return suma;
	}
}
