% ============================================
% REPRESENTASI PENGETAHUAN - SILSILAH KELUARGA
% ============================================
%        hari
%         |
%        agus
%       /    \
%    budi     ani
%   /  \      |
%  ria  ita   rudi
% ============================================

% ----- DATA DASAR (FAKTA) -----

% Format: ortu(OrangTua, Anak)
ortu(hari, agus).      % hari orang tua dari agus
ortu(agus, budi).      % agus orang tua dari budi
ortu(agus, ani).       % agus orang tua dari ani
ortu(budi, ria).       % budi orang tua dari ria
ortu(budi, ita).       % budi orang tua dari ita
ortu(ani, rudi).       % ani orang tua dari rudi
% Jenis kelamin - cowok (laki-laki)
cowok(hari).
cowok(agus).
cowok(budi).
cowok(rudi).
% Jenis kelamin - cewek (perempuan)
cewek(ani).
cewek(ria).
cewek(ita).
% ----- RELASI ORANG TUA (kebalikan) -----
% anak(Anak, OrangTua)
anak(X, Y) :- ortu(Y, X).
% ----- RELASI AYAH dan IBU (berdasarkan jenis kelamin) -----
% ayah(Ayah, Anak)
ayah(X, Y) :- ortu(X, Y), cowok(X).
% ibu(Ibu, Anak)
ibu(X, Y) :- ortu(X, Y), cewek(X).
% ----- RELASI SAUDARA (DIPERBAIKI) -----
% saudara(X, Y) - X dan Y bersaudara kandung
saudara(X, Y) :-
    ortu(Z, X),
    ortu(Z, Y),
    X \== Y.
% saudara_cowok(X, Y) - Y saudara cowok dari X
saudara_cowok(X, Y) :-
    cowok(Y),
    ortu(Z, X),
    ortu(Z, Y),
    X \== Y.
% saudara_cewek(X, Y) - Y saudara cewek dari X
saudara_cewek(X, Y) :-
    cewek(Y),
    ortu(Z, X),
    ortu(Z, Y),
    X \== Y.
% ----- RELASI KAKEK/NENEK (2 generasi ke atas) -----
% kakek(Kakek, Cucu)
kakek(X, Y) :-
    cowok(X),
    ortu(X, Z),
    ortu(Z, Y).
% nenek(Nenek, Cucu)
nenek(X, Y) :-
    cewek(X),
    ortu(X, Z),
    ortu(Z, Y).
% ----- RELASI BUYUT (3 generasi ke atas) -----
% buyut(Buyut, Cicit)
buyut(X, Y) :-
    ortu(X, A),
    ortu(A, B),
    ortu(B, Y).
% ----- RELASI CUCU (2 generasi ke bawah) -----
% cucu(Cucu, KakekNenek)
cucu(X, Y) :-
    ortu(Y, Z),
    ortu(Z, X).
% ----- RELASI CICIT (3 generasi ke bawah) -----
% cicit(Cicit, Buyut)
cicit(X, Y) :-
    ortu(Y, A),
    ortu(A, B),
    ortu(B, X).
% ----- RELASI REKURSIF (KETURUNAN) -----
% keturunan_dari(X, Y) - X adalah keturunan dari Y
keturunan_dari(X, Y) :- ortu(Y, X).                    % langsung (anak)
keturunan_dari(X, Y) :- ortu(Z, X), keturunan_dari(Z, Y). % tidak langsung
% leluhur_dari(X, Y) - X adalah leluhur dari Y
leluhur_dari(X, Y) :- ortu(X, Y).                      % langsung (orang tua)
leluhur_dari(X, Y) :- ortu(X, Z), leluhur_dari(Z, Y).    % tidak langsung
% tampilkan_silsilah - Menampilkan seluruh silsilah keluarga
tampilkan_silsilah :-
    write('========================================='), nl,
    write('           SILSILAH KELUARGA            '), nl,
    write('========================================='), nl, nl,
    write('        hari'), nl,
    write('         |'), nl,
    write('        agus'), nl,
    write('       /    \\'), nl,
    write('    budi     ani'), nl,
    write('   /  \\      |'), nl,
    write('  ria  ita   rudi'), nl, nl,
    write('========================================='), nl,
    write('Data Keluarga:'), nl,
    forall(ortu(X, Y), (format('~w adalah orang tua dari ~w~n', [X, Y]))), nl,
    write('Jenis Kelamin:'), nl,
    forall(cowok(X), (format('~w adalah cowok (laki-laki)~n', [X]))),
    forall(cewek(X), (format('~w adalah cewek (perempuan)~n', [X]))), nl,
    write('========================================='), nl.

% ----- FUNGSI TEST UNTUK VERIFIKASI -----

test :-
    write('========================================='), nl,
    write('        TEST RELASI KELUARGA            '), nl,
    write('========================================='), nl, nl,
    
    write('--- RELASI ORANG TUA ---'), nl,
    forall(ortu(X, Y), (format('~w adalah orang tua dari ~w~n', [X, Y]))), nl,
    
    write('--- RELASI ANAK ---'), nl,
    forall(anak(X, Y), (format('~w adalah anak dari ~w~n', [X, Y]))), nl,
    
    write('--- RELASI AYAH ---'), nl,
    forall(ayah(X, Y), (format('~w adalah ayah dari ~w~n', [X, Y]))), nl,
    
    write('--- RELASI IBU ---'), nl,
    forall(ibu(X, Y), (format('~w adalah ibu dari ~w~n', [X, Y]))), nl,
    
    write('--- RELASI SAUDARA ---'), nl,
    forall(saudara(X, Y), (format('~w dan ~w adalah saudara~n', [X, Y]))), nl,
    
    write('--- RELASI KAKEK ---'), nl,
    forall(kakek(X, Y), (format('~w adalah kakek dari ~w~n', [X, Y]))), nl,
    
    write('--- RELASI NENEK ---'), nl,
    forall(nenek(X, Y), (format('~w adalah nenek dari ~w~n', [X, Y]))), nl,
    
    write('--- RELASI CUCU ---'), nl,
    forall(cucu(X, Y), (format('~w adalah cucu dari ~w~n', [X, Y]))), nl,
    
    write('--- RELASI KETURUNAN (REKURSIF) ---'), nl,
    forall(keturunan_dari(X, hari), (format('~w adalah keturunan dari hari~n', [X]))), nl,
    
    write('========================================='), nl.