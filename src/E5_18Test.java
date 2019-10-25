import junit.framework.TestCase;

public class E5_18Test extends TestCase {
    E5_18 testTax;
    public E5_18Test() {
    }

    public void testGetIncomeTaxU50K() {
        testTax = new E5_18(25432.55);
        assertEquals("Error 1", "254.33", testTax.getIncomeTax());
    }

    public void testGetIncomeTaxU75K() {
        testTax = new E5_18(62093.99);
        assertEquals("Error 2","741.88", testTax.getIncomeTax());
    }

    public void testGetIncomeTaxU100K() {
        testTax = new E5_18(97114.57);
        assertEquals("Error 3","1,663.44", testTax.getIncomeTax());
    }

    public void testGetIncomeTaxU250K() {
        testTax = new E5_18(224320.55);
        assertEquals("Error 4","6,722.82", testTax.getIncomeTax());
    }

    public void testGetIncomeTaxU500K() {
        testTax = new E5_18(325432.55);
        assertEquals("Error 5","11,521.63", testTax.getIncomeTax());
    }

    public void testGetIncomeTaxO500K() {
        testTax = new E5_18(25432000.55);
        assertEquals("Error 6","1,516,170.03", testTax.getIncomeTax());
    }


}