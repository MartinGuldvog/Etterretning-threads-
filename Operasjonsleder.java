import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Collections;

class Operasjonsleder extends Thread {
    private Monitor monitor;
    private int antallkanaler = Telegrafist.IDteller;
    private ArrayList<ArrayList<Melding>> sorterteMeldinger = new ArrayList<ArrayList<Melding>>();
    private static int dokuementTeller = 1;

    public Operasjonsleder(Monitor m){
        this.monitor = m;
    }

    public void lagBeholdere(){
        for (int i = 0; i <= antallkanaler; i++){
            ArrayList<Melding> ny = new ArrayList<Melding>();
            sorterteMeldinger.add(ny);
        }
    }

    public void testInnhold(){
        for (ArrayList<Melding> i : sorterteMeldinger){
            for (Melding e : i){
                System.out.println(e.hentMelding());
            }
        }
    }

    public void testSekvensNummer(){
        int teller = 0;
        for (ArrayList<Melding> i : sorterteMeldinger){
            System.out.println("\nTelegrafist " + teller + " har lyttet på:");
            teller++;
            for (Melding e : i){
                // System.out.println(e.hentSekvensnummer());
                System.out.print(e.hentMelding());
            }
        }
    }

    public static void skrivTilFil(ArrayList<ArrayList<Melding>> liste, int index, String fil) {
        File f = new File(fil);
        try{
            PrintWriter printer = new PrintWriter(f ,"utf-8");
            ArrayList<Melding> temp = liste.get(index);

            for (Melding m : temp){
                printer.append(m.hentMelding() + "\n\n");
            }
            printer.close();

            }catch (Exception e) {
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
                    this.sorterteMeldinger.get(temp).add(ny);
                }
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        //sorter listene her!
        for (ArrayList<Melding> array : this.sorterteMeldinger){
            Collections.sort(array);
        }

        testSekvensNummer();
        // testInnhold();
        for (int i = 0; i <= antallkanaler -1; i++){
            skrivTilFil(sorterteMeldinger, i, "tekst" + this.dokuementTeller + ".txt");
            dokuementTeller++;
        }
    }
}
