import java.util.ArrayList;

class KryptoMonitor {
    private ArrayList<Melding> meldinger = new ArrayList<Melding>();

    KryptoMonitor(){}

    public void sendMelding(Melding e){
        meldinger.add(e);
    }


}
