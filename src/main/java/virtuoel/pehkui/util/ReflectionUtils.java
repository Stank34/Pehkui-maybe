package virtuoel.pehkui.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.Packet;
import net.minecraft.predicate.NumberRange;
import net.minecraft.server.network.ServerPlayNetworkHandler;

public final class ReflectionUtils
{
	public static float getFlyingSpeed(final LivingEntity entity)
	{
		return entity.airStrafingSpeed;
	}
	
	public static void setFlyingSpeed(final LivingEntity entity, final float speed)
	{
		entity.airStrafingSpeed = speed;
	}
	
	public static double getMountedHeightOffset(final Entity entity)
	{
		return entity.getMountedHeightOffset();
	}
	
	public static void sendPacket(final ServerPlayNetworkHandler handler, final Packet<?> packet)
	{
		handler.sendPacket(packet);
	}
	
	public static boolean isDummy(final NumberRange<?> range)
	{
		return range.isDummy();
	}
	
	public static Optional<Field> getField(final Optional<Class<?>> classObj, final String fieldName)
	{
		return classObj.map(c ->
		{
			try
			{
				final Field f = c.getDeclaredField(fieldName);
				f.setAccessible(true);
				return f;
			}
			catch (SecurityException | NoSuchFieldException e)
			{
				
			}
			return null;
		});
	}
	
	public static void setField(final Optional<Class<?>> classObj, final String fieldName, Object object, Object value)
	{
		ReflectionUtils.getField(classObj, fieldName).ifPresent(f ->
		{
			try
			{
				f.set(object, value);
			}
			catch (IllegalArgumentException | IllegalAccessException e)
			{
				
			}
		});
	}
	
	public static Optional<Method> getMethod(final Optional<Class<?>> classObj, final String methodName, Class<?>... args)
	{
		return classObj.map(c ->
		{
			try
			{
				final Method m = c.getMethod(methodName, args);
				m.setAccessible(true);
				return m;
			}
			catch (SecurityException | NoSuchMethodException e)
			{
				
			}
			return null;
		});
	}
	
	public static <T> Optional<Constructor<T>> getConstructor(final Optional<Class<T>> clazz, final Class<?>... params)
	{
		return clazz.map(c ->
		{
			try
			{
				return c.getConstructor(params);
			}
			catch (NoSuchMethodException | SecurityException e)
			{
				return null;
			}
		});
	}
	
	public static Optional<Class<?>> getClass(final String className, final String... classNames)
	{
		Optional<Class<?>> ret = getClass(className);
		
		for (final String name : classNames)
		{
			if (ret.isPresent())
			{
				return ret;
			}
			
			ret = getClass(name);
		}
		
		return ret;
	}
	
	public static Optional<Class<?>> getClass(final String className)
	{
		try
		{
			return Optional.of(Class.forName(className));
		}
		catch (ClassNotFoundException e)
		{
			
		}
		
		return Optional.empty();
	}
	
	private ReflectionUtils()
	{
		
	}
}
