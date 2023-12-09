package utilsplugin.discord;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import arc.util.Log;
import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.GuildMessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.gateway.intent.Intent;
import discord4j.gateway.intent.IntentSet;
import discord4j.rest.util.Color;
import mindustry.Vars;
import mindustry.gen.Call;
import mindustry.maps.Map;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import utilsplugin.utils.config;

public class bot {
  public static GuildMessageChannel channel;
  public static void load() {
    GatewayDiscordClient gateway = DiscordClient.create(config.get("discordbot_token"))
                                                        .gateway()
                                                        .setEnabledIntents(IntentSet.of(Intent.GUILD_MEMBERS, Intent.GUILD_MESSAGES))
                                                        .login()
                                                        .block();
    channel = gateway.getChannelById(Snowflake.of(config.get("discordbot_channelid"))).ofType(GuildMessageChannel.class).block();
    gateway.on(MessageCreateEvent.class).subscribe(e -> {
      var message = e.getMessage();
      if (message.getContent().isEmpty()) return;
      var member = e.getMember().orElse(null);
      if (member == null || member.isBot()) return;
      if(message.getChannelId().equals(channel.getId())) {
        String content = message.getContent();
        if (content.startsWith("!maps")){
          Map map = Vars.state.map;
          try {
            Cloudinary cloudinary = new Cloudinary(config.get("CLOUDINARY_URL"));
            cloudinary.config.secure = true;
            File mapfile = map.previewFile().file();
            if (!mapfile.exists()) {
              mapfile.createNewFile();
            }
            byte[] b = map.texture.getTextureData().getPixmap().getPixels().array();
            Files.write(Paths.get(mapfile.toURI()), b);
            var upload = cloudinary.uploader().upload(map.previewFile().file(), ObjectUtils.emptyMap());
            Object url = upload.get("url");
            Log.info(url);
            sendEmbedImage("Map: " + map.plainName() + "\nAuthor: " + map.author(), Color.DARK_GRAY, url.toString());
          } catch (Exception er) {
            Log.err(er);
          }
        }else{
          String user = member.getDisplayName();
          Call.sendMessage("[blue][][white] " + user + ": " + content);
        }
      }
    });
  }
  public static void send(String content) {
    channel.createMessage(content).subscribe();
  }
  public static void sendEmbed(String content, Color color) {
    EmbedCreateSpec embed = EmbedCreateSpec.builder()
      .color(color)
      .title(content)
      .build();
    channel.createMessage(embed).subscribe();
  }
  public static void sendEmbedImage(String content, Color color, String Url) {
    EmbedCreateSpec embed = EmbedCreateSpec.builder()
      .color(color)
      .title(content)
      .image(Url)
      .build();
    channel.createMessage(embed).subscribe();
  }
}
