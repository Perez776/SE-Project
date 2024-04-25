package Matches;

import API.APIInfo;

public class MatchesModel {

    public MatchesModel() {
        //APIInfo api = new APIInfo("https://site.api.espn.com/apis/site/v2/sports/soccer/eng.1/scoreboard");
        APIInfo api = new APIInfo(" https://site.api.espn.com/apis/site/v2/sports/basketball/nba/scoreboard");
        api.getESPNMatchesAPI("");

    }
}
