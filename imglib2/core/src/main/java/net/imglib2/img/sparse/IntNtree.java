/*
 * #%L
 * ImgLib2: a general-purpose, multidimensional image processing library.
 * %%
 * Copyright (C) 2009 - 2012 Stephan Preibisch, Stephan Saalfeld, Tobias
 * Pietzsch, Albert Cardona, Barry DeZonia, Curtis Rueden, Lee Kamentsky, Larry
 * Lindsey, Johannes Schindelin, Christian Dietz, Grant Harris, Jean-Yves
 * Tinevez, Steffen Jaensch, Mark Longair, Nick Perry, and Jan Funke.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are
 * those of the authors and should not be interpreted as representing official
 * policies, either expressed or implied, of any organization.
 * #L%
 */

package net.imglib2.img.sparse;

import net.imglib2.img.basictypeaccess.IntAccess;

/**
 * IntAccess based on a {@link Ntree}<Integer>.
 * 
 * @author Tobias Pietzsch
 */
public final class IntNtree implements IntAccess, NtreeAccess< Integer, IntNtree >
{

	private long[] position;

	private Ntree< Integer > data;

	/**
	 * Standard constructor called by factory
	 * 
	 * @param dimensions
	 *            The dimensions of the tree
	 * @param value
	 *            Uniform value of created nodes of the tree
	 */
	public IntNtree( long[] dimensions, long[] position, int value )
	{
		this.data = new Ntree< Integer >( dimensions, value );

		this.position = position;

	}

	/* Copy constructor */
	private IntNtree( Ntree< Integer > data, long[] position )
	{
		this.data = data;
		this.position = position;
	}

	@Override
	public void close()
	{
		// TODO:
	}

	@Override
	public int getValue( final int index )
	{
		// ignore index, get tree position from RandomAccess/Cursor
		return data.getNode( position ).getValue();
	}

	@Override
	public void setValue( final int index, final int value )
	{
		// ignore index, get tree position from RandomAccess/Cursor
		data.createNodeWithValue( position, value );
	}

	@Override
	public Ntree< Integer > getCurrentStorageNtree()
	{
		return data;
	}

	@Override
	public IntNtree createInstance( long[] pos )
	{
		return new IntNtree( data, pos );
	}
}
