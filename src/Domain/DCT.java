/** @file DCT.java
 * @brief Classe funcional de l'algorisme JPEG.
 *
 * Aquesta classe representa les matrius de DCT i totes les operacions necessaries que es necessiten d'aquesta en el procés de realitzar l'algorisme JPEG.
 *
 * Els imports que utilitza són:
 *       - import static java.lang.Math.*
 *
 * @author Albert Pita Argemí
 */

package Domain;

import static java.lang.Math.*;

/*
 * Classe DCT
 */

/** @class DCT
 * @brief Classe funcional de l'algorisme JPEG.
 * 
 * Aquesta classe representa les matrius de DCT i totes les operacions necessaries que es necessiten d'aquesta en el procés de realitzar l'algorisme JPEG.
 * 
 * @author Albert Pita Argemí
 */
public class DCT {
    /** @brief Matriu de DCT*/
    private final double[][] dct;
    /** @brief Matriu inversa de DCT*/
    private final double[][] t_dct;
    
    /** @brief Constructora per defecte.
     * S'exectua al declarar una nova DCT. Inicialitza la <em>dct</em> i la <em>t_dct</em> a dues matrius 8x8 de doubles. Després
     * aplica la 2D DCT a <em>dct</em>. I finalment aplica la inversa a la <em>dct</em> per formar la <em>t_dct</em>. 
     * 
     * \pre <em>Cert.</em>
     * \post Crea un nova instància de DCT inicialitzant les respectives matrius amb els valors adients.
     */ 
    public DCT() {
        dct = new double[8][8];
        t_dct = new double[8][8];
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0) dct[i][j] = (1/sqrt(8));
                else dct[i][j] = cos((PI*i*(2*j + 1))/16)/2;
            }
        }
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                t_dct[i][j] = dct[j][i];
            }
        }
    }
    
    /** @brief Aplica la DCT a un cert bloc donat.
     * 
     * @param M Block que va ser tractat
     * @return Matriu de doubles resultant d'aplicar la DCT a <em>M</em>.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna la matriu resultant d'aplicar la DCT a <em>M</em>.
     */ 
    public double[][] twoDimensionalDCT (double[][] M) {
        double[][] resultM = matrixMult(dct, M);
        resultM = matrixMult(resultM, t_dct);
        
        return resultM;
    }
    
    /** @brief Aplica la DCT inversa a un cert bloc donat.
     * 
     * @param M Block que va ser tractat
     * @return Matriu de doubles resultant d'aplicar la DCT inversa a <em>M</em>.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna la matriu resultant d'aplicar la DCT inversa a <em>M</em>.
     */ 
    public double[][] inverseTwoDimensionalDCT (double[][] M) {
        double[][] resultM = matrixMult(t_dct, M);
        resultM = matrixMult(resultM, dct);
        
        return resultM;
    }
    
    /** @brief Multiplica dues matrius.
     * 
     * @param M1 Primera matriu.
     * @param M2 Segona matriu.
     * @return <em>M1</em> * <em>M2</em>.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna la multiplicació entre <em>M1</em> i <em>M2</em>.
     */ 
    private double[][] matrixMult (double[][] M1, double[][] M2) {
        
        double[][] resultM = new double[8][8];
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                resultM[i][j] = 0;
                for (int k = 0; k < 8; k++) {
                    resultM[i][j] += M1[i][k] * M2[k][j];
                }
            }
        }
        
        return resultM;
    }
}

