package sejevic.com._DWorldGenerationGame.core.room;

import sejevic.com._DWorldGenerationGame.core.utils.Coordinate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//create a room with height and width dimensions
//farthest position from center is wall, rest are floor tiles
// add tiles coordinates to HashSet
public class Room {
  Coordinate centerPosition;
  int height;
  int width;
  HashSet<Coordinate> wallTiles = new HashSet<>();
  HashSet<Coordinate> floorTiles = new HashSet<>();

  //use centerPosition and height and width to calc wall and floor coordinates
  public Room(Coordinate centerPosition, int height , int width) {
    inputCheck(centerPosition, height, width);
    this.centerPosition = centerPosition;
    this.height = height;
    this.width = width;
    fillWall();
    fillFloor();
  }

  //checks for bad input
  private void inputCheck(Coordinate centerPosition, int height , int width) {
    if (centerPosition == null) {
      throw new IllegalArgumentException("centerPosition was null");
    }
    if (height < 2 || width < 2) {
      throw new IllegalArgumentException("minimum size fot height and width is 2");
    }
    if (centerPosition.y - height < 0 || centerPosition.x - width < 0) {
      throw new IllegalArgumentException("room is out of bounds");
    }
  }

  //splits filling a Wall in two functions
  private void fillWall() {
    fillWallLeftRight();
    fillWallUpDown();
  }

  //stay left or right side(first loop) and traverse from center + height to center - height(second loop)
  private void fillWallLeftRight() {
    for (int x = centerPosition.x - width; x <= centerPosition.x + width; x = x + 2 * width) {//jump from left to right
      for(int y = centerPosition.y - height; y <= centerPosition.y + height; y++) {
        wallTiles.add(new Coordinate(x,y));
      }
    }
  }

  //stay up or down (first loop) and traverse from center + width to center - width(second loop)
  private void fillWallUpDown() {
    for (int y = centerPosition.y - height; y <= centerPosition.y + height; y = y + 2 * height) {//jump from bot to top
      for(int x = centerPosition.x - width; x <= centerPosition.x + width; x++) {
        wallTiles.add(new Coordinate(x,y));
      }
    }
  }

  //wall edges are centerPosition.x (-+) width and centerPosition.y (-+) height
  //traverse in between and add coordinates to floorTiles
  private void fillFloor() {
    for (int x = centerPosition.x - width + 1; x < centerPosition.x + width ; x++) {
      for (int y = centerPosition.y - height + 1; y < centerPosition.y + height ; y++) {
        floorTiles.add(new Coordinate(x, y));
      }
    }
  }

  public List<Coordinate> getWalls() {
    return new ArrayList<>(wallTiles);
  }

  public List<Coordinate>  getFloor() {
    return new ArrayList<>(floorTiles);
  }

  //calc distance from center of one room to another
  public double distanceTo(Room other) {
    return centerPosition.distanceBettwen(other.centerPosition);
  }

  //get wall and floor coordinates
  public List<Coordinate> getAllCoordinates() {
    List<Coordinate> coordinates = getFloor();
    coordinates.addAll(getWalls());
    return coordinates;
  }

  //check if room occupies that space
  public boolean  containsCoordinate(Coordinate c) {
    return wallTiles.contains(c) || floorTiles.contains(c);
  }

  public Coordinate getCenter() {
    return centerPosition;
  }

  @Override
  public boolean equals (Object obj) {
    if(this == obj) { return true; }
    if (!(obj instanceof Room room)) { return false; }

    return this.centerPosition == room.centerPosition && this.width == room.width
            && this.height == room.height ;
  }

  @Override
  public int hashCode() {
    return 31 * centerPosition.x + centerPosition.y + width + height;
  }
}
