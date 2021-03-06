/** @file viewPreviewText.java
 * @brief Aquesta classe és la view de previsualització de texts.
 *
 * Els imports que utilitza són:
 *     - import java.awt.Color
 *     - import java.awt.Dimension
 *     - import java.awt.Frame
 *     - import java.awt.Insets
 *     - import java.io.IOException
 *
 * @author Albert Pita Argemí
 */
package Presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.io.IOException;

/*
 * Classe viewPreviewText
 */

/** @class viewPreviewText
 * @brief Aquest és la view de previsualització de textos.
 *
 *  @author Albert Pita Argemí
 */
public class viewPreviewText extends javax.swing.JFrame {
    /** @brief Instància del controlador de persistentació, inicialitzada al crear el controlador de presentació.*/
    private final CtrlPresentation ctrlPresentation;
    /** @brief Posició X del ratolí.*/
    private int xMouse;
    /** @brief Posició Y del ratolí.*/
    private int yMouse;
    
    /** @brief Creadora per defecte de viewPreviewText.
     * 
     * @param cP És el controlador de presentació.
     * @throws IOException
     * 
     * \pre Cert.
     * \post Ha creat una instància de viewPreviewText.
     */
    public viewPreviewText(CtrlPresentation cP) throws IOException {
        this.setUndecorated(true);
        
        ctrlPresentation = cP;
        
        initComponents();
        
        this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
        
        this.setSize(new Dimension(500, 300));
        this.setResizable(false);
        
        panelBig.setBorder(new RoundedBorder(50));
        
        String path = ctrlPresentation.getPath();
        
        textAreaDisplay.setEditable(false);
        textAreaDisplay.setMargin(new Insets(2, 10, 2 ,2));
        textAreaDisplay.setText(ctrlPresentation.getText(path));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBig = new javax.swing.JPanel();
        labelExit = new javax.swing.JLabel();
        labelMinimize = new javax.swing.JLabel();
        labelCrown = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaDisplay = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBig.setOpaque(false);
        panelBig.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelBigMouseDragged(evt);
            }
        });
        panelBig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBigMousePressed(evt);
            }
        });

        labelExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/SortirMitjana.png"))); // NOI18N
        labelExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelExitMouseClicked(evt);
            }
        });

        labelMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/MinimitzarMitjana.png"))); // NOI18N
        labelMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMinimizeMouseClicked(evt);
            }
        });

        labelCrown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/the_crownMitjana.png"))); // NOI18N

        textAreaDisplay.setColumns(20);
        textAreaDisplay.setRows(5);
        jScrollPane1.setViewportView(textAreaDisplay);

        javax.swing.GroupLayout panelBigLayout = new javax.swing.GroupLayout(panelBig);
        panelBig.setLayout(panelBigLayout);
        panelBigLayout.setHorizontalGroup(
            panelBigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBigLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                    .addGroup(panelBigLayout.createSequentialGroup()
                        .addComponent(labelCrown)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelMinimize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelExit)))
                .addContainerGap())
        );
        panelBigLayout.setVerticalGroup(
            panelBigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBigLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCrown)
                    .addGroup(panelBigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelMinimize)
                        .addComponent(labelExit)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** @brief Tanca el form.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post El form es tanca.
     */
    private void labelExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelExitMouseClicked
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_labelExitMouseClicked

    /** @brief Minimitza el form.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post El form s'ha minimitzat.
     */
    private void labelMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizeMouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_labelMinimizeMouseClicked

    /** @brief Canvi de posició del form.
     * 
     * @param evt S'utilitza per saber la posició relativa del ratolí.
     * 
     * \pre <em>Cert.</em>
     * \post Canvia la posició relativa del form a on ha arrossegat el ratolí.
     */
    private void panelBigMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBigMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_panelBigMouseDragged

    /** @brief Iniciar posició relativa del form a les variables.
     * 
     * @param evt S'utiltiza per saber la posició relativa del ratolí.
     * 
     * \pre <em>Cert.</em>
     * \post Ha iniciat la posició relativa del form a les dos variables 2D.
     */
    private void panelBigMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBigMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_panelBigMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCrown;
    private javax.swing.JLabel labelExit;
    private javax.swing.JLabel labelMinimize;
    private javax.swing.JPanel panelBig;
    private javax.swing.JTextArea textAreaDisplay;
    // End of variables declaration//GEN-END:variables
}
