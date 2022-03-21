/*/////////////////En t�te////////////////////////////
 * Programme : blowfish
 *
 * Description : Ce programme utilise les API FileCipher
 *               et Blowfish.
 * 
 * Auteur : Christophe LAGAILLARDE
 *
 * Date : 27/11/2021
 *        
 *///////////////////////////////////////////////////

// D�claration des bibliotheques de fonctions...
import java.security.Key;
import java.util.InputMismatchException;
import java.util.Scanner;

//////////////////Le Programme principal/////////////

//////////////////D�but//////////////////////////////
public class Blowfish {

	public static void main(String[] args) {

		String messageAChiffrer = null;
		String messageChiffre = null;
		String messageDechiffre = null;
		String nomFichierAChiffre = null;
		String messageFichierDechiffre = null;
		String adresseFichierChiffre = "C://Users/Lagaillarde/eclipse-workspace-Java/Blowfish/src/texteChiffre.cryp";
		Key clefSymetrique = null;
		int choix = 0;

		// Choix 
		try {
			System.out.print("Saisir 1 pour chiffrer et puis d�chiffrer une phrase\n"
					+ "Ou 2 pour chiffrer et puis d�chiffrer un fichier texte ");
			@SuppressWarnings("resource")
			Scanner saisieUtilisateur= new Scanner(System.in); 
			choix = saisieUtilisateur.nextInt(); 
		}catch(InputMismatchException | IllegalStateException e) {
			// Exception g�r� dans le else � la fin du programme
		}


		if(choix == 1) {
			System.out.print("Saisir le message � crypter :");
			Scanner saisiChoix1 = new Scanner(System.in); 
			messageAChiffrer = saisiChoix1.nextLine(); 
			saisiChoix1.close(); 

			try {
				clefSymetrique = ApiBlowfish.generateKey();
				
				messageChiffre = ApiBlowfish.encryptInString(messageAChiffrer,clefSymetrique);
				System.out.printf("\n\nLe message chiffr� avec Blowfish est:\n" + messageChiffre);
				
				messageDechiffre = ApiBlowfish.decryptInString(messageChiffre, clefSymetrique);
				System.out.printf("\n\nLe message dechiffr� avec Blowfish est:\n" + messageDechiffre);

			} catch (Exception apiNeFonctionnePas) {
				System.err.println("L'Api Blowfish ne fonctionne pas");
			} 
		}


		if(choix ==2) {
			System.out.print("Saisir le nom du fichier � chiffrer:");
			Scanner saisiChoix2 = new Scanner(System.in); 
			nomFichierAChiffre = saisiChoix2.nextLine(); 
			saisiChoix2.close(); 

			try {
				clefSymetrique = ApiBlowfish.generateKey();
				
				ApiFileCipher.chiffrerFichierTexte(nomFichierAChiffre, adresseFichierChiffre, clefSymetrique);
				
				messageFichierDechiffre = ApiFileCipher.dechiffrerFichierTexte(adresseFichierChiffre, clefSymetrique);
				System.out.println("Le texte d�crypt� du fichier crypt� est :\n\n"+ messageFichierDechiffre);
			}catch(Exception apiNeFonctionnePas) {
				System.err.println("Erreur survenu au niveau de l'api Blowfish");
			}
		}
		else if(choix != 1 && choix != 2){
			System.err.println("Choix non valide\nFin du programme");
		}
	}

}
//////////////////Fin//////////////////////////////
