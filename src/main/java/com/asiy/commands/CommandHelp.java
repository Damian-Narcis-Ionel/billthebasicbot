package com.asiy.commands;

import com.asiy.commands.JDA.ExecuteArgs;
import com.asiy.commands.JDA.ICommand;

import java.util.ArrayList;

import static com.asiy.utils.Constants.HELP_COMMAND_DESC;
import static com.asiy.utils.Constants.HELP_COMMAND_NAME;

public class CommandHelp implements ICommand {

    @Override
    public void execute(ExecuteArgs var1) {

    }

    @Override
    public String getName() {
        return HELP_COMMAND_NAME;
    }

    @Override
    public String helpMessage() {
        return HELP_COMMAND_DESC;
    }

    @Override
    public boolean needOwner() {
        return false;
    }

    private void listCommands(ArrayList<ICommand> commands){

//        EmbedBuilder info = new EmbedBuilder();
//        info.setTitle("Information");
//        info.setFooter("Creator: Asiy");
//        info.setTitle("List of commands");
//
//        for(ICommand c : commands){
//            info.addField(c.getName(),,false)
//        }

    }

}
