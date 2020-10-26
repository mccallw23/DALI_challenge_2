import java.util.ArrayList;



// in Blitz survival games, one sub objective is to obtain the "blitz star" which grants the player a massive advantage.
// the first chest a player opens has a 5% chance of containing the star.  Each subsequent chest has an additional
// 5% chance of containing the blitz star.

//The following program provides a monte carlo simulation of opening chests in blitz survival games in order to determine
// how many chests a player needs to have a reasonable chance of obtaining the blitz star


public class BlitzStar {

    public static void main(String[] args) {

        //number of times to run monte carlo simulation
       double timesToExecute = 100000;

       // for each simulation, store the number of chests it took to get the blitz star as a value in an arraylist (to be averaged later)
        ArrayList<Integer> numtimes = new ArrayList<Integer>();


        //run the simulations
        for (int i = 0; i< timesToExecute; i++)
        {
            //how many times a chest has been opened
            int times = 1;
            //generate a random number between 0 - 100
            double random = Math.random() * 100;
            // the random number is 0-100, so there's a 5% chance its between 0 and 5 (chest contains blitz star 5% of the time)
            double threshold = 5;

            // if the random number is less than the threshold, the chest contains a blitz star, otherwise, increase
            // chance by 5% and try again.
            while (random > threshold)
            {
                //create a new random number for comparison
                random = Math.random() * 100;
                //make the next chest 5% more likely to contain the star
                threshold += 5;
                // we are opening 1 more chest
                times +=1;
            }

            //once the chest contains the star, add how many times it took to the list

            numtimes.add(times);
            //run a new simulation
        }


        //time for statistical analysis


        // to compute the average number of chests it took over the simulations to get the blitz star
        // sum every element in the array, divide by the number of elements in the array
        double sum = 0;
        double division = 0;

        //find the maximum chests it took over the simulations
        double max = numtimes.get(0);
        //find the minimum number of chests it took in the simulations
        double min = numtimes.get(0);

        // if a player has access to a certain number of chests, how often will they get the star?
        int numAvailable = 4;
        // how many blitz stars would they have gotten?  How many missed?
        double numYes = 0;
        double numNo = 0;

        //
        for (Integer j: numtimes)
        {
            //compute the sum and the number to divide the sum by to get the average
            sum += j;
            division += 1;

            // update new minimum value
            if (min > j)
            {
                min = j;
            }

            // update new maximum value
            if(max < j)
            {
                max = j;
            }

            // if the number of times it took is less than the number of chests the player has access to, the player
            // will find the blitz star.
            if(j <= numAvailable)
            {
                numYes++;

            }
            // otherwise the player didn't have enough chests.
            else
                {
                    numNo++;
                }



        }

        //report results in console


        // how often did the player get the star given how many chests they had?
        double percent = (numYes/timesToExecute) * 100;


        // average number of chests it took to obtain the blitz star.
        double avg = sum/division;

        // report results
        System.out.println("average number of chests " + avg);
        System.out.println("max number of chests in " + timesToExecute + " runs: " + max);
        System.out.println("min number of chests in " + timesToExecute + " runs: " + min);
        System.out.println("if you have access to " + numAvailable + " chests, your chances of success are: " + percent + "%");


    }
}
