/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;

/**
 *
 * @author Tim
 */
public class Les extends Activiteit{
    
    public Les(String naam, Formule formule, LocalDate datum) {
        super(naam, formule, datum);
    }
    
}