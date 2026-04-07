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


% -----------------------------------------------------------------
% CONTOH QUERY - JAWABAN TUGAS
% -----------------------------------------------------------------
% 
% 1. Siapa anak dari Anto dan Wati?
%    ?- anak(Anak, anto).
%    Anak = ita ; Anak = budi ; Anak = ida.
%
%    ?- anak(Anak, wati).
%    Anak = ita ; Anak = budi ; Anak = ida.
%
% 2. Siapa orang tua Hadi?
%    ?- ortu(hadi, X).
%    X = ita ; X = deni.
%
% 3. Siapa saudara kandung Ita?      salah
%    ?- saudara_kandung(ita, X).
%    X = budi ; X = ida.
%
% 4. Siapa paman Hadi? (Budi adalah paman Hadi) salah
%    ?- paman(X, hadi).
%    X = budi.
%
% 5. Siapa bibi Hadi? (Ida adalah bibi Hadi) salah
%    ?- bibi(X, hadi).
%    X = ida.
%
% 6. Siapa bibi Dina? (Ita dan Ida adalah bibi Dina) salah
%    ?- bibi(X, dina).
%    X = ita ; X = ida.
%
% 7. Siapa kakek Andi? (Anto adalah kakek Andi) perbaikan
%    ?- kakek(X, andi).
%    X = anto.
%
% 8. Siapa nenek Rita? (Wati adalah nenek Rita)
%    ?- nenek(X, rita).
%    X = wati.
%
% 9. Siapa saja cucu Anto dan Wati? (Hadi, Dina, Andi, Rita)
%    ?- cucu(X, anto).
%    X = hadi ; X = dina ; X = andi ; X = rita.
%
%    ?- cucu(X, wati).
%    X = hadi ; X = dina ; X = andi ; X = rita.
%
% 10. Apakah Budi paman dari Hadi? (Ya)
%     ?- paman(budi, hadi).
%     true.
%
% 11. Apakah Ida bibi dari Hadi? (Ya)
%     ?- bibi(ida, hadi).
%     true.
%
% 12. Apakah Ita bibi dari Dina? (Ya)
%     ?- bibi(ita, dina).
%     true.
% -----------------------------------------------------------------