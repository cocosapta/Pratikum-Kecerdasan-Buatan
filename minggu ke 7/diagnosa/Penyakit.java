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
public class Penyakit {
    private String nama;
    private Map<String, Integer> gejalaBobot;
    private List<String> gejalaRule;

    public Penyakit(String nama) {
        this.nama = nama;
        this.gejalaBobot = new HashMap<>();
        this.gejalaRule = new ArrayList<>();
    }

    public void tambahGejalaBobot(String gejala, int bobot) {
        gejalaBobot.put(gejala, bobot);
    }

    public void tambahGejalaRule(String gejala) {
        gejalaRule.add(gejala);
    }

    public String getNama() { return nama; }
    public Map<String, Integer> getGejalaBobot() { return gejalaBobot; }
    public List<String> getGejalaRule() { return gejalaRule; }
}
