/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.view.dialog;

import com.jtk.medicalrecord.entity.Dosis;
import com.jtk.medicalrecord.entity.DosisPK;
import com.jtk.medicalrecord.entity.Obat;
import com.jtk.medicalrecord.util.AsyncProgress;
import com.jtk.medicalrecord.util.MessageHelper;

/**
 *
 * @author Zulkhair Abdullah D
 */
public class ObatDialog extends javax.swing.JDialog {

    private final Obat obat = new Obat();
    private final Dosis dosis;

    /**
     * Creates new form SearchObatDialog
     *
     * @param parent
     * @param modal
     * @param dosis
     */
    public ObatDialog(java.awt.Frame parent, boolean modal, Dosis dosis) {
        super(parent, modal);
        initComponents();
        this.dosis = dosis;
        setLocationRelativeTo(null);
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
        btnCari = new javax.swing.JButton();
        txtObat = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDosis = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHari = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Input Obat");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnCari.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jtk/medicalrecord/image/search-mini.png"))); // NOI18N
        btnCari.setText("Cari");
        btnCari.setMaximumSize(new java.awt.Dimension(93, 25));
        btnCari.setMinimumSize(new java.awt.Dimension(93, 25));
        btnCari.setPreferredSize(new java.awt.Dimension(93, 25));
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        txtObat.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel1.setText("Obat");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel2.setText("Dosis");

        jLabel3.setText("x");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel4.setText("/ Hari");

        btnSubmit.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jtk/medicalrecord/image/check.png"))); // NOI18N
        btnSubmit.setText("Submit");
        btnSubmit.setMaximumSize(new java.awt.Dimension(93, 25));
        btnSubmit.setMinimumSize(new java.awt.Dimension(93, 25));
        btnSubmit.setPreferredSize(new java.awt.Dimension(93, 25));
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDosis, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHari, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtObat, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtObat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtHari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        SearchObatDialog dialog = new SearchObatDialog(null, true, obat);
        dialog.show();
        if (obat.getObatId() != null && !obat.getObatId().isEmpty()) {
            txtObat.setText(obat.getObatNama());
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        if (obat.getObatId() != null && !obat.getObatId().isEmpty()) {
            if (txtDosis.getText().isEmpty()) {
                MessageHelper.addWarnMessage("Perhatian", "Harap isi jumlah dosis");
            } else if (txtObat.getText().isEmpty()) {
                MessageHelper.addWarnMessage("Perhatian", "Harap isi per hari");
            } else {
                LoadingDialog dialog = new LoadingDialog(new AsyncProgress() {
                    @Override
                    public void done() {
                    }

                    @Override
                    public void doInBackground() throws Exception {
                        DosisPK dosisPK = new DosisPK();
                        dosisPK.setObatId(obat.getObatId());

                        dosis.setDosisPK(dosisPK);
                        dosis.setObat(obat);
                        dosis.setObatDosis(txtDosis.getText() + "x" + txtHari.getText());
                    }
                }, null, true);
                dialog.start();
                this.dispose();
            }
        } else {
            MessageHelper.addWarnMessage("Perhatian", "Harap pilih obat terlebih dahulu");
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtDosis;
    private javax.swing.JTextField txtHari;
    private javax.swing.JTextField txtObat;
    // End of variables declaration//GEN-END:variables
}
