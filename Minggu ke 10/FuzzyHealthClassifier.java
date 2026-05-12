package fuzzyhealthclassifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FuzzyHealthClassifier extends JFrame {
    
    private JTextField txtTinggi, txtBerat;
    private JLabel lblHasilFuzzy, lblHasilCrisp, lblDetail;
    private JButton btnHitung;
    private JPanel panelVisual;
    
    private double[] tinggiMembership;
    private double[] beratMembership;
    private double[] hasilKesehatan;
    
    public FuzzyHealthClassifier() {
        initComponents();
        setTitle("Sistem Logika Fuzzy - Klasifikasi Kesehatan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel Input
        JPanel panelInput = new JPanel(new GridBagLayout());
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Data"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 0; gbc.gridy = 0;
        panelInput.add(new JLabel("Tinggi Badan (cm):"), gbc);
        gbc.gridx = 1;
        txtTinggi = new JTextField("161", 10);
        panelInput.add(txtTinggi, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panelInput.add(new JLabel("Berat Badan (kg):"), gbc);
        gbc.gridx = 1;
        txtBerat = new JTextField("41", 10);
        panelInput.add(txtBerat, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        btnHitung = new JButton("Hitung Kategori Kesehatan");
        gbc.gridwidth = 2;
        panelInput.add(btnHitung, gbc);
        
        // Panel Output
        JPanel panelOutput = new JPanel();
        panelOutput.setLayout(new BoxLayout(panelOutput, BoxLayout.Y_AXIS));
        panelOutput.setBorder(BorderFactory.createTitledBorder("Hasil Diagnosis"));
        
        lblHasilFuzzy = new JLabel("Hasil Fuzzy: ");
        lblHasilCrisp = new JLabel("Hasil Crisp: ");
        lblDetail = new JLabel("Detail: ");
        
        panelOutput.add(lblHasilFuzzy);
        panelOutput.add(Box.createRigidArea(new Dimension(0, 5)));
        panelOutput.add(lblHasilCrisp);
        panelOutput.add(Box.createRigidArea(new Dimension(0, 5)));
        panelOutput.add(lblDetail);
        
        // Panel Visual
        panelVisual = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawMembership(g);
            }
        };
        panelVisual.setPreferredSize(new Dimension(580, 220));
        panelVisual.setBorder(BorderFactory.createTitledBorder("Visualisasi Derajat Keanggotaan"));
        panelVisual.setBackground(Color.WHITE);
        
        add(panelInput, BorderLayout.NORTH);
        add(panelOutput, BorderLayout.CENTER);
        add(panelVisual, BorderLayout.SOUTH);
        
        btnHitung.addActionListener(e -> hitungKesehatan());
        
        pack();
        setLocationRelativeTo(null);
        
        // Jalankan hitungan awal dengan nilai default
        hitungKesehatan();
    }
    
    private double[] fuzzifikasiTinggi(double tinggi) {
        double rendah = 0, sedang = 0, tinggiVal = 0;

        // Himpunan Rendah: 140-160
        if (tinggi <= 160) {
            if (tinggi <= 140) rendah = 1;
            else if (tinggi > 140 && tinggi <= 160) {
                rendah = (160 - tinggi) / 20;
            }
        }

        // Himpunan Sedang: 155-175 (PERBAIKAN)
        if (tinggi >= 155 && tinggi <= 175) {
            if (tinggi <= 165) {
                sedang = (tinggi - 155) / 10;  // naik dari 0 ke 1
            } else {
                sedang = (175 - tinggi) / 10;  // turun dari 1 ke 0
            }
        }
        // TAMBAHAN: Jika tinggi tepat 175, sedang = 1
        if (tinggi == 175) sedang = 1.0;

        // Himpunan Tinggi: 170-190
        if (tinggi >= 170) {
            if (tinggi >= 190) tinggiVal = 1;
            else if (tinggi >= 170 && tinggi < 190) {
                tinggiVal = (tinggi - 170) / 20;
            }
        }

        return new double[]{rendah, sedang, tinggiVal};
    }
    
    private double[] fuzzifikasiBerat(double berat) {
        double kurus = 0, normal = 0, gemuk = 0;
        
        if (berat <= 50) {
            if (berat <= 30) kurus = 1;
            else if (berat > 30 && berat <= 50) {
                kurus = (50 - berat) / 20;
            }
        }
        
        if (berat >= 45 && berat <= 65) {
            if (berat <= 55) normal = (berat - 45) / 10;
            else normal = (65 - berat) / 10;
        }
        
        if (berat >= 60) {
            if (berat >= 80) gemuk = 1;
            else if (berat >= 60 && berat < 80) {
                gemuk = (berat - 60) / 20;
            }
        }
        
        return new double[]{kurus, normal, gemuk};
    }
    
    private double[] evaluasiRules(double[] tinggi, double[] berat) {
        double SS = 0, A = 0, AS = 0, TS = 0;
        
        SS = Math.max(SS, Math.min(tinggi[0], berat[0])); // Rendah & Kurus
        A = Math.max(A, Math.min(tinggi[0], berat[1]));  // Rendah & Normal
        AS = Math.max(AS, Math.min(tinggi[0], berat[2])); // Rendah & Gemuk
        A = Math.max(A, Math.min(tinggi[1], berat[0]));  // Sedang & Kurus
        A = Math.max(A, Math.min(tinggi[1], berat[1]));  // Sedang & Normal
        TS = Math.max(TS, Math.min(tinggi[1], berat[2])); // Sedang & Gemuk
        AS = Math.max(AS, Math.min(tinggi[2], berat[0])); // Tinggi & Kurus
        TS = Math.max(TS, Math.min(tinggi[2], berat[1])); // Tinggi & Normal
        TS = Math.max(TS, Math.min(tinggi[2], berat[2])); // Tinggi & Gemuk
        
        return new double[]{SS, A, AS, TS};
    }
    
    private double defuzzifikasiCentroid(double[] hasil) {
        double[] index = {0.8, 0.6, 0.4, 0.2};
        double pembilang = 0, penyebut = 0;
        
        for (int i = 0; i < hasil.length; i++) {
            pembilang += hasil[i] * index[i];
            penyebut += hasil[i];
        }
        
        if (penyebut == 0) return 0;
        return pembilang / penyebut;
    }
    
    private String metodeMaximum(double[] hasil) {
        String[] kategori = {"Sangat Sehat (SS)", "Sehat (A)", "Agak Sehat (AS)", "Tidak Sehat (TS)"};
        double maxVal = -1;
        int maxIdx = 0;
        
        for (int i = 0; i < hasil.length; i++) {
            if (hasil[i] > maxVal) {
                maxVal = hasil[i];
                maxIdx = i;
            }
        }
        return kategori[maxIdx];
    }
    
    private String crispIndexToKategori(double index) {
        if (index >= 0.7) return "Sangat Sehat (SS)";
        else if (index >= 0.5) return "Sehat (A)";
        else if (index >= 0.3) return "Agak Sehat (AS)";
        else return "Tidak Sehat (TS)";
    }
    
    private void hitungKesehatan() {
        try {
            double tinggi = Double.parseDouble(txtTinggi.getText());
            double berat = Double.parseDouble(txtBerat.getText());
            
            tinggiMembership = fuzzifikasiTinggi(tinggi);
            beratMembership = fuzzifikasiBerat(berat);
            hasilKesehatan = evaluasiRules(tinggiMembership, beratMembership);
            
            double crispIndex = defuzzifikasiCentroid(hasilKesehatan);
            String kategoriMax = metodeMaximum(hasilKesehatan);
            String kategoriCentroid = crispIndexToKategori(crispIndex);
            
            lblHasilFuzzy.setText(String.format(
                "<html>Hasil Fuzzy: SS=%.3f, A=%.3f, AS=%.3f, TS=%.3f</html>",
                hasilKesehatan[0], hasilKesehatan[1], hasilKesehatan[2], hasilKesehatan[3]
            ));
            
            lblHasilCrisp.setText(String.format(
                "<html>Hasil Crisp: Maximum = %s<br>Centroid = %s (Index=%.3f)</html>",
                kategoriMax, kategoriCentroid, crispIndex
            ));
            
            lblDetail.setText(String.format(
                "<html>Tinggi=%.1f cm (R=%.3f, S=%.3f, T=%.3f)<br>Berat=%.1f kg (K=%.3f, N=%.3f, G=%.3f)</html>",
                tinggi, tinggiMembership[0], tinggiMembership[1], tinggiMembership[2],
                berat, beratMembership[0], beratMembership[1], beratMembership[2]
            ));
            
            panelVisual.repaint();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void drawMembership(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);
        
        int width = panelVisual.getWidth() - 40;
        int startX = 20;
        int startY = 30;
        
        g2d.setFont(new Font("Arial", Font.BOLD, 11));
        g2d.drawString("Derajat Keanggotaan Tinggi:", startX, startY);
        
        String[] tinggiLabel = {"Rendah", "Sedang", "Tinggi"};
        for (int i = 0; i < tinggiMembership.length && i < 3; i++) {
            int barWidth = (width - 40) / 3 - 10;
            int x = startX + i * (barWidth + 15);
            int barHeight = (int)(tinggiMembership[i] * 60);
            
            g2d.setColor(new Color(70, 130, 200));
            g2d.fillRect(x, startY + 50 - barHeight, barWidth, barHeight);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, startY + 50 - barHeight, barWidth, barHeight);
            g2d.drawString(String.format("%.2f", tinggiMembership[i]), x + 5, startY + 65);
            g2d.drawString(tinggiLabel[i], x + 5, startY + 80);
        }
        
        g2d.drawString("Derajat Keanggotaan Berat:", startX, startY + 110);
        
        String[] beratLabel = {"Kurus", "Normal", "Gemuk"};
        if (beratMembership != null) {
            for (int i = 0; i < beratMembership.length && i < 3; i++) {
                int barWidth = (width - 40) / 3 - 10;
                int x = startX + i * (barWidth + 15);
                int barHeight = (int)(beratMembership[i] * 60);
                
                g2d.setColor(new Color(100, 180, 100));
                g2d.fillRect(x, startY + 170 - barHeight, barWidth, barHeight);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, startY + 170 - barHeight, barWidth, barHeight);
                g2d.drawString(String.format("%.2f", beratMembership[i]), x + 5, startY + 185);
                g2d.drawString(beratLabel[i], x + 5, startY + 200);
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FuzzyHealthClassifier().setVisible(true);
        });
    }
}