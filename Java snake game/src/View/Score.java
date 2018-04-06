package View;

//code for strings for the score name difficulty etc
public class Score {
    private  String name;
    private  String score;
    private  String difficulty;
    
    public Score(String name, String score, String difficulty){
        this.name = name;
        this.score = score;
        this.difficulty = difficulty;
    }
    
    public String getName(){
        return name;
    }
    
    public String getScore(){
        return score;
    }
    
    public String getDifficulty(){
        return difficulty;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setScore(String score){
        this.name = score;
    }
    
    public void setDifficulty(String difficulty){
        this.name = difficulty;
    }
    
}
