/** @file viewStatistics.java
 * @brief Aquesta classe és la view de les estadístiques.
 *
 * Els imports que utilitza són:
 *     - import java.awt.Color
 *     - import java.awt.Dimension
 *     - import java.awt.Frame
 *     - import java.io.IOException
 *     - import java.util.ArrayList
 *     - import java.util.logging.Level
 *     - import java.util.logging.Logger
 *     - import javax.swing.table.DefaultTableModel
 *
 * @author Albert Pita Argemí
 */
package Presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/*
 * Classe viewStatistics
 */

/** @class viewStatistics
 *  @brief Aquest és la view de les estadístiques.
 *
 *  @author Albert Pita Argemí
 */
public class viewStatistics extends javax.swing.JFrame {
    /** @brief Instància del controlador de persistentació, inicialitzada al crear el controlador de presentació.*/
    private final CtrlPresentation ctrlPresentation;
    /** @brief Taula on es recopilen les estadístiques del compressor.*/
    private final DefaultTableModel model = new DefaultTableModel();
    /** @brief Posició X del ratolí.*/
    private int xMouse;
    /** @brief Posició Y del ratolí.*/
    private int yMouse;
    
    /** @brief Creadora per defecte de viewStatistics.
     * 
     * @param cP Es el controlador de presentació.
     * 
     * \pre <em>Cert.</em>
     * \post Ha creat una instància de viewStatistics.
     */
    public viewStatistics(CtrlPresentation cP) {
        this.setUndecorated(true);
        
        ctrlPresentation = cP;
        initComponents();
        
        this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
        
        this.setSize(new Dimension(500, 300));
        this.setResizable(false);
           
        jPanel1.setBorder(new RoundedBorder(50));
        
        String[] header = {"Algorisme", "Acció", "Temps", "Rati", "Nom"};
        model.setColumnIdentifiers(header);
        model.setRowCount(0);
        tableStatistics.setModel(model);
    }
    
    /** @brief Fa "visibles" els objectes del Form corresponent.
     * 
     * @throws IOException
     * 
     * \pre <em>Cert.</em>
     * \post Ha fet "visible" els objectes del Form corresponent
     */ 
    public void makeVisible() throws IOException {
       syncTable();
        
       jPanel1.setEnabled(true);
       jScrollPane1.setEnabled(true);
       labelBack.setEnabled(true);
       labelCrown.setEnabled(true);
       labelExit.setEnabled(true);
       labelMinimize.setEnabled(true);
       tableStatistics.setEnabled(true);
    }
    
    /** @brief Fa "invisibles" els objectes del Form corresponent.
     * 
     * \pre <em>Cert.</em>
     * \post Ha fet "invisibles" els objectes del Form corresponent
     */
    public void makeInvisible() {
        jPanel1.setEnabled(false);
        jScrollPane1.setEnabled(false);
        labelBack.setEnabled(false);
        labelCrown.setEnabled(false);
        labelExit.setEnabled(false);
        labelMinimize.setEnabled(false);
        tableStatistics.setEnabled(false);
    }
    
    /** @brief Carrega les dades de la BD al form.
     * 
     * @throws  IOException
     * 
     * \pre Cert.
     * \post Ha carregat tots els estadistics al form de historial estadístic
     */
    private void syncTable() throws IOException {
         ArrayList<String> tableContent = ctrlPresentation.getTableContent();

        model.setRowCount(0);
        
        for(String line: tableContent) {
            String[] row = line.split(" ");  
            model.addRow(row);
        }
        
        tableStatistics.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelBack = new javax.swing.JLabel();
        labelExit = new javax.swing.JLabel();
        labelMinimize = new javax.swing.JLabel();
        labelCrown = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableStatistics = new javax.swing.JTable();
        labelBin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        labelBack.setBackground(new java.awt.Color(255, 255, 255));
        labelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/EndarrereMitjana.png"))); // NOI18N
        labelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBackMouseClicked(evt);
            }
        });

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

        labelCrown.setBackground(new java.awt.Color(255, 255, 255));
        labelCrown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/the_crownMitjana.png"))); // NOI18N

        tableStatistics.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableStatistics);

        labelBin.setBackground(new java.awt.Color(255, 255, 255));
        labelBin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/BrossaMitjana.png"))); // NOI18N
        labelBin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBinMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelCrown)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelMinimize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelExit))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelBin)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelExit)
                        .addComponent(labelMinimize))
                    .addComponent(labelCrown))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelBack)
                    .addComponent(labelBin))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, Short.MAX_VALUE)
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
        ctrlPresentation.exit();
    }//GEN-LAST:event_labelExitMouseClicked

    /** @brief El form es minimitzarà.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post El form s'ha minimitzat.
     */
    private void labelMinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelMinimizeMouseClicked
        this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_labelMinimizeMouseClicked

    /** @brief Canvi de form de estadístiques a form de main menú.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post Ha canviat del form de estadístiques a form de main menú.
     */
    private void labelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBackMouseClicked
        ctrlPresentation.syncViewS_to_viewMM();
    }//GEN-LAST:event_labelBackMouseClicked

    /** @brief Canvi de posició del form.
     * 
     * @param evt S'utiltiza per saber la posició relativa del ratolí.
     * 
     * \pre <em>Cert.</em>
     * \post Canvia la posició relativa del form a on ha arrossegat el ratolí.
     */
    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jPanel1MouseDragged

    /** @brief Iniciar posició relativa del form a les variables.
     * 
     * @param evt S'utilitza per saber la posició relativa del ratolí.
     * 
     * \pre <em>Cert.</em>
     * \post Ha iniciat la posició relativa del form a les dos variables 2D.
     */
    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    /** @brief Eliminar tot el historial estadístic de la BD.
     * 
     * @param evt
     * 
     * \pre Cert.
     * \post S'ha eliminat tot el historial estadístic de la BD.
     */
    private void labelBinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBinMouseClicked
        try {
            ctrlPresentation.deleteTableContent();
            syncTable();
        } catch (IOException ex) {
            Logger.getLogger(viewStatistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_labelBinMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBack;
    private javax.swing.JLabel labelBin;
    private javax.swing.JLabel labelCrown;
    private javax.swing.JLabel labelExit;
    private javax.swing.JLabel labelMinimize;
    private javax.swing.JTable tableStatistics;
    // End of variables declaration//GEN-END:variables
}
