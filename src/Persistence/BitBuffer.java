/** @file BitBuffer.java
 * @brief Aquesta classe llegeix o escriu bits des o a l'arxiu.
 *
 * Els imports que utilitza són:
 *       - import java.io.*
 *       - import java.util.BitSet
 *
 * @author Adrià  Ventura i Herce
 */

package Persistence;

import java.io.*;
import java.util.BitSet;

/*
 * Classe BitBuffer
 */

/** @class BitBuffer
* @brief Aquesta classe llegeix o escriu bits des o a l'arxiu.
*
* @author Adria  Ventura i Herce
*/
public class BitBuffer {
    /** @brief Instància de la classe BufferedOutputStream.*/
    final BufferedOutputStream OUT_STREAM;
    /** @brief Instància de la classe BufferedInputStream*/
    final BufferedInputStream INPUT_STREAM;
    /** @brief Int que representa el límit quan volem escriure un byte.*/
    int outBitCounter;
    /** @brief Int que representa una mena de Buffer de sortida, on guardarem els bits que vulguem escriure a la memòria intermèdia.*/
    int outBitBuffer;
    /** @brief Int que representa los 8 bits que té un byte.*/
    int inBitBuffer;
    /** @brief Int que representa el límit quan volem llegir un byte.*/
    int inBitCounter;
    /** @brief BitSet que representa un byte.*/
    BitSet bitBuffer;
    
    /** @brief Constructor, donem buffered out\in stream (estableix un d'ells en null).
     * 
     * @param outBuffer Buffer de sortida.
     * @param inBuffer Buffer d'entrada.
     * 
     * \pre <em>Cert.</em>
     * \post 
     */
    public BitBuffer(BufferedOutputStream outBuffer, BufferedInputStream inBuffer){
        OUT_STREAM = outBuffer;
        INPUT_STREAM = inBuffer;
        outBitCounter = 7;
        outBitBuffer = 0;
        inBitCounter = 8;
        bitBuffer = new BitSet(8);
    }
    
    /** @brief Retorna quants bytes hi ha disponibles.
     * 
     * @return Retorna un int que representa la longitud restant del <em>INPUT_STREAM</em>.
     * @throws IOException
     * 
     * \pre Cert.
     * \post Retorna la longitud restant del <em>INPUT_STREAM</em>.
     */
    public int length() throws IOException {
        return INPUT_STREAM.available();
    }
    
    /** @brief Obtè una cadena binaria i escriu els bits a la cadena.
     * 
     * @param bits Cadena binaria a passar a binari.
     * @throws IOException
     * 
     * \pre <em>bits</em> és vàlid.
     * \post Ha escrit els bits a la cadena.
     */
    public void write(String bits) throws IOException {
        for(int i = 0; i < bits.length(); i++, outBitCounter--) {
            if(outBitCounter == -1)
                clearBuffer();
            if(bits.charAt(i) == '1')
                outBitBuffer+=Math.pow(2, outBitCounter);
        }
    }
    
    /** @brief Aquest mètode obté el nombre sencer i la mida en bits i escriu el valor binari a la memòria intermèdia.
     * 
     * @param toBits objecte que volem passar a binari.
     * @param howManyBits quants bits tindrà el objecte a representar en binari.
     * @throws IOException
     * 
     * \pre <em>toBits</em> i <em>howManyBits</em> són vàlids.
     * \post Ha escrit en binari a la memòria intermèdia el parametre a escriure.
     */
    public void write(int toBits, int howManyBits) throws IOException {
        if(toBits < 0)
            toBits+=256;
        String str = Integer.toBinaryString(toBits);
        while(str.length() < howManyBits)
            str = "0" + str;
        write(str);
    }
    
    /** @brief Aquest mètode escriu els últims bits en la memòria intermèdia a l'arxiu i tanca la memòria intermèdia.
     * 
     * @param isFile Indica si el que s'esta tractant es un fitxer o una carpeta.
     * @throws IOException
     * 
     * \pre Cert.
     * \post Ha escrit els últims bits a la memória intermèdia i ha tancat la memòria intermèdia.
     */
    public void close(boolean isFile) throws IOException {
        if(outBitCounter != 7)
            OUT_STREAM.write(outBitBuffer);
        if(OUT_STREAM != null)
            if (isFile) OUT_STREAM.close();
            else OUT_STREAM.flush();
    }
    
    /** @brief Aquest mètode obté un nombre enter que representa quants bits necessiten llegir de l'arxiu i torna el valor sencer d'ells.
     * 
     * @param howManyBits quants bits tindrà el objecte a representar en binari.
     * @return Retorna el valor sencer  de la suma de bits llegits.
     * @throws IOException
     * 
     * \pre <em>howManyBits</em> és vàlid.
     * \post Ha llegit els <em>howManyBits</em> a llegir del arxiu.
     */
    public int read(int howManyBits) throws IOException {
        int bitsCalc = 0;
        while(howManyBits > 0) {
            if(inBitCounter == 8)
                if(fillBuffer() == -1)
                    return -1;
            if(bitBuffer.get(inBitCounter))
                bitsCalc+=Math.pow(2, howManyBits - 1);
            inBitCounter++;
            howManyBits--;
        }
        return bitsCalc;
    }
    
    /** @brief Aquest mètode plena la memòria intermèdia de bits.
     * 
     * @return Retorna 1 quan ha acabat.
     * @throws IOException
     * 
     * \pre Cert.
     * \post Ha emplenat la memòria intermèdia.
     */
    private int fillBuffer() throws IOException {
        bitBuffer.clear();
        int next = INPUT_STREAM.read();
        if (next == -1)
            return -1;
        for(int i = 7 ; i > -1 ; i--, next/=2) {
            if(next % 2 == 1)
                bitBuffer.set(i);
        }
        inBitCounter = 0;
        return 1;	
    }
    
    /** @brief Aquest mètode escriu el byte a la memòria intermèdia a l'arxiu i actualitza els comptadors.
     * 
     * @throws IOException
     * 
     * \pre Cert.
     * \post Ha escrit el que faltava a la memòria intermèdia i ha resetejat els comptadors.
     */
    private void clearBuffer() throws IOException {
        OUT_STREAM.write(outBitBuffer);
        outBitCounter = 7;
        outBitBuffer = 0;
    } 
}
