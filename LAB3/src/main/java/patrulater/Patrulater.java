package patrulater;

class Patrulater {
    public int latura1, latura2, latura3, latura4;
    public double unghi1, unghi2, unghi3, unghi4;

    public Patrulater() {
        this(0, 0, 0, 0);
    }

    public Patrulater(int latura1, int latura2, int latura3, int latura4) {
        this.latura1 = latura1;
        this.latura2 = latura2;
        this.latura3 = latura3;
        this.latura4 = latura4;
    }

    public Patrulater(double unghi1, double unghi2, double unghi3, double unghi4) {
        this(0, 0, 0, 0, unghi1, unghi2, unghi3, unghi4);
    }

    public Patrulater(int latura1, int latura2, int latura3, int latura4,
                      double unghi1, double unghi2, double unghi3, double unghi4) {
        this(latura1, latura2, latura3, latura4);
        this.unghi1 = unghi1;
        this.unghi2 = unghi2;
        this.unghi3 = unghi3;
        this.unghi4 = unghi4;
    }

    public int perimetru() {
        int result;
        result = latura1 + latura2 + latura3 + latura4;
        return result;
    }
}

class Paralelogram extends Patrulater {
    public Paralelogram(int latura1, int latura2, int unghi1) {
        super(latura1, latura2, latura1, latura2, unghi1, 180 - unghi1, unghi1, 180 - unghi1);
    }

    public int arie() {
        int result;
        result = latura1 * latura2 * (int) Math.sin(unghi1);
        return result;
    }

    public int perimetru() {
        int result;
        result = 2 * latura1 + 2 * latura2;
        return result;
    }

}

class Romb extends Paralelogram
{
    public int d1, d2;

    public Romb(int latura1, int unghi1, int d1, int d2)
    {
        super(latura1, latura1, unghi1);
        this.d1 = d1;
        this.d2 = d2;
    }

    public int arie() {
        int result;
        result = (d1*d2)/2;
        return result;
    }


}

class Dreptunghi extends Paralelogram
{
    public Dreptunghi(int latura1, int latura2)
    {
        super(latura1, latura2, 90);
    }

    public int arie() {
        int result;
        result = latura1 * latura2;
        return result;
    }


}


class Patrat extends Dreptunghi
{
    public Patrat(int latura1)
    {
        super(latura1, latura1);
    }

    public int arie() {
        int result;
        result = latura1 * latura1;
        return result;
    }

}

class Main
{
    public static void main(String[] args)
    {
        Patrat patrat = new Patrat(5);
        System.out.println(patrat.arie());
        System.out.println(patrat.perimetru());

        Romb romb = new Romb(5, 60, 4, 6);
        System.out.println(romb.arie());
    }
}
