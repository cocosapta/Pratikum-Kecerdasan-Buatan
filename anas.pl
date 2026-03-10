% Fakta
lahir(anas, 1952).
lulus(anas, sd).
daftar_pns(anas, 1985).

% Aturan
bisa_jadi_pns(Orang, Tahun) :-
    lahir(Orang, TahunLahir),
    lulus(Orang, sd),
    Umur is Tahun - TahunLahir,
    Umur =< 35.

pensiun(Orang, Tahun) :-
    lahir(Orang, TahunLahir),
    Tahun is TahunLahir + 60.

status(Orang, TahunSekarang, Status) :-
    lahir(Orang, TahunLahir),
    Umur is TahunSekarang - TahunLahir,
    (bisa_jadi_pns(Orang, 1985) ->
        (Umur >= 60 -> Status = pensiun ; Status = aktif)
    ;   Status = bukan_pns).

% Jalankan
cek :-
    status(anas, 2005, S),
    write(S).