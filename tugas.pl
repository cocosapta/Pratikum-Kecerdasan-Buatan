
% =================================================================
% PROLOG VERSION CHILL - STRUKTUR ORG KANTOR
% =================================================================

% -----------------------------------------------------------------
% 1. SIAPA ankbuah SIAPA (Relasi atasan-bawahan)
% -----------------------------------------------------------------
ankbuah(adi, burhan).      % Adi ankbuah Burhan
ankbuah(burhan, bahrun).   % Burhan ankbuah Bahrun
ankbuah(burhan, bisrin).   % Burhan ankbuah Bisrin
ankbuah(bisrin, ferdi).    % Bisrin ankbuah Ferdi
ankbuah(bahrun, fahri).    % Bahrun ankbuah Fahri
ankbuah(bahrun, farah).    % Bahrun ankbuah Farah

% -----------------------------------------------------------------
% 2. KEBALIKAN: SIAPA BOSNYA
% -----------------------------------------------------------------
anakbuah(X, Y) :- ankbuah(Y, X).

% -----------------------------------------------------------------
% 3. SEMUA YANG ADA DI BAWAH KENDALI (REKURSIF)
% -----------------------------------------------------------------
kekuasaannya(Bos, Anak) :- ankbuah(Bos, Anak).
kekuasaannya(Bos, Anak) :- ankbuah(Bos, X), kekuasaannya(X, Anak).

% -----------------------------------------------------------------
% 4. SEMUA BOS DARI ATAS (REKURSIF)
% -----------------------------------------------------------------
rantai_komando(Anak, Bos) :- anakbuah(Anak, Bos).
rantai_komando(Anak, Bos) :- anakbuah(Anak, X), rantai_komando(X, Bos).


% -----------------------------------------------------------------
% 5. Siapa aja yang jadi bos (punya anak buah)
% -----------------------------------------------------------------
bos(X) :- 
    setof(X, Y^ankbuah(X, Y), Daftar),
    member(X, Daftar).

% -----------------------------------------------------------------
% Siapa yang paling gede (ga punya bos)
% -----------------------------------------------------------------
ceo(X) :-
    ankbuah(X, _),
    \+ anakbuah(X, _).

% -----------------------------------------------------------------
% Karyawan biasa (ga punya anak buah)
% -----------------------------------------------------------------
karyawan_biasa(X) :-
    anakbuah(X, _),
    \+ ankbuah(X, _).

% -----------------------------------------------------------------
% Siapa aja yang selevel (satu bos)
% -----------------------------------------------------------------
selevel(X, Y) :-
    anakbuah(X, Bos),
    anakbuah(Y, Bos),
    X \= Y.


% -----------------------------------------------------------------
% tampilkan_struktur - Menampilkan struktur organisasi
% -----------------------------------------------------------------
tampilkan_struktur :-
    write('========================================='), nl,
    write('        STRUKTUR ORGANISASI             '), nl,
    write('========================================='), nl, nl,
    write('            adi'), nl,
    write('             |'), nl,
    write('          burhan'), nl,
    write('         /  |  \\'), nl,
    write('    bahrun bisrin ferdi'), nl,
    write('     /    \\'), nl,
    write('  fahri   farah'), nl, nl,
    write('========================================='), nl,
    write('========================================='), nl.

% -----------------------------------------------------------------
% -----------------------------------------------------------------
% =================================================================
% POHON KELUARGA - TUGAS 2
% =================================================================
% 
% Anto + Wati (pasangan)
%    ├── Ita (perempuan) + Deni (laki)
%    │       └── Hadi (laki)
%    ├── Budi (laki)
%    │       └── Dina (perempuan)
%    └── Ida (perempuan) + Rudi (laki)
%            ├── Andi (laki)
%            └── Rita (perempuan)
% =================================================================

% -----------------------------------------------------------------
% 1. PREDIKAT DASAR: anak, laki, perempuan
% -----------------------------------------------------------------

% Fakta anak(Anak, OrangTua)
anak(ita, anto).
anak(ita, wati).
anak(budi, anto).
anak(budi, wati).
anak(ida, anto).
anak(ida, wati).

anak(hadi, ita).
anak(hadi, deni).

anak(dina, budi).

anak(andi, ida).
anak(andi, rudi).
anak(rita, ida).
anak(rita, rudi).

% Jenis kelamin laki-laki
laki(anto).
laki(deni).
laki(budi).
laki(rudi).
laki(hadi).
laki(andi).

% Jenis kelamin perempuan
perempuan(wati).
perempuan(ita).
perempuan(ida).
perempuan(dina).
perempuan(rita).

% -----------------------------------------------------------------
% 2. RELASI ORANG TUA
% -----------------------------------------------------------------

% ortu(Anak, OrangTua)
ortu(Anak, OrangTua) :- anak(Anak, OrangTua).

% ayah(Anak, Ayah)
ayah(Anak, Ayah) :-
    anak(Anak, Ayah),
    laki(Ayah).

% ibu(Anak, Ibu)
ibu(Anak, Ibu) :-
    anak(Anak, Ibu),
    perempuan(Ibu).

% -----------------------------------------------------------------
% 3. RELASI SAUDARA KANDUNG
% -----------------------------------------------------------------

% saudara_kandung(X, Y) - X dan Y punya orang tua yang sama
saudara_kandung(X, Y) :-
    ortu(X, Z),
    ortu(Y, Z),
    X \= Y.

% saudara_laki(X, Y) - Y adalah saudara laki-laki dari X
saudara_laki(X, Y) :-
    laki(Y),
    ortu(X, Z),
    ortu(Y, Z),
    X \= Y.

% saudara_perempuan(X, Y) - Y adalah saudara perempuan dari X
saudara_perempuan(X, Y) :-
    ortu(X, Z),
    ortu(Y, Z),
    perempuan(Y),
    X \= Y.

% -----------------------------------------------------------------
% 4. RELASI PAMAN (saudara laki dari orang tua)
% -----------------------------------------------------------------

% paman(Paman, Keponakan)
paman(Paman, Keponakan) :-
     setof(P,
        Ortu^(
            ortu(Keponakan, Ortu),
            laki(P),
            saudara_laki(Ortu, P)
        ),
        List),
    member(Paman, List).
% -----------------------------------------------------------------
% 5. RELASI BIBI (saudara perempuan dari orang tua)
% -----------------------------------------------------------------

% bibi(Bibi, Keponakan)
bibi(Bibi, Keponakan) :-
    setof(B,
        Ortu^(
            ortu(Keponakan, Ortu),
            saudara_perempuan(Ortu, B)
        ),
        List),
    member(Bibi, List).

% -----------------------------------------------------------------
% 6. RELASI KAKEK (orang tua laki dari orang tua)
% -----------------------------------------------------------------

% kakek(Kakek, Cucu)
kakek(Kakek, Cucu) :-
    laki(Kakek),
    ortu(Cucu, OrtuCucu),
    ortu(OrtuCucu, Kakek).

% -----------------------------------------------------------------
% 7. RELASI NENEK (orang tua perempuan dari orang tua)
% -----------------------------------------------------------------

% nenek(Nenek, Cucu)
nenek(Nenek, Cucu) :-
    perempuan(Nenek),
    ortu(Cucu, OrtuCucu),
    ortu(OrtuCucu, Nenek).

% -----------------------------------------------------------------
% 8. RELASI TAMBAHAN (kakek-nenek langsung)
% -----------------------------------------------------------------

% kakek_nenek(KakekNenek, Cucu) - kakek atau nenek
kakek_nenek(KakekNenek, Cucu) :-
    ortu(Cucu, Ortu),
    ortu(Ortu, KakekNenek).

% cucu(Cucu, KakekNenek) - kebalikan dari kakek_nenek
cucu(Cucu, KakekNenek) :- kakek_nenek(KakekNenek, Cucu).

