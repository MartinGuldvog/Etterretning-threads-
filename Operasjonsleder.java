import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

class Operasjonsleder extends Thread {
    private Monitor monitor;
    private int antallkanaler = Telegrafist.IDteller;
    private ArrayList<ArrayList<String>> sorterteMeldinger = new ArrayList<ArrayList<String>>();
    private static int dokuementTeller = 1;

    public Operasjonsleder(Monitor m){
        this.monitor = m;
    }

    public void lagBeholdere(){
        for (int i = 0; i <= antallkanaler; i++){
            ArrayList<String> ny = new ArrayList<String>();
            sorterteMeldinger.add(ny);
        }
    }

    public void testInnhold(){
        for (ArrayList<String> i : sorterteMeldinger){
            for (String e : i){
                System.out.println(e);
            }
        }
    }

    public static void skrivTilFil(ArrayList<ArrayList<String>> liste, int index, String fil) {
        File f = new File(fil);
        try{
            PrintWriter printer = new PrintWriter(f /*,"utf-8"*/);
            ArrayList<String> temp = liste.get(index);

            for (String s : temp){
                printer.append(s + "\n\n");
            }
            printer.close();

            }catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
    }

    @Override
    public void run(){
        lagBeholdere();
        while (monitor.kryptograferFerdigOgMonitorTom() != true){
            try{
                Melding ny = monitor.hentMeldingFraMonitor();
                if (ny.hentMelding() != null){
                    int temp = ny.hentFraID();
                    this.sorterteMeldinger.get(temp).add(ny.hentMelding());
                    System.out.println(ny.hentMelding());
                }
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        testInnhold();
        for (int i = 0; i <= antallkanaler -1; i++){
            skrivTilFil(sorterteMeldinger, i, "tekst" + this.dokuementTeller + ".txt");
            dokuementTeller++;
        }
    }
}
