import java.util.function.DoubleBinaryOperator;

public class NBody {
    public static double readRadius(String file_name) {
        In in = new In (file_name);
        in.readInt();
        return in.readDouble();
    }


    public static Body[] readBodies(String file_name) {
        In in = new In (file_name);
        int num = in.readInt();
        double radius = in.readDouble();
        Body[] result = new Body[num];
        int n = 0;
        while (! in.isEmpty() && n<num){
            double xxpoint = in.readDouble();
            double yypoint = in.readDouble();
            double xxvel = in.readDouble();
            double yyvel = in.readDouble();
            double planet_mass = in.readDouble();
            String planet_name = in.readString();
            result[n] = new Body(xxpoint, yypoint, xxvel, yyvel,planet_mass,planet_name);
            n = n+1;
        }
        return result;
    }
    public static void main(String args[]) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] bodies = readBodies(filename);
        double universeRadius = readRadius(filename);

        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time < T) {
            double[] xForce = new double[bodies.length];
            double[] yForce = new double[bodies.length];
            for(int i = 0; i<bodies.length; i ++) {
                xForce[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForce[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i< bodies.length; i ++) {
                bodies[i].update(dt, bodies[i].calcNetForceExertedByX(bodies), bodies[i].calcNetForceExertedByY(bodies));
            }
            time = time + dt;

            StdDraw.clear();
            StdDraw.setScale(- universeRadius, universeRadius);
            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Body body: bodies) {
                body.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", universeRadius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }

}
