package it.corso.accenture.entities;

import java.io.*;
import java.util.*;

public class Util {
	private static Scanner input;
	public static File creaFile(String path) {
		File file = new File(path);
		if(!file.exists()) {
			System.out.println("Il file non esisteva, l'ho creato");
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	public static void riempiDaFile(List<Contatto> rubrica, File file) {
		String line = null;
		try {
			BufferedReader r = null;
			r = new BufferedReader(new FileReader(file.getPath()));
			String[] infoContatto;
			while ((line = r.readLine()) != null) {
				infoContatto = line.split(" ");
				rubrica.add(new Contatto(infoContatto[1], infoContatto[2]));
			}
			r.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String printFile(File file) {
		String s ="", line = null;
		try {
			BufferedReader r = null;
			r = new BufferedReader(new FileReader(file.getPath()));
			while ((line = r.readLine()) != null) {
			s += line+"\n";
			}
			r.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public static File printRubrica(List<Contatto> rubrica, File file) {
		Collections.sort(rubrica, new SortByName());
		for(int i=0; i<rubrica.size(); i++) {
			try {
	        	BufferedWriter output = new BufferedWriter(new FileWriter(file));
	            output.write(stampaListaRubrica(rubrica));
	            output.close();
	        } catch ( IOException e ) {
	            e.printStackTrace();
	        }
		}
		return file;
	}
	
	public static String stampaListaRubrica(List<Contatto> rubrica) {
		String result="";
		for(int i=0; i<rubrica.size(); i++) {
			if(!rubrica.get(i).getNome().equalsIgnoreCase("eliminato") || !rubrica.get(i).getNumero().equalsIgnoreCase("eliminato")) {
				result+="[cod:"+i+"] "+rubrica.get(i).getNome()+" "+rubrica.get(i).getNumero()+"\n";
			}
			else {
				rubrica.remove(i);
			}
		}
		return result;
	}
	
	public static int aggiungiContatto(List<Contatto> rubrica, Contatto contatto) {
		char[] numeri = {'0', '1', '2', '3', '4', '5', '6', '7' ,'8', '9'};
		int numeriValidi = 0;
		for(int i=0; i<contatto.getNumero().length(); i++) {
			for(int j=0; j<numeri.length; j++) {
				if(contatto.getNumero().charAt(i) == numeri[j]){
					numeriValidi++;
				}
			}
		}
		if(numeriValidi != 10) {
			//il numero non è valido
			System.out.println("Il numero che hai inserito non è valido!");
			return -1;
		}
		rubrica.add(new Contatto(contatto.getNome().toUpperCase(), contatto.getNumero()));
		System.out.println("CONTATTO AGGIUNTO!");
		return numeriValidi;
	}
	
	public static int eliminaContattoDaNome(List<Contatto> rubrica, String nome) {
		input = new Scanner(System.in);
		List<Contatto> contattiPossibili = new ArrayList<Contatto>();
		for(int i=0; i<rubrica.size(); i++) {
			if(nome.equalsIgnoreCase(rubrica.get(i).getNome())) {
				//il contatto potrebbe essere questo
				contattiPossibili.add(new Contatto(rubrica.get(i).getNome(), rubrica.get(i).getNumero()));
			}
		}
		if(contattiPossibili.size() == 0) {
			System.out.println("NESSUN CONTATTO TROVATO!");
			return -1;
		}
		System.out.println("Ho trovato "+contattiPossibili.size()+" possibili contatti da eliminare");
		for(int i=0; i<contattiPossibili.size(); i++) {
			System.out.println("["+i+"] - NOME: "+contattiPossibili.get(i).getNome()+", NUMERO: "+contattiPossibili.get(i).getNumero());
		}
		System.out.print("Inserisci il codice del contatto da eliminare: ");
		int contattoDaEliminare = input.nextInt();
		for(int i=0; i<rubrica.size(); i++) {
			if(rubrica.get(i).isUguale(contattiPossibili.get(contattoDaEliminare))) {
				//questo va eliminato
				eliminaContatto(rubrica, rubrica.get(i));
				return 1;
			}
		}
		return -1;
	}
	
	public static int eliminaContatto(List<Contatto> rubrica, Contatto contatto) {
		for(int i=0; i<rubrica.size(); i++) {
			if(rubrica.get(i).getNome().equalsIgnoreCase(contatto.getNome()) && rubrica.get(i).getNumero().equalsIgnoreCase(contatto.getNumero())) {
				//elimina contatto
				System.out.println("CONTATTO: "+rubrica.get(i).getNome()+" ELIMINATO!");
				rubrica.get(i).setNome("eliminato");
				rubrica.get(i).setNumero("eliminato");
				return 1;
			}
		}
		System.out.println("NESSUN CONTATTO TROVATO!");
		return -1;
	}

	public static int aggiornaContatto(List<Contatto> rubrica, int codice) {
		input = new Scanner(System.in);
		if(codice<0 || codice>rubrica.size()) {
			System.out.println("CODICE NON VALIDO!");
			return -1;
		}
		System.out.print("RINOMINA IL CONTATTO ["+rubrica.get(codice).getNome()+"] CON: ");
		String nuovoNome = input.next();
		System.out.print("RICOMPONI IL NUMERO DI ["+nuovoNome+"]: ");
		String nuovoNumero = input.next();
		eliminaContatto(rubrica, rubrica.get(codice));
		Contatto contattoNuovo = new Contatto(nuovoNome, nuovoNumero);
		aggiungiContatto(rubrica, contattoNuovo);
		return 1;
	}
	
	public static void trovaContattoDaNome(List<Contatto> rubrica, String nome) {
		List<Contatto> contattiPossibili = new ArrayList<Contatto>();
		for(int i=0; i<rubrica.size(); i++) {
			if(nome.equalsIgnoreCase(rubrica.get(i).getNome())) {
				//il contatto potrebbe essere questo
				contattiPossibili.add(new Contatto(rubrica.get(i).getNome(), rubrica.get(i).getNumero()));
			}
		}
		System.out.println("Ci sono "+contattiPossibili.size()+" contatti nominati "+nome);
		for(int i=0; i<contattiPossibili.size(); i++) {
			System.out.println("["+i+"] - NOME: "+contattiPossibili.get(i).getNome()+", NUMERO: "+contattiPossibili.get(i).getNumero());
		}
	}
	
	public static void trovaContattoDaNumero(List<Contatto> rubrica, String numero) {
		List<Contatto> contattiPossibili = new ArrayList<Contatto>();
		for(int i=0; i<rubrica.size(); i++) {
			if(numero.equalsIgnoreCase(rubrica.get(i).getNumero())) {
				//il contatto potrebbe essere questo
				contattiPossibili.add(new Contatto(rubrica.get(i).getNome(), rubrica.get(i).getNumero()));
			}
		}
		System.out.println("Ci sono "+contattiPossibili.size()+" con il numero "+numero);
		for(int i=0; i<contattiPossibili.size(); i++) {
			System.out.println("["+i+"] - NOME: "+contattiPossibili.get(i).getNome()+", NUMERO: "+contattiPossibili.get(i).getNumero());
		}
	}
}

