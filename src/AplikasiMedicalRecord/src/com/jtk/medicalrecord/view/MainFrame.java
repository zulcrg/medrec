/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jtk.medicalrecord.view;

import com.jtk.medicalrecord.util.CardLayoutEnum;
import com.jtk.medicalrecord.util.ConfigHelper;
import com.jtk.medicalrecord.view.panel.InputMedrec;
import com.jtk.medicalrecord.view.panel.InputMedrecAnamnesa;
import com.jtk.medicalrecord.view.panel.LihatMedrec;
import com.jtk.medicalrecord.view.panel.LoginPanel;
import com.jtk.medicalrecord.view.panel.LoginPanel2;
import com.jtk.medicalrecord.view.panel.MainMenu;
import com.jtk.medicalrecord.view.panel.Pengaturan;
import com.jtk.medicalrecord.view.panel.PengaturanAkun;
import com.jtk.medicalrecord.view.panel.PengaturanBR;
import com.jtk.medicalrecord.view.panel.PengaturanWaktu;
import com.jtk.medicalrecord.view.panel.RegistrasiAkun;
import com.jtk.medicalrecord.view.panel.SynchronizeLoading;
import java.awt.CardLayout;
import javax.swing.UIManager;

/**
 *
 * @author Zulkhair Abdullah D
 */
public class MainFrame extends javax.swing.JFrame {

    public static final CardLayout cardLayout = new CardLayout();
    public static MainFrame instance;
    private final LoginPanel loginPanel = new LoginPanel();
    private final LoginPanel2 loginPanel2 = new LoginPanel2();
    private final RegistrasiAkun registrasiAkun = new RegistrasiAkun();
    private final MainMenu mainMenu = new MainMenu();
    private final InputMedrec inputMedrec = new InputMedrec();
    private final LihatMedrec lihatMedrec = new LihatMedrec();
    private final Pengaturan pengaturan = new Pengaturan();
    private final PengaturanAkun pengaturanAkun = new PengaturanAkun();
    private final PengaturanBR pengaturanBR = new PengaturanBR();
    private final PengaturanWaktu pengaturanWaktu = new PengaturanWaktu();
    private final SynchronizeLoading synchronizeLoading = new SynchronizeLoading();

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        addCardLayout();
        setInstance();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void setInstance() {
        instance = this;
    }

    private void addCardLayout() {
        loginPanel.preparation();
        loginPanel2.preparation();
        registrasiAkun.preparation();
        inputMedrec.preparation();
        lihatMedrec.preparation();
        pengaturan.preparation();
        pengaturanAkun.preparation();
        pengaturanBR.preparation();
        pengaturanWaktu.preparation();
        synchronizeLoading.preparation();

        cardPanel.setLayout(cardLayout);
        cardPanel.add(loginPanel, CardLayoutEnum.LOGIN.toString());
        cardPanel.add(loginPanel2, CardLayoutEnum.LOGIN2.toString());
        cardPanel.add(registrasiAkun, CardLayoutEnum.REGISTRASI_AKUN.toString());
        cardPanel.add(mainMenu, CardLayoutEnum.MAIN_MENU.toString());
        cardPanel.add(inputMedrec, CardLayoutEnum.INPUT_MEDREC.toString());
        cardPanel.add(lihatMedrec, CardLayoutEnum.LIHAT_MEDREC.toString());
        cardPanel.add(pengaturan, CardLayoutEnum.PENGATURAN.toString());
        cardPanel.add(pengaturanAkun, CardLayoutEnum.PENGATURAN_AKUN.toString());
        cardPanel.add(pengaturanBR, CardLayoutEnum.PENGATURAN_BR.toString());
        cardPanel.add(pengaturanWaktu, CardLayoutEnum.PENGATURAN_WAKTU.toString());
        cardPanel.add(synchronizeLoading, CardLayoutEnum.SYNC.toString());

        if (ConfigHelper.readConfig() == null) {
            showLoginPanel();
        } else {
            showLoginPanel2();
        }

    }

    public void showRegistrasiAkun() {
        registrasiAkun.preparation();
        cardLayout.show(cardPanel, CardLayoutEnum.REGISTRASI_AKUN.toString());
    }

    public void showLoginPanel() {
        loginPanel.preparation();
        cardLayout.show(cardPanel, CardLayoutEnum.LOGIN.toString());
    }

    public void showLoginPanel2() {
        loginPanel2.preparation();
        cardLayout.show(cardPanel, CardLayoutEnum.LOGIN2.toString());
    }

    public void showMainMenu() {
        mainMenu.preparation();
        cardLayout.show(cardPanel, CardLayoutEnum.MAIN_MENU.toString());
    }

    public void showInputMedrec() {
        mainMenu.preparation();
        cardLayout.show(cardPanel, CardLayoutEnum.INPUT_MEDREC.toString());
    }

    public void showLihatMedrec() {
        mainMenu.preparation();
        cardLayout.show(cardPanel, CardLayoutEnum.LIHAT_MEDREC.toString());
    }

    public void showPengaturan() {
        mainMenu.preparation();
        cardLayout.show(cardPanel, CardLayoutEnum.PENGATURAN.toString());
    }

    public void showPengaturanAkun() {
        mainMenu.preparation();
        cardLayout.show(cardPanel, CardLayoutEnum.PENGATURAN_AKUN.toString());
    }

    public void showPengaturanBr() {
        mainMenu.preparation();
        cardLayout.show(cardPanel, CardLayoutEnum.PENGATURAN_BR.toString());
    }

    public void showPengaturanWaktu() {
        mainMenu.preparation();
        cardLayout.show(cardPanel, CardLayoutEnum.PENGATURAN_WAKTU.toString());
    }

    public void showSync() {
        mainMenu.preparation();
        cardLayout.show(cardPanel, CardLayoutEnum.SYNC.toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Medical Record");
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setPreferredSize(new java.awt.Dimension(1024, 788));

        cardPanel.setMinimumSize(new java.awt.Dimension(1024, 768));
        cardPanel.setPreferredSize(new java.awt.Dimension(1024, 768));
        cardPanel.setLayout(new java.awt.CardLayout());

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cardPanel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
