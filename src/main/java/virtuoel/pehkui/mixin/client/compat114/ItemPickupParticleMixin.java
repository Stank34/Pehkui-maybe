package virtuoel.pehkui.mixin.client.compat114;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.ItemPickupParticle;
import net.minecraft.entity.Entity;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(ItemPickupParticle.class)
public class ItemPickupParticleMixin
{
	@Dynamic @Shadow @Final @Mutable Entity field_3821; // UNMAPPED_FIELD
	@Dynamic @Shadow @Final @Mutable float field_3822; // UNMAPPED_FIELD
	
	/*
	@Dynamic
	@ModifyArg(method = MixinConstants.BUILD_GEOMETRY, index = 2, at = @At(value = "INVOKE", target = MixinConstants.LERP, ordinal = 4))
	private double pehkui$buildGeometry$offset(double value)
	{
		final float scale = ScaleUtils.getEyeHeightScale(field_3821, MinecraftClient.getInstance().getTickDelta());
		
		if (scale != 1.0F)
		{
			return (value - field_3822) + (field_3822 * scale);
		}
		
		return value;
	}
	*/
}
