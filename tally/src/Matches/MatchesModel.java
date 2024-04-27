package Matches;

import java.util.ArrayList;

import API.APIInfo;

public class MatchesModel {

    public MatchesModel() {
        //APIInfo api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/eng.1/scoreboard");
        APIInfo api = new APIInfo(" https://site.api.espn.com/apis/site/v2/sports/basketball/nba/scoreboard");
        //api.getESPNMatchesAPI("");

        ArrayList<ArrayList<Object>> apiItems = api.getESPNMatchesAPI(null);

        for(int i = 0; i < apiItems.size(); i++)  {
            Object item = apiItems.get(i);
            System.out.println(item);
        }

    }
}
