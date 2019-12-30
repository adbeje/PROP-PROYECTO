/** @file LZSS.java
 * @brief Classe que representa l'algoritme LZSS.
 *
 * L'import que utilitza és:
 *     - import import.io.*
 *
 * @author Adrià Ventura i Herce
 */

package Domain;

import java.io.*;

/** @class LZSS
* @brief Classe que representa l'algoritme LZSS.
*
* Classe que hereda de Algorithm.
* Consta de dues operacions, <em>compress</em> i <em>decompress</em>.
*
* @author Adrià Ventura i Herce
*/
public class LZSS extends Algorithm {
    /*@brief és el tamany de la finestra en potencia de 2.*/
    final int WINDOW_SIZE;
    /*@brief és tamany màxim de coincidència en potencia de 2.*/
    final int MAX_MATCH_SIZE;
    /*@brief és tamany mínim de coincidència en potencia de 2.*/
    final int MIN_MATCH_SIZE;
    
    /**
     *  @brief Constructora pel cas de voler tindre opcions a l'hora de comprimir.
     * 
     * @param winSize és el tamany de la finestra.
     * @param maxMatch és tamany màxim de coincidència.
     * @param minMatch és tamany mínim de coincidència.
    */
    public LZSS(int winSize, int maxMatch, int minMatch) {
        this.WINDOW_SIZE = winSize;
        this.MAX_MATCH_SIZE = maxMatch;
        this.MIN_MATCH_SIZE = minMatch;
    }
    
    /**
     *  @brief Constructora per defecte al mode automàtic de compressió.
    */
    public LZSS() {
        WINDOW_SIZE = 12;
        MAX_MATCH_SIZE = 5;
        MIN_MATCH_SIZE = 3;
    }
      
    /** @brief Comprimeix un arxiu .txt fent servir l'algorisme LZSS.
     * @throws IOException
     * 
     * \pre Entren valors vàlids per comprimir.
     * \post Surt un arxiu compres amb l'algorisme LZSS.
     */
    @Override
    public void compress() throws IOException {
	    
        CtrlDomain.initializeCompressionBitBuffer();
                
        CtrlDomain.writeBitBuffer(WINDOW_SIZE, 8);
        CtrlDomain.writeBitBuffer(MAX_MATCH_SIZE, 8);
	    
        int winBytes = (int)Math.pow(2, WINDOW_SIZE);
        System.out.println(winBytes);
        int maxMatch = (int)Math.pow(2, MAX_MATCH_SIZE);
        System.out.println(maxMatch);
        StringBuffer winBuffer = new StringBuffer(winBytes);
	
        int nextByte;
        String bestMatch = "";
        int index = -1;
        int tmpIndex = -1;	
		
        while((nextByte = CtrlDomain.read()) != -1) {
                if(nextByte < 0 ) 
                    nextByte+=256;
                tmpIndex = winBuffer.indexOf(bestMatch + (char)nextByte);
                if(tmpIndex != -1 && bestMatch.length() + 1 < maxMatch ) {
                    bestMatch+=(char)nextByte;
                    index = tmpIndex;
                }
                else {
                        if(bestMatch.length() >= MIN_MATCH_SIZE) {
                            CtrlDomain.writeBitBuffer("1");
                            CtrlDomain.writeBitBuffer(index, WINDOW_SIZE);
                            CtrlDomain.writeBitBuffer(bestMatch.length(), MAX_MATCH_SIZE);
                            tmpIndex = -1;
                            index = -1;
                            winBuffer.append(bestMatch);
                            bestMatch = "" + (char)(nextByte);
                        }
                        else{
                                bestMatch+=(char)(nextByte);
                                while((tmpIndex = winBuffer.indexOf(bestMatch)) == -1 && bestMatch != "") {
                                    CtrlDomain.writeBitBuffer("0");
                                    CtrlDomain.writeBitBuffer((bestMatch.charAt(0)), 8);
                                    winBuffer.append(bestMatch.charAt(0));
                                    bestMatch = bestMatch.substring(1);
                                }					
                        }
                }
                if (winBuffer.length() > winBytes)
            winBuffer = winBuffer.delete(0, winBuffer.length() - winBytes);
        }
        while(bestMatch.length() > 0 && bestMatch != "") {
                if(index != -1 && bestMatch.length() > MIN_MATCH_SIZE) {
                    CtrlDomain.writeBitBuffer("1");
                    CtrlDomain.writeBitBuffer(index, WINDOW_SIZE);
                    CtrlDomain.writeBitBuffer(bestMatch.length(), MAX_MATCH_SIZE);
                    bestMatch = "";
                }
                else {
                    CtrlDomain.writeBitBuffer("0");
                    CtrlDomain.writeBitBuffer((byte)(bestMatch.charAt(0)), 8);
                    bestMatch = bestMatch.substring(1);
                }
        }
        CtrlDomain.closeBitBuffer(this.get_isFile());
    }
    
    
     /** @brief Descomprimeix un arxiu .lzss fent servir l'algorisme LZSS.
     * @throws IOException
     * 
     * \pre Entren valors vàlids per descomprimir.
     * \post Surt un arxiu compres amb l'algorisme LZSS.
     */
    @Override
    public void decompress() throws IOException {
	    
        CtrlDomain.initializeDecompressionBitBuffer();

        final int WINDOW = CtrlDomain.read();
        final int MAX_MATCH = CtrlDomain.read();
        int winBytes = (int)Math.pow(2, WINDOW);
        StringBuffer winBuffer = new StringBuffer(winBytes);
	    
        int nextIndex;
        int nextLen;
        int nextBit;

        while((nextBit = CtrlDomain.readBitBuffer(1)) != -1) {
                if(nextBit == 1) {
                    nextIndex = CtrlDomain.readBitBuffer(WINDOW);
                    nextLen = CtrlDomain.readBitBuffer(MAX_MATCH);
                    String toAdd = winBuffer.substring(nextIndex, nextIndex + nextLen);
                    for(int i = 0; i < toAdd.length(); i++) {
                            CtrlDomain.write(toAdd.charAt(i));
                            winBuffer.append(toAdd.charAt(i));
                    }
                }
                else {	
                    nextIndex = CtrlDomain.readBitBuffer(8);
                    if(nextIndex != -1)
                            CtrlDomain.write(nextIndex);
                    winBuffer.append((char)nextIndex);
                }
                if (winBuffer.length() > winBytes)
                    winBuffer = winBuffer.delete(0, winBuffer.length() - winBytes);	
        }
	    
        CtrlDomain.closeBitBuffer(this.get_isFile());
    }
}



