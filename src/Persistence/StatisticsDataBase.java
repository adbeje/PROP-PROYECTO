/** @file StatisticsDataBase.java
 *  @brief Aquest el gestor de BD dels estadístics.
 *
 * Els imports que utilitza són:
 *       - Presentation.viewStatistics
 *       - import java.io.*
 *       - import java.util.*
 *       - import java.util.logging.*
 *
 *  @author Adrià Ventura i Herce
 */

package Persistence;

import Presentation.viewStatistics;
import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * Base de dades de les estadístiques
 */

/** @class StatisticsDataBase
 * 
 *  @brief Aquest el gestor de BD dels estadístics.
 *
 *  @author Adrià Ventura i Herce
 */
public class StatisticsDataBase {
    /** @brief Fitxer destí on aniran les estadístiques de compressió i descompressió.*/
    private static File statisticsDataBase;
    
    /** @brief Constructora per defecte.
     * 
     * @throws IOException 
     * 
     * \pre <em>Cert.</em>
     * \post Crea una nova instància de base de dades d'estadístiques.
     */
    public void createDataBase() throws IOException {
            
        statisticsDataBase = new File("HistorialEstadístic.txt");
        if (!statisticsDataBase.exists()) {
            statisticsDataBase.createNewFile();
        }
    }
    
    /** @brief Guarda el paràmetre <em> statistics </em> a la BD.
     * 
     *  @param statistics Estadístiques a guardar a la BD.
     *  @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha guardat les estadístiques a la base de dades.
     */
    public void saveStatistics(String statistics) throws IOException {
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(statisticsDataBase, true));
        if (statisticsDataBase.length() != 0) bw.newLine();
        bw.write(statistics);
        bw.close();
    }
    
    /** @brief Retornar totes les estadístiques.
     * 
     *  @throws java.io.IOException 
     *  @return Retorna un ArrayList amb tot el contingut de la BD.
     * 
     * \pre <em>Cert.</em>
     * \post Es retorna tota la taula de estadístiques.
     */
    public ArrayList<String> getTableContent() throws IOException {
        ArrayList<String> tableContent = new ArrayList<>();
        
        File table = new File("HistorialEstadístic.txt");
        try {
            BufferedReader bR = new BufferedReader(new FileReader(table));
            String linea = bR.readLine();
            while (linea != null) {
                tableContent.add(linea);
                linea = bR.readLine();
            }
            
            return tableContent;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(viewStatistics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(viewStatistics.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /** @brief Eliminar totes les estadístiques.
     * 
     *  @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha eliminat totes les dades emmagatzemades a la BD.
     */
    public void deleteTableContent() throws IOException {
        try (FileWriter fW = new FileWriter("HistorialEstadístic.txt", false); PrintWriter pW = new PrintWriter(fW, false)) {
            pW.flush();
        }
    }
}




















