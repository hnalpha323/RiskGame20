package nullrestrictor;

/**
 * An element should not accept the null values if necessary
 * A method should not return the null values if necessary
 * @author Meet
 * @version 3.0.0
 */

public @interface NullHandler 
{
	/**
     * If a string claims it is NotNull and its value always gives empty string
     * @return the empty string value to avoid problems with nulls
     */
    String value() default "";
}
