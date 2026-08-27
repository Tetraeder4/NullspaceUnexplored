package net.tetraeder4.nullspaceunexplored.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.tetraeder4.nullspaceunexplored.NullspaceUnexplored;

import java.util.function.Function;

public class ModItems {
    public static final Item DRYWALL_DEBRIS = registerItem("drywall_debris", Item::new);

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NullspaceUnexplored.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NullspaceUnexplored.MOD_ID, name)))));
    }

    public static void registerModItems() {
        NullspaceUnexplored.LOGGER.info("Registering Mod Items for " + NullspaceUnexplored.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(DRYWALL_DEBRIS);
        });
    }
}

