/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interactiveworld;

import mygame.util.Account;
import mygame.util.Curriculum;

/**
 *
 * @author natnael
 */
public class World {
    
    private static Account account;
    private static Curriculum curriculum;
    
    public World(Account account,Curriculum curriculum){
        this.account = account;
    }
    
    public void start(){
        System.out.println("-------game world started!--------");
        System.out.println("----------------------------------");
        System.out.println("name: "+account.getName()+" age: "+account.getAge());
        System.out.println("----------------------------------");
    }
    
}
