package akkamaddi.akkamaddiCore.api;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Optional;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper.UnableToAccessFieldException;

public abstract class ModContents
{
    public abstract String getModId();

    public boolean isModLoaded()
    {
        return Loader.isModLoaded(getModId());
    }

    @SuppressWarnings("rawtypes")
	protected <T> Content<T> of(Class classToAccess, String fieldName)
    {
        return new Content<T>(classToAccess, null, fieldName, Functions.identity());
    }
    @SuppressWarnings("rawtypes")
	protected <T> Content<T> of(Class classToAccess, Object instance, String fieldName)
    {
        return new Content<T>(classToAccess, instance, fieldName, Functions.identity());
    }
    @SuppressWarnings("rawtypes")
	protected <T> Content<T> of(Class classToAccess, String fieldName, Function functionToApply)
    {
        return new Content<T>(classToAccess, null, fieldName, functionToApply);
    }
    @SuppressWarnings("rawtypes")
	protected <T> Content<T> of(Class classToAccess, Object instance, String fieldName, Function functionToApply)
    {
        return new Content<T>(classToAccess, instance, fieldName, functionToApply);
    }

    public class Content<T>
    {
        @SuppressWarnings("rawtypes")
		private final Class classToAccess;
        private final Object instance;
        private final String fieldName;
        @SuppressWarnings("rawtypes")
		private final Function functionToApply;

        @SuppressWarnings("rawtypes")
		private Content(Class classToAccess, Object instance, String fieldName, Function functionToApply)
        {
            this.classToAccess = classToAccess;
            this.instance = instance;
            this.fieldName = fieldName;
            this.functionToApply = functionToApply;
        }

        @SuppressWarnings("unchecked")
		public T get()
        {
            if (!isModLoaded())
            {
                throw new ModMissingException();
            }

            try
            {
                return (T) functionToApply.apply(
                           ReflectionHelper.getPrivateValue(classToAccess, instance, fieldName));
            }
            catch (UnableToAccessFieldException e)
            {
                throw new ModReflectionFailException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
	public static <T> Function<Optional<T>, T> optionalGet()
    {
        return (Function<Optional<T>, T>) OPTIONAL_GET;
    }
    
    @SuppressWarnings("rawtypes")
	private static final Function OPTIONAL_GET = new Function<Optional, Object>()
    {
        @Override public Object apply(Optional input)
        {
            return input.get();
        }
    };

    @SuppressWarnings("serial")
	public class ModMissingException extends RuntimeException { }

    @SuppressWarnings("serial")
	public class ModReflectionFailException extends RuntimeException
    {
        public ModReflectionFailException(Throwable e)
        {
            super(e);
        }
    }
} // end class ModContents
