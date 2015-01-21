package crypto;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;

public class Main extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Main() {

        initUI();
    }

    private void initUI() {

        Container pane = getContentPane();
        //GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(new FlowLayout());
        
        JButton check = new JButton("Check for Typos");
        final JTextField passphrase = new JTextField("Passphrase", 35);
        final JTextField rs = new JTextField("Account id (RS format)", 35);
        final JTextField info = new JTextField("INFO: waiting...");
        final String letters = "abcdefghijklmnopqrstuvwxyz ";

        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("checking");
                info.setText("INFO: Running...");
                String pp = passphrase.getText();
                System.out.println(rs.getText());
                long acc = Convert.parseAccountId(rs.getText());
                System.out.println(pp + " " + acc);
                String ol = oneletter(pp, acc, LETTERS);
                if(ol=="0") ol=oneletter(pp, acc, FULL_LETTERS);
                if(ol =="0") ol=twoletter(pp, acc, LETTERS);
                if(ol =="0") ol=twoletter(pp, acc, FULL_LETTERS);
                if(ol =="0") ;// fail;
                
            }
        });
        // NXT-RJU8-JSNR-H9J4-2KWKY
        pane.add(passphrase);
        pane.add(rs);
        pane.add(check);
        
        pane.setComponentOrientation(
                ComponentOrientation.LEFT_TO_RIGHT);

        setTitle("NXT Typo Finder");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public long toAccid(String phpr)
    {
    	return Convert.fullHashToId(Crypto.sha256().digest(Crypto.getPublicKey(phpr)));
    }
    
    public boolean compare(String phpr, long target)
    {
    	return toAccid(phpr) == target;
    }
    
    public String oneletter(String pp, long acc, String letters)
    {
    	for(int a=0; a<pp.length();a++)
    	{
    		for(int b=0; b<letters.length();b++)
    		{
    			String typo = pp.substring(0, a) + letters.charAt(b) + pp.substring(a+1);
    			System.out.println(typo);
    			if(compare(typo, acc))
    			{
    				// winning
    				System.out.println("WIN");
    				return typo;
    			}
    		}
    		
    		for(int b=0; b<letters.length();b++)
    		{
    			String typo = pp.substring(0, a) + letters.charAt(b) + pp.substring(a);
    			System.out.println(typo);
    			if(compare(typo, acc))
    			{
    				// winning
    				System.out.println("WIN");
    				return typo;
    			}
    		}
    		
    		
    			String typo = pp.substring(0, a) + pp.substring(a+1);
    			//alert(a + " " + b + " " + typo);
    			if(compare(typo, acc))
    			{
    				// winning
    				System.out.println("WIN");
    				return typo;
    			}
    		

    	}
    	return "0";
    }
    String LETTERS = "abcdefghijklmnopqrstuvwxyz ";
    String FULL_LETTERS = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*(){}[]?/+=\\|-_\"\',<.>;:";
    
    public String twoletter(String pp, long acc,String letters)
    {
    	
    	for(int a=0; a<pp.length();a++)
    	{
    		for(int b=0; b<letters.length();b++)
    		{
    			String typo = pp.substring(0, a) + letters.charAt(b) + pp.substring(a+1);
    			for(int c=0; c<pp.length();c++)
    	    	{
    	    		for(int d=0; d<letters.length();d++)
    	    		{
    	    			String typo2 = typo.substring(0, a) + letters.charAt(d) + typo.substring(a+1);
    	    			if(compare(typo2, acc))
    	    			{
    	    				// winning
    	    				System.out.println("WIN");
    	    				return typo2;
    	    			}
    	    		}
    	    		
    	    		for(int d=0; d<letters.length();d++)
    	    		{
    	    			String typo2 = typo.substring(0, a) + letters.charAt(d) + typo.substring(a);
    	    			if(compare(typo2, acc))
    	    			{
    	    				// winning
    	    				System.out.println("WIN");
    	    				return typo2;
    	    			}
    	    		}
    	    			String typo2 = typo.substring(0, a) + typo.substring(a+1);
    	    			if(compare(typo2, acc))
    	    			{
    	    				// winning
    	    				System.out.println("WIN");
    	    				return typo2;
    	    			}
    	    	}
    		}
    		
    		for(int b=0; b<letters.length();b++)
    		{
    			String typo = pp.substring(0, a) + letters.charAt(b) + pp.substring(a);
    			for(int c=0; c<pp.length();c++)
    	    	{
    	    		for(int d=0; d<letters.length();d++)
    	    		{
    	    			String typo2 = typo.substring(0, a) + letters.charAt(d) + typo.substring(a+1);
    	    			if(compare(typo2, acc))
    	    			{
    	    				// winning
    	    				System.out.println("WIN");
    	    				return typo2;
    	    			}
    	    		}
    	    		
    	    		for(int d=0; d<letters.length();d++)
    	    		{
    	    			String typo2 = typo.substring(0, a) + letters.charAt(d) + typo.substring(a);
    	    			if(compare(typo2, acc))
    	    			{
    	    				// winning
    	    				System.out.println("WIN");
    	    				return typo2;
    	    			}
    	    		}
    	    			String typo2 = typo.substring(0, a) + typo.substring(a+1);
    	    			if(compare(typo2, acc))
    	    			{
    	    				// winning
    	    				System.out.println("WIN");
    	    				return typo2;
    	    			}
    	    	}
    		}
    		
    		
    			String typo = pp.substring(0, a) + pp.substring(a+1);
    			for(int c=0; c<pp.length();c++)
    	    	{
    	    		for(int d=0; d<letters.length();d++)
    	    		{
    	    			String typo2 = typo.substring(0, a) + letters.charAt(d) + typo.substring(a+1);
    	    			if(compare(typo2, acc))
    	    			{
    	    				// winning
    	    				System.out.println("WIN");
    	    				return typo2;
    	    			}
    	    		}
    	    		
    	    		for(int d=0; d<letters.length();d++)
    	    		{
    	    			String typo2 = typo.substring(0, a) + letters.charAt(d) + typo.substring(a);
    	    			if(compare(typo2, acc))
    	    			{
    	    				// winning
    	    				System.out.println("WIN");
    	    				return typo2;
    	    			}
    	    		}
    	    			String typo2 = typo.substring(0, a) + typo.substring(a+1);
    	    			if(compare(typo2, acc))
    	    			{
    	    				// winning
    	    				System.out.println("WIN");
    	    				return typo2;
    	    			}
    	    	}
    		
    			System.out.println(a);
    	}
    	return "0";
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main ex = new Main();
                ex.setVisible(true);
                System.out.println(Convert.rsAccount(ex.toAccid("AAAAAAAAAAAAAAAAAAAA")));
            }
        });
    }
}
