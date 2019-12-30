/** @file PPM.java
 * @brief Classe que representa una imatge PPM.
 *
 * Els imports que utilitza són:
 *     - import java.io.*
 *
 * @author Albert Pita Argemí
 */

package Domain;

import java.io.*;

/*
 * Classe PPM
 */

/** @class PPM
 * @brief Classe que representa una imatge PPM.
 * 
 * Aquesta classe representa una imatge PPM amb les respectives operacions que es poden aplicar en ella.
 * 
 * @author Albert Pita Argemí
 */
public class PPM {
    /** @brief Nombres magics que diuen el tipus de PPM que és*/
    private byte[] magic_number;
    /** @brief Altura*/
    private int height;
    /** @brief Amplada*/
    private int width;
    /** @brief Valor màxim*/
    private int maxVal;
    
    //YCbCr
    /** @brief Component Y*/
    private double[][] Y;
    /** @brief Component Cb*/
    private double[][] Cb;
    /** @brief Component Cr*/
    private double[][] Cr;
    
    //RGB
    /** @brief Component R*/
    private int[][] R;
    /** @brief Component G*/
    private int[][] G;
    /** @brief Component B*/
    private int[][] B;
    
    /** @brief Constructora per defecte.
     * 
     * Llegeix de <em>inBuffer</em> la imatge.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Crea un nova instància de PPM inicialitzant els components R, G, B, maxVal, height, width i magic_number.
     */ 
    public PPM () throws IOException {
        readHeader();
        readContent();
    }
    
    /** @brief Constructora que incialitza la imatge amb uns certs valors de Y, Cb, Cr, height i width.
     *  
     * @param y Component Y.
     * @param cb Component Cb.
     * @param cr Component Cr.
     * @param i_height Altura de l'imatge.
     * @param i_width Amplada de l'imatge.
     * 
     * \pre <em>Cert.</em>
     * \post Crea un nova instància de PPM inicialitzant els components Y, Cb, Cr, height i width amb els valors donats, i maxVal i magic_number amb els per defecte.
     */ 
    public PPM (double[][] y, double[][] cb, double[][] cr, int i_height, int i_width) {
        Y = y;
        Cb = cb;
        Cr = cr;
        height = i_height;
        width = i_width;
        maxVal = 255;
        magic_number = new byte[2];
        magic_number[0] = (byte) 'P';
        magic_number[1] = (byte) '6';
    }
    
    /** @brief Llegeix la capçalera de l'imatge.
     *  
     * @param inBuffer
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Els atributs magic_number, width, height i maxVal quedan inicialitzats.
     */ 
    private void readHeader () throws IOException {
        magic_number = new byte[2];
        CtrlDomain.read(magic_number);
        if (magic_number[0] != (byte)'P') throw new IOException (getClass().getName() + ".readHeader: Invalid .ppm file");
        
        width = CtrlDomain.readAsciiInt();
        height = CtrlDomain.readAsciiInt();
        
        int auxMaxVal = CtrlDomain.readAsciiInt();
        if (auxMaxVal > 255 || auxMaxVal < 0) throw new IOException (getClass().getName() + ".readHeader: Invalid .ppm file, maximum value not in range");
        else maxVal = auxMaxVal;
    }
    
    /** @brief Llegeix les components RGB de l'imatge.
     *  
     * @param inBuffer
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Els atributs R, G, B quedan inicialitzats.
     */ 
    private void readContent() throws IOException {
        R = new int[height][width];
        G = new int[height][width];
        B = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                R[i][j] = CtrlDomain.read();
                G[i][j] = CtrlDomain.read();
                B[i][j] = CtrlDomain.read();
            }
        }
    }
    
    /** @brief Transforma les components RGB a YCbCr.
     *  
     * \pre <em>Cert.</em>
     * \post Les component RGB han estat transformades i guardades a YCbCr respectivament.
     */
    public void RGBtoYCbCr() {
        Y = new double[height][width];
        Cb = new double[height][width];
        Cr = new double[height][width];
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Y[i][j] = 0.299 * R[i][j] + 0.587 * G[i][j] + 0.114 * B[i][j];
                Cb[i][j] = -0.1687 * R[i][j] - 0.3313 * G[i][j] + 0.5 * B[i][j] + 128;
                Cr[i][j] = 0.5 * R[i][j] - 0.4187 * G[i][j] - 0.0813 * B[i][j] + 128;
            }
        }
    }
    
    /** @brief Transforma les components YCbCr a RGB.
     *  
     * \pre <em>Cert.</em>
     * \post Les component YCbCr han estat transformades i guardades a RGB respectivament.
     */
    public void YCbCrtoRGB() {  
        R = new int[height][width];
        G = new int[height][width];
        B = new int[height][width];
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                R[i][j] = (int) (Y[i][j] + 1.402*(Cr[i][j] - 128));
                G[i][j] = (int) (Y[i][j] - 0.34414*(Cb[i][j] - 128) - 0.71414*(Cr[i][j]-128));
                B[i][j] = (int) (Y[i][j] + 1.772*(Cb[i][j] - 128));
                
                if (R[i][j] > 255) R[i][j] = 255;
                if (R[i][j] < 0) R[i][j] = 0;
                if (G[i][j] > 255) G[i][j] = 255;
                if (G[i][j] < 0) G[i][j] = 0;
                if (B[i][j] > 255) B[i][j] = 255;
                if (B[i][j] < 0) B[i][j] = 0;
                
            }
        }
    }
    
    /** @brief Escriu l'imatge.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post L'imatge ha estat escrita a través del Controlador de Domini que ho envia a la capa de Persistencia.
     */
    public void write() throws IOException {
        
        CtrlDomain.write('P');
        CtrlDomain.write('6');
        CtrlDomain.write('\n');
        CtrlDomain.write(String.valueOf(width).getBytes());
        CtrlDomain.write(' ');
        CtrlDomain.write(String.valueOf(height).getBytes());
        CtrlDomain.write('\n');
        CtrlDomain.write(String.valueOf(255).getBytes()); 
        CtrlDomain.write('\n');
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                CtrlDomain.write(R[i][j]);
                CtrlDomain.write(G[i][j]);
                CtrlDomain.write(B[i][j]);
            }
        }
    }
    
    //Getters
    
    /** @brief Afaga l'altura.
     * 
     * @return int height
     * 
     * \pre <em>Cert.</em>
     * \post Retorna l'altura.
     */
    public int getHeight() {
        return height;
    }
    
    /** @brief Afaga l'amplada.
     * 
     * @return int width
     * 
     * \pre <em>Cert.</em>
     * \post Retorna l'amplada.
     */
    public int getWidth() {
        return width;
    }
    
    /** @brief Afaga la component Y.
     * 
     * @return Matriu de doubles de la component Y.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna Y.
     */
    public double[][] getY() {
        return Y.clone();
    }
    
    /** @brief Afaga la component Cb.
     * 
     * @return Matriu de doubles de la component Cb.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna Cb.
     */
    public double[][] getCb() {
        return Cb.clone();
    }
    
    /** @brief Afaga la component Cr.
     * 
     * @return Matriu de doubles de la component Cr.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna Cr.
     */
    public double[][] getCr() {
        return Cr.clone();
    }
    
    /** @brief Afaga la component R.
     * 
     * @return Matriu de integers de la component R.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna R.
     */
    public int[][] getR() {
        return R.clone();
    }
    
    /** @brief Afaga la component G.
     * 
     * @return Matriu de integers de la component G.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna G.
     */
    public int[][] getG() {
        return G.clone();
    }
    
    /** @brief Afaga la component B.
     * 
     * @return Matriu de integers de la component B.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna B.
     */
    public int[][] getB() {
        return B.clone();
    } 
}
