/** @file Huffman.java
 * @brief Classe funcional de l'algorisme JPEG.
 *
 * Aquesta classe representa les taules de Huffman i totes les operacions necessaries que es necessiten d'aquesta en el procés de realitzar l'algorisme JPEG.
 *
 * Els imports que utilitza són:
 *     - import java.io.*
 *     - import java.util.ArrayList
 *
 * @author Albert Pita Argemí
 */

package Domain;

import java.io.*;
import java.util.ArrayList;

/*
 * Classe Huffman
 */

/** @class Huffman
 * @brief Classe funcional de l'algorisme JPEG.
 * 
 * Aquesta classe representa les taules de Huffman i totes les operacions necessaries que es necessiten d'aquesta en el procés de realitzar l'algorisme JPEG.
 * 
 * @author Albert Pita Argemí
 */
public class Huffman {
    /** @brief Arbre binàri que representa la taula DC per la luminància recomenada per l'estàndard de JPEG.*/
    private final BinaryTreeDC DCluminance;
    /** @brief Arbre binàri que representa la taula DC per la cromanància recomenada per l'estàndard de JPEG.*/
    private final BinaryTreeDC DCchrominance;
    /** @brief Arbre binàri que representa la taula AC per la luminància recomenada per l'estàndard de JPEG.*/
    private final BinaryTreeAC ACluminance;
    /** @brief Arbre binàri que representa la taula AC per la cromanància recomenada per l'estàndard de JPEG.*/
    private final BinaryTreeAC ACchrominance;    
    
    /** @brief Constructora per defecte.
     * S'exectua al declarar un nou Huffman.
     * 
     * @throws IOException
     * 
     * \pre <em> Cert. </em>
     * \post Crea un nova instància de Huffman inicialitzant les respectives taules als seus valors pertinents, tots ells obtinguts de quatre arxius de text ubicats a la carpeta <em>HuffmanTables</em>.
     */        
    public Huffman() throws IOException {
        DCluminance = new BinaryTreeDC ("../data/HuffmanTables/DC_luminance.txt");
        DCchrominance = new BinaryTreeDC ("../data/HuffmanTables/DC_chrominance.txt");
        ACluminance = new BinaryTreeAC ("../data/HuffmanTables/AC_luminance.txt");
        ACchrominance = new BinaryTreeAC("../data/HuffmanTables/AC_chrominance.txt");
    }
    
    /** @brief Fa la compressió d'un bloc de la luminància.
     * 
     * Inicialment aplica al block que li passen la codificació Run-Length. Després fa l'entropy coding. I finalment obté de les taules de Huffman
     * previament inicialitzades els valors codificats.
     * 
     * @param array Block 8x8 ja transformat.
     * @return String amb el block ja comprimit i codificat.
     * 
     * \pre <em> Cert. </em>
     * \post Retorna una String amb el block ja comprimit i codificat.
     */ 
    public String luminanceCompression(int[] array) {
        ArrayList<int[]> data = RLE(array);
        data = EC(data);
        
        String compressed = "";
        
        int size = data.size();
        for (int i = 0; i < size; i++) {
            int x = data.get(i)[0];
            int y = data.get(i)[1];
            int z = data.get(i)[2];
            
            if (i == 0) {
                compressed += DCluminance.getCodeWord(x);
                compressed += NumbertoBinary(y);
            }
            else {
                compressed += ACluminance.getCodeWord(x, y);
                compressed += NumbertoBinary(z);
            }
        }
        
        return compressed;
    }
    
    /** @brief fa la descompressió d'un bloc de la luminància.
     * 
     * Inicialment obté els valors decodificats de les taules de Huffman. Aplica la inversa de l'entropy coding. I finalment aplica al block resultant
     * la codificació inversa de Run-Length.
     * 
     * @param data String amb la codificació comprimida del component de luminància.
     * @param block Block 8x8 obtingut de la descompressió de data.
     * @param index Index que apunta a la primera posició del bloc dins de <em>data</em>.
     * @return Posició dins de <em>data</em> on acaba el bloc.
     * @throws IOException
     * 
     * \pre <em>index</em> no apunta al final de <em>data</em>.
     * \post Retorna la posició final del bloc, on teóricament comença el següent.
     */
    public int luminanceDecompression (String data, int[] block, int index) throws IOException {

        int aux_index = index;
        
        String content = DCluminance.getCategory(data, aux_index);
        String[] categoryLength = content.split("/");
        
        int DCHLen = Integer.parseInt(categoryLength[0]);
        int length = Integer.parseInt(categoryLength[1]);
        
        aux_index += length;
        
        if (DCHLen == -1) throw new IOException (getClass().getName() + ".luminanceDecompressión: Error DCHLen -1");
        
        block[0] = getNum(data.substring(aux_index, aux_index + DCHLen));
        
        aux_index += DCHLen;
        
        int blockSize = 1;
        while (blockSize < 64) {
            
            String actualPoint = ACluminance.getRunSize(data, aux_index);
            String[] runSize = actualPoint.split("/");
            
            length = Integer.parseInt(runSize[2]);

            aux_index += length;
            
            int x = Integer.parseInt(runSize[0]);
            int y = getNum(data.substring(aux_index, aux_index + Integer.parseInt(runSize[1])));
            
            if (x == -1 && y == -1) throw new IOException (getClass().getName() + ".luminanceDecompressión: actualPoint.x -1, actualPoint.y -1");
            
            aux_index += Integer.parseInt(runSize[1]);
            
            if (x == y && y == 0) {
                while (blockSize < 64) {
                    block[blockSize++] = 0;
                }
                break;
            }
            for (int i = 0; i < x; i++) {
                block[blockSize++] = 0;
            }
            block[blockSize++] = y;
        }
        
        return aux_index;
    }
    
    /** @brief Fa la compressió d'un block de la cromanància.
     * 
     * Inicialment aplica al block que li passen la codificació Run-Length. Després fa l'entropy coding. I finalment obté de les taules de Huffman
     * previament inicialitzades els valors codificats.
     * 
     * @param array Block 8x8 ja transformat.
     * @return String amb el block ja comprimit i codificat.
     * 
     * \pre <em> Cert. </em>
     * \post Retorna una String amb el block ja comprimit i codificat.
     */ 
    public String chrominanceCompression(int[] array) {
        ArrayList<int[]> data = RLE(array);
        data = EC(data);
        
        String compressed = "";
        
        int size = data.size();
        for (int i = 0; i < size; i++) {
            int x = data.get(i)[0];
            int y = data.get(i)[1];
            int z = data.get(i)[2];
            
            if (i == 0) {
                compressed += DCchrominance.getCodeWord(x);
                compressed += NumbertoBinary(y);
            }
            else {
                compressed += ACchrominance.getCodeWord(x, y);
                compressed += NumbertoBinary(z);
            }
        }
        
        return compressed;
    }
    
    /** @brief fa la descompressió d'un bloc de la cromanància.
     * 
     * Inicialment obté els valors decodificats de les taules de Huffman. Aplica la inversa de l'entropy coding. I finalment aplica al block resultant
     * la codificació inversa de Run-Length.
     * 
     * @param data String amb la codificació comprimida d'una de les componentsde cromanància.
     * @param block Block 8x8 obtingut de la descompressió de data.
     * @param index Index que apunta a la primera posició del bloc dins de <em>data</em>.
     * @return Posició dins de <em>data</em> on acaba el bloc.
     * @throws IOException
     * 
     * \pre <em>index</em> no apunta al final de <em>data</em>.
     * \post Retorna la posició final del bloc, on teóricament comença el següent.
     */
    public int chrominanceDecompression(String data, int[] block, int index) throws IOException {
        
        int aux_index = index;
        
        String content = DCchrominance.getCategory(data, aux_index);
        String[] categoryLength = content.split("/");
        
        int DCHLen = Integer.parseInt(categoryLength[0]);
        int length = Integer.parseInt(categoryLength[1]);
        
        aux_index += length;
        
        if (DCHLen == -1) throw new IOException (getClass().getName() + ".luminanceDecompressión: Error DCHLen -1");
        
        block[0] = getNum(data.substring(aux_index, aux_index + DCHLen));
        
        aux_index += DCHLen;
        
        int blockSize = 1;
        while (blockSize < 64) {
            
            String actualPoint = ACchrominance.getRunSize(data, aux_index);
            String[] runSize = actualPoint.split("/");
            
            length = Integer.parseInt(runSize[2]);
            
            aux_index += length;
            
            int x = Integer.parseInt(runSize[0]);
            int y = getNum(data.substring(aux_index, aux_index + Integer.parseInt(runSize[1])));
            
            if (x == -1 && y == -1) throw new IOException (getClass().getName() + ".luminanceDecompressión: actualPoint.x -1, actualPoint.y -1");
            
            aux_index += Integer.parseInt(runSize[1]);
            
            if (x == y && y == 0) {
                while (blockSize < 64) {
                    block[blockSize++] = 0;
                }
                break;
            }
            for (int i = 0; i < x; i++) {
                block[blockSize++] = 0;
            }
            block[blockSize++] = y;
        }

        return aux_index;
    }
    
    /** @brief Aplica a un vector de ints la codificació Run-Length.
     * 
     * @param array Block 8x8 en forma de vector de ints.
     * @return ArrayList amb <em>array</em> codificat en Run-Length.
     * @throws IOException
     * 
     * \pre <em>Cert</em>.
     * \post Retorna un ArrayList amb la codificació en Run-Length del paràmetre <em>array</em>.
     */
    private ArrayList<int[]> RLE (int[] array) {
        ArrayList<int[]> data = new ArrayList<>();
        
        int countZero = 0;
        for (int i = 0; i < 64; i++) {
            int[] actualData = new int[3];
            if (i == 0) {
                actualData[0] = array[0];
                data.add(actualData);
            }
            else {
                boolean flag = true;
                for (int j = i; j < 64; j++) {
                    if (array[j] != 0) flag = false;
                }
                
                if (flag) {
                    actualData[0] = actualData[1] = 0;
                    data.add(actualData);
                    break;
                }
                
                if (countZero == 16) {
                    actualData[0] = 15;
                    actualData[1] = 0;
                    countZero = 0;
                    data.add(actualData);
                }
                
                if (array[i] == 0) countZero++;
                else {
                    actualData[0] = countZero;
                    actualData[1] = array[i];
                    countZero = 0;
                    data.add(actualData);
                }
            }
        }
        
        return data;
    }
    
    /** @brief Aplica a un ArrayList de vector de ints la entropy coding.
     * 
     * @param RLEtoEC ArrayList de vector de ints amb la codificació en Run-Length d'un block.
     * @return ArrayList amb el resultat d'aplicar la entropy coding a el paràmetre <em>RLEtoEC</em>.
     * 
     * \pre <em>Cert</em>.
     * \post Retorna un ArrayList amb el resultat d'aplicar la entropy coding a el paràmetre <em>RLEtoEC</em>.
     */
    private ArrayList<int[]> EC (ArrayList<int[]> RLEtoEC) {
        int size = RLEtoEC.size();
        
        for (int i = 0; i < size; i++) {
            int[] actualData = RLEtoEC.get(i);
            if (i == 0) {
                int DC = actualData[0];
                RLEtoEC.get(0)[0] = getNumberBits(DC);
                RLEtoEC.get(0)[1] = DC;
            }
            else if (RLEtoEC.get(i)[0] != 0 || RLEtoEC.get(i)[1] != 0) {
                RLEtoEC.get(i)[2] = RLEtoEC.get(i)[1];
                RLEtoEC.get(i)[1] = getNumberBits(RLEtoEC.get(i)[2]);
            }
        }
        
        return RLEtoEC;
    }
    
    /** @brief Obté el nombre de bits d'un cert nombre.
     * 
     * @param num Numero del qual es vol saber els bits.
     * @return int amb el nombre de bits del <em>num</em>.
     * 
     * \pre <em>Cert</em>.
     * \post Retorna el nombre de bits del paràmetre <em>num</em>.
     */
    private static int getNumberBits(int num) {
        if (num == 0) return 0;
        if (num < 0) num *= -1;
        
        double result = Math.log(num) / Math.log(2);
        
        for (int i = (int) (result - 5); i <= result + 5; i++) {
            if (Math.pow(2, i-1) <= num && num <= Math.pow(2, i) - 1) return i;
        }
        return 0;
    }
    
    /** @brief Obté la representació binària d'un cert nombre.
     * 
     * @param num Numero del qual es vol saber la representació binària.
     * @return String amb la representació binària del <em>num</em>.
     * 
     * \pre <em>Cert</em>.
     * \post Retorna la representació binària del paràmetre <em>num</em>.
     */
    private static String NumbertoBinary(int num) {
        if (num == 0) return "";
        int bitNumber = getNumberBits(num);
        
        String res;
        if (num < 0) {
            res = Integer.toBinaryString((-num) ^ (int)(Math.pow(2, bitNumber)-1));
        }
        else res = Integer.toBinaryString(num);
        
        int p = bitNumber - res.length();
        for (int i = 0; i < p; i++) res = "0" + res;
        
        return res;
    }
    
    /** @brief Obté el numero a partir de la seva representació binària.
     * 
     * @param s_num Representació binària d'un cert nombre.
     * @return int que representa el nombre que té per representació binària <em>s_num</em>.
     * 
     * \pre <em>Cert</em>.
     * \post Retorna el nombre que té per representació binària <em>s_num</em>.
     */
    private static int getNum (String s_num) {
        if (s_num.equals("")) return 0;
        
        int size = s_num.length();
        int num;
        if (s_num.charAt(0) == '1') {
            num = Integer.parseInt(s_num, 2);
        }
        else {
            String negative_num = "";
            for (int i = 0; i < size; i++) {
                if (s_num.charAt(i) == '0') negative_num += "1";
                else negative_num += "0";
            }
            num = -Integer.parseInt(negative_num, 2);
        }
        
        return num;
    }
}
