import java.util.ArrayList;

public class Hovedprogram {

    public static Operasjonssentral ops = new Operasjonssentral(3);
    public static Kanal[] kanaler = ops.hentKanalArray();
    public static KryptoMonitor kryptoMonitor = new KryptoMonitor();
    public static DekryptoMonitor dekryptoMonitor = new DekryptoMonitor();
    public static ArrayList<Telegrafist> telegrafister = new ArrayList<Telegrafist>();

    public static void main(String[] args) {

        opprettTelegraf();


        // String dekrypterMld = Kryptografi.dekrypter(kryptertMelding);

    }

    public static void opprettTelegraf(){
        for (Kanal e : kanaler){
            telegrafister.add(new Telegrafist(kanaler,kryptoMonitor));
        }
    }
}
