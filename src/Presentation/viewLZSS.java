/** @file viewLZSS.java
 * @brief Aquesta classe és la view del selector d'arxius del LZSS.
 *
 * Els imports que utilitza són:
 *     - import java.awt.Color
 *     - import java.awt.Dimension
 *     - import java.awt.Frame
 *     - import java.io.File
 *     - import java.io.IOException
 *     - import java.util.logging.Level
 *     - import java.util.logging.Logger
 *     - import javax.swing.JFileChooser
 *     - import javax.swing.JOptionPane
 *
 * @author Adrià Ventura i Herce
 */
package Presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * Classe viewLZSS
 */

/** @class viewLZSS
 * @brief Aquest és la view del selector d'arxius del LZSS.
 *
 * @author Adria Ventura i Herce
 */
public class viewLZSS extends javax.swing.JFrame {
    /** @brief Instància del controlador de persistentació, inicialitzada al crear el controlador de presentació.*/
    private final CtrlPresentation ctrlPresentation;
    /** @brief Posició X del ratolí.*/
    private int xMouse;
    /** @brief Posició Y del ratolí.*/
    private int yMouse;
    
    /** @brief Creadora per defecte de viewLZSS.
     * 
     * @param cP Es el controlador de presentació.
     * 
     * \pre <em>Cert.</em>
     * \post Ha creat una instància de viewLZSS.
     */
    public viewLZSS(CtrlPresentation cP) {
        this.setUndecorated(true);
        
        ctrlPresentation = cP;
        
        initComponents();
        
        this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
        
        this.setSize(new Dimension(500, 380));
        this.setResizable(false);
        
        slectorFiles.setBorder(new RoundedBorder(50));
        btnAfegirArxiu.setBorder(new RoundedBorder(40, 4 , 4));
        btnAfegirArxiu.setContentAreaFilled(false);
        btnBuscar.setBorder(new RoundedBorder(40, 4, 4));
        btnBuscar.setContentAreaFilled(false);
        btnSelecciona.setBorder(new RoundedBorder(40, 4, 4));
        btnSelecciona.setContentAreaFilled(false);
        btnPrevisualiza.setBorder(new RoundedBorder(40, 4, 4));
        btnPrevisualiza.setContentAreaFilled(false);
    }
    
    /** @brief Fa "visibles" els objectes del Form corresponent.
    * 
    * \pre <em>Cert.</em>
    * \post Ha fet "visible" els objectes del Form corresponent
    */
    public void makeVisible() {
        //Activa panels
        panelEstadistiques.setEnabled(true);
        slectorFiles.setEnabled(true);
        
        //Activa botons
        btnAfegirArxiu.setEnabled(true);
        btnBuscar.setEnabled(true);
        btnSelecciona.setEnabled(true);
        btnPrevisualiza.setEnabled(true);
        
        //Activa labels
        labelInPath.setEnabled(true);
        labelOutPath.setEnabled(true);
        labelTempsCom.setEnabled(true);
        labelCrown.setEnabled(true);
        labelRatiCom.setEnabled(true);
        labelBack.setEnabled(true);
        labelAccions.setEnabled(true);
        
        //Desactiva comboBox
        cBWinSize.setEnabled(true);
        cBMaxSeqLen.setEnabled(true);
        cBMinSeqLen.setEnabled(true);
        
        //Activa textField
        in_path.setEnabled(true);
        out_path.setEnabled(true);
    }
    
    /**
    * @brief Fa "invisibles" els objectes del Form corresponent.
    * 
    * \pre <em>Cert.</em>
    * \post Ha fet "invisibles" els objectes del Form corresponent
    */
    public void makeInvisible() {
        //Desactiva botons
        btnAfegirArxiu.setEnabled(false);
        btnBuscar.setEnabled(false);
        btnSelecciona.setEnabled(false);
        btnPrevisualiza.setEnabled(false);
        
        //Desactiva labels
        labelInPath.setEnabled(false);
        labelOutPath.setEnabled(false);
        labelTempsCom.setEnabled(false);
        labelCrown.setEnabled(false);
        labelRatiCom.setEnabled(false);
        labelBack.setEnabled(false);
        labelAccions.setEnabled(false);
        
        //Desactiva textField
        in_path.setEnabled(false);
        out_path.setEnabled(false);
        
        //Desactiva comboBox
        cBWinSize.setEnabled(false);
        cBMaxSeqLen.setEnabled(false);
        cBMinSeqLen.setEnabled(false);
        
        //Desactiva panels
        panelEstadistiques.setEnabled(false);
        slectorFiles.setEnabled(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        slectorFiles = new javax.swing.JPanel();
        labelExit = new javax.swing.JLabel();
        labelMinimize = new javax.swing.JLabel();
        labelCrown = new javax.swing.JLabel();
        labelAccions = new javax.swing.JLabel();
        labelBack = new javax.swing.JLabel();
        labelSeleccioArxius = new javax.swing.JLabel();
        labelInPath = new javax.swing.JLabel();
        in_path = new javax.swing.JTextField();
        labelOutPath = new javax.swing.JLabel();
        out_path = new javax.swing.JTextField();
        btnAfegirArxiu = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnSelecciona = new javax.swing.JButton();
        panelEstadistiques = new javax.swing.JPanel();
        labelRatiCom = new javax.swing.JLabel();
        labelTempsCom = new javax.swing.JLabel();
        labelWinSize = new javax.swing.JLabel();
        cBWinSize = new javax.swing.JComboBox<>();
        labelMaxSeqLen = new javax.swing.JLabel();
        cBMaxSeqLen = new javax.swing.JComboBox<>();
        labelMinSeqLen = new javax.swing.JLabel();
        cBMinSeqLen = new javax.swing.JComboBox<>();
        btnPrevisualiza = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        slectorFiles.setBackground(new java.awt.Color(255, 255, 255));
        slectorFiles.setOpaque(false);
        slectorFiles.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                slectorFilesMouseDragged(evt);
            }
        });
        slectorFiles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                slectorFilesMousePressed(evt);
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

        labelAccions.setBackground(new java.awt.Color(255, 255, 255));
        labelAccions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/AccionsMitjana.png"))); // NOI18N
        labelAccions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAccionsMouseClicked(evt);
            }
        });

        labelBack.setBackground(new java.awt.Color(255, 255, 255));
        labelBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/EndarrereMitjana.png"))); // NOI18N
        labelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelBackMouseClicked(evt);
            }
        });

        labelSeleccioArxius.setBackground(new java.awt.Color(255, 255, 255));
        labelSeleccioArxius.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        labelSeleccioArxius.setForeground(new java.awt.Color(0, 0, 0));
        labelSeleccioArxius.setText("Selecció d'arxius:");

        labelInPath.setBackground(new java.awt.Color(255, 255, 255));
        labelInPath.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        labelInPath.setForeground(new java.awt.Color(0, 0, 0));
        labelInPath.setText("in_path");

        labelOutPath.setBackground(new java.awt.Color(255, 255, 255));
        labelOutPath.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        labelOutPath.setForeground(new java.awt.Color(0, 0, 0));
        labelOutPath.setText("out_path");

        btnAfegirArxiu.setBackground(new java.awt.Color(255, 255, 255));
        btnAfegirArxiu.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        btnAfegirArxiu.setForeground(new java.awt.Color(0, 0, 0));
        btnAfegirArxiu.setText("Afegir arxiu");
        btnAfegirArxiu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAfegirArxiuActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscar.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(0, 0, 0));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnSelecciona.setBackground(new java.awt.Color(255, 255, 255));
        btnSelecciona.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        btnSelecciona.setForeground(new java.awt.Color(0, 0, 0));
        btnSelecciona.setText("Selecciona");
        btnSelecciona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionaActionPerformed(evt);
            }
        });

        panelEstadistiques.setBackground(new java.awt.Color(255, 255, 255));

        labelRatiCom.setBackground(new java.awt.Color(255, 255, 255));
        labelRatiCom.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        labelRatiCom.setForeground(new java.awt.Color(0, 0, 0));
        labelRatiCom.setText("Rati de compressió:");

        labelTempsCom.setBackground(new java.awt.Color(255, 255, 255));
        labelTempsCom.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        labelTempsCom.setForeground(new java.awt.Color(0, 0, 0));
        labelTempsCom.setText("Temps de compressió:");

        javax.swing.GroupLayout panelEstadistiquesLayout = new javax.swing.GroupLayout(panelEstadistiques);
        panelEstadistiques.setLayout(panelEstadistiquesLayout);
        panelEstadistiquesLayout.setHorizontalGroup(
            panelEstadistiquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEstadistiquesLayout.createSequentialGroup()
                .addGroup(panelEstadistiquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelRatiCom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTempsCom, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelEstadistiquesLayout.setVerticalGroup(
            panelEstadistiquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEstadistiquesLayout.createSequentialGroup()
                .addComponent(labelTempsCom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelRatiCom))
        );

        labelWinSize.setBackground(new java.awt.Color(255, 255, 255));
        labelWinSize.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        labelWinSize.setForeground(new java.awt.Color(0, 0, 0));
        labelWinSize.setText("Window Size :");

        cBWinSize.setBackground(new java.awt.Color(255, 255, 255));
        cBWinSize.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        cBWinSize.setForeground(new java.awt.Color(0, 0, 0));
        cBWinSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "512", "1024", "2048", "4096", "8192" }));
        cBWinSize.setSelectedIndex(3);
        cBWinSize.setToolTipText("");

        labelMaxSeqLen.setBackground(new java.awt.Color(255, 255, 255));
        labelMaxSeqLen.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        labelMaxSeqLen.setForeground(new java.awt.Color(0, 0, 0));
        labelMaxSeqLen.setText("Max Sequence Lenght :");

        cBMaxSeqLen.setBackground(new java.awt.Color(255, 255, 255));
        cBMaxSeqLen.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        cBMaxSeqLen.setForeground(new java.awt.Color(0, 0, 0));
        cBMaxSeqLen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "4", "8", "16", "32", "64" }));
        cBMaxSeqLen.setSelectedIndex(3);

        labelMinSeqLen.setBackground(new java.awt.Color(255, 255, 255));
        labelMinSeqLen.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        labelMinSeqLen.setForeground(new java.awt.Color(0, 0, 0));
        labelMinSeqLen.setText("Min Sequence Lenght :");

        cBMinSeqLen.setBackground(new java.awt.Color(255, 255, 255));
        cBMinSeqLen.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        cBMinSeqLen.setForeground(new java.awt.Color(0, 0, 0));
        cBMinSeqLen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5", "6", "8", "9" }));
        cBMinSeqLen.setSelectedIndex(1);

        btnPrevisualiza.setBackground(new java.awt.Color(255, 255, 255));
        btnPrevisualiza.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        btnPrevisualiza.setForeground(new java.awt.Color(0, 0, 0));
        btnPrevisualiza.setText("Previsualitza");
        btnPrevisualiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevisualizaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout slectorFilesLayout = new javax.swing.GroupLayout(slectorFiles);
        slectorFiles.setLayout(slectorFilesLayout);
        slectorFilesLayout.setHorizontalGroup(
            slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slectorFilesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(slectorFilesLayout.createSequentialGroup()
                        .addComponent(labelCrown)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelAccions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelMinimize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelExit))
                    .addGroup(slectorFilesLayout.createSequentialGroup()
                        .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(slectorFilesLayout.createSequentialGroup()
                                    .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelSeleccioArxius)
                                        .addComponent(labelWinSize, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panelEstadistiques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, slectorFilesLayout.createSequentialGroup()
                                    .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(slectorFilesLayout.createSequentialGroup()
                                            .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(labelInPath)
                                                .addComponent(labelOutPath))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(in_path)
                                                .addComponent(out_path)))
                                        .addGroup(slectorFilesLayout.createSequentialGroup()
                                            .addComponent(labelMaxSeqLen, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cBMaxSeqLen, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(slectorFilesLayout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(cBWinSize, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(slectorFilesLayout.createSequentialGroup()
                                            .addComponent(labelMinSeqLen, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cBMinSeqLen, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(12, 12, 12)))
                            .addGroup(slectorFilesLayout.createSequentialGroup()
                                .addComponent(labelBack)
                                .addGap(335, 335, 335)))
                        .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSelecciona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAfegirArxiu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPrevisualiza))))
                .addContainerGap())
        );
        slectorFilesLayout.setVerticalGroup(
            slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slectorFilesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelAccions)
                    .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelCrown)
                        .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelMinimize)
                            .addComponent(labelExit))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelSeleccioArxius)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelInPath)
                    .addComponent(in_path, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAfegirArxiu, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelOutPath)
                    .addComponent(out_path, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelWinSize)
                    .addComponent(cBWinSize, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cBMaxSeqLen, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMaxSeqLen))
                .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(slectorFilesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cBMinSeqLen, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMinSeqLen))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(panelEstadistiques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, slectorFilesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrevisualiza)
                        .addGap(13, 13, 13)))
                .addGroup(slectorFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelBack, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSelecciona, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(slectorFiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slectorFiles, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** @brief Iniciar posició relativa del form a les variables.
     * 
     * @param evt S'utiliza per saber la posició relativa del ratolí.
     * 
     * \pre <em>Cert.</em>
     * \post Ha iniciat la posició relativa del form a les dos variables 2D.
     */
    private void slectorFilesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slectorFilesMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_slectorFilesMousePressed

    /** @brief Canvi de posició del form.
     * 
     * @param evt S'utilitza per saber la posició relativa del ratolí.
     * 
     * \pre <em>Cert.</em>
     * \post Canvia la posició relativa del form a on ha arrossegat el ratolí.
     */
    private void slectorFilesMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_slectorFilesMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_slectorFilesMouseDragged

    /** @brief Compressió del arxiu a tractar amb opcions extra.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post S'ha comprès l'arxiu a tractar i s'ha deixat al path destí seleccionat.
     */
    private void btnSeleccionaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionaActionPerformed
        String in = in_path.getText();
        String out = out_path.getText();
        String algorithmType = ctrlPresentation.getAlgorithmType();
        String compOptions = (String)cBWinSize.getSelectedItem() + " "
        + (String)cBMaxSeqLen.getSelectedItem() + " "
        + (String)cBMinSeqLen.getSelectedItem();

        if(in.equals("") || out.equals("")) {
            JOptionPane.showMessageDialog(null, "Les direccions no son coherents", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ctrlPresentation.manualCompressFileOptions(in, out, algorithmType, compOptions);
                labelTempsCom.setText(labelTempsCom.getText() + ctrlPresentation.getTotalTime());
                labelRatiCom.setText(labelRatiCom.getText() + ctrlPresentation.getCompressRatio());
                JOptionPane.showMessageDialog(null, "Compressió finalitzada");
            } catch (IOException ex) {
                Logger.getLogger(viewLZSS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSeleccionaActionPerformed

    /** @brief Selecció del path desti on volem que estigui la sortida.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post S'ha obert un fileChooser i s'ha seleccionat el path destí.
     */
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            out_path.setText(file.getPath());
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    /** @brief Selecció del path desti del arxiu origen a tractar.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post S'ha obert un fileChooser i s'ha seleccionat el path del arxiu origen.
     */
    private void btnAfegirArxiuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAfegirArxiuActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        resetVariables();
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            in_path.setText(file.getPath());
        }
        if(!in_path.getText().equals("")) {
            out_path.setText(in_path.getText().substring(0,in_path.getText().lastIndexOf('/')) + "/comp_" +
                in_path.getText().substring(in_path.getText().lastIndexOf('/') + 1,in_path.getText().lastIndexOf('.')));
        } else {
            JOptionPane.showMessageDialog(null, "No s'ha seleccionat cap arxiu per descomprimir", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAfegirArxiuActionPerformed

    /** @brief Canvi de form del selecctor d'arxius compressió manual a form de selecció d'algorismes.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post Ha canviat del form del selecctor d'arxius compressió manual a form de selecció d'algorismes.
     */
    private void labelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBackMouseClicked
        resetVariables();
        cBWinSize.setSelectedIndex(3);
        cBMaxSeqLen.setSelectedIndex(3);
        cBMinSeqLen.setSelectedIndex(1);
        ctrlPresentation.syncViewLZSS_to_viewAlg();
    }//GEN-LAST:event_labelBackMouseClicked

    /** @brief Canvi de form de selecctor d'arxius compressió manual a form de accions.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post Ha canviat del form de selecctor d'arxius compressió manual a form de accions.
     */
    private void labelAccionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAccionsMouseClicked
        resetVariables();
        ctrlPresentation.syncViewLZSS_to_viewA();
    }//GEN-LAST:event_labelAccionsMouseClicked

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

    /** @brief El form es tancarà.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post El form es tanca.
     */
    private void labelExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelExitMouseClicked
        ctrlPresentation.exit();
    }//GEN-LAST:event_labelExitMouseClicked

    /** @brief Es mostrarà l'arxiu indicat al path d'entrada.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post S'ha mostrat l'arxiu indicat.
     */
    private void btnPrevisualizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevisualizaActionPerformed
        String in = in_path.getText();
        String out = out_path.getText();
        String algorithmType = ctrlPresentation.getAlgorithmType();
        String compOptions = (String)cBWinSize.getSelectedItem() + " "
        + (String)cBMaxSeqLen.getSelectedItem() + " "
        + (String)cBMinSeqLen.getSelectedItem();
        
        if (in.equals("")) {
            JOptionPane.showMessageDialog(null, "No s'ha seleccionat cap arxiu per previsualitzar", "Error", JOptionPane.WARNING_MESSAGE);
        }
        else {
            try {
                ctrlPresentation.manualCompressFileOptions(in, out + "Pre", algorithmType, compOptions);
                String deleteCompression = ctrlPresentation.getPath();
                ctrlPresentation.decompressFile(ctrlPresentation.getPath(), out + "View");
                String deleteDecompression = ctrlPresentation.getPath();
                
                viewPreviewText display = new viewPreviewText(ctrlPresentation);
                display.setVisible(true);
                
                ctrlPresentation.deleteFile(deleteCompression);
                ctrlPresentation.deleteFile(deleteDecompression);
                
            } catch (IOException ex) {
                Logger.getLogger(viewFileSelectorCompressionAutomatic.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
    }//GEN-LAST:event_btnPrevisualizaActionPerformed
    
    /** @brief Resetejar els objectes modificats durant el procès de selecció.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post S'han resetejat els objectes swing del form.
     */
    private void resetVariables() {
        in_path.setText("");
        out_path.setText("");
        labelTempsCom.setText("Temps de compressió: ");
        labelRatiCom.setText("Rati de compressió: ");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAfegirArxiu;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnPrevisualiza;
    private javax.swing.JButton btnSelecciona;
    private javax.swing.JComboBox<String> cBMaxSeqLen;
    private javax.swing.JComboBox<String> cBMinSeqLen;
    private javax.swing.JComboBox<String> cBWinSize;
    private javax.swing.JTextField in_path;
    private javax.swing.JLabel labelAccions;
    private javax.swing.JLabel labelBack;
    private javax.swing.JLabel labelCrown;
    private javax.swing.JLabel labelExit;
    private javax.swing.JLabel labelInPath;
    private javax.swing.JLabel labelMaxSeqLen;
    private javax.swing.JLabel labelMinSeqLen;
    private javax.swing.JLabel labelMinimize;
    private javax.swing.JLabel labelOutPath;
    private javax.swing.JLabel labelRatiCom;
    private javax.swing.JLabel labelSeleccioArxius;
    private javax.swing.JLabel labelTempsCom;
    private javax.swing.JLabel labelWinSize;
    private javax.swing.JTextField out_path;
    private javax.swing.JPanel panelEstadistiques;
    private javax.swing.JPanel slectorFiles;
    // End of variables declaration//GEN-END:variables
}
