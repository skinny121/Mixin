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
package org.spongepowered.asm.mixin.transformer;

import java.util.SortedSet;

import org.spongepowered.asm.lib.tree.ClassNode;


/**
 * Mixin Transformer Module interface for pre- and post-processors
 */
public interface IMixinTransformerModule {

    /**
     * Called before the mixins are applied
     * 
     * @param transformedName Target class name
     * @param targetClass Target class tree
     * @param mixins Mixins to be applied
     */
    public abstract void preApply(String transformedName, ClassNode targetClass, SortedSet<MixinInfo> mixins);

    /**
     * Called after the mixins are applied
     * 
     * @param transformedName Target class name
     * @param targetClass Target class tree
     * @param mixins Mixins which were applied
     */
    public abstract void postApply(String transformedName, ClassNode targetClass, SortedSet<MixinInfo> mixins);

}
