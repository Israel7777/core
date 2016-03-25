/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.util;

import com.jme3.scene.Node;
import java.io.Serializable;

/**
 *
 * @author natnael
 */
public class Account implements Serializable{
    private String name;
    private int age;
    private Node blockTerain ;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the blockTerain
     */
    public Node getBlockTerain() {
        return blockTerain;
    }

    /**
     * @param blockTerain the blockTerain to set
     */
    public void setBlockTerain(Node blockTerain) {
        this.blockTerain = blockTerain;
    }
    
    
}
