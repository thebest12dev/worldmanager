package worldmanager;

import com.thebest12lines.worldmanager.annotation.CoreClass;
import com.thebest12lines.worldmanager.util.Output;
import net.querz.nbt.io.NBTUtil;
import net.querz.nbt.io.NamedTag;
import net.querz.nbt.tag.CompoundTag;
import net.querz.nbt.tag.ListTag;
import net.querz.nbt.tag.Tag;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/// The main API for managing and configuring the data file (in this case, `worldmanager.dat`).
/// @author thebest12lines
@CoreClass
public class DataFile {
    /// No constructor
    private DataFile() {}
    /// Creates the data file if it does not exist.
    /// May be subject to exceptions.
    public static void createIfNotExists() {
        if (!Files.exists(Path.of("worldmanager/worldmanager.dat"))) initializeFile();
    }
    /// Private method for initializing the data file.
    private static void initializeFile() {
        boolean success = false;
        Output.print("["+DataFile.class.getCanonicalName()+"]: Data file does not exist! Recreating file...");
        try {
            CompoundTag data = new CompoundTag();
            NBTUtil.write(data,"worldmanager/worldmanager.dat");
            CompoundTag tagData = new CompoundTag();
            CompoundTag tagMetadata = new CompoundTag();
            CompoundTag tagFlags = new CompoundTag();
            ListTag<CompoundTag> tagLoadedWorlds = (ListTag<CompoundTag>) ListTag.createUnchecked(CompoundTag.class);
            CompoundTag tagLoadedWorldsMinecraftSavesFolder = new CompoundTag();
            tagMetadata.putString("Author","thebest12lines");
            tagMetadata.putString("Branch","alpha");
            tagMetadata.putString("Name","worldmanager");
            tagMetadata.putLong("Build", 4L);
            tagMetadata.putString("Version","0.3.0");
            tagFlags.putByte("ExplicitDebugMode",(byte) 1);
            tagFlags.putByte("ExplicitVerboseMode", (byte) 1);
            tagFlags.putByte("NoStart", (byte) 0);
            tagFlags.putByte("VersionLatest", (byte) 1);
            tagFlags.putByte("ForceGui", (byte) 0);
            tagFlags.putString("ObjectsJSONLocation", "${worldmanagerObjectsFolder}");
            tagFlags.putString("ExplicitTheme","default");
            tagLoadedWorldsMinecraftSavesFolder.putString("BackupsPath","${backupsPath}");
            tagLoadedWorldsMinecraftSavesFolder.putString("MinecraftVersion","null");
            tagLoadedWorldsMinecraftSavesFolder.putString("Name","Java Edition Worlds");
            tagLoadedWorldsMinecraftSavesFolder.putString("Type","MinecraftSavesFolder");
            tagLoadedWorldsMinecraftSavesFolder.putString("Path","${defaultMinecraftSavesFolder}");
            tagLoadedWorldsMinecraftSavesFolder.putString("Version","null");
            tagFlags.putByte("ParseCommandLineArguments", (byte) 1);
            tagLoadedWorlds.add(tagLoadedWorldsMinecraftSavesFolder);
            tagData.put("Flags",tagFlags);
            tagData.put("Metadata",tagMetadata);
            tagData.put("LoadedWorlds",tagLoadedWorlds);
            tagData.putInt("LastErrorCode",0);
            data.put("Data",tagData);
            data.putLong("SchematicVersion",1L);
            data.putByteArray("InternalCommands", new byte[] {0x00,0x0c});
            NBTUtil.write(data,"worldmanager/worldmanager.dat");
            success = true;

        } catch (IOException e) {
            Output.print("["+DataFile.class.getCanonicalName()+"]: Something went wrong while trying to initialize the data file! Exception is shown below.");
            throw new RuntimeException(e);
        } finally {
            if (success) {
                Output.print("["+DataFile.class.getCanonicalName()+"]: Data has been successfully written!");
            }
        }
    }

    /**
     * Returns the specified flag as a <code>net.querz.nbt.tag.Tag</code> with any tag.
     * @param flagName The 'flag' (or setting) to return
     * @param clazz The class to initialize the value with
     * @return The returned tag.
     */
    public static Tag<?> getFlag(String flagName, Class clazz) {
        try {
           // createIfNotExists();
            NamedTag tag = NBTUtil.read("worldmanager/worldmanager.dat");
            CompoundTag data = (CompoundTag) tag.getTag();
            CompoundTag flags = data.getCompoundTag("Data").getCompoundTag("Flags");
            return flags.get(flagName,clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the flag to its value.
     * @param flagName The name of the flag.
     * @param value The value of the flag.
     */
    public static void setFlag(String flagName, String value) {
        try {
            // createIfNotExists();
            NamedTag tag = NBTUtil.read("worldmanager/worldmanager.dat");
            CompoundTag data = (CompoundTag) tag.getTag();
            CompoundTag flags = data.getCompoundTag("Data").getCompoundTag("Flags");
            flags.putString(flagName,value);
            NBTUtil.write(data,"worldmanager/worldmanager.dat");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
