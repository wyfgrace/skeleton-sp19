import java.util.function.DoubleBinaryOperator;

public class NBody {
    public static double readRadius(String file_name) {
        final In in = new In(file_name);
        in.readInt();
        return in.readDouble();
    }

    public static Body[] readBodies(String file_name) {
        final In in = new In(file_name);
        final int num = in.readInt();
        final double radius = in.readDouble();
        final Body[] result = new Body[num];
        int n = 0;
        while (!in.isEmpty() && n < num) {
            final double xxpoint = in.readDouble();
            final double yypoint = in.readDouble();
            final double xxvel = in.readDouble();
            final double yyvel = in.readDouble();
            final double planet_mass = in.readDouble();
            final String planet_name = in.readString();
            result[n] = new Body(xxpoint, yypoint, xxvel, yyvel, planet_mass, planet_name);
            n = n + 1;
        }
        return result;
    }

    public static void main(String args[]) {
        final double T = Double.parseDouble(args[0]);
        final double dt = Double.parseDouble(args[1]);
        final String filename = args[2];
        final Body[] bodies = readBodies(filename);
        final double universeRadius = readRadius(filename);

        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time < T) {
            final double[] xForce = new double[bodies.length];
            final double[] yForce = new double[bodies.length];
            for (int i = 0; i < bodies.length; i++) {
                xForce[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForce[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, bodies[i].calcNetForceExertedByX(bodies), bodies[i].calcNetForceExertedByY(bodies));
            }
            time = time + dt;

            StdDraw.clear();
            StdDraw.setScale(-universeRadius, universeRadius);
            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Body body : bodies) {
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
