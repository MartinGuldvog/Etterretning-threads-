import java.util.ArrayList;

class KryptoMonitor {
    private ArrayList<Melding> meldinger = new ArrayList<Melding>();

    KryptoMonitor(){}

    public void sendMeldingTilMonitor(Melding e){
        this.meldinger.add(e);
    }

    public Melding sendMeldingTilKryptograf(int i){
        return this.meldinger.get(i);
    }


}
