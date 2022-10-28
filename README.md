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
  - er p venstrebarn **OG** har høyresøsken så er det den som er neste node.

### Oppgave 4
**Oppgave 4** tar også for seg traversering ved hjelp av postorden, men denne gangen skal vi gå igjennom hele treet. Vi begynner med postorden metoden.
Vi lager først en ny node og setter den til å være rot noden. Vi bruker deretter førstePostOrden til å sette p. Vi kan bruke nestePostOrden til å finne p sin neste og gjør dette, så sant det er flere noder igjen, helt til vi når siste node. Vi har nå traversert igjennom hele treet.\
\
postordenRecursive kan kalle på seg selv så alt vi trenger å sjekke her er om p har flere barn. Vi sjekker først venstre subtre, deretter høyre subtre helt til vi ikke har flere noder igjen.

### Oppgave 5
**Oppgave 5** handler også om traversering, men denne gangen er det nivåorden istedenfor. Da bruker vi en kø eller "qeue" og løser problemet med Arraylist.
Dette kopierte jeg primært fra kompendiet med noen endringer for denne oppgaven spesiikt.

### Oppgave 6
**Oppgave 6** tar for seg metoden fjern, fjernalle og nullstill. Alle disse metodene er inspirert fra kompendiet.
Vi lager først fjern ved å instansere en ny Node p, så lenge noden fortsatt er i treet skal vi fortsette. Vi sjekker verdien til p, hvor p ligger (venstre eller høyre subtre) og fortsetter helt til p er null. Vi har 3 ulike tilfeller i metoden fjern.\
- Tilfelle 1: p kun har et venstrebarn.
- Tilfelle 2: p kun har et høyrebarn.
- Tilfelle 3: p har både venstre og høyrebarn.\

Ved tilfelle 1 og 2 lager vi en ny Node b, som er barnenoden til p. Avhengig av om p er venstre eller høyre oppdater vi b til riktig side. Vi oppdaterer også b sin forelder til å være q.\
Ved tilfelle 3 har vi to barn. Vi kan ikke fjerne p da dette ville ødelagt treet. Vi setter heller p til en ny verdi (hengitt med r) også fjerner vi heller r. Vi setter først s er lik p, også r = p.høyre (ergo høyre subtre). r har ingen venstre barn og kan nå fjernes.\
Hvis s er forskjellig fra p fjerner vi r ved at s.venstre settes lik r.høyre.\
Hvis s **ER** lik p fjernes r ved at s.høyre settes lik r.høyre.\
\
Dette er da avhengig av hvor dypt i treet s befinner seg.\
\
Når fjern er kodet er det lett å gjøre fjernalle. Det gjør vi bare ved å kjøre gjentatte kall på fjern metoden og fortsetter helt til verdien ikke finnes i treet lenger.
Vi holder følge med hvor mange ganger vi fjernet en verdi og oppdaterer fjernet for hver gang loopen kjøres.\
\
Nullstill koder vi ved å først sjekke om treet er tomt. Er treet tom trenger vi ikke nullstille det da det allerede ikke har noen verdier. Vi nullstiller med å først lage en Node p og setter den lik rot. Vi setter så p til å være lik første node i postorden.\
Vi lager så en løkke som kjører så lenge antall ikke er null (så lenge treet har verdier). Vi sjekker så om p ikke er lik "null" ergo at treet fortsatt har en node p etter postorden kallet. Så sant p fortsatt finnes fjerner vi p sin verdi fra treet. Vi setter også verdien til p lik null og "sletter" noden fra treet. Vi setter så p lik neste node i postorden og dette blir på en måte "telleren" hvor i løkken. Så lenge neste node finnes fortsetter vi. Vi setter til slutt rot lik "null" for å slette rot noden også. Treet er nå tomt. Vi må huske å trekke fra antall hver gang slik at vi bryter ut av løkken.