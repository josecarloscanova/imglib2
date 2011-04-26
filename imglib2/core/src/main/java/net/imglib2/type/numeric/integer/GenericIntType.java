/**
 * Copyright (c) 2009--2010, Stephan Preibisch & Stephan Saalfeld
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.  Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials
 * provided with the distribution.  Neither the name of the Fiji project nor
 * the names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * @author Stephan Preibisch & Stephan Saalfeld
 */
package net.imglib2.type.numeric.integer;

import net.imglib2.img.NativeImg;
import net.imglib2.img.basictypeaccess.IntAccess;
import net.imglib2.img.basictypeaccess.array.IntArray;
import net.imglib2.type.NativeType;
import net.imglib2.util.Util;

public abstract class GenericIntType<T extends GenericIntType<T>> extends AbstractIntegerType<T> implements NativeType<T>
{
	int i = 0;

	final protected NativeImg<T, ? extends IntAccess> img;
	
	// the DataAccess that holds the information 
	protected IntAccess dataAccess;
	
	// this is the constructor if you want it to read from an array
	public GenericIntType( NativeImg<T, ? extends IntAccess> intStorage )
	{
		img = intStorage;
	}

	// this is the constructor if you want it to be a variable
	public GenericIntType( final int value )
	{
		img = null;
		dataAccess = new IntArray( 1 );
		setValue( value );
	}

	// this is the constructor if you want it to be a variable
	public GenericIntType() { this( 0 ); }
	
	@Override
	public int getEntitiesPerPixel() { return 1; }

	@Override
	public void updateContainer( final Object c ) { dataAccess = img.update( c ); }

	protected int getValue(){ return dataAccess.getValue( i ); }
	protected void setValue( final int f ){ dataAccess.setValue( i, f ); }
	
	@Override
	public void mul( final float c )
	{
		final int a = getValue();
		setValue( Util.round( a * c ) );
	}
	
	@Override
	public void mul( final double c )
	{
		final int a = getValue();
		setValue( ( int )Util.round( a * c ) );
	}
	
	@Override
	public void add( final T c )
	{
		final int a = getValue();
		setValue( a + c.getValue() );
	}

	@Override
	public void div( final T c )
	{
		final int a = getValue();
		setValue( a / c.getValue() );
	}

	@Override
	public void mul( final T c )
	{
		final int a = getValue( );
		setValue( a * c.getValue() );
	}

	@Override
	public void sub( final T c )
	{
		final int a = getValue( );
		setValue( a - c.getValue() );
	}

	@Override
	public int compareTo( final T c ) 
	{ 
		final int a = getValue();
		final int b = c.getValue();
		if ( a > b )
			return 1;
		else if ( a < b )
			return -1;
		else 
			return 0;
	}

	@Override
	public void set( final T c )
	{
		setValue( c.getValue() );
	}

	@Override
	public void setOne() { setValue( 1 ); }

	@Override
	public void setZero() { setValue( 0 ); }

	@Override
	public void inc()
	{
		int a = getValue();
		setValue( ++a );
	}

	@Override
	public void dec()
	{
		int a = getValue();
		setValue( --a );
	}
	
	@Override
	public String toString(){ return "" + getValue(); }

	@Override
	public void updateIndex( final int index ) { i = index; }
	@Override
	public int getIndex() { return i; }
	
	@Override
	public void incIndex() { ++i; }
	@Override
	public void incIndex( final int increment ) { i += increment; }
	@Override
	public void decIndex() { --i; }
	@Override
	public void decIndex( final int decrement ) { i -= decrement; }	

	@Override
	public int getBitsPerPixel() { return 32; }
}
