/** @file JPEG.java
 * @brief Classe que respresenta l'algorisme JPEG.
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
 * Classe JPEG
 */

/** @class JPEG
 * @brief Classe que respresenta l'algorisme JPEG.
 * 
 * Aquesta hereda de la classe abstracta Algorithm.
 * Consta de dues operacions. La primera és comprimir imatges PPM a .jpeg i la segona al revés, descomprimir imatges JPEG a .ppm.
 * Aquest algorisme està desarrollat en base a l'estàndard de JPEG.
 * 
 * @author Albert Pita Argemí
 */
public class JPEG extends Algorithm {
    
    /** @brief Imatge tractada.*/
    private PPM image;
    /** @brief Instància de la classe Huffman per poder realitzar diverses operacions.*/
    private Huffman huffman;
    /** @brief Qualitat de l'imatge */
    private int quality;
    
    // Constructora
    
    /** @brief Constructora per defecte.
     * 
     * S'exectua al declarar un nou JPEG.
     * \pre <em> Cert. </em>
     * \post Crea un JPEG buit.
     */
    public JPEG() {};
    
    /** @brief Constructora donada una certa qualitat.
     * 
     * @param quality Qualitat de la compressió
     * \pre <em>quality</em> esta entre 0 i 100.
     * \post Crea una nova instància de JPEG amb la qualitat igual a <em>quality</em>.
     */
    public JPEG(int quality) {
        this.quality = quality;
    }
    
    /** @brief Comprimeix una imatge .ppm a un ficher .jpeg utilitzant l'algorisme JPEG de compressió.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'enviarà l'imatge comprimida a la capa de Persistencia a través del controlador de Domini perque la guardi en un fitxer amb la corresponent extensió .jpeg.
     */
    @Override
    public void compress () throws IOException {  
        
        image = new PPM();
        image.RGBtoYCbCr();
        
        int height = image.getHeight();
        int width = image.getWidth();
        
        int checked_height = JPEG_Utils.checkHeight(height);
        int checked_width = JPEG_Utils.checkWidth(width);
        
        CtrlDomain.write(Integer.toBinaryString(height).getBytes());
        CtrlDomain.write(' ');
        CtrlDomain.write(Integer.toBinaryString(width).getBytes());
        CtrlDomain.write(' ');
        CtrlDomain.write(Integer.toBinaryString(quality).getBytes());
        CtrlDomain.write('\n');
        
        double[][] treatedComponent = image.getY();
        int[][] SQY = JPEG_Utils.treatComponent(treatedComponent, checked_height, checked_width, height, width, quality, true);
        
        treatedComponent = image.getCb();
        int[][] SQCb = JPEG_Utils.treatComponent(treatedComponent, checked_height, checked_width, height, width, quality, false);
        
        treatedComponent = image.getCr();
        int[][] SQCr = JPEG_Utils.treatComponent(treatedComponent, checked_height, checked_width, height, width, quality, false);
        
        huffman = new Huffman();
        
        int size = SQY.length;
        for (int i = 0; i < size; i++) {
            CtrlDomain.write((huffman.luminanceCompression(SQY[i])).getBytes());
        }
        CtrlDomain.write('\n');
        
        size = SQCb.length;
        for (int i = 0; i < size; i++) {
            CtrlDomain.write((huffman.chrominanceCompression(SQCb[i])).getBytes());
        }
        CtrlDomain.write('\n');
        
        size = SQCr.length;
        for (int i = 0; i < size; i++) {
            CtrlDomain.write((huffman.chrominanceCompression(SQCr[i])).getBytes());
        }
    }
    
    /** @brief Descomprimeix una ficher .jpeg a una imatge .ppm utilitzant l'algorisme JPEG de descompressió.
     *
     * @throws IOException
     * 
     * \pre <em>Cert</em>
     * \post S'enviarà l'imatge descomprimida a la capa de Persistencia a través del controlador de Domini perque la guardi en un fitxer amb la corresponent extensió .ppm.
     */
    @Override
    public void decompress () throws IOException {
        String measurements, sY, sCb, sCr;
        
        if (this.get_isFile()) {
            CtrlDomain.initializeBufferedReader();

            measurements = CtrlDomain.readLine();
            sY = CtrlDomain.readLine();
            sCb = CtrlDomain.readLine();
            sCr = CtrlDomain.readLine();

            CtrlDomain.closeBufferedReader();
        }
        else {
            measurements = CtrlDomain.readLine();
            sY = CtrlDomain.readLine();
            sCb = CtrlDomain.readLine();
            sCr = CtrlDomain.readLine();
        }
        
        int height, width, comp_quality;
        String[] split_measurements = measurements.split(" ");
        
        height = Integer.parseInt(split_measurements[0], 2);
        width = Integer.parseInt(split_measurements[1], 2);
        comp_quality = Integer.parseInt(split_measurements[2], 2);
        
        this.quality = comp_quality;
        
        int checked_height, checked_width;
        checked_height = JPEG_Utils.checkHeight(height);
        checked_width = JPEG_Utils.checkWidth(width);
        
        ArrayList<int[]> SQY = new ArrayList<>();
        ArrayList<int[]> SQCb = new ArrayList<>();
        ArrayList<int[]> SQCr = new ArrayList<>();
        
        huffman = new Huffman();
        
        int index = 0;
        
        while (index < sY.length()) {
            int[] block = new int[64];
            index = huffman.luminanceDecompression(sY, block, index);
            SQY.add(block);
        }

        index = 0;
        
        while (index < sCb.length()) {
            int[] block = new int[64];
            index = huffman.chrominanceDecompression(sCb, block, index);
            SQCb.add(block);
        }

        index = 0;
        
        while (index < sCr.length()) {
            int[] block = new int[64];
            index = huffman.chrominanceDecompression(sCr, block, index);
            SQCr.add(block);
        }
        
        double[][] Y = JPEG_Utils.untreatComponent(SQY, checked_height, checked_width, quality, true);
        double[][] Cb = JPEG_Utils.untreatComponent(SQCb, checked_height, checked_width, quality, false);
        double[][] Cr = JPEG_Utils.untreatComponent(SQCr, checked_height, checked_width, quality, false);
        
        image = new PPM(Y, Cb, Cr, height, width);
        image.YCbCrtoRGB();
        image.write();
    }
}
