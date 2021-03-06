package com.mycompany.myapp.web.rest.morpion;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;
import com.mycompany.myapp.security.AuthoritiesConstants;
import java.io.StringWriter;

@RestController
//@Secured(AuthoritiesConstants.ADMIN)
@RequestMapping("/api")
public class RemoteCallTicTacToe {
    
    Case[][] table;
    BetterMiniMaxAI AI;
    
    /**
     * Assuming the playLoc is currently blank
     * 
     * @param playLoc
     *   a length-2 array containing coordinates of the play location of the player
     * @return
     *   a JSON containing the new table and the state of the game :
     *   0 : not finished
     *   1 : player win
     *   2 : player lose
     *   3 : draw
     */
    @GetMapping(path="/morpion/play", produces={"application/JSON"})
    public String playerMove(@RequestParam(value = "x") int x, @RequestParam(value = "y") int y){
        table[x][y].fill(Fill.x);//player fill
        
        int gameState = Main.detectEnd(table);
        
        if(gameState==0){
            //AI plays
            AI.chooseCaseToFill(table).fill(AI.getMyFill());
            gameState = Main.detectEnd(table);
        }//else do nothing

        JSONObject obj = new JSONObject();
        obj.put("board", getFills(table));
        obj.put("state", gameState);

        return obj.toString();
        //return "{board: "+JSONify(getFills(table))+", state: "+gameState+"}";
    }
    
    /**
     * @return
     *   the table in a JSON form
     */
    @GetMapping(path="/morpion/init", produces={"application/JSON"})
    public String init(@RequestParam(value = "startSize", defaultValue = "3") int startSize){
        table = new Case[startSize][startSize];
        for(int i = 0;i<startSize;++i){
            for(int j = 0;j<startSize;++j){
                table[i][j] = new Case(i,j);
            }
        }
        AI = new BetterMiniMaxAI(Fill.o, startSize);

        JSONObject obj = new JSONObject();
        obj.put("board", getFills(table));

        return obj.toString();
    }
    
    /**
     * table to fills
     */
    private String[][] getFills(Case[][] table){
        String[][] fills = new String[table.length][table[0].length];
        for(int i = 0;i<table.length;++i){
            for(int j = 0;j<table[i].length;++j){
                fills[i][j] = table[i][j].filledBy().toString();
            }
        }
        return fills;
    }
    
    /**
     * table to JSON
     */
    private String JSONify(String[][] table){
        String res = "[";
        
        for(String[] slist : table){
            res+="[";
            for(String s : slist){
                res+="{"+s+"},";
            }
            res=res.substring(0,res.length()-1)+"],";
        }
        
        return res.substring(0,res.length()-1)+"]";
    }
}