package atm.bloodworkxgaming.packcrashinfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import net.minecraftforge.fml.common.ICrashCallable;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PackCrashCallable implements ICrashCallable {
    @Override
    public String getLabel() {
        return "Modpack Information";
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();
        String name = PackCrashInfoConfig.modpackName;
        String version = PackCrashInfoConfig.modpackVersion;
        String author = PackCrashInfoConfig.modpackAuthor;
        boolean fromManifest = false;

        if (PackCrashInfoConfig.gatherManifestFirst) {
            Gson gson = new GsonBuilder().create();
            File f = new File("manifest.json");

            if (!f.exists()) {
                f = new File("minecraftinstance.json");
                PackCrashInfo.LOGGER.info("No manifest present, attempting to load the minecraftinstance.json file.");
            }

            if (f.exists()) {
                try (FileReader reader = new FileReader(f)) {
                    ManifestMockup manifest = gson.fromJson(reader, ManifestMockup.class);
                    name = manifest.name;
                    version = manifest.version;
                    author = manifest.author;
                    fromManifest = true;
                } catch (IOException | JsonSyntaxException | JsonIOException e) {
                    PackCrashInfo.LOGGER.error("Could not read from manifest", e);
                }
            } else {
                PackCrashInfo.LOGGER.error("No manifest or minecraftinstance.json present");
            }
        }

        sb.append("Modpack: [").append(name).append("] Version: [").append(version).append("] by author [").append(author).append("]");
        if (!fromManifest && PackCrashInfoConfig.gatherManifestFirst) {
            sb.append(" !!There was a problem reading the manifest!!");
        }

        return sb.toString();
    }
}
