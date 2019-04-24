import java.util.ArrayList;

class DekryptoMonitor {
    private ArrayList<Melding> meldinger = new ArrayList<Melding>();

    public void sendMeldingTilDekMonitor(Melding e){
        this.meldinger.add(e);
    }

    public Melding sendMeldingTilOperasjonsleder(int i){
        return this.meldinger.get(i);
    }

}
