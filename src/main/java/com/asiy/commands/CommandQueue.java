package com.asiy.commands;

import com.asiy.commands.JDA.ExecuteArgs;
import com.asiy.commands.JDA.ICommand;
import com.asiy.music.GuildMusicManager;
import com.asiy.music.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static com.asiy.utils.Constants.*;
import static com.asiy.utils.Constants.BOT_NAME;

public class CommandQueue implements ICommand {

    @Override
    public void execute(ExecuteArgs executeArgs) {

        final TextChannel channel = executeArgs.getTextChannel();
        final GuildMusicManager musicManager = PlayerManager.getINSTANCE().getMusicManager(executeArgs.getGuild());
        final BlockingQueue<AudioTrack> queue = musicManager.scheduler.queue;

        if (queue.isEmpty()) {
            channel.sendMessage("The queue is currently empty").queue();
            return;
        }

        final int trackCount = Math.min(queue.size(), 20);
        final List<AudioTrack> trackList = new ArrayList<>(queue);
        final AudioPlayer audioPlayer = musicManager.audioPlayer;
        final AudioTrack currentTrack = audioPlayer.getPlayingTrack();
        final AudioTrackInfo currentTrackInfo = currentTrack.getInfo();

        EmbedBuilder currentEmbed = new EmbedBuilder();
        currentEmbed.setFooter("Creator: Asiy");
        currentEmbed.setTitle("Currently playing");
        currentEmbed.setColor(Color.YELLOW);

        String toSend = String.format("Now playing `%s` by `%s` (Link: <%s>)\n",currentTrackInfo.title, currentTrackInfo.author, currentTrackInfo.uri);

        currentEmbed.addField(toSend,
                currentTrackInfo.title +" "+ currentTrackInfo.author+" "+ currentTrackInfo.uri, false);

        executeArgs.getMessage().getChannel().sendTyping().queue();
        channel.sendMessageEmbeds(currentEmbed.build()).queue();

        currentEmbed.clear();

        final MessageAction messageAction = channel.sendMessage(" ");

        EmbedBuilder embed = new EmbedBuilder();
        embed.setFooter("Creator: Asiy");
        embed.setTitle("Current queue");
        embed.setColor(Color.ORANGE);

        for (int i = 0; i <  trackCount; i++) {
            final AudioTrack track = trackList.get(i);
            final AudioTrackInfo info = track.getInfo();

            embed.addField(" ", "**"+String.valueOf(i+1)+"** "+info.title +" **by** "+info.author+" **#** "+" Duration: "+formatTime(track.getDuration()) ,false);
        }

        if (trackList.size() > trackCount) {
            messageAction.append("**And `")
                    .append(String.valueOf(trackList.size() - trackCount))
                    .append("` more...**");
        }

        executeArgs.getMessage().getChannel().sendTyping().queue();
        executeArgs.getMessage().getChannel().sendMessageEmbeds(embed.build()).queue();
        embed.clear();
        messageAction.queue();

    }

    @Override
    public String getName() {
        return QUEUE_COMMAND_NAME;
    }

    @Override
    public String helpMessage() {
        return QUEUE_COMMAND_DESC;
    }

    @Override
    public boolean needOwner() {
        return false;
    }

    private String formatTime(long timeInMillis) {
        final long hours = timeInMillis / TimeUnit.HOURS.toMillis(1);
        final long minutes = timeInMillis / TimeUnit.MINUTES.toMillis(1);
        final long seconds = timeInMillis % TimeUnit.MINUTES.toMillis(1) / TimeUnit.SECONDS.toMillis(1);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
