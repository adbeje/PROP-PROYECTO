/** @file JPEG_Utils.java
 * @brief Classe funcional de l'algorisme JPEG.
 *
 * Consta de varies funcions utilitzades per poder aplicar l'algorisme JPEG.
 *
 * L'import que utilitza és:
 *     - import java.util.ArrayList
 *
 * @author Albert Pita Argemí
 */

package Domain;

import java.util.ArrayList;

/*
 * Classe JPEG_Utils
 */

/** @class JPEG_Utils
 * @brief Classe funcional de l'algorisme JPEG.
 * 
 * Consta de varies funcions utilitzades per poder aplicar l'algorisme JPEG.
 * 
 * @author Albert Pita Argemí
 */
public class JPEG_Utils {
    
    /** @brief Instància de la classe DCT per poder realitzar diverses operacions.*/
    private final static DCT dct = new DCT();
    /** @brief Instància de la classe Q per poder realitzar diverses operacions.*/
    private static Q q;
    
    /** @brief Array amb l'ordre en zig zag per poder codificar un block 8x8 de l'imatge.*/
    private static final int[] zigZagOrder = {
        0,  1,  8, 16,  9,  2,  3, 10,
        17, 24, 32, 25, 18, 11,  4,  5,
        12, 19, 26, 33, 40, 48, 41, 34,
        27, 20, 13,  6,  7, 14, 21, 28,
        35, 42, 49, 56, 57, 50, 43, 36,
        29, 22, 15, 23, 30, 37, 44, 51,
        58, 59, 52, 45, 38, 31, 39, 46,
        53, 60, 61, 54, 47, 55, 62, 63,
    };
    
    /** @brief Array amb l'ordre invers al zig zag per poder descodificar un block 8x8 de l'imatge.*/
    private static final int[] inverseZigZagOrder = {
        0,  1,  5, 6,  14,  15,  27, 28,
        2, 4, 7, 13, 16, 26,  29,  42,
        3, 8, 12, 17, 25, 30, 41, 43,
        9, 11, 18, 24, 31, 40, 44, 53,
        10, 19, 23, 32, 39, 45, 52, 54,
        20, 22, 33, 38, 46, 51, 55, 60,
        21, 34, 37, 47, 50, 56, 59, 61,
        35, 36, 48, 49, 57, 58, 62, 63,
    };
    
    /**
     * @param quality *  @brief Tracta un component de l'imatge.
     * 
     * Divideix el component en blocks de 8x8. Per cada block resta als seus valors 128, li aplica la 2D DCT, segons
     * si es el component de luminància o cromanància, li aplica la respectiva Quantificació i finalment se li aplica
     * l'ordenació en zig zag.
     * 
     * @param component Component Y, Cb o Cr de l'imatge.
     * @param checked_height Altura divisible entre 8.
     * @param checked_width Amplada divisible entre 8.
     * @param height Altura real de l'imatge.
     * @param width Amplada real de l'imatge.
     * @param Y Indica si el component és la luminància o no.
     * @return Retorna un vector de vectors de ints els segons del qual són els blocks 8x8 de la <em>component</em> ja tractats.
     * 
     * \pre El <em>component</em> és o Y o Cb o Cr, <em>checked_height</em>, <em>checked_width</em>, <em>height</em> i <em>width</em> són vàlids.
     * \post Es retorna una matriu de ints, de tamany <em>checked_height</em>x<em>checked_width</em>, amb tots els blocks 8x8 del component tractats.
     */
    public static int[][] treatComponent (double[][] component, int checked_height, int checked_width, int height, int width, int quality, boolean Y) {      
        q = new Q(quality);
        
        int[][] result = new int[(checked_height/8)*(checked_width/8)][64];
        
        int index = 0;
        int i = 0;
        int j = 0;
        
        double[][] treatedMatrix = new double[8][8];
        
        while (i < checked_height) {
            
            for (int h = 0; h < 8; h++) {
                for (int w = 0; w < 8; w++) {
                    if (i+h >= height) treatedMatrix[h][w] = treatedMatrix[h-1][w];
                    else if (j+w >= width) treatedMatrix[h][w] = treatedMatrix[h][w-1];
                    else treatedMatrix[h][w] = component[i+h][w+j] - 128;
                }
            }
            
            treatedMatrix = dct.twoDimensionalDCT(treatedMatrix);
            
            int[] treatedArray;
            if (Y) treatedArray = q.luminanceQuantization(treatedMatrix);
            else treatedArray = q.chrominanceQuantization(treatedMatrix);

            result[index] = ZZSort(treatedArray);
            
            index++;
            j += 8;
            if (j >= checked_width) {
                j = 0;
                i += 8;
            }   
        }
        
        return result;
    }
    
    /**
     * @param quality *  @brief Aplica el procés invers a tractar un component de l'imatge.
     * 
     * Inicialment se li passen tots el blocks de 8x8. Per cada block se li aplica l'ordenació inversa en zig zag,
     * depèn de si és el component de luminància o cromanància se li aplica la respectiva Quantificació inversa,
     * després se li aplica la 2D DCT inversa i finalment se li suma 128 als seus valors i es guarden a la seva posició
     * dins de la component.
     * 
     * @param component ArrayList amb tots els blocks 8x8 de la component.
     * @param height Altura de l'imatge final descomprimida.
     * @param width Amplada de l'imatge final descomprimida.
     * @param Y Indica si el component és la luminància o no.
     * @return Retorna una matriu de doubles la qual respresenta la totalitat de la respectiva component.
     * 
     * \pre <em>component</em> respresenta tots els blocks 8x8 de les components Y, Cb o Cr, <em>height</em> i <em>width</em> mides vàlides de la nova imatge.
     * \post Es retorna una matriu de doubles, de tamany <em>height</em>x<em>width</em>, representant la unió de tots els blocks 8x8 ja tractats.
     */
    public static double[][] untreatComponent (ArrayList<int[]> component, int height, int width, int quality, boolean Y) {
        q = new Q(quality);
        
        double[][] result = new double[height][width];
        
        int index = 0;
        int i = 0;
        int j = 0;
        
        while (i < height) {
            
            int[] untreatedArray = component.get(index);
            untreatedArray = inverseZZSort(untreatedArray);
            
            double[][] untreatedMatrix;
            if(Y) untreatedMatrix = q.inverseLuminanceQuantization(untreatedArray);
            else untreatedMatrix = q.inverseChrominanceQuantization(untreatedArray);
            
            untreatedMatrix = dct.inverseTwoDimensionalDCT(untreatedMatrix);
            
            for (int h = 0; h < 8; h++) {
                for (int w = 0; w < 8; w++) {
                    result[h+i][w+j] = untreatedMatrix[h][w] + 128;
                }
            }
            
            index++;
            j += 8;
            if (j >= width) {
                j = 0;
                i += 8;
            }
        }
        
        return result;
    }
    
    /** @brief Funció que ordena en zig zag un vector de ints.
     * 
     * @param array Vector de ints que representen un block.
     * @return Vector ordenat en zig zag.
     * 
     * \pre <em>Cert</em>.
     * \post Retorna el vector <em>array</em> ordenat en zig zag.
     */
    private static int[] ZZSort(int[] array) {
        int[] sorted = new int[64];
        
        for (int i = 0; i < 64; i++) sorted[i] = array[zigZagOrder[i]];
        
        return sorted;
    }
    
    /** @brief Funció que ordena de forma inversa a en zig zag un vector de ints.
     * 
     * @param array Vector de ints que representen un block.
     * @return Vector ordenat inversament a en zig zag.
     * 
     * \pre <em>Cert</em>.
     * \post Retorna el vector <em>array</em> ordenat inversament a en zig zag.
     */
    private static int[] inverseZZSort(int[] array) {
        
        int[] inverseSorted = new int[64];
        
        for (int i = 0; i < 64; i++) inverseSorted[i] = array[inverseZigZagOrder[i]];

        return inverseSorted;
    }
    
    /** @brief Comprova que l'altura sigui divisible per 8 i si no ho és li suma el necessari.
     * @param height Altura  
     * 
     * @return int checked_height
     * 
     * \pre <em>Cert.</em>
     * \post Retorna l'altura ja comprovada.
     */
    public static int checkHeight(int height) {
        int modHeight = height % 8;
        
        if (modHeight != 0) return height + (8 - modHeight);
        return height;
    }
    
    /** @brief Comprova que l'amplada sigui divisible per 8 i si no ho és li suma el necessari.
     * @param width Amplada 
     * 
     * @return int checked_width
     * 
     * \pre <em>Cert.</em>
     * \post Retorna l'amplada ja comprovada.
     */
    public static int checkWidth(int width) {
        int modWidth = width % 8;
        
        if (modWidth != 0) return width + (8 - modWidth);
        return width;
    }
}
