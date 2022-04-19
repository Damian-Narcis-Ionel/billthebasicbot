package com.asiy.commands;

import com.asiy.commands.JDA.ExecuteArgs;
import com.asiy.commands.JDA.ICommand;
import com.asiy.music.PlayerManager;
import com.asiy.utils.Constants;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.net.URI;
import java.net.URISyntaxException;

import static com.asiy.utils.Constants.*;

public class CommandPlay implements ICommand {

    @Override
    public void execute(ExecuteArgs executeArgs) {

        if(!executeArgs.getMemberVoiceState().inAudioChannel()){
            executeArgs.getTextChannel().sendMessage(ERROR_USER_NOT_IN_VOICE_CHANNEL).queue();
            return;
        }

        if(!executeArgs.getSelfVoiceState().inAudioChannel()){
            final AudioManager audioManager = executeArgs.getGuild().getAudioManager();
            final VoiceChannel memberChannel = (VoiceChannel) executeArgs.getMemberVoiceState().getChannel();

            audioManager.openAudioConnection(memberChannel);

        }

        String link = String.join(" ", executeArgs.getArgs());
        link = link.replace(Constants.PREFIX+getName()+" ","");

        if(!isUrl(link)){
            link = "ytsearch: "+link + " official music video";
        }

        PlayerManager.getINSTANCE().loadAndPlay(executeArgs.getTextChannel(),link);

    }

    public boolean isUrl(String url){
        try{
            new URI(url);
            return true;
        }catch (URISyntaxException e){
            return false;
        }
    }

    @Override
    public String getName() {
        return PLAY_COMMAND_NAME;
    }

    @Override
    public String helpMessage() {
        return PLAY_COMMAND_DESC;
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
