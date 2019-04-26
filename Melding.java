class Melding {
    public String innhold;
    public static int sekvensnummer;
    public int fraID;
    // public boolean harBlittDekryptert = false;

    public Melding(String e, int i){
        innhold = e;
        fraID = i;
        sekvensnummer++;
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
