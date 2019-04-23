class Melding {
    public String innhold;
    public static int sekvensnummer;
    public int fraID;

    public Melding(String e, Kanal i){
        innhold = e;
        fraID = i.hentId();
        sekvensnummer++;
    }
}
