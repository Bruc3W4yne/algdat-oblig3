package no.oslomet.cs.algdat.Oblig3;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;

public class SBinTre<T> {

    public static void main(String[] args) {
        Integer[] a = {4,7,2,9,4,10,8,7,4,6};
        SBinTre<Integer> tre = new SBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) { tre.leggInn(verdi); }

        System.out.println(tre.antall());      // Utskrift: 10
        System.out.println(tre.antall(5));     // Utskrift: 0
        System.out.println(tre.antall(4));     // Utskrift: 3
        System.out.println(tre.antall(7));     // Utskrift: 2
        System.out.println(tre.antall(10));    // Utskrift: 1
    }

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

    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postorden(Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public ArrayList<T> serialize() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }


} // ObligSBinTre
