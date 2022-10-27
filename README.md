# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Isak Midtvedt, s350289@oslomet.no


# Oppgavebeskrivelse

### Oppgave 1
**Oppgave 1** ble løst ved hjelp av kildekode fra kompendiet, men la til riktig referanse/innlegging av forelder.

### Oppgave 2
I **Oppgave** 2 bygger vi videre på $\color{yellow} inneholder()$. Vi "stjeler" while-loopen der ifra. \
Loopen sjekker hver verdi i treet og sammenligner med gitt/ønsket *verdi*.\
Er verdien negativ (-1) flytter vi oss nedover mot venstre, er verdien positiv (1) flytter vi oss nedover til høyre.
Er forskjellen mellom tallen 0, øker vi antall (forekomsten av gitt *verdi*) og flytter oss nedover mot høyre.\
\
Vi returnerer til slutt antall og vi vet nå hvor mange ganger gitt *verdi* forekommer.

### Oppgave 3
**Oppgave 3** består av to deler og tar for seg traversering ved hjelp av postorden (venstre $\rightarrow$ høyre $\rightarrow$ node)
- Del 1 førstePostOrden:
  - Del 1 tar for seg å finne første node ved *postorden traversering*. Dette gjør vi ved å starte i rot-noden for så å gå videre til venstre subtree før vi går til høyre subtre.
  - Metoden fortsetter helt til p ikke har et venstre barn og hopper så over til høyre subtre hvor den fortsetter helt til p ikke har et høyre barn.
  - Når begge subtre har blitt gått igjennom returnerer vi p
- Del 2 nestePostOrden:
  - nestePostOrden skal finne noden etter/før nåværende node. Da begynner vi først med en sjekk om p har søsken for å bestemme om neste node er høyre, venstre eller foreldre noden.
  - er foreldre noden tom er det ingen neste node
  - er p høyrebarn er det foreldre som er neste ndoe
  - er p venstrebarn er det foreldre som er neste node
  - er p venstrebarn **OG** har høyresøsken så er det den som er neste node da regelen er venstre, høyre, node.

### Oppgave 4
**Oppgave 4** tar også for seg traversering ved hjelp av postorden, men denne gangen skal vi gå igjennom hele treet. Vi begynner med postorden metoden.
Vi lager først en ny node og setter den til å være rot noden. Vi bruker deretter førstePostOrden til å sette p. Vi kan bruke nestePostOrden til å finne p sin neste og gjør dette, så sant det er flere noder igjen, helt til vi når siste node. Vi har nå traversert igjennom hele treet.\
\
postordenRecursive kan kalle på seg selv så alt vi trenger å sjekke her er om p har flere barn. Vi sjekker først venstre subtre, deretter høyre subtre helt til vi ikke har flere noder igjen.

### Oppgave 5