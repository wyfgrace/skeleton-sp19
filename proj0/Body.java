public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;



    public Body(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    private static double square(double x) {
        return x * x;
    }

    public double calcDistance(Body other) {
        return Math.sqrt(square(other.xxPos - xxPos) + square(other.yyPos - yyPos));
    }

    public double calcForceExertedBy(Body other) {
        return 6.67 * 1e-11 * mass * other.mass / square(calcDistance(other));
    }

    public double calcForceExertedByX(Body other) {
        return calcForceExertedBy(other) * (other.xxPos - xxPos) / calcDistance(other);
    }

    public double calcForceExertedByY(Body other) {
        return calcForceExertedBy(other) * (other.yyPos - yyPos) / calcDistance(other);
    }

    public double calcNetForceExertedByX(Body[] bodies){
        int n = 0;
        double xNetForce = 0;
        while (n < bodies.length){
            if (this != bodies[n]) {
                xNetForce = xNetForce + calcForceExertedByX(bodies[n]);
            }
            n=n+1;
        }
        return xNetForce;
    }

    public double calcNetForceExertedByY(Body[] bodies){
        int n = 0;
        double yNetForce = 0;
        while (n < bodies.length){
            if (this != bodies[n]) {
                yNetForce = yNetForce + calcForceExertedByY(bodies[n]);
            }
            n=n+1;
        }
        return yNetForce;
    }

    public void update(double dt, double fX, double fY) {
            double acX = fX / mass;
            double acY = fY / mass;
            xxVel = xxVel + dt * acX;
            yyVel = yyVel + dt * acY;
            xxPos = xxPos + dt * xxVel;
            yyPos = yyPos + dt * yyVel;
    }


//
//    public void draw() {
//        StdDraw.setPenRadius(0.02);
//        StdDraw.setPenColor(StdDraw.BLUE);
//        StdDraw.point(x, y);
//    }
}

