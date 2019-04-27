import java.util.ArrayList;

public class Hovedprogram {

    public static Operasjonssentral ops = new Operasjonssentral(3);
    public static Kanal[] kanaler = ops.hentKanalArray();
    public static Monitor kryptoMonitor = new Monitor();
    public static Monitor dekryptoMonitor = new Monitor();
    public static ArrayList<Telegrafist> telegrafister = new ArrayList<Telegrafist>();
    public static ArrayList<Kryptograf> kryptografer = new ArrayList<Kryptograf>();
    public static Operasjonsleder operasjonsleder;

    public static void main(String[] args) {

        opprettTelegraf();
        opprettKryptografer(20);
        startAlt();
        operasjonsleder.testInnhold();

        // for (Telegrafist t : telegrafister){
        //     System.out.println("Telegrafist. KanalID = " + t.hentKanal().hentId());
        // }
        //
        // for (Kryptograf k : kryptografer){
        //     System.out.println("Kryptograf. ID = " + k.hentId());
        // }



    }

    public static void opprettTelegraf(){
        for (Kanal e : kanaler){
            Telegrafist ny = new Telegrafist(e, kryptoMonitor);
            telegrafister.add(ny);
        }
    }

    public static void startTelegrafister(){
        for (Telegrafist t : telegrafister){
            t.start();
        }
    }

    public static void opprettKryptografer(int e){
        for (int i=0 ; i < e; i++){
            Kryptograf ny = new Kryptograf(kryptoMonitor, dekryptoMonitor);
            kryptografer.add(ny);
        }
    }

    public static void startKryptografer(){
        for (Kryptograf k : kryptografer){
            k.start();
        }
    }

    public static void startAlt(){
        startTelegrafister();
        startKryptografer();
        operasjonsleder = new Operasjonsleder(dekryptoMonitor);
        operasjonsleder.start();
    }

}
