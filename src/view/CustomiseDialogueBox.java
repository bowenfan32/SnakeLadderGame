package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import controller.CustomiseXYboxActionListener;
import model.GameEngine;
import view.BoardPanel;

//import model.Login;

public class CustomiseDialogueBox extends JDialog {

	private JTextField txtFldSnake;
    private JTextField txtFldLadder;
    private JLabel lbSnake;
    private JLabel lbLadder;
   // private Jlabel lbX;
   // private Jlabel lbY;
    private JButton btnEnter;
    private JButton btnCancel;
    private boolean succeeded;
    
 
    private GameEngine gameEngineImpl;
    private GameAppFrame gameAppFrame;
    
    CustomiseXYboxActionListener cgAct = new CustomiseXYboxActionListener(this.gameEngineImpl, this.gameAppFrame);
   
    public CustomiseDialogueBox(Frame parent, GameEngine gameEngineImpl) {
    	
        super(parent, "Customise", true);//changed
        
        this.gameEngineImpl = gameEngineImpl;
        
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        lbSnake = new JLabel("How many Snakes?: "); //changed from lbUsername
      //  lbX = new JLabel("How many Snakes?: "); //changed from lbUsername
       // lbY = new JLabel("How many Ladders?: "); //changed from lbUsername
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbSnake, cs);
 
        txtFldSnake = new JTextField(20);
     
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(txtFldSnake, cs);
 
        lbLadder = new JLabel("How many Ladders?: "); //changed from lbPassword
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbLadder, cs);
        
       
        
 
        txtFldLadder = new JTextField(20); //????
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(txtFldLadder, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
 
     
        btnEnter = new JButton("Enter");
 
        btnEnter.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
            	//
            	   int numSn = getSnakes();
                   int numLa = getLadders();
            	gameEngineImpl.customizeSNNum(numSn); //was getSnakes(); hard coded for now
            	gameEngineImpl.customizeLDNum(numLa);
            
                //succeeded = true;
                dispose();
                cgAct.actionPerformed(e);
                
                /* for 0 -> numberOfSNakes {
                 * 		show CustomiseXYbox "enter the coordinates of your snake"
                 * 			customiseXYbox.getHead
                 * 			customiseXYbox.getTail 
                 * 			add(new Snake(get)
                 * }
                 */
                
               
            
            }            	
              
    	
            	}
        

        );
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnEnter);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
 
    public int getSnakes() {
    	
    	String change2 = txtFldSnake.getText(); //hard coded for now to test
        int inter2 = Integer.parseUnsignedInt(change2);
    	return  inter2;
    }
 
    public int getLadders() {
    	//String change = txtFldLadder.getText();
    	   int inter = 2;//Integer.parseUnsignedInt(change);		
        return inter;
    
    }
    
   
 
    
    public boolean isSucceeded() {
        return succeeded;
    }
	
}
