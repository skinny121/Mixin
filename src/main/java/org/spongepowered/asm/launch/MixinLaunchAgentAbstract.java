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
package org.spongepowered.asm.launch;

import java.io.File;
import java.net.URI;


/**
 * Launch agent base class
 */
public abstract class MixinLaunchAgentAbstract implements IMixinLaunchAgent {

    /**
     * URI to the container
     */
    protected final URI uri;

    /**
     * File containing this tweaker
     */
    protected final File container;
    
    /**
     * "Main" manifest attributes from the container
     */
    protected final MainAttributes attributes;
    
    /**
     * Create a new launch agent for the specified URI
     * 
     * @param uri
     */
    public MixinLaunchAgentAbstract(URI uri) {
        this.uri = uri;
        this.container = this.uri != null ? new File(this.uri) : null;
        this.attributes = MainAttributes.of(uri);
    }
    
    @Override
    public String toString() {
        return String.format("LaunchAgent[%s:%s]", this.getClass().getSimpleName(), this.uri);
    }

}
