/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kelompok6.tabadmin;

import java.util.List;
import java.util.Map;
import javax.swing.text.AbstractDocument.Content;
import kelompok6.form.Menu;
import kelompok6.model.AdminModel;
import kelompok6.tabadmin.CetakUser;
import kelompok6.tabadmin.EditUser;

/**
 *
 * @author bayui
 */
public class MenuAdmin extends javax.swing.JFrame {
    private static AdminModel session;
    // private javax.swing.JPanel Content; // Removed duplicate declaration
        /**
         * Creates new form menu_admin
         */
        public MenuAdmin(AdminModel admin) {
            initComponents();
            this.session = admin;
            Home();
    }

    public void Home() {
        Home home = new Home();
        Content.removeAll();
        Content.add(home.getContentPane(), java.awt.BorderLayout.CENTER);
        Content.revalidate();
        Content.repaint();
    }

    // ...existing code...
    public void search() {

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

        jTextField1 = new javax.swing.JTextField();
        Content = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        home = new javax.swing.JMenu();
        administrasi = new javax.swing.JMenu();
        listUser = new javax.swing.JMenuItem();
        laporanUser = new javax.swing.JMenuItem();
        editUser = new javax.swing.JMenuItem();
        hapusUser = new javax.swing.JMenuItem();
        Logout = new javax.swing.JMenu();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout ContentLayout = new javax.swing.GroupLayout(Content);
        Content.setLayout(ContentLayout);
        ContentLayout.setHorizontalGroup(
            ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        ContentLayout.setVerticalGroup(
            ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 634, Short.MAX_VALUE)
        );

        home.setText("Home");
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        jMenuBar1.add(home);

        administrasi.setText("Administrasi");

        listUser.setText("List User");
        listUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listUserActionPerformed(evt);
            }
        });
        administrasi.add(listUser);

        laporanUser.setText("Laporan User");
        laporanUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporanUserActionPerformed(evt);
            }
        });
        administrasi.add(laporanUser);

        editUser.setText("Edit User");
        editUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editUserMouseClicked(evt);
            }
        });
        editUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUserActionPerformed(evt);
            }
        });
        administrasi.add(editUser);

        hapusUser.setText("Hapus User");
        hapusUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusUserActionPerformed(evt);
            }
        });
        administrasi.add(hapusUser);

        jMenuBar1.add(administrasi);

        Logout.setText("Log out");
        Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutMouseClicked(evt);
            }
        });
        jMenuBar1.add(Logout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listUserActionPerformed
        // TODO add your handling code here:
        ListUser listuser = new ListUser();
        Content.removeAll();
        Content.add(listuser.getContentPane(), java.awt.BorderLayout.CENTER);
        Content.revalidate();
        Content.repaint();
    }//GEN-LAST:event_listUserActionPerformed

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        // TODO add your handling code here:
        Home();
    }//GEN-LAST:event_homeMouseClicked

    private void laporanUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporanUserActionPerformed
        // TODO add your handling code here:
        CetakUser reportGenerator = new CetakUser();
        List<Map<String, ?>> data = reportGenerator.fetchData();
        String outputFilePath = "CetakLaporanUser.pdf";
        reportGenerator.generateReport(data, outputFilePath);
    }//GEN-LAST:event_laporanUserActionPerformed

    private void editUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUserActionPerformed
        // TODO add your handling code here:
        EditUser edituUser = new EditUser();
        Content.removeAll();
        Content.add(edituUser.getContentPane(), java.awt.BorderLayout.CENTER);
        Content.revalidate();
        Content.repaint();
    }//GEN-LAST:event_editUserActionPerformed

    private void hapusUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusUserActionPerformed
        // TODO add your handling code here:
        HapusUser hapusUser = new HapusUser();
        Content.removeAll();
        Content.add(hapusUser.getContentPane(), java.awt.BorderLayout.CENTER);
        Content.revalidate();
        Content.repaint();
    }//GEN-LAST:event_hapusUserActionPerformed

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        // TODO add your handling code here:
        AdminModel session = null;
        // Navigate to the Utama screen
        setVisible(false);
        Menu utama = new Menu();
        utama.setVisible(true);
    }//GEN-LAST:event_LogoutMouseClicked

    private void editUserMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_editUserMouseClicked
        // TODO add your handling code here:

    }// GEN-LAST:event_editUserMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuAdmin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuAdmin(session).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Content;
    private javax.swing.JMenu Logout;
    private javax.swing.JMenu administrasi;
    private javax.swing.JMenuItem editUser;
    private javax.swing.JMenuItem hapusUser;
    private javax.swing.JMenu home;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenuItem laporanUser;
    private javax.swing.JMenuItem listUser;
    // End of variables declaration//GEN-END:variables
}
