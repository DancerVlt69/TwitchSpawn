package net.programmer.igoodie.twitchspawn.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.List;

public class TwitchSpawnCommand extends CommandBase {

//    public static final String COMMAND_NAME = "twitchspawn";
//
//    public static void register(CommandDispatcher<CommandSource> dispatcher) {
//        LiteralArgumentBuilder<CommandSource> root = Commands.literal(COMMAND_NAME);
//
//        root.then(Commands.literal("status").executes(TwitchSpawnCommand::statusModule));
//        root.then(Commands.literal("start").executes(TwitchSpawnCommand::startModule));
//        root.then(Commands.literal("stop").executes(TwitchSpawnCommand::stopModule));
//
//        root.then(Commands.literal("reloadcfg").executes(TwitchSpawnCommand::reloadModule));
//
//        root.then(Commands.literal("rules")
//                .executes(context -> rulesModule(context, null))
//                .then(CommandArguments.rulesetName("ruleset_name")
//                        .executes(context -> rulesModule(context, RulesetNameArgumentType.getRulesetName(context, "ruleset_name"))))
//        );
//
//        root.then(Commands.literal("simulate")
//                .then(CommandArguments.nbtCompound("event_simulation_json")
//                        .executes(context -> simulateModule(context, null))
//                        .then(CommandArguments.streamer("streamer_nick")
//                                .executes(context -> simulateModule(context, StreamerArgumentType.getStreamer(context, "streamer_nick")))))
//        );
//
//        root.then(Commands.literal("test")
//                .then(CommandArguments.streamer("streamer_nick")
//                        .executes(context -> testModule(context, StreamerArgumentType.getStreamer(context, "streamer_nick"))))
//        );
//
//        dispatcher.register(root);
//    }
//
//    /* ------------------------------------------------------------ */
//
//    public static int statusModule(CommandContext<CommandSource> context) {
//        String translationKey = TwitchSpawn.TRACE_MANAGER.isRunning() ?
//                "commands.twitchspawn.status.on" : "commands.twitchspawn.status.off";
//
//        context.getSource().sendFeedback(new TranslationTextComponent(translationKey), false);
//
//        return 1;
//    }
//
//    public static int startModule(CommandContext<CommandSource> context) {
//        String sourceNickname = context.getSource().getName();
//
//        // If has no permission
//        if (!ConfigManager.CREDENTIALS.hasPermission(sourceNickname)) {
//            context.getSource().sendFeedback(new TranslationTextComponent(
//                    "commands.twitchspawn.start.no_perm"), true);
//            TwitchSpawn.LOGGER.info("{} tried to run TwitchSpawn, but no permission", sourceNickname);
//            return 0;
//        }
//
//        try {
//            TwitchSpawn.TRACE_MANAGER.start();
//            return 1;
//
//        } catch (IllegalStateException e) {
//            context.getSource().sendFeedback(new TranslationTextComponent(
//                    "commands.twitchspawn.start.illegal_state"), true);
//            return 0;
//        }
//    }
//
//    public static int stopModule(CommandContext<CommandSource> context) {
//        String sourceNickname = context.getSource().getName();
//
//        // If has no permission
//        if (!ConfigManager.CREDENTIALS.hasPermission(sourceNickname)) {
//            context.getSource().sendFeedback(new TranslationTextComponent(
//                    "commands.twitchspawn.stop.no_perm"), true);
//            TwitchSpawn.LOGGER.info("{} tried to stop TwitchSpawn, but no permission", sourceNickname);
//            return 0;
//        }
//
//        try {
//            TwitchSpawn.TRACE_MANAGER.stop(context.getSource(), "Command execution");
//            return 1;
//
//        } catch (IllegalStateException e) {
//            context.getSource().sendFeedback(new TranslationTextComponent(
//                    "commands.twitchspawn.stop.illegal_state"), true);
//            return 0;
//        }
//    }
//
//    public static int reloadModule(CommandContext<CommandSource> context) {
//        CommandSource source = context.getSource();
//        String sourceNickname = source.getName();
//
//        boolean isOp = Stream.of(TwitchSpawn.SERVER.getPlayerList().getOppedPlayerNames())
//                .anyMatch(oppedPlayerName -> oppedPlayerName.equalsIgnoreCase(sourceNickname));
//
//        // If is not OP or has no permission
//        if (!isOp && !ConfigManager.CREDENTIALS.hasPermission(sourceNickname)) {
//            context.getSource().sendFeedback(new TranslationTextComponent(
//                    "commands.twitchspawn.reloadcfg.no_perm"), true);
//            TwitchSpawn.LOGGER.info("{} tried to reload TwitchSpawn configs, but no permission", sourceNickname);
//            return 0;
//        }
//
//        if (TwitchSpawn.TRACE_MANAGER.isRunning()) {
//            source.sendFeedback(new TranslationTextComponent(
//                    "commands.twitchspawn.reloadcfg.already_started"), false);
//            return 0;
//        }
//
//        try {
//            ConfigManager.loadConfigs();
//            source.sendFeedback(new TranslationTextComponent(
//                    "commands.twitchspawn.reloadcfg.success"), false);
//            return 1;
//
//        } catch (TwitchSpawnLoadingErrors e) {
//            String errorLog = "• " + e.toString().replace("\n", "\n• ");
//            source.sendFeedback(new TranslationTextComponent(
//                    "commands.twitchspawn.reloadcfg.invalid_syntax", errorLog), false);
//            return 0;
//        }
//    }
//
//    /* ------------------------------------------------------------ */
//
//    public static int rulesModule(CommandContext<CommandSource> context, String rulesetName) {
//        if (rulesetName == null) {
//            context.getSource().sendFeedback(new TranslationTextComponent(
//                    "commands.twitchspawn.rules.list",
//                    ConfigManager.RULESET_COLLECTION.getStreamers()), true);
//            return 1;
//        }
//
//        TSLRuleset ruleset = ConfigManager.RULESET_COLLECTION.getRuleset(rulesetName);
//
//        if (ruleset == null) {
//            context.getSource().sendFeedback(new TranslationTextComponent(
//                    "commands.twitchspawn.rules.one.fail",
//                    rulesetName), true);
//            return 0;
//        }
//
//        String translationKey = rulesetName.equalsIgnoreCase("default") ?
//                "commands.twitchspawn.rules.default" : "commands.twitchspawn.rules.one";
//        context.getSource().sendFeedback(new TranslationTextComponent(translationKey,
//                rulesetName, ruleset.toString()), true);
//        return 1;
//    }
//
//    /* ------------------------------------------------------------ */
//
//    public static int simulateModule(CommandContext<CommandSource> context, String streamerNick) {
//        try {
//            String sourceName = context.getSource().getName();
//            String streamerName = streamerNick != null ? streamerNick : sourceName;
//
//            // If has no permission
//            if (!ConfigManager.CREDENTIALS.hasPermission(sourceName)) {
//                context.getSource().sendFeedback(new TranslationTextComponent(
//                        "commands.twitchspawn.simulate.no_perm"), true);
//                TwitchSpawn.LOGGER.info("{} tried to simulate an event, but no permission", sourceName);
//                return 0;
//            }
//
//            CompoundNBT nbt = context.getArgument("event_simulation_json", CompoundNBT.class);
//            String eventName = nbt.getString("event");
//
//            if (eventName.isEmpty()) {
//                context.getSource().sendFeedback(new TranslationTextComponent(
//                        "commands.twitchspawn.simulate.missing"), true);
//                return 0;
//            }
//
//            TSLEventPair eventPair = TSLEventKeyword.toPairs(eventName).iterator().next();
//
//            if (eventPair == null) {
//                context.getSource().sendFeedback(new TranslationTextComponent(
//                        "commands.twitchspawn.simulate.invalid_event", eventName), true);
//                return 0;
//            }
//
//            boolean random = nbt.getBoolean("random");
//            EventArguments simulatedEvent = new EventArguments(eventPair.getEventType(), eventPair.getEventAccount());
//            simulatedEvent.streamerNickname = streamerName;
//
//            if (random) {
//                simulatedEvent.randomize("SimulatorDude", "Simulating a message");
//
//            } else {
//                simulatedEvent.actorNickname = nbt.getString("actor").isEmpty() ? "SimulatorDude" : nbt.getString("actor");
//                simulatedEvent.message = "Simulating a message";
//                simulatedEvent.donationAmount = nbt.getDouble("amount");
//                simulatedEvent.donationCurrency = nbt.getString("currency");
//                simulatedEvent.subscriptionMonths = nbt.getInt("months");
//                simulatedEvent.raiderCount = nbt.getInt("raiders");
//                simulatedEvent.viewerCount = nbt.getInt("viewers");
//            }
//
//            ConfigManager.RULESET_COLLECTION.handleEvent(simulatedEvent);
//
//            context.getSource().sendFeedback(new TranslationTextComponent(
//                    "commands.twitchspawn.simulate.success", nbt), true);
//
//            return 1;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//
//    private static final int DEFAULT_FADE_IN_TICKS = 10;
//    private static final int DEFAULT_STAY_TICKS = 70;
//    private static final int DEFAULT_FADE_OUT_TICKS = 20;
//
//    public static int testModule(CommandContext<CommandSource> context, String streamerNick) throws CommandSyntaxException {
//        if (!ConfigManager.RULESET_COLLECTION.hasStreamer(streamerNick)) {
//            TwitchSpawn.LOGGER.info("There are no ruleset associated with {}", streamerNick);
//            context.getSource().sendFeedback(new TranslationTextComponent("commands.twitchspawn.test.not_found", streamerNick), true);
//            return 0;
//        }
//
//        ServerPlayerEntity streamerPlayer = context.getSource().asPlayer();
//        TSLRuleset ruleset = ConfigManager.RULESET_COLLECTION.getRuleset(streamerNick);
//        TimeTaskQueue queue = ConfigManager.RULESET_COLLECTION.getQueue(streamerNick);
//
//        Collection<TSLEvent> events = ruleset.getEvents();
//        Iterator<TSLEvent> eventIterator = events.iterator();
//        TSLEvent event;
//        int index = 0;
//
//        while (eventIterator.hasNext()) {
//            event = eventIterator.next();
//
//            TSLEventPair eventPair = TSLEventKeyword.toPairs(event.getName()).iterator().next();
//            EventArguments eventArguments = new EventArguments(eventPair);
//            eventArguments.randomize();
//            eventArguments.streamerNickname = streamerPlayer.getName().getString();
//            eventArguments.actorNickname = "TesterKid";
//
//            for (TSLAction action : event.getActions()) {
//                ITextComponent text = ITextComponent.Serializer.fromJsonLenient(
//                        String.format("{text:\"Testing %s action\", color:\"dark_purple\"}", TSLActionKeyword.ofClass(action.getClass())));
//                STitlePacket packet = new STitlePacket(STitlePacket.Type.TITLE, text,
//                        DEFAULT_FADE_IN_TICKS, DEFAULT_STAY_TICKS, DEFAULT_FADE_OUT_TICKS);
//
//                ITextComponent subtext = ITextComponent.Serializer.fromJsonLenient(
//                        String.format("{text:\"Rules traversed: %.02f%%\", color:\"dark_purple\"}", 100 * (index + 1f) / ruleset.getRulesRaw().size()));
//                STitlePacket subtitlePacket = new STitlePacket(STitlePacket.Type.SUBTITLE, subtext,
//                        DEFAULT_FADE_IN_TICKS, DEFAULT_STAY_TICKS, DEFAULT_FADE_OUT_TICKS);
//
//                queue.queue(() -> {
//                    streamerPlayer.connection.sendPacket(packet);
//                    streamerPlayer.connection.sendPacket(subtitlePacket);
//                });
//                queue.queue(() -> action.process(eventArguments));
//
//                index++;
//            }
//        }
//
//        TwitchSpawn.LOGGER.info("Tests queued for {}", streamerNick);
//        context.getSource().sendFeedback(new TranslationTextComponent("commands.twitchspawn.test.success", streamerNick), true);
//        return 1;
//    }

    @Override
    public String getName() {
        return "twitchspawn";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/twitchspawn start|stop|reloadcfg|status|simulate|test";
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return super.getTabCompletions(server, sender, args, targetPos); // TODO
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

    }
}
