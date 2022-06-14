package com.asiy.commands;

import com.asiy.commands.JDA.ExecuteArgs;
import com.asiy.commands.JDA.ICommand;
import com.asiy.utils.Constants;

import java.util.concurrent.TimeUnit;

import static com.asiy.utils.Constants.BAC_COMMAND_DESC;
import static com.asiy.utils.Constants.BAC_COMMAND_NAME;

public class CommandBac implements ICommand {


    @Override
    public void execute(ExecuteArgs executeArgs) {

        executeArgs.getMessage().getChannel().sendTyping().queue();

        long millisToBac = 1655704800000L; // 20.06.2022
        long currentTime = System.currentTimeMillis();

        long diff = millisToBac-currentTime;

        String timeLeft =  String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(diff),
                TimeUnit.MILLISECONDS.toMinutes(diff) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diff)),
                TimeUnit.MILLISECONDS.toSeconds(diff) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff)));

        String gaba = "<@224890052084367360>";
        executeArgs.getMessage().getChannel().sendMessage(gaba + " mai sunt "+timeLeft+" pana la BAC. Bft coae.").queue();


    }

    @Override
    public String getName() {
        return BAC_COMMAND_NAME;
    }

    @Override
    public String helpMessage() {
        return BAC_COMMAND_DESC;
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
