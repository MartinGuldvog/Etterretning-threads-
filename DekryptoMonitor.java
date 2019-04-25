import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class DekryptoMonitor {
    private ArrayList<Melding> meldinger = new ArrayList<Melding>();
    private Lock laas = new ReentrantLock();
    private Condition ikkeTom = laas.newCondition();

    DekryptoMonitor(){}

    public void sendMeldingTilDekryptoMonitor(Melding e) throws InterruptedException{
        laas.lock();
        try{
            this.meldinger.add(e);
            ikkeTom.signalAll();
        } finally{
            laas.unlock();
        }
    }

    public Melding sendMeldingTilOperasjonsleder() throws InterruptedException{
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

    public int stoerrelse(){
        return meldinger.size();
    }

}
