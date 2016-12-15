package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Application {
		private final static int PORT = 1000;
		private final static String HOST = "localhost"; 
	
	public static void main(String[] args) {
		Socket s = null;		
		try {
			// Cree une socket pour communiquer avec le service se trouvant sur la
			// machine host au port PORT
			s = new Socket(HOST, PORT);
			// Cree les streams pour lire et ecrire du texte dans cette socket
			BufferedReader sin = new BufferedReader (new InputStreamReader(s.getInputStream ( )));
			PrintWriter sout = new PrintWriter (s.getOutputStream ( ), true);
			// Cree le stream pour lire du texte a partir du clavier 
			// (on pourrait aussi utiliser Scanner)
			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));			
			// Informe l'utilisateur de la connection
			System.out.println("Connect� au serveur " + s.getInetAddress() + ":"+ s.getPort());
			
			String line;
			while(true) {
				System.out.print("> "); System.out.flush();
				line = clavier.readLine();
				if (line.equals("")) break;
				// envoie au serveur
				sout.println(line);
				// lit la r�ponse provenant du serveur
				line = sin.readLine();
				// Verifie si la connection est fermee.
				// Si oui on sort de la boucle
				if (line == null) { 
					System.out.println("Connection fermee"); 
					break;
				}
				// Ecrit la ligne envoyee par le serveur
				System.out.println(line);
			}
		}
		catch (IOException e) {System.out.println("Connection fermee par le serveur");}
		System.out.println("Bye");
		// Refermer dans tous les cas la socket
		try { if (s != null) s.close(); } 
		catch (IOException e2) { ; }		
	}
}
