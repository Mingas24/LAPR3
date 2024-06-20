/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Francisco
 */
public class InputCSVReaderTest {

    ArrayList<String> csvSeveralLines;
    ArrayList<String> line;
    String inputFile = "csvReaderTest.csv";
    String file = "";
    String l0, l1, s0, s1, s2, s3, s4;

    public InputCSVReaderTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

        l0 = "marengo;lodi;austerlitz;borodino;waterloo";
        l1 = "nile;palma;biscay;trafalgar;nelson";

        s0 = "marengo";
        s1 = "lodi";
        s2 = "austerlitz";
        s3 = "borodino";
        s4 = "waterloo";

        csvSeveralLines = new ArrayList<>();

        csvSeveralLines.add(l0);
        csvSeveralLines.add(l1);

        line = new ArrayList<>();
        line.add(s0);
        line.add(s1);
        line.add(s2);
        line.add(s3);
        line.add(s4);

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of inputCSVReader method, of class InputCSVReader.
     * @throws java.lang.Exception
     */
    @Test
    public void testInputCSVReader() throws Exception {
        System.out.println("inputCSVReader");
        List<String> expResult = csvSeveralLines;
        List<String> result = InputCSVReader.inputCSVReader(inputFile);
        assertEquals(expResult, result);
    }

    /**
     * Test of inputCSVReader method, of class InputCSVReader to give the exception.
     * @throws java.lang.Exception
     */
    @Test
    public void testInputCSVReaderEXC() throws Exception {
        System.out.println("inputCSVReaderEXC");
        csvSeveralLines.clear();
        List<String> expResult = csvSeveralLines;
        List<String> result = InputCSVReader.inputCSVReader(file);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of lineReader method, of class InputCSVReader.
     */
    @Test
    public void testLineReader() {
        System.out.println("lineReader");

        List<String> result = InputCSVReader.lineReader(l0);
        assertEquals(line, result);
    }

}
