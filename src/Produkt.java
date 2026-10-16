public class Produkt {
    private String name;
    private double pris;
    private double momsSats;


    public Produkt(String name, double pris) {
        this(name, pris, 0);
    }

    public Produkt(String name, double pris, double momsSats) {
        this.name = name;
        this.pris = pris;
        this.momsSats = momsSats;
    }


    public String getName(){
        return name;

    }
    public double getPris(){
        return pris;


    }
    public double getMomsSats(){
        return momsSats;
    }
}

