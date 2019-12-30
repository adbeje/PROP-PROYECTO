/** @file Algorithm.java
 * @brief Classe abstracta que representa un algorisme de compressió i descompressió.
 *
 * Els imports que utilitza són:
 *     - import java.io.IOException
 *
 * @author Albert Pita Argemí
 */
package Domain;

import java.io.IOException;

/*
 * Classe Algorithm
 */

/** @class Algorithm
 * @brief Classe abstracta que representa un algorisme de compressió i descompressió.
 *
 * @author Albert Pita Argemí
 */
public abstract class Algorithm {
    /** @brief Boolea que indica si un algorisme esta comprimint un arxiu o una carpeta.*/
    private boolean isFile;
    
    /** @brief Setter per modificar l'atribut privat <em>isFile</em>.
     * 
     * @param b Valor que isFile ha de prendre.
     * 
     * \pre <em>Cert.</em>
     * \post L'atribut privat <em>isFile</em> té ara valor <em>b</em>.
     */
    public void set_isFile(boolean b) {
        isFile = b;
    }
    
    /** @brief Getter de l'atribut privat <em>isFile</em>.
     * 
     * @return Retorna el valor de <em>isFile</em>.
     * 
     * \pre <em>Cert.</em>
     * \post S'ha retornat el valor de l'atribut privat <em>isFile</em>.
     */
    public boolean get_isFile() {
        return isFile;
    }
    
    /** @brief Operació abstracta de compressió.
     * 
     * @throws IOException
     */
    public abstract void compress() throws IOException;
    
    /** @brief Operació abstracta de descompressió.
     * 
     * @throws IOException
     */
    public abstract void decompress() throws IOException;
    
}
