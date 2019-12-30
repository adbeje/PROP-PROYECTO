/** @file LZ78.java
 * @brief Classe que representa l'algorisme LZ78.
 *
 * Els imports que utilitza són:
 *    - import java.io.*
 *    - import java.util.*
 *
 * @author Carlos Gascón Dominguez
 */

package Domain;

import java.io.*;
import java.util.*;

/** @class LZ78
 * @brief Classe que representa l'algorisme LZ78.
 * 
 * Aquesta classe hereda de Algorithm.
 * Consta de dues operacions, <em>compress</em> i <em>decompress</em>.
 *
 * @author Carlos Gascón Dominguez
 */
public class LZ78 extends Algorithm {
	
    /** @brief Constructora per defecte.
     * 
     * S'executa al declarar un nou LZ78.
     * \pre <em> Cert. </em>
     * \post Crea un LZ78 buit.
     */
    public LZ78() {};

    /** @brief Realitza la compressió d'un arxiu .txt.
     * 
     * @throws IOException
     * 
     * \pre L'arxiu .txt que es vol comprimir ja ha estat seleccionat.
     * \post L'arxiu destí seleccionat prèviament contindrà el resultat de la compressió.
     */
    @Override
    public void compress() throws IOException {

        CtrlDomain.initializeCompressionBitBuffer();
        TreeMap<String,Integer> dict = new TreeMap<>();

        Integer index = 1;
        Integer cindex = 0;
        int x = 0;
        int binsize = 1;
        char c;
        String str = "";

        while((x = CtrlDomain.read()) != -1) {
                c = (char) x; 
                str += c;
                if(dict.containsKey(str) == false) {
                        dict.put(str,index);
                        CtrlDomain.writeBitBuffer(0, binsize);
                        CtrlDomain.writeBitBuffer(str.charAt(str.length()-1),16);
                }
                else {
                        while(dict.containsKey(str) != false && (x = CtrlDomain.read()) != -1) {
                                cindex = dict.get(str);
                                c = (char) x; 												 
                                str += c;
                        }
                        if(dict.containsKey(str) == false) {
                                dict.put(str,index);
                                CtrlDomain.writeBitBuffer(cindex,binsize);
                                CtrlDomain.writeBitBuffer(str.charAt(str.length()-1),16);
                        }
                        else { 
                                CtrlDomain.writeBitBuffer(dict.get(str), binsize);
                        }
                }
                str = "";
                index++;		
                binsize = Integer.toBinaryString(index).length();
        }
        
        CtrlDomain.closeBitBuffer(this.get_isFile());
    }

    /** @brief Realitza la descompressió d'un arxiu .lz78.
     * 
     * @throws IOException
     * 
     * \pre L'arxiu .lz78 que es vol descomprimir ja ha estat seleccionat.
     * \post L'arxiu destí seleccionat prèviament contindrà el resultat de la descompressió.
    */
    @Override
    public void decompress() throws IOException {

        CtrlDomain.initializeDecompressionBitBuffer();
        TreeMap<Integer,String> dict = new TreeMap<>();

        int x = 0;
        int binsize = 1;
        int y;
        char c;		
        String str = "";
        Integer index = 1;

        while((x = CtrlDomain.readBitBuffer(binsize)) != -1) {
                y = CtrlDomain.readBitBuffer(16);
                c = (char) y;
                if(y != -1) {
                        str += c;
                        if(x == 0) {
                                dict.put(index,str);
                                CtrlDomain.write(c);
                        }
                        else {
                                str = dict.get(x) + c;
                                dict.put(index, str);
                                for(int i = 0; i < str.length();i++)
                                        CtrlDomain.write(str.charAt(i));
                        }
                }
                else {
                        str = dict.get(x);
                        for(int i = 0; i < str.length();i++)
                                CtrlDomain.write(str.charAt(i));
                }
                str = "";
                index++;
                binsize = Integer.toBinaryString(index).length();
        }
        
        CtrlDomain.closeBitBuffer(this.get_isFile());
    }
}
