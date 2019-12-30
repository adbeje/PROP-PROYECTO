/** @file BinaryTreeAC.java
 * @brief Classe funcional de l'algorisme JPEG.
 *
 * Aquesta classe representa una taula AC de Huffman donada per l'estàndard del JPEG. La representa mitjançant arbres binaris.
 *
 * L'import que utilitza és:
 *     - import java.io.IOException;
 *
 * @author Albert Pita Argemí
 */
package Domain;

import java.io.IOException;

/*
 * Classe BinaryTreeAC
 */

/** @class BinaryTreeAC
 * @brief Classe funcional de l'algorisme JPEG.
 * 
 * Aquesta classe representa una taula AC de Huffman donada per l'estàndard del JPEG. La representa mitjançant arbres binaris.
 *
 * @author Albert Pita Argemí
 */
public class BinaryTreeAC {
    
    /** @brief Atribut private que representa el node arrel del qual penja tot l'arbre.*/
    private Node root;
    
    /** @class Node
     * @brief Classe que representa un node de l'arbre binari.
     *
     * @author Albert Pita Argemí
     */
    class Node {
        /** @brief String que representa el valor a la taula Huffman.*/
        String value;
        /** @brief Valor que representa el run de la taula de Huffman.*/
        int run;
        /** @brief Valor que representa el size de la taula de Huffman.*/
        int size;
        /** @brief Longitud de <em>value</em>.*/
        int length;
        /** @brief Node esquerra de l'arbre.*/
        Node left;
        /** @brief Node dret de l'arbre.*/
        Node right;
        
        /** @brief Constructora per defecte.
         *
         * @param value Valor del node.
         * @param run Valor run del node.
         * @param size Valor size del node.
         * @param length Longitud del valor del node.
         * 
         * \pre <em>Cert.</em>
         * \post Es crea un node amb valors <em>value</em>, <em>run</em>, <em>size</em> i <em>length</em>, i els nodes dret i esquerra buits.
         */
        Node(String value, int run, int size, int length) {
            this.value = value;
            this.run = run;
            this.size = size;
            this.length = length;
            right = null;
            left = null;
        }
    }
    
    /** @brief Creadora per defecte.
     * 
     * @param path Path on esta ubicada la taula que volem inicialitzar.
     * 
     * @throws java.io.IOException
     * 
     * \pre <em>Cert.</em>
     * \post Es crea una nova instància de BinaryTreeAC on <em>root</em> és un arbre binari que representa una taula AC de l'estàndard de Huffman.
     */
    public BinaryTreeAC(String path) throws IOException {
        CtrlDomain.initializeBufferedReaderHuffman(path);
        load_tree();
        CtrlDomain.closeBufferedReaderHuffman();
    }
    
    /** @brief Carrega l'arbre a partir del fitxer, demanant les dades a Persistencia a traves del Controlador de Domini.
     * 
     * \pre <em>Cert.</em>
     * \post <em>root</em> és ara un arbre binari que representa una taula AC de l'estàndard de Huffman.
     */ 
    private void load_tree() throws IOException {
        String lineContent;
        while ((lineContent = CtrlDomain.readLineHuffman()) != null) {
            String[] content = lineContent.split(" ");
            String[] runSize = content[0].split("/");
            
            int run, size;
            
            if (runSize[0].charAt(0) >= 'A') run = runSize[0].charAt(0) - 'A' + 10;
            else run = runSize[0].charAt(0) - '0';
            
            if (runSize[1].charAt(0) >= 'A') size = runSize[1].charAt(0) - 'A' + 10;
            else size = runSize[1].charAt(0) - '0';
            
            add(run, size, content[1]);
        }
    }
    
    /** @brief Afegeix nodes recursivament a l'arbre.
     *
     * Bàsicament recorre <em>codeWord</em> guiant-se per l'iterador <em>i</em> que marca quina char de la paraula s'esta tractant.
     * Si el node actual no existeix és crea i depenent de l'iterador s'inicialitzara amb uns valors o altres.
     * Si ja existeix, depenent de si el char tractat és un 0 o un 1 la recursivitat seguirà cap el node esquerra o el dret, respectivament.
     * 
     * @param current Node actual que s'esta tractant.
     * @param run Run que haura de tenir la fulla final.
     * @param size Size que haura de tenir la fulla final.
     * @param codeWord Valor que haura de tenir la fulla final.
     * @param i Index que marca la posició de la paraula que estem mirant.
     * 
     * \pre <em>Cert.</em>
     * \post Si el node no existeix es crea amb diferents valors depenent de la posició que marqui la <em>i</em>. Si ja existeix, el visita.
     */ 
    private Node addRecursive(Node current, int run, int size, String codeWord, int i) {
        int length = codeWord.length();
        
        if (current == null) {
            if (i == length) current = new Node(codeWord, run, size, codeWord.length());
            else current = new Node("", -1, -1, 0);
            
            if (i < length) {
                char c = codeWord.charAt(i);
                
                if (c == '0') current.left = addRecursive(current.left, run, size, codeWord, ++i);
                else if (c == '1') current.right = addRecursive(current.right, run, size, codeWord, ++i);  
            }    
        }
        
        else {
            char c = codeWord.charAt(i);
            
            if (c == '0') {
                current.left = addRecursive(current.left, run, size, codeWord, ++i);
            }
            else if (c == '1') {
                current.right = addRecursive(current.right, run, size, codeWord, ++i);
            }
        }
        
        return current;
    }
    
    /** @brief Afegeix un nou codeWord a l'arbre.
     * 
     * @param run Run que haura de tenir la fulla final.
     * @param size Size que haura de tenir la fulla final.
     * @param codeWord Valor que haura de tenir la fulla final.
     * 
     * \pre <em>Cert.</em>
     * \post A <em>root</em> se li ha afegit <em>codeWord</em>.
     */ 
    private void add(int run, int size, String codeWord) {
        root = addRecursive(root, run, size, codeWord, 0);
    }
    
    /** @brief Busca recursivament el codeWord que esta associat a un cert run i a un cert size.
     * 
     * Busca les fulles de l'arbre i comprova si el size i el run d'aquell node fulla correspon al <em>size</em> i al <em>run </em> passats per paràmetre.
     * 
     * @param run Run del codeWord que s'esta buscant.
     * @param size Size del codeWord que s'esta buscant.
     * @param current Node actual que s'esta tractant.
     * @return Retorna el codeWord associat associat al run <em>run</em> i al size <em>size</em>.
     *
     * \pre <em>Cert.</em>
     * \post Retorna una String amb el codeWord associat al run <em>run</em> i al size <em>size</em>.
     */
    private String recursiveGetCodeWord(int run, int size, Node current) {
        
        if (current != null) {
            if (current.left == null && current.right == null && current.run == run && current.size == size) {
                return current.value;
            }
            
            else {
                String res = recursiveGetCodeWord(run, size, current.left);
                if (res.equals("")) res = recursiveGetCodeWord(run, size, current.right);
                
                return res;
            }
        }
        
        return "";
    }
    
    /** @brief Busca el codeWord que esta associat a una certa categoria.
     * 
     * @param run Run del codeWord que s'esta buscant.
     * @param size Size del codeWord que s'esta buscant.
     * @return Retorna el codeWord associat associat al run <em>run</em> i al size <em>size</em>.
     *
     * \pre <em>Cert.</em>
     * \post Retorna una String amb el codeWord associat al run <em>run</em> i al size <em>size</em>.
     */
    public String getCodeWord(int run, int size) {
        return recursiveGetCodeWord(run, size, this.root);
    }
    
    /** @brief Busca recursivament el valor de run i size que estan associats a un cert codeWord.
     * 
     * @param data Dades de la qual volem llegir el codeWord i que retorni el respectiu run i size que estem buscant.
     * @param current Node actual que s'esta tractant.
     * @param i Posició actual del iterador que marca quina posició de les dades s'està visitant.
     * 
     * @return Retorna el run i el size del codeWord llegit de <em>data</em>.
     *
     * \pre <em>Cert.</em>
     * \post Retorna una String amb el run i size associats al codeWord llegit de <em>data</em>.
     */
    private String recursiveGetRunSize(String data, Node current, int i) {
        
        if (current != null) {
            if (current.left == null && current.right == null) {                
                return current.run + "/" + current.size + "/" + current.length;
            }
            
            else {
                char c = data.charAt(i);
                
                if (c == '0') return recursiveGetRunSize(data, current.left, ++i);
                else return recursiveGetRunSize(data, current.right, ++i);
            }
        }
        
        return "";
    }
    
    /** @brief Busca el run i el size que esta associada a un cert codeWord.
     * 
     * @param data Dades de la qual volem llegir el codeWord i que retorni els respectius run i size que estem buscant.
     * @param index Posició actual del iterador que marca quina posició de les dades s'està visitant.
     * 
     * @return Retorna el run i el size del codeWord llegit de <em>data</em>.
     *
     * \pre <em>Cert.</em>
     * \post Retorna una String amb el run i size associats al codeWord llegit de <em>data</em>.
     */
    public String getRunSize(String data, int index) {
        return recursiveGetRunSize(data, this.root, index);
    }
}
