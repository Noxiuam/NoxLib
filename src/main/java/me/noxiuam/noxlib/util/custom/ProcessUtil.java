package me.noxiuam.noxlib.util.custom;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class ProcessUtil
{

    /*
     * Creates a new ProcessBuilder, gets the InputStream from it and then returns the output in the form of a StringBuilder.
     */
    @SneakyThrows
    public StringBuilder buildProcessAndGetInput(String[] args, int buffer)
    {
        ProcessBuilder pb = new ProcessBuilder(args);
        pb.directory(new File("C:\\Windows\\System32\\"));
        pb.redirectErrorStream(true);
        BufferedReader br = new BufferedReader(new InputStreamReader(pb.start().getInputStream()));
        StringBuilder sb = new StringBuilder();
        while (br.readLine() != null && sb.length() < buffer) {
            sb.append(br.readLine()).append("\n");
        }

        return sb;
    }

    /*
     * Creates a new ProcessBuilder and returns it, aya is gay.
     */
    public ProcessBuilder createNewProcessBuilder(String[] args)
    {
        return new ProcessBuilder(args);
    }

    /*
     * Runs a command from cmd.exe and then returns the output in the form of a StringBuilder.
     */
    @SneakyThrows
    public StringBuilder runTerminalCommandAndGetOutput(String[] args, int buffer)
    {
        ProcessBuilder pb = new ProcessBuilder(args);
        pb.directory(new File("C:\\Windows\\System32\\"));
        pb.redirectErrorStream(true);
        BufferedReader br = new BufferedReader(new InputStreamReader(pb.start().getInputStream()));
        StringBuilder sb = new StringBuilder();
        while (br.readLine() != null && sb.length() < buffer) {
            sb.append(br.readLine()).append("\n");
        }

        return sb;
    }

}
