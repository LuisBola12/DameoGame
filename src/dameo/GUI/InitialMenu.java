/**
 *
 * @authors Javier Molina, Carlos Solorzano, Luis Bolaños, Jarod Venegas and 
 * Aaron Campos.
 */
package dameo.GUI;

import javax.swing.JOptionPane;

/**
 * 
 * @author Clase que hereda de JFrame para poder desplegar el menú inicial del
 *         juego.
 */
public class InitialMenu extends javax.swing.JFrame {

    /**
     * Constructor de la clase
     */
    public InitialMenu() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        Titulo = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        creditButton = new javax.swing.JButton();
        boardBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Background.setBackground(new java.awt.Color(51, 102, 0));
        Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Titulo.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        Titulo.setForeground(new java.awt.Color(51, 51, 0));
        Titulo.setText("Dameo ");
        Background.add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 190, 60));

        exitButton.setBackground(new java.awt.Color(102, 102, 0));
        exitButton.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        exitButton.setForeground(java.awt.SystemColor.info);
        exitButton.setText("Salir");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        Background.add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 200, 60));

        playButton.setBackground(new java.awt.Color(102, 102, 0));
        playButton.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        playButton.setForeground(java.awt.SystemColor.info);
        playButton.setText("Jugar");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });
        Background.add(playButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 200, 60));

        creditButton.setBackground(new java.awt.Color(102, 102, 0));
        creditButton.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        creditButton.setForeground(java.awt.SystemColor.info);
        creditButton.setText("Créditos");
        creditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditButtonActionPerformed(evt);
            }
        });
        Background.add(creditButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 200, 60));

        boardBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dameoGUI/images/board.png"))); // NOI18N
        Background.add(boardBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 510));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método encargado de manejar los eventos del objeto exitButton. Se hace
     * invisible la ventana actual para posteriormente cerrar el programa.
     * 
     * @param evt evento a manejar
     */
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_exitButtonActionPerformed
        this.setVisible(false);
        System.exit(0);
    }// GEN-LAST:event_exitButtonActionPerformed

    /**
     * Método encargado de manejar los eventos del objeto playButton. Se crea un
     * objeto SeleccionInicial para poder acceder al siguiente menú del jeugo, se
     * hace invisible la ventana actual y se hace visible la ventana
     * SeleccionInicial.
     * 
     * @param evt evento a manejar
     */
    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_playButtonActionPerformed
        SelectionMenu init = new SelectionMenu();
        this.setVisible(false);
        init.setVisible(true);
    }// GEN-LAST:event_playButtonActionPerformed

    /**
     * Método encargado de manejar los eventos del objeto loadButton.
     * 
     * @param evt evento a manejar
     */
    private void creditButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_loadButtonActionPerformed
        JOptionPane.showMessageDialog(null, "Aaron Campos         B91554\nCarlos Solórzano     B87741\nJarod Venegas         B98410\nJavier"
                + " Molina            B84981\nLuis Bolaños             B91145","Autores",JOptionPane.INFORMATION_MESSAGE);
    }// GEN-LAST:event_loadButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel boardBackground;
    private javax.swing.JButton creditButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton playButton;
    // End of variables declaration//GEN-END:variables
}
