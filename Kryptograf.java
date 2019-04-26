import java.util.ArrayList;

class Kryptograf extends Thread {
    // private Operasjonsleder operasjonsleder;
    private KryptoMonitor kryptoMonitor;
    private DekryptoMonitor dekryptoMonitor;
    private Operasjonsleder operasjonsleder;
    private static int IDteller = 0;
    private int ID;
    public boolean alleDekryptert = false;

    public Kryptograf(Operasjonsleder a, KryptoMonitor b, DekryptoMonitor c){
        this.operasjonsleder = a;
        this.kryptoMonitor = b;
        this.dekryptoMonitor = c;
        this.ID = this.IDteller;
        this.IDteller++;
    }

    @Override
    public void run(){
        int teller = 0;
        while (kryptoMonitor.sjekkAlleLest() != true){
            try{
                Melding ny = kryptoMonitor.sendMeldingTilKryptograf();
                String dekrypterMld = Kryptografi.dekrypter(ny.hentMelding());
                Melding dekryptertMelding = new Melding(dekrypterMld, ny.hentFraID());
                dekryptoMonitor.sendMeldingTilDekryptoMonitor(dekryptertMelding);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        this.alleDekryptert = true;
        dekryptoMonitor.settAlleDekryptert();
    }
}
