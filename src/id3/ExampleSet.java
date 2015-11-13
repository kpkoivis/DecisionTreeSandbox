/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

import static id3.Logarithm.log2;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author kris
 * @param <E>
 */
public class ExampleSet<E> extends HashSet<E> {

    Set<_Class> setOfClasses = new HashSet<>();

    @Override
    public boolean add(E e) {
        Example example = (Example) e;
        _Class _class = example.getExampleClass();
        this.setOfClasses.add(_class);
        return super.add(e);
    }

    public Set<_Class> getSetOfClasses() {
        return this.setOfClasses;
    }

    public ExampleSet getExamplesWithClass(_Class _class) {
        ExampleSet examplesWithClass = new ExampleSet();
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Example e = (Example)iterator.next();
            if (e.getExampleClass().equals(_class)) {
                examplesWithClass.add(e);
            }
        }
        return examplesWithClass;
    }

    public ExampleSet getExamplesWithAttributeValuePair(AttributeValuePair pair) {
        ExampleSet examplesWithPair = new ExampleSet();
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Example e = (Example)iterator.next();
            if (e.hasAttributeValuePair(pair)) {
                examplesWithPair.add(e);
            }
        }
        return examplesWithPair;
    }
    
    
    
    
    public Double GetProportionWithClass(_Class _class) {
        Double result = getExamplesWithClass(_class).size()*1.0 / this.size()*1.0;
        //System.out.println(result);
        //System.out.println(getExamplesWithClass(_class).size()*1.0);
        return result;
    }
    
    public Double CalcEntropy() {
        Double entropy = 0.0;
        Set<_Class> classes = this.getSetOfClasses();
        for (_Class c : classes) {
            Double proportion = this.GetProportionWithClass(c);
            Double _entropy = proportion * log2(proportion);
            entropy += _entropy;
        }
        entropy = entropy * -1.0;
        return entropy;
    }

    public Set getAttributeValuePairsWithAttribute(String attribute) {
        Set<AttributeValuePair> pairs = new HashSet<>();
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Example e = (Example)iterator.next();
            Set<AttributeValuePair> examplePairs = e.getAttributeValuePairs();
            Iterator pairIterator = examplePairs.iterator();
            while (pairIterator.hasNext()) {
                AttributeValuePair pair = (AttributeValuePair)pairIterator.next();
                if (pair.getAttribute().equals(attribute)) {
                    pairs.add(pair);
                }
            }
        }
        return pairs;
    }
    

    
    public Double CalcInformationGain(String attribute) {
        Set pairs = getAttributeValuePairsWithAttribute(attribute);
        Iterator iterator = pairs.iterator();
        Double weightedEntropy = 0.0;
        while (iterator.hasNext()) {
            AttributeValuePair pair = (AttributeValuePair)iterator.next();
            ExampleSet pairSet = getExamplesWithAttributeValuePair(pair);
            Double entropy = pairSet.CalcEntropy();
            Double proportion = (1.0 * pairSet.size()) / (1.0 * this.size());
            weightedEntropy += proportion * entropy;
        }
        return this.CalcEntropy() - weightedEntropy;
    }
    
    public Set<String> getAttributes() {
        Set<String> attributeSet = new HashSet<>();
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            Example e = (Example)iterator.next();
            attributeSet.addAll(e.getAttributes());
        }
        return attributeSet;
    }
    
    
}
