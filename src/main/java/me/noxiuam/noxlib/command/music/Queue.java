package me.noxiuam.noxlib.command.music;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.audio.GuildMusicManager;
import me.noxiuam.noxlib.audio.PlayerManager;
import me.noxiuam.noxlib.command.Command;
import me.noxiuam.noxlib.command.CommandContext;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Queue extends Command
{
    public Queue()
    {
        super("queue", "Lists the current music queue.", NoxLib.getInstance().getPrefix() + "queue");
    }

    @Override
    public void execute(CommandContext ctx)
    {
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(ctx.getGuild());
        final BlockingQueue<AudioTrack> queue = musicManager.scheduler.queue;

        if (queue.isEmpty())
        {
            ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Error getting the queue", "The queue is currently empty, search for a song to fill it up!", NoxLib.getInstance().getImageDatabase().getErrorImage()).build()).queue(m -> m.delete().queueAfter(5, TimeUnit.SECONDS));
            return;
        }

        final int trackCount = Math.min(queue.size(), 20);
        final List<AudioTrack> trackList = new ArrayList<>(queue);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <  trackCount; i++)
        {
            final AudioTrack track = trackList.get(i);
            final AudioTrackInfo info = track.getInfo();

            sb.append(i + 1 + ". **")
                    .append(info.title)
                    .append("**\nby: *" + info.author)
                    .append("*\n__Length: " + formatTime(track.getDuration()))
                    .append("__\n\n");
        }

        if (trackList.size() > trackCount)
        {
            sb.append("And `")
                    .append(trackList.size() - trackCount)
                    .append("` more...");
        }

        ctx.getChannel().sendMessage(NoxLib.getInstance().getMessageUtil().createEmbedWithThumbnail("Current Music Queue", sb.toString(), NoxLib.getInstance().getImageDatabase().getDefaultImage()).build()).queue();
    }

    private String formatTime(long timeInMillis)
    {
        final long hours = timeInMillis / TimeUnit.HOURS.toMillis(1);
        final long minutes = timeInMillis / TimeUnit.MINUTES.toMillis(1);
        final long seconds = timeInMillis % TimeUnit.MINUTES.toMillis(1) / TimeUnit.SECONDS.toMillis(1);

        return String.format("%02dh %02dm %02ds", hours, minutes, seconds);
    }
}
