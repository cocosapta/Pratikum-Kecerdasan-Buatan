/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package diagnosa;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
/**
 *
 * @author FX506
 */
public class MainGUI extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainGUI.class.getName());
    private RuleBasedDiagnosa ruleBased;
    private BobotDiagnosa bobot;
    
    // Map untuk menghubungkan checkbox dengan nama gejala standar
    private Map<JCheckBox, String> gejalaMap;
    private boolean isDiagnosaMode = true;
    /**
     * Creates new form MainGUI
     */
    public MainGUI() {
        initComponents();
        initData();
        setupGejalaMap();
        setupUI();
    }
    private void initData() {
        ruleBased = new RuleBasedDiagnosa();
        bobot = new BobotDiagnosa();
    }
    
    private void setupGejalaMap() {
        gejalaMap = new HashMap<>(); 
        // Mapping checkbox ke nama gejala standar (untuk pencocokan dengan rule & bobot)
        gejalaMap.put(jCheckBox1, "Demam 37.5-39°C");
        gejalaMap.put(jCheckBox2, "Batuk kering >3x/jam");
        gejalaMap.put(jCheckBox3, "Pilek encer");
        gejalaMap.put(jCheckBox4, "Sakit tenggorokan");
        gejalaMap.put(jCheckBox5, "Demam >39°C mendadak");
        gejalaMap.put(jCheckBox6, "Nyeri sendi");
        gejalaMap.put(jCheckBox7, "Ruam kulit (torniket +)");
        gejalaMap.put(jCheckBox8, "Mual muntah >2x/hari");
        gejalaMap.put(jCheckBox9, "Sering haus >3L/hari");
        gejalaMap.put(jCheckBox10, "Sering BAK >8x/hari");
        gejalaMap.put(jCheckBox11, "Mudah lelah");
        gejalaMap.put(jCheckBox12, "Luka sulit sembuh >2 minggu");
        gejalaMap.put(jCheckBox13, "Sakit kepala pagi berdenyut");
        gejalaMap.put(jCheckBox14, "Pusing");
        gejalaMap.put(jCheckBox15, "Penglihatan kabur");
        gejalaMap.put(jCheckBox16, "TD ≥140/90 mmHg");
        gejalaMap.put(jCheckBox17, "Mimisan");
    }
   private void setupUI() {
        // Set title
        setTitle("Sistem Pakar Diagnosa Penyakit");
        
        // Setup panel hasil dengan border
        jPanel4.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(243, 156, 18), 2),
            "📊 HASIL DIAGNOSA (2 Metode)",
            TitledBorder.LEFT, TitledBorder.TOP
        ));
        
        // Setup panel riwayat
        jPanel5.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(52, 73, 94), 2),
            "📝 Riwayat Diagnosa",
            TitledBorder.LEFT, TitledBorder.TOP
        ));
        
        // Setup panel gejala
        jPanel2.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
            "☑ Pilih gejala yang Anda alami (centang jika Ya)",
            TitledBorder.LEFT, TitledBorder.TOP
        ));
        
        // Setup header
        jPanel1.setBackground(new Color(44, 62, 80));
        jLabel1.setForeground(Color.WHITE);
        
        // Setup tombol awal (mode DIAGNOSA)
        setDiagnosaMode();
        
        // Setup label hasil
        jLabel5.setText("🔍 Rule-Based : ");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 13));
        jLabel10.setText("Belum diagnosa");
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel10.setForeground(new Color(230, 126, 34));
        
        jLabel8.setText("⚖️ Metode Bobot : ");
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 13));
        jLabel9.setText("Belum diagnosa");
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel9.setForeground(new Color(52, 152, 219));
        
        // Setup text area riwayat
        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new Color(52, 73, 94));
        jTextArea1.setForeground(Color.WHITE);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 11));
        
        // Sembunyikan panel yang tidak diperlukan
        jPanel6.setVisible(false);
        jPanel7.setVisible(false);
        
        // Atur ulang layout jPanel4
        jPanel4.removeAll();
        jPanel4.setLayout(new java.awt.BorderLayout(10, 10));
        
        // Panel untuk hasil rule-based
        javax.swing.JPanel rulePanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        rulePanel.setBackground(new Color(255, 248, 225));
        rulePanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        rulePanel.add(jLabel5);
        rulePanel.add(jLabel10);
        
        // Panel untuk hasil bobot
        javax.swing.JPanel bobotPanel = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        bobotPanel.setBackground(new Color(225, 245, 254));
        bobotPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        bobotPanel.add(jLabel8);
        bobotPanel.add(jLabel9);
        
        // Panel untuk kedua hasil
        javax.swing.JPanel hasilContainer = new javax.swing.JPanel(new java.awt.GridLayout(2, 1, 5, 5));
        hasilContainer.add(rulePanel);
        hasilContainer.add(bobotPanel);
        
        jPanel4.add(hasilContainer, java.awt.BorderLayout.CENTER);
        jPanel4.add(jPanel5, java.awt.BorderLayout.SOUTH);
    }
    private void setDiagnosaMode() {
        isDiagnosaMode = true;
        jButton1.setText("🔍 DIAGNOSA SEKARANG");
        jButton1.setBackground(new Color(39, 174, 96));  // Hijau
        jButton1.setForeground(Color.WHITE);
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14));
    }
    
    /**
     * Set tombol ke mode RESET ALL
     */
    private void setResetMode() {
        isDiagnosaMode = false;
        jButton1.setText("🗑️ RESET ALL");
        jButton1.setBackground(new Color(231, 76, 60));  // Merah
        jButton1.setForeground(Color.WHITE);
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14));
    }
    
    /**
     * Method untuk melakukan diagnosa berdasarkan checkbox yang dipilih
     */
    private void diagnosa() {
        // Kumpulkan gejala yang dipilih (checkbox dicentang)
         List<String> gejalaUser = new ArrayList<>();
        int totalCheckbox = gejalaMap.size();
        int checkedCount = 0;

        for (Map.Entry<JCheckBox, String> entry : gejalaMap.entrySet()) {
            if (entry.getKey().isSelected()) {
                gejalaUser.add(entry.getValue());
                checkedCount++;
            }
        }
        if (checkedCount == totalCheckbox) {
            int confirm = JOptionPane.showConfirmDialog(this,
                "MAAF APAKAH ANDA MEMPUNYAI KOMPLIKASI PENYAKIT.\n" +
                "Anda memilih SEMUA gejala. Ini mungkin terjadi secara medis.\n" +
                "Apakah Anda yakin ingin melanjutkan?",
                "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
        }
        // Validasi: minimal pilih 1 gejala
        if (gejalaUser.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Silakan pilih minimal 1 gejala yang Anda alami!",
                "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Diagnosa dengan 2 metode
        String hasilRule = ruleBased.diagnosa(gejalaUser);
        String hasilBobot = bobot.diagnosa(gejalaUser);
        
        // Tampilkan hasil
        jLabel10.setText(hasilRule);
        jLabel9.setText(hasilBobot);
        
        // Simpan ke riwayat
        String waktu = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
        String gejalaText = gejalaUser.toString();
        if (gejalaText.length() > 80) {
            gejalaText = gejalaText.substring(0, 80) + "...";
        }
        
        String riwayat = String.format("[%s] Gejala: %s\n   → Rule-Based: %s | Bobot: %s\n",
            waktu, gejalaText, hasilRule, hasilBobot);
        jTextArea1.append(riwayat);
        
        // Auto-scroll ke bawah
        jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
        
        // Ubah tombol menjadi mode RESET ALL setelah diagnosa berhasil
        setResetMode();
    }
    
    /**
     * Method untuk reset semua checkbox dan hasil
     */
    private void resetAll() {
        // Reset semua checkbox
        for (JCheckBox chk : gejalaMap.keySet()) {
            chk.setSelected(false);
        }
        
        // Reset hasil diagnosa
        jLabel10.setText("Belum diagnosa");
        jLabel9.setText("Belum diagnosa");
        
        // Ubah tombol kembali ke mode DIAGNOSA
        setDiagnosaMode();
        
        // Optional: tampilkan pesan
        JOptionPane.showMessageDialog(this, 
            "Semua gejala dan hasil diagnosa telah direset!",
            "Reset Berhasil", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Method untuk uji coba 10 percobaan otomatis
     */
    private void ujiCobaOtomatis() {
        jTextArea1.append("\n========== UJI COBA 10 PERCOBAAN ==========\n\n");
        
        List<List<String>> dataUji = new ArrayList<>();
        
        // Uji 1: Influenza
        List<String> uji1 = new ArrayList<>();
        uji1.add("Demam 37.5-39°C");
        uji1.add("Batuk kering >3x/jam");
        uji1.add("Pilek encer");
        dataUji.add(uji1);
        
        // Uji 2: Demam Berdarah
        List<String> uji2 = new ArrayList<>();
        uji2.add("Demam >39°C mendadak");
        uji2.add("Nyeri sendi");
        uji2.add("Ruam kulit (torniket +)");
        uji2.add("Mual muntah >2x/hari");
        dataUji.add(uji2);
        
        // Uji 3: Diabetes
        List<String> uji3 = new ArrayList<>();
        uji3.add("Sering haus >3L/hari");
        uji3.add("Sering BAK >8x/hari");
        uji3.add("Mudah lelah");
        dataUji.add(uji3);
        
        // Uji 4: Hipertensi
        List<String> uji4 = new ArrayList<>();
        uji4.add("Sakit kepala pagi berdenyut");
        uji4.add("Pusing");
        uji4.add("TD ≥140/90 mmHg");
        dataUji.add(uji4);
        
        // Uji 5: Influenza lengkap
        List<String> uji5 = new ArrayList<>();
        uji5.add("Batuk kering >3x/jam");
        uji5.add("Pilek encer");
        uji5.add("Sakit tenggorokan");
        uji5.add("Demam 37.5-39°C");
        dataUji.add(uji5);
        
        // Uji 6: Demam Berdarah (kurang mual)
        List<String> uji6 = new ArrayList<>();
        uji6.add("Nyeri sendi");
        uji6.add("Ruam kulit (torniket +)");
        uji6.add("Mual muntah >2x/hari");
        dataUji.add(uji6);
        
        // Uji 7: Diabetes lengkap
        List<String> uji7 = new ArrayList<>();
        uji7.add("Mudah lelah");
        uji7.add("Luka sulit sembuh >2 minggu");
        uji7.add("Sering haus >3L/hari");
        dataUji.add(uji7);
        
        // Uji 8: Hipertensi lengkap
        List<String> uji8 = new ArrayList<>();
        uji8.add("Pusing");
        uji8.add("Penglihatan kabur");
        uji8.add("Mimisan");
        uji8.add("TD ≥140/90 mmHg");
        dataUji.add(uji8);
        
        // Uji 9: Gejala campuran (Influenza + Sakit kepala)
        List<String> uji9 = new ArrayList<>();
        uji9.add("Demam 37.5-39°C");
        uji9.add("Pilek encer");
        uji9.add("Sakit kepala pagi berdenyut");
        dataUji.add(uji9);
        
        // Uji 10: Demam Berdarah lengkap
        List<String> uji10 = new ArrayList<>();
        uji10.add("Demam >39°C mendadak");
        uji10.add("Mual muntah >2x/hari");
        uji10.add("Ruam kulit (torniket +)");
        uji10.add("Nyeri sendi");
        dataUji.add(uji10);
        
        for (int i = 0; i < dataUji.size(); i++) {
            String hasilRule = ruleBased.diagnosa(dataUji.get(i));
            String hasilBobot = bobot.diagnosa(dataUji.get(i));
            jTextArea1.append(String.format("Uji %2d | Rule-Based: %-18s | Bobot: %-18s | Gejala: %s\n",
                (i+1), hasilRule, hasilBobot, dataUji.get(i).toString()));
        }
        
        jTextArea1.append("\n========== AKHIR UJI COBA ==========\n\n");
        jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
        
        JOptionPane.showMessageDialog(this, 
            "Uji coba 10 percobaan selesai!\nLihat hasilnya di riwayat diagnosa.",
            "Uji Coba Selesai", JOptionPane.INFORMATION_MESSAGE);
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText(" SISTEM PAKAR DIAGNOSA PENYAKIT ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jCheckBox1.setText("Suhu 37.5–39°C, suhu meningkat  bertahap, menggigil ringan");
        jCheckBox1.addActionListener(this::chkDemam1);

        jCheckBox2.setText("Batuk kering, berdahak ringan,  frekuensi >3x/jam");
        jCheckBox2.addActionListener(this::chkBatuk);

        jCheckBox3.setText("Hidung tersumbat, hidung berair,  cairan bening encer, bersin berulang");
        jCheckBox3.addActionListener(this::chkPilek);

        jCheckBox4.setText("Nyeri saat menelan, kemerahan  ringan pada faring");
        jCheckBox4.addActionListener(this::chkTenggorokan);

        jCheckBox5.setText("Suhu > 39°C mendadak, pola  2–7 hari suhu turun naik");
        jCheckBox5.addActionListener(this::chkDemam2);

        jCheckBox6.setText("Nyeri pada otot, nyeri  pada tulang");
        jCheckBox6.addActionListener(this::chkNyeriSendi);

        jCheckBox7.setText("Bintik merah, bintik tidak hilang  saat ditekan, uji torniket positif");
        jCheckBox7.addActionListener(this::chkRuam);

        jCheckBox8.setText("Muntah >2x/hari, nafsu  makan turun");
        jCheckBox8.addActionListener(this::chkMual);

        jCheckBox9.setText("Minum >3 liter/hari");
        jCheckBox9.addActionListener(this::chkHaus);

        jCheckBox10.setText("Buang air kecil 8x/hari,  sering buang air kecil  pada  malam hari");
        jCheckBox10.addActionListener(this::chkBAK);

        jCheckBox11.setText("Energi cepat habis, kadar gula  darah >200 mg/dL");
        jCheckBox11.addActionListener(this::chkLelah);

        jCheckBox12.setText("Infeksi berulang,  penyembuhan >2 minggu");
        jCheckBox12.addActionListener(this::chkLuka);

        jCheckBox13.setText("Sakit kepala terjadi pagi hari, berdenyut, terutama di bagian belakang kepala");
        jCheckBox13.addActionListener(this::chkSakitKepala);

        jCheckBox14.setText("Terasa berputar dan  ringan (terkait perubahan  posisi)");
        jCheckBox14.addActionListener(this::chkPusing);

        jCheckBox15.setText("Penglihatan kabur");
        jCheckBox15.addActionListener(this::chkPenglihatan);

        jCheckBox16.setText("Konsisten tinggi ≥ 140/90  mmHg, Detak jantung  meningkat");
        jCheckBox16.addActionListener(this::chkTD);

        jCheckBox17.setText("Perdarahan dari hidung");
        jCheckBox17.addActionListener(this::chkMimisan);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox2)
                            .addComponent(jCheckBox1)
                            .addComponent(jCheckBox3)
                            .addComponent(jCheckBox4))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox11)
                            .addComponent(jCheckBox12)
                            .addComponent(jCheckBox10)
                            .addComponent(jCheckBox9)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox6)
                            .addComponent(jCheckBox7)
                            .addComponent(jCheckBox8)
                            .addComponent(jCheckBox5))
                        .addGap(85, 85, 85)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox13)
                            .addComponent(jCheckBox15)
                            .addComponent(jCheckBox14)
                            .addComponent(jCheckBox16))))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jCheckBox17)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCheckBox4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox14)
                    .addComponent(jCheckBox6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox8)
                    .addComponent(jCheckBox16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox17)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jLabel4.setText("HASIL DIAGNOSA (2 Metode):");

        jPanel6.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                bobotPanel(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel5.setText("Rule-Based :");
        jLabel5.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                bobotText(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel10.setText("Rule-Based ");
        jLabel10.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                hasilBobotLabel(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(0, 261, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10))
                .addGap(14, 14, 14))
        );

        jPanel7.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel7bobotPanel(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel8.setText("Rule-Based :");
        jLabel8.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                ruleText(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel9.setText("Belum diagnosa");
        jLabel9.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                hasilRuleLabel(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(0, 237, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(14, 14, 14))
        );

        jPanel5.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                riwayatPanel(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jScrollPane1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                riwayatArea(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel3.setText("Riwayat Diagnosa:  ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jButton1.setText("DIAGNOSA SEKARANG");
        jButton1.addActionListener(this::btnDiagnosa);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(402, 402, 402)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkDemam1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDemam1
        // TODO add your handling code here:
    }//GEN-LAST:event_chkDemam1

    private void chkBatuk(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBatuk
        // TODO add your handling code here:
    }//GEN-LAST:event_chkBatuk

    private void chkPilek(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPilek
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPilek

    private void chkTenggorokan(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTenggorokan
        // TODO add your handling code here:
    }//GEN-LAST:event_chkTenggorokan

    private void chkDemam2(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDemam2
        // TODO add your handling code here:
    }//GEN-LAST:event_chkDemam2

    private void chkNyeriSendi(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNyeriSendi
        // TODO add your handling code here:
    }//GEN-LAST:event_chkNyeriSendi

    private void chkRuam(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRuam
        // TODO add your handling code here:
    }//GEN-LAST:event_chkRuam

    private void chkMual(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMual
        // TODO add your handling code here:
    }//GEN-LAST:event_chkMual

    private void chkHaus(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkHaus
        // TODO add your handling code here:
    }//GEN-LAST:event_chkHaus

    private void chkBAK(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBAK
        // TODO add your handling code here:
    }//GEN-LAST:event_chkBAK

    private void chkLelah(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLelah
        // TODO add your handling code here:
    }//GEN-LAST:event_chkLelah

    private void chkLuka(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLuka
        // TODO add your handling code here:
    }//GEN-LAST:event_chkLuka

    private void chkSakitKepala(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSakitKepala
        // TODO add your handling code here:
    }//GEN-LAST:event_chkSakitKepala

    private void chkPusing(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPusing
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPusing

    private void chkPenglihatan(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPenglihatan
        // TODO add your handling code here:
    }//GEN-LAST:event_chkPenglihatan

    private void chkTD(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTD
        // TODO add your handling code here:
    }//GEN-LAST:event_chkTD

    private void chkMimisan(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMimisan
        // TODO add your handling code here:
    }//GEN-LAST:event_chkMimisan

    private void btnDiagnosa(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosa
       if (isDiagnosaMode) {
            // Mode DIAGNOSA
            diagnosa();
        } else {
            // Mode RESET ALL
            resetAll();
        }
    }//GEN-LAST:event_btnDiagnosa

    private void bobotPanel(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_bobotPanel
        // TODO add your handling code here:
    }//GEN-LAST:event_bobotPanel

    private void bobotText(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_bobotText
        // TODO add your handling code here:
    }//GEN-LAST:event_bobotText

    private void ruleText(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_ruleText
        // TODO add your handling code here:
    }//GEN-LAST:event_ruleText

    private void jPanel7bobotPanel(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel7bobotPanel
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel7bobotPanel

    private void hasilRuleLabel(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_hasilRuleLabel
        // TODO add your handling code here:
    }//GEN-LAST:event_hasilRuleLabel

    private void hasilBobotLabel(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_hasilBobotLabel
        // TODO add your handling code here:
    }//GEN-LAST:event_hasilBobotLabel

    private void riwayatPanel(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_riwayatPanel
        // TODO add your handling code here:
    }//GEN-LAST:event_riwayatPanel

    private void riwayatArea(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_riwayatArea
        // TODO add your handling code here:
    }//GEN-LAST:event_riwayatArea

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MainGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
