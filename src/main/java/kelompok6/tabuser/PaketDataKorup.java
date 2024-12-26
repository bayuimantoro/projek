package kelompok6.tabuser;

import kelompok6.model.PaketModel;
import kelompok6.repo.PaketDataRepo;
import kelompok6.model.UserModel;

/**
 *
 * @author bayui
 */
public class PaketData extends javax.swing.JFrame {

    /**
     * Creates new form paket_data
     */
    private static UserModel pesan;
        private PaketDataRepo paketDataRepo;
            
        public PaketData(UserModel session) {
            getComponents();
            pesan = session;
            this.paketDataRepo = new PaketDataRepo();
        }
    
        private void beliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beliMouseClicked
            String selectedPaket = (String) level.getSelectedItem();
            if (selectedPaket != null && !selectedPaket.equals("Pilih :")) {
                PaketModel paket = new PaketModel(selectedPaket, selectedPaket, selectedPaket);
                paket.setId(generateUniqueId()); // Implement this method to generate a unique ID
                paket.setPaket(selectedPaket);
                paket.setUsernameUtama(pesan.getUsername());
                boolean success = paketDataRepo.create(paket);
                if (success) {
                    // Show success message
                    javax.swing.JOptionPane.showMessageDialog(this, "Paket berhasil dibeli!");
                } else {
                    // Show error message
                    javax.swing.JOptionPane.showMessageDialog(this, "Gagal membeli paket.");
                }
            } else {
                // Show warning message
                javax.swing.JOptionPane.showMessageDialog(this, "Silakan pilih paket terlebih dahulu.");
            }
        }//GEN-LAST:event_beliMouseClicked
    
        private String generateUniqueId() {
            // Implement your unique ID generation logic here
            return java.util.UUID.randomUUID().toString();
        }
    
        public static void main(String args[]) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new PaketData(pesan).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> level;
    // End of variables declaration//GEN-END:variables
}
