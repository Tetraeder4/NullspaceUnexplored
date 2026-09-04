package net.tetraeder4.nullspaceunexplored.datagen;

import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.Identifier;

import java.util.Optional;

public class CustomModelTemplates {
    public static final ModelTemplate CAVE_VINE;

    private static ModelTemplate create(final String id, final TextureSlot... slots) {
        return new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath("nullspaceunexplored","block/" + id)), Optional.empty(), slots);
    }

    static {CAVE_VINE = create("template_cave_vine", TextureSlot.TEXTURE);}
}
