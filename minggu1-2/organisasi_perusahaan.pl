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
kekuasaannya(Bos, Karyawan) :- ankbuah(Bos, Karyawan).
kekuasaannya(Bos, Karyawan) :- ankbuah(Bos, X), kekuasaannya(X, Karyawan).
% -----------------------------------------------------------------
% 4. SEMUA BOS DARI ATAS (REKURSIF)
% -----------------------------------------------------------------
rantai_komando(Karyawan, Bos) :- anakbuah(Karyawan, Bos).
rantai_komando(Karyawan, Bos) :- anakbuah(Karyawan, X), rantai_komando(X, Bos).
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
% -----------------------------------------------------------------
% tampilkan_struktur - Menampilkan struktur organisasi
tampilkan_struktur :-
    write('========================================='), nl,
    write('        STRUKTUR ORGANISASI             '), nl,
    write('========================================='), nl, nl,
    write('            adi'), nl,
    write('             |'), nl,
    write('          burhan'), nl,
    write('         /    \\'), nl,
    write('    bahrun bisrin '), nl,
    write('     /   \    \\'), nl,
    write('     /   \    ferdi'), nl,
    write('  fahri   farah'), nl, nl,
    write('========================================='), nl,
    write('========================================='), nl.

% -----------------------------------------------------------------
% TESTING QUERY - COPAS AJA
% -----------------------------------------------------------------
% 
% 1. Anak buah langsung Burhan?
%    ?- ankbuah(burhan, X).
%    X = bahrun ; X = bisrin.
%
% 2. Bosnya Ferdi?
%    ?- anakbuah(ferdi, X).
%    X = bisrin.
%
% 3. Rantai komando Ferdi dari atas?
%    ?- rantai_komando(ferdi, X).
%    X = bisrin ; X = burhan ; X = adi.
%
% 4. Siapa yang paling tinggi?
%    ?- ceo(X).
%    X = adi.
%
% 5. Siapa aja yang jadi bos?
%    ?- bos(X).
%    X = adi ; X = bahrun ; X = bisrin ; X = burhan.
%
% 6. Karyawan biasa (ga punya anak buah)?
%    ?- karyawan_biasa(X).
%    X = ferdi ; X = fahri ; X = farah.
% 9. Siapa selevel sama Fahri?
%    ?- selevel(fahri, X).
%    X = farah.
% -----------------------------------------------------------------