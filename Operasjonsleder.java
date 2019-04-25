import java.util.ArrayList;

class Operasjonsleder extends Thread {
    private DekryptoMonitor monitor;
    private int antallkanaler = Telegrafist.IDteller;
    private ArrayList<ArrayList<String>> SorterteMeldinger = new ArrayList<ArrayList<String>>();

    public Operasjonsleder(DekryptoMonitor m){
        this.monitor = m;
    }

    public void lagBeholdere(){
        for (int i = 0; i <= antallkanaler; i++){
            ArrayList<String> ny = new ArrayList<String>();
            SorterteMeldinger.add(ny);
        }
    }

    // public void sorter(Melding e){
    //     meldinger.get(e.hentFraID()).add(e.hentMelding());
    // }

    @Override
    public void run(){
        try{
            Melding ny = monitor.sendMeldingTilOperasjonsleder();
            int temp = ny.hentFraID();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

    }
}
