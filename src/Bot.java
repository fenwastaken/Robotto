import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;


public class Bot extends PircBot{

	String name = "robotto";
	String irc = "irc.esper.net";
	String port = "6667";
	String channel = "#m2i";
	String pass = "";
	int num = -1;

	public Bot(String pass){
		try {
			this.pass = pass;
			this.setAutoNickChange(true);
			this.setVerbose(true);
			this.setName("Robotto");
			this.connect(irc);
			//this.joinChannel(channel);
			//setPass();
			//opSelf();
		} catch (IOException | IrcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connexion failed");
		}
	}

	@Override
	protected void onJoin(String channel, String sender, String login,
			String hostname) {
		// TODO Auto-generated method stub
		super.onJoin(channel, sender, login, hostname);

		if(!sender.toLowerCase().equals(name.toLowerCase())){
			this.sendMessage(channel, "Welcome " + sender + "!");
		}

	}

	@Override
	protected void onMessage(String channel, String sender, String login,
			String hostname, String message) {
		// TODO Auto-generated method stub
		super.onMessage(channel, sender, login, hostname, message);
		System.out.println("message: " + sender + ", " + message);
		if(message.toLowerCase().contains("robotto")){
			sendMessage(channel, "Beep boop, I'm a bot!");
		}

		if(message.equals("!number")){
			newNumber();
		}

		if(message.startsWith("!guess ")){
			if(num < 0){
				sendMessage(channel, "Use !number first, " + sender + ".");
			}
			else{
				String number = message.substring(7);
				int guess = Integer.parseInt(number);
				if(guess == num){
					sendMessage(channel, sender + " found the number, it was " + num + ".");
					num = -1;
				}
				else if(guess > num){
					sendMessage(channel, "It's less than that, " + sender + ".");
				}
				else{
					sendMessage(channel, "It's more than that, " + sender + ".");
				}
			}
		}

		if(message.startsWith("!yt ")){
			String search = message.substring(message.indexOf(" "));
			youtube(search);
		}

	}

	@Override
	protected void onPrivateMessage(String sender, String login,
			String hostname, String message) {
		// TODO Auto-generated method stub
		super.onPrivateMessage(sender, login, hostname, message);
		/**/
		if(message.startsWith("!join ")){
			String channel = message.substring(message.indexOf(" ") + 1);
			this.joinChannel(channel);
		}

		if(message.startsWith("!part ")){
			String channel = message.substring(message.indexOf(" ") + 1);
			this.partChannel(channel);
		}

		if(message.startsWith("!say ")){
			String mess = message.substring(message.indexOf(" ") + 1);
			this.sendMessage(channel, mess);
		}

		if(message.equals("!op")){
			opSelf();
		}
		/**/
	}

	/**
	 * Méthode qui choisit un nombre au hasard
	 */
	public void newNumber(){
		if(num < 0){
			num = (int)((Math.random() * 100) + 1);
			this.sendMessage(channel, "A new number was chosen");
		}
		else{
			this.sendMessage(channel, "There's already a number to be guessed");
		}
	}

	public void youtube(String search){
		URL url;
		try {
			// get URL content

			search = search.replace(" ", "+");

			String a="https://www.youtube.com/results?search_query=" + search;
			url = new URL(a);
			URLConnection conn = url.openConnection();

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));

			String inputLine;
			String html = null;
			while ((inputLine = br.readLine()) != null) {
				html = html + "\n" + inputLine;
			}
			br.close();

			String vid;

			// System.out.println(html);

			int start = html.indexOf("watch?");
			int end = html.indexOf("\"", start);

			vid = html.substring(start, end);

			System.out.println("https://www.youtube.com/"+vid);
			this.sendMessage(channel, "https://www.youtube.com/" + vid);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void opSelf(){
		sendMessage("nickserv", "identify " + pass);
	}

}
