package no.oslomet.cs.algdat.Oblig3;


import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class SBinTre<T> {

    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node


    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    //////// Oppgave 1 ////////
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Null verdier ikke tillat");

        Node<T> p = rot, q = null;
        int cmp = 0;

        while (p != null) {
            q = p;
            cmp = comp.compare(verdi, p.verdi);
            p = cmp < 0 ? p.venstre : p.høyre;
        }

        p = new Node<>(verdi, null);


        if (q == null) rot = p;
        else if (cmp < 0) q.venstre = p;
        else q.høyre = p;

        if (!(q == null)) p.forelder = q;
        else p.forelder = null;

        antall++;
        endringer++;

        return true;
    }

    /////// Oppgave 2 ///////
    public int antall(T verdi) {

        if (verdi == null) return 0;

        int antall = 0;
        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) {
                p = p.venstre;
            } else if (cmp > 0) {
                p = p.høyre;
            } else {
                antall++;
                p = p.høyre;
            }
        }
        return antall;
    }

    /////// Oppgave 3 ////////
    private static <T> Node<T> førstePostorden(Node<T> p) {

        while (true) {                                              //Så lenge p har barn skal metoden fortsette
            if (p.venstre != null) p = p.venstre;                   //Sjekker at p har flere venstre barn
            else if (p.høyre != null) p = p.høyre;                  //Sjekker at p har flere høyre barn
            else return p;                                          //p har ingen flere barn igjen og vi har nådd første node.
        }
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {

        if (p.forelder == null) return null;                        //hvis forelder noden er tom er det ingen neste node.

        if (p.forelder.høyre == p) return p.forelder;               //sjekker om p er høyrebarn, nestepostorden er da forelder node til p
        else if (p.forelder.høyre == null) return p.forelder;       //p er venstrebarn, sjekker om p har høyresøsken, nestepostorden er igjen foreldre node til p.
        else return førstePostorden(p.forelder.høyre);              //her er p venstrebarn OG har høyresøsken, da kan vi bruke førstepostorden til å finne neste node.
    }

    //////// Oppgave 4 ///////
    public void postorden(Oppgave<? super T> oppgave) {

        Node<T> p = rot;                                            // setter p lik rot node

        p = førstePostorden(p);                                     //finner første node i postorden

        while (p != null) {                                         //så lenge det er flere noder skal metoden fortsette
            oppgave.utførOppgave(p.verdi);                          //skriver ut verdien til noden
            p = nestePostorden(p);                                  //siden vi er inne i en løkke kan vi kalle nestePostOrden og få neste node helt til det ikke er fler.
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {

        if (p.venstre != null) postordenRecursive(p.venstre, oppgave);              //så lenge p har venstre barn skal vi fortsette i venstre subtre

        if (p.høyre != null) postordenRecursive(p.høyre, oppgave);                  //så lenge p har høyre barn skal vi fortsette i høyre subtre

        oppgave.utførOppgave(p.verdi);                                              //skriver ut verdi til node.
    }

    ////// Oppgave 5 ///////
    public ArrayList<T> serialize() {
        ArrayList<Node<T>> qeue = new ArrayList<>();
        ArrayList<T> printedList = new ArrayList<>();

        qeue.add(rot);

        while (!qeue.isEmpty()) {
            Node<T> p = qeue.remove(0);
            printedList.add(p.verdi);

            if (p.venstre != null) qeue.add(p.venstre);
            if (p.høyre != null) qeue.add(p.høyre);
        }
        return printedList;
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        SBinTre<K> binTre = new SBinTre<>(c);
        for (K verdi : data) {
            binTre.leggInn(verdi);
        }
        return binTre;
    }

    /////// Oppgave 6 ////////
    public boolean fjern(T verdi) {

        if (verdi == null) return false;

        Node<T> p = rot, q = null;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);

            if (cmp < 0) {
                q = p;
                p = p.venstre;
            } else if (cmp > 0) {
                q = p;
                p = p.høyre;
            } else {
                break;
            }
        }

        if (p == null) return false;

        if (p.venstre == null || p.høyre == null) {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;
            if (p == rot) rot = b;
            else if (p == q.venstre) q.venstre = b;
            else q.høyre = b;

            if (b != null) b.forelder = q;
        } else {
            Node<T> s = p, r = p.høyre;

            while (r.venstre != null) {
                s = r;
                r = r.venstre;
            }

            p.verdi = r.verdi;

            if (s != p) s.venstre = r.høyre;
            else s.høyre = r.høyre;

        }
        antall--;
        return true;
    }

    public int fjernAlle(T verdi) {
        int fjernet = 0;

        if (tom()) return 0;

        while (fjern(verdi)) {
            fjernet++;
        }
        return fjernet;
    }

    public void nullstill() {

        if (tom()) return;
        Node<T> p = rot;

        p = førstePostorden(p);

        while (antall != 0) {
            if (p != null) {
                fjern(p.verdi);
                p.verdi = null;
                p = nestePostorden(p);
            }
            rot = null;
            antall--;
        }
    }
} // ObligSBinTre
