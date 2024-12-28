package kelompok6.tabadmin;

import java.util.List;
import kelompok6.repo.PaketRepo;
import kelompok6.model.PaketModel;

/**
 * HapusPaket class
 */
public class HapusPaket extends javax.swing.JFrame {

    /**
     * Creates new form HapusPaket
     */
    public HapusPaket() {
        initComponents();
        loadData();
    }

    public void loadData() {
        PaketRepo paketRepo = new PaketRepo();
        List<PaketModel> pakets = paketRepo.readAll();
        String[] columnNames = { "ID", "Username", "Paket" };
        Object[][] data = new Object[pakets.size()][3];

        for (int i = 0; i < pakets.size(); i++) {
            PaketModel paket = pakets.get(i);
            data[i][0] = paket.getId();
            data[i][1] = paket.getUsername();
            data[i][2] = paket.getPaket();
        }

        tabelPaket.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPaket = new javax.swing.JTable();
        Hapus = new javax.swing.JToggleButton();
        Field = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabelPaket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Username", "Paket"
            }
        ));
        jScrollPane1.setViewportView(tabelPaket);

        Hapus.setText("Hapus");
        Hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HapusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Field, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Hapus)
                .addContainerGap(107, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Hapus)
                    .addComponent(Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }

    private void HapusMouseClicked(java.awt.event.MouseEvent evt) {
        String deleteByUserName = Field.getText();
        if (deleteByUserName.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Kolom Username tidak boleh kosong.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        int response = javax.swing.JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus paket dengan Username: " + deleteByUserName + "?", "Konfirmasi", javax.swing.JOptionPane.YES_NO_OPTION, javax.swing.JOptionPane.QUESTION_MESSAGE);
        if (response == javax.swing.JOptionPane.YES_OPTION) {
            PaketRepo paketRepo = new PaketRepo();
            List<PaketModel> pakets = paketRepo.findByUsername(deleteByUserName);
            if (!pakets.isEmpty()) {
                for (PaketModel paket : pakets) {
                    paketRepo.delete(paket.getId());
                }
                javax.swing.JOptionPane.showMessageDialog(this, "Paket berhasil dihapus.", "Sukses", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                loadData();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Username tidak ditemukan. Harap masukkan Username yang valid untuk menghapus.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HapusPaket().setVisible(true);
            }
        });
    }

    private javax.swing.JTextField Field;
    private javax.swing.JToggleButton Hapus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelPaket;
}
