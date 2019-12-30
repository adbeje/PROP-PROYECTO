/** @file LZ78Test.java
 * @brief Aquest és el test unitari de l'algorisme LZ78.
 *
 * Els imports que utilitza són:
 *       - import java.io.*
 *       - import junit.framework.Assert
 *       - import org.junit.Test
 *       - import org.apache.commons.io.FileUtils
 *       - import Domain.LZ78
 *
 * @author Carlos Gascón Dominguez
 */

package Domain.Tests;

import java.io.*;
import junit.framework.Assert;
import org.junit.Test;
import org.apache.commons.io.FileUtils;
import Domain.*;
import Persistence.CtrlPersistence;

/*
 * Test unitari de l'algorisme LZ78
 */

/** @class LZ78Test
 * @brief Aquest és el test unitari de l'algorisme LZ78.
 *
 * @author Carlos Gascón Dominguez
 */
public class LZ78Test {
    /** @brief Path del fitxer d'entrada per la compressió i referent per comparar a la descompressió. */
    private final String in_path = "../data/JocsDeProves/LZ78Test/lz78sample1.txt";
    /** @brief Path del fitxer de sortida del test de compressió.*/
    private final String out_path = "../data/JocsDeProves/LZ78Test/comptest.lz78";
    /** @brief Path del fitxer d'entrada per la descompressió i referent per comparar a la compressió*/
    private final String in_path2 = "../data/JocsDeProves/LZ78Test/lz78comp1.lz78";
    /** @brief Path del fitxer de sortida del test de descompressió */
    private final String out_path2 = "../data/JocsDeProves/LZ78Test/dectest.txt";
    /** @brief Instancia del controlador de persistència */
    private static final CtrlPersistence ctrlPersistence = CtrlPersistence.getInstance();
    /** @brief Instancia del controlador de domini */
    private static final CtrlDomain ctrlDomain = CtrlDomain.getInstance();
    /** @brief Instancia de l'algorisme LZ78 */
    private final Algorithm algorithm = new LZ78();
    
    /** @brief Creadora per defecte.
     *
     * \pre <em>Cert.</em>
     * \post Crea una instància de la classe LZ78Test.
     */
    public LZ78Test() {};

    /** @brief Test del mètode de compressió de la classe LZ78.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCompress() throws Exception {
        
        System.out.println("**Testing compress**");
        ctrlPersistence.openFile(in_path, out_path);
        algorithm.set_isFile(true);
        algorithm.compress();
        File expected = new File(in_path2);
        File output = new File(out_path);
        Assert.assertTrue("Són diferents !", FileUtils.contentEquals(expected,output));
    }

    /** @brief Test del mètode de descompressió de la classe LZ78.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testDecompress() throws Exception {
        System.out.println("**Testing decompress**");
        ctrlPersistence.openFile(in_path2, out_path2);
        algorithm.set_isFile(true);
        algorithm.decompress();
        ctrlPersistence.flush_outBuffer();
        File expected = new File(in_path);
        File output = new File(out_path2);
        Assert.assertTrue("Són diferents !", FileUtils.contentEquals(expected,output));
    }
    
}
