package com.asiy.commands;

import com.asiy.commands.JDA.ExecuteArgs;
import com.asiy.commands.JDA.ICommand;
import com.asiy.music.GuildMusicManager;
import com.asiy.music.PlayerManager;
import com.asiy.utils.Constants;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import static com.asiy.utils.Constants.ERROR_BOT_NOT_IN_VOICE_CHANNEL;
import static com.asiy.utils.Constants.ERROR_USER_NOT_IN_VOICE_CHANNEL;

public class CommandClear implements ICommand {
    
    @Override
    public void execute(ExecuteArgs executeArgs) {
        final TextChannel channel = executeArgs.getTextChannel();
        final Member self = executeArgs.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inAudioChannel()) {
            channel.sendMessage(ERROR_BOT_NOT_IN_VOICE_CHANNEL).queue();
            return;
        }

        final Member member = executeArgs.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inAudioChannel()) {
            channel.sendMessage(ERROR_USER_NOT_IN_VOICE_CHANNEL).queue();
            return;
        }

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            channel.sendMessage("You need to be in the same voice channel as me for this to work").queue();
            return;
        }

        final GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(executeArgs.getGuild());

        musicManager.scheduler.queue.clear();

        channel.sendMessage("The queue has been cleared").queue();
    }

    @Override
    public String getName() {
        return Constants.CLEAR_COMMAND_NAME;
    }

    @Override
    public String helpMessage() {
        return Constants.CLEAR_COMMAND_DESC;
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
