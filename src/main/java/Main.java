import com.asiy.commands.*;
import com.asiy.commands.JDA.JDACommands;
import com.asiy.utils.Constants;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

import static com.asiy.utils.Constants.PREFIX;
import static com.asiy.utils.Constants.TOKEN;
import static net.dv8tion.jda.api.requests.GatewayIntent.*;

public class Main {
    public static JDA jda;

    public static final GatewayIntent[] INTENTS = {GUILD_MEMBERS,
            GUILD_BANS,
            GUILD_EMOJIS,
            GUILD_WEBHOOKS,
            GUILD_INVITES,
            GUILD_VOICE_STATES,
            GUILD_PRESENCES,
            GUILD_MESSAGES,
            GUILD_MESSAGE_REACTIONS,
            GUILD_MESSAGE_TYPING,
            DIRECT_MESSAGES,
            DIRECT_MESSAGE_REACTIONS,
            DIRECT_MESSAGE_TYPING};

    public static void main(String[] args) {

        JDACommands jdaCommands = new JDACommands(PREFIX);
        jdaCommands.registerCommand(new CommandPlay());
        jdaCommands.registerCommand(new CommandInfo());
        jdaCommands.registerCommand(new CommandLeave());
        jdaCommands.registerCommand(new CommandPause());
        jdaCommands.registerCommand(new CommandResume());
        jdaCommands.registerCommand(new CommandQueue());
        jdaCommands.registerCommand(new CommandNowPlaying());
        jdaCommands.registerCommand(new CommandClear());
        jdaCommands.registerCommand(new CommandShuffle());

        try{

            JDA jda = JDABuilder.create(TOKEN, Arrays.asList(INTENTS))
                    .enableCache(CacheFlag.VOICE_STATE)
                    .setActivity(Activity.playing(Constants.DEFAULT_STATUS_PLAYING))
                    .setStatus(OnlineStatus.ONLINE)
                    .addEventListeners(jdaCommands)
                    .build();

        }catch (LoginException e){
            e.printStackTrace();

        }


    }

}
