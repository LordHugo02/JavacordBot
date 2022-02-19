import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandInteraction;

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
                event.getChannel().sendMessage(tab[0]);
            }
        });
    }


}

