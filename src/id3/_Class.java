/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

import java.util.Objects;

/**
 *
 * @author kris
 * @param <C>
 */

public class _Class {
    private String c;

    public _Class(String c) {
        this.c = c;
    }
    
    public void set(String c) {
        this.c = c;
    }
    
    public String get() {
        return c; 
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final _Class other = (_Class) obj;
        if ((this.c == null) ? (other.c != null) : !this.c.equals(other.c)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.c);
        return hash;
    }

    
}
/*
public class _Class<C extends Comparable<C>> implements Comparable<_Class<C>> {
    private C c;

    public _Class(C c) {
        this.c = c;
    }
    
    public void set(C c) {
        this.c = c;
    }
    
    public C get() {
        return c; 
    }

    @Override
    public int compareTo(_Class<C> otherClass) {
    return 0;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if ()
        final Person other = (Person) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        return true;
    }
        
        
}
*/