class Telegrafist extends Thread {
    public Kanal kanal;
    private KryptoMonitor monitor;
    public static int IDteller = 0;
    private int ID;
    public static boolean alleLest = false;

    public Telegrafist(Kanal e, KryptoMonitor i){
        this.kanal = e;
        this.monitor = i;
        this.ID = this.IDteller;
        this.IDteller++;
    }

    public int hentId(){
        return this.ID;
    }

    public int hentAntallKanaler(){
        return this.IDteller;
    }

    @Override
    public void run(){
        while (kanal.lytt() != null){
            try{
                Melding melding = new Melding(this.kanal.lytt(), ID);
                monitor.sendMeldingTilMonitor(melding);
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        this.alleLest = true;
        monitor.SettAlleLest();
    }
}
