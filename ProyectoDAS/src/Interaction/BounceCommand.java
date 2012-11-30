/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interaction;

import GeometricFiguresManagement.GeometricFigure;

/**
 *
 * @author Andrea
 */
public class BounceCommand implements Command{

    private GeometricFigure received;
    
    @Override
    public void execute(GeometricFigure receiver) {
        receiver.bounce(received);
    }

    public void setReceived(GeometricFigure received) {
        this.received = received;
    }
    
    
    
    
    
    
}
