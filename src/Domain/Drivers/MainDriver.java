/** @file MainDriver.java
 * @brief Aquest és el driver principal. Utilitzant-lo es poden provar tots els algorismes i totes les funcions.
 *
 * Els imports que utilitza són:
 *     - import java.io.*
 *     - import java.util.Scanner
 *     - import Domain.*
 *     - import Persistence.*
 *
 * @author Carlos Gascón Dominguez
 */

package Domain.Drivers;

import java.io.*;
import java.util.Scanner;
import Domain.*;
import Persistence.*;

/*
 * Main Driver
 */

/** @class MainDriver
 * @brief Aquest és el driver principal. Utilitzant-lo es poden provar tots els algorismes i totes les funcions.
 *
 * @author Carlos Gascón Dominguez
 */
public class MainDriver {
    
    private static final CtrlPersistence ctrlPersistence = CtrlPersistence.getInstance();
    
    private static final CtrlDomain ctrlDomain = CtrlDomain.getInstance();
    
    /** @brief Ensenya les opcions que té l'usuari.
     * \pre <em>Cert</em>
     * \post Ha escrit per pantalla les opcions que té l'usuari.
     */
    private static void showMenu() {
        System.out.println("Selecciona acció: ");
        System.out.println("1) Compressió");
        System.out.println("2) Descompressió");
        System.out.println("3) Sortir");
    }
    
    /** @brief Ensenya els modes de compressió que pot triar l'usuari.
     * 
     * \pre <em>Cert</em>
     * \post Ha escrit per pantalla els modes de compressió que pot triar l'usuari.
     */
    private static void showMenuAutoMan() {
        System.out.println("Selecciona mode: ");
        System.out.println("1) Automàtic");
        System.out.println("2) Manual");
    }
    
    /** @brief Ensenya els algorismes de compressió que pot triar l'usuari.
     * 
     * \pre <em>Cert</em>
     * \post Ha escrit per pantalla els algorismes de compressió que pot triar l'usuari.
     */
    private static void showMenuSelAlg() {
        System.out.println("Selecciona algorisme: ");
        System.out.println("1) LZ78");
        System.out.println("2) LZW");
        System.out.println("3) LZSS");
        System.out.println("4) JPEG");
    }
    
    /** @brief MainDriver
     *
     * Llegeix de l'entrada estàndard i escull l'opció que es vol provar.
     * @param args
     * @throws java.io.IOException
     *
     * \pre <em>Cert.</em>
     * \post <em>Cert.</em>
     */
     public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        
        String in_path;
        String out_path;
        String algorithm;
        
        System.out.println("o----------------------o");
        System.out.println("|     EL COMPRESSOR    |");
        System.out.println("o----------------------o");
        
        showMenu();
        
        String choosen_opt = in.nextLine();
        String choosen_mode, choosen_algorithm;
        boolean end = false;
        
        while(!end) {
            switch (choosen_opt) {
                case "1":
                    System.out.println("Has seleccionat: Compressió");
                    showMenuAutoMan();
                    choosen_mode = in.nextLine();
                    switch (choosen_mode) {
                        case "1":
                            System.out.println("Has seleccionat el mode Automàtic");
                            System.out.println("Introdueix el path al fitxer que vols comprimir: ");
                            in_path = in.nextLine();
                            System.out.println("Introdueix el path on vols que el teu fitxer comprimit es guardi: ");
                            out_path = in.nextLine();
                            algorithm = ctrlDomain.getType(in_path);
                            if("DIR".equals(algorithm)) {
                                System.out.println("Comprimint...");
                                ctrlDomain.compressFolder(in_path, out_path);
                                System.out.println("Compressió finalitzada");
                            }       
                            else {
                                System.out.println("Comprimint...");
                                ctrlDomain.compressFile(in_path, out_path);
                                System.out.println("Compressió finalitzada");
                            }
                            break;
                        case "2":
                            showMenuSelAlg();
                            choosen_algorithm = in.nextLine();
                            System.out.println("Introdueix el path al fitxer que vols comprimir: ");
                            in_path = in.nextLine();
                            System.out.println("Introdueix el path on vols que el teu fitxer comprimit es guardi: ");
                            out_path = in.nextLine();
                            switch (choosen_algorithm){
                                case "1":
                                    algorithm = "LZ78";
                                    ctrlDomain.compressFile(in_path, out_path, algorithm);
                                    break;
                                case "2":
                                    algorithm = "LZW";
                                    ctrlDomain.compressFile(in_path, out_path, algorithm);
                                    break;
                                case "3":
                                    algorithm = "LZSS";
                                    ctrlDomain.compressFile(in_path, out_path, algorithm);
                                    break;
                                case "4":
                                    algorithm = "JPEG";
                                    ctrlDomain.compressFile(in_path, out_path, algorithm);
                                    break;
                            }
                            break;
                    }
                    showMenu();
                    choosen_opt = in.nextLine();
                    break;
                
                case "2":
                    System.out.println("Has seleccionat: Descompressió");
                    System.out.println("Introdueix el path al fitxer que vols descomprimir: ");
                    
                    in_path = in.nextLine();
                    
                    System.out.println("Introdueix el path on vols que el teu fitxer descomprimit es guardi: ");
                    
                    out_path = in.nextLine();
                    
                    algorithm = ctrlDomain.getType(in_path);
                    if("DIR".equals(algorithm)) {
                        System.out.println("Descomprimint...");
                        ctrlDomain.decompressFolder(in_path, out_path);
                        System.out.println("Descompressió finalitzada");
                    }
                    else {
                        System.out.println("Descomprimint...");
                        ctrlDomain.decompressFile(in_path, out_path);
                        System.out.println("Descompressió finalitzada");
                    }
                    
                    showMenu();
                    choosen_opt = in.nextLine();
                    break;
                
                case "3":
                    System.out.println("Sortida");
                    end = true;
                    break;
                    
                default: 
                    System.out.println("Acció invàlida");
                    showMenu();
                    choosen_opt = in.nextLine();
            }
        }
    }
}

