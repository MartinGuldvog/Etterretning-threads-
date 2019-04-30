class Melding implements Comparable<Melding>{
    public String innhold;
    public static int sekvensteller;
    private int sekvensnummer;
    public int fraID;

    public Melding(String e, int i){
        innhold = e;
        fraID = i;
        this.sekvensteller++;
        this.sekvensnummer = this.sekvensteller;
    }

    public String hentMelding(){
        return innhold;
    }

    public int hentSekvensnummer(){
        return this.sekvensnummer;
    }

    public int hentFraID(){
        return fraID;
    }

    public void leggTilKryptertString(String s){
        this.innhold = s;
    }

    @Override
    public int compareTo(Melding m){
        Integer denne = this.sekvensnummer;
        Integer sammenlign = m.hentSekvensnummer();
        return denne.compareTo(sammenlign);
    }
}
