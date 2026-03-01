# Pratikum-Kecerdasan-Buatan
#  Program Prolog Silsilah Keluarga dan Struktur Organisasi

## DAFTAR ISI
1. [Pendahuluan](#pendahuluan)
2. [File 1: silsilah.pl](#file-1-silsilahpl)
3. [File 2: tugas.pl](#file-2-tugaspl)
4. [Cara Menjalankan Program](#cara-menjalankan-program)
5. [Daftar Predikat Lengkap](#daftar-predikat-lengkap)
6. [Contoh Query](#contoh-query)
7. [Troubleshooting](#troubleshooting)

---

## PENDAHULUAN

Repository ini berisi dua program Prolog yang merepresentasikan pengetahuan tentang:
1. **Silsilah Keluarga** - Hubungan kekeluargaan antar individu
2. **Struktur Organisasi Kantor** - Hierarki atasan-bawahan dalam perusahaan

Program ini dibuat untuk pembelajaran representasi pengetahuan menggunakan bahasa pemrograman Prolog.

---

## FILE 1: silsilah.pl

### Deskripsi
Program ini merepresentasikan silsilah keluarga dengan struktur pohon sebagai berikut:

```
        hari
         |
        agus
       /    \
    budi     ani
   /  \      |
  ria  ita   rudi
```

### Fakta Dasar
- **ortu(OrangTua, Anak)** - Relasi orang tua dan anak
- **cowok(Nama)** - Individu berjenis kelamin laki-laki
- **cewek(Nama)** - Individu berjenis kelamin perempuan

### Relasi yang Tersedia

| Relasi | Deskripsi | Contoh |
|--------|-----------|--------|
| `anak(X, Y)` | X adalah anak dari Y | `anak(ria, budi)` |
| `ayah(X, Y)` | X adalah ayah dari Y | `ayah(budi, ria)` |
| `ibu(X, Y)` | X adalah ibu dari Y | `ibu(ani, rudi)` |
| `saudara(X, Y)` | X dan Y bersaudara kandung | `saudara(ria, ita)` |
| `saudara_cowok(X, Y)` | Y adalah saudara laki-laki X | `saudara_cowok(ani, budi)` |
| `saudara_cewek(X, Y)` | Y adalah saudara perempuan X | `saudara_cewek(budi, ani)` |
| `kakek(X, Y)` | X adalah kakek dari Y | `kakek(hari, ria)` |
| `nenek(X, Y)` | X adalah nenek dari Y | `nenek(ani, rudi)` |
| `buyut(X, Y)` | X adalah buyut dari Y | `buyut(hari, ria)` |
| `cucu(X, Y)` | X adalah cucu dari Y | `cucu(budi, hari)` |
| `cicit(X, Y)` | X adalah cicit dari Y | `cicit(rudi, hari)` |
| `keturunan_dari(X, Y)` | X adalah keturunan dari Y | `keturunan_dari(ria, hari)` |
| `leluhur_dari(X, Y)` | X adalah leluhur dari Y | `leluhur_dari(hari, rudi)` |
| `tampilkan_silsilah` | Menampilkan diagram silsilah | `tampilkan_silsilah` |
| `test` | Menjalankan semua test | `test` |

---

## FILE 2: tugas.pl

Program ini terdiri dari dua bagian: **Struktur Organisasi Kantor** dan **Pohon Keluarga Tugas 2**.

### BAGIAN A: STRUKTUR ORGANISASI KANTOR

#### Deskripsi
Hierarki organisasi kantor:

```
            adi
             |
          burhan
         /  |  \
    bahrun bisrin ferdi
     /    \
  fahri   farah
```

#### Fakta Dasar
- **ankbuah(Atasan, Bawahan)** - Relasi atasan-bawahan

#### Relasi yang Tersedia

| Relasi | Deskripsi | Contoh |
|--------|-----------|--------|
| `anakbuah(X, Y)` | X adalah bawahan Y | `anakbuah(burhan, adi)` |
| `kekuasaannya(Bos, Bawahan)` | Semua bawahan (langsung & tidak langsung) | `kekuasaannya(adi, X)` |
| `rantai_komando(Bawahan, Atasan)` | Semua atasan (langsung & tidak langsung) | `rantai_komando(fahri, X)` |
| `bos(X)` | X adalah bos (punya bawahan) | `bos(X)` |
| `ceo(X)` | X adalah CEO (tidak punya atasan) | `ceo(X)` |
| `karyawan_biasa(X)` | X karyawan biasa (tidak punya bawahan) | `karyawan_biasa(X)` |
| `selevel(X, Y)` | X dan Y selevel (satu bos) | `selevel(bahrun, bisrin)` |
| `tampilkan_struktur` | Menampilkan struktur organisasi | `tampilkan_struktur` |

---

### BAGIAN B: POHON KELUARGA TUGAS 2

#### Deskripsi
Struktur keluarga:

```
Anto + Wati (pasangan)
    ├── Ita (perempuan) + Deni (laki)
    │       └── Hadi (laki)
    ├── Budi (laki)
    │       └── Dina (perempuan)
    └── Ida (perempuan) + Rudi (laki)
            ├── Andi (laki)
            └── Rita (perempuan)
```

#### Fakta Dasar
- **anak(Anak, OrangTua)** - Relasi anak-orang tua
- **laki(Nama)** - Individu laki-laki
- **perempuan(Nama)** - Individu perempuan

#### Relasi yang Tersedia

| Relasi | Deskripsi | Contoh |
|--------|-----------|--------|
| `ortu(Anak, OrangTua)` | Orang tua dari anak | `ortu(hadi, ita)` |
| `ayah(Anak, Ayah)` | Ayah dari anak | `ayah(hadi, deni)` |
| `ibu(Anak, Ibu)` | Ibu dari anak | `ibu(hadi, ita)` |
| `saudara_kandung(X, Y)` | X dan Y saudara kandung | `saudara_kandung(ita, budi)` |
| `saudara_laki(X, Y)` | Y saudara laki-laki X | `saudara_laki(ita, budi)` |
| `saudara_perempuan(X, Y)` | Y saudara perempuan X | `saudara_perempuan(budi, ita)` |
| `paman(Paman, Keponakan)` | Paman dari keponakan | `paman(budi, hadi)` |
| `bibi(Bibi, Keponakan)` | Bibi dari keponakan | `bibi(ita, andi)` |
| `kakek(Kakek, Cucu)` | Kakek dari cucu | `kakek(anto, hadi)` |
| `nenek(Nenek, Cucu)` | Nenek dari cucu | `nenek(wati, dina)` |
| `kakek_nenek(X, Y)` | X kakek/nenek dari Y | `kakek_nenek(anto, hadi)` |
| `cucu(Cucu, KakekNenek)` | Cucu dari kakek/nenek | `cucu(hadi, anto)` |

---

## CARA MENJALANKAN PROGRAM

### 1. Memulai Prolog
```bash
$ prolog
# atau
$ gprolog
# atau
$ swipl
```

### 2. Load File
```prolog
% Load file silsilah.pl
?- [silsilah].

% Load file tugas.pl  
?- [tugas].
```

### 3. Menjalankan Query
```prolog
% Contoh query sederhana
?- ortu(budi, ria).
true.

% Query dengan variabel
?- ortu(budi, Anak).
Anak = ria ;
Anak = ita.

% Query dengan multiple solutions (tekan ; untuk lihat solusi lain)
?- saudara(X, Y).
```

### 4. Menampilkan Visualisasi
```prolog
% Tampilkan silsilah keluarga
?- tampilkan_silsilah.

% Tampilkan struktur organisasi
?- tampilkan_struktur.
```

### 5. Menjalankan Test Otomatis
```prolog
% Jalankan semua test untuk silsilah.pl
?- test.
```

---

## DAFTAR PREDIKAT LENGKAP

### silsilah.pl
```
Fakta:
  ortu/2          - orang tua dari anak
  cowok/1         - berjenis kelamin laki-laki
  cewek/1         - berjenis kelamin perempuan

Relasi:
  anak/2          - anak dari orang tua
  ayah/2          - ayah dari anak
  ibu/2           - ibu dari anak
  saudara/2       - saudara kandung
  saudara_cowok/2 - saudara laki-laki
  saudara_cewek/2 - saudara perempuan
  kakek/2         - kakek dari cucu
  nenek/2         - nenek dari cucu
  buyut/2         - buyut dari cicit
  cucu/2          - cucu dari kakek/nenek
  cicit/2         - cicit dari buyut
  keturunan_dari/2 - semua keturunan
  leluhur_dari/2  - semua leluhur
  test/0          - menjalankan semua test
  tampilkan_silsilah/0 - menampilkan diagram
```

### tugas.pl - Struktur Organisasi
```
Fakta:
  ankbuah/2       - atasan dari bawahan

Relasi:
  anakbuah/2      - bawahan dari atasan
  kekuasaannya/2  - semua bawahan (rekursif)
  rantai_komando/2 - semua atasan (rekursif)
  bos/1           - orang yang punya bawahan
  ceo/1           - orang tanpa atasan
  karyawan_biasa/1 - orang tanpa bawahan
  selevel/2       - orang dengan bos sama
  tampilkan_struktur/0 - menampilkan diagram
```

### tugas.pl - Pohon Keluarga
```
Fakta:
  anak/2          - anak dari orang tua
  laki/1          - berjenis kelamin laki-laki
  perempuan/1     - berjenis kelamin perempuan

Relasi:
  ortu/2          - orang tua dari anak
  ayah/2          - ayah dari anak
  ibu/2           - ibu dari anak
  saudara_kandung/2 - saudara kandung
  saudara_laki/2  - saudara laki-laki
  saudara_perempuan/2 - saudara perempuan
  paman/2         - paman dari keponakan
  bibi/2          - bibi dari keponakan
  kakek/2         - kakek dari cucu
  nenek/2         - nenek dari cucu
  kakek_nenek/2   - kakek atau nenek
  cucu/2          - cucu dari kakek/nenek
```

---

## CONTOH QUERY

### Untuk silsilah.pl
```prolog
% Siapa orang tua dari ria?
?- ortu(OrangTua, ria).

% Siapa anak dari agus?
?- ortu(agus, Anak).

% Apakah ria dan ita saudara?
?- saudara(ria, ita).

% Siapa saja saudara dari ani?
?- saudara(ani, Saudara).

% Siapa kakek dari ria?
?- kakek(Kakek, ria).

% Siapa semua keturunan hari?
?- keturunan_dari(Keturunan, hari).

% Siapa semua leluhur rudi?
?- leluhur_dari(Leluhur, rudi).
```

### Untuk tugas.pl - Struktur Organisasi
```prolog
% Siapa bawahan dari burhan?
?- ankbuah(burhan, Bawahan).

% Siapa atasan dari fahri?
?- anakbuah(fahri, Atasan).

% Semua bawahan adi (langsung & tidak langsung)
?- kekuasaannya(adi, Bawahan).

% Semua atasan fahri (langsung & tidak langsung)
?- rantai_komando(fahri, Atasan).

% Siapa CEO?
?- ceo(X).

% Apakah bahrun dan bisrin selevel?
?- selevel(bahrun, bisrin).
```

### Untuk tugas.pl - Pohon Keluarga
```prolog
% Siapa orang tua hadi?
?- ortu(hadi, OrangTua).

% Siapa ayah hadi?
?- ayah(hadi, Ayah).

% Siapa ibu hadi?
?- ibu(hadi, Ibu).

% Siapa paman hadi?
?- paman(Paman, hadi).

% Siapa bibi hadi?
?- bibi(Bibi, hadi).

% Siapa kakek hadi?
?- kakek(Kakek, hadi).

% Siapa cucu anto?
?- cucu(Cucu, anto).
```

---

## TROUBLESHOOTING

### 1. Query menghasilkan "false" padahal seharusnya "true"
```prolog
% Cek apakah file sudah di-load dengan benar
?- listing(ortu).

% Coba query manual tanpa predikat
?- ortu(budi, ria).  % Cek fakta dasar
```

### 2. Perbedaan operator `=` dan `==`
- `X = Y` : X dan Y dapat diunifikasi (disamakan)
- `X == Y` : X dan Y identik
- `X \= Y` : X dan Y tidak dapat diunifikasi
- `X \== Y` : X dan Y tidak identik

### 3. Mengatasi duplikasi solusi
```prolog
% Gunakan setof/3 atau findall/3 untuk mengumpulkan solusi
?- setof(X, keturunan_dari(X, hari), Daftar).
```

### 4. Reload file setelah perubahan
```prolog
?- make.  % Reload semua file yang dimodifikasi
?- [silsilah].  % Load ulang file tertentu
```

---

## KONTAK & KONTRIBUSI

Program ini dibuat untuk tujuan pembelajaran representasi pengetahuan menggunakan Prolog.

**Catatan**: Semua nama dalam program ini hanyalah contoh dan tidak merujuk pada individu atau organisasi nyata.

---

© 2024 - Program Prolog Silsilah Keluarga dan Struktur Organisasi
