package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import model.GameEngine;
import view.BoardPanel;
//import model.Login;

public class CustomiseXYbox extends JDialog {

	private JTextField txtFldSnake;
    private JTextField txtFldLadder;
    private JLabel lbSnake;
    private JLabel lbLadder;
    int getX = getSnakes(txtFldSnake);
    int getY = getLadders(txtFldLadder);
    private JButton btnEnter;
    private JButton btnCancel;
    private boolean succeeded;
 
    private GameEngine gameEngineImpl;
    private Board boardCustom;
    
    public CustomiseXYbox(Frame parent, GameEngine gameEngineImpl) {
    	
        super(parent, "Customise", true);//changed
        
        this.gameEngineImpl = gameEngineImpl;
        
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        lbSnake = new JLabel("POS X?: "); //changed from lbUsername
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
 
        lbLadder = new JLabel("POSY ?: "); //changed from lbPassword
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
         
            	int stopXY = 2;   //boardCustom.numSnakes;
            	
            	
            	for(int x=0; x!=stopXY; x++) {
            	
            	//dispose();	
            	gameEngineImpl.customizeSNLX(getX);
            	gameEngineImpl.customizeSNLY(getY);
            	/*JOptionPane.showMessageDialog(CustomiseXYbox.this,
                        loginError.getLocalizedMessage(),
                        "Login",
                        JOptionPane.ERROR_MESSAGE);
            	
            	"Get snake " + stopXY + " XY coordintes.",
                "Login",
                JOptionPane.INFORMATION_MESSAGE);
                // reset username and password*/
                txtFldSnake.setText("");
                txtFldLadder.setText("");
               
                succeeded = false;
                
            	}
            	succeeded = true;
            	dispose();	
            	
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
    
 
    public int getSnakes( ) {
    	String change2 = txtFldSnake.getText(); //hard coded for now to test
        int inter2 = Integer.parseUnsignedInt(change2);
    	return  inter2;
    }
 
    public int getLadders() {
    	String change = txtFldLadder.getText();
    	   int inter = Integer.parseUnsignedInt(change);		
        return inter;
    
    }
    
   
 
    
    public boolean isSucceeded() {
        return succeeded;
    }
	
}