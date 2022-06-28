# NoxLib

A Java library designed to aid in creating Discord bots.

Full documentation is not complete yet, will likely transfer to a Wiki and a complete recode of the games framework is needed, it looks horrible.

## Features

This lib includes almost everything you could possibly need for a bot.

- Custom Game Support
- Custom Command Support
- YouTube Music Support

## Setup

To use this in creating a bot, there are a few things you need to understand first.

1. There are tiers for bots, this is so it can have either everything at once, or limited features for those who only
   want a few things.
2. This library was made to be used only on one server, not multiple at a time.

**Setting up the actual bot is very straight forward.**

If you're wanting to create a bot with just simple logging using NoxLib, here is an example of the main class:

```java
import lombok.*;
import net.dv8tion.jda.api.*;

import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.config.Config;
import me.noxiuam.noxlib.services.Tier;
import me.noxiuam.noxlib.event.*;

public class Main {

   public JDA jda;

   @SneakyThrows
   public Main() {
      this.jda = JDABuilder.createDefault("YOUR_TOKEN_HERE")
              .setStatus(OnlineStatus.ONLINE)
              // Only include these listeners if you need them.
              .addEventListeners(new Listener(), new LogListener())
              .build().awaitReady();

      // Initialize NoxLib
      new NoxLib();
      // Set the NoxLib's JDA, this is accessed for some features
      NoxLib.getInstance().setBotJda(this.jda);
      // Set the configuration for NoxLib, this is mandatory
      NoxLib.getInstance().configuration = new Config(Tier.PLATINUM, "YOUR_PREFIX", "YOUR_GUILD_ID", "YOUR_LOG_CHANNEL_ID");
   }

   public static void main(String[] args) {
      new Main();
   }
}
```

## Listeners

Here is a full list of all the event listeners within NoxLib that you can register in the main class:

- AutoReactionListener (Used for auto reactions)
- GameListener (Used for game related things)
- Listener (The default listener that every bot should be using when using NoxLib)
- LogListener (Used for logging)
- TicketListener (Used for tickets)
- VerificationListener (Used for verifying members)

## Creating Custom Commands

With this Command API, all you're doing is making a new class for each of them, extending the `GenericCommand` object.

Here is an example of what the command should look like:

```java
import lombok.SneakyThrows;
import me.noxiuam.noxlib.NoxLib;
import me.noxiuam.noxlib.command.GenericCommand;
import me.noxiuam.noxlib.command.util.CommandContext;
import me.noxiuam.noxlib.services.Tier;

public class TestCommand extends GenericCommand {

   public TestCommand() {
      super("hello", "An example command.", NoxLib.getInstance().getPrefix() + this.getName(), Tier.BRONZE);
   }

   @Override
   @SneakyThrows
   public void execute(CommandContext ctx) {
      ctx.getChannel().sendMessage("Hello World!").queue();
   }
}
```

To register your custom command, in the main class, simply use:
```java
NoxLib.getInstance().getCommandManager().register(new TestCommand());
```

## Creating Custom Games
NoxLib has its own custom game support, to see how they are made, it's best to see the Glass Bridge game class.

A custom game must be registered in the `GameFramework` class, and must also extend `Game`.

Here is an example game:

```java
import me.noxiuam.noxlib.command.util.CommandContext;
import me.noxiuam.noxlib.feature.games.Game;
import net.dv8tion.jda.api.entities.Member;

public class TestGame extends Game {

   public TestGame() {
      // Name, ID, and two unicode reactions.
      super("Test Game", 1, "\u2B05", "\u27A1");
   }

   @Override
   public void init(CommandContext ctx, Member member) {
      // Game setup logic
      this.run(ctx, member);
   }

   @Override
   public void run(CommandContext ctx, Member member) {
      // Game start logic, should always be ran at the end of init().
   }

   @Override
   public void handleGameInput(String unicode, Member member) {
      // Main game logic, use this for handling inputs, game movement, etc.
   }
}
```
