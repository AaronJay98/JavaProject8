import java.text.DecimalFormat;

public class E5_18 {
    private double dblUserIncome;                                       //Will hold the users income as he/she inputs it
    private double dblUserIncomeTax = 0;                                //Will hold the users income tax after calculating what his tax percentage is
    private String strDecimalFormat = "##0.00";                         //This will put the users income tax into the correct format (may be adjusted starting at line 39-42)
    private DecimalFormat dfIncomeTax;

    public E5_18(double inputIncome) {
        if(inputIncome < 0.0) {
            inputIncome = 0.0;
        }
        dblUserIncome = inputIncome;
    }

    public String getIncomeTax() {
        if (dblUserIncome <= 50000)
            dblUserIncomeTax = dblUserIncome * .01;
        else if (dblUserIncome <= 75000) {
            dblUserIncomeTax = 50000 * .01;
            dblUserIncomeTax += (dblUserIncome - 50000) * .02;
        }
        else if (dblUserIncome <= 100000) {
            dblUserIncomeTax =  50000 * .01;
            dblUserIncomeTax += 25000 * .02;
            dblUserIncomeTax += (dblUserIncome - 75000) * .03;
        }
        else if (dblUserIncome <= 250000) {
            dblUserIncomeTax =  50000 * .01;
            dblUserIncomeTax += 25000 * .02;
            dblUserIncomeTax += 25000 * .03;
            dblUserIncomeTax += (dblUserIncome - 100000) * .04;
        }
        else if (dblUserIncome <= 500000) {
            dblUserIncomeTax =  50000 * .01;
            dblUserIncomeTax += 25000 * .02;
            dblUserIncomeTax += 25000 * .03;
            dblUserIncomeTax += 150000 * .04;
            dblUserIncomeTax += (dblUserIncome - 250000) * .05;
        }
        else {
            dblUserIncomeTax = 50000 * .01;
            dblUserIncomeTax += 25000 * .02;
            dblUserIncomeTax += 25000 * .03;
            dblUserIncomeTax += 150000 * .04;
            dblUserIncomeTax += 250000 * .05;
            dblUserIncomeTax += (dblUserIncome - 500000) * .06;
        }
        formatTax();

        dfIncomeTax = new DecimalFormat(strDecimalFormat);       //Creates the actual decimal format

        return dfIncomeTax.format(dblUserIncomeTax);
    }

    private void formatTax() {
        double dblTempUserIncome = dblUserIncome;               //Temp variable for calculations in following while loop
        while((dblTempUserIncome / 1000) > 0) {                 //Checks if another comma is needed when outputting
            strDecimalFormat = "###," + strDecimalFormat;       //Creates the comma in the formatting and allows for 3 extra digits
            dblTempUserIncome = dblTempUserIncome /1000;        //Sets the temp variable to result from while condition to be reevaluated
        }
    }
//    while(dblUserIncome < 0) {
//        System.out.println("Error: You have entered a number below 0.\nPlease enter your non-negative income: ");
//        dblUserIncome = sUserInput.nextDouble();
//    }
}
