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
public class BobotDiagnosa {
   private List<Penyakit> daftarPenyakit;

    public BobotDiagnosa() {
        daftarPenyakit = new ArrayList<>();
        initDataBobot();
    }

    private void initDataBobot() {
        Penyakit flu = new Penyakit("Influenza");
        flu.tambahGejalaBobot("Demam 37.5-39°C", 30);
        flu.tambahGejalaBobot("Batuk kering >3x/jam", 30);
        flu.tambahGejalaBobot("Pilek encer", 20);
        flu.tambahGejalaBobot("Sakit tenggorokan", 20);
        daftarPenyakit.add(flu);

        Penyakit dbd = new Penyakit("Demam Berdarah");
        dbd.tambahGejalaBobot("Demam >39°C mendadak", 35);
        dbd.tambahGejalaBobot("Nyeri sendi", 20);
        dbd.tambahGejalaBobot("Ruam kulit (torniket +)", 35);
        dbd.tambahGejalaBobot("Mual muntah >2x/hari", 10);
        daftarPenyakit.add(dbd);

        Penyakit diabetes = new Penyakit("Diabetes");
        diabetes.tambahGejalaBobot("Sering haus >3L/hari", 25);
        diabetes.tambahGejalaBobot("Sering BAK >8x/hari", 25);
        diabetes.tambahGejalaBobot("Mudah lelah", 25);
        diabetes.tambahGejalaBobot("Luka sulit sembuh >2 minggu", 25);
        daftarPenyakit.add(diabetes);

        Penyakit hipertensi = new Penyakit("Hipertensi");
        hipertensi.tambahGejalaBobot("Sakit kepala pagi berdenyut", 25);
        hipertensi.tambahGejalaBobot("Pusing", 20);
        hipertensi.tambahGejalaBobot("Penglihatan kabur", 20);
        hipertensi.tambahGejalaBobot("TD ≥140/90 mmHg", 25);
        hipertensi.tambahGejalaBobot("Mimisan", 10);
        daftarPenyakit.add(hipertensi);
    }

    public String diagnosa(List<String> gejalaUser) {
        String hasil = "Tidak terdeteksi";
        int maxSkor = 0;
        List<String> penyakitDenganSkorTinggi = new ArrayList<>();

        for (Penyakit p : daftarPenyakit) {
            int skor = 0;
            Map<String, Integer> bobotMap = p.getGejalaBobot();
            for (String gejala : gejalaUser) {
                if (bobotMap.containsKey(gejala)) {
                    skor += bobotMap.get(gejala);
                }
            }
            
            // Jika skor lebih tinggi dari maxSkor
            if (skor > maxSkor) {
                maxSkor = skor;
                penyakitDenganSkorTinggi.clear();
                penyakitDenganSkorTinggi.add(p.getNama());
            } 
            // Jika skor sama dengan maxSkor
            else if (skor == maxSkor && skor > 0) {
                penyakitDenganSkorTinggi.add(p.getNama());
            }
        }
        
        // Jika ada skor
        if (maxSkor > 0) {
            // Prioritas jika skor sama: DBD > Diabetes > Hipertensi > Influenza
            if (penyakitDenganSkorTinggi.size() > 1) {
                if (penyakitDenganSkorTinggi.contains("Demam Berdarah")) return "Demam Berdarah";
                if (penyakitDenganSkorTinggi.contains("Diabetes")) return "Diabetes";
                if (penyakitDenganSkorTinggi.contains("Hipertensi")) return "Hipertensi";
                if (penyakitDenganSkorTinggi.contains("Influenza")) return "Influenza";
            }
            hasil = penyakitDenganSkorTinggi.get(0);
        }
        
        return hasil;
    }
}
