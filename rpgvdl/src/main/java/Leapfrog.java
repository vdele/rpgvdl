/*
 * Leapfrog.java by Richard J. Davies
 * from `Introductory Java for Scientists and Engineers'
 * chapter: `Numerical Computation'
 * section: `Example - The Leapfrog Method'
 *
 * The program solves the ODE y' = -10y by the leapfrog
 * method with a step size of 0.001. It compares the
 * results with the analytical solution of the ODE.
 */
public class Leapfrog
{
    // Constants for the iteration
    public static final double STEPSIZE = 0.001;
    public static final int STEPS = 2000;


    // The `main' method actually implements
    // the Leapfrog method.
    public static void main(final String[] argv)
    {
        // Set up array for y_n values and
        // insert initial data.
        final double[] y = new double[Leapfrog.STEPS + 1];
        y[0] = 1;

        // Perform first step by another method
        // as leapfrog needs two points.
        y[1] = y[0] - Leapfrog.STEPSIZE * 10 * y[0];

        // Perform iteration
        for (int i=2; i<=Leapfrog.STEPS; i++)
        {
            y[i] = -2 * Leapfrog.STEPSIZE * 10 * y[i-1] + y[i-2];

            // Print computed value and error.
            System.out.println("At t=" + i * Leapfrog.STEPSIZE
                    + " y estimated at " + y[i]
                            + " an error of "
                            + (y[i] - Math.exp(-10*i*Leapfrog.STEPSIZE)));
        }
    }
}