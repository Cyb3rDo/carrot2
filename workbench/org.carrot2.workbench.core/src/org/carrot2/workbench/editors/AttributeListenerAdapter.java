
/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2017, Dawid Weiss, Stanisław Osiński.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package org.carrot2.workbench.editors;

/**
 * Empty implementation adapter for {@link IAttributeListener}.
 */
public abstract class AttributeListenerAdapter implements IAttributeListener
{
    public void valueChanged(AttributeEvent event)
    {
        // Empty.
    }
    
    public void valueChanging(AttributeEvent event)
    {
        // Empty.
    }
}
