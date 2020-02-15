track "drums"
section "intro"

bar "bar1" beats 4 withNotes "RC S" "on beat" "1/4" "on quaver" "1/2" "no semiquaver" and "note" "RC S" "on beat" "1/4" "on quaver" "1/3" "on semiquaver" "1/2"

forTrack "drums" section "introduction" withBars "bar1" ^ 12 and "pattern1" ^ 2

song "Billie Jean" withTracks "drums"

export "BillieJeanScenario"
