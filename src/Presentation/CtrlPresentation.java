/** @file CtrlPresentation.java
 * @brief Aquesta classe és el controlador de presentació.
 *
 * Els imports que utilitza són:
 *     - import Domain.CtrlDomain
 *     - import java.awt.image.BufferedImage
 *     - import java.io.*
 *     - import java.util.ArrayList
 *
 * @author Albert Pita Argemí
 */
package Presentation;

import Domain.CtrlDomain;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

/*
 * Controlador de Presentació
 */

/** @class CtrlPresentation
 * @brief Aquest és el controlador de presentació.
 *
 * @author Albert Pita Argemí
 */
public class CtrlPresentation {
    
    /** @brief Instància del controlador de domini, inicialitzada al crear el controlador de presentació.*/
    private final CtrlDomain ctrlDomain;
    /** @brief View del menú principal.*/
    private final viewMainMenu viewMM;
    /** @brief View de l'ajuda del menú principal.*/
    private final viewMenuHelp viewMH;
    /** @brief View de les estadístiques.*/
    private final viewStatistics viewS;
    /** @brief View de les accions possibles.*/
    private final viewActions viewA;
    /** @brief View de les opcions de compressió.*/
    private final viewCompressionOptions viewCO;
    /** @brief View de la tria d'algorismes.*/
    private final viewAlgorithms viewAlg;
    /** @brief View del selector de fitxers de descompressió.*/
    private final viewFileSelectorDecompression viewFSD;
    /** @brief View del selector de fitxers de compressió automàtic.*/
    private final viewFileSelectorCompressionAutomatic viewFSCA;
    /** @brief View del selector de fitxers de compressió manuals.*/
    private final viewFileSelectorCompressionManual viewFSCM;
    /** @brief View del selector d'opcions del JPEG.*/
    private final viewJPEG viewJPEG;
    /** @brief View del selector d'opcions del LZSS.*/
    private final viewLZSS viewLZSS;
    
    /** @brief Atribut que representa el tipus d'algorisme utilitzat en una certa compressió o descompressió.*/
    private String algorithmType = "";
    
    /** @brief Constructora per defecte.
     * 
     * Inicialitza totes les views del controlador al igual que la instància del controlador de domini.
     * 
     * \pre <em>Cert.</em>
     * \post S'ha creat una instància del controlador de presentació.
     */
    public CtrlPresentation() {
        ctrlDomain = CtrlDomain.getInstance();
        
        viewMM = new viewMainMenu(this);
        viewMH = new viewMenuHelp(this);
        viewS = new viewStatistics(this);
        viewA = new viewActions(this);
        viewCO = new viewCompressionOptions(this);
        viewAlg = new viewAlgorithms(this);
        viewFSD = new viewFileSelectorDecompression(this);
        viewFSCA = new viewFileSelectorCompressionAutomatic(this);
        viewFSCM = new viewFileSelectorCompressionManual(this);
        viewJPEG = new viewJPEG(this);
        viewLZSS = new viewLZSS(this);
    }
    
    /** @brief Inicialitza la presentació*/
    public void initilizePresentation() {
        viewMM.setVisible(true);
        viewMM.makeVisible();
    }
    
    /**
     * Funcions de sincronització
     */
    
    /** @brief Transició entre la view del menú principal i la view de les accions possibles.*/
    public void syncViewMM_to_viewA() {
        viewA.setLocationRelativeTo(viewMM);
        
        viewMM.setVisible(false);
        viewMM.makeInvisible();
        
        viewA.setVisible(true);
        viewA.makeVisible();
    }
    
    /** @brief Transició entre la view del menú principal i la view de les estadístiques.
     * 
     * @throws IOException 
     */
    public void syncViewMM_to_viewS() throws IOException {
        viewS.setLocationRelativeTo(viewMM);
        
        viewMM.setVisible(false);
        viewMM.makeInvisible();
        
        viewS.setVisible(true);
        viewS.makeVisible();
    }
    
    /** @brief Transició entre la view del menú principal i la view de l'ajuda del menú.*/
    public void syncViewMM_to_viewMH() {
        viewMH.setLocationRelativeTo(viewMM);
        
        viewMM.setVisible(false);
        viewMM.makeInvisible();
        
        viewMH.setVisible(true);
        viewMH.makeVisible();
    }
    
    /** @brief Transició entre la view de les accions possibles i la view del menú principal.*/
    public void syncViewA_to_viewMM() {
        viewMM.setLocationRelativeTo(viewA);
        
        viewA.setVisible(false);
        viewA.makeInvisible();
        
        viewMM.setVisible(true);
        viewMM.makeVisible();
    }
    
    /** @brief Transició entre la view de les estadístiques i la view del menú principal.*/
    public void syncViewS_to_viewMM() {
        viewMM.setLocationRelativeTo(viewS);
        
        viewS.setVisible(false);
        viewS.makeInvisible();
        
        viewMM.setVisible(true);
        viewMM.makeVisible();
    }
    
    /** @brief Transició entre la view de l'ajuda del menú i la view del menú principal.*/
    public void syncViewMH_to_viewMM() {
        viewMM.setLocationRelativeTo(viewMH);
        
        viewMH.setVisible(false);
        viewMH.makeInvisible();
        
        viewMM.setVisible(true);
        viewMM.makeVisible();
    }
    
    /** @brief Transició entre la view de les accions possibles i la view de les opcions de compressió.*/
    public void syncViewA_to_viewCO() {
        viewCO.setLocationRelativeTo(viewA);
        
        viewA.setVisible(false);
        viewA.makeInvisible();
        
        viewCO.setVisible(true);
        viewCO.makeVisible();
    }
    
    /** @brief Transició entre la view de les opcions de compressió i la view de les accions possibles.*/
    public void syncViewCO_to_viewA() {
        viewA.setLocationRelativeTo(viewCO);
        
        viewCO.setVisible(false);
        viewCO.makeInvisible();
        
        viewA.setVisible(true);
        viewA.makeVisible();
    }
    
    /** @brief Transició entre la view de les accions possibles i la view del selector de fitxers de descompressió.*/
    public void syncViewA_to_viewFSD() {
        viewFSD.setLocationRelativeTo(viewA);
        
        viewA.setVisible(false);
        viewA.makeInvisible();
        
        viewFSD.setVisible(true);
        viewFSD.makeVisible();
    }
    
    /** @brief Transició entre la view del selector de fitxers de descompressió i la view de les accions possibles.*/
    public void syncViewFSD_to_viewA() {
        viewA.setLocationRelativeTo(viewFSD);
        
        viewFSD.setVisible(false);
        viewFSD.makeInvisible();
        
        viewA.setVisible(true);
        viewA.makeVisible();
    }
    
    /** @brief Transició entre la view de les opcions de compressió i la view de la tria d'algorismes.*/
    public void syncViewCO_to_viewAlg() {
        viewAlg.setLocationRelativeTo(viewCO);
        
        viewCO.setVisible(false);
        viewCO.makeInvisible();
        
        viewAlg.setVisible(true);
        viewAlg.makeVisible();
    }
    
    /** @brief Transició entre la view de la tria d'algorismes i la view de les opcions de compressió.*/
    public void syncViewAlg_to_viewCO() {
        viewCO.setLocationRelativeTo(viewAlg);
        
        viewAlg.setVisible(false);
        viewAlg.makeInvisible();
        
        viewCO.setVisible(true);
        viewCO.makeVisible();
    }
    
    /** @brief Transició entre la view de les opcions de compressió i la view del selector de fitxers de compressió automàtic.*/
    public void syncViewCO_to_viewFSCA() {
        viewFSCA.setLocationRelativeTo(viewCO);
        
        viewCO.setVisible(false);
        viewCO.makeInvisible();
        
        viewFSCA.setVisible(true);
        viewFSCA.makeVisible();
    }
    
    /** @brief Transició entre la view del selector de fitxers de compressió automàtic i la view de les opcions de compressió.*/
    public void syncViewFSCA_to_viewCO() {
        viewCO.setLocationRelativeTo(viewFSCA);
        
        viewFSCA.setVisible(false);
        viewFSCA.makeInvisible();
        
        viewCO.setVisible(true);
        viewCO.makeVisible();
    }
    
    /** @brief Transició entre la view de la tria d'algorismes i la view del selector de fitxers de compressió manuals.*/
    public void syncViewAlg_to_viewFSCM() {
        viewFSCM.setLocationRelativeTo(viewAlg);
        
        viewAlg.setVisible(false);
        viewAlg.makeInvisible();
        
        viewFSCM.setVisible(true);
        viewFSCM.makeVisible();
    }
    
    /** @brief Transició entre la view del selector de fitxers de compressió manuals i la view de la tria d'algorismes.*/
    public void syncViewFSCM_to_viewAlg() {
        viewAlg.setLocationRelativeTo(viewFSCM);
        
        viewFSCM.setVisible(false);
        viewFSCM.makeInvisible();
        
        viewAlg.setVisible(true);
        viewAlg.makeVisible();
    }
    
    /** @brief Transició entre la view del selector de fitxers de compressió automàtic i la view de les accions possibles.*/
    public void syncViewFSCA_to_viewA() {
        viewA.setLocationRelativeTo(viewFSCA);
        
        viewFSCA.setVisible(false);
        viewFSCA.makeInvisible();
        
        viewA.setVisible(true);
        viewA.makeVisible();
    }
    
    /** @brief Transició entre la view del selector de fitxers de compressió manuals i la view de les accions possibles.*/
    public void syncViewFSCM_to_viewA() {
        viewA.setLocationRelativeTo(viewFSCM);
        
        viewFSCM.setVisible(false);
        viewFSCM.makeInvisible();
        
        viewA.setVisible(true);
        viewA.makeVisible();
    }
    
    /** @brief Transició entre la view de la tria d'algorismes i la view del selector d'opcions del JPEG.*/
    public void syncViewAlg_to_viewJPEG() {
        viewJPEG.setLocationRelativeTo(viewAlg);
        
        viewAlg.setVisible(false);
        viewAlg.makeInvisible();
        
        viewJPEG.setVisible(true);
        viewJPEG.makeVisible();
    }
    
    /** @brief Transició entre la view del selector d'opcions del JPEG i la view de la tria d'algorismes.*/
    public void syncViewJPEG_to_viewAlg() {
        viewAlg.setLocationRelativeTo(viewJPEG);
        
        viewJPEG.setVisible(false);
        viewJPEG.makeInvisible();
        
        viewAlg.setVisible(true);
        viewAlg.makeVisible();
    }
    
    /** @brief Transició entre la view del selector d'opcions del JPEG i la view de les accions possibles.*/
    public void syncViewJPEG_to_viewA() {
        viewA.setLocationRelativeTo(viewJPEG);
        
        viewJPEG.setVisible(false);
        viewJPEG.makeInvisible();
        
        viewA.setVisible(true);
        viewA.makeVisible();
    }
    
    /** @brief Transició entre la view de la tria d'algorismes i la view del selector d'opcions del LZSS.*/
    public void syncViewAlg_to_viewLZSS() {
        viewLZSS.setLocationRelativeTo(viewAlg);
        
        viewAlg.setVisible(false);
        viewAlg.makeInvisible();
        
        viewLZSS.setVisible(true);
        viewLZSS.makeVisible();
    }
    
    /** @brief Transició entre la view del selector d'opcions del LZSS i la view de la tria d'algorismes.*/
    public void syncViewLZSS_to_viewAlg() {
        viewAlg.setLocationRelativeTo(viewLZSS);
        
        viewLZSS.setVisible(false);
        viewLZSS.makeInvisible();
        
        viewAlg.setVisible(true);
        viewAlg.makeVisible();
    }
    
    /** @brief Transició entre la view del selector d'opcions del LZSS i la view de les accions possibles.*/
    public void syncViewLZSS_to_viewA() {
        viewA.setLocationRelativeTo(viewLZSS);
        
        viewLZSS.setVisible(false);
        viewLZSS.makeInvisible();
        
        viewA.setVisible(true);
        viewA.makeVisible();
    }
    
    /** @brief Tanca el programa.*/
    public void exit() {
        System.exit(0);
    }
    
    /*
     * Setters
     */
    
    /** @brief Setter de l'atribut <em>algorithmType</em>.
     * 
     * @param algorithm Valor que es vol posar al atribut <em>algorithmType</em>.
     * 
     * \pre <em>Cert.</em>
     * \post L'atribut privat <em>algorithmType</em> té valor <em>algorithm</em>.
     */
    public void setAlgorithmType(String algorithm) {
        algorithmType = algorithm;
    }
    
    /*
     * Getters
     */
    
    /** @brief Getter de l'atribut <em>algorithm</em>.
     * 
     * @return Retorna el valor de <em>algorithm</em>.
     * \pre <em>Cert.</em>
     * \post S'ha retornat el valor actual de l'atribut privat <em>algorithmType</em>.
     */
    public String getAlgorithmType() {
        return algorithmType;
    }
    
    /*
     * Operacions que utilitza el controlador de domini.
     */
    
    /** @brief Crida al controlador de domini perque comprimeixi una carpeta.
     * 
     * @param in_path Path de la carpeta que es vol comprimir.
     * @param out_path Path del fitxer on es guarden totes les compressions.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de domini perque realitzi la compressió de la carpeta.
     */
    public void compressFolder(String in_path, String out_path) throws IOException {
        ctrlDomain.compressFolder(in_path, out_path);
    }
    
    /** @brief Crida al controlador de domini perque comprimeixi un fitxer.
     * 
     * @param in_path Path de la fitxer que es vol comprimir.
     * @param out_path Path del fitxer on es vol guardar la compressió.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de domini perque realitzi la compressió del fitxer.
     */
    public void compressFile(String in_path, String out_path) throws IOException {
        ctrlDomain.compressFile(in_path, out_path);
    }
    
    /** @brief Crida al controlador de domini perque comprimeixi un fitxer manualment.
     * 
     * @param in_path Path de la fitxer que es vol comprimir.
     * @param out_path Path del fitxer on es vol guardar la compressió.
     * @param algorithmType Tipus d'algorisme que es vol utilitzar.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de domini perque realitzi la compressió del fitxer manualment.
     */
    public void manualCompressFile(String in_path, String out_path, String algorithmType) throws IOException {
        ctrlDomain.compressFile(in_path, out_path, algorithmType);
    }
    
    /** @brief Crida al controlador de domini perque comprimeixi un fitxer manualment amb certes opcions.
     * 
     * @param in_path Path de la fitxer que es vol comprimir.
     * @param out_path Path del fitxer on es vol guardar la compressió.
     * @param algorithmType Tipus d'algorisme que es vol utilitzar.
     * @param Options Opcions de compressió volgudes.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de domini perque realitzi la compressió del fitxer manualment amb certes opcions.
     */
    public void manualCompressFileOptions(String in_path, String out_path, String algorithmType, String Options) throws IOException {
        ctrlDomain.compressFile(in_path, out_path, algorithmType, Options);
    }
    
    /** @brief Crida al controlador de domini perque descomprimeixi una carpeta.
     * 
     * @param in_path Path de la carpeta que es vol descomprimir.
     * @param out_path Path del fitxer on es vol crear la nova carpeta amb les descompressions dins.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de domini perque realitzi la descompressió de la carpeta.
     */
    public void decompressFolder(String in_path, String out_path) throws IOException {
        ctrlDomain.decompressFolder(in_path, out_path);
    }
    
    /** @brief Crida al controlador de domini perque descomprimeixi un fitxer.
     * 
     * @param in_path Path de la fitxer que es vol descomprimir.
     * @param out_path Path del fitxer on es vol guardar la descompressió.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de domini perque realitzi la descompressió del fitxer.
     */
    public void decompressFile(String in_path, String out_path) throws IOException {
        ctrlDomain.decompressFile(in_path, out_path);
    }
    
    /** @brief Crida al controlador de domini perque obtingui tot el contingut de la base de dades d'estadístiques.
     * 
     * @return Retorna un ArrayList de String amb el contingut de la base de dades estadístiques passat pel controlador de domini.
     * @throws IOException 
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de domini perque obtingui tot el contingut de la base de dades d'estadístiques.
     */
    public ArrayList<String> getTableContent() throws IOException {
        return ctrlDomain.getTableContent();
    }
    
    /** @brief Crida al controlador de domini perque esborri tot el contingut de la base de dades d'estadístiques.
     * 
     * @throws IOException 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de domini perque esborri tot el contingut de la base de dades d'estadístiques.
     */
    public void deleteTableContent() throws IOException {
        ctrlDomain.deleteTableContent();
    }
    
    /** @brief Crida al controlador de domini per saber el valor de l'atribut totalTime.
     * 
     * @return Retorna una String amb el valor de <em>totalTime</em> passat pel controlador de Domini.
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de domini per saber el valor de l'atribut totalTime.
     */
    public String getTotalTime() {
        return ctrlDomain.getTotalTime();
    }
    
    /** @brief Crida al controlador de domini per saber el valor de l'atribut compressRatio.
     * 
     * @return Retorna una String amb el valor de <em>compressRatio</em> passat pel controlador de Domini.
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de domini per saber el valor de l'atribut compressRatio.
     */
    public String getCompressRatio() {
        return ctrlDomain.getCompressRatio();
    }
    
    /** @brief Crida al controlador de domini per saber el valor de l'atribut path.
     * 
     * @return Retorna una String amb el valor de <em>path</em> passat pel controlador de Domini.
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de domini per saber el valor de l'atribut path.
     */
    public String getPath() {
        return ctrlDomain.getPath();
    }
    
    /** @brief Crida al controlador de domini per saber el contingut d'un cert fitxer amb path <em>path</em>.
     * 
     * @param path Path del fitxer del qual volem saber el contingut.
     * @return Retorna una String amb contingut del fitxer passat pel controlador de Domini.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de domini per el contingut d'un cert fitxer amb path <em>path</em>.
     */
    public String getText(String path) throws IOException {
        return ctrlDomain.getText(path);
    }
    
    /** @brief Crida al controlador de domini per saber la representació en una BufferedImage de la imatge amb path <em>path</em>.
     * 
     * @param path Path del fitxer del qual volem saber el contingut.
     * @return Retorna una BufferedImage que representa el contingut del imatge, passat pel controlador de Domini.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de domini per saber la representació en una BufferedImage de la imatge amb path <em>path</em>.
     */
    public BufferedImage getImage(String path) throws IOException {
        return ctrlDomain.getImage(path);
    }
    
    /** @brief Crida al controlador de domini perque esborri el fitxer amb path <em>path</em>. 
     * 
     * @param path Path del fitxer que volem esborrar.
     * 
     * \pre <em>Cert</em>.
     * \post S'ha cridat al controlador de domini perque esborri el fitxer amb path <em>path</em>.
     */
    public void deleteFile(String path) {
        ctrlDomain.deleteFile(path);
    }
    
    /** @brief Crida al controlador de domini per saber quin tipus de fitxer és el arxiu amb path <em>in_path</em>. 
     * 
     * @param in_path Path de l'arxiu del qual volem saber el tipus.
     * @return Retorna el tipus de fitxer de l'arxiu passat pel controlador de domini.
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de domini per saber quin tipus de fitxer és el arxiu amb path <em>in_path</em>.
     */
    public String getType(String in_path) {
        return ctrlDomain.getType(in_path);
    }
}
