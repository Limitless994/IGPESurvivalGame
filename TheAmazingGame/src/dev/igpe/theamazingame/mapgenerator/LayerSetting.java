package dev.igpe.theamazingame.mapgenerator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Random;

public final class LayerSetting {

	private int m_width;

	private int m_height;

	private int m_stepSize;

	private int m_depth;

	private Random m_random;

	public LayerSetting(int width, int height, int stepSize, int depth, Random random) {
		this.m_width = width;
		this.m_height = height;
		this.m_stepSize = stepSize;
		this.m_depth = depth;
		this.m_random = random;
	}

	public int getWidth() {
		return this.m_width;
	}

	public int getHeight() {
		return this.m_height;
	}

	public int getStepSize() {
		return this.m_stepSize;
	}

	public int getDepth() {
		return this.m_depth;
	}

	public Random getRandom() {
		return this.m_random;
	}

	public void serialize(DataOutputStream out) {
		try {
			out.writeInt(m_width);
			out.writeInt(m_height);
			out.writeInt(m_stepSize);
			out.writeInt(m_depth);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deserialize(DataInputStream in) {
		try {
			m_width = in.readInt();
			m_height = in.readInt();
			m_stepSize = in.readInt();
			m_depth = in.readInt();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
