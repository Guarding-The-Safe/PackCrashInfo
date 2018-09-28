package atm.bloodworkxgaming.packcrashinfo;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import static atm.bloodworkxgaming.packcrashinfo.PackCrashInfo.VERSION;

@Mod(modid = PackCrashInfo.MOD_ID, version = VERSION, name = "Pack Crash Info")
public class PackCrashInfo {
    public static final String MOD_ID = "packcrashinfo";
    public static final String VERSION = "%VERSION%";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        FMLCommonHandler.instance().registerCrashCallable(new PackCrashCallable());
    }
}
