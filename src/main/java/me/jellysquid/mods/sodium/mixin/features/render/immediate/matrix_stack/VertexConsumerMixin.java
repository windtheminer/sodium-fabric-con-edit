package me.jellysquid.mods.sodium.mixin.features.render.immediate.matrix_stack;

import net.caffeinemc.mods.sodium.api.math.MatrixHelper;
import net.minecraft.client.render.VertexConsumer;

import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(VertexConsumer.class)
public interface VertexConsumerMixin {
    @Shadow
    VertexConsumer normal(float x, float y, float z);

    @Shadow
    VertexConsumer vertex(double x, double y, double z);

    /**
     * @reason Avoid allocations
     * @author JellySquid
     */
    @Overwrite
    default VertexConsumer vertex(Matrix4f matrix, float x, float y, float z) {
        float xt = MatrixHelper.transformPositionX(matrix, x, y, z);
        float yt = MatrixHelper.transformPositionY(matrix, x, y, z);
        float zt = MatrixHelper.transformPositionZ(matrix, x, y, z);

        return this.vertex(xt, yt, zt);
    }

    /**
     * @reason Avoid allocations
     * @author JellySquid
     */
    // TODO 24w05a Not sure if this is solid. Disabling for now.
    /*
    @Overwrite
    default VertexConsumer normal(MatrixStack.Entry matrix, float x, float y, float z) {
        float xt = MatrixHelper.transformNormalX(matrix.getNormalMatrix(), x, y, z);
        float yt = MatrixHelper.transformNormalY(matrix.getNormalMatrix(), x, y, z);
        float zt = MatrixHelper.transformNormalZ(matrix.getNormalMatrix(), x, y, z);

        return this.normal(xt, yt, zt);
    }*/
}
