/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIQytetet;

/**
 *
 * @author fran
 */
public class VistaJugador extends javax.swing.JPanel {

    /**
     * Creates new form VistaJugador
     */
    public VistaJugador() {
        initComponents();
    }

    public void actualizar(String descripcionJugador) {
        this.jtTextoJugador.setText(descripcionJugador);
        this.repaint(); //Investiga para qué sirven estos métodos
        this.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtTextoJugador = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(0, 23, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jugador", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        setForeground(new java.awt.Color(0, 23, 255));
        setToolTipText("");

        jtTextoJugador.setEditable(false);
        jtTextoJugador.setColumns(20);
        jtTextoJugador.setRows(5);
        jScrollPane1.setViewportView(jtTextoJugador);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jtTextoJugador;
    // End of variables declaration//GEN-END:variables
}
