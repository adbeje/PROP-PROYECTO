/** @file Main.java
 * @brief Aquesta classe és el main que executarà el programa.
 *
 * Els imports que utilitza són:
 *     - import Presentation.CtrlPresentation
 *
 *  @author Adrià Ventura i Herce
 */
package Main;

import Presentation.CtrlPresentation;

/*
 * Classe Main
 */

/** @class Main
 * @brief Aquest és el Main del projecte.
 *
 *  @author Adria Ventura i Herce
 */
public class Main {
    
    /** @brief Main
     * 
     * @param args No se li passa cap argument.
     * 
     * \pre <em>Cert.</em>
     * \post S'executa el projecte.
     */
    public static void main(String[] args)
    {   
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CtrlPresentation ctrlPresentation;
                ctrlPresentation = new CtrlPresentation();
                ctrlPresentation.initilizePresentation();
            };
        });
    }
}
