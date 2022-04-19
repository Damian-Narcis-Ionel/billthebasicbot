package com.asiy.commands;

import com.asiy.commands.JDA.ExecuteArgs;
import com.asiy.commands.JDA.ICommand;

import static com.asiy.utils.Constants.ERROR_USER_NOT_IN_VOICE_CHANNEL;

public class CommandShufflePlaylist implements ICommand {


    @Override
    public void execute(ExecuteArgs executeArgs) {

        if(!executeArgs.getMemberVoiceState().inAudioChannel()){
            executeArgs.getTextChannel().sendMessage(ERROR_USER_NOT_IN_VOICE_CHANNEL).queue();
            return;
        }

       // PlayerManager.getINSTANCE().
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String helpMessage() {
        return null;
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
