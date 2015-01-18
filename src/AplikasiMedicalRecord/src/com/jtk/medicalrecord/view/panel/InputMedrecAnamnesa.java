/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.view.panel;

import javax.swing.JTextArea;

/**
 *
 * @author M Haska Ash Shiddiq
 */
public class InputMedrecAnamnesa extends javax.swing.JPanel {

    /**
     * Creates new form InputMedrec
     */
    public InputMedrecAnamnesa() {
        initComponents();
    }

    public void viewState() {
        txtAnamnesaKhusus.setEditable(false);
        txtKeluhanUtama.setEditable(false);
        txtRiwayatPenyakitDahulu.setEditable(false);
        txtRiwayatPenyakitKeluarga.setEditable(false);
    }

    public void clear() {
        txtAnamnesaKhusus.setText("");
        txtKeluhanUtama.setText("");
        txtRiwayatPenyakitDahulu.setText("");
        txtRiwayatPenyakitKeluarga.setText("");
    }

    public JTextArea getTxtAnamnesaKhusus() {
        return txtAnamnesaKhusus;
    }

    public JTextArea getTxtKeluhanUtama() {
        return txtKeluhanUtama;
    }

    public JTextArea getTxtRiwayatPenyakitDahulu() {
        return txtRiwayatPenyakitDahulu;
    }

    public JTextArea getTxtRiwayatPenyakitKeluarga() {
        return txtRiwayatPenyakitKeluarga;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtKeluhanUtama = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAnamnesaKhusus = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtRiwayatPenyakitDahulu = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtRiwayatPenyakitKeluarga = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setMinimumSize(new java.awt.Dimension(240, 400));
        setPreferredSize(new java.awt.Dimension(240, 600));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Anamnesa");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel2.setText("Keluhan Utama");

        txtKeluhanUtama.setColumns(20);
        txtKeluhanUtama.setRows(5);
        txtKeluhanUtama.setMinimumSize(new java.awt.Dimension(53, 20));
        txtKeluhanUtama.setPreferredSize(new java.awt.Dimension(53, 20));
        jScrollPane1.setViewportView(txtKeluhanUtama);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel3.setText("Anamnesa Khusus");

        txtAnamnesaKhusus.setColumns(20);
        txtAnamnesaKhusus.setRows(5);
        txtAnamnesaKhusus.setMinimumSize(new java.awt.Dimension(53, 20));
        txtAnamnesaKhusus.setPreferredSize(new java.awt.Dimension(53, 20));
        jScrollPane2.setViewportView(txtAnamnesaKhusus);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel4.setText("Riwayat Penyakit Dahulu");

        txtRiwayatPenyakitDahulu.setColumns(20);
        txtRiwayatPenyakitDahulu.setRows(5);
        txtRiwayatPenyakitDahulu.setMinimumSize(new java.awt.Dimension(53, 20));
        txtRiwayatPenyakitDahulu.setPreferredSize(new java.awt.Dimension(53, 20));
        jScrollPane3.setViewportView(txtRiwayatPenyakitDahulu);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel5.setText("Riwayat Penyakit Keluarga");

        txtRiwayatPenyakitKeluarga.setColumns(20);
        txtRiwayatPenyakitKeluarga.setRows(5);
        txtRiwayatPenyakitKeluarga.setMinimumSize(new java.awt.Dimension(53, 20));
        txtRiwayatPenyakitKeluarga.setPreferredSize(new java.awt.Dimension(53, 20));
        jScrollPane5.setViewportView(txtRiwayatPenyakitKeluarga);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea txtAnamnesaKhusus;
    private javax.swing.JTextArea txtKeluhanUtama;
    private javax.swing.JTextArea txtRiwayatPenyakitDahulu;
    private javax.swing.JTextArea txtRiwayatPenyakitKeluarga;
    // End of variables declaration//GEN-END:variables
}
