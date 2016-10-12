import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Interface extends JFrame {

	
	Bot bot;
	
	JButton btJoin;
	JButton btTalk;
	JButton btDo;
	JComboBox<String> cbJoin = new JComboBox<String>();
	JTextField tf = new JTextField("m2i");
	
	public Interface(String pass){
		this.bot = new Bot(pass);
		this.setSize(320, 200);
		this.setTitle("Robotto");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		initControles();
	}
	
	public void initControles(){
		JPanel zoneClient = (JPanel) this.getContentPane();	
		
		JPanel panel = new JPanel();	
		zoneClient.add(panel);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		JPanel panButtons = new JPanel();
		panButtons.setLayout(new BoxLayout(panButtons, BoxLayout.Y_AXIS));
		panButtons.setBorder(BorderFactory.createLineBorder(Color.RED));
		panel.add(panButtons);
		
		
		cbJoin.addItem("Join");
		cbJoin.addItem("Part");
		Line lineJoin = new Line(cbJoin, "Go");
		btJoin = lineJoin.getBt();
		panButtons.add(lineJoin);
		
		Line lineTalk = new Line("Talk", "Ok");
		lineTalk.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		btTalk = lineTalk.getBt();
		panButtons.add(lineTalk);
		
		Line lineDo = new Line("Do", "Ok");
		btDo = lineDo.getBt();
		panButtons.add(lineDo);
		
		panButtons.add(tf);
		
		
		btJoin.addActionListener(new appActionListener());
		btTalk.addActionListener(new appActionListener());
		btDo.addActionListener(new appActionListener());
	}
	
	class appActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			action(e);
			connect(e);
			talk(e);
			
		}
		
	}

	public void action(ActionEvent e){
		if(e.getSource() == btDo){
			String action = tf.getText();
			bot.sendAction(bot.channel, action);
			tf.setText("");
			
		}
	}
	
	public void talk(ActionEvent e){
		if(e.getSource() == btTalk){
			String say = tf.getText();
			bot.sendMessage(bot.channel, say);
			tf.setText("");
			
		}
	}
	
	public void connect(ActionEvent e){
		if(e.getSource() == btJoin){
			
			String channel = tf.getText();
			
			if(channel.indexOf("#") > 0){
				
			}
			else{
				channel = "#" + channel;
			}
			
			if(cbJoin.getSelectedItem().toString().toLowerCase().equals("join")){
				bot.joinChannel(channel);
			}
			else{
				bot.partChannel(channel);
			}
		}	
	}
	
}
