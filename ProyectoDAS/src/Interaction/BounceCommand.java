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

    private GeometricFigure receiver;
    private GeometricFigure received;
    
    @Override
    public void execute() {
        receiver.bounce(received);
    }

    /**
     *
     * @param receiver
     */
    @Override
    public void setReceiver(GeometricFigure receiver) {
        this.receiver = receiver;
    }

    public void setReceived(GeometricFigure received) {
        this.received = received;
    }
    
    
    
    
    
    
}
