import java.util.ArrayList;

class Kryptograf extends Thread {
    private Monitor kryptoMonitor;
    private Monitor dekryptoMonitor;
    private static int IDteller = 0;
    private int ID;
    public static boolean alleLest = false;

    public Kryptograf(Monitor b, Monitor c){
        this.kryptoMonitor = b;
        this.dekryptoMonitor = c;
        this.ID = this.IDteller;
        this.IDteller++;
    }

    @Override
    public void run(){
        boolean kjor = true;
        while (kryptoMonitor.telegrafisterFerdigOgMonitorTom() != true){
        // while (kryptoMonitor.hentMeldingFraMonitor() != null){
            System.out.println("test while loop krypto");
            try{
                Melding ny = kryptoMonitor.hentMeldingFraMonitor();
                String dekrypterMld = Kryptografi.dekrypter(ny.hentMelding());
                Melding dekryptertMelding = new Melding(dekrypterMld, ny.hentFraID());
                dekryptoMonitor.sendMeldingTilMonitor(dekryptertMelding);
                System.out.println("test kryp");
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        this.alleLest = true;
    }
}
