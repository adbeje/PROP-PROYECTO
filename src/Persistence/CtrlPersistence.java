/** @file CtrlPersistence.java
 * @brief Aquest és el controlador de persistencia.
 *
 * Els imports que utilitza són:
 *       - import java.io.*
 *       - import java.util.ArrayList
 *       - import java.util.logging.Level
 *       - java.util.logging.Logger
 *
 * @author Albert Pita Argemí
 */

package Persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador de Persistencia
 */

/** @class CtrlPersistence
 * @brief Aquest és el controlador de persistencia.
 * 
 * @author Albert Pita Argemí
 */
public class CtrlPersistence {
    
    /** @brief Buffer d'entrada per llegir un fitxer o carpeta.*/
    private static BufferedInputStream inBuffer;
    /** @brief Buffer de sortida per escriure a un fitxer.*/
    private static BufferedOutputStream outBuffer;
    /** @brief Buffer especial que utilitzen els algorismes LZ78, LZSS i LZW per poder manejar bits.*/
    private BitBuffer BitBuffer;
    /** @brief Reader útil per poder llegir més ràpidament en certes situacions.*/
    private BufferedReader bufferedReader;
    /** @brief Reader útil per poder llegir més ràpidament les taules de Huffman.*/
    private BufferedReader bufferedReaderHuffman;
    /** @brief Base de dades on es guarden les estadístiques.*/
    private static StatisticsDataBase statisticsDataBase;
    
    /** @brief Constructora per defecte.
     * 
     * \pre <em>Cert.</em>
     * \post Crea una nova instància de CtrlPersistence.
     */
    private CtrlPersistence() {
        statisticsDataBase = new StatisticsDataBase();
        try {
            statisticsDataBase.createDataBase();
        } catch (IOException ex) {
            Logger.getLogger(CtrlPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /** @brief Obté l'ùnica instància del controlador de persistència.
    * 
    * @return Retorna la única instància del controlador de persistència.
    * 
    * \pre <em> Cert. </em>
    * \post Retorna el controlador de persistència.
    */
    public static CtrlPersistence getInstance(){
        return CtrlPersistenceHolder.INSTANCE;
    }
    
    private static class CtrlPersistenceHolder {
        private static final CtrlPersistence INSTANCE = new CtrlPersistence();
    }
    
    /** @brief Obre un fitxer.
     * 
     * Inicialitza el buffer d'entrada i de sortida amb els paràmetres passats.
     * 
     * @param in_path Path d'entrada.
     * @param out_path Path de sortida.
     * @throws IOException
     * 
     * \pre <em> Cert. </em>
     * \post Els atributs privats <em>inBuffer</em> i <em>outBuffer</em> apunten als fitxer d'entrada i sortida.
    */
    public static void openFile(String in_path, String out_path) throws IOException {
        inBuffer = new BufferedInputStream (new FileInputStream (in_path));
        outBuffer = new BufferedOutputStream (new FileOutputStream (out_path));
    }
    
    /** @brief Tanca un fitxer.
     * 
     * Tanca el buffer d'entrada i de sortida.
     * 
     * @throws IOException
     * 
     * \pre <em> Cert. </em>
     * \post Els atributs privats <em>inBuffer</em> i <em>outBuffer</em> es tanquen.
    */
    public static void closeFile() throws IOException {
        inBuffer.close();
        outBuffer.close();
    }
    
    /** @brief Tanca el buffer de sortida.
     * 
     * Tanca el buffer de sortida.
     * 
     * @throws IOException
     * 
     * \pre <em> Cert. </em>
     * \post L'atribut privat <em>outBuffer</em> es tanca.
    */
    public static void closeBufferedOutput() throws IOException {
        outBuffer.close();
    }
    
    /** @brief Llegeix el pròxim byte de dades del <em>inBuffer</em> i el retorna com un integer en un rang de 0 a 255.
     * 
     * @throws IOException
     * @return Retorna el pròxim byte de dades del <em>inBuffer</em> i el retorna com un integer en un rang de 0 a 255.
     * 
     * \pre <em> Cert. </em>
     * \post Es retorna el pròxim byte de dades del <em>inBuffer</em> i el retorna com un integer en un rang de 0 a 255.
    */
    public int read() throws IOException {
        return inBuffer.read();
    }
    
    /** @brief Llegeix un nombre de bytes de dades del <em>inBuffer</em> i els guarda al vector de bytes que se li passa per parametre.
     * 
     * @param b Vector on es guardaran els bytes llegits.
     * @return Retorna el nombre de bytes que s'han pogut llegir.
     * @throws IOException
     * 
     * \pre <em> Cert. </em>
     * \post S'haura llegit del <em>inBuffer</em> el nombre de bytes indicat pel tamany del vector que se li passa si es pot.
    */
    public int read(byte[] b) throws IOException {
        return inBuffer.read(b);
    }
    
    /** @brief Escriu el byte especificat al <em>outBuffer</em>.
     * 
     * @param b Byte que es vol escriure per l'<em>outBuffer</em>.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha escrit pel <em>outBuffer</em> el byte indicat.
     */
    public void write(int b) throws IOException {
        outBuffer.write(b);
    }
    
    /** @brief Escriu <em>b.length</em> bytes del vector passat per parametres al <em>outBuffer</em>.
     * 
     * @param b Dades que es volen escriure per l'<em>outBuffer</em>.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha escrit pel <em>outBuffer</em> les dades indicades.
     */
    public void write(byte[] b) throws IOException {
        outBuffer.write(b);
    }
    
    /** @brief Inicialitza l'atribut privat <em>BitBuffer</em> per comprimir fitxers de text.
     * 
     * Ho fa passant per parametres (<em>outBuffer</em>, null).
     * 
     * \pre <em>Cert.</em>
     * \post S'ha inicialitzat l'atribut privat <em>BitBuffer</em>.
     */
    public void initializeCompressionBitBuffer() {
        BitBuffer = new BitBuffer(outBuffer, null);
    }
    
    /** @brief Inicialitza l'atribut privat <em>BitBuffer</em> per descomprimir fitxers de text.
     * 
     * Ho fa passant per parametres (null, <em>inBuffer</em>).
     * 
     * \pre <em>Cert.</em>
     * \post S'ha inicialitzat l'atribut privat <em>BitBuffer</em>.
     */
    public void initializeDecompressionBitBuffer() {
        BitBuffer = new BitBuffer(null, inBuffer);
    }
    
    /** @brief Crida a la classe BitBuffer perque es vol tancar el <em>BitBuffer</em>.
     * 
     * Depen de si el que es tracta es un fitxer o una carpeta tancara de forma diferent.
     * 
     * @param isFile Indica si el que s'esta tractant es un fitxer o una carpeta.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Es crida a la classe BitBuffer per tancar el <em>BitBuffer</em>.
     */
    public void closeBitBuffer(boolean isFile) throws IOException {
        BitBuffer.close(isFile);
    }
    
    /** @brief Crida a la classe BitBuffer perque es vol llegir de <em>BitBuffer</em>.
     * 
     * @param i Bits que es vol llegir.
     * @return Retorna el valor sencer  de la suma de bits llegits.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Es crida a la classe BitBuffer per llegir <em>i</em> bits del <em>BitBuffer</em>.
     */
    public int readBitBuffer(int i) throws IOException {
        return BitBuffer.read(i);
    }
    
    /** @brief Crida a la classe BitBuffer perque es vol escriure a <em>BitBuffer</em>.
     * 
     * @param s Cadena binaria a passar a binari.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Es crida a la classe BitBuffer per escriure <em>s</em> bits al <em>BitBuffer</em>.
     */
    public void writeBitBuffer(String s) throws IOException {
        BitBuffer.write(s);
    }
    
    /** @brief Crida a la classe BitBuffer perque es vol escriure a <em>BitBuffer</em>.
     * 
     * @param i objecte que volem passar a binari.
     * @param p quants bits tindrà el objecte a representar en binari.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Es crida a la classe BitBuffer perque es vol escriure a <em>BitBuffer</em>.
     */
    public void writeBitBuffer(int i, int p) throws IOException {
        BitBuffer.write(i, p);
    }
    
    /** @brief Llegeix un integer del <em>inBuffer</em>.
     * 
     * @return Retorna un integer.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Retorna el integer llegit del <em>inBuffer</em>.
     */
    public int readAsciiInt() throws IOException {
        char c = readAsciiChar();
        if ((c < '0') || ('9' < c)) throw new IOException (getClass().getName() + ".readAsciiInt: Expected Ascii Integer");
        
        int i = 0;
        do {
            i = i * 10 + c - '0';
            c = (char) inBuffer.read();
        } while (('0' <= c) && (c <= '9'));
        
        return i; 
    }
    
    /** @brief Llegeix un caràcter del <em>inBuffer</em>.
     * 
     * @return Retorna un caràcter.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Retorna un caràcter llegit del <em>inBuffer</em>.
     */
    private char readAsciiChar () throws IOException {
        char c;
        do {
            c = (char) inBuffer.read();
            if (c == '#') {
                do {
                    c = (char) inBuffer.read();
                } while ((c != '\n') && (c != '\r'));
            }
        } while ((c == ' ') || (c == '\t') || (c == '\n') || (c == '\r'));
        
        return c;
    }
    
    /** @brief Inicialitza l'atribut privat <em>bufferedReader</em> apuntant a un fitxer amb un cert path donat.
     * 
     * @param path Path del fitxer que es vol llegir.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha inicialitzat l'atribut privat <em>bufferedReader</em> apuntant al fitxer amb path <em>path</em>.
     */
    public void initializeBufferedReader(String path) throws IOException {
        bufferedReader = new BufferedReader (new FileReader (path));
    }
    
    /** @brief Inicialitza l'atribut privat <em>bufferedReaderHuffman</em> apuntant a un fitxer amb un cert path donat.
     * 
     * @param path Path del fitxer que es vol llegir.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha inicialitzat l'atribut privat <em>bufferedReaderHuffman</em> apuntant al fitxer amb path <em>path</em>.
     */
    public void initializeBufferedReaderHuffman(String path) throws IOException {
        bufferedReaderHuffman = new BufferedReader (new FileReader (path));
    }
    
    /** @brief Inicialitza l'atribut privat <em>bufferedReader</em> sobre l'<em>inBuffer</em>.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha inicialitzat l'atribut privat <em>bufferedReader</em> sobre l'<em>inBuffer</em>.
     */
    public void initializeBufferedReader() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(inBuffer));
    }
    
    /** @brief Es tanca l'atribut privat <em>bufferedReader</em>.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha tancat l'atribut privat <em>bufferedReader</em>.
     */
    public void closeBufferedReader() throws IOException {
        bufferedReader.close();
    }
    
    /** @brief Es tanca l'atribut privat <em>bufferedReaderHuffman</em>.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha tancat l'atribut privat <em>bufferedReaderHuffman</em>.
     */
    public void closeBufferedReaderHuffman() throws IOException {
        bufferedReaderHuffman.close();
    }    
    
    /** @brief Es llegeix un linea de l'atribut privat <em>bufferedReader</em>.
     * 
     * @return Retorna una linea de text.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha llegeix i retornat un linea de l'atribut privat <em>bufferedReader</em>.
     */
    public String readLine() throws IOException {
        return bufferedReader.readLine();
    }
    
    /** @brief Es llegeix un linea de l'atribut privat <em>bufferedReaderHuffman</em>.
     * 
     * @return Retorna una linea de text.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha llegeix i retornat un linea de l'atribut privat <em>bufferedReaderHuffman</em>.
     */
    public String readLineHuffman() throws IOException {
        return bufferedReaderHuffman.readLine();
    }
    
    /** @brief Retorna el tamany d'un cert fitxer amb path <em>path</em>.
     * 
     * @param path Path del fitxer del que volem saber el seu tamany.
     * @return Retorna el tamany del fitxer.
     * 
     * \pre <em>Cert.</em>
     * \post Es retorna el tamany del fitxer.
     */
    public static double get_fileLength(String path) {
        File file = new File(path);
        double fileLenght =  ((double) file.length()) * 100;
        return fileLenght;
    }
    
    /** @brief Guarda a la base de dades d'estadístiques les <em>statistics</em>.
     * 
     * @param statistics Estadístiques que s'han de guardar.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Es crida a la base de dades perque guardi les <em>statistics</em>.
     */
    public static void saveStatistics(String statistics) throws IOException{
        statisticsDataBase.saveStatistics(statistics);
    }
    
    /** @brief Obté tot el contingut que tingui la base de dades d'estadístiques.
     * 
     * @return Retorna una ArrayList amb tot el contingut de la base de dades de d'estadístiques.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Es crida a la base de dades per obtenir totes les estadístiques fins al moment.
     */
    public ArrayList<String> getTableContent() throws IOException {
        return statisticsDataBase.getTableContent();
    }
    
    /** @brief Borra tot el contingut que tingui la base de dades d'estadístiques.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Es crida a la base de dades per borrar totes les estadístiques fins al moment.
     */
    public void deleteTableContent() throws IOException {
        statisticsDataBase.deleteTableContent();
    }
    
    /** @brief Obté tot el text d'un fitxer amb path <em>path</em>.
     * 
     * @param path Path del fitxer que es vol llegir.
     * @return Retorna tot el text del fitxer.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Ha retornat tot el text d'un fitxer amb path <em>path</em>.
     */
    public String getText(String path) throws IOException {
        StringBuilder sB = new StringBuilder(512);
        try (Reader r = new InputStreamReader(new FileInputStream(new File(path)))) {
            int c = 0;
            while ((c = r.read()) != -1) {
                sB.append((char) c);
            }
        }
        return sB.toString();
    }
    
    /** @brief Obté tot el contingut d'una imatge amb path <em>paht</em>.
     * 
     * @param path Path de la imatge que es vol llegir.
     * @return Retorna un ArrayList de la qual els dos primers valors son el tamany de l'imatge i la resta son els pixels d'aquesta.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Ha retornat un ArrayList de la qual els dos primers valors son el tamany de l'imatge i la resta son els pixels d'aquesta.
     */
    public ArrayList<Integer> getImage(String path) throws IOException {
        ArrayList<Integer> image = new ArrayList<>();
        
        inBuffer = new BufferedInputStream (new FileInputStream (path));
        
        inBuffer.read(new byte[2]);
        int width = readAsciiInt();
        int height = readAsciiInt();
        readAsciiInt();

        image.add(width);
        image.add(height);
        
        int r, g, b;
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                r = inBuffer.read();
                image.add(r);
                g = inBuffer.read();
                image.add(g);
                b = inBuffer.read();
                image.add(b);
            }
        }
        
        inBuffer.close();
        return image;
    }
    
    /** @brief Elimina un fitxer amb path <em>path</em>.
     * 
     * @param path Path del fitxer que es vol eliminar.
     * 
     * \pre <em>Cert.</em>
     * \post S'ha eliminat el fitxer amb path <em>path</em>.
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        file.delete();
    }
    
    /** @brief Fa que <em>inBuffer</em> apunti al fitxer amb path <em>in_path</em>.
     * 
     * @param in_path Path del fitxer del que es vol llegir.
     * @throws FileNotFoundException
     * 
     * \pre <em>Cert.</em>
     * \post <em>inBuffer</em> apunta al fitxer.
     */
    public static void set_inBuffer(String in_path) throws FileNotFoundException {
        inBuffer = new BufferedInputStream (new FileInputStream (in_path));
    }
    
    /** @brief Fa que <em>outBuffer</em> apunti al fitxer amb path <em>out_path</em>.
     * 
     * @param out_path Path del fitxer al que es vol escriure.
     * @throws FileNotFoundException
     * 
     * \pre <em>Cert.</em>
     * \post <em>outBuffer</em> apunta al fitxer.
     */
    public static void set_outBuffer(String out_path) throws FileNotFoundException {
        outBuffer = new BufferedOutputStream (new FileOutputStream (out_path));
    }
    
    /** @brief Fa que <em>outBuffer</em> sigui buidat.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post <em>outBuffer</em> es buidat.
     */
    public static void flush_outBuffer() throws IOException {
        outBuffer.flush();
    }
    
    /** @brief Es llegeix un linea del <em>inBuffer</em>.
     * 
     * @return Retorna una linea de text.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha llegeix i retornat un linea del <em>inBuffer</em>.
     */
    public static String readLine_inBuffer() throws IOException{
        String res = "";
        int x;
        char c;
        while((x = inBuffer.read()) != 10){
            c = (char) x;
            res += c;
        }
        return res;
    }
    
    /** @brief Indica si es un fitxer o no.
     * 
     * @param in_path Path del fitxer que es vol comprovar.
     * @return Boolea que indica si es un fitxer o no.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna un boolea que indica si es un fitxer o no.
     */
    public static boolean isFile(String in_path){
        File f = new File(in_path);
        return f.isFile();
    }
    
    /** @brief Indica si es un directori o no.
     * 
     * @param in_path Path del directori que es vol comprovar.
     * @return Boolea que indica si es un directori o no.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna un boolea que indica si es un directori o no.
     */
    public static boolean isDirectory(String in_path){
        File f = new File(in_path);
        return f.isDirectory();
    }
    
    /** @brief Obté el pare del objecte fitxer donat.
     * 
     * @param in_path Path del fitxer del qual es vol obtenir el seu pare.
     * @return Retorna una String el valor el qual es el pare del fitxer objecte donat.
     * 
     * \pre <em>Cert.</em>
     * \post Ha retornat una String el valor el qual es el pare del fitxer objecte donat.
     */
    public static String getFileParent(String in_path){
        File f = new File(in_path);
        return f.getParent();
    }
    
    /** @brief Obté el path absolut del fitxer que te per path <em>path</em>.
     * 
     * @param in_path Path del fitxer del qual es vol obtenir el seu path absolut.
     * @return Retorna una String amb el path absolut del fitxer que te per path <em>path</em>.
     * 
     * \pre <em>Cert.</em>
     * \post Ha retornat una String amb el path absolut del fitxer que te per path <em>path</em>.
     */
    public static String getFileAbsolutePath(String in_path){
        File f = new File(in_path);
        return f.getAbsolutePath();
    }
    
    /** @brief Crea un nou directori amb path <em>path</em>.
     * 
     * @param path Path del nou directori.
     * 
     * \pre <em>Cert.</em>
     * \post S'ha creat un nou directori amb path <em>path</em>.
     */
    public static void makeDirectory(String path) {
        File f = new File(path);
        f.mkdirs();
    }
    
    /** @brief Crea un nou fitxer amb path <em>path</em>.
     * 
     * @param path Path del nou fitxer.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha creat un nou fitxer amb path <em>path</em>.
     */
    public static void makeFile(String path) throws IOException {
        File f = new File(path);
        f.createNewFile();
    }
    
    /** @brief Agafa tots els paths dels fitxers de text que tingui la carpeta amb path <em>in_path</em>. Incloent els que estan dins d'altres carpetes.
     * 
     * @param in_path Path de la carpeta de la qual volem obtenir tots paths dels fitxers de text.
     * @return Retorna un ArrayList amb tots els paths dels fitxers de text que hi hagin.
     * 
     * \pre <em>Cert.</em>
     * \post S'han retornat tots els paths dels fitxers de text que hi hagin a la carpeta amb path <em>in_path</em> i als possibles subdirectoris d'aquesta.
     */
    public static ArrayList<String> listTextFiles (String in_path) {
        ArrayList<String> filepaths = new ArrayList<>();
        File f = new File(in_path);
        
        File[] textFiles = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                String name = file.getAbsolutePath();
                return name.endsWith(".txt");
            }
        });
        
        if (textFiles != null) {
            for (File fI : textFiles) {
                filepaths.add(fI.getAbsolutePath());
            }
        }
        
        File[] foldersFiles = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        
        if (foldersFiles != null) {
            for (File fF : foldersFiles) {
                ArrayList<String> folderTextFiles = listTextFiles(fF.getAbsolutePath());
                for(String textPath: folderTextFiles){
                    filepaths.add(textPath);
                }
            }
        }
        return filepaths;
    }
    
    /** @brief Agafa tots els paths de les imatges que tingui la carpeta amb path <em>in_path</em>. Incloent les que estan dins d'altres carpetes.
     * 
     * @param in_path Path de la carpeta de la qual volem obtenir tots els paths de les imatges.
     * @return Retorna un ArrayList amb tots els paths de les imatges que hi hagin.
     * 
     * \pre <em>Cert.</em>
     * \post S'han retornat tots els paths de les imatges que hi hagin a la carpeta amb path <em>in_path</em> i als possibles subdirectoris d'aquesta.
     */
    public static ArrayList<String> listImageFiles (String in_path) {
        ArrayList<String> imagepaths = new ArrayList<>();
        File f = new File(in_path);
        
        File[] imageFiles = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                String name = file.getAbsolutePath();
                return name.endsWith(".ppm");
            }
        });
        
        
        if (imageFiles != null) {
            for (File fI : imageFiles) {
                imagepaths.add(fI.getAbsolutePath());
            }
        }
        
        File[] foldersFiles = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        
        
        if (foldersFiles != null) {
            for (File fF : foldersFiles) {
                ArrayList<String> folderImagesFiles = listImageFiles(fF.getAbsolutePath());
                for(String imagePath: folderImagesFiles){
                    imagepaths.add(imagePath);
                }
            }
        }
        return imagepaths;
    }
    
    /** @brief Agafa tots els paths dels directoris que tingui la carpeta amb path <em>in_path</em>. Incloent-se a si mateix i els que estan dins d'altres carpetes.
     * 
     * @param in_path Path de la carpeta de la qual volem obtenir tots els paths dels directoris que hi hagin.
     * @return Retorna un ArrayList amb tots els paths dels directoris, incloent l'actual, que hi hagin.
     * 
     * \pre <em>Cert.</em>
     * \post S'han retornat tots els paths dels directoris que hi hagin a la carpeta amb path <em>in_path</em>, incloent aquesta, i als possibles subdirectoris d'aquesta.
     */
    public static ArrayList<String> listFolders (String in_path) {
        ArrayList<String> folderspaths = new ArrayList<>();
        File f = new File(in_path);
        
        if (f.isDirectory()) folderspaths.add(in_path);
        
        File[] foldersFiles = f.listFiles();
        if (foldersFiles != null) {
            for (File fF : foldersFiles) {
                ArrayList<String> folderFoldersFiles = listFolders(fF.getAbsolutePath());
                for(String folderPath: folderFoldersFiles){
                    folderspaths.add(folderPath);
                }
            }
        }
        return folderspaths;
    }
}
