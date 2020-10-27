package it.corso.accenture.entities;

import java.io.*;
import java.util.*;

public class Menu {
	public static void menu() {
		Scanner input = new Scanner(System.in);
		int scelta = 0;
		File fileRubrica = Util.creaFile("rubrica.txt");
		List<Contatto> rubrica = new ArrayList<Contatto>();
		Util.riempiDaFile(rubrica, fileRubrica);
		System.out.println("Benvenuto nella rubrica!\n\n"
				+ "[---------------[MENU]---------------]\n");
		try {
		do {
			System.out.print(""
					+ "[1]PER VISUALIZZARE LA RUBRICA\n"
					+ "[2]PER INSERIRE UN NUOVO CONTATTO\n"
					+ "[3]PER ELIMINARE UN CONTATTO DAL NOME\n"
					+ "[4]PER ELIMINARE UN CONTATTO DA NOME E NUMERO\n"
					+ "[5]PER AGGIORNARE UN CONTATTO\n"
					+ "[6]PER TROVARE UN CONTATTO DAL NOME\n"
					+ "[7]PER TROVARE UN CONTATTO DAL NUMERO\n"
					+ "[0]PER USCIRE\n"
					+ "\nLa tua scelta: ");
			scelta = input.nextInt();
			switch (scelta) {
			case 0:
				System.out.println("\n[--------------[EXIT]----------------]\n");
				break;
			case 1:
				//visualizza rubrica
				System.out.println("\n[-------------[RUBRICA]--------------]\n");
				System.out.println(Util.printFile(Util.printRubrica(rubrica, fileRubrica)));
				System.out.println("[---------------[MENU]---------------]\n");
				break;
			case 2:
				//inserisci contatto
				System.out.println("\n[-------[INSERIMENTO CONTATTO]-------]\n");
				System.out.print("NOME DEL CONTATTO DA SALVARE: ");
				String nome = input.next();
				System.out.print("NUMERO DEL CONTATTO DA SALVARE: ");
				String numero = input.next();
				Contatto contatto = new Contatto(nome, numero);
				Util.aggiungiContatto(rubrica, contatto);
				Util.printRubrica(rubrica, fileRubrica);
				System.out.println("\n[---------------[MENU]---------------]\n");
				break;
			case 3:
				//delete contatto da nome
				System.out.println("\n[-----[ELIMINA CONTATTO DA NOME]-----]\n");
				System.out.print("NOME DEL CONTATTO DA ELIMINARE: ");
				nome = input.next();
				Util.eliminaContattoDaNome(rubrica, nome);
				Util.printRubrica(rubrica, fileRubrica);
				System.out.println("\n[---------------[MENU]---------------]\n");
				break;
			case 4:
				//delete contatto da oggetto
				System.out.println("\n[---------[ELIMINA CONTATTO]---------]\n");
				System.out.print("NOME DEL CONTATTO DA ELIMINARE: ");
				nome = input.next();
				System.out.print("NUMERO DEL CONTATTO DA ELIMINARE: ");
				numero = input.next();
				contatto = new Contatto(nome, numero);
				Util.eliminaContatto(rubrica, contatto);
				Util.printRubrica(rubrica, fileRubrica);
				System.out.println("\n[---------------[MENU]---------------]\n");
				break;
			case 5:
				//aggiorna contatto
				System.out.println("\n[---------[AGGIORNA CONTATTO]--------]\n");
				System.out.println(Util.printFile(Util.printRubrica(rubrica, fileRubrica)));
				System.out.print("CODICE DEL CONTATTO DA AGGIORNARE: ");
				int codice = input.nextInt();
				Util.aggiornaContatto(rubrica, codice);
				Util.printRubrica(rubrica, fileRubrica);
				System.out.println("\n[---------------[MENU]---------------]\n");
				break;
			case 6:
				//ricerca per nome
				System.out.println("\n[-----------[CERCA DA NOME]----------]\n");
				System.out.print("CHE NOME VUOI CERCARE?: ");
				nome = input.next();
				Util.trovaContattoDaNome(rubrica, nome);
				System.out.println("\n[---------------[MENU]---------------]\n");
				break;
			case 7:
				//ricerca per numero
				System.out.println("\n[----------[CERCA DA NUMERO]---------]\n");
				System.out.print("CHE NUMERO VUOI CERCARE?: ");
				numero = input.next();
				Util.trovaContattoDaNumero(rubrica, numero);
				System.out.println("\n[---------------[MENU]---------------]\n");
				break;
			default:
				System.out.println("\n[--------------[ERRORE]--------------]\n");
				System.out.println("SCELTA NON VALIDA!");
				System.out.println("\n[---------------[MENU]---------------]\n");
				break;
			}
		}while(scelta != 0);
		}catch(InputMismatchException e) {
			System.out.println("NON HAI INSERITO UN NUMERO!");
		}
		System.out.println("Programma terminato...");
		input.close();
	}
}
