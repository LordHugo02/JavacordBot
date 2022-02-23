import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;
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
                        int lancers = 0;
                        System.out.println(tab.length);
                        if (tab.length == 2)
                        {
                            if (tab[1].length() > 9)
                                event.getChannel().sendMessage("Veuillez entrer un nombre entre 1 et (10^10)-1");
                            else
                                lancers = Integer.parseInt(tab[1]);

                        }
                        else
                            lancers = 6;
                        int random = (int) (Math.random() * lancers) + 1;

                        if (lancers !=0)
                            event.getChannel().sendMessage("Vous obtenez un "+ random + " ! \n Sur un dé à  : " + lancers + " faces");
                        break;


                    case "img":


                        EmbedBuilder embed = null;
                        try {
                        embed = new EmbedBuilder()
                                .setTitle("Title")
                                .setDescription("Description")
                                .setAuthor(event.getMessageAuthor().getDisplayName(), null,  event.getMessageAuthor().getAvatar())
                                .addField("A field", "Some text inside the field")
                                .addInlineField("An inline field", "More text")
                                .addInlineField("Another inline field", "Even more text")
                                .setColor(Color.BLUE)
                                .setFooter("Footer", "https://cdn.discordapp.com/embed/avatars/1.png")
                                .setImage(String.valueOf(new URL("https://picsum.photos/200/300")));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                        // Send the embed
                        event.getChannel().sendMessage(embed);

                            break;

                    default:
                        event.getChannel().sendMessage("désolé, la commande " + tab[0] + " n'est pas attribuée");
                        break;


                }
            }
        });
    }


}

