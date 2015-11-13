/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author kris
 */
public class ID3tester {

    static String predictorAttributes[] = {"Ulkonäkö", "Vammamekanismi", "Kipu", "Varaaminen"};

    static String rawExamples[][] = {
        {"1", "Normaali", "Nyrjähdys", "Ei-Kova", "Ei", "N"},
        {"2", "Normaali", "Nyrjähdys", "Ei-Kova", "Kyllä", "N"},
        {"3", "Virheasento", "Nyrjähdys", "Ei-Kova", "Ei", "P"},
        {"4", "Turvonnut", "Putoaminen", "Ei-Kova", "Ei", "P"},
        {"5", "Turvonnut", "Muu", "Kova", "Ei", "P"},
        {"6", "Turvonnut", "Muu", "Kova", "Kyllä", "N"},
        {"7", "Virheasento", "Muu", "Kova", "Kyllä", "P"},
        {"8", "Normaali", "Putoaminen", "Ei-Kova", "Ei", "N"},
        {"9", "Normaali", "Muu", "Kova", "Ei", "P"},
        {"10", "Turvonnut", "Putoaminen", "Kova", "Ei", "P"},
        {"11", "Normaali", "Putoaminen", "Kova", "Kyllä", "P"},
        {"12", "Virheasento", "Putoaminen", "Ei-Kova", "Kyllä", "P"},
        {"13", "Virheasento", "Nyrjähdys", "Kova", "Ei", "P"},
        {"14", "Turvonnut", "Putoaminen", "Ei-Kova", "Kyllä", "N"},};

    public static void main(String[] args) {
        ExampleSet examples = createExampleSet(rawExamples, predictorAttributes);
        ArrayList<String> predictorAttributesAsList = new ArrayList<>(Arrays.asList(predictorAttributes));
        ID3 id3 = new ID3(examples, predictorAttributesAsList);
        id3.induce();
    }

    private static ExampleSet createExampleSet(String[][] rawExamples, String[] attributes) {
        ExampleSet examples = new ExampleSet();
        for (String[] rawExample : rawExamples) {
            Example example = new Example();
            example.addAttributeValuePair(new AttributeValuePair(attributes[0], rawExample[1]));
            example.addAttributeValuePair(new AttributeValuePair(attributes[1], rawExample[2]));
            example.addAttributeValuePair(new AttributeValuePair(attributes[2], rawExample[3]));
            example.addAttributeValuePair(new AttributeValuePair(attributes[3], rawExample[4]));
            example.setExampleClass(new _Class(rawExample[5]));
            examples.add(example);
        }
        return examples;
    }

}
