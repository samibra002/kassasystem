import java.util.ArrayList;

public class Receipt {
    private static int nextNumber = 1;
    private int kvittoNumber;
    private ArrayList<ReceiptRow> rader = new ArrayList<>();

    public Receipt() {
        kvittoNumber = nextNumber;
        nextNumber++;
    }

    public void addRow(Produkt produkt, int antal) {
        ReceiptRow rad = new ReceiptRow(produkt, antal);
        rader.add(rad);


    }

    public double getTotal() {
        double total = 0;
        for (ReceiptRow rad : rader) {
            total = total + rad.getSumma();
        }
        return total;


    }

public double getTotalMoms() {
    double moms = 0;
    for (ReceiptRow rad : rader) {
        moms = moms + rad.getMoms();
    }
    return moms;

}


    public double getMomsForRate(double sats) {
        double moms = 0;

        for (ReceiptRow rad : rader) {
            if (rad.getProdukt().getMomsSats() == sats) {
                moms = moms + rad.getMoms();
            }
        }

        return moms;
    }



public double getTotalNetto(){
        return getTotal() - getTotalMoms();
}


    public String getKvittoText() {
       String text = "Kvittonumber: " + kvittoNumber + "´\n\n";

        for (ReceiptRow rad : rader) {

            text = text + rad.getProdukt().getName()
                    + " " + rad.getAntal()
                    + " x " + rad.getProdukt().getPris()
                    + " = " + rad.getSumma() + " kr\n";
        }


        text = text + "\nNetto: " + String.format("%.2f", getTotalNetto()) + " kr\n";


        text = text + "Moms 12%: " + String.format("%.2f", getMomsForRate(12)) + " kr\n";

        text = text + "Moms 25%: " + String.format("%.2f", getMomsForRate(25)) + " kr\n";


        text = text + "Brutto TOTAL: " + String.format("%.2f", getTotal()) + " kr\n";


        return text;
    }



}


