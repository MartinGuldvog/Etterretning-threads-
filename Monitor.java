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
        if (this.telegrafister.size() > 0){
            for (Telegrafist t : this.telegrafister){
                if (t.alleLest() == false){
                    return false;
                }else{
                    return true;
                }
            }
        }
        return false;
    }

    public boolean kryptograferFerdig(){
        if (this.kryptografer.size() > 0){
            for (Kryptograf k : this.kryptografer){
                if (k.alleLest() == false){
                    return false;
                }else{
                    return true;
                }
            }
        }
        return true;
    }

    public void sendMeldingTilMonitor(Melding e) throws InterruptedException{
        laas.lock();
        try{
            this.meldinger.add(e);
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
            return true;
        }else{
            return false;
        }
    }

    public boolean kryptograferFerdigOgMonitorTom(){
        if (meldinger.size() == 0 && kryptograferFerdig() == true && this.antallUt == this.antallInn){
            return true;
        }else{
            return false;
        }
    }

    public void leggTilTelegraf(Telegrafist t){
        this.telegrafister.add(t);
    }

    public void leggTilKryptograf(Kryptograf k){
        this.kryptografer.add(k);
    }

}
