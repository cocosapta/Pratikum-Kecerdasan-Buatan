% =====================================================
% REPRESENTASI PENGETAHUAN - TUGAS MINGGU 3
% =====================================================

% =====================================================
% SOAL NOMOR 1: ANDI DAN KALKULUS
% =====================================================

:- discontiguous benci_matakuliah/2.
:- discontiguous suka_matakuliah/2.

% Fakta-fakta dasar
mahasiswa(andi).
jurusan(andi, elektro).

% Relasi mahasiswa teknik
mahasiswa_teknik(X) :- jurusan(X, elektro).

% Fakta matakuliah sulit
matakuliah_sulit(kalkulus).

% Fakta Andi tidak pernah hadir kuliah matakuliah kalkulus
tidak_pernah_hadir(andi, kalkulus).

% Aturan 5: Setiap mahasiswa teknik pasti akan suka kalkulus atau akan membencinya
suka_matakuliah(X, kalkulus) :- 
    mahasiswa_teknik(X), 
    \+ benci_matakuliah(X, kalkulus).

% Aturan 7: Mahasiswa yang tidak pernah hadir pada kuliah matakuliah sulit, 
% maka mereka pasti tidak suka terhadap matakuliah tersebut.
benci_matakuliah(X, M) :-
    mahasiswa(X),
    matakuliah_sulit(M),
    tidak_pernah_hadir(X, M).

% Aturan default: jika tidak suka dan tidak ada aturan yang menyatakan benci, 
% maka dianggap benci (untuk memenuhi aturan 5)
benci_matakuliah(X, kalkulus) :- 
    mahasiswa_teknik(X), 
    \+ suka_matakuliah(X, kalkulus).

% Aturan 6: Setiap mahasiswa pasti akan suka terhadap suatu matakuliah
suka_suatu_matakuliah(X) :- 
    mahasiswa(X), 
    suka_matakuliah(X, _).

% Query untuk soal nomor 1
% ?- suka_matakuliah(andi, kalkulus).
% Hasil: false (Andi tidak suka kalkulus karena tidak pernah hadir)

