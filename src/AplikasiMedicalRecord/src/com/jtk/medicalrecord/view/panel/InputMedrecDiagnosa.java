/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.view.panel;

import com.jtk.medicalrecord.entity.Dosis;
import com.jtk.medicalrecord.util.CommonHelper;
import com.jtk.medicalrecord.util.MessageHelper;
import com.jtk.medicalrecord.view.dialog.ObatDialog;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author M Haska Ash Shiddiq
 */
public class InputMedrecDiagnosa extends javax.swing.JPanel {

    private final List<Dosis> dosises = new ArrayList<>();

    /**
     * Creates new form InputMedrecDiagnosa
     */
    public InputMedrecDiagnosa() {
        initComponents();
    }

    public void viewState() {
        txtDiagnosisBanding.setEditable(false);
        txtDiagnosisKerja.setEditable(false);
        txtPengobatan.setEditable(false);
        txtPrognosis.setEditable(false);
        btnHapus.setVisible(false);
        btnTambah.setVisible(false);
    }
    
    public void clear(){
        txtDiagnosisBanding.setText("");
        txtDiagnosisKerja.setText("");
        txtPengobatan.setText("");
        txtPrognosis.setText("");
        btnHapus.setText("");
        dosises.removeAll(dosises);
        createTableValue();
    }

    public void createTableValue() {
        Object[] columnsName = {"Nama Obat", "Dosis"};

        DefaultTableModel dtm = new DefaultTableModel(null, columnsName) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Dosis p : dosises) {
            Object[] o = new Object[2];
            o[0] = p.getObat().getObatNama();
            o[1] = p.getObatDosis();

            dtm.addRow(o);
        }
        tblObat.setModel(dtm);
        CommonHelper.resizeColumnWidth(tblObat);
    }

    public JTextArea getTxtDiagnosisBanding() {
        return txtDiagnosisBanding;
    }

    public JTextArea getTxtDiagnosisKerja() {
        return txtDiagnosisKerja;
    }

    public JTextArea getTxtPengobatan() {
        return txtPengobatan;
    }

    public JTextArea getTxtPrognosis() {
        return txtPrognosis;
    }

    public List<Dosis> getDosises() {
        return dosises;
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
        txtDiagnosisKerja = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiagnosisBanding = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtPengobatan = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtPrognosis = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblObat = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setMinimumSize(new java.awt.Dimension(240, 500));
        setPreferredSize(new java.awt.Dimension(250, 650));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Diagnosa");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel2.setText("Diagnosis Kerja");

        jScrollPane1.setMinimumSize(new java.awt.Dimension(53, 10));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(53, 10));

        txtDiagnosisKerja.setColumns(20);
        txtDiagnosisKerja.setRows(5);
        txtDiagnosisKerja.setMaximumSize(new java.awt.Dimension(53, 20));
        txtDiagnosisKerja.setMinimumSize(new java.awt.Dimension(53, 20));
        txtDiagnosisKerja.setPreferredSize(new java.awt.Dimension(53, 20));
        jScrollPane1.setViewportView(txtDiagnosisKerja);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel3.setText("Diagnosis Banding");

        jScrollPane2.setPreferredSize(new java.awt.Dimension(250, 100));

        txtDiagnosisBanding.setColumns(20);
        txtDiagnosisBanding.setRows(5);
        txtDiagnosisBanding.setMaximumSize(new java.awt.Dimension(53, 20));
        txtDiagnosisBanding.setMinimumSize(new java.awt.Dimension(53, 20));
        txtDiagnosisBanding.setPreferredSize(new java.awt.Dimension(53, 20));
        jScrollPane2.setViewportView(txtDiagnosisBanding);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel4.setText("Pengobatan");

        jScrollPane3.setMinimumSize(new java.awt.Dimension(53, 10));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(53, 10));

        txtPengobatan.setColumns(20);
        txtPengobatan.setRows(5);
        txtPengobatan.setMaximumSize(new java.awt.Dimension(53, 20));
        txtPengobatan.setMinimumSize(new java.awt.Dimension(53, 20));
        txtPengobatan.setPreferredSize(new java.awt.Dimension(53, 20));
        jScrollPane3.setViewportView(txtPengobatan);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel5.setText("Prognosis");

        jScrollPane4.setMinimumSize(new java.awt.Dimension(53, 10));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(53, 10));

        txtPrognosis.setColumns(20);
        txtPrognosis.setRows(5);
        txtPrognosis.setMaximumSize(new java.awt.Dimension(53, 20));
        txtPrognosis.setMinimumSize(new java.awt.Dimension(53, 20));
        txtPrognosis.setPreferredSize(new java.awt.Dimension(53, 20));
        jScrollPane4.setViewportView(txtPrognosis);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel6.setText("Obat");

        btnTambah.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jtk/medicalrecord/image/plus.png"))); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jtk/medicalrecord/image/trash.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.setMaximumSize(new java.awt.Dimension(93, 25));
        btnHapus.setMinimumSize(new java.awt.Dimension(93, 25));
        btnHapus.setPreferredSize(new java.awt.Dimension(93, 25));
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane6.setMaximumSize(new java.awt.Dimension(53, 100));
        jScrollPane6.setMinimumSize(new java.awt.Dimension(53, 100));
        jScrollPane6.setPreferredSize(new java.awt.Dimension(53, 100));

        tblObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Obat", "Dosis"
            }
        ));
        jScrollPane6.setViewportView(tblObat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(81, 81, 81))
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnTambah))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTambah)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        Dosis d = new Dosis();
        ObatDialog obatDialog = new ObatDialog(null, true, d);
        obatDialog.show();
        if (d.getDosisPK() != null) {
            dosises.add(d);
        }
        createTableValue();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        if (tblObat.isRowSelected(tblObat.getSelectedRow())) {
            dosises.remove(tblObat.getSelectedRow());
            createTableValue();
        } else {
            MessageHelper.addWarnMessage("Perhatian", "Harap pilih obat terlebih dahulu");
        }
    }//GEN-LAST:event_btnHapusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable tblObat;
    private javax.swing.JTextArea txtDiagnosisBanding;
    private javax.swing.JTextArea txtDiagnosisKerja;
    private javax.swing.JTextArea txtPengobatan;
    private javax.swing.JTextArea txtPrognosis;
    // End of variables declaration//GEN-END:variables
}
