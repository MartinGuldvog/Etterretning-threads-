import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class Monitor {
    private ArrayList<Melding> meldinger = new ArrayList<Melding>();
    private Lock laas = new ReentrantLock();
    private Condition ikkeTom = laas.newCondition();
    private int antallInn;
    private int antallUt;

    Monitor(){}

    public void sendMeldingTilMonitor(Melding e) throws InterruptedException{
        laas.lock();
        try{
            // System.out.println("monitor mottar melding");
            this.meldinger.add(e.hentSekvensnummer()-1, e);
            antallInn++;
            ikkeTom.signalAll();
            // for (Melding m : meldinger){
            //     System.out.println("det er noe her");
            // }
        } finally{
            laas.unlock();
        }
    }

    public Melding hentMeldingFraMonitor() throws InterruptedException{
        laas.lock();
        try{
            if (this.meldinger.size() == 0){
                ikkeTom.await();
                System.out.println("venter på å sende");
            }
            System.out.println("monitor sender melding");
            antallUt++;
            return this.meldinger.remove(0);
        } finally{
            laas.unlock();
        }
    }

    public boolean telegrafisterFerdigOgMonitorTom(){
        // if (Telegrafist.alleLest = true && meldinger.size() <= 0){
        if (this.antallUt == this.antallInn){
            if (Telegrafist.alleLest = true){
                System.out.println("telegrafister og montitor ferdig");
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean kryptograferFerdigOgMonitorTom(){
        // if (Kryptograf.alleLest = true && meldinger.size() <= 0){
        if (this.antallUt == this.antallInn){
            if (Kryptograf.alleLest = true){
                System.out.println("kryptografer og monitor ferdig");
                return true;
            }
            return false;
        }
        return false;
    }

}
