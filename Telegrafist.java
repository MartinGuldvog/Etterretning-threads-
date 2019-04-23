class Telegrafist{
    private Kanal kanal;
    private KryptoMonitor monitor;

    public Telegrafist(Kanal e, KryptoMonitor i){
        kanal = e;
        monitor = i;
    }

    public Melding lytt(){
        while (kanal.lytt() != null){
            Melding melding = new Melding(this.kanal.lytt(), this.kanal);
            monitor.add(melding);
        }
    }
}
