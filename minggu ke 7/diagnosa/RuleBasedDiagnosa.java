/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diagnosa;
import java.util.*;
/**
 *
 * @author FX506
 */
public class RuleBasedDiagnosa {
   private List<Penyakit> daftarPenyakit;
    
    // Prioritas penyakit (semakin kecil angkanya, semakin prioritas)
    private Map<String, Integer> prioritas;

    public RuleBasedDiagnosa() {
        daftarPenyakit = new ArrayList<>();
        prioritas = new HashMap<>();
        initData();
        initPrioritas();
    }

    private void initData() {
        Penyakit flu = new Penyakit("Influenza");
        flu.tambahGejalaRule("Demam 37.5-39°C");
        flu.tambahGejalaRule("Batuk kering >3x/jam");
        flu.tambahGejalaRule("Pilek encer");
        flu.tambahGejalaRule("Sakit tenggorokan");
        daftarPenyakit.add(flu);

        Penyakit dbd = new Penyakit("Demam Berdarah");
        dbd.tambahGejalaRule("Demam >39°C mendadak");
        dbd.tambahGejalaRule("Nyeri sendi");
        dbd.tambahGejalaRule("Ruam kulit (torniket +)");
        dbd.tambahGejalaRule("Mual muntah >2x/hari");
        daftarPenyakit.add(dbd);

        Penyakit diabetes = new Penyakit("Diabetes");
        diabetes.tambahGejalaRule("Sering haus >3L/hari");
        diabetes.tambahGejalaRule("Sering BAK >8x/hari");
        diabetes.tambahGejalaRule("Mudah lelah");
        diabetes.tambahGejalaRule("Luka sulit sembuh >2 minggu");
        daftarPenyakit.add(diabetes);

        Penyakit hipertensi = new Penyakit("Hipertensi");
        hipertensi.tambahGejalaRule("Sakit kepala pagi berdenyut");
        hipertensi.tambahGejalaRule("Pusing");
        hipertensi.tambahGejalaRule("Penglihatan kabur");
        hipertensi.tambahGejalaRule("TD ≥140/90 mmHg");
        hipertensi.tambahGejalaRule("Mimisan");
        daftarPenyakit.add(hipertensi);
    }
    
    private void initPrioritas() {
        // Prioritas: DBD paling tinggi (karena berbahaya), lalu Diabetes, Hipertensi, Influenza
        prioritas.put("Demam Berdarah", 1);
        prioritas.put("Diabetes", 2);
        prioritas.put("Hipertensi", 3);
        prioritas.put("Influenza", 4);
    }

    public String diagnosa(List<String> gejalaUser) {
        List<String> penyakitTerpenuhi = new ArrayList<>();
        
        // Cari semua penyakit yang memenuhi syarat
        for (Penyakit p : daftarPenyakit) {
            List<String> gejalaPenyakit = p.getGejalaRule();
            int cocok = 0;
            for (String g : gejalaPenyakit) {
                if (gejalaUser.contains(g)) cocok++;
            }
            // Minimal 70% gejala cocok
            if ((double) cocok / gejalaPenyakit.size() >= 0.7) {
                penyakitTerpenuhi.add(p.getNama());
            }
        }
        
        // Jika tidak ada penyakit yang terpenuhi
        if (penyakitTerpenuhi.isEmpty()) {
            return "Tidak terdeteksi";
        }
        
        // Jika hanya 1 penyakit
        if (penyakitTerpenuhi.size() == 1) {
            return penyakitTerpenuhi.get(0);
        }
        
        // Jika lebih dari 1, pilih berdasarkan prioritas (nilai paling kecil = prioritas tertinggi)
        penyakitTerpenuhi.sort((p1, p2) -> {
            int prioritas1 = prioritas.getOrDefault(p1, 99);
            int prioritas2 = prioritas.getOrDefault(p2, 99);
            return Integer.compare(prioritas1, prioritas2);
        });
        
        return penyakitTerpenuhi.get(0);
    }
}
