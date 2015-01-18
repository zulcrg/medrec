/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.view.dialog;

import com.jtk.medicalrecord.entity.Obat;
import com.jtk.medicalrecord.jpacontroller.ObatJpaController;
import com.jtk.medicalrecord.util.CommonHelper;
import com.jtk.medicalrecord.util.MessageHelper;
import com.zlib.util.ZClass;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Zulkhair Abdullah D
 */
public class SearchObatDialog extends javax.swing.JDialog {

    private final Obat obat;
    private boolean first = true;
    private List<Obat> obats = new ArrayList<>();
    private final ObatJpaController obatJpaController = new ObatJpaController();

    /**
     * Creates new form SearchObatDialog
     *
     * @param parent
     * @param modal
     * @param obat
     */
    public SearchObatDialog(java.awt.Frame parent, boolean modal, Obat obat) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.obat = obat;
        obats = obatJpaController.findObatEntities();
        createTableValue();
    }

    private void createTableValue() {
        Object[] columnsName = {"Kode Obat", "Nama Obat", "Jenis Obat"};

        DefaultTableModel dtm = new DefaultTableModel(null, columnsName) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Obat p : obats) {
            Object[] o = new Object[3];
            o[0] = p.getObatId();
            o[1] = p.getObatNama();
            o[2] = p.getObatJenis();

            dtm.addRow(o);
        }
        tblObat.setModel(dtm);
        CommonHelper.resizeColumnWidth(tblObat);
    }

    private void select() {
        if (tblObat.isRowSelected(tblObat.getSelectedRow())) {
            Obat o = obats.get(tblObat.getSelectedRow());
            ZClass.copyClass(o, obat);
            this.dispose();
        } else {
            MessageHelper.addWarnMessage("Perhatian", "Harap pilih obat terlebih dahulu");
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
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblObat = new javax.swing.JTable();
        txtPilih = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pencarian Obat");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtSearch.setForeground(new java.awt.Color(153, 153, 153));
        txtSearch.setText("Cari berdasarkan Kode, Nama, atau Jenis Obat");
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        tblObat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Obat", "Nama Obat", "Jenis Obat"
            }
        ));
        tblObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblObatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblObat);

        txtPilih.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jtk/medicalrecord/image/check.png"))); // NOI18N
        txtPilih.setText("Pilih");
        txtPilih.setMaximumSize(new java.awt.Dimension(93, 25));
        txtPilih.setMinimumSize(new java.awt.Dimension(93, 25));
        txtPilih.setPreferredSize(new java.awt.Dimension(93, 25));
        txtPilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPilihActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtPilih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPilih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void tblObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblObatMouseClicked
        if (evt.getClickCount() == 2) {
            select();
        }
    }//GEN-LAST:event_tblObatMouseClicked

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained
        if (first) {
            txtSearch.setForeground(Color.black);
            txtSearch.setText("");
            first = false;
        }
    }//GEN-LAST:event_txtSearchFocusGained

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
        if (txtSearch.getText().isEmpty()) {
            txtSearch.setForeground(Color.getHSBColor(153, 153, 153));
            txtSearch.setText("Cari berdasarkan Kode, Nama, atau Jenis Obat");
            first = true;
        }
    }//GEN-LAST:event_txtSearchFocusLost

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        obats = obatJpaController.findObatEntitiesByIdOrNamaOrJenis(txtSearch.getText());
        createTableValue();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtPilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPilihActionPerformed
        select();
    }//GEN-LAST:event_txtPilihActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblObat;
    private javax.swing.JButton txtPilih;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
