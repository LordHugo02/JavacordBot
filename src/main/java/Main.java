import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandInteraction;

import java.awt.*;
import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

public class Main
{

    static PrivateToken t = new PrivateToken();
    static String token = t.getToken();
    static char prefix = '*';


    public static void main(String[] args)
    {

        System.out.println("Initialisation");
        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
        System.out.println("Initialisation done");


        api.addMessageCreateListener(event ->
        {
            String [] tab = event.getMessageContent().split(" ");
            if (tab[0].charAt(0) == prefix)
            {
                tab[0] = tab[0].substring(1);
                System.out.println(tab[0]);

                switch (tab[0])
                {
                    case "ping":
                        event.getChannel().sendMessage("pong!");
                        break;

                    case "dice":
                        int lancers = -1;
                        BigInteger t;
                        System.out.println(tab.length);
                        if (tab.length == 2)
                        {
                            lancers = Integer.parseInt(tab[1]);
                            if (lancers <= 0)
                                event.getChannel().sendMessage("Veuillez entrer un nombre entre 1 et " + Integer.MAX_VALUE);
                        }
                        else
                            lancers = 6;
                        int random = (int) (Math.random() * lancers) + 1;

                        event.getChannel().sendMessage("Vous obtenez un "+ random + " ! \n Sur un dé à  : " + lancers + " faces");
                        break;

                    default:
                        event.getChannel().sendMessage("désolé, la commande " + tab[0] + " n'est pas attribuée");
                        break;


                }
            }
        });
    }


}

