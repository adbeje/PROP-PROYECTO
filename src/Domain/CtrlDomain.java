/** @file CtrlDomain.java
 * @brief Aquest és el controlador de domini.
 *
 * Els imports que utilitza són:
 *       - import Persistence.CtrlPersistence
 *       - import java.io.IOException
 *       - import java.awt.image.BufferedImage
 *       - import java.nio.file.Path
 *       - import java.nio.file.Paths
 *       - java.util.ArrayList
 *
 * @author Albert Pita Argemí
 */

package Domain;

import Persistence.CtrlPersistence;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/*
 * Controlador de Domini
 */

/** @class CtrlDomain
 * @brief Aquest és el controlador de domini.
 *
 * @author Albert Pita Argemí
 */
public class CtrlDomain {
    
    /** @brief Instància del controlador de persistencia, inicialitzada al crear el controlador de domini. */
    private static CtrlPersistence ctrlPersistence;
    /** @brief Atribut que fa referencia als algorismes de compressió i descompressió.*/
    private Algorithm algorithm;

    /** @brief Atribut que fa referencia al temps total que ha tardat un algorisme en comprimir o descomprimir. */
    private String totalTime;
    /** @brief Atribut que fa referencia al rati de compressió d'una compressió. */
    private String compressedRatio;
    /** @brief Atribut que fa referencia al path de sortida d'un fitxer després d'una compressió o descompressió.*/
    private String out_path;

    /** @brief Constructora per defecte.
    * 
    * \pre <em> Cert. </em>
    * \post Crea un nova instància de CtrlDomain.
    */  
    private CtrlDomain() {
        ctrlPersistence = CtrlPersistence.getInstance();
    }
    
    /** @brief Obté l'ùnica instància del controlador de domini.
    * 
    * @return Retorna la única instància del controlador de domini.
    * 
    * \pre <em> Cert. </em>
    * \post Retorna el controlador de domini.
    */  
    public static CtrlDomain getInstance() {
        return CtrlDomainHolder.INSTANCE;
    }
    
    private static class CtrlDomainHolder {

        private static final CtrlDomain INSTANCE = new CtrlDomain();
    }
    
    /** @brief Comprimeix fitxers automàticament amb les millors opcions ja siguin imatges o textos.
     * 
     * @param in_path Path del fitxer que volem comprimir.
     * @param out_path Path del lloc on vole guardar el resultat de la compressió del fitxer d'entrada.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Al fitxer que te per path <em>out_path</em> conté el contingut del fitxer que té per path <em>in_path</em> comprimit.
     */
    public void compressFile (String in_path, String out_path) throws IOException {
        long startTime = System.nanoTime();
        
        String algorithmType = getType(in_path);
        
        switch (algorithmType) {
            case "PPM" :
                algorithm = new JPEG(50);
                
                out_path += ".jpeg";
                algorithmType = "JPEG";
                ctrlPersistence.openFile(in_path, out_path);
                
                algorithm.set_isFile(true);
                algorithm.compress();
                break;
                
            case "TXT" :
                algorithm = new LZSS(12,5,3);
                
                out_path += ".lzss";
                algorithmType = "LZSS";
                ctrlPersistence.openFile(in_path, out_path);
                
                algorithm.set_isFile(true);
                algorithm.compress();
                break;
                
        }
        
        this.out_path = out_path;
        
        ctrlPersistence.closeFile();
        
        long finishTime = System.nanoTime();
        long Time = finishTime - startTime;
        totalTime = Float.toString(Math.round((Time/1000000000f)* 100) / 100f)+"s";
        compressedRatio = Double.toString(Math.round(get_ratio(in_path,out_path) * 100) / 100d)+"%";
        
        int indexName = out_path.lastIndexOf('/');
        String fileName = out_path.substring(indexName + 1);
        
        String stadistics = algorithmType + " Compressió " + totalTime + " " + compressedRatio + " " + fileName;
        ctrlPersistence.saveStatistics(stadistics);
        
    }
    
    /** @brief Comprimeix fitxers amb un cert algorisme donat.
     * 
     * @param in_path Path del fitxer que volem comprimir.
     * @param out_path Path del lloc on volem guardar el resultat de la compressió del fitxer d'entrada.
     * @param algorithmType Algorisme que volem utilitzar per la compressió del fitxer.
     * 
     * @throws IOException
     * 
     * \pre L'algorisme passat per parametres es adient pel tipus de fitxer.
     * \post Al fitxer que te per path <em>out_path</em> conté el contingut del fitxer que té per path <em>in_path</em> comprimit amb l'algorisme <em>algorithmType</em>.
     */
    public void compressFile (String in_path, String out_path, String algorithmType) throws IOException {
        long startTime = System.nanoTime();
        
        switch (algorithmType) {
            case "JPEG" :
                algorithm = new JPEG(50);
                
                out_path += ".jpeg";
                ctrlPersistence.openFile(in_path, out_path);
                
                algorithm.set_isFile(true);
                algorithm.compress();
                break;
                
            case "LZ78" :
                algorithm = new LZ78();
                
                out_path += ".lz78";
                ctrlPersistence.openFile(in_path, out_path);
                
                algorithm.set_isFile(true);
                algorithm.compress();
                break; 
                
            case "LZSS" :
                algorithm = new LZSS(12,5,3);
                
                out_path += ".lzss";
                ctrlPersistence.openFile(in_path, out_path);
  
                algorithm.set_isFile(true);
                algorithm.compress();
                break;
            
            case "LZW" :
                algorithm = new LZW();
                
                out_path += ".lzw";
                ctrlPersistence.openFile(in_path, out_path);
                
                algorithm.set_isFile(true);
                algorithm.compress();
                break;
        }
        
        this.out_path = out_path;
        
        ctrlPersistence.closeFile();
        
        long finishTime = System.nanoTime();
        long Time = finishTime - startTime;
        totalTime = Float.toString(Math.round((Time/1000000000f)* 100) / 100f)+"s";
        compressedRatio = Double.toString(Math.round(get_ratio(in_path,out_path) * 100) / 100d)+"%";
        
        int indexName = out_path.lastIndexOf('/');
        String fileName = out_path.substring(indexName + 1);
        
        String stadistics = algorithmType + " Compressió " + totalTime + " " + compressedRatio + " " + fileName;
        ctrlPersistence.saveStatistics(stadistics);
    }
    
    /** @brief Comprimeix fitxers donades unes certs opcions.
     * 
     * @param in_path Path del fitxer que volem comprimir.
     * @param out_path Path del lloc on es vol guardar el resultat de la compressió del fitxer d'entrada.
     * @param algorithmType Algorisme que volem utilitzar per la compressió del fitxer.
     * @param Options Opcions que l'usuari a triat per variar el resultat o rendiment de la compressió.
     * 
     * @throws IOException
     * 
     * \pre L'algorisme passat per parametres es adient pel tipus de fitxer al igual que les opcions.
     * \post Al fitxer que te per path <em>out_path</em> conté el contingut del fitxer que té per path <em>in_path</em> comprimit amb les opcions donades i l'algorisme triat.
     */
    public void compressFile (String in_path, String out_path, String algorithmType, String Options) throws IOException{
        long startTime = System.nanoTime();
        
        String[] compressionOptions = Options.split(" ");
        
        switch (algorithmType) {
            case "JPEG" :
                int quality = Integer.parseInt(compressionOptions[0]);
                algorithm = new JPEG(quality);
                
                out_path += ".jpeg";
                ctrlPersistence.openFile(in_path, out_path);
                
                algorithm.set_isFile(true);
                algorithm.compress();
                break;
                
            case "LZ78" :
                algorithm = new LZ78();
                
                out_path += ".lz78";
                ctrlPersistence.openFile(in_path, out_path);
                
                algorithm.set_isFile(true);
                algorithm.compress();
                break; 
                
            case "LZSS" :
                int winSize = (int)(Math.log(Integer.parseInt(compressionOptions[0]))/Math.log(2)+1e-10);
                int maxLen = (int)(Math.log(Integer.parseInt(compressionOptions[1]))/Math.log(2)+1e-10);
                int minLen = Integer.parseInt(compressionOptions[2]);
                
                algorithm = new LZSS(winSize, maxLen, minLen);
                
                out_path += ".lzss";
                ctrlPersistence.openFile(in_path, out_path);
                
                algorithm.set_isFile(true);
                algorithm.compress();
                break;
                
            case "LZW" :
                algorithm = new LZW();
                
                out_path += ".lzw";
                ctrlPersistence.openFile(in_path, out_path);
                
                algorithm.set_isFile(true);
                algorithm.compress();
                break;
        }
        
        this.out_path = out_path;
        
        
        ctrlPersistence.closeFile();
        
        long finishTime = System.nanoTime();
        long Time = finishTime - startTime;
        totalTime = Float.toString(Math.round((Time/1000000000f)* 100) / 100f)+"s";
        compressedRatio = Double.toString(Math.round(get_ratio(in_path,out_path) * 100) / 100d)+"%";
        
        int indexName = out_path.lastIndexOf('/');
        String fileName = out_path.substring(indexName + 1);
        
        String stadistics = algorithmType + " Compressió " + totalTime + " " + compressedRatio + " " + fileName;
        ctrlPersistence.saveStatistics(stadistics);
    }
    
    /** @brief Descomprimeix fitxers.
     * 
     * @param in_path Path del fitxer que volem descomprimir.
     * @param out_path Path on volem que es guardi la descompressió del fitxer d'entrada.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Al fitxer que te per path <em>out_path</em> conté el contingut del fitxer que té per path <em>in_path</em> descomprimit.
     */
    public void decompressFile (String in_path, String out_path) throws IOException {
        long startTime = System.nanoTime();
        
        String algorithmType = getType(in_path);
        
        switch (algorithmType) {
            case "JPEG" :
                algorithm = new JPEG();
                
                out_path += ".ppm";
                ctrlPersistence.openFile(in_path, out_path);
                
                algorithm.set_isFile(true);
                algorithm.decompress();
                break;
                
            case "LZ78" :
                algorithm = new LZ78();
                
                out_path += ".txt";
                ctrlPersistence.openFile(in_path, out_path);
      
                algorithm.set_isFile(true);
                algorithm.decompress();
                break;
                
            case "LZSS" :
                algorithm = new LZSS(12,5,3);
                
                out_path += ".txt";
                ctrlPersistence.openFile(in_path, out_path);
                
                algorithm.set_isFile(true);
                algorithm.decompress();
                break;
                
            case "LZW" :
                algorithm = new LZW();
                
                out_path += ".txt";
                ctrlPersistence.openFile(in_path, out_path);
                
                algorithm.set_isFile(true);
                algorithm.decompress();
                break;
        }
        
        this.out_path = out_path;
              
        ctrlPersistence.closeFile();
            
        long finishTime = System.nanoTime();
        long Time = finishTime - startTime;
        totalTime = Float.toString(Math.round((Time/1000000000f)* 100) / 100f)+"s";
        
        int indexName = out_path.lastIndexOf('/');
        String fileName = out_path.substring(indexName + 1);
        
        String stadistics = algorithmType + " Descompressió " + totalTime + " - " + fileName;
        ctrlPersistence.saveStatistics(stadistics);
    }
    
    /** @brief Crida al controlador de persistencia perque escrigui els paths de les carpetes que hi ha dins de la carpeta que volem comprimir.
     * 
     * @param folderList Llista de paths de les carpetes que hi ha dins de la carpeta que es vol comprimir.
     * @param base_path Path base.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha escrit al fitxer que representa la carpeta comprimida tots els paths de les possibles carpetes que hi ha dins.
     */
    public void writeFolders(ArrayList<String> folderList, Path base_path) throws IOException {
        for (String folderPath : folderList) {
            ctrlPersistence.write("0".getBytes());
            Path carpath = Paths.get(ctrlPersistence.getFileAbsolutePath(folderPath));
            ctrlPersistence.write(base_path.relativize(carpath).toString().getBytes());
            ctrlPersistence.write("\n".getBytes()); 
        }
        
    }
    
    /** @brief Crida al controlador de persistencia perque escrigui els paths de els arxius de text que hi ha dins de la carpeta que volem comprimir i els comprimeixi.
     * 
     * @param textList Llista de paths dels fitxers de text que hi ha dins de la carpeta que es vol comprimir.
     * @param base_path Path base.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha escrit al fitxer que representa la carpeta comprimida tots els paths dels fitxers de text que hi ha dins d'aquesta, amb la respectiva compressió del seu contingut.
     */
    public void writeTextFiles(ArrayList<String> textList, Path base_path) throws IOException {
        Path arcpath;
        
        for (String textPath : textList) {
            arcpath = Paths.get(ctrlPersistence.getFileAbsolutePath(textPath));
            
            ctrlPersistence.set_inBuffer(arcpath.toString());
            ctrlPersistence.write("1".getBytes());
            ctrlPersistence.write(base_path.relativize(arcpath).toString().getBytes());
            ctrlPersistence.write("\n".getBytes());
            
            algorithm = new LZW();
                        
            algorithm.set_isFile(false);
            algorithm.compress();
        }
    }
    
    /** @brief Crida al controlador de persistencia perque escrigui els paths de les imatges que hi ha dins de la carpeta que volem comprimir i les comprimeixi.
     * 
     * @param imageList Llista de paths de les imatges de text que hi ha dins de la carpeta que es vol comprimir.
     * @param base_path Path base.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha escrit al fitxer que representa la carpeta comprimida tots els paths de les imatges que hi ha dins d'aquesta, amb la respectiva compressió del seu contingut.
     */
    public void writeImageFiles(ArrayList<String> imageList, Path base_path) throws IOException {
        
        Path arcpath;
        
        for (String imagePath : imageList) {
            arcpath = Paths.get(ctrlPersistence.getFileAbsolutePath(imagePath));
            
            ctrlPersistence.set_inBuffer(arcpath.toString());
            ctrlPersistence.write("1".getBytes());
            ctrlPersistence.write(base_path.relativize(arcpath).toString().getBytes());
            ctrlPersistence.write("\n".getBytes());
            
            algorithm = new JPEG(50);
                        
            algorithm.set_isFile(false);
            algorithm.compress();
            ctrlPersistence.write("\n".getBytes());
        }
    }
    
    /** @brief Comprimeix carpetes.
     * 
     * Funciona de la següent manera. Inicialment obté tots els paths de totes les possibles carpetes que hi han dins de la carpeta que es vol comprimir.
     * Després fa el mateix amb els fitxers de text i finalment amb les imatges. Obviament en el cas que siguin text o imatges escriu també el seu contingut comprés.
     * 
     * @param in_path Path de la carpeta que es vol comprimir.
     * @param out_path Path del fitxer on es guarden totes les compressions.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Al fitxer que te per path <em>out_path</em> conté el contingut de la carpeta comprimida que té per path <em>in_path</em>.
     */
    public void compressFolder (String in_path, String out_path) throws IOException {
        long startTime = System.nanoTime();
        
        Path base_path = Paths.get(ctrlPersistence.getFileParent(in_path));
        ctrlPersistence.set_outBuffer(out_path);
        
        ArrayList<String> folderList = ctrlPersistence.listFolders(in_path);
        ArrayList<String> textList = ctrlPersistence.listTextFiles(in_path);
        ArrayList<String> imageList = ctrlPersistence.listImageFiles(in_path);
        
        writeFolders(folderList, base_path);
        writeTextFiles(textList, base_path);
        writeImageFiles(imageList, base_path);
        
        ctrlPersistence.write("stop\n".getBytes());
        ctrlPersistence.flush_outBuffer();
        ctrlPersistence.closeFile();
        
        long finishTime = System.nanoTime();
        long Time = finishTime - startTime;
        totalTime = Float.toString(Math.round((Time/1000000000f)* 100) / 100f)+"s";
        
        int indexName = out_path.lastIndexOf('/');
        String fileName = out_path.substring(indexName + 1);
        
        String stadistics = "Directori Compressió " + totalTime + " - " + fileName;
        ctrlPersistence.saveStatistics(stadistics);
    }
    
    /** @brief Descomprimeix carpetes.
     * 
     * @param in_path Path de la carpeta que es vol descomprimir.
     * @param out_path Path de la carpeta volen guardar totes les descompressions.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha descomprés la carpeta amb path <em>in_path</em> i el seu contingut s'ha guardat a <em>out_path</em>.
     */
    public void decompressFolder (String in_path, String out_path) throws IOException {
        long startTime = System.nanoTime();
        
        boolean canvi = false;
        
        Path dec_base_path = Paths.get(out_path);
        ctrlPersistence.set_inBuffer(in_path);
        
        String line, dec_out_path;
        line = ctrlPersistence.readLine_inBuffer();
        while(! "stop".equals(line)) {
            if(line.charAt(0) == '0'){
                dec_out_path = dec_base_path.toString() + "/" + line.substring(1);
                ctrlPersistence.makeDirectory(dec_out_path);
            }
            else {
                dec_out_path = dec_base_path.toString() + "/" + line.substring(1);
                ctrlPersistence.makeFile(dec_out_path);
                ctrlPersistence.set_outBuffer(dec_out_path);
                String algorithmType = getType(dec_out_path);
                switch (algorithmType){
                    case "PPM":
                        if (!canvi) {
                            canvi = true;
                            ctrlPersistence.initializeBufferedReader();
                        }
                        
                        algorithm = new JPEG();
                        
                        algorithm.set_isFile(false);
                        algorithm.decompress();
                        ctrlPersistence.flush_outBuffer();
                        break;
                        
                    case "TXT":
                        algorithm = new LZW();
                        
                        algorithm.set_isFile(false);
                        algorithm.decompress();
                        ctrlPersistence.flush_outBuffer();
                        break;    
                }
            }
            if(canvi) line = ctrlPersistence.readLine();     
            else line = ctrlPersistence.readLine_inBuffer();
        }
        
        ctrlPersistence.closeBufferedOutput();
        ctrlPersistence.closeBufferedReader();
        
        long finishTime = System.nanoTime();
        long Time = finishTime - startTime;
        totalTime = Float.toString(Math.round((Time/1000000000f)* 100) / 100f)+"s";
        
        int indexName = out_path.lastIndexOf('/');
        String fileName = out_path.substring(indexName + 1);
        
        String stadistics = "Directori Descompressió " + totalTime + " - " + fileName;
        ctrlPersistence.saveStatistics(stadistics);
    } 
    
    /**
     * @brief Retorna el tipus del fitxer.
     *
     * @param in_path 
     * @return Retorna el tipus del fitxer.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna el tipus del fitxer.
     */
    public String getType (String in_path) {
        if (in_path.endsWith(".txt")) return "TXT";
        else if (in_path.endsWith(".ppm")) return "PPM";
        else if (in_path.endsWith(".lzss")) return "LZSS";
        else if (in_path.endsWith(".lz78")) return "LZ78";
        else if (in_path.endsWith(".lzw")) return "LZW";
        else if (in_path.endsWith(".jpeg")) return "JPEG";
        return "DIR";
    }
    
    /** @brief Retorna el rati de la compressió.
     *
     * @param in_path Path del fitxer original.
     * @param out_path Path del fitxer comprimit.
     * @return Retorna el rati de la compressió.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna el rati de la compressió.
     */
    public double get_ratio(String in_path, String out_path){
        double outLength = ctrlPersistence.get_fileLength(out_path);
        double inLength = ctrlPersistence.get_fileLength(in_path);
        double rati = 100 - ((outLength * 100)/inLength);
        return rati;
    }
    
      
    /** @brief Demana el contingut de la la base de dades d'estadístiques al controlador de persistencia.
     *
     * @return Retorna un ArrayList de String amb el contingut de la base de dades estadístiques.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna un ArrayList de String amb el contingut de la base de dades estadístiques.
     */
    public ArrayList<String> getTableContent() throws IOException {
        return ctrlPersistence.getTableContent();
    }
    
    /** @brief Getter per saber el valor de l'atribut <em>totalTime</em>.
     *
     * @return Retorna una String amb el valor de <em>totalTime</em>.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna una String amb el valor de <em>totalTime</em>.
     */
    public String getTotalTime() {
        return totalTime;
    }
    
    /** @brief Getter per saber el valor de l'atribut <em>compressRatio</em>.
     *
     * @return Retorna una String amb el valor de <em>compressRatio</em>.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna una String amb el valor de <em>compressRatio</em>.
     */
    public String getCompressRatio() {
        return compressedRatio;
    }
    
    /** @brief Getter per saber el valor de l'atribut <em>out_path</em>.
     *
     * @return Retorna una String amb el valor de <em>out_path</em>.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna una String amb el valor de <em>out_path</em>.
     */
    public String getPath() {
        return out_path;
    }
    
    /**
     * Funcions de persistencia
     */
    
    /** @brief Demana a la capa de persistencia que llegeixi un byte.
     * 
     * @throws IOException
     * @return Retorna el següent byte de dades, o -1 si s’arriba al final del flux.
     * 
     * \pre <em> Cert. </em>
     * \post Els atributs privats <em>inBuffer</em> i <em>outBuffer</em> es tanquen.
    */
    public static int read() throws IOException {
        return ctrlPersistence.read();
    }
    
    /** @brief Crida al controlador de persistencia per llegir un nombre de bytes de dades i els guarda al vector de bytes que se li passa per parametre.
     * 
     * @param b Vector on es guardaran els bytes llegits.
     * @return Retorna el nombre de bytes que s'han pogut llegir.
     * @throws IOException
     * 
     * \pre <em> Cert. </em>
     * \post El controlador de persistencia haura llegit el nombre de bytes indicat pel tamany del vector que se li passa si es pot.
    */
    public static int read(byte[] b) throws IOException {
        return ctrlPersistence.read(b);
    }
    
    /** @brief Crida al controlador de persistencia per escriure el byte especificat.
     * 
     * @param b Byte que es vol escriure.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post El controlador de persistencia ha escrit el byte indicat.
     */
    public static void write(int b) throws IOException {
        ctrlPersistence.write(b);
    }
    
    /** @brief Crida al controlador de persistencia perque escrigui <em>b.length</em> bytes del vector passat per parametres.
     * 
     * @param b Dades que es volen escriure.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post El controlador de persistencia ha escrit les dades indicades.
     */
    public static void write(byte[] b) throws IOException {
        ctrlPersistence.write(b);
    }
    
    /** @brief Crida al controlador de persistencia perque llegeixi un integer.
     * 
     * @return Retorna l'integer llegit.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Crida a la capa de persistencia perque llegeixi un integer.
     */
    public static int readAsciiInt() throws IOException {
        return ctrlPersistence.readAsciiInt();
    }
    
    /** @brief Crida al controlador de persistencia perque inicialitzi el bufferedReader apuntant a un fitxer amb un cert path donat.
     * 
     * @param path Path del fitxer que es vol llegir.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat a persistencia perque inicialiti el bufferedReader apuntant a un fitxer amb un cert path zem>path</em>.
     */
    public static void initializeBufferedReader(String path) throws IOException {
        ctrlPersistence.initializeBufferedReader(path);
    }
    
    /** @brief Crida al controlador de persistencia perque inicialitzi el bufferedReaderHuffman apuntant a un fitxer amb un cert path donat.
     * 
     * @param path Path del fitxer que es vol llegir.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat a persistencia perque inicialiti el bufferedReaderHuffman apuntant a un fitxer amb un cert path zem>path</em>.
     */
    public static void initializeBufferedReaderHuffman(String path) throws IOException {
        ctrlPersistence.initializeBufferedReaderHuffman(path);
    }
    
    /** @brief Crida al controlador de persistencia perque inicialitzi el bufferedReader.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat a persistencia perque inicialiti el bufferedReader.
     */
    public static void initializeBufferedReader() throws IOException {
        ctrlPersistence.initializeBufferedReader();
    }
    
    /** @brief Crida al controlador de persistencia perque tanqui el bufferedReader.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de persistencia perque tanqui el bufferedReader.
     */
    public static void closeBufferedReader() throws IOException {
        ctrlPersistence.closeBufferedReader();
    }
    
    /** @brief Crida al controlador de persistencia perque tanqui el bufferedReaderHuffman.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de persistencia perque tanqui el bufferedReaderHuffman.
     */
    public static void closeBufferedReaderHuffman() throws IOException {
        ctrlPersistence.closeBufferedReaderHuffman();
    }
    
    /** @brief Crida al controlador de persistencia perque llegeixi un linea de text.
     * 
     * @return Retorna la linea de text que el controlador de persistencia ha llegit.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de persistencia perque llegeixi un linea de text.
     */
    public static String readLine() throws IOException {
        return ctrlPersistence.readLine();
    }
    
    /** @brief Crida al controlador de persistencia perque llegeixi un linea de text del bufferedReaderHuffman.
     * 
     * @return Retorna la linea de text que el controlador de persistencia ha llegit del bufferedReaderHuffman.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de persistencia perque llegeixi un linea de text del bufferedReaderHuffman.
     */
    public static String readLineHuffman() throws IOException {
        return ctrlPersistence.readLineHuffman();
    }
    
    /** @brief Crida al controlador de persistencia perque inicialitzi el BitBuffer de compressió.
     * 
     * \pre <em>Cert.</em>
     * \post El controlador ha inicialitzat el BitBuffer per poder comprimir.
     */
    public static void initializeCompressionBitBuffer() {
        ctrlPersistence.initializeCompressionBitBuffer();
    }
    
    /** @brief Crida al controlador de persistencia perque inicialitzi el BitBuffer de descompressió.
     * 
     * \pre <em>Cert.</em>
     * \post S'ha inicialitzat l'atribut privat <em>BitBuffer</em> per poder descomprimir.
     */
    public static void initializeDecompressionBitBuffer() {
        ctrlPersistence.initializeDecompressionBitBuffer();
    }
    
    /** @brief Crida al controlador de persistencia perque llegeixi <em>i</em> bits del BitBuffer.
     * 
     * @param i Bits que es vol llegir.
     * @return Retorna un integer.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de persistencia perque llegeixi <em>i</em> bits del BitBuffer.
     */
    public static int readBitBuffer(int i) throws IOException {
        return ctrlPersistence.readBitBuffer(i);
    }
    
    /** @brief Crida al controlador de persistencia perque escrigui <em>s</em> al BitBuffer.
     * 
     * @param s Bits que es volen escriure.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de persistencia perque escrigui <em>s</em> al BitBuffer.
     */
    public static void writeBitBuffer(String s) throws IOException {
        ctrlPersistence.writeBitBuffer(s);
    }
    
    /**
     *  @brief Crida al controlador de persistencia perque escrigui <em>s</em> al BitBuffer.
     * 
     * @param i Bits que es volen objecte que volem passar a binari.
     * @param p Bits que quants bits tindrà  el objecte a representar en binari.
     * @throws IOException 
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de persistencia perque escrigui <em>s</em> al BitBuffer.
     */
    public static void writeBitBuffer(int i, int p) throws IOException {
        ctrlPersistence.writeBitBuffer(i, p);
    }
    
    /** @brief Crida al controlador de persistencia perque tanqui el BitBuffer.
     * 
     * @param isFile Indica si el que s'esta tractant es un fitxer o una carpeta.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Es crida al controlador de persistencia perque tanqui el BitBuffer.
     */
    public static void closeBitBuffer(boolean isFile) throws IOException {
        ctrlPersistence.closeBitBuffer(isFile);
    }
    
    /** @brief Crida al controlador de persistencia perque borri tot el contingut que tingui la base de dades d'estadístiques.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de persistencia perque borri tot el contingut que tingui la base de dades d'estadístiques.
     */
    public void deleteTableContent() throws IOException {
        ctrlPersistence.deleteTableContent();
    }
    
    /** @brief Crida al controlador de persistencia perque obtingui tot el text d'un fitxer amb path <em>path</em>.
     * 
     * @param path Path del fitxer que es vol llegir.
     * @return Retorna tot el text del fitxer.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de persistencia per obtenir tot el text d'un fitxer amb path <em>path</em>.
     */
    public String getText(String path) throws IOException {
        return ctrlPersistence.getText(path);
    }
    
    /** @brief Crida al controlador de persistencia per obtenir el contingut d'una imatge i transformarla a una BufferedImage.
     * 
     * @param path Path de la imatge que es vol llegir.
     * @return Retorna una BufferedImage representant la imatge amb path <em>path</em>.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha retornat una BufferedImage que representa l'imatge amb path <em>path</em>.
     */
    public BufferedImage getImage(String path) throws IOException {
        ArrayList<Integer> image = ctrlPersistence.getImage(path);
        int width = image.get(0);
        int height = image.get(1);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        int pixel, r, g, b, k = 2;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                r = image.get(k++);
                g = image.get(k++);
                b = image.get(k++);
                pixel = (r * 65536) + (g * 256) + b;
                bufferedImage.setRGB(j, i, pixel);
            }
        }
        
        return bufferedImage; 
    }
    
    /** @brief Crida al controlador de persistencia perque elimini un fitxer amb path <em>path</em>.
     * 
     * @param path Path del fitxer que es vol eliminar.
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de persistencia perque elimini un fitxer amb path <em>path</em>.
     */
    public void deleteFile(String path) {
        ctrlPersistence.deleteFile(path);
    }
    
    /** @brief Es crida al controlador de persistencia perque llegeixi un linea de text.
     * 
     * @return Retorna una linea de text.
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post S'ha cridat al controlador de persistencia perque llegeixi un linea de text.
     */
    public static String readLine_inBuffer() throws IOException {
        return ctrlPersistence.readLine_inBuffer();
    }
}
