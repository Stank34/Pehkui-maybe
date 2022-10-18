package virtuoel.pehkui.mixin.client.compat115;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleRenderUtils;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin
{
	@ModifyConstant(method = MixinConstants.RENDER_SHADOW_PART, constant = @Constant(doubleValue = 0.015625D), remap = false)
	private static double pehkui$renderShadowPart$shadowHeight(double value)
	{
		return value - 0.0155D;
	}
	
	@Inject(method = "renderHitbox", at = @At(value = "TAIL"))
	private void pehkui$renderHitbox(MatrixStack matrices, VertexConsumer vertices, Entity entity, float f, CallbackInfo ci)
	{
		final float interactionWidth = ScaleUtils.getInteractionWidthScale(entity);
		final float interactionHeight = ScaleUtils.getInteractionHeightScale(entity);
		
		if (interactionWidth != 1.0F || interactionHeight != 1.0F)
		{
			final double scaledWidth = (entity.getWidth() * interactionWidth * 0.3D) - (entity.getWidth() * 0.3D);
			final double scaledHeight = (entity.getHeight() * interactionHeight * 0.3D) - (entity.getHeight() * 0.3D);
			
			final Box box = entity.getBoundingBox().expand(scaledWidth, scaledHeight, scaledWidth).offset(-entity.getX(), -entity.getY(), -entity.getZ());
			
			ScaleRenderUtils.renderInteractionBox(matrices, vertices, box);
		}
	}
}
