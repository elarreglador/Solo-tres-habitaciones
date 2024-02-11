
import java.io.*;
import java.net.*;

public class Servidor {

	@SuppressWarnings("resource")
	public static void main(String args[]) throws IOException {
		int puerto = 9009;
		ServerSocket servidor; // CREE serverSocket
		servidor = new ServerSocket(puerto);
		System.out.println("[SERVIDOR] Inicio servidor en puerto "+puerto);

		//Objeto para almacenar informacion
		Top top3 = new Top(3);
		top3.setTop("Isildur", 34);
		top3.setTop("Bilbo", 27);
		top3.setTop("Frodo", 26);

		while (true) {
			Socket socketCliente = new Socket();
			socketCliente = servidor.accept(); // ESPERO AL CLIENTE
			System.out.println("[SERVIDOR] Se ha inicia hilo para atender al cliente.");
			ServidorHilo fil = new ServidorHilo(socketCliente, top3);
			fil.start();
		}
	}
}
