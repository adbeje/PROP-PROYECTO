/** @file LZW.java
 * @brief Classe que representa l'algorisme LZW.
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

 /** @class LZW
* @brief Classe que representa l'algorisme LZW.
* 
* Classe que hereda de Algorithm.
* Consta de dues operacions, <em>compress</em> i <em>decompress</em>.
*
* @author Carlos Gascón Dominguez
*/
public class LZW extends Algorithm {
 
    /** @brief Constructora per defecte.
     * 
     * S'executa al declarar un nou LZW.
     * \pre <em> Cert. </em>
     * \post Crea un LZW buit.
     */
    public LZW() {};
    
    /** @brief Realitza la compressió d'un arxiu .txt.
     * 
     * @throws IOException
     * 
     * \pre L'arxiu .txt que es vol comprimir ja ha estat seleccionat. Aquest arxiu no està buit.
     * \post L'arxiu destí sleccionat prèviament contindrà el resultat de la compressió.
     */
    @Override
    public void compress() throws IOException {
        
        CtrlDomain.initializeCompressionBitBuffer();
        
        TreeMap<String,Integer> dict = new TreeMap<>();
        
        for(int i = 0; i < 256; i++){
            dict.put(Character.toString((char) i), i);
        }
        
        int x;
        int code = 257;
        int binsize = 9;
        int max = 511;
        int auxcode;
        char B;
        String prefix = "";
        String auxprefix;
        
         while((x = CtrlDomain.read()) != -1) {
            B = (char) x;
            auxprefix = prefix + B;
            if(dict.containsKey(auxprefix) == true) prefix += B;
            else {
                dict.put(auxprefix, code);
                code++;
                auxcode = dict.get(prefix);
                CtrlDomain.writeBitBuffer(auxcode, binsize);
                prefix = Character.toString(B);
            }
            if(dict.size() > max){
                    binsize++;
                    max = (int) (Math.pow(2, binsize)) - 1;
            }
        }
        auxcode = dict.get(prefix);
        CtrlDomain.writeBitBuffer(auxcode, binsize);
        CtrlDomain.writeBitBuffer(256, binsize);
        CtrlDomain.closeBitBuffer(this.get_isFile());
    }
 
    /** @brief Realitza la descompressió d'un arxiu .lzw.
     * 
     * @throws IOException
     *
     * \pre L'arxiu .lzw que es vol descomprimir ja ha estat seleccionat.
     * \post L'arxiu destí seleccionat prèviament contindrà el resultat de la descompressió.
    */
    @Override
    public void decompress() throws IOException {
        
        CtrlDomain.initializeDecompressionBitBuffer();
        
        TreeMap<Integer,String> dict = new TreeMap<>();
        
        for(int i = 0; i < 256; i++) {
            dict.put(i, Character.toString((char) i));
        }
        
        int code = 257;
        int max = 510;
        int binsize = 9;
        String entry = "";
        String auxstr;
        char B;
        int old, index;
                
        index = CtrlDomain.readBitBuffer(binsize);
        auxstr = dict.get(index);
        for(int i = 0; i < auxstr.length();i++){
            CtrlDomain.write(auxstr.charAt(i));
        }
        
        old = index;
        
        while((index = CtrlDomain.readBitBuffer(binsize)) != 256){
            if(dict.containsKey(index) == true){
                entry = dict.get(index);
                for(int i = 0; i < entry.length();i++){
                    CtrlDomain.write(entry.charAt(i));
                }
                B = dict.get(index).charAt(0);
                auxstr = dict.get(old) + B;
                dict.put(code, auxstr);
                code++;
            }
            else {
                B = dict.get(old).charAt(0);
                auxstr = dict.get(old) + B;
                dict.put(code, auxstr);
                code++;
                for(int i = 0; i < auxstr.length();i++){
                    CtrlDomain.write(auxstr.charAt(i));
                }
            }
            old = index;
            if(dict.size() > max){
                binsize++;
                max = (int) (Math.pow(2, binsize)) - 2;
            }
        }
        CtrlDomain.closeBitBuffer(this.get_isFile());
    }
}
