/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author kris
 */
public class ID3 {

    private final ExampleSet examples;
    private final ArrayList<String> predictingAttributes;

    public ID3(ExampleSet examples, ArrayList<String> predictingAttributes) {
        this.examples = examples;
        this.predictingAttributes = predictingAttributes;
    }

    public void induce() {
        _induce(0);
    }

    private String levelString(int level) {
        String indent = "Tree level " + level + ":     ";
        for (int i = 0; i < level; i++) {
            indent += "    ";
        }
        return indent;
    }

    private void _induce(int level) {

        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.HALF_EVEN);
        
        String levelStr = levelString(level);

        if (this.examples.isEmpty()) {
            System.out.println(levelStr + "Example set is empty.");
            return;
        }
        Set _classes = this.examples.getSetOfClasses();
        if (_classes.size() == 1) {
            _Class c = (_Class) _classes.toArray()[0];
            System.out.println(levelStr + "All examples are in the same class, which is " + c.get() + ". This is a leaf.");
            return;
        }
        if (this.predictingAttributes.isEmpty()) {
            System.out.println(levelStr + "Ran out of predicting attributes");
            return;
        }
        System.out.println(levelStr + "A new node. Let's choose the best attribute for it. My options are:");
        Double bestIG = -1.0;
        String bestAttribute = "";
        for (int i = 0; i < this.predictingAttributes.size(); i++) {
            String attribute = this.predictingAttributes.get(i);
            Double InformationGain = this.examples.CalcInformationGain(attribute);
            System.out.println(" " + df.format(InformationGain) + " " + attribute.toUpperCase());
            if (InformationGain > bestIG) {
                bestIG = InformationGain;
                bestAttribute = attribute;
            }
        }
        System.out.println(levelStr + "I choose attribute " + bestAttribute.toUpperCase() + " for node because it has the highest IG " + df.format(bestIG) + ".");
        Set<AttributeValuePair> pairs = this.examples.getAttributeValuePairsWithAttribute(bestAttribute);
        Iterator iterator = pairs.iterator();
        while (iterator.hasNext()) {
            AttributeValuePair pair = (AttributeValuePair) iterator.next();
            System.out.println(levelStr + "  New edge " + pair.getValue().toUpperCase());
            ExampleSet newSet = this.examples.getExamplesWithAttributeValuePair(pair);
            ArrayList<String> newPredictingAttributes = new ArrayList<>(this.predictingAttributes);
            newPredictingAttributes.remove(bestAttribute);
            ID3 id3 = new ID3(newSet, newPredictingAttributes);
            id3._induce(level + 1);
        }
    }

}
