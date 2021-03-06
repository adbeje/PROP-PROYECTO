/** @file viewAlgorithms.java
 * @brief Aquesta classe és la view de la selecció dels algorismes.
 *
 * Els imports que utilitza són:
 *     - import java.awt.Color
 *     - import java.awt.Dimension
 *     - import java.awt.Frame
 *     - import javax.swing.JOptionPane
 *
 * @author Adrià Ventura i Herce
 */

package Presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JOptionPane;

/*
 * Classe viewAlgorithms
 */

/** @class viewAlgorithms
 *  @brief Aquest és la view de la selecció dels algorismes.
 *
 *  @author Adria Ventura i Herce
 */
public class viewAlgorithms extends javax.swing.JFrame {
    /** @brief Instància del controlador de persistentació, inicialitzada al crear el controlador de presentació.*/
    private final CtrlPresentation ctrlPresentation;
    /** @brief Posició X del ratolí.*/
    private int xMouse;
    /** @brief Posició Y del ratolí.*/
    private int yMouse;
    
    /** @brief Creadora per defecte de viewAlgorithms.
     * 
     * @param cP Es el controlador de presentació.
     * 
     * \pre <em>Cert.</em>
     * \post Ha creat una instància de viewAlgorithms.
     */
    public viewAlgorithms(CtrlPresentation cP) {
        this.setUndecorated(true);
        
        ctrlPresentation = cP;
        
        initComponents();
        
        this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
        
        this.setSize(new Dimension(500, 300));
        this.setResizable(false);
        
        panelAlg.setBorder(new RoundedBorder(50));
        btnSelecciona.setBorder(new RoundedBorder(40, 4, 4));
        btnSelecciona.setContentAreaFilled(false);
        
        groupAlgorismes.add(radioBtnLZ78);
        groupAlgorismes.add(radioBtnLZW);
        groupAlgorismes.add(radioBtnLZSS);
        groupAlgorismes.add(radioBtnJPEG);
        
    }
    
    /** @brief Fa "visibles" els objectes del Form corresponent.
     * 
     * \pre <em>Cert.</em>
     * \post Ha fet "visible" els objectes del Form corresponent
     */
    public void makeVisible() {
        //Activa Panels
        panelAlg.setEnabled(true);
        
        //Activa Buttons
        labelBack.setEnabled(true);
        btnSelecciona.setEnabled(true);
        radioBtnLZ78.setEnabled(true);
        radioBtnLZW.setEnabled(true);
        radioBtnLZSS.setEnabled(true);
        radioBtnJPEG.setEnabled(true); 
    }
    
    /** @brief Fa "invisibles" els objectes del Form corresponent.
     * 
     * \pre <em>Cert.</em>
     * \post Ha fet "invisibles" els objectes del Form corresponent
     */
    public void makeInvisible() {
        //Desactiva Buttons
        labelBack.setEnabled(false);
        btnSelecciona.setEnabled(false);
        radioBtnLZ78.setEnabled(false);
        radioBtnLZW.setEnabled(false);
        radioBtnLZSS.setEnabled(false);
        radioBtnJPEG.setEnabled(false); 
        
        //Desactiva Panels
        panelAlg.setEnabled(false);  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupAlgorismes = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        panelAlg = new javax.swing.JPanel();
        radioBtnLZ78 = new javax.swing.JRadioButton();
        radioBtnLZSS = new javax.swing.JRadioButton();
        radioBtnJPEG = new javax.swing.JRadioButton();
        btnSelecciona = new javax.swing.JButton();
        labelBack = new javax.swing.JLabel();
        labelCrown = new javax.swing.JLabel();
        labelExit = new javax.swing.JLabel();
        labelMinimize = new javax.swing.JLabel();
        radioBtnLZW = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelAlg.setBackground(new java.awt.Color(255, 255, 255));
        panelAlg.setOpaque(false);
        panelAlg.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelAlgMouseDragged(evt);
            }
        });
        panelAlg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelAlgMousePressed(evt);
            }
        });

        radioBtnLZ78.setBackground(new java.awt.Color(255, 255, 255));
        radioBtnLZ78.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        radioBtnLZ78.setForeground(new java.awt.Color(0, 0, 0));
        radioBtnLZ78.setText("LZ78");

        radioBtnLZSS.setBackground(new java.awt.Color(255, 255, 255));
        radioBtnLZSS.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        radioBtnLZSS.setForeground(new java.awt.Color(0, 0, 0));
        radioBtnLZSS.setText("LZSS");

        radioBtnJPEG.setBackground(new java.awt.Color(255, 255, 255));
        radioBtnJPEG.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        radioBtnJPEG.setForeground(new java.awt.Color(0, 0, 0));
        radioBtnJPEG.setText("JPEG");

        btnSelecciona.setBackground(new java.awt.Color(255, 255, 255));
        btnSelecciona.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        btnSelecciona.setForeground(new java.awt.Color(0, 0, 0));
        btnSelecciona.setText("Selecciona");
        btnSelecciona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionaActionPerformed(evt);
            }
        });

        labelBack.setBackground(new java.awt.Color(255, 255, 255));
        labelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/EndarrereMitjana.png"))); // NOI18N
        labelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBackMouseClicked(evt);
            }
        });

        labelCrown.setBackground(new java.awt.Color(255, 255, 255));
        labelCrown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/the_crownMitjana.png"))); // NOI18N

        labelExit.setBackground(new java.awt.Color(255, 255, 255));
        labelExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/SortirMitjana.png"))); // NOI18N
        labelExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelExitMouseClicked(evt);
            }
        });

        labelMinimize.setBackground(new java.awt.Color(255, 255, 255));
        labelMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/MinimitzarMitjana.png"))); // NOI18N
        labelMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMinimizeMouseClicked(evt);
            }
        });

        radioBtnLZW.setBackground(new java.awt.Color(255, 255, 255));
        radioBtnLZW.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        radioBtnLZW.setForeground(new java.awt.Color(0, 0, 0));
        radioBtnLZW.setText("LZW");

        javax.swing.GroupLayout panelAlgLayout = new javax.swing.GroupLayout(panelAlg);
        panelAlg.setLayout(panelAlgLayout);
        panelAlgLayout.setHorizontalGroup(
            panelAlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAlgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAlgLayout.createSequentialGroup()
                        .addComponent(labelBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 345, Short.MAX_VALUE)
                        .addComponent(btnSelecciona))
                    .addGroup(panelAlgLayout.createSequentialGroup()
                        .addGroup(panelAlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(radioBtnLZW, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(radioBtnLZ78, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelCrown, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioBtnJPEG, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(radioBtnLZSS, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
                        .addComponent(labelMinimize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelExit)))
                .addContainerGap())
        );
        panelAlgLayout.setVerticalGroup(
            panelAlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAlgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelAlgLayout.createSequentialGroup()
                        .addGroup(panelAlgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelExit)
                            .addComponent(labelMinimize))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSelecciona))
                    .addGroup(panelAlgLayout.createSequentialGroup()
                        .addComponent(labelCrown)
                        .addGap(23, 23, 23)
                        .addComponent(radioBtnLZ78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioBtnLZW)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioBtnLZSS, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioBtnJPEG)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                        .addComponent(labelBack)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAlg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAlg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** @brief Canvi de form del selecctor d'algoritmes al form del algoritme seleccionat.
     * 
     * @param evt
     * 
     * \pre S'ha seleccionat un algorisme.
     * \post Ha canviat del form de selecctor d'algoritmes al del algoritme seleccionat.
     */
    private void btnSeleccionaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionaActionPerformed
        String algorithm = "";
        
        if(radioBtnLZ78.isSelected()) algorithm = radioBtnLZ78.getText();
        else if(radioBtnLZW.isSelected()) algorithm = radioBtnLZW.getText();
        else if(radioBtnLZSS.isSelected()) algorithm = radioBtnLZSS.getText();
        else if(radioBtnJPEG.isSelected())algorithm = radioBtnJPEG.getText();
        
        if(!algorithm.equals("")) {
            ctrlPresentation.setAlgorithmType(algorithm);
            switch (algorithm) {
                case "JPEG":
                    ctrlPresentation.syncViewAlg_to_viewJPEG();
                    break;
                case "LZSS":
                    ctrlPresentation.syncViewAlg_to_viewLZSS();
                    break;
                default:
                    ctrlPresentation.syncViewAlg_to_viewFSCM();
                    break;
            }
            groupAlgorismes.clearSelection();
        } else {
            JOptionPane.showMessageDialog(null, "No has seleccionat cap algorisme", "error!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_btnSeleccionaActionPerformed

    /** @brief Canvi de form del selecctor d'algoritmes a form de opcions de compressió.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post Ha canviat del form del selector d'algoritmes a form de opcions de compressió.
     */
    private void labelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBackMouseClicked
        groupAlgorismes.clearSelection();
        ctrlPresentation.syncViewAlg_to_viewCO();
    }//GEN-LAST:event_labelBackMouseClicked

    /** @brief Tanca el form.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post El form es tanca.
     */
    private void labelExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelExitMouseClicked
        ctrlPresentation.exit();
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
     * @param evt S'utiliza per saber la posició relativa del ratolí.
     * 
     * \pre <em>Cert.</em>
     * \post Canvia la posició relativa del form a on ha arrossegat el ratolí.
     */
    private void panelAlgMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAlgMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_panelAlgMouseDragged

    /** @brief Iniciar posició relativa del form a les variables.
     * 
     * @param evt S'utiliza per saber la posició relativa del ratolí.
     * 
     * \pre <em>Cert.</em>
     * \post Ha iniciat la posició relativa del form a les dos variables 2D.
     */
    private void panelAlgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAlgMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_panelAlgMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelecciona;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup groupAlgorismes;
    private javax.swing.JLabel labelBack;
    private javax.swing.JLabel labelCrown;
    private javax.swing.JLabel labelExit;
    private javax.swing.JLabel labelMinimize;
    private javax.swing.JPanel panelAlg;
    private javax.swing.JRadioButton radioBtnJPEG;
    private javax.swing.JRadioButton radioBtnLZ78;
    private javax.swing.JRadioButton radioBtnLZSS;
    private javax.swing.JRadioButton radioBtnLZW;
    // End of variables declaration//GEN-END:variables
}
