package com.example.projectattempt1;

import android.graphics.Canvas;

public class Ball {
	public boolean isAlive;
	public int xPos, yPos, maxX, maxY, xIncrment, yIncrement, width, height;
	public Object[] bag = new Object[100];
	int bag_index = 0;
	public int speed = 10, aliendied = 0, persondied = 0, heartdied = 0;
	public boolean reset = false;

	public Ball(boolean passisAlive, int passxPos, int passyPos, int passmaxX,
			int passmaxY) {
		isAlive = passisAlive;
		xPos = passxPos;
		yPos = passyPos;
		maxX = passmaxX;
		maxY = passmaxY;
		xIncrment = 1;
		yIncrement = 1;
		width = 55;
		height = 52;
	}

	public void add(Object a) {
		bag[bag_index++] = a;
	}

	public void launched(int mode) {
		if (mode == 0) {
			xIncrment = -1;
			yIncrement = -1;
		} else if (mode == 1) {
			xIncrment = 0;
			yIncrement = -1;
		} else if (mode == 2) {
			xIncrment = 1;
			yIncrement = -1;
		}
	}

	public boolean isCollide(Object item) {

		float itemEndX, itemEndY;
		float midX, midY;
		midX = xPos + width / 2;
		midY = yPos + height / 2;
		itemEndX = ((float) ((Alien) item).xPos)
				+ ((float) ((Alien) item).width);
		itemEndY = ((float) ((Alien) item).yPos)
				+ ((float) ((Alien) item).height);

		if ((midX >= ((float) ((Alien) item).xPos) && midX <= itemEndX)
				&& (midY > ((float) ((Alien) item).yPos) && midY < itemEndY)) {
			aliendied++;
			return true;
		}
		return false;
	}

	public void BarCollide(Object item) {
		if (xPos + 50 > ((MovingBar) bag[0]).x
				&& xPos < ((MovingBar) bag[0]).x + width * 2 + 120
				&& yPos + height > ((MovingBar) bag[0]).y) {
			yIncrement *= -1;
		} else if (yPos > 1100) {
			reset = true;
			persondied++;
			heartdied++;
		}
	}

	public void bounce(Object item) {
		float midX, midY;
		midX = xPos + width / 2;
		midY = yPos - height / 2;
		float LeftoverX = Math.abs(((float) ((Alien) item).xPos) - midX);
		float LeftoverY = Math.abs(((float) ((Alien) item).yPos) - midY);
		int xbackIncrement = (xIncrment * -1) * 3;
		int ybakcIncrement = (yIncrement * -1) * 3;
		while (LeftoverX > 0 && LeftoverY > 0) {
			LeftoverX -= xbackIncrement;
			LeftoverY -= ybakcIncrement;
		}
		if (LeftoverX == LeftoverY) {
			xIncrment *= -1;
			yIncrement *= -1;
		}

		else if (LeftoverX < LeftoverY)
			xIncrment *= -1;
		else if (LeftoverX > LeftoverY)
			yIncrement *= -1;

		(((Alien) item).isAlive) = false;
		speed += 0.2;
	}

	public void update() {
		if ((xPos) >= maxX) {
			xIncrment *= -1;
		} else if (xPos <= 0) {
			xIncrment *= -1;
		}
		if ((yPos) >= maxY) {
			yIncrement *= -1;
		} else if (yPos <= 50) {
			yIncrement *= -1;
		}

		for (int i = bag_index - 1; i > 1; i--) {
			if (((Alien) bag[i]).isAlive == true)
				if (isCollide(bag[i])) {
					bounce(bag[i]);
					((Alien) bag[i]).isAlive = false;
				}

			bag_index--;
		}

		BarCollide(bag[0]);
		bag_index--;

		xPos += xIncrment * speed;
		yPos += yIncrement * speed;
	}
}