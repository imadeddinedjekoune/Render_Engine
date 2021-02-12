/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Noise;

/**
 *
 * @author Imad
 */
public class Test {
    public static void main (String args[])
    {
        OpenSimplexNoise noise = new OpenSimplexNoise();
        System.out.println(noise.eval(1, 0));
    }
}
