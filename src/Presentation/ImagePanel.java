/** @file ImagePanel.java
 * @brief Classe que s'exten de JPanel perque en ell es puguin mostrar imatges.
 *
 * Els imports que utilitza són:
 *     - import java.awt.Dimension
 *     - import java.awt.Graphics
 *     - import java.awt.image.BufferedImage
 *     - import javax.swing.JPanel
 *
 * @author Albert Pita Argemí
 */
package Presentation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/*
 * Classe ImagePanel
 */

/** @class ImagePanel
 * @brief Classe que s'hereda de JPanel perque en ell es puguin mostrar imatges.
 * 
 * Implementa l'operació paintComponent per tal que al JPanel es puguin mostrar imatges.
 * 
 * @author Albert Pita Argemí
 */
public class ImagePanel extends JPanel {
    /**@brief Imatge per mostrar al panel.*/
    private final BufferedImage image;
    
    /** @brief Constructora per defecte.
     * 
     * @param image Imatge que es vol mostrar al panel.
     * \pre <em>Cert.</em>
     * \post S'ha creat una nova instància de ImagePanel.
     */
    public ImagePanel(BufferedImage image) {
        this.image = image;
        this.setPreferredSize(new Dimension(image.getHeight(), image.getWidth()));
    }
    
    /** @brief Mostra l'imatge.
     * 
     * @param g Grafics per mostrar l'imatge.
     * 
     * \pre <em>Cert.</em>
     * \post S'ha pinta l'imatge al panel.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
