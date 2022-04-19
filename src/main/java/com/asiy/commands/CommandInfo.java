package com.asiy.commands;


import com.asiy.commands.JDA.ExecuteArgs;
import com.asiy.commands.JDA.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

import static com.asiy.utils.Constants.*;

public class CommandInfo implements ICommand {

    @Override
    public void execute(ExecuteArgs executeArgs) {

        executeArgs.getMessage().getChannel().sendTyping();

        EmbedBuilder info = new EmbedBuilder();
        info.setTitle("Information");
        info.setFooter("Creator: Asiy");
        info.setDescription(INFO_MESSAGE);
        info.addField("Help", "For help type "+PREFIX+HELP_COMMAND_NAME,false);
        info.addField("Also,", "Thanks for using "+BOT_NAME,false);
        info.setColor(Color.GREEN);


        executeArgs.getMessage().getChannel().sendTyping().queue();
        executeArgs.getMessage().getChannel().sendMessageEmbeds(info.build()).queue();

        info.clear();

    }



    @Override
    public String getName() {
        return INFO_COMMAND_NAME;
    }

    @Override
    public String helpMessage() {
        return INFO_COMMAND_DESC;
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
