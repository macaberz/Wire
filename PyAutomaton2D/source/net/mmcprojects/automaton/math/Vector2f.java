/*
 * Copyright (C) 2014 Benny Bobaganoosh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.mmcprojects.automaton.math;

public class Vector2f 
{
	private float m_x;
	private float m_y;
	
	public Vector2f(float x, float y)
	{
		this.m_x = x;
		this.m_y = y;
	}

	public float length()
	{
		return (float)Math.sqrt(m_x * m_x + m_y * m_y);
	}

	public float max()
	{
		return Math.max(m_x, m_y);
	}

	public float dot(Vector2f r)
	{
		return m_x * r.getX() + m_y * r.getY();
	}
	
	public Vector2f normalized()
	{
		float length = length();
		
		return new Vector2f(m_x / length, m_y / length);
	}

	public float cross(Vector2f r)
	{
		return m_x * r.getY() - m_y * r.getX();
	}

	/*
	public Vector2f lerp(Vector2f dest, float lerpFactor)
	{
		return dest.sub(this).mul(lerpFactor).add(this);
	}
	*/

	public Vector2f rotate(float angle)
	{
		double rad = Math.toRadians(angle);
		double cos = Math.cos(rad);
		double sin = Math.sin(rad);
		
		return new Vector2f((float)(m_x * cos - m_y * sin),(float)(m_x * sin + m_y * cos));
	}
	
	public void add(Vector2f r)
	{
		this.m_x += r.getX();
		this.m_y += r.getY();
	}
	
	public void add(float r)
	{
		this.m_x += r;
		this.m_y += r;
	}
	
	public void sub(Vector2f r)
	{
		this.m_x -= r.getX();
		this.m_y -= r.getY();
	}
	
	public void sub(float r)
	{
		this.m_x -= r;
		this.m_y -= r;
	}
	
	public void mul(Vector2f r)
	{
		this.m_x *= r.getX();
		this.m_y *= r.getY();
	}
	
	public void mul(float r)
	{
		this.m_x *= r;
		this.m_y *= r;
	}
	
	public void div(Vector2f r)
	{
		this.m_x /= r.getX();
		this.m_y /= r.getY();
	}
	
	public void div(float r)
	{
		this.m_x /= r;
		this.m_y /= r;
	}
	
	public Vector2f abs()
	{
		return new Vector2f(Math.abs(m_x), Math.abs(m_y));
	}
	
	public String toString()
	{
		return "(" + m_x + " " + m_y + ")";
	}

	public void set(float x, float y) { this.m_x = x; this.m_y = y; }
	public void set(Vector2f r) { set(r.getX(), r.getY()); }

	public float getX()
	{
		return m_x;
	}

	public void setX(float x)
	{
		this.m_x = x;
	}

	public float getY()
	{
		return m_y;
	}

	public void setY(float y)
	{
		this.m_y = y;
	}

	public boolean equals(Vector2f r)
	{
		return m_x == r.getX() && m_y == r.getY();
	}
}