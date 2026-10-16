public class ReceiptRow {
    private Produkt produkt;
    private int antal;


    public ReceiptRow(Produkt produkt, int antal){
        this.produkt = produkt;
        this.antal = antal;

    }

    public Produkt getProdukt(){
        return produkt;
    }
    public int getAntal(){
        return antal;

    }
    public double getSumma(){
        return antal * produkt.getPris();

    }
    public double getMoms(){
        double brutto = getSumma();
        double momsSats =
                produkt.getMomsSats();
                double netto = brutto / (1+ momsSats /100);
                return brutto - netto;
        }
        public double getNetto(){
        return getSumma() - getMoms();
        }
    }



