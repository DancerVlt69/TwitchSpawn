package net.programmer.igoodie.twitchspawn.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.programmer.igoodie.twitchspawn.TwitchSpawn;
import net.programmer.igoodie.twitchspawn.network.packet.EventPacket;
import net.programmer.igoodie.twitchspawn.network.packet.GlobalChatCooldownPacket;
import net.programmer.igoodie.twitchspawn.network.packet.OsRunPacket;
import net.programmer.igoodie.twitchspawn.network.packet.StatusChangedPacket;

public class NetworkManager {

    private static final String PROTOCOL_VERSION = "0.1.0-iskall";

    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(TwitchSpawn.MOD_ID, "network"))
            .clientAcceptedVersions(v -> v.equals(PROTOCOL_VERSION))
            .serverAcceptedVersions(v -> v.equals(PROTOCOL_VERSION))
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public static void initialize() {
        CHANNEL.registerMessage(0, StatusChangedPacket.class,
                StatusChangedPacket::encode,
                StatusChangedPacket::decode,
                StatusChangedPacket::handle);

        CHANNEL.registerMessage(1, OsRunPacket.class,
                OsRunPacket::encode,
                OsRunPacket::decode,
                OsRunPacket::handle);

        CHANNEL.registerMessage(2, GlobalChatCooldownPacket.class,
                GlobalChatCooldownPacket::encode,
                GlobalChatCooldownPacket::decode,
                GlobalChatCooldownPacket::handle
        );

        CHANNEL.registerMessage(3, EventPacket.class,
                EventPacket::encode,
                EventPacket::decode,
                EventPacket::handle
        );
    }

}
