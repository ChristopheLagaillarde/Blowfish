/*/////////////////En t?te////////////////////////////
 * Programme : APIFileCipher
 *
 * Description : Ce programme permet de chiffrer et
 *               d?chiffrer un fichier texte au choix.
 *          
 * Auteur : Christophe LAGAILLARDE
 *
 * Date : 27/11/2021
 *        
 *///////////////////////////////////////////////////

// D?claration des bibliotheques de fonctions...
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;

//////////////////D?but//////////////////////////////
public class ApiFileCipher {
	
	/**
	 * Chiffre un fichier texte avec Blowfish
	 * @param nomDuFichierAchiffrer: un String du nom du fichier
	 * @param adresseFichierChiffer ? chiffrer : Un String
	 * @param clefSymetrique
	 * @return void
	 */

	public static void chiffrerFichierTexte(String nomFichierAChiffrer, String adresseFichierChiffre, Key clefSymetrique) {
		String adresseFichierAChiffrer = "C://Users/Lagaillarde/eclipse-workspace-Java/Blowfish/src/" + nomFichierAChiffrer + ".txt"; 
		byte[] byteDuFichierAChiffrer = null;
		byte[] byteDuFichierChiffre = null;

		
		File fichierAChiffrer = new File(adresseFichierAChiffrer);
		try {
			FileInputStream lireFichierAChiffrer = new FileInputStream(fichierAChiffrer);
			DataInputStream ouvreFluxLireDataFichierAChiffrer=  new DataInputStream(lireFichierAChiffrer); 

			byteDuFichierAChiffrer = ouvreFluxLireDataFichierAChiffrer.readAllBytes();

			ouvreFluxLireDataFichierAChiffrer.close();
			lireFichierAChiffrer.close();
			
			
		} catch (FileNotFoundException fichierInexistant) {
			System.err.println("Le fichier n'existe pas");
			
		} catch (IOException echecDuChiffrement) {
			System.err.println("Le chiffrement a ?chou?");
		}

		
		try {
			byteDuFichierChiffre = ApiBlowfish.encryptInByte(byteDuFichierAChiffrer, clefSymetrique);
		} catch (Exception echecApiBlowfish) {
			System.err.println("ApiBlowfish : L'encryptage ne fonctionne pas");
		}	
		
		
		try {
			FileOutputStream ouvreFluxFichierChiffre = new FileOutputStream(adresseFichierChiffre);
			DataOutputStream ouvreFluxEcrireDataFichierChiffre = new DataOutputStream(ouvreFluxFichierChiffre);

			ouvreFluxEcrireDataFichierChiffre.write(byteDuFichierChiffre);

			ouvreFluxEcrireDataFichierChiffre.close();
			ouvreFluxFichierChiffre.close();
			
		} catch (FileNotFoundException fichierInexistant) {
			System.err.println("Le fichier n'existe pas");
		}
		catch (IOException echecDuDechiffrement) {
			System.err.println("Le dechiffrement a ?chou?");
		}
	
	}
	
	/**
	 * D?chiffre un fichier texte crypt? avec Blowfish
	 * @param addresseFichierADechiffrer: un String de l'adresse du fichier ? d?chiffrer
	 * @param clefSymetrique
	 * @return texteFichierDecrypteEnString : un String du texte d?chiffr?
	 */
	
	public static String dechiffrerFichierTexte(String addresseFichierADechiffrer, Key clefSymetrique) {
		byte[] texteFichierDecrypteEnByte = null;
		try {
			File fichierCrypte = new File(addresseFichierADechiffrer);
			FileInputStream lireFichierCrypte = new FileInputStream(fichierCrypte);
			DataInputStream ouvreFluxLireDataFichierCrypte=  new DataInputStream(lireFichierCrypte); 

			texteFichierDecrypteEnByte = ouvreFluxLireDataFichierCrypte.readAllBytes();
		
			ouvreFluxLireDataFichierCrypte.close();
			lireFichierCrypte.close();
			
			
			try {
				texteFichierDecrypteEnByte = ApiBlowfish.decryptInByte(texteFichierDecrypteEnByte, clefSymetrique);
			} catch (Exception erreurDecryptage) {
				System.err.println("Echec du d?cryptage");
			}
			
			
		}catch (FileNotFoundException fichierInexistant) {
			System.err.println("Le fichier crypt? n'existe pas");
			
		}catch (IOException echecDuChiffrement) {
			System.err.println("Le d?chiffrement a ?chou?");
		}
				
		return new String(texteFichierDecrypteEnByte);
	}

}
//////////////////Fin//////////////////////////////
