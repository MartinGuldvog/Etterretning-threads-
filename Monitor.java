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
    private ArrayList<Telegrafist> telegrafister = new ArrayList<Telegrafist>();
    private ArrayList<Kryptograf> kryptografer = new ArrayList<Kryptograf>();

    Monitor(){}

    public boolean telegrafisterFerdig(){
        for (Telegrafist t : this.telegrafister){
            if (t.alleLest() == false){
                return false;
            }
        }
        return true;
    }

    public boolean kryptograferFerdig(){
        for (Kryptograf k : this.kryptografer){
            if (k.alleLest() == false){
                return false;
            }
        }
        return true;
    }


    public boolean harMottatMelding(){
        return antallInn >= 1;
    }

    public void sendMeldingTilMonitor(Melding e) throws InterruptedException{
        laas.lock();
        try{
            this.meldinger.add(/*e.hentSekvensnummer() -1,*/ e);
            antallInn++;
            ikkeTom.signal();
        } finally{
            laas.unlock();
        }
    }

    public Melding hentMeldingFraMonitor() throws InterruptedException{
        laas.lock();
        try{
            if (this.meldinger.size() == 0){
                ikkeTom.await();
            }
            antallUt++;
            return this.meldinger.remove(0);
        } finally{
            laas.unlock();
        }
    }

    public boolean telegrafisterFerdigOgMonitorTom(){
        if (meldinger.size() == 0 && telegrafisterFerdig() == true && this.antallUt == this.antallInn){
            System.out.println("telegrafister og montitor ferdig");
            // ikkeTom.signalAll();
            return true;
        }
        return false;
    }

    public boolean kryptograferFerdigOgMonitorTom(){
        if (meldinger.size() == 0 && kryptograferFerdig() == true && this.antallUt == this.antallInn){
            System.out.println("kryptografer og monitor ferdig");
            // ikkeTom.signalAll();
            return true;
        }
        return false;
    }

    public void leggTilTelegraf(Telegrafist t){
        this.telegrafister.add(t);
    }

    public void leggTilKryptograf(Kryptograf k){
        this.kryptografer.add(k);
    }

}
//
// this.antallUt == this.antallInn &&
// this.antallUt == this.antallInn &&
