/*
 * This file is part of Mixin, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.asm.mixin.injection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

/**
 * Specifies that this mixin method should inject a callback (or
 * callback<b>s</b>) to itself in the target method(s) identified by
 * {@link #method}.
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
    
    /**
     * String representation of a
     * {@link org.spongepowered.asm.mixin.injection.struct.MemberInfo 
     * MemberInfo} which identifies the target method.
     * 
     * @return target method for this injector
     */
    public String method();
    
    /**
     * Array of {@link At} annotations which describe the
     * {@link InjectionPoint}s in the target method. Allows one or more
     * callbacks to be injected in the target method.
     * 
     * @return injection point specifiers for this injector
     */
    public At[] at();
    
    /**
     * Setting an injected callback to <em>cancellable</em> allows the injected
     * callback to inject optional RETURN opcodes into the target method, the
     * return behaviour can then be controlled from within the callback by
     * interacting with the supplied {@link CallbackInfo} object.
     * 
     * @return true if this injector should inject appropriate RETURN opcodes
     *      which allow it to be cancelled
     */
    public boolean cancellable() default false;
    
    /**
     * Specifies the local variable capture behaviour for this injector.
     * 
     * <p>When capturing local variables in scope, the variables are appended to
     * the callback invocation after the {@link CallbackInfo} argument.</p>
     * 
     * <p>Capturing local variables from the target scope requires careful
     * planning because unlike other aspects of an injection (such as the target
     * method name and signature), the local variable table is <b>not</b> safe
     * from modification by other transformers which may be in use in the
     * production environment. Even other injectors which target the same target
     * method have the ability to modify the local variable table and thus it is
     * in no way safe to assume that local variables in scope at development
     * time will be so in production.</p>
     * 
     * <p>To provide some level of flexibility, especially where changes can be
     * anticipated (for example a well-known mod makes changes which result in a
     * particular structure for the local variable table) it is possible to
     * provide <em>overloads</em> for the handler method which will become
     * surrogate targets for the orphaned injector by annotating them with an
     * {@link Surrogate} annotation.</p>
     * 
     * <p>It is also important to nominate the failure behaviour to follow when
     * local capture fails and so all {@link LocalCapture} behaviours which
     * specify a capture action imply a particular behaviour for handling
     * failure. See the javadoc on the {@link LocalCapture} members for more
     * details.</p>
     * 
     * <p>Determining what local variables are available to you and in what
     * order can be somewhat tricky, and so a simple mechanism for enumerating
     * available locals is provided. By setting <code>locals</code> to
     * {@link LocalCapture#PRINT}, the injector writes the local capture state
     * to STDERR instead of injecting the callback. Using the output thus
     * obtained it is then a straightforward matter of altering the callback
     * method signature to match the signature proposed by the Callback
     * Injector.</p> 
     * 
     * @return the desired local capture behaviour for this injector
     */
    public LocalCapture locals() default LocalCapture.NO_CAPTURE;


    /**
     * By default, the annotation processor will attempt to locate an
     * obfuscation mapping for all {@link Inject} methods since it is
     * anticipated that in general the target of a {@link Inject} annotation
     * will be an obfuscated method in the target class. However since it is
     * possible to also apply mixins to non-obfuscated targets (or non-
     * obfuscated methods in obfuscated targets, such as methods added by Forge)
     * it may be necessary to suppress the compiler error which would otherwise
     * be generated. Setting this value to <em>false</em> will cause the
     * annotation processor to skip this annotation when attempting to build the
     * obfuscation table for the mixin.
     * 
     * @return True to instruct the annotation processor to search for
     *      obfuscation mappings for this annotation 
     */
    public boolean remap() default true;
}
