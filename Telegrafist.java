class Telegrafist extends Thread {
    public Kanal kanal;
    private Monitor monitor;
    public static int IDteller = 0;
    private int ID;
    private boolean alleLest = false;

    public Telegrafist(Kanal e, Monitor i){
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

    public Kanal hentKanal(){
        return this.kanal;
    }

    public boolean alleLest(){
        return this.alleLest;
    }

    @Override
    public void run(){
        monitor.leggTilTelegraf(this);
        while (kanal.lytt() != null){
            try{
                Melding melding = new Melding(this.kanal.lytt(), ID);
                if (melding.hentMelding() != null){
                    monitor.sendMeldingTilMonitor(melding);
                    System.out.println("test telegraf: Melding: " + melding.hentSekvensnummer() + " TelegrafensID " + this.ID);
                }
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        this.alleLest = true;
        System.out.println("telegraf: " + this.ID + " er interrupted");
        Thread.currentThread().interrupt();
    }
}
