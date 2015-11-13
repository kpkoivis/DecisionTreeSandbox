/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author kris
 */
public class Example {

    private Set attributeValuePairs = new HashSet();
    private _Class exampleClass;

    public void setExampleClass(_Class exampleClass) {
        this.exampleClass = exampleClass;
    }

    public _Class getExampleClass() {
        return this.exampleClass;
    }

    public void addAttributeValuePair(AttributeValuePair pair) {
        this.attributeValuePairs.add(pair);
    }

    public Set getAttributeValuePairs() {
        return this.attributeValuePairs;
    }

    public boolean hasAttributeValuePair(AttributeValuePair pair) {
        Iterator iterator = this.attributeValuePairs.iterator();
        while (iterator.hasNext()) {
            AttributeValuePair p = (AttributeValuePair)iterator.next();
            if (p.equals(pair)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> getAttributes() {
        Set<String> attributeSet = new HashSet<>();
        Iterator iterator = this.attributeValuePairs.iterator();
        while (iterator.hasNext()) {
            AttributeValuePair pair = (AttributeValuePair)iterator.next();
            attributeSet.add(pair.getAttribute());
        }
        return attributeSet;
    }
    
    
}
