import java.util.ArrayList;

class Kryptograf extends Thread {
    private Monitor kryptoMonitor;
    private Monitor dekryptoMonitor;
    private static int IDteller = 0;
    private int ID;
    public boolean alleLest = false;

    public Kryptograf(Monitor b, Monitor c){
        this.kryptoMonitor = b;
        this.dekryptoMonitor = c;
        this.ID = this.IDteller;
        this.IDteller++;
    }

    public int hentId(){
        return this.ID;
    }

    public boolean alleLest(){
        return this.alleLest;
    }

    @Override
    public void run(){
        dekryptoMonitor.leggTilKryptograf(this);
        while (kryptoMonitor.telegrafisterFerdigOgMonitorTom() != true){
            try{
                Melding ny = kryptoMonitor.hentMeldingFraMonitor();
                if (ny.hentMelding() != null){
                    String dekrypterMld = Kryptografi.dekrypter(ny.hentMelding());
                    Melding dekryptertMelding = new Melding(dekrypterMld, ny.hentFraID());
                    dekryptoMonitor.sendMeldingTilMonitor(dekryptertMelding);
                }
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        this.alleLest = true;
        System.out.println("Kryptograf: " + this.ID + " er interrupted");
        // Thread.currentThread().interrupt();
        // return;
        // }
    }
}
