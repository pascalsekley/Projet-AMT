/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.demoweek2.services;

import com.mycompany.demoweek2.model.Fruit;


public class FruitsManager {

    public Fruit getRandomFruit(){
        return new Fruit("Banana", "Yellow");
    }
}
