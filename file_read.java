int levelnum = 0, enemy_rows = 1, enemy_speed = 1, enemy_shoot_percentage = 0, forcefield_height = 1;

Random rand = new Random();

public void initializeLevel() {
	string path = "l" + levelnum + ".txt";

	AssetManager am = context.getAssets();
	InputStream is = am.open(path);

	//Read file
	enemy_rows = is.ReadLine();
	enemy_speed = is.ReadLine();
	enemy_shoot_percentage = is.ReadLine();
	forcefield_height = is.ReadLine();

	is.close();

	//Create an array with dimensions 8 by enemy_rows
	Alien[][] enemies = new Alien[enemy_rows][8];
	
	//Fill enemy array
	for (int y = 0; y < enemy_rows; y++)
for(int x = 0; x < 8; x++) {
			int randomInt = rand.nextInt(3);
			enemies[y][x] = new Alien(randomInt, true, shoot_bool, timer, screenwidth / 8 * x, screenwidth / 8 * y);
		}
}

//output position
for(int i = 0; i < enemy_rows; i++) {
	for(int j = 0; j < 8; j++) {
		//Display alien
		int color = enemies[i][j].getColor();
		if (color == 0)
			show orange at enemies[i][j].getXPos(), enemies[i][j].getYPos();
		else if (color == 1)
			show purple at enemies[i][j].getXPos(), enemies[i][j].getYPos();
		else if (color == 2)
			show green at enemies[i][j].getXPos(), enemies[i][j].getYPos();		
	}
}