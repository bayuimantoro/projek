package kelompok6.tabuser;

import kelompok6.model.PaketModel;
import kelompok6.repo.PaketRepo;
import kelompok6.model.UserModel;

/**
 *
 * @author bayui
 */
public class PaketDataKorup extends javax.swing.JFrame {

    /**
     * Creates new form paket_data
     */
    private static UserModel pesan;
        private PaketRepo paketDataRepo;
            
        public PaketDataKorup(UserModel session) {
            getComponents();
            pesan = session;
            this.paketDataRepo = new PaketRepo();
        }
    
        private void beliMouseClicked(java.awt.event.MouseEvent evt) {                                  
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
        }                                 
    
        private String generateUniqueId() {
            // Implement your unique ID generation logic here
            return java.util.UUID.randomUUID().toString();
        }
    
        public static void main(String args[]) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new PaketDataKorup(pesan).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JComboBox<String> level;
    // End of variables declaration                   
}