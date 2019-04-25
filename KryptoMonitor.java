import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class KryptoMonitor {
    private ArrayList<Melding> meldinger = new ArrayList<Melding>();
    private Lock laas = new ReentrantLock();
    private boolean alleLest;
    private Condition ikkeTom = laas.newCondition();
    // private Condition ikkeFull = laas.newCondition();

    KryptoMonitor(){}

    public void sendMeldingTilMonitor(Melding e) throws InterruptedException{
        laas.lock();
        try{
            this.meldinger.add(e);
            ikkeTom.signalAll();
        } finally{
            laas.unlock();
        }
    }

    public Melding sendMeldingTilKryptograf() throws InterruptedException{
        laas.lock();
        try{
            if (this.meldinger.size() == 0){
                ikkeTom.await();
            }
            return this.meldinger.remove(0);
        } finally{
            laas.unlock();
        }
    }

    public int stoerrelse() throws InterruptedException{
        laas.lock();
        try{
            return meldinger.size();
        }finally{
            laas.unlock();
        }
    }

    public void SettAlleLest(){
        this.alleLest = true;
    }

    public boolean sjekkAlleLest(){
        return alleLest;
    }

}
