/** @file BinaryTreeDC.java
 * @brief Classe funcional de l'algorisme JPEG.
 *
 * Aquesta classe representa una taula DC de Huffman donada per l'estàndard del JPEG. La representa mitjançant arbres binaris.
 *
 * L'import que utilitza és:
 *     - import java.io.IOException;
 *
 * @author Albert Pita Argemí
 */
package Domain;

import java.io.IOException;

/*
 * Classe BinaryTreeDC
 */

/** @class BinaryTreeDC
 * @brief Classe funcional de l'algorisme JPEG.
 * 
 * Aquesta classe representa una taula DC de Huffman donada per l'estàndard del JPEG. La representa mitjançant arbres binaris.
 *
 * @author Albert Pita Argemí
 */
public class BinaryTreeDC {
    
    /** @brief Atribut private que representa el node arrel del qual penja tot l'arbre.*/
    private Node root;
    
    /*
     * Classe Node
     */
    
    /** @class Node
     * @brief Classe que representa un node de l'arbre binari.
     *
     * @author Albert Pita Argemí
     */
    class Node {
        /** @brief String que representa el valor a la taula Huffman.*/
        String value;
        /** @brief Enter que representa a quina categoria pertany de la taula estàndard de Huffman.*/
        int category;
        /** @brief Longitud de <em>value</em>.*/
        int length;
        /** @brief Node esquerra de l'arbre.*/
        Node left;
        /** @brief Node dret de l'arbre.*/
        Node right;
        
        /** @brief Constructora per defecte.
         *
         * @param value Valor del node.
         * @param category Categoria del node.
         * @param length Longitud del valor del node.
         * 
         * \pre <em>Cert.</em>
         * \post Es crea un node amb valors <em>value</em>, <em>category</em> i <em>length</em>, i els nodes dret i esquerra buits.
         */
        Node(String value, int category, int length) {
            this.value = value;
            this.category = category;
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
     * \post Es crea una nova instància de BinaryTreeDC on <em>root</em> és un arbre binari que representa una taula DC de l'estàndard de Huffman.
     */
    public BinaryTreeDC(String path) throws IOException {
        CtrlDomain.initializeBufferedReaderHuffman(path);
        load_tree();
        CtrlDomain.closeBufferedReaderHuffman();
    }
    
    /** @brief Carrega l'arbre a partir del fitxer, demanant les dades a Persistencia a traves del Controlador de Domini.
     * 
     * \pre <em>Cert.</em>
     * \post <em>root</em> és ara un arbre binari que representa una taula DC de l'estàndard de Huffman.
     */ 
    private void load_tree() throws IOException {
        String lineContent;
        while ((lineContent = CtrlDomain.readLineHuffman()) != null) {
            String[] content = lineContent.split(" ");
            add(Integer.parseInt(content[0]), content[1]);
        }
    }
    
    /** @brief Afegeix nodes recursivament a l'arbre.
     *
     * Bàsicament recorre <em>codeWord</em> guiant-se per l'iterador <em>i</em> que marca quina char de la paraula s'esta tractant.
     * Si node actual no existeix és crea i depenent de l'iterador s'inicialitzara amb uns valors o altres.
     * Si ja existeix, depenent de si el char tractat és un 0 o un 1 la recursivitat seguirà cap el node esquerra o el dret, respectivament.
     * És comprova també la categoria dels nodes que estan al mig entre l'arrel i una fulla, perque tinguin com a categoria la més alta del respectius subarbres que penjen de dreta i esquerra.
     * Això es fa per disminuir el temps de busqueda d'altres funcions.
     * 
     * @param current Node actual que s'esta tractant.
     * @param category Categoria que haura de tenir la fulla final.
     * @param codeWord Valor que haura de tenir la fulla final.
     * @param i Index que marca la posició de la paraula que estem mirant.
     * 
     * \pre <em>Cert.</em>
     * \post Si el node no existeix es crea amb diferents valors, depenent de la posició que marqui la <em>i</em>. Si ja existeix, el visita.
     */ 
    private Node addRecursive(Node current, int category, String codeWord, int i) {
        int length = codeWord.length();
        
        if (current == null) {
            if (i == 0) current = new Node("root", -1, 0);
            else if (i == length) current = new Node(codeWord, category, codeWord.length());
            else current = new Node("", -1, 0);
            
            if (i < length) {
                char c = codeWord.charAt(i);
                
                if (!current.value.equals("root") && current.category < category) {
                    current.category = category;
                }
            
                if (c == '0') current.left = addRecursive(current.left, category, codeWord, ++i);
                else if (c == '1') current.right = addRecursive(current.right, category, codeWord, ++i);
            }
        }
        else {
            char c = codeWord.charAt(i);
            
            if (!current.value.equals("root") && current.category < category) {
                    current.category = category;
            } 
            
            if (c == '0') {
                current.left = addRecursive(current.left, category, codeWord, ++i);
            }
            else if (c == '1') {
                current.right = addRecursive(current.right, category, codeWord, ++i);
            }
        }
        
        return current;
    }
    
    /** @brief Afegeix un nou codeWord a l'arbre.
     * 
     * @param category Categoria que haura de tenir la fulla final.
     * @param codeWord Valor que haura de tenir la fulla final.
     * 
     * \pre <em>Cert.</em>
     * \post A <em>root</em> se li ha afegit <em>codeWord</em>.
     */ 
    private void add(int category, String codeWord) {
        root = addRecursive(root, category, codeWord, 0);
    }
    
    /** @brief Busca recursivament el codeWord que esta associat a una certa categoria.
     * 
     * @param current Node actual que s'esta tractant.
     * @param category Categoria del codeWord que estem buscant.
     * @return Retorna el codeWord associat a la categoria <em>category</em>.
     *
     * \pre <em>Cert.</em>
     * \post Retorna una String amb el codeWord associat a la categoria <em>category</em>.
     */
    private String recursiveGetCodeWord(int category, Node current) {
        
        if (current != null) {
            if (current.left == null && current.right == null && current.category == category) {
                return current.value;
            }
            else {
                if (category > current.left.category) return recursiveGetCodeWord(category, current.right);
                else return recursiveGetCodeWord(category, current.left);
            }
        }
        
        return "";
    }
    
    /** @brief Busca el codeWord que esta associat a una certa categoria.
     * 
     * @param category Categoria del codeWord que estem buscant.
     * @return Retorna el codeWord associat a la categoria <em>category</em>.
     *
     * \pre <em>Cert.</em>
     * \post Retorna una String amb el codeWord associat a la categoria <em>category</em>.
     */
    public String getCodeWord(int category) {
        return recursiveGetCodeWord(category, this.root);
    }

    /** @brief Busca recursivament la categoria que esta associada a un cert codeWord.
     * 
     * @param data Dades de la qual volem llegir el codeWord i que retorni la respectiva categoria que estem buscant.
     * @param current Node actual que s'esta tractant.
     * @param i Posició actual del iterador que marca quina posició de les dades s'està visitant.
     * 
     * @return Retorna la categoria del codeWord llegit de <em>data</em>.
     *
     * \pre <em>Cert.</em>
     * \post Retorna una String amb la categoria associada al codeWord llegit de <em>data</em>.
     */
    private String recursiveGetCategory(String data, Node current, int i) {
        
        if (current != null) {
            if (current.left == null && current.right == null) {
                return current.category + "/" + current.length;
            }
            
            else {
                char c = data.charAt(i);
                
                if (c == '0') return recursiveGetCategory(data, current.left, ++i);
                else return recursiveGetCategory(data, current.right, ++i);
            }
        }
        
        return "";      
    } 
    
    /** @brief Busca la categoria que esta associada a un cert codeWord.
     * 
     * @param data Dades de la qual volem llegir el codeWord i que retorni la respectiva categoria que estem buscant.
     * @param index Posició actual del iterador que marca quina posició de les dades s'està visitant.
     * 
     * @return Retorna la categoria del codeWord llegit de <em>data</em>.
     *
     * \pre <em>Cert.</em>
     * \post Retorna una String amb la categoria associada al codeWord llegit de <em>data</em>.
     */
    public String getCategory(String data, int index) {
        return recursiveGetCategory(data, this.root, index);
    }
}
