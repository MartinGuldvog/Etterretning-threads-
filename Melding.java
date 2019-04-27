class Melding {
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
}
