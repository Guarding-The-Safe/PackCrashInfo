package atm.bloodworkxgaming.packcrashinfo;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static atm.bloodworkxgaming.packcrashinfo.PackCrashInfo.VERSION;

@Mod(modid = PackCrashInfo.MOD_ID, version = VERSION, name = "Pack Crash Info")
public class PackCrashInfo {
    public static final String MOD_ID = "packcrashinfo";
    public static final String VERSION = "%VERSION%";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    private static final PackCrashCallable callable = new PackCrashCallable();

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        FMLCommonHandler.instance().registerCrashCallable(callable);

        String information = callable.call();
        String header = "|" + StringUtils.repeat('-', information.length() + 2) + "|";
        String name = "Modpack Information";
        LOGGER.info(header);
        LOGGER.info("| " + name + StringUtils.repeat(' ', information.length() - name.length()) + " |");
        LOGGER.info("| " + information + " |");
        LOGGER.info(header);

    }
}
