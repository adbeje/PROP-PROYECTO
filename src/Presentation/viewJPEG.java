/** @file viewJPEG.java
 * @brief Aquesta és la view del selector d'opcions del JPEG.
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
 *     - import javax.swing.event.DocumentEvent
 *     - import javax.swing.event.DocumentListener
 *
 * @author Albert Pita Argemí
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
 * View del JPEG
 */

/** @class viewJPEG
 * @brief Aquesta és la view del selector d'opcions del JPEG.
 *
 * @author Albert Pita Argemí
 */
public class viewJPEG extends javax.swing.JFrame {

    /** @brief Instància del controlador de presentació.*/
    private final CtrlPresentation ctrlPresentation;
    
    /** @brief Posició x del ratolí.*/
    private int xMouse;
    /** @brief Posició y del ratolí.*/
    private int yMouse;

    /** @brief Creadora per defecte.
     * 
     * Crea un nou form viewJPEG.
     * 
     * @param cP Es el controlador de presentació.
     * 
     * \pre <em>Cert.</em>
     * \post S'ha creat una nova instància de viewJPEG.
     */
    public viewJPEG(CtrlPresentation cP) {
        this.setUndecorated(true);
        
        ctrlPresentation = cP;
        
        initComponents();
        
        this.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
        
        this.setSize(new Dimension(550, 350));
        this.setResizable(false);
        
        jPanel1.setBorder(new RoundedBorder(50));
        
        btnAfegirArxiu.setBorder(new RoundedBorder(40, 4, 4));
        btnAfegirArxiu.setContentAreaFilled(false);
        btnBuscar.setBorder(new RoundedBorder(40, 4, 4));
        btnBuscar.setContentAreaFilled(false);
        btnSelecciona.setBorder(new RoundedBorder(40, 4, 4));
        btnSelecciona.setContentAreaFilled(false);
        btnPrevisualitza.setBorder(new RoundedBorder(40, 4, 4));
        btnPrevisualitza.setContentAreaFilled(false);
        
        quality.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent de) {
                warn();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                //warn();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                warn();
            }
            
            public void warn() {
                String text = quality.getText();
                if (!text.matches("[0-9]+") || Integer.parseInt(text) > 10 || Integer.parseInt(text) < 0) {
                    JOptionPane.showMessageDialog(null, "El valor ha d'estar entre 0 i 10.", "Error", JOptionPane.WARNING_MESSAGE);  
                }
            }
        });
        
    }
    
    /** @brief Fa "visibles" els objectes del Form corresponent.
    * 
    * \pre <em>Cert.</em>
    * \post Ha fet "visible" els objectes del Form corresponent
    */
    public void makeVisible() {
        btnAfegirArxiu.setEnabled(true);
        btnBuscar.setEnabled(true);
        btnSelecciona.setEnabled(true);
        btnPrevisualitza.setEnabled(true);
        comboBoxQuality.setEnabled(true);
        in_path.setEnabled(true);
        jPanel1.setEnabled(true);
        jPanel2.setEnabled(true);
        labelAccions.setEnabled(true);
        labelArxiuGran.setEnabled(true);
        labelArxiusPetit.setEnabled(true);
        labelBack.setEnabled(true);
        labelCrown.setEnabled(true);
        labelExit.setEnabled(true);
        labelInPath.setEnabled(true);
        labelMinimize.setEnabled(true);
        labelOutPath.setEnabled(true);
        labelQuality.setEnabled(true);
        labelRatiCom.setEnabled(true);
        labelSeleccioArxius.setEnabled(true);
        labelTempsCom.setEnabled(true);
        out_path.setEnabled(true);
        quality.setEnabled(true);
        sliderQuality.setEnabled(true);
    }
    
    /** @brief Fa "invisibles" els objectes del Form corresponent.
    * 
    * \pre <em>Cert.</em>
    * \post Ha fet "invisibles" els objectes del Form corresponent
    */
    public void makeInvisible() {
        btnAfegirArxiu.setEnabled(false);
        btnBuscar.setEnabled(false);
        btnSelecciona.setEnabled(false);
        btnPrevisualitza.setEnabled(false);
        comboBoxQuality.setEnabled(false);
        in_path.setEnabled(false);
        jPanel1.setEnabled(false);
        jPanel2.setEnabled(false);
        labelAccions.setEnabled(false);
        labelArxiuGran.setEnabled(false);
        labelArxiusPetit.setEnabled(false);
        labelBack.setEnabled(false);
        labelCrown.setEnabled(false);
        labelExit.setEnabled(false);
        labelInPath.setEnabled(false);
        labelMinimize.setEnabled(false);
        labelOutPath.setEnabled(false);
        labelQuality.setEnabled(false);
        labelRatiCom.setEnabled(false);
        labelSeleccioArxius.setEnabled(false);
        labelTempsCom.setEnabled(false);
        out_path.setEnabled(false);
        quality.setEnabled(false);
        sliderQuality.setEnabled(false);
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
        jPanel2 = new javax.swing.JPanel();
        labelRatiCom = new javax.swing.JLabel();
        labelTempsCom = new javax.swing.JLabel();
        labelQuality = new javax.swing.JLabel();
        quality = new javax.swing.JTextField();
        comboBoxQuality = new javax.swing.JComboBox<>();
        sliderQuality = new javax.swing.JSlider();
        labelArxiusPetit = new javax.swing.JLabel();
        labelArxiuGran = new javax.swing.JLabel();
        btnPrevisualitza = new javax.swing.JButton();

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

        in_path.setForeground(new java.awt.Color(0, 0, 0));

        labelOutPath.setBackground(new java.awt.Color(255, 255, 255));
        labelOutPath.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        labelOutPath.setForeground(new java.awt.Color(0, 0, 0));
        labelOutPath.setText("out_path");

        out_path.setForeground(new java.awt.Color(0, 0, 0));

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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        labelRatiCom.setBackground(new java.awt.Color(255, 255, 255));
        labelRatiCom.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        labelRatiCom.setForeground(new java.awt.Color(0, 0, 0));
        labelRatiCom.setText("Rati de compressió:");

        labelTempsCom.setBackground(new java.awt.Color(255, 255, 255));
        labelTempsCom.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        labelTempsCom.setForeground(new java.awt.Color(0, 0, 0));
        labelTempsCom.setText("Temps de compressió:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelRatiCom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTempsCom, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(labelTempsCom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelRatiCom))
        );

        labelQuality.setBackground(new java.awt.Color(255, 255, 255));
        labelQuality.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        labelQuality.setForeground(new java.awt.Color(0, 0, 0));
        labelQuality.setText("Qualitat:");

        quality.setForeground(new java.awt.Color(0, 0, 0));
        quality.setText("0");

        comboBoxQuality.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        comboBoxQuality.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxQuality.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baixa", "Mitjana", "Alta" }));
        comboBoxQuality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxQualityActionPerformed(evt);
            }
        });

        sliderQuality.setBackground(new java.awt.Color(255, 255, 255));
        sliderQuality.setForeground(new java.awt.Color(0, 0, 0));
        sliderQuality.setMajorTickSpacing(1);
        sliderQuality.setMaximum(10);
        sliderQuality.setValue(0);
        sliderQuality.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderQualityStateChanged(evt);
            }
        });

        labelArxiusPetit.setBackground(new java.awt.Color(255, 255, 255));
        labelArxiusPetit.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        labelArxiusPetit.setForeground(new java.awt.Color(0, 0, 0));
        labelArxiusPetit.setText("Arxiu petit");

        labelArxiuGran.setBackground(new java.awt.Color(255, 255, 255));
        labelArxiuGran.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        labelArxiuGran.setForeground(new java.awt.Color(0, 0, 0));
        labelArxiuGran.setText("Arxiu gran");

        btnPrevisualitza.setBackground(new java.awt.Color(255, 255, 255));
        btnPrevisualitza.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        btnPrevisualitza.setForeground(new java.awt.Color(0, 0, 0));
        btnPrevisualitza.setText("Previsualitza");
        btnPrevisualitza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevisualitzaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelCrown)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelAccions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelMinimize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelExit))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelInPath)
                            .addComponent(labelOutPath))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(out_path, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                            .addComponent(in_path))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAfegirArxiu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelBack)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(labelQuality)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(quality, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(comboBoxQuality, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(labelArxiusPetit)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelArxiuGran))
                                .addComponent(sliderQuality, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelSeleccioArxius)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSelecciona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPrevisualitza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelAccions)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelCrown)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelMinimize)
                            .addComponent(labelExit))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelSeleccioArxius)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelInPath)
                    .addComponent(in_path, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAfegirArxiu, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelOutPath)
                    .addComponent(out_path, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelQuality)
                        .addComponent(quality, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboBoxQuality, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelArxiusPetit)
                    .addComponent(labelArxiuGran))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sliderQuality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrevisualitza)
                        .addGap(13, 13, 13)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
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
    
    /** @brief Canvi de form de selecctor d'arxius compressió manual a form de accions.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post Ha canviat del form de selecctor d'arxius compressió manual a form de accions.
     */
    private void labelAccionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAccionsMouseClicked
        resetVariables();
        ctrlPresentation.syncViewJPEG_to_viewA();
    }//GEN-LAST:event_labelAccionsMouseClicked
    
    /** @brief Canvi de form del selecctor d'arxius compressió manual a form de selecció d'algorismes.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post Ha canviat del form del selecctor d'arxius compressió manual a form de selecció d'algorismes.
     */
    private void labelBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelBackMouseClicked
        resetVariables();
        ctrlPresentation.syncViewJPEG_to_viewAlg();
    }//GEN-LAST:event_labelBackMouseClicked
    
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
        String qual = quality.getText();

        if(in.equals("") || out.equals("")) {
            JOptionPane.showMessageDialog(null, "Les direccions no son coherents", "Error", JOptionPane.WARNING_MESSAGE);
        } 
        else if (qual.equals("")) {
            JOptionPane.showMessageDialog(null, "No s'ha seleccionat cap qualitat", "Error", JOptionPane.WARNING_MESSAGE);
        }
        else {
            try {
                int compressionQuality = Integer.parseInt(qual) * 10;
                ctrlPresentation.manualCompressFileOptions(in, out, algorithmType, Integer.toString(compressionQuality));
                labelTempsCom.setText(labelTempsCom.getText() + ctrlPresentation.getTotalTime());
                labelRatiCom.setText(labelRatiCom.getText() + ctrlPresentation.getCompressRatio());
                JOptionPane.showMessageDialog(null, "Compressió finalitzada");
            } catch (IOException ex) {
                Logger.getLogger(viewJPEG.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }//GEN-LAST:event_btnSeleccionaActionPerformed

    /** @brief Canviar el valor dels objectes relacionats amb les millores en cas de rebre un event del combobox.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post S'ha canviat i concorda amb el valor del combobox.
     */
    private void comboBoxQualityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxQualityActionPerformed
        String selected_item = comboBoxQuality.getItemAt(comboBoxQuality.getSelectedIndex());
        switch (selected_item) {
            case "Baixa":
                quality.setText("0");
                sliderQuality.setValue(0);
                break;
            case "Mitjana":
                quality.setText("5");
                sliderQuality.setValue(5);
                break;
            case "Alta":
                quality.setText("10");
                sliderQuality.setValue(10);
                break;
        }
    }//GEN-LAST:event_comboBoxQualityActionPerformed

    /** @brief Canvi de posició del form.
     * 
     * @param evt S'utilitza per saber la posició relativa del ratolí.
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

    /** @brief Canviar el valor dels objectes relacionats amb les millores en cas de rebre un event del slider.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post S'ha canviat i concorda amb el valor del slider.
     */
    private void sliderQualityStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderQualityStateChanged
        int value = sliderQuality.getValue();
        quality.setText(Integer.toString(value));

        switch (value) {
            case 0:
                comboBoxQuality.setSelectedItem("Baixa");
                break;
            case 5:
                comboBoxQuality.setSelectedItem("Mitjana");
                break;
            case 10:
                comboBoxQuality.setSelectedItem("Alta");
                break;
        }
        
    }//GEN-LAST:event_sliderQualityStateChanged

    /** @brief Es mostrarà l'arxiu indicat al path d'entrada.
     * 
     * @param evt
     * 
     * \pre <em>Cert.</em>
     * \post S'ha mostrat l'arxiu indicat.
     */
    private void btnPrevisualitzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevisualitzaActionPerformed
        String in = in_path.getText();
        String out = out_path.getText();
        String algorithmType = ctrlPresentation.getAlgorithmType();
        String compOptions = quality.getText();
        
        if (in.equals("")) {
            JOptionPane.showMessageDialog(null, "No s'ha seleccionat cap arxiu per previsualitzar", "Error", JOptionPane.WARNING_MESSAGE);
        }
        else if(compOptions.equals("")) {
            JOptionPane.showMessageDialog(null, "No s'ha seleccionat cap qualitat", "Error", JOptionPane.WARNING_MESSAGE);
        }
        else {
            try {
                ctrlPresentation.manualCompressFileOptions(in, out + "Pre", algorithmType, compOptions);
                String deleteCompression = ctrlPresentation.getPath();
                ctrlPresentation.decompressFile(ctrlPresentation.getPath(), out + "View");
                String deleteDecompression = ctrlPresentation.getPath();
                
                viewPreviewImage display = new viewPreviewImage(ctrlPresentation);
                display.setVisible(true);
                
                ctrlPresentation.deleteFile(deleteCompression);
                ctrlPresentation.deleteFile(deleteDecompression);
                
            } catch (IOException ex) {
                Logger.getLogger(viewFileSelectorCompressionAutomatic.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
    }//GEN-LAST:event_btnPrevisualitzaActionPerformed
   
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
    private javax.swing.JButton btnPrevisualitza;
    private javax.swing.JButton btnSelecciona;
    private javax.swing.JComboBox<String> comboBoxQuality;
    private javax.swing.JTextField in_path;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelAccions;
    private javax.swing.JLabel labelArxiuGran;
    private javax.swing.JLabel labelArxiusPetit;
    private javax.swing.JLabel labelBack;
    private javax.swing.JLabel labelCrown;
    private javax.swing.JLabel labelExit;
    private javax.swing.JLabel labelInPath;
    private javax.swing.JLabel labelMinimize;
    private javax.swing.JLabel labelOutPath;
    private javax.swing.JLabel labelQuality;
    private javax.swing.JLabel labelRatiCom;
    private javax.swing.JLabel labelSeleccioArxius;
    private javax.swing.JLabel labelTempsCom;
    private javax.swing.JTextField out_path;
    private javax.swing.JTextField quality;
    private javax.swing.JSlider sliderQuality;
    // End of variables declaration//GEN-END:variables
}
