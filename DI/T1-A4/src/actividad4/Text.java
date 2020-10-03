/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividad4;

import java.awt.Point;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author SERGI
 */
public class Text {
    private Boolean MouseClicked = false;
    private Boolean MouseEntered = false;
    private Boolean MouseExited = false;
    private Boolean MousePressed = false;
    private Boolean MouseReleased = true;
    private Double MouseWheelMoved = 0.0;
    private Point MouseDragged = null;
    private Point MouseMoved = null;
    private Boolean FocusGained = false;
    private char KeyTyped = ' ';
    
    public Text() {}

    public Boolean getMouseClicked() {
        return MouseClicked;
    }

    public void setMouseClicked(Boolean MouseClicked) {
        this.MouseClicked = MouseClicked;
    }

    public Boolean getMouseEntered() {
        return MouseEntered;
    }

    public void setMouseEntered(Boolean MouseEntered) {
        this.MouseEntered = MouseEntered;
    }

    public Boolean getMouseExited() {
        return MouseExited;
    }

    public void setMouseExited(Boolean MouseExited) {
        this.MouseExited = MouseExited;
    }

    public Boolean getMousePressed() {
        return MousePressed;
    }

    public void setMousePressed(Boolean MousePressed) {
        this.MousePressed = MousePressed;
    }

    public Boolean getMouseReleased() {
        return MouseReleased;
    }

    public void setMouseReleased(Boolean MouseReleased) {
        this.MouseReleased = MouseReleased;
    }
    public Double getMouseWheelMoved() {
        return MouseWheelMoved;
    }

    public void setMouseWheelMoved(Double MouseWheelMoved) {
        this.MouseWheelMoved += MouseWheelMoved;
    }

    public String getMouseDragged() {
        if(MouseDragged != null) return "X: "+MouseDragged.x+" Y: "+MouseDragged.y;
        return "";
    }

    public void setMouseDragged(Point MouseDragged) {
        this.MouseDragged = MouseDragged;
    }

    public String getMouseMoved() {
         if(MouseMoved != null) return "X: "+MouseMoved.x+" Y: "+MouseMoved.y;
         return "";
    }

    public void setMouseMoved(Point MouseMoved) {
        this.MouseMoved = MouseMoved;
    }

    public Boolean getFocusGained() {
        return FocusGained;
    }

    public void setFocusGained(Boolean FocusGained) {
        this.FocusGained = FocusGained;
    }

    public char getKeyTyped() {
        return KeyTyped;
    }

    public void setKeyTyped(char KeyTyped) {
        this.KeyTyped = KeyTyped;
    }   
    
    @Override
    public String toString() {
        String toReturn="";
        toReturn+="\nMouseEntered event: \t"+MouseEntered;
        toReturn+="\nMouseExited event: \t"+MouseExited;
        toReturn+="\nMousePressed event: \t"+MousePressed;
        toReturn+="\nMouseClicked event: \t"+MouseClicked;
        toReturn+="\nMouseReleased event: \t"+MouseReleased;
        toReturn+="\nMouseWheelMoved event: \t"+BigDecimal.valueOf(MouseWheelMoved).setScale(2,RoundingMode.HALF_UP)+" (Amount)";
        toReturn+="\nMouseDragged event: \t"+getMouseDragged();
        toReturn+="\nMouseMoved event: \t"+getMouseMoved();
        toReturn+="\nFocusGained event: \t"+FocusGained;
        toReturn+="\nKeyTyped event: \t"+KeyTyped;
        return toReturn;
    }
    
    
    
}
