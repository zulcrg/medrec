/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.view.dialog;

import com.jtk.medicalrecord.entity.Obat;
import com.jtk.medicalrecord.entity.Pasien;
import com.jtk.medicalrecord.jpacontroller.PasienJpaController;
import com.jtk.medicalrecord.util.AsyncProgress;
import com.jtk.medicalrecord.util.CommonHelper;
import com.jtk.medicalrecord.util.MessageHelper;
import com.zlib.util.ZClass;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zulkhair Abdullah D
 */
public class SearchPasienDialog extends javax.swing.JDialog {

    private final PasienJpaController pasienJpaController = new PasienJpaController();
    private List<Pasien> pasiens = new ArrayList<>();
    private final Pasien pasien;
    boolean first = true;

    /**
     * Creates new form SearchPasienDialog
     *
     * @param parent
     * @param modal
     * @param pasien
     */
    public SearchPasienDialog(java.awt.Frame parent, boolean modal, Pasien pasien) {
        super(parent, modal);
        initComponents();
        this.pasien = pasien;
        LoadingDialog dialog = new LoadingDialog(new AsyncProgress() {

            @Override
            public void done() {
            }

            @Override
            public void doInBackground() throws Exception {
                pasiens = pasienJpaController.findPasienEntities();
                createTableValue();
                setLocationRelativeTo(null);
            }
        }, null, true);
        dialog.start();
    }

    private void createTableValue() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Object[] columnsName = {"NIK", "Nama", "Tempat Lahir", "Tanggal Lahir", "Alamat"};

        DefaultTableModel dtm = new DefaultTableModel(null, columnsName) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Pasien p : pasiens) {
            Object[] o = new Object[5];
            o[0] = p.getPasId();
            o[1] = p.getPasNama();
            o[2] = p.getPasTmpLhr();
            o[3] = sdf.format(p.getPasTglLhr());
            o[4] = p.getPasAlamat();

            dtm.addRow(o);
        }
        tblPasien.setModel(dtm);
        CommonHelper.resizeColumnWidth(tblPasien);
    }

    private void select() {
        if (tblPasien.isRowSelected(tblPasien.getSelectedRow())) {
            Pasien p = pasiens.get(tblPasien.getSelectedRow());
            ZClass.copyClass(p, pasien);
            this.dispose();
        } else {
            MessageHelper.addWarnMessage("Perhatian", "Harap pilih pasien terlebih dahulu");
        }
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
        btnPilih = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPasien = new javax.swing.JTable();
        textSearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnPilih.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        btnPilih.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jtk/medicalrecord/image/check.png"))); // NOI18N
        btnPilih.setText("Pilih");
        btnPilih.setMaximumSize(new java.awt.Dimension(93, 25));
        btnPilih.setMinimumSize(new java.awt.Dimension(93, 25));
        btnPilih.setPreferredSize(new java.awt.Dimension(93, 25));
        btnPilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jtk/medicalrecord/image/plus.png"))); // NOI18N
        jButton1.setText("Tambah Pasien");
        jButton1.setMaximumSize(new java.awt.Dimension(140, 25));
        jButton1.setMinimumSize(new java.awt.Dimension(140, 25));
        jButton1.setPreferredSize(new java.awt.Dimension(140, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tblPasien.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        tblPasien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIK", "Nama", "Tempat Lahir", "Tanggal Lahir", "Alamat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPasienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPasien);
        if (tblPasien.getColumnModel().getColumnCount() > 0) {
            tblPasien.getColumnModel().getColumn(0).setResizable(false);
            tblPasien.getColumnModel().getColumn(1).setResizable(false);
            tblPasien.getColumnModel().getColumn(2).setResizable(false);
            tblPasien.getColumnModel().getColumn(3).setResizable(false);
            tblPasien.getColumnModel().getColumn(4).setResizable(false);
        }

        textSearch.setForeground(new java.awt.Color(153, 153, 153));
        textSearch.setText("Cari berdasarkan NIK atau Nama");
        textSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textSearchFocusLost(evt);
            }
        });
        textSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(840, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(textSearch)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(btnPilih, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(400, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnPilih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textSearchFocusGained
        if (first) {
            textSearch.setForeground(Color.black);
            textSearch.setText("");
            first = false;
        }
    }//GEN-LAST:event_textSearchFocusGained

    private void tblPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPasienMouseClicked
        if (evt.getClickCount() == 2) {
            select();
        }
    }//GEN-LAST:event_tblPasienMouseClicked

    private void textSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textSearchFocusLost
        if (textSearch.getText().isEmpty()) {
            textSearch.setForeground(Color.getHSBColor(153, 153, 153));
            textSearch.setText("Cari berdasarkan NIK atau Nama");
            first = true;
        }
    }//GEN-LAST:event_textSearchFocusLost

    private void textSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textSearchKeyReleased
        pasiens = pasienJpaController.findPasienByNikOrName(textSearch.getText());
        createTableValue();
    }//GEN-LAST:event_textSearchKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        InputPasienDialog dialog = new InputPasienDialog(null, true);
        dialog.show();
        pasiens = pasienJpaController.findPasienEntities();
        createTableValue();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnPilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihActionPerformed
        select();
    }//GEN-LAST:event_btnPilihActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPilih;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPasien;
    private javax.swing.JTextField textSearch;
    // End of variables declaration//GEN-END:variables
}
