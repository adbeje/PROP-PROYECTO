/** @file Q.java
 * @brief Classe funcional de l'algorisme JPEG.
 *
 * Aquesta classe representa la classe de Quantificació i totes les operacions necessaries que es necessiten d'aquesta en el procés de realitzar l'algorisme JPEG.
 *
 * Els imports que utilitza són:
 *     - import static java.lang.Math.*
 *
 * @author Albert Pita Argemí
 */

package Domain;

import static java.lang.Math.*;

/*
 * Classe Q
 */

/** @class Q
 * @brief Classe funcional de l'algorisme JPEG.
 * 
 * Aquesta classe representa la classe de Quantificació i totes les operacions necessaries que es necessiten d'aquesta en el procés de realitzar l'algorisme JPEG.
 *
 * @author Albert Pita Argemí
 */
public class Q {
    /** @brief Matriu estàndard de Quantificació de la luminància.*/
    private static final int[][] QLuminance = {
        {16, 11, 10, 16, 24, 40, 51, 61},
        {12, 12, 14, 19, 26, 58, 60, 55},
        {14, 13, 16, 24, 40, 57, 69, 56},
        {14, 17, 22, 29, 51, 87, 80, 62},
        {18, 22, 37, 56, 68, 109, 103, 77},
        {24, 35, 55, 64, 81, 104, 113, 92},
        {49, 64, 78, 87, 103, 121, 120, 101},
        {72, 92, 95, 98, 112, 100, 103, 99}
    };
    
    /** @brief Matriu estàndard de Quantificació de la crominància.*/
    private static final int[][] QChrominance = {
        {17, 18, 24, 47, 99, 99, 99, 99},
        {18, 21, 26, 66, 99, 99, 99, 99},
        {24, 26, 56, 99, 99, 99, 99, 99},
        {47, 66, 99, 99, 99, 99, 99, 99},
        {99, 99, 99, 99, 99, 99, 99, 99},
        {99, 99, 99, 99, 99, 99, 99, 99},
        {99, 99, 99, 99, 99, 99, 99, 99},
        {99, 99, 99, 99, 99, 99, 99, 99}
    };
    
    /** @brief Matriu de Quantificació de la luminància modificada acorde amb la qualitat */
    private static int[][] QualityLuminance;
    /** @brief Matriu de Quantificació de la crominància modificada acorde amb la qualitat */
    private static int[][] QualityChrominance;
    
    /** @brief Constructora per defecte.
     * 
     * @param quality Qualitat de l'imatge.
     * 
     * \pre <em>Cert.</em>
     * \post Inicialitza les matrius de quantificació acorde amb la qualitat donada.
     */
    public Q(int quality) {
        QualityLuminance = createQLuminance(quality);
        QualityChrominance = createQChrominance(quality);
    }
    
    /** @brief Crea una nova matriu de quantificació per la luminància a partir d'una certa qualitat.
     * 
     * @param quality Qualitat de l'imatge.
     * @return Retorna una matriu de quantificació per la luminància formada a partir de <em>quality</em>.
     * 
     * \pre <em>Cert</em>
     * \post S'ha generat una matriu de quantificació per la luminància a partir de <em>quality</em>.una 
     */
    private int[][] createQLuminance(int quality) {
        int[][] Q = new int[8][8];
        
        if (quality == 0) quality = 1;
        
        double s;
        if (quality < 50) s = 5000 / quality;
        else s = 200 - 2 * quality;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Q[i][j] = (int) Math.floor((s * QLuminance[i][j] + 50) / 100);
                if (Q[i][j] == 0) Q[i][j] = 1;
            }
        }
        
        return Q;
    }
    
    /** @brief Crea una nova matriu de quantificació per la cromanància a partir d'una certa qualitat.
     * 
     * @param quality Qualitat de l'imatge.
     * @return Retorna una matriu de quantificació per la cromanància formada a partir de <em>quality</em>.
     * 
     * \pre <em>Cert</em>
     * \post S'ha generat una matriu de quantificació per la cromanàcia a partir de <em>quality</em>.una 
     */
    private int[][] createQChrominance(int quality) {
        int[][] Q = new int[8][8];
        
        if (quality == 0) quality = 1;
        
        double s;
        if (quality < 50) s = 5000 / quality;
        else s = 200 - 2 * quality;
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Q[i][j] = (int) Math.floor((s * QChrominance[i][j] + 50) / 100);
                if (Q[i][j] == 0) Q[i][j] = 1;
            }
        }
        
        return Q;
    }
     
    /** @brief Aplica la Quantificació a la luminància
     * 
     * @param M Matriu de doubles que representa el block tractat
     * @return 
     * 
     * \pre <em>Cert.</em>
     * \post Retorna un vector de ints el qual es el resultat d'aplica la Quantificació a <em>M</em>.
     */ 
    public int[] luminanceQuantization (double[][] M) {
        int[] Q = new int[64];
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Q[(8*i)+j] = (int) round(M[i][j] / QualityLuminance[i][j]);
            }
        }
        
        return Q;
    }
    
    /** @brief Aplica la Quantificació inversa a la luminància
     * 
     * @param A Vector de ints que representa el block tractat
     * @return 
     * 
     * \pre <em>Cert.</em>
     * \post Retorna una matriu de doubles la qual es el resultat d'aplicar la Quantificació inversa a <em>A</em>.
     */ 
    public double[][] inverseLuminanceQuantization (int[] A) {
        double[][] iQ = new double[8][8];
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                iQ[i][j] = A[(8*i)+j] * QualityLuminance[i][j];
            }
        }

        return iQ;
    }
    
    /** @brief Aplica la Quantificació a la crominància
     * 
     * @param M Matriu de doubles que representa el block tractat
     * @return 
     * 
     * \pre <em>Cert.</em>
     * \post Retorna un vector de ints el qual es el resultat d'aplica la Quantificació a <em>M</em>.
     */
    public int[] chrominanceQuantization (double[][] M) {
        int[] Q = new int[64];
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Q[(8*i)+j] = (int) round(M[i][j] / QualityChrominance[i][j]);
            }
        }
        
        return Q;
    }
    
    /** @brief Aplica la Quantificació inversa a la crominància
     * 
     * @param A Vector de ints que representa el block tractat
     * @return 
     * 
     * \pre <em>Cert.</em>
     * \post Retorna una matriu de doubles la qual es el resultat d'aplicar la Quantificació inversa a <em>A</em>.
     */ 
    public double[][] inverseChrominanceQuantization (int[] A) {      
        double[][] iQ = new double[8][8];
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                iQ[i][j] = A[(8*i)+j] * QualityChrominance[i][j];
            }
        }
        
        return iQ;
    }
}
