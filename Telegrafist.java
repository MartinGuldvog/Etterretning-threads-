class Telegrafist extends Thread {
    private Kanal kanal;
    private KryptoMonitor monitor;
    private static int ID = 0;

    public Telegrafist(Kanal[] e, KryptoMonitor i){
        this.kanal = e[this.ID];
        this.monitor = i;
        this.ID++;
    }

    // public int hentID(){
    //     return this.ID;
    // }

    @Override
    public void run(){
        while (kanal.lytt() != null){
            Melding melding = new Melding(this.kanal.lytt(), this.kanal);
            monitor.sendMelding(melding);
        }
    }
}
