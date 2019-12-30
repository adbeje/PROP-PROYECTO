/** @file RoundedBorder.java
 * @brief Classe que s'exten de Border per crear marges rodons.
 *
 * Els imports que utilitza són:
 *     - import java.awt.Component
 *     - import java.awt.Graphics
 *     - import java.awt.Insets
 *     - import javax.swing.border.Border
 *
 * @author Albert Pita Argemí
 */
package Presentation;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.Border;

/*
 * Classe RoundedBorder
 */

/** @class RoundedBorder
 * @brief Classe que s'exten de Border per crear marges rodons.
 *
 * @author Albert Pita Argemí
 */
public class RoundedBorder implements Border {
    
    /** @brief Atribut que representa el radi de les cantonades. */
    private final int radius;
    /** @brief Atribut que representa el tamany del inset left.*/
    private final int left;
    /** @brief Atribut que representa el tamany del inset right.*/
    private final int right;
    
    /** @brief Creadora amb un cert radi.
     * 
     * @param radius Radi que es vol per les cantonades.
     * 
     * \pre <em>Cert</em>.
     * \post Crea una nova instància de RoundedBorder amb radius igual a <em>radius</em>.
     */
    public RoundedBorder(int radius) {
        this.radius = radius;
        this.left = 1;
        this.right = 1;
    }
    
    /** @brief Creadora amb un cert radi, un cert left i un cert right.
     * 
     * @param radius Radi que es vol per les cantonades.
     * @param left Valor de left del inset.
     * @param right Valor de right del inset.
     * 
     * \pre <em>Cert</em>.
     * \post Crea una nova instància de RoundedBorder amb radius igual a <em>radius</em>, left igual a <em>left</em> i right igual a <em>right</em>.
     */
    public RoundedBorder(int radius, int left, int right) {
        this.radius = radius;
        this.left = left;
        this.right = right;
    }

    /** @brief Pinta el marge.
     * 
     * @param cmpnt Component al que se li pinten els marges rodons.
     * @param grphcs Gràfics.
     * @param x 
     * @param y
     * @param width
     * @param height
     * 
     * \pre <em>Cert.</em>
     * \post Pinta el marge amb les cantonades rodones.
     */
    @Override
    public void paintBorder(Component cmpnt, Graphics grphcs, int x, int y, int width, int height) {
        
        if (cmpnt.getClass().getSimpleName().equals("JPanel")) {
            grphcs.setColor(cmpnt.getBackground());
            grphcs.fillRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
        else if (cmpnt.getClass().getSimpleName().equals("JButton")) {
            grphcs.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    /** @brief Retorna les noves dimensions del component.
     * 
     * @param cmpnt Component que s'esta observant.
     * @return Retorna les noves dimensions del component.
     * 
     * \pre <em>Cert.</em>
     * \post S'ha retornat les noves dimensions del component.
     */
    @Override
    public Insets getBorderInsets(Component cmpnt) {
        
        if (cmpnt.getClass().getSimpleName().equals("JPanel")) return new Insets(0, 0, 0, 0);
        else if (cmpnt.getClass().getSimpleName().equals("JButton")) return new Insets(4, this.left, 4, this.right);
        
        return null;
    }

    /** @brief Retorna si es opac o no.
     * 
     * @return Retorna un boolea si es opac o no.
     * 
     * \pre <em>Cert.</em>
     * \post Retorna sempre que el marge és opac.
     */
    @Override
    public boolean isBorderOpaque() {
        return true;
    } 
}
