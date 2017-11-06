package problem2;

public class StarMap {
    private int width;
    private int height;
    private int[][] map;

    public StarMap(int width, int height){
        width = width;
        height = height;

        map = new int[height][width];
        System.out.println("New Star Map created.");
    }

    public void PrintMap(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(map[i][j] > 0){
                    System.out.print("*");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    public boolean PlotStar(int x, int y){
        System.out.println("Plotting your star now...");

        if(0 <= x && x < width && 0 <= y && y < height){
            map[y][x] = 0x1;
            return true;
        }

        System.out.println("Hm, seems that was outside of my bounds.");
        return false;
    }

    public boolean RemovePlot(int x, int y){
        System.out.println("Removing your star now...");

        try{
            int star_val = map[y][x];

            if(map[y][x] == 0x0){
                System.out.println("Sorry, it seems there is no star there.");
                return false;
            }

            map[x][y] = 0x0;

            return true;
        } catch(ArrayIndexOutOfBoundsException e){

            System.out.println("I do not have that in my plot.");
            return false;
        }
    }



}
