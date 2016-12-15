package serveur;

import java.io.*;
import java.net.*;


class ServiceInversion implements Runnable {
	private static int compteur = 1;
	private int numero;
	
	private Socket client;
	
	ServiceInversion(Socket socket) {
		this.numero = compteur++;
		client = socket;
	}

	public void run() {
		System.out.println("Nouveau client : " + this.client.getInetAddress());
		try {BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);

			while (true) {
				// lit la ligne
				String line = in.readLine();
				System.out.println("Service " + numero + " a recu " + line);
		
				// Si cette ligne est vide, le serviceh se termine
				if (line == null) break;
				if (line.equals("")) break;
				// sinon l'ecrit a l'envers
				String invLine = new String (new StringBuffer(line).reverse());
				// et l'envoie dans la socket
				out.println(invLine);
			}
		}
		catch (IOException e) {
		}
		System.err.println("Fin du service d'inversion " + this.numero + " : ");
		try {client.close();} catch (IOException e2) {}
	}
	
	protected void finalize() throws Throwable {
		 client.close(); 
	}

	// lancement du service
	public void lancer() {
		(new Thread(this)).start();		
	}

}
